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
	private Long id;

	@JoinColumn
	@ManyToOne
	private Monopatin monopatin;

	@Column
	private Long idUsuario;

	@Column
	private Long idCuenta;

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
	
	@Column
	private Float costoViaje;

	public Viaje() {
		super();
	}

	public Viaje(Long id, Monopatin monopatin, Long idUsuario, Long idCuenta, Parada paradaInicio, Parada paradaDestino,
			EstadoViajeEnum estado, Timestamp tiempoInicio, Timestamp tiempoFin, Timestamp tiempoPausaInicio,
			Timestamp tiempoPausaFin, float kilometrosRecorridos) {
		super();
		this.id = id;
		this.monopatin = monopatin;
		this.idUsuario = idUsuario;
		this.idCuenta = idCuenta;
		this.paradaInicio = paradaInicio;
		this.paradaDestino = paradaDestino;
		this.estado = estado;
		this.tiempoInicio = tiempoInicio;
		this.tiempoFin = tiempoFin;
		this.tiempoPausaInicio = tiempoPausaInicio;
		this.tiempoPausaFin = tiempoPausaFin;
		this.kilometrosRecorridos = kilometrosRecorridos;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Monopatin getMonopatin() {
		return monopatin;
	}

	public void setMonopatin(Monopatin monopatin) {
		this.monopatin = monopatin;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Parada getParadaInicio() {
		return paradaInicio;
	}

	public void setParadaInicio(Parada paradaInicio) {
		this.paradaInicio = paradaInicio;
	}

	public Parada getParadaDestino() {
		return paradaDestino;
	}

	public void setParadaDestino(Parada paradaDestino) {
		this.paradaDestino = paradaDestino;
	}

	public EstadoViajeEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoViajeEnum estado) {
		this.estado = estado;
	}

	public Timestamp getTiempoInicio() {
		return tiempoInicio;
	}

	public void setTiempoInicio(Timestamp tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public Timestamp getTiempoFin() {
		return tiempoFin;
	}

	public void setTiempoFin(Timestamp tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	public Timestamp getTiempoPausaInicio() {
		return tiempoPausaInicio;
	}

	public void setTiempoPausaInicio(Timestamp tiempoPausaInicio) {
		this.tiempoPausaInicio = tiempoPausaInicio;
	}

	public Timestamp getTiempoPausaFin() {
		return tiempoPausaFin;
	}

	public void setTiempoPausaFin(Timestamp tiempoPausaFin) {
		this.tiempoPausaFin = tiempoPausaFin;
	}

	public float getKilometrosRecorridos() {
		return kilometrosRecorridos;
	}

	public void setKilometrosRecorridos(float kilometrosRecorridos) {
		this.kilometrosRecorridos = kilometrosRecorridos;
	}
	
	public Float getCostoViaje() {
		return this.costoViaje;
	}
	
	public void setCostoViaje(Float costoViaje) {
		this.costoViaje = costoViaje;
	}	

	@Override
	public String toString() {
		return "Viaje [id=" + id + ", monopatin=" + monopatin + ", idUsuario=" + idUsuario + ", idCuenta=" + idCuenta
				+ ", paradaInicio=" + paradaInicio + ", paradaDestino=" + paradaDestino + ", estado=" + estado
				+ ", tiempoInicio=" + tiempoInicio + ", tiempoFin=" + tiempoFin + ", tiempoPausaInicio="
				+ tiempoPausaInicio + ", tiempoPausaFin=" + tiempoPausaFin + ", kilometrosRecorridos="
				+ kilometrosRecorridos + "]";
	}	
	
}
