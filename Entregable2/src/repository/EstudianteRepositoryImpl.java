package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dto.CarreraDTO;
import dto.EstudianteDTO;


public class EstudianteRepositoryImpl implements EstudianteRepository{
	EntityManager entityManager;
	public EstudianteRepositoryImpl() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entregable2");
		this.entityManager= emf.createEntityManager();
	}

	@Override
	public EstudianteDTO insertarEstudiante(EstudianteDTO estudiante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstudianteDTO> obtenerEstudiantesOrdenadosporEdad(String edad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstudianteDTO> obtenerEstudianteporLibreta(int LU) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstudianteDTO> obtenerEstudiantesporGenero(String genero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstudianteDTO> obtenerEstudiantesporCarrerayCiudad(CarreraDTO carrera, String ciudad) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
