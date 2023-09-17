package dto;

import java.io.Serializable;
import java.util.List;
import entity.Inscripcion;

@SuppressWarnings("serial")
public class CarreraDTO implements Serializable{

	private String nombre;

	private List<Inscripcion> estudiantes;

	public CarreraDTO(String nombre, List<Inscripcion> estudiantes) {
		super();
		this.nombre = nombre;
		this.estudiantes = estudiantes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Inscripcion> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Inscripcion> estudiantes) {
		this.estudiantes = estudiantes;
	}

}
