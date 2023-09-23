package dto;


/**
 * ReporteCarrerasDTO
 * Para cada carrera incluye información de los inscriptos y egresados por año
 */

public class ReporteCarreraDTO {

	private String carrera;
	
	private Long cantidadInscriptos;
	
	private Long cantidadEgresados;
	
	private int anio;

	public ReporteCarreraDTO() {
		super();
	}

	public ReporteCarreraDTO(String carrera, Long cantidadInscriptos, Long cantidadEgresados, int anio) {
		super();
		this.carrera = carrera;
		this.cantidadInscriptos = cantidadInscriptos;
		this.cantidadEgresados = cantidadEgresados;
		this.anio = anio;
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
		return anio;
	}

	public void setFecha(int anio) {
		this.anio = anio;
	}

	@Override
	public String toString() {
		return "Carrera: " + carrera + " - Anio: " + anio + " - Cantidad de Inscriptos: " + cantidadInscriptos + " - Cantidad de egresados: " + cantidadEgresados;
	}		
}
