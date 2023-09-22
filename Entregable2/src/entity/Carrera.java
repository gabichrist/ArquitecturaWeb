package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(value = {
		// MAS TARDE REVISO SI ESTAN BIEN ESCRITOS TODOS LOS JPQL Y QUE FUNCIONEN
		@NamedQuery(name = Carrera.OBTENER_POR_INSCRIPTOS, query = "SELECT DISTINCT i.carrera "
				+ "FROM Inscripcion i,  Estudiante e, Carrera c "
				+ "WHERE c.idCarrera = i.carrera.idCarrera AND e.LU = i.estudiante.LU "
				+ "GROUP BY i.carrera.idCarrera "
				+ "ORDER BY COUNT(i.estudiante) DESC"),
		@NamedQuery(name = Carrera.OBTENER_POR_INSCRIPTOS_DTO, query = "" ),
			//	+ "SELECT NEW dto.EstudiantesPorCarreraDTO(c.nombre, COUNT(DISTINC i.estudiante) AS cantidadEstudiantes) "
			//	+ "FROM Inscripcion i "
			//	+ "JOIN i.estudiante e "
			//	+ "WHERE i.carrera.idCarrera = c.idCarrera AND i.estudiante.LU = e.LU )) "
			//	+ "FROM Carrera c ")
		
		//SIN HACER
		@NamedQuery(name = Carrera.GENERAR_REPORTE_CARRERA, query = "")
			//	+ "SELECT NEW dto.ReporteCarreraDTO()

})
		
public class Carrera {
	
	public static final String OBTENER_POR_INSCRIPTOS = "Carrera.obtenerPorInscriptos";
	public static final String OBTENER_POR_INSCRIPTOS_DTO = "Carrera.obtenerPorInscriptosDTO";
	public static final String GENERAR_REPORTE_CARRERA = "Carrera.generarReporteCarrera";

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