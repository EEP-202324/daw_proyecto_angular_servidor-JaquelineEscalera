package com.example.Universidad;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UniversidadApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAUniversidadWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades/99", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(99);

        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Nombre1");

        String apellido = documentContext.read("$.apellido");
        assertThat(apellido).isEqualTo("Apellido1");

        String correo = documentContext.read("$.correo");
        assertThat(correo).isEqualTo("correo1@example.com");

        String numero = documentContext.read("$.numero");
        assertThat(numero).isEqualTo("123456789");
    }

    @Test
    void shouldNotReturnAUniversidadWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades/1000", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    @DirtiesContext
    void shouldCreateANewUniversidad() {
        Universidad newUniversidad = new Universidad(null, "NuevoNombre", "NuevoApellido", "nuevo@example.com", "000000000");
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/universidades", newUniversidad, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewUniversidad = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewUniversidad, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String nombre = documentContext.read("$.nombre");
        String apellido = documentContext.read("$.apellido");
        String correo = documentContext.read("$.correo");
        String numero = documentContext.read("$.numero");

        assertThat(id).isNotNull();
        assertThat(nombre).isEqualTo("NuevoNombre");
        assertThat(apellido).isEqualTo("NuevoApellido");
        assertThat(correo).isEqualTo("nuevo@example.com");
        assertThat(numero).isEqualTo("000000000");
    }

    @Test
    void shouldReturnAllUniversidadesWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int universidadCount = documentContext.read("$.length()");
        assertThat(universidadCount).isEqualTo(3);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(99, 100, 101);

        JSONArray nombres = documentContext.read("$..nombre");
        assertThat(nombres).containsExactlyInAnyOrder("Nombre1", "Nombre2", "Nombre3");
    }

    @Test
    void shouldReturnAPageOfUniversidades() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades?page=0&size=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnASortedPageOfUniversidades() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades?page=0&size=1&sort=nombre,desc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        String nombre = documentContext.read("$[0].nombre");
        assertThat(nombre).isEqualTo("Nombre3");
    }

    @Test
    void shouldReturnASortedPageOfUniversidadesWithNoParametersAndUseDefaultValues() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(3);

        JSONArray nombres = documentContext.read("$..nombre");
        assertThat(nombres).containsExactly("Nombre1", "Nombre2", "Nombre3");
    }

    @Test
    @DirtiesContext
    void shouldUpdateAnExistingUniversidad() {
        Universidad universidadUpdate = new Universidad(null, "ActualizadoNombre", "ActualizadoApellido", "actualizado@example.com", "111111111");
        HttpEntity<Universidad> request = new HttpEntity<>(universidadUpdate);
        ResponseEntity<Void> response = restTemplate.exchange("/universidades/99", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate.getForEntity("/universidades/99", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String nombre = documentContext.read("$.nombre");
        String apellido = documentContext.read("$.apellido");
        String correo = documentContext.read("$.correo");
        String numero = documentContext.read("$.numero");
        assertThat(id).isEqualTo(99);
        assertThat(nombre).isEqualTo("ActualizadoNombre");
        assertThat(apellido).isEqualTo("ActualizadoApellido");
        assertThat(correo).isEqualTo("actualizado@example.com");
        assertThat(numero).isEqualTo("111111111");
    }
}
