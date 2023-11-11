package com.administrador.dto;

import java.util.List;

import com.administrador.enums.EstadoMonopatinEnum;

import lombok.Data;

@Data
public class MonopatinDto {

	private Long id;

	private Float latitud;
	
	private Float longitud;
	
    private EstadoMonopatinEnum estado;
	
	private Float kilometrosRecorridos;
	
	private Float tiempoUsoConPausas;
	
	private Float tiempoUsoSinPausas;

	private List<ViajeDto> viajes;
}
