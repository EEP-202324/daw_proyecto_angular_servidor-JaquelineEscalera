package com.example.Universidad;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UniversidadApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    private Universidad[] universidades;

    @BeforeEach
    void setUp() {
        universidades = new Universidad[]{
                new Universidad(99L, "Nombre", "Apellido", "correo@example.com", "123456789"),
                new Universidad(100L, "OtroNombre", "OtroApellido", "otro@example.com", "987654321"),
                new Universidad(101L, "TercerNombre", "TercerApellido", "tercer@example.com", "555555555")
        };
    }

    @Test
    void shouldReturnAUniversidadWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidad/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(99);
        

        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Nombre");

        String apellido = documentContext.read("$.apellido");
        assertThat(apellido).isEqualTo("Apellido");

        String correo = documentContext.read("$.correo");
        assertThat(correo).isEqualTo("correo@example.com");

        String telefono = documentContext.read("$.telefono");
        assertThat(telefono).isEqualTo("123456789");
    }
    
    @Test
    void shouldNotReturnAUniversidadWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidad/1000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void shouldCreateANewUniversidad() {
        Universidad newUniversidad = new Universidad(null, "NuevoNombre", "NuevoApellido", "nuevo@example.com", "111111111");
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/universidad", newUniversidad, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewUniversidad = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewUniversidad, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String nombre = documentContext.read("$.nombre");
        String apellido = documentContext.read("$.apellido");
        String correo = documentContext.read("$.correo");
        String telefono = documentContext.read("$.telefono");

        assertThat(id).isNotNull();
        assertThat(nombre).isEqualTo("NuevoNombre");
        assertThat(apellido).isEqualTo("NuevoApellido");
        assertThat(correo).isEqualTo("nuevo@example.com");
        assertThat(telefono).isEqualTo("111111111");
    }
}
