package com.viajesmonopatin.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class CuentaDto {

	private Long id_cuenta;

	private Timestamp fecha_alta;

	private int id_mercado_pago;

	private float saldo;
	
	private List<Long> idUsuarios;
}
