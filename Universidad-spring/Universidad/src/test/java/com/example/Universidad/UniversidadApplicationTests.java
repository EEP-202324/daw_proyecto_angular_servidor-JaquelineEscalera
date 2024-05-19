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
        assertThat(nombre).isEqualTo("Juan");

        String apellido = documentContext.read("$.apellido");
        assertThat(apellido).isEqualTo("Perez");

        String correo = documentContext.read("$.correo");
        assertThat(correo).isEqualTo("juan.perez@example.com");

        String numero = documentContext.read("$.numero");
        assertThat(numero).isEqualTo("1234567890");
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
        Universidad newUniversidad = new Universidad(null, "Maria", "Lopez", "maria.lopez@example.com", "0987654321");
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
        assertThat(nombre).isEqualTo("Maria");
        assertThat(apellido).isEqualTo("Lopez");
        assertThat(correo).isEqualTo("maria.lopez@example.com");
        assertThat(numero).isEqualTo("0987654321");
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
        assertThat(nombres).containsExactlyInAnyOrder("Juan", "Ana", "Carlos");

        JSONArray apellidos = documentContext.read("$..apellido");
        assertThat(apellidos).containsExactlyInAnyOrder("Perez", "Gomez", "Sanchez");

        JSONArray correos = documentContext.read("$..correo");
        assertThat(correos).containsExactlyInAnyOrder("juan.perez@example.com", "ana.gomez@example.com", "carlos.sanchez@example.com");

        JSONArray numeros = documentContext.read("$..numero");
        assertThat(numeros).containsExactlyInAnyOrder("1234567890", "0987654321", "1122334455");
    }

    @Test
    void shouldReturnAPageOfUniversidades() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades?page=0&size=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);

        Number id = documentContext.read("$[0].id");
        assertThat(id).isEqualTo(99);

        String nombre = documentContext.read("$[0].nombre");
        assertThat(nombre).isEqualTo("Juan");

        String apellido = documentContext.read("$[0].apellido");
        assertThat(apellido).isEqualTo("Perez");

        String correo = documentContext.read("$[0].correo");
        assertThat(correo).isEqualTo("juan.perez@example.com");

        String numero = documentContext.read("$[0].numero");
        assertThat(numero).isEqualTo("1234567890");
    }

    @Test
    void shouldReturnASortedPageOfUniversidades() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades?page=0&size=1&sort=id,desc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        Number id = documentContext.read("$[0].id");
        assertThat(id).isEqualTo(101);

        String nombre = documentContext.read("$[0].nombre");
        assertThat(nombre).isEqualTo("Carlos");

        String apellido = documentContext.read("$[0].apellido");
        assertThat(apellido).isEqualTo("Sanchez");

        String correo = documentContext.read("$[0].correo");
        assertThat(correo).isEqualTo("carlos.sanchez@example.com");

        String numero = documentContext.read("$[0].numero");
        assertThat(numero).isEqualTo("1122334455");
    }

    @Test
    @DirtiesContext
    void shouldUpdateAnExistingUniversidad() {
        Universidad universidadUpdate = new Universidad(null, "Roberto", "Martinez", "roberto.martinez@example.com", "1123581321");
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
        assertThat(nombre).isEqualTo("Roberto");
        assertThat(apellido).isEqualTo("Martinez");
        assertThat(correo).isEqualTo("roberto.martinez@example.com");
        assertThat(numero).isEqualTo("1123581321");
    }

    @Test
    void shouldNotUpdateAUniversidadThatDoesNotExist() {
        Universidad unknownUniversidad = new Universidad(null, "Roberto", "Martinez", "roberto.martinez@example.com", "1123581321");
        HttpEntity<Universidad> request = new HttpEntity<>(unknownUniversidad);
        ResponseEntity<Void> response = restTemplate.exchange("/universidades/99999", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
