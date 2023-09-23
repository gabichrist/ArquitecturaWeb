package dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EstudiantesPorCarreraDTO implements Serializable{
	
	private String nombre;
	
	private Long cantidadEstudiantes;
	

	public EstudiantesPorCarreraDTO() {
		super();
	}

	public EstudiantesPorCarreraDTO(String nombre, Long cantidadEstudiantes) {
		super();	
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

	@Override
	public String toString() {
		return "Carrera: " + nombre + ", cantidadEstudiantes= " + cantidadEstudiantes;
	}
	
}
