package dto;

import java.io.Serializable;
import java.util.ArrayList;
import entity.Inscripcion;

@SuppressWarnings("serial")
public class EstudianteDTO implements Serializable {

	private int LU;

	private String nombres;
	
	private String apellido;

	private int edad;

	private String genero;

	private String dni;

	private String ciudad;

	private ArrayList<Inscripcion> carreras;
	

	public EstudianteDTO() {
		super();
	}

	public EstudianteDTO(int lU, String nombres, String apellido, int edad, String genero, String dni, String ciudad,
			ArrayList<Inscripcion> carreras) {
		super();
		LU = lU;
		this.nombres = nombres;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
		this.carreras = carreras;
	}

	public int getLU() {
		return LU;
	}

	public void setLU(int lU) {
		LU = lU;
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

	public ArrayList<Inscripcion> getCarreras() {
		return carreras;
	}

	public void setCarreras(ArrayList<Inscripcion> carreras) {
		this.carreras = carreras;
	}

}
