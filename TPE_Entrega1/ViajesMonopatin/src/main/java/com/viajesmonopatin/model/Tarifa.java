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

	@Column
	private float tarifa;

	@Column
	private float tarifaExtra;

	@Column
	private Timestamp validoDesde;

	public Tarifa() {
		super();
	}

	public Tarifa(int id, float tarifa, float tarifaExtra, Timestamp validoDesde) {
		super();
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

	public float getTarifa() {
		return tarifa;
	}

	public void setTarifa(float tarifa) {
		this.tarifa = tarifa;
	}

	public float getTarifaExtra() {
		return tarifaExtra;
	}

	public void setTarifaExtra(float tarifaExtra) {
		this.tarifaExtra = tarifaExtra;
	}

	public Timestamp getValidoDesde() {
		return validoDesde;
	}

	public void setValidoDesde(Timestamp validoDesde) {
		this.validoDesde = validoDesde;
	}

}
