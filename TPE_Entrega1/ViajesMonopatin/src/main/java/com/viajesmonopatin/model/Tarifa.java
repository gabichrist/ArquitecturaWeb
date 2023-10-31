package com.viajesmonopatin.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Tarifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Float tarifa;

	@Column(nullable = false)
	private Float tarifaExtra;

	@Column(nullable = false)
	private Timestamp validoDesde;

	public Tarifa() {
	}

	public Tarifa(Long id, Float tarifa, Float tarifaExtra, Timestamp validoDesde) {
		this.id = id;
		this.tarifa = tarifa;
		this.tarifaExtra = tarifaExtra;
		this.validoDesde = validoDesde;
	}

}
