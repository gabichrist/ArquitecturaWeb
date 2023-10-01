package com.example.entregable3.model;

import java.io.Serializable;


import jakarta.persistence.*;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InscripcionId implements Serializable {

	@ManyToOne
	@JoinColumn(name = "lu")
	private Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "idCarrera")
	private Carrera carrera;

	public InscripcionId() {
		super();
	}

	public InscripcionId(Estudiante estudiante, Carrera carrera) {
		this.estudiante = estudiante;
		this.carrera = carrera;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

}
