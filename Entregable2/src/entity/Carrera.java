package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrera {

	@Id
	private int idCarrera;
	
	@Column(nullable = false)
	private String nombre;
	
	@OneToMany(mappedBy = "carrera")
	private List<Inscripcion> estudiantes;
	
	public Carrera() {
		super();
	}

	public Carrera(int idCarrera, String nombre) {
		super();
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		this.estudiantes = new ArrayList<Inscripcion>();
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

	public int getIdCarrera() {
		return idCarrera;
	}

	@Override
	public String toString() {
		return "Carrera [idCarrera=" + idCarrera + ", nombre=" + nombre + "]";
	}
		
}