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
	
	@Column(name = "id_monopatin")
	private Long idMonopatin;
	
	@Column(nullable = false)
	private	Timestamp inicio;
	
	@Column 
	private Timestamp fin;
	
	@Column
	private String descripcion;

	public Mantenimiento() {
		super();
	}

	public Mantenimiento(Long id, Long idMonopatin, Timestamp inicio, Timestamp fin, String descripcion) {
		super();
		this.id = id;
		this.idMonopatin = idMonopatin;
		this.inicio = inicio;
		this.fin = fin;
		this.descripcion = descripcion;
	}

	public Long getIdMonopatin() {
		return idMonopatin;
	}

	public void setIdMonopatin(Long idMonopatin) {
		this.idMonopatin = idMonopatin;
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

	@Override
	public String toString() {
		return "Mantenimiento [id=" + id + ", idMonopatin=" + idMonopatin + ", inicio=" + inicio + ", fin=" + fin + "]";
	}	
}
