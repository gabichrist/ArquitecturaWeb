package repository;

import javax.persistence.EntityManager;

import entity.Carrera;
import entity.Estudiante;
import entity.Inscripcion;
import entity.InscripcionId;
import factory.ConnectionFactory;

public class InscripcionRepositoryImpl implements InscripcionRepository {
	EntityManager entityManager;

	public InscripcionRepositoryImpl() {
		this.entityManager = ConnectionFactory.connection();
	}

	public InscripcionRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Inscripcion getById(InscripcionId id) {
		return this.entityManager.find(Inscripcion.class, id);
	}

	@Override
	public Inscripcion insertarInscripcion(Inscripcion inscripcion) {
		entityManager.getTransaction().begin();	
		if (this.getById(inscripcion.getId()) == null) {
			entityManager.persist(inscripcion);
		} else {
			entityManager.merge(inscripcion);
		}
		entityManager.getTransaction().commit();	
		return getById(inscripcion.getId());
	}

	@Override
	public Inscripcion obtenerInscripcion(Estudiante estudiante, Carrera carrera) {
		return getById(new InscripcionId(estudiante, carrera));
	}

}
