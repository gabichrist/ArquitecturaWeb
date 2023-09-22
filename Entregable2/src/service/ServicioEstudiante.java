package service;

import java.util.List;
import entity.Carrera;
import entity.Estudiante;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;

public class ServicioEstudiante {

	private EstudianteRepository estudianteRepository;

	public ServicioEstudiante() {
		this.estudianteRepository = new EstudianteRepositoryImpl();
	}

	public Estudiante altaEstudiante(Estudiante estudiante) {
		Estudiante e = new Estudiante();
		e.setNombres(estudiante.getNombres());
		e.setApellido(estudiante.getApellido());
		e.setEdad(estudiante.getEdad());
		e.setDni(estudiante.getDni());
		e.setGenero(estudiante.getGenero());
		e.setCiudad(estudiante.getCiudad());
		Estudiante est = this.estudianteRepository.insertarEstudiante(e);	
		return(est);
	}

	public Estudiante matricularEstudiante(Estudiante estudiante, Carrera carrera) { 
		return null;
	}

	public List<Estudiante> listarEstudiantesOrdenadosporEdad() {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesOrdenadosporEdad();
		return estudiantes;
	}

	public List<Estudiante> listarEstudiantesOrdenados(String criterioOrdenamiento) {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesOrdenados(criterioOrdenamiento);
		return estudiantes;
	}
	
	public Estudiante obtenerEstudianteporLibreta(int LU) {
		Estudiante estudiante = this.estudianteRepository.obtenerEstudianteporLibreta(LU);		
		return estudiante;
	}

	public List<Estudiante> listarEstudiantesporGenero(String genero) {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesporGenero(genero);
		return estudiantes;	
	}

	public List<Estudiante> listarEstudiantesporCarrerayCiudad(Carrera carrera, String ciudad) {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesporCarrerayCiudad(carrera, ciudad);
		return estudiantes;
	}

}
