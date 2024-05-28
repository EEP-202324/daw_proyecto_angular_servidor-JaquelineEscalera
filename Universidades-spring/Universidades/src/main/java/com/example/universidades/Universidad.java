package com.example.universidades;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Universidad {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String gmail;
	private String numero;

	
	public Universidad() {
		
	}
	public Universidad(Long id, String nombre, String gmail, String numero) {
		this.id =id;
		this.nombre = nombre;
		this.gmail = gmail;
		this.numero = numero;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "Universidad [id=" + id + ", nombre=" + nombre + ", gmail=" + gmail + ", numero=" + numero + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(gmail, id, nombre, numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Universidad other = (Universidad) obj;
		return Objects.equals(gmail, other.gmail) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(numero, other.numero);
	}
	
}