package entity;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class InscripcionId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name = "LU")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "idCarrera")
    private Carrera carrera;

    public InscripcionId() {
		super();
    }
    
	public InscripcionId(Estudiante estudiante, Carrera carrera) {
	    this.estudiante = estudiante;
	    this.carrera = carrera;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    InscripcionId that = (InscripcionId) obj;

	    return (
	    		
	    		this.estudiante.equals(that.getEstudiante()) & 
	    		this.carrera.equals(that.getCarrera())
    		);
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(estudiante.getLU(), carrera.getIdCarrera());
	}
}