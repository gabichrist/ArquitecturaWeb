package com.example.entregable3.dtos;

import java.sql.Timestamp;

public class InscripcionDTO {
	private Long lu;

	private Long idCarrera;

	private Timestamp fechaIngreso;

	private Timestamp fechaEgreso;

	private Boolean esGraduado;

	public InscripcionDTO() {
	}
	
	public InscripcionDTO(Long lu, Long idCarrera) {
		this.lu = lu;
		this.idCarrera = idCarrera;
	}
	
	public Long getLu() {
		return lu;
	}

	public void setLu(Long lu) {
		this.lu = lu;
	}

	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
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

	public Boolean isEsGraduado() {
		return esGraduado;
	}

	public void setEsGraduado(boolean esGraduado) {
		this.esGraduado = esGraduado;
	}

}
