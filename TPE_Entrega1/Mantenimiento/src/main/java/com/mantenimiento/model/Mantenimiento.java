package com.mantenimiento.model;

import java.sql.Timestamp;

//import com.viajesmonopatin.model.Monopatin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;

@Entity
public class Mantenimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@JoinColumn
	//@ManyToOne
	//private Monopatin monopatin;
	
	//@Column(name = "id_monopatin")
	//private Long monopatin;
	
	@Column(nullable = false)
	private	Timestamp inicio;
	
	@Column 
	private Timestamp fin;
	
	@Column
	private String descripcion;

	public Mantenimiento() {
		super();
	}

	public Mantenimiento(Long id, Timestamp inicio, Timestamp fin, String descripcion) {
		super();
		this.id = id;
		//this.monopatin = monopatin;
		this.inicio = inicio;
		this.fin = fin;
		this.descripcion = descripcion;
	}

	//public Monopatin getMonopatin() {
	//	return monopatin;
	//}

	//public void setMonopatin(long idMonopatin) {
	//	this.monopatin = idMonopatin;
	//}

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

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	//@Override
	//public String toString() {
	//	return "Mantenimiento [id=" + id + ", monopatin=" + monopatin + ", inicio=" + inicio + ", fin=" + fin + "]";
//	}

	
}
