package com.example.entregable3.model;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Inscripcion {
	@Id
	@EmbeddedId
	private InscripcionId id;

	@ManyToOne
	@JoinColumn(name = "LU", referencedColumnName = "LU", insertable = false, updatable = false)
	private Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "idCarrera", referencedColumnName = "idCarrera", insertable = false, updatable = false)
	private Carrera carrera;

	@Column
	private Timestamp fecha_ingreso;

	@Column(nullable = true)
	private Timestamp fecha_egreso;

	@Column(nullable = false)
	private boolean esGraduado;

	public Inscripcion() {
	}

	public Inscripcion(Estudiante estudiante, Carrera carrera, Timestamp fecha_ingreso, Timestamp fecha_egreso,
			Boolean esGraduado) {
		super();
		this.id = new InscripcionId(estudiante, carrera);
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_egreso = fecha_egreso;
		this.esGraduado = esGraduado;
	}

	public Inscripcion(Estudiante estudiante, Carrera carrera) {
		super();
		this.id = new InscripcionId(estudiante, carrera);
		Date now = new Date();
		this.fecha_ingreso = new Timestamp(now.getTime());
		this.fecha_egreso = null;
		this.esGraduado = false;
	}

}
