package entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Inscripcion {
	
	@Id
    @EmbeddedId
    private InscripcionId id;
    
    @ManyToOne
    @JoinColumn(name = "LU", referencedColumnName = "LU", insertable = false, updatable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idCarrera", referencedColumnName = "idCarrera", insertable = false, updatable = false)
    private Carrera carrera;
	
	@Column
	private Timestamp fecha_ingreso;
	
	@Column(nullable = true)
	private Timestamp fecha_egreso;
	
	@Column(nullable = false)
	private boolean esGraduado;

	public Inscripcion() {
	    // Constructor requerido por Hibernate, para pueder generar la entidad desde una búsqueda.
	}
	
	public Inscripcion(Estudiante estudiante, Carrera carrera, Timestamp fecha_ingreso, Timestamp fecha_egreso, Boolean esGraduado) {
		super();
		this.id = new InscripcionId(estudiante, carrera);
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_egreso = fecha_egreso;
		this.esGraduado = esGraduado;
	}

	public Inscripcion(Estudiante estudiante, Carrera carrera) {
		super();
		this.id = new InscripcionId(estudiante, carrera);
		Date now = new Date();
		this.fecha_ingreso = new Timestamp(now.getTime());
		this.fecha_egreso = null;
		this.esGraduado = false;
	}

	public InscripcionId getId() {
		return this.id;
	}
	
	public Estudiante getEstudiante() {
		return this.id.getEstudiante();
	}

	public Carrera getCarrera() {
		return this.id.getCarrera();
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
		return "Inscripcion [estudiante=" + id.getEstudiante() + ", carrera=" + id.getCarrera() + ", fecha_ingreso=" + fecha_ingreso
				+ ", fecha_egreso=" + fecha_egreso + "]";
	}
}