package com.example.entregable3.model;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "estudiantes")
public class Estudiante implements Serializable{
    @Id
    private int LU;

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

	public Estudiante(int lU, String nombres, String apellido, int edad, String genero, String dni, String ciudad) {
		super();
		LU = lU;
		this.nombres = nombres;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return LU + ", " + nombres + " " + apellido + ", edad: " + edad + ", " + genero + ", DNI: " + dni + ", "
				+ ciudad;
	}
}
