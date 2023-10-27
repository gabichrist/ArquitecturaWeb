package com.viajesmonopatin.dto;

import java.sql.Timestamp;

public class ViajeMonopatinUsuarioDto {
	
	public Long idMonopatin;
	
	public Long idUsuario;
	
	public int idParadaInicio;
	
	public int idParadaDestino;
	
 	public ViajeMonopatinUsuarioDto() {
		super();
	}

	public ViajeMonopatinUsuarioDto(Long idMonopatin, Long idUsuario, int idParadaInicio, int idParadaDestino) {
		super();
		this.idMonopatin = idMonopatin;
		this.idUsuario = idUsuario;
		this.idParadaInicio = idParadaInicio;
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

	public int getIdParadaInicio() {
		return idParadaInicio;
	}

	public void setIdParadaInicio(int idParadaInicio) {
		this.idParadaInicio = idParadaInicio;
	}

	public int getIdParadaDestino() {
		return idParadaDestino;
	}

	public void setIdParadaDestino(int idParadaDestino) {
		this.idParadaDestino = idParadaDestino;
	}	

	@Override
	public String toString() {
		return "ViajeMonopatinUsuarioDto [idMonopatin=" + idMonopatin + ", idUsuario=" + idUsuario + ", idParadaInicio="
				+ idParadaInicio + ", idParadaDestino=" + idParadaDestino + "]";
	}
				
}
