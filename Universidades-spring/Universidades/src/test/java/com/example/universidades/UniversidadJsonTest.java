package com.example.universidades;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class UniversidadJsonTest {

	@Autowired
	private JacksonTester<Universidad> json;
	
	 @Autowired
	    private JacksonTester<Universidad[]> jsonList;

	    private Universidad[] universidades;

	    @BeforeEach
	    void setUp() {
	        universidades = Arrays.array(
	                new Universidad(1L, "nombre", "gmail", "numero"),
	                new Universidad(2L, "Juan", "juancho@gmail.com", "685993254"),
	                new Universidad(3L, "Marta", "mar@gmail.com", "685946684"));
	    }
	    
	@Test
	void universidadSerializationTest() throws IOException {
		Universidad universidad = new Universidad(1L, "nombre", "gmail", "numero");
		assertThat(json.write(universidad)).isStrictlyEqualToJson("single.json");
		assertThat(json.write(universidad)).hasJsonPathNumberValue("@.id");
		assertThat(json.write(universidad)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
		assertThat(json.write(universidad)).hasJsonPathStringValue("@.nombre");
		assertThat(json.write(universidad)).extractingJsonPathStringValue("@.nombre").isEqualTo("nombre");
		assertThat(json.write(universidad)).hasJsonPathStringValue("@.gmail");
		assertThat(json.write(universidad)).extractingJsonPathStringValue("@.gmail").isEqualTo("gmail");
		assertThat(json.write(universidad)).hasJsonPathStringValue("@.numero");
		assertThat(json.write(universidad)).extractingJsonPathStringValue("@.numero").isEqualTo("numero");
	}

	@Test
	void universidadDeserializationTest() throws IOException {
		String expected = """
				        {
				         "id" : 1,
				"nombre" : "nombre",
				"gmail" : "gmail",
				"numero" : "numero"
				        }
				        """;
		assertThat(json.parse(expected)).isEqualTo(new Universidad(1L, "nombre", "gmail", "numero"));
		assertThat(json.parseObject(expected).getId()).isEqualTo(1);
		assertThat(json.parseObject(expected).getNombre()).isEqualTo("nombre");
		assertThat(json.parseObject(expected).getGmail()).isEqualTo("gmail");
		assertThat(json.parseObject(expected).getNumero()).isEqualTo("numero");
	}
	
	@Test
	void universidadesListDeserializationTest() throws IOException {
	   String expected="""
	         [
  {"id": 1, "nombre":"nombre","gmail":"gmail", "numero":"numero" },
  {"id": 2, "nombre":"Juan","gmail":"juancho@gmail.com", "numero":"685993254" },
  {"id": 3, "nombre":"Marta","gmail":"mar@gmail.com", "numero":"685946684"  }
]
	         """;
	   assertThat(jsonList.parse(expected)).isEqualTo(universidades);
	}
}
