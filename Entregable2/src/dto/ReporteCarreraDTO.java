package dto;


/**
 * ReporteCarrerasDTO
 * Para cada carrera incluye información de los inscriptos y egresados por año
 */

public class ReporteCarreraDTO {

	private String carrera;
	
	private Long cantidadInscriptos;
	
	private Long cantidadEgresados;
	
	private int a�o;

	public ReporteCarreraDTO() {
		super();
	}

	public ReporteCarreraDTO(String carrera, Long cantidadInscriptos, Long cantidadEgresados, int a�o) {
		super();
		this.carrera = carrera;
		this.cantidadInscriptos = cantidadInscriptos;
		this.cantidadEgresados = cantidadEgresados;
		this.a�o = a�o;
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
		return a�o;
	}

	public void setFecha(int a�o) {
		this.a�o = a�o;
	}

	@Override
	public String toString() {
		return "Carrera: " + carrera + " - A�o: " + a�o + " - Cantidad de Inscriptos: " + cantidadInscriptos + " - Cantidad de egresados: " + cantidadEgresados;
	}		
}
