package com.mantenimiento.dto;

import com.mantenimiento.enums.EstadoMonopatinEnum;

import lombok.Data;

@Data
public class MonopatinUsoDto {

	private Long id;
	
	private EstadoMonopatinEnum estado;
	
	private Float kilometrosRecorridos;
	
	private Float tiempoUsoConPausas;
	
	private Float tiempoUsoSinPausas;
	
}
