package com.administrador.dto;

import lombok.Data;

@Data
public class ViajeMonopatinDto {

	private Long idMonopatin;

	private Long cantidadViajes;
	

	public ViajeMonopatinDto() {
	}

	public ViajeMonopatinDto(Long idMonopatin, Long cantidadViajes) {
		super();
		this.idMonopatin = idMonopatin;
		this.cantidadViajes = cantidadViajes;
	}
	
}