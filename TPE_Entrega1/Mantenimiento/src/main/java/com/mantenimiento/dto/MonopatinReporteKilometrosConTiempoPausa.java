package com.mantenimiento.dto;

import com.mantenimiento.enums.EstadoMonopatinEnum;

import lombok.Data;

@Data
public class MonopatinReporteKilometrosConTiempoPausa {

	private Long id;
	
	private EstadoMonopatinEnum estado;
	
	private Float kilometrosRecorridos;

	private float minutosEnPausa;
	
	public MonopatinReporteKilometrosConTiempoPausa(MonopatinUsoDto monopatinUsoDto) {
		this.id = monopatinUsoDto.getId();
		this.estado = monopatinUsoDto.getEstado();
		this.kilometrosRecorridos = monopatinUsoDto.getKilometrosRecorridos();
		this.minutosEnPausa = monopatinUsoDto.getTiempoUsoConPausas() - monopatinUsoDto.getTiempoUsoSinPausas();
	}
}
