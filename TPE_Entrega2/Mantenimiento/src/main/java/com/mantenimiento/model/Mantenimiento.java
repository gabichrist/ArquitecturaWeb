package com.mantenimiento.model;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Mantenimiento {

	@Id
	private ObjectId id;
	
	private Long idMonopatin;
	
	private	Instant inicio;

	private Instant fin;

	private String descripcion;

	public Mantenimiento() {
		super();
	}

	public Mantenimiento(ObjectId id, Long idMonopatin, Instant inicio, Instant fin, String descripcion) {
		super();
		this.id = id;
		this.idMonopatin = idMonopatin;
		this.inicio = inicio;
		this.fin = fin;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Mantenimiento [id=" + id + ", idMonopatin=" + idMonopatin + ", inicio=" + inicio + ", fin=" + fin + "]";
	}	
}
