package com.example.entregable3.dtos;

import com.example.entregable3.model.Carrera;

public class CarreraConInscriptosDTO {
	private int idCarrera;

	private String nombre;

	private int cantidadInscriptos;
	
	public CarreraConInscriptosDTO() {
	}
	
	public CarreraConInscriptosDTO(Carrera c) {
		this.idCarrera = c.getIdCarrera();
		this.nombre = c.getNombre();
		this.cantidadInscriptos = c.cantidadInscriptos();
	}

	public CarreraConInscriptosDTO(String nombre, int cantidadInscriptos) {
		this.nombre = nombre;
		this.cantidadInscriptos = cantidadInscriptos;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadInscriptos() {
		return cantidadInscriptos;
	}

	public void setCantidadInscriptos(int cantidadInscriptos) {
		this.cantidadInscriptos = cantidadInscriptos;
	}
	
}
