package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import entity.Carrera;
import entity.Estudiante;

public class EstudianteRepositoryImpl implements EstudianteRepository {
	EntityManager entityManager;

	public EstudianteRepositoryImpl() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entregable2");
		this.entityManager = emf.createEntityManager();
	}

	public EstudianteRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Estudiante insertarEstudiante(Estudiante estudiante) {
		entityManager.getTransaction().begin();	
		if (estudiante.getDni() == null) {
			entityManager.persist(estudiante);
		} else {
			estudiante = entityManager.merge(estudiante);
		}
		entityManager.getTransaction().commit();	
		return estudiante;
	}

	@Override
	public List<Estudiante> obtenerEstudiantesOrdenadosporEdad() {
		TypedQuery<Estudiante> q = entityManager.createNamedQuery(Estudiante.OBTENER_POR_EDAD, Estudiante.class);
		List<Estudiante> estudiantes = q.getResultList();
		return estudiantes;
	}
	
	@Override
	public List<Estudiante> obtenerEstudiantesOrdenados(String criterioOrdenamiento) {
		//TypedQuery<Estudiante> q = entityManager.createNamedQuery(Estudiante.OBTENER_ORDENADOS, Estudiante.class);
		//List<Estudiante> estudiantes = q.getResultList();
		//return estudiantes;
		return null;
	}

	@Override
	public Estudiante obtenerEstudianteporLibreta(int LU) {
		TypedQuery<Estudiante> q = entityManager.createNamedQuery(Estudiante.OBTENER_POR_LIBRETA, Estudiante.class)
				.setParameter("libreta", LU);
		Estudiante estudiante = q.getSingleResult();
		return estudiante;
	}

	@Override
	public List<Estudiante> obtenerEstudiantesporGenero(String genero) {
		TypedQuery<Estudiante> q = entityManager.createNamedQuery(Estudiante.OBTENER_POR_GENERO, Estudiante.class)
				.setParameter("genero", genero);
		List<Estudiante> estudiantes = q.getResultList();
		return estudiantes;
	}

	@Override
	public List<Estudiante> obtenerEstudiantesporCarrerayCiudad(Carrera carrera, String ciudad) {
		TypedQuery<Estudiante> q = entityManager
				.createNamedQuery(Estudiante.OBTENER_POR_CARRERA_Y_CIUDAD, Estudiante.class)
				.setParameter("carrera", carrera.getIdCarrera()).setParameter("ciudad", ciudad);
		List<Estudiante> estudiantes = q.getResultList();
		return estudiantes;
	}


}
