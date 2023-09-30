package com.example.entregable3.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCarrera;

	@Column(nullable = false)
	private String nombre;

	@OneToMany(mappedBy = "id.carrera")
	private Set<Inscripcion> inscripcionSet;

	public Carrera() {
		super();
	}

	public Carrera(int idCarrera, String nombre) {
		super();
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		this.inscripcionSet = new HashSet<>();
	}
	
	@Override
	public String toString() {
		return "Nombre carrera: " + nombre;
	}

}
