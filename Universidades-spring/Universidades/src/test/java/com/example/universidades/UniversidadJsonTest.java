package com.example.universidades;

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

	@Test
	void universidadSerializationTest() throws IOException {
		Universidad universidad = new Universidad(1L, "nombre", "gmail", "numero");
		assertThat(json.write(universidad)).isStrictlyEqualToJson("expected.json");
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
}
