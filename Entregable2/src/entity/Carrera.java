package entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
	
	@OneToMany(mappedBy = "id.carrera")
	private Set<Inscripcion> inscripcionSet;
	
	public Carrera() {
		super();
	}

	public Carrera(int idCarrera, String nombre) {
		super();
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		this.inscripcionSet = new HashSet<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Estudiante> getEstudiantes() {
		// TODO: Iterar inscripciones y devolver colección de estudiantes.
		return new HashSet<>();
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	@Override
	public String toString() {
		return "Carrera [idCarrera=" + idCarrera + ", nombre=" + nombre + "]";
	}
		
}