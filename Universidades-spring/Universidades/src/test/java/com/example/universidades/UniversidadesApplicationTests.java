package com.example.universidades;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
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
	@DirtiesContext
	void shouldCreateANewUniversidad() {
		Universidad newUniversidad = new Universidad(1L, "nombre", "gmail", "numero");
		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/universidades", newUniversidad, Void.class);

		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewUniversidad = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewUniversidad, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	@Test
	void shouldReturnAllUniversidadWhenListIsRequested() {
		ResponseEntity<String> response = restTemplate.getForEntity("/universidades", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		int universidadCount = documentContext.read("$.length()");
		assertThat(universidadCount).isEqualTo(2);

		JSONArray ids = documentContext.read("$..id");
		assertThat(ids).containsExactlyInAnyOrder(1, 3);

		JSONArray nombres = documentContext.read("$..nombre");
		assertThat(nombres).containsExactlyInAnyOrder("Universidad Complutense de Madrid", "Marta");

		JSONArray gmails = documentContext.read("$..gmail");
		assertThat(gmails).containsExactlyInAnyOrder("juancho@gmail.com",  "mar@gmail.com");

		JSONArray numeros = documentContext.read("$..numero");
		assertThat(numeros).containsExactlyInAnyOrder("685993254",  "685946684");
	}

	@Test
	@DirtiesContext
	void shouldUpdateAnExistingUniversidad() {
		Universidad universidadUpdate = new Universidad(1L, "Universidad Complutense de Madrid", "juancho@gmail.com"
	,"685993254");
		HttpEntity<Universidad> request = new HttpEntity<>(universidadUpdate);
		ResponseEntity<Void> response = restTemplate.exchange("/universidades/1", HttpMethod.PUT, request, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

		ResponseEntity<String> getResponse = restTemplate.getForEntity("/universidades/1", String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
		Number id = documentContext.read("$.id");
		String nombre = documentContext.read("$.nombre");
		String gmail = documentContext.read("$.gmail");
		String numero = documentContext.read("$.numero");
		assertThat(id).isEqualTo(1);
		assertThat(nombre).isEqualTo("Universidad Complutense de Madrid");
		assertThat(gmail).isEqualTo("juancho@gmail.com");
		assertThat(numero).isEqualTo("685993254");
	}
	
	@Test
	void shouldNotUpdateAUniversidadThatDoesNotExist() {
	    Universidad unknownCard = new  Universidad(null, "Debla", null, null);
	    HttpEntity< Universidad> request = new HttpEntity<>(unknownCard);
	    ResponseEntity<Void> response = restTemplate
	            .exchange("/ universidades/99999", HttpMethod.PUT, request, Void.class);
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	@DirtiesContext
	void shouldDeleteAnExistingUniversidad() {
	    ResponseEntity<Void> response = restTemplate
	            .exchange("/universidades/2", HttpMethod.DELETE, null, Void.class);
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	    
	 // Add the following code:
	    ResponseEntity<String> getResponse = restTemplate
	            .getForEntity("/universidades/2", String.class);
	    assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}
