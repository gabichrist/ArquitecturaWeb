package dto;

import java.time.LocalDate;

/**
 * ReporteCarrerasDTO
 * Para cada carrera incluye información de los inscriptos y egresados por año
 */

public class ReporteCarreraDTO {

	private String carrera;
	
	private Long cantidadInscriptos;
	
	private Long cantidadEgresados;
	
	private LocalDate fecha;

	public ReporteCarreraDTO() {
		super();
	}

	public ReporteCarreraDTO(String carrera, Long cantidadInscriptos, Long cantidadEgresados, LocalDate fecha) {
		super();
		this.carrera = carrera;
		this.cantidadInscriptos = cantidadInscriptos;
		this.cantidadEgresados = cantidadEgresados;
		this.fecha = fecha;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public Long getCantidadInscriptos() {
		return cantidadInscriptos;
	}

	public void setCantidadInscriptos(Long cantidadInscriptos) {
		this.cantidadInscriptos = cantidadInscriptos;
	}

	public Long getCantidadEgresados() {
		return cantidadEgresados;
	}

	public void setCantidadEgresados(Long cantidadEgresados) {
		this.cantidadEgresados = cantidadEgresados;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "ReporteCarrerasDTO [carrera=" + carrera + ", cantidadInscriptos=" + cantidadInscriptos
				+ ", cantidadEgresados=" + cantidadEgresados + ", fecha=" + fecha + "]";
	}		
}
