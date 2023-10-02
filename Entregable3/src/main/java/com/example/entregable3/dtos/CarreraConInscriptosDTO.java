package com.example.entregable3.dtos;

public class CarreraConInscriptosDTO {
	private String nombre;
	private Long cantidadEstudiantes;
	
	public CarreraConInscriptosDTO() {
	}
	
	public CarreraConInscriptosDTO(String nombre, Long cantidadEstudiantes) {
		this.nombre = nombre;
		this.cantidadEstudiantes = cantidadEstudiantes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCantidadEstudiantes() {
		return cantidadEstudiantes;
	}

	public void setCantidadEstudiantes(Long cantidadEstudiantes) {
		this.cantidadEstudiantes = cantidadEstudiantes;
	}	
	
}
