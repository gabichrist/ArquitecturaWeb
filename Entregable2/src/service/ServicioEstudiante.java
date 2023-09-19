package service;

import java.util.ArrayList;
import java.util.List;
import dto.CarreraDTO;
import dto.EstudianteDTO;
import entity.Estudiante;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;

public class ServicioEstudiante {

	private EstudianteRepository estudianteRepository;

	public ServicioEstudiante() {
		this.estudianteRepository = new EstudianteRepositoryImpl();
	}

	public EstudianteDTO altaEstudiante(EstudianteDTO estudiante) {
		Estudiante e = new Estudiante();
		e.setNombres(estudiante.getNombres());
		e.setApellido(estudiante.getApellido());
		e.setEdad(estudiante.getEdad());
		e.setDni(estudiante.getDni());
		e.setGenero(estudiante.getGenero());
		e.setCiudad(estudiante.getCiudad());
		Estudiante est = this.estudianteRepository.insertarEstudiante(e);
		EstudianteDTO edto = new EstudianteDTO();
		edto.setNombres(est.getNombres());
		edto.setApellido(est.getApellido());
		edto.setEdad(est.getEdad());
		edto.setDni(est.getDni());
		edto.setGenero(est.getGenero());
		edto.setCiudad(est.getCiudad());
		edto.setLU(est.getLU());
		edto.setCarreras(est.getCarreras());
		return edto;

	}

	public EstudianteDTO matricularEstudiante(EstudianteDTO estudiante, CarreraDTO carrera) { 
		return null;
	}

	public List<EstudianteDTO> listarEstudiantesOrdenadosporEdad() {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesOrdenadosporEdad();
		List<EstudianteDTO> estudiantesDTO = new ArrayList<EstudianteDTO>();
		estudiantes.forEach(e -> {
			EstudianteDTO edto = new EstudianteDTO();
			edto.setNombres(e.getNombres());
			edto.setApellido(e.getApellido());
			edto.setEdad(e.getEdad());
			edto.setDni(e.getDni());
			edto.setGenero(e.getGenero());
			edto.setCiudad(e.getCiudad());
			edto.setLU(e.getLU());
			edto.setCarreras(e.getCarreras());
			estudiantesDTO.add(edto);

		});
		return estudiantesDTO;
	}

	public EstudianteDTO obtenerEstudianteporLibreta(int LU) {
		Estudiante estudiante = this.estudianteRepository.obtenerEstudianteporLibreta(LU);
		EstudianteDTO edto = new EstudianteDTO();
		edto.setNombres(estudiante.getNombres());
		edto.setApellido(estudiante.getApellido());
		edto.setEdad(estudiante.getEdad());
		edto.setDni(estudiante.getDni());
		edto.setGenero(estudiante.getGenero());
		edto.setCiudad(estudiante.getCiudad());
		edto.setLU(estudiante.getLU());
		edto.setCarreras(estudiante.getCarreras());
		return edto;
	}

	public List<EstudianteDTO> listarEstudiantesporGenero(String genero) {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesporGenero(genero);
		List<EstudianteDTO> estudiantesDTO = new ArrayList<EstudianteDTO>();
		estudiantes.forEach(e -> {
			EstudianteDTO edto = new EstudianteDTO();
			edto.setNombres(e.getNombres());
			edto.setApellido(e.getApellido());
			edto.setEdad(e.getEdad());
			edto.setDni(e.getDni());
			edto.setGenero(e.getGenero());
			edto.setCiudad(e.getCiudad());
			edto.setLU(e.getLU());
			edto.setCarreras(e.getCarreras());
			estudiantesDTO.add(edto);
		});
		return estudiantesDTO;	
	}

	public List<EstudianteDTO> listarEstudiantesporCarrerayCiudad(CarreraDTO carrera, String ciudad) {
		return null;
	}
}
