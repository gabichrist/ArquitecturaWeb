package com.mantenimiento.dto;

import com.mantenimiento.enums.EstadoMonopatinEnum;

import lombok.Data;

@Data
public class MonopatinTiempoSinPausas {

	private Long id;
	
	private EstadoMonopatinEnum estado;

	private Float tiempoUsoSinPausas;

}
