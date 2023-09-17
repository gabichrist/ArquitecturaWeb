package dto;

import java.io.Serializable;
import java.sql.Timestamp;
import entity.Carrera;
import entity.Estudiante;

@SuppressWarnings("serial")
public class InscripcionDTO implements Serializable{
	
	private Estudiante estudiante;
	private Carrera carrera;
	private Timestamp fecha_ingreso;
	private Timestamp fecha_egreso;
	private boolean esGraduado;
	
	public InscripcionDTO(Estudiante estudiante, Carrera carrera, Timestamp fecha_ingreso, Timestamp fecha_egreso,
			boolean esGraduado) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_egreso = fecha_egreso;
		this.esGraduado = esGraduado;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Timestamp getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Timestamp fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public Timestamp getFecha_egreso() {
		return fecha_egreso;
	}

	public void setFecha_egreso(Timestamp fecha_egreso) {
		this.fecha_egreso = fecha_egreso;
	}

	public boolean isEsGraduado() {
		return esGraduado;
	}

	public void setEsGraduado(boolean esGraduado) {
		this.esGraduado = esGraduado;
	}
	
	
}
