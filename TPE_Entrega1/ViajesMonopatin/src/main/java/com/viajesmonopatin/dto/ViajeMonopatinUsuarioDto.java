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

	public Long getIdMonopatin() {
		return idMonopatin;
	}

	public void setIdMonopatin(Long idMonopatin) {
		this.idMonopatin = idMonopatin;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Long getIdParadaDestino() {
		return idParadaDestino;
	}

	public void setIdParadaDestino(Long idParadaDestino) {
		this.idParadaDestino = idParadaDestino;
	}	

	@Override
	public String toString() {
		return "ViajeMonopatinUsuarioDto [idMonopatin=" + idMonopatin + ", idUsuario=" + idUsuario + ", idParadaDestino=" + idParadaDestino + "]";
	}
				
}
