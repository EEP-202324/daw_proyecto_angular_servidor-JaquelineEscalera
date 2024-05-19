package com.example.Universidad;

import org.springframework.data.annotation.Id;

record Universidad(@Id Long id, String nombre, String apellido, String correo, String numero) {
}
