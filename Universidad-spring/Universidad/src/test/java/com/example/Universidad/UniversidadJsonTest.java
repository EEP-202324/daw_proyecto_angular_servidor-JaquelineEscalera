package com.example.Universidad;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class UniversidadJsonTest {

    @Autowired
    private JacksonTester<Universidad> json;

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
    void universidadSerializationTest() throws IOException {
        Universidad universidad = universidades[0];
        assertThat(json.write(universidad)).isStrictlyEqualToJson("single.json");
        assertThat(json.write(universidad)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(universidad)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.nombre");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.nombre").isEqualTo("Nombre");
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.apellido");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.apellido").isEqualTo("Apellido");
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.correo");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.correo").isEqualTo("correo@example.com");
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.telefono");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.telefono").isEqualTo("123456789");
    }

    @Test
    void universidadDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 99,
                    "nombre": "Nombre1",
                    "apellido": "Apellido1",
                    "correo": "correo1@example.com",
                    "telefono": "123456789"
                }
                """;
        assertThat(json.parse(expected)).isEqualTo(new Universidad(99L, "Nombre", "Apellido", "correo@example.com", "123456789"));
        assertThat(json.parseObject(expected).id()).isEqualTo(99);
        assertThat(json.parseObject(expected).nombre()).isEqualTo("Nombre");
        assertThat(json.parseObject(expected).apellido()).isEqualTo("Apellido");
        assertThat(json.parseObject(expected).correo()).isEqualTo("correo@example.com");
        assertThat(json.parseObject(expected).telefono()).isEqualTo("123456789");
    }
}
