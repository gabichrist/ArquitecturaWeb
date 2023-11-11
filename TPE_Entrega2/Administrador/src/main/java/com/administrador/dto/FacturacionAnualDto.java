package com.administrador.dto;

import lombok.Data;

@Data
public class FacturacionAnualDto {
	private int anio;
	private int desdeMes;
	private int hastaMes;
	private Float totalFacturado;
}
