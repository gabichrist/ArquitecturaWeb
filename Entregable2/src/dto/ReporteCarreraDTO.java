package dto;


/**
 * ReporteCarrerasDTO
 * Para cada carrera incluye informaciÃ³n de los inscriptos y egresados por aÃ±o
 */

public class ReporteCarreraDTO {

	private String carrera;
	
	private Long cantidadInscriptos;
	
	private Long cantidadEgresados;
	
	private int año;

	public ReporteCarreraDTO() {
		super();
	}

	public ReporteCarreraDTO(String carrera, Long cantidadInscriptos, Long cantidadEgresados, int año) {
		super();
		this.carrera = carrera;
		this.cantidadInscriptos = cantidadInscriptos;
		this.cantidadEgresados = cantidadEgresados;
		this.año = año;
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

	public int getFecha() {
		return año;
	}

	public void setFecha(int año) {
		this.año = año;
	}

	@Override
	public String toString() {
		return "Carrera: " + carrera + " - Año: " + año + " - Cantidad de Inscriptos: " + cantidadInscriptos + " - Cantidad de egresados: " + cantidadEgresados;
	}		
}
