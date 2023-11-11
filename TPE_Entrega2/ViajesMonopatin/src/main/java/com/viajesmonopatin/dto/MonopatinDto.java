package com.viajesmonopatin.dto;

import com.viajesmonopatin.enums.EstadoMonopatinEnum;

import lombok.Data;

@Data
public class MonopatinDto {

	private Long id;

	private Float latitud;
	
	private Float longitud;
	
    private EstadoMonopatinEnum estado;

    private Long idParada;

	public MonopatinDto() {
	}
	
	public MonopatinDto(Long id, Float latitud, Float longitud, EstadoMonopatinEnum estado) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = estado;
	}

}
