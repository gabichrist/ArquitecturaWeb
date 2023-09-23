package entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = Carrera.OBTENER_POR_NOMBRE, query = "SELECT c FROM Carrera c WHERE c.nombre = :nombre"),
		@NamedQuery(name = Carrera.OBTENER_POR_INSCRIPTOS_DTO, query = "SELECT NEW dto.EstudiantesPorCarreraDTO(c.nombre, (SELECT COUNT(i.estudiante) "
				+ "FROM Inscripcion i " + "JOIN i.estudiante e "
				+ "WHERE i.carrera.idCarrera = c.idCarrera AND i.estudiante.LU = e.LU)) " + "FROM Carrera c"),
		@NamedQuery(name = Carrera.GENERAR_REPORTE_CARRERA, query = "SELECT NEW dto.ReporteCarreraDTO(c.nombre, count(*), "
				+ "SUM(CASE WHEN(i.esGraduado = true and EXTRACT(year from i.fecha_egreso) = EXTRACT(year from i.fecha_ingreso)) THEN 1 ELSE 0 END), EXTRACT(YEAR from i.fecha_ingreso)) "
				+ "FROM Carrera c " + "JOIN Inscripcion i on (i.carrera = c.idCarrera) "
				+ "GROUP BY c.nombre, EXTRACT(year from i.fecha_ingreso) "
				+ "ORDER BY c.nombre ASC, EXTRACT(year from i.fecha_ingreso) ASC") })

public class Carrera {

	public static final String OBTENER_POR_NOMBRE = "Carrera.obtenerPorNombre";
	public static final String OBTENER_POR_INSCRIPTOS = "Carrera.obtenerPorInscriptos";
	public static final String OBTENER_POR_INSCRIPTOS_DTO = "Carrera.obtenerPorInscriptosDTO";
	public static final String GENERAR_REPORTE_CARRERA = "Carrera.generarReporteCarrera";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
		return "Nombre carrera: " + nombre;
	}

}