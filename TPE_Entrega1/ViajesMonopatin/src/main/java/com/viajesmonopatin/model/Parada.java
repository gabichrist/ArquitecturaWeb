package com.viajesmonopatin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Parada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String direccion;

	@Column(nullable = false)
	private Float latitud;

	@Column(nullable = false)
	private Float longitud;

	public Parada() {
		super();
	}

	public Parada(Long id, String direccion, float latitud, float longitud) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}
}
