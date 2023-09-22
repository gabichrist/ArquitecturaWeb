package dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EstudiantesPorCarreraDTO implements Serializable{
	
	private String nombre;
	
	private Integer cantidadEstudiantes;
	

	public EstudiantesPorCarreraDTO() {
		super();
	}

	public EstudiantesPorCarreraDTO(String nombre, Integer cantidadEstudiantes) {
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

	public Integer getCantidadEstudiantes() {
		return cantidadEstudiantes;
	}

	public void setCantidadEstudiantes(Integer cantidadEstudiantes) {
		this.cantidadEstudiantes = cantidadEstudiantes;
	}

	@Override
	public String toString() {
		return "EstudiantesPorCarreraDTO [nombre=" + nombre + ", cantidadEstudiantes=" + cantidadEstudiantes + "]";
	}
	
}
