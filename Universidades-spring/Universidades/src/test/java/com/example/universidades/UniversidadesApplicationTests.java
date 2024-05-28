package com.example.universidades;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UniversidadesApplicationTests {
	@Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAUniversidadWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/universidades/1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1);
        
        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("nombre");
        
        String gmail = documentContext.read("$.gmail");
        assertThat(gmail).isEqualTo("gmail");
        
        String numero = documentContext.read("$.numero");
        assertThat(numero).isEqualTo("numero");
    }
    
    @Test
    void shouldNotReturnAUniversidadWithAnUnknownId() {
      ResponseEntity<String> response = restTemplate.getForEntity("/universidades/999", String.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
      assertThat(response.getBody()).isBlank();
    }
    
    @Test
    void shouldCreateANewUniversidad() {
       Universidad newUniversidad = new Universidad(2L, "Carlos", "carlos@gamil.com", "625449585");
       ResponseEntity<Void> createResponse = restTemplate.postForEntity("/universidades", newUniversidad, Void.class);
      
       assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
      
       URI locationOfNewUniversidad = createResponse.getHeaders().getLocation();
       ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewUniversidad, String.class);
       assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    }
}
