package com.viajesmonopatin.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.viajesmonopatin.enums.EstadoMonopatinEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Monopatin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private float latitud;
	
	@Column(nullable = false)
	private float longitud;
	
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMonopatinEnum estado;
	
	@Column(nullable = false)
	private float kilometrosRecorridos;
	
	@Column(nullable = false)
	private float tiempoUsoConPausas;
	
	@Column(nullable = false)
	private float tiempoUsoSinPausas;

	@JoinColumn
	@OneToMany
	private Set<Viaje> viajes;
	
	public Monopatin() {
		super();
		this.viajes = new HashSet<>();
	}

	public Monopatin(int id, float latitud, float longitud, EstadoMonopatinEnum estado, float kilometrosRecorridos,
			float tiempoUsoConPausas, float tiempoUsoSinPausas) {
		super();
		this.viajes = new HashSet<>();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = estado;
		this.kilometrosRecorridos = kilometrosRecorridos;
		this.tiempoUsoConPausas = tiempoUsoConPausas;
		this.tiempoUsoSinPausas = tiempoUsoSinPausas;
	}
	
	public Monopatin(int id, float latitud, float longitud, EstadoMonopatinEnum estado, float kilometrosRecorridos,
			float tiempoUsoConPausas, float tiempoUsoSinPausas, Collection<Viaje> viajes) {
		super();
		this.viajes = new HashSet<>(viajes);
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.estado = estado;
		this.kilometrosRecorridos = kilometrosRecorridos;
		this.tiempoUsoConPausas = tiempoUsoConPausas;
		this.tiempoUsoSinPausas = tiempoUsoSinPausas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public EstadoMonopatinEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoMonopatinEnum estado) {
		this.estado = estado;
	}

	public float getKilometrosRecorridos() {
		return kilometrosRecorridos;
	}

	public void setKilometrosRecorridos(float kilometrosRecorridos) {
		this.kilometrosRecorridos = kilometrosRecorridos;
	}

	public float getTiempoUsoConPausas() {
		return tiempoUsoConPausas;
	}

	public void setTiempoUsoConPausas(float tiempoUsoConPausas) {
		this.tiempoUsoConPausas = tiempoUsoConPausas;
	}

	public float getTiempoUsoSinPausas() {
		return tiempoUsoSinPausas;
	}

	public void setTiempoUsoSinPausas(float tiempoUsoSinPausas) {
		this.tiempoUsoSinPausas = tiempoUsoSinPausas;
	}
}
