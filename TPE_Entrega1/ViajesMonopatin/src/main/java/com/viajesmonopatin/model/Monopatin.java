package com.viajesmonopatin.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.viajesmonopatin.dto.MonopatinDto;
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
	private Float latitud;
	
	@Column(nullable = false)
	private Float longitud;
	
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMonopatinEnum estado;
	
	@Column(nullable = false)
	private Float kilometrosRecorridos;
	
	@Column(nullable = false)
	private Float tiempoUsoConPausas;
	
	@Column(nullable = false)
	private Float tiempoUsoSinPausas;

	@JoinColumn
	@OneToMany
	private Set<Viaje> viajes;
	
	public Monopatin() {
		super();
		this.viajes = new HashSet<>();
	}

	public Monopatin(int id, Float latitud, Float longitud, EstadoMonopatinEnum estado, Float kilometrosRecorridos,
			Float tiempoUsoConPausas, Float tiempoUsoSinPausas) {
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
	
	public Monopatin(int id, Float latitud, Float longitud, EstadoMonopatinEnum estado, Float kilometrosRecorridos,
			Float tiempoUsoConPausas, Float tiempoUsoSinPausas, Collection<Viaje> viajes) {
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
	
	public Monopatin(MonopatinDto monopatin) {
		super();
		this.viajes = new HashSet<>();
		this.latitud = monopatin.getLatitud();
		this.longitud = monopatin.getLongitud();
		this.estado = monopatin.getEstado();
		this.kilometrosRecorridos = (float) 0;
		this.tiempoUsoConPausas = (float) 0;
		this.tiempoUsoSinPausas = (float) 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public EstadoMonopatinEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoMonopatinEnum estado) {
		this.estado = estado;
	}

	public Float getKilometrosRecorridos() {
		return kilometrosRecorridos;
	}

	public void setKilometrosRecorridos(Float kilometrosRecorridos) {
		this.kilometrosRecorridos = kilometrosRecorridos;
	}

	public Float getTiempoUsoConPausas() {
		return tiempoUsoConPausas;
	}

	public void setTiempoUsoConPausas(Float tiempoUsoConPausas) {
		this.tiempoUsoConPausas = tiempoUsoConPausas;
	}

	public Float getTiempoUsoSinPausas() {
		return tiempoUsoSinPausas;
	}

	public void setTiempoUsoSinPausas(Float tiempoUsoSinPausas) {
		this.tiempoUsoSinPausas = tiempoUsoSinPausas;
	}
}
