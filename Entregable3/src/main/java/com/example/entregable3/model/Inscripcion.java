package com.example.entregable3.model;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Inscripcion {
	@Id
	@EmbeddedId
	private InscripcionId id;

	@ManyToOne
	@JoinColumn(name = "lu", referencedColumnName = "lu", insertable = false, updatable = false)
	private Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "idCarrera", referencedColumnName = "idCarrera", insertable = false, updatable = false)
	private Carrera carrera;

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

	public InscripcionId getId() {
		return id;
	}

	public void setId(InscripcionId id) {
		this.id = id;
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
	}

	public boolean isEsGraduado() {
		return esGraduado;
	}

	public void setEsGraduado(boolean esGraduado) {
		this.esGraduado = esGraduado;
	}

}
