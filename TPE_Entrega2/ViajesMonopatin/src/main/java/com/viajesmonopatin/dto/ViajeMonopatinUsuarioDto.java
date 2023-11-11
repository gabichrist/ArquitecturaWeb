package com.viajesmonopatin.dto;

import lombok.Data;

@Data
public class ViajeMonopatinUsuarioDto {
	
	public Long idMonopatin;
	
	public Long idUsuario;
	
	public Long idCuenta;
	
	public Long idParadaDestino;
	
 	public ViajeMonopatinUsuarioDto() {
	}

	public ViajeMonopatinUsuarioDto(Long idMonopatin, Long idUsuario, Long idCuenta, Long idParadaDestino) {
		super();
		this.idMonopatin = idMonopatin;
		this.idUsuario = idUsuario;
		this.idCuenta = idCuenta;
		this.idParadaDestino = idParadaDestino;
	}

}
