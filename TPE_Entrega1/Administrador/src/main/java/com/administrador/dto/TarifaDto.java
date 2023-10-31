package com.administrador.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TarifaDto {

	private Long id;

	private Float tarifa;

	private Float tarifaExtra;

	private Timestamp validoDesde;
}
