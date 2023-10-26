package com.mantenimiento.model;

import java.sql.Timestamp;

import com.viajesmonopatin.model.Monopatin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Mantenimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_mantenimiento;
	
	@JoinColumn
	@ManyToOne
	private Monopatin monopatin;
	
	@Column(nullable = false)
	private	Timestamp inicio;
	
	@Column 
	private Timestamp fin;
	
	@Column
	private String descripcion;

	public Mantenimiento() {
		super();
	}

	public Mantenimiento(int id, Monopatin monopatin, Timestamp inicio, Timestamp fin, String descripcion) {
		super();
		this.id_mantenimiento = id;
		this.monopatin = monopatin;
		this.inicio = inicio;
		this.fin = fin;
		this.descripcion = descripcion;
	}

	public Monopatin getMonopatin() {
		return monopatin;
	}

	public void setMonopatin(Monopatin monopatin) {
		this.monopatin = monopatin;
	}

	public Timestamp getInicio() {
		return inicio;
	}

	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}

	public Timestamp getFin() {
		return fin;
	}

	public void setFin(Timestamp fin) {
		this.fin = fin;
	}

	public int getId() {
		return id_mantenimiento;
	}

	@Override
	public String toString() {
		return "Mantenimiento [id=" + id_mantenimiento + ", monopatin=" + monopatin + ", inicio=" + inicio + ", fin=" + fin + "]";
	}

	
}
