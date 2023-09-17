package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Estudiante {
	
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
	
	@OneToMany(mappedBy = "estudiante")
	private List<Inscripcion> carreras;
	
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
		this.carreras = new ArrayList<Inscripcion>();
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


	public List<Inscripcion> getCarreras() {
		return carreras;
	}

	@Override
	public String toString() {
		return "Estudiante [LU=" + LU + ", nombres=" + nombres + ", apellido=" + apellido + ", edad=" + edad
				+ ", genero=" + genero + ", dni=" + dni + ", ciudad=" + ciudad + "]";
	}
	
}