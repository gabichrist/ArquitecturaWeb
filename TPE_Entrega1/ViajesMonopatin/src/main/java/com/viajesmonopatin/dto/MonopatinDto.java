package com.viajesmonopatin.dto;

import com.viajesmonopatin.enums.EstadoMonopatinEnum;

import lombok.Data;

@Data
public class MonopatinDto {

	private int id;

	private Float latitud;
	
	private Float longitud;
	
    private EstadoMonopatinEnum estado;

	public MonopatinDto() {
	}
	
	public MonopatinDto(int id, Float latitud, Float longitud, EstadoMonopatinEnum estado) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public EstadoMonopatinEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoMonopatinEnum estado) {
		this.estado = estado;
	}

}
