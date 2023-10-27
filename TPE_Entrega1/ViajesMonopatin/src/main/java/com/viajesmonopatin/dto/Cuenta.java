package com.viajesmonopatin.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Cuenta {

	private Long id_cuenta;

	private Timestamp fecha_alta;

	private int id_mercado_pago;

	private float saldo;
}
