package entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Inscripcion implements Serializable{

	@Id
	@GeneratedValue(strategy=
	GenerationType.AUTO)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Estudiante estudiante;
	
	@Id
	@GeneratedValue(strategy=
	GenerationType.AUTO)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Carrera carrera;
	
	@Column
	private Timestamp fecha_ingreso;
	
	@Column(nullable = true)
	private Timestamp fecha_egreso;
	
	@Column(nullable = false)
	private boolean esGraduado;

	public Inscripcion() {
		super();
	}

	public Inscripcion(Estudiante estudiante, Carrera carrera, Timestamp fecha_ingreso, Timestamp fecha_egreso, Boolean esGraduado) {
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
	

	public boolean EsGraduado() {
		return esGraduado;
	}

	public void setEsGraduado(boolean esGraduado) {
		this.esGraduado = esGraduado;
	}

	@Override
	public String toString() {
		return "Inscripcion [estudiante=" + estudiante + ", carrera=" + carrera + ", fecha_ingreso=" + fecha_ingreso
				+ ", fecha_egreso=" + fecha_egreso + "]";
	}
	
}