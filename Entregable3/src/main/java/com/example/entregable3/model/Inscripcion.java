package com.example.entregable3.model;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Inscripcion {
	@JsonIgnore
	@EmbeddedId
	private InscripcionId id;

	@Column
	private Timestamp fechaIngreso;

	@Column(nullable = true)
	private Timestamp fechaEgreso;

	@Column(nullable = false)
	private boolean esGraduado;

	public Inscripcion() {
	}

	public Inscripcion(Estudiante estudiante, Carrera carrera, Timestamp fechaIngreso, Timestamp fechaEgreso,
			Boolean esGraduado) {
		super();
		this.id = new InscripcionId(estudiante, carrera);
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.esGraduado = esGraduado;
	}

	public Inscripcion(Estudiante estudiante, Carrera carrera) {
		super();
		this.id = new InscripcionId(estudiante, carrera);
		Date now = new Date();
		this.fechaIngreso = new Timestamp(now.getTime());
		this.fechaEgreso = null;
		this.esGraduado = false;
	}

	@JsonProperty("lu")
	private int jsonLu() {
		return this.id.getEstudiante().getLu();
	}	
	
	@JsonProperty("idCarrera")
	private int jsonIdCarrera() {
		return this.id.getCarrera().getIdCarrera();
	}
	
	@JsonProperty("estudiante")
	private String jsonEstudiante() {
		Estudiante e = this.id.getEstudiante();
		return e.getApellido() + ", " + e.getNombres();
	}

	@JsonProperty("carrera")
	private String stringCarrera() {
		return this.id.getCarrera().getNombre();
	}
	
	public InscripcionId getId() {
		return id;
	}

	public void setId(InscripcionId id) {
		this.id = id;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Timestamp fechaEgreso) {
		this.fechaEgreso = fechaEgreso;

		this.esGraduado = (fechaEgreso != null);
	}

	public boolean isEsGraduado() {
		return esGraduado;
	}

	public void setEsGraduado(boolean esGraduado) {
		this.esGraduado = esGraduado;
		if (!esGraduado)
			this.fechaEgreso = null;
	}

}
