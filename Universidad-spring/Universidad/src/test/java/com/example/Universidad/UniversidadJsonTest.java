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
                new Universidad(99L, "Juan", "Perez", "juan.perez@example.com", "1234567890"),
                new Universidad(100L, "Ana", "Gomez", "ana.gomez@example.com", "0987654321"),
                new Universidad(101L, "Carlos", "Sanchez", "carlos.sanchez@example.com", "1122334455"));
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
                .isEqualTo("Juan");
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.apellido");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.apellido")
                .isEqualTo("Perez");
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.correo");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.correo")
                .isEqualTo("juan.perez@example.com");
        assertThat(json.write(universidad)).hasJsonPathStringValue("@.numero");
        assertThat(json.write(universidad)).extractingJsonPathStringValue("@.numero")
                .isEqualTo("1234567890");
    }

    @Test
    void universidadDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 99,
                    "nombre": "Juan",
                    "apellido": "Perez",
                    "correo": "juan.perez@example.com",
                    "numero": "1234567890"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new Universidad(99L, "Juan", "Perez", "juan.perez@example.com", "1234567890"));
        assertThat(json.parseObject(expected).id()).isEqualTo(99L);
        assertThat(json.parseObject(expected).nombre()).isEqualTo("Juan");
        assertThat(json.parseObject(expected).apellido()).isEqualTo("Perez");
        assertThat(json.parseObject(expected).correo()).isEqualTo("juan.perez@example.com");
        assertThat(json.parseObject(expected).numero()).isEqualTo("1234567890");
    }

    @Test
    void universidadListSerializationTest() throws IOException {
        assertThat(jsonList.write(universidades)).isStrictlyEqualToJson("list.json");
    }

    @Test
    void universidadListDeserializationTest() throws IOException {
        String expected = """
                [
                     {"id": 99, "nombre": "Juan", "apellido": "Perez", "correo": "juan.perez@example.com", "numero": "1234567890"},
                     {"id": 100, "nombre": "Ana", "apellido": "Gomez", "correo": "ana.gomez@example.com", "numero": "0987654321"},
                     {"id": 101, "nombre": "Carlos", "apellido": "Sanchez", "correo": "carlos.sanchez@example.com", "numero": "1122334455"}
                ]
                """;
        assertThat(jsonList.parse(expected)).isEqualTo(universidades);
    }
}
