package com.viajesmonopatin.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tarifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private Float tarifa;

	@Column(nullable = false)
	private Float tarifaExtra;

	@Column(nullable = false)
	private Timestamp validoDesde;

	public Tarifa() {
	}

	public Tarifa(int id, Float tarifa, Float tarifaExtra, Timestamp validoDesde) {
		this.id = id;
		this.tarifa = tarifa;
		this.tarifaExtra = tarifaExtra;
		this.validoDesde = validoDesde;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getTarifa() {
		return tarifa;
	}

	public void setTarifa(Float tarifa) {
		this.tarifa = tarifa;
	}

	public Float getTarifaExtra() {
		return tarifaExtra;
	}

	public void setTarifaExtra(Float tarifaExtra) {
		this.tarifaExtra = tarifaExtra;
	}

	public Timestamp getValidoDesde() {
		return validoDesde;
	}

	public void setValidoDesde(Timestamp validoDesde) {
		this.validoDesde = validoDesde;
	}

}
