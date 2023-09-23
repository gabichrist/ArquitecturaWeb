package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
<<<<<<< HEAD
		entityManager.getTransaction().commit();	
		return this.obtenerEstudiantePorLibreta(estudiante.getLU());
=======
		entityManager.getTransaction().commit();
		
		return obtenerEstudiantePorLibreta(estudiante.getLU());
>>>>>>> c52b1e1b4eb167fe3a38515d0fc02b439e5ec606
	}

	@Override
	public List<Estudiante> obtenerEstudiantesOrdenadosPorEdad() {
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
	public Estudiante obtenerEstudiantePorLibreta(int LU) {
		return entityManager.find(Estudiante.class, LU);

//		TypedQuery<Estudiante> q = entityManager.createNamedQuery(Estudiante.OBTENER_POR_LIBRETA, Estudiante.class)
//				.setParameter("libreta", LU);
//		Estudiante estudiante = q.getSingleResult();
//		return estudiante;
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
