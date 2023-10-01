package com.example.entregable3.model;

import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    private int lu;

	@Column(nullable = false)
	private String nombres;
	
	@Column(nullable = false)
	private String apellido;

	@Column(nullable = false)
	private int edad;

	@Column(nullable = false)
	private String genero;

	@Column(nullable = false)
	private String dni;

	@Column(nullable = false)
	private String ciudad;

	@OneToMany(mappedBy = "id.estudiante")
	private Set<Inscripcion> inscripcionSet;

	public Estudiante() {
		super();
	}

	public Estudiante(int lu, String nombres, String apellido, int edad, String genero, String dni, String ciudad) {
		super();
		this.lu = lu;
		this.nombres = nombres;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return lu + ", " + nombres + " " + apellido + ", edad: " + edad + ", " + genero + ", DNI: " + dni + ", "
				+ ciudad;
	}

	public int getLu() {
		return lu;
	}

	public void setLu(int lu) {
		this.lu = lu;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}
 
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
}
