package com.administrador.dto;

import java.sql.Timestamp;

import com.administrador.enums.EstadoViajeEnum;

import lombok.Data;


@Data
public class ViajeDto {

	private Long id;

	private MonopatinDto monopatin;

	private int idUsuario;

	private int idCuenta;

	private ParadaDto paradaInicio;
	
	private ParadaDto paradaDestino;
	
	private EstadoViajeEnum estado;
	
	private Timestamp tiempoInicio;
	
	private Timestamp tiempoFin;
	
	private Timestamp tiempoPausaInicio;
	
	private Timestamp tiempoPausaFin;
	
	private float kilometrosRecorridos;
}
