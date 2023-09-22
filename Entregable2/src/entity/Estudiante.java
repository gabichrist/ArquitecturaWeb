package entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(value = {
	//TODO: ALGO SE ROMPE CON ESTAS QUERIES, no sé aún si es por lo los refactors que hice yo
		
//		@NamedQuery(name = Estudiante.OBTENER_POR_EDAD, query = "SELECT e FROM Estudiante e ORDER BY e.edad"),
//		@NamedQuery(name = Estudiante.OBTENER_POR_LIBRETA, query = "SELECT e FROM Estudiante e WHERE e.lu = :libreta"),
//		@NamedQuery(name = Estudiante.OBTENER_POR_GENERO, query = "SELECT e FROM Estudiante e WHERE e.genero = :genero"),
//		@NamedQuery(name = Estudiante.OBTENER_POR_CARRERA_Y_CIUDAD, query = "SELECT e FROM Estudiante JOIN e.carreras i " + 
//		"WHERE i.idCarrera = :carrera AND e.ciudad = :ciudad")
	})

public class Estudiante {
	
	public static final String OBTENER_POR_EDAD = "Estudiante.obtenerPorEdad";
	public static final String OBTENER_POR_LIBRETA = "Estudiante.obtenerPorLibreta";
	public static final String OBTENER_POR_GENERO = "Estudiante.obtenerPorGenero";
	public static final String OBTENER_POR_CARRERA_Y_CIUDAD = "Estudiante.obtenerPorCarrerayCiudad";

	@Id
	private int LU;
	
	@Column(nullable = false)
	private String nombres;
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private int edad;
	
	@Column(nullable = false)
	private String genero;
	
	@Column(nullable = false)
	private String dni;
	
	@Column(nullable = false)
	private String ciudad;
	
	@OneToMany(mappedBy = "id.estudiante")
	private Set<Inscripcion> inscripcionSet;
	
	public Estudiante() {
		super();
	}

	public Estudiante(int lU, String nombres, String apellido, int edad, String genero, String dni, String ciudad) {
		super();
		LU = lU;
		this.nombres = nombres;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
		// this.carreraSet = new Set<Inscripcion>();
	}
	
	public int getLU() {
		return LU;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public Collection<Inscripcion> getCarreras() {
		//TODO: iterar inscripciones y devolver carreras
		return new HashSet<>();
	}

	@Override
	public String toString() {
		return "Estudiante [LU=" + LU + ", nombres=" + nombres + ", apellido=" + apellido + ", edad=" + edad
				+ ", genero=" + genero + ", dni=" + dni + ", ciudad=" + ciudad + "]";
	}
	
}