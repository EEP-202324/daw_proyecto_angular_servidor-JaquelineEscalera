package com.example.Universidad;

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
				new Universidad(99L, "Nombre1", "Apellido1", "correo1@example.com", "123456789"),
                new Universidad(100L, "Nombre2", "Apellido2", "correo2@example.com", "987654321"),
                new Universidad(101L, "Nombre3", "Apellido3", "correo3@example.com", "555555555"));
	}

    @Test
    void universidadSerializationTest() throws IOException {
        Universidad universidad = universidades[0];
        assertThat(json.write(universidad)).isStrictlyEqualToJson("single.json");
        assertThat(json.write(universidad)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(universidad)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.nombre");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.nombre")
                .isEqualTo("Nombre1");
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.apellido");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.apellido")
                .isEqualTo("Apellido1");
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.correo");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.correo")
                .isEqualTo("correo1@example.com");
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.numero");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.numero")
                .isEqualTo("123456789");
    }

	@Test
	void universidadDeserializationTest() throws IOException {
		String expected = """
				 {
                    "id": 99,
                    "nombre": "Nombre1",
                    "apellido": "Apellido1",
                    "correo": "correo1@example.com",
                    "numero": "123456789"
                }
                """;
		assertThat(json.parse(expected))
				.isEqualTo(new Universidad(99L, "Nombre1", "Apellido1", "correo1@example.com", "123456789"));
		assertThat(json.parseObject(expected).getId()).isEqualTo(99L);
		assertThat(json.parseObject(expected).getNombre()).isEqualTo("Nombre1");
		assertThat(json.parseObject(expected).getApellido()).isEqualTo("Apellido1");
		assertThat(json.parseObject(expected).getCorreo()).isEqualTo("correo1@example.com");
		assertThat(json.parseObject(expected).getNumero()).isEqualTo("123456789");
	}

	@Test
	void universidadListSerializationTest() throws IOException {
		assertThat(jsonList.write(universidades)).isStrictlyEqualToJson("list.json");
	}

	@Test
    void universidadListDeserializationTest() throws IOException {
        String expected = """
                [
                    {"id": 99, "nombre": "Nombre1", "apellido": "Apellido1", "correo": "correo1@example.com", "numero": "123456789"},
                    {"id": 100, "nombre": "Nombre2", "apellido": "Apellido2", "correo": "correo2@example.com", "numero": "987654321"},
                    {"id": 101, "nombre": "Nombre3", "apellido": "Apellido3", "correo": "correo3@example.com", "numero": "555555555"}
                ]
                """;
        assertThat(jsonList.parse(expected)).isEqualTo(universidades);
    }
}









