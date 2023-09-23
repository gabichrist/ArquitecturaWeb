package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entity.Carrera;
import entity.Estudiante;
import factory.ConnectionFactory;

public class EstudianteRepositoryImpl implements EstudianteRepository {
	EntityManager entityManager;

	public EstudianteRepositoryImpl() {
		this.entityManager = ConnectionFactory.connection();
	}

	public EstudianteRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Estudiante insertarEstudiante(Estudiante estudiante) {
		entityManager.getTransaction().begin();	
		if (this.obtenerEstudiantePorLibreta(estudiante.getLU()) == null) {
			entityManager.persist(estudiante);
		} else {
			estudiante = entityManager.merge(estudiante);
		}
		entityManager.getTransaction().commit();	
		return this.obtenerEstudiantePorLibreta(estudiante.getLU());
	}

	@Override
	public List<Estudiante> obtenerEstudiantesOrdenadosPorEdad() {
		TypedQuery<Estudiante> q = entityManager.createNamedQuery(Estudiante.OBTENER_POR_EDAD, Estudiante.class);
		List<Estudiante> estudiantes = q.getResultList();
		return estudiantes;
	}

	@Override
	public Estudiante obtenerEstudiantePorLibreta(int LU) {
		TypedQuery<Estudiante> q = entityManager.createNamedQuery(Estudiante.OBTENER_POR_LIBRETA, Estudiante.class)
				.setParameter("libreta", LU);		
		if (q.getResultList().isEmpty()) {
			return null;
		}	
		Estudiante estudiante = q.getSingleResult();
		return estudiante;
	}

	@Override
	public List<Estudiante> obtenerEstudiantesPorGenero(String genero) {
		TypedQuery<Estudiante> q = entityManager.createNamedQuery(Estudiante.OBTENER_POR_GENERO, Estudiante.class)
				.setParameter("genero", genero);
		List<Estudiante> estudiantes = q.getResultList();
		return estudiantes;
	}

	@Override
	public List<Estudiante> obtenerEstudiantesPorCarrerayCiudad(Carrera carrera, String ciudad) {
		TypedQuery<Estudiante> q = entityManager
				.createNamedQuery(Estudiante.OBTENER_POR_CARRERA_Y_CIUDAD, Estudiante.class)
				.setParameter("carrera", carrera.getIdCarrera()).setParameter("ciudad", ciudad);
		List<Estudiante> estudiantes = q.getResultList();
		return estudiantes;
	}


}
