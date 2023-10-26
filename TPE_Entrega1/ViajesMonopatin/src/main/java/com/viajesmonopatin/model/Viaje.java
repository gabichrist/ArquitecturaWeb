package com.viajesmonopatin.model;

import java.sql.Timestamp;

import com.viajesmonopatin.enums.EstadoViajeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Viaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn
	@ManyToOne
	private Monopatin monopatin;

	@Column
	private int idUsuario;

	@Column
	private int idCuenta;

	@JoinColumn
	@ManyToOne
	private Parada paradaInicio;
	
	@JoinColumn
	@ManyToOne
	private Parada paradaDestino;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EstadoViajeEnum estado;
	
	@Column
	private Timestamp tiempoInicio;
	
	@Column
	private Timestamp tiempoFin;
	
	@Column
	private Timestamp tiempoPausaInicio;
	
	@Column
	private Timestamp tiempoPausaFin;
	
	@Column
	private float kilometrosRecorridos;

	public Viaje() {
		super();
	}
	
}
