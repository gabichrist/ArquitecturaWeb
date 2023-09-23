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

	@Override
	public Inscripcion matricular(Estudiante estudiante, Carrera carrera) {
		Inscripcion inscripcion = new Inscripcion(estudiante, carrera);
		if (this.getById(inscripcion.getId()) == null) {
			entityManager.getTransaction().begin();	
			entityManager.persist(inscripcion);
			entityManager.getTransaction().commit();	
		}
		return getById(inscripcion.getId());
	}

	public Inscripcion getById(InscripcionId id) {
		return this.entityManager.find(Inscripcion.class, id);
	}

}
