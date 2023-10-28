package com.viajesmonopatin.dto;

public class ViajeMonopatinUsuarioDto {
	
	public int idMonopatin;
	
	public int idUsuario;
	
	public int idCuenta;
	
	public int idParadaInicio;
	
	public int idParadaDestino;
	
 	public ViajeMonopatinUsuarioDto() {
		super();
	}

	public ViajeMonopatinUsuarioDto(int idMonopatin, int idUsuario, int idCuenta, int idParadaInicio, int idParadaDestino) {
		super();
		this.idMonopatin = idMonopatin;
		this.idUsuario = idUsuario;
		this.idCuenta = idCuenta;
		this.idParadaInicio = idParadaInicio;
		this.idParadaDestino = idParadaDestino;
	}

	public int getIdMonopatin() {
		return idMonopatin;
	}

	public void setIdMonopatin(int idMonopatin) {
		this.idMonopatin = idMonopatin;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
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
