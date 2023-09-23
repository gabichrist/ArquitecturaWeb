package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dto.EstudiantesPorCarreraDTO;
import dto.ReporteCarreraDTO;
import entity.Carrera;
import entity.Estudiante;
import factory.ConnectionFactory;

public class CarreraRepositoryImpl implements CarreraRepository{
	EntityManager entityManager;

	public CarreraRepositoryImpl() {
		this.entityManager = ConnectionFactory.connection();
	}

	public CarreraRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Carrera insertarCarrera(Carrera carrera) {	
		entityManager.getTransaction().begin();	
		if (this.obtenerCarreraPorNombre(carrera.getNombre()) == null) {
			entityManager.persist(carrera);
		} else {
			carrera = entityManager.merge(carrera);
		}
		entityManager.getTransaction().commit();
		return this.obtenerCarreraPorNombre(carrera.getNombre());		
	}	
	
	@Override
	public Carrera obtenerCarreraPorNombre(String nombre) {	
		TypedQuery<Carrera> q = entityManager.createNamedQuery(Carrera.OBTENER_POR_NOMBRE, Carrera.class).setParameter("nombre", nombre);
		if (q.getResultList().isEmpty()) {
			return null;
		}
		Carrera carrera = q.getSingleResult();
		System.out.println("carrera " + carrera);
		return carrera;
	}
	
	// f- Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
	@Override
	public List<Carrera> obtenerCarrerasOrdenadasporInscriptos() {
		TypedQuery<Carrera> q = entityManager.createNamedQuery(Carrera.OBTENER_POR_INSCRIPTOS, Carrera.class);
		List<Carrera> carreras = q.getResultList();
		return carreras;
	}
	
	// esta no estoy segura
	@Override
	public List<EstudiantesPorCarreraDTO> obtenerCarrerasOrdenadasporInscriptosDTO() {
		entityManager.getTransaction().begin();		
		TypedQuery<EstudiantesPorCarreraDTO> q = entityManager.createNamedQuery(Carrera.OBTENER_POR_INSCRIPTOS_DTO, EstudiantesPorCarreraDTO.class);
		List<EstudiantesPorCarreraDTO> carreras = q.getResultList();		
		entityManager.getTransaction().commit();
		return carreras;
	}

	@Override
	public ReporteCarreraDTO generarReporteCarrera(Carrera carrera) {
		// TODO Auto-generated method stub
		return null;
	}


}
