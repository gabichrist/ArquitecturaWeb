package service;

import java.util.List;
import entity.Carrera;
import entity.Estudiante;
import entity.Inscripcion;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;
import repository.InscripcionRepository;
import repository.InscripcionRepositoryImpl;

public class ServicioEstudiante {

	private EstudianteRepository estudianteRepository;

	public ServicioEstudiante() {
		this.estudianteRepository = new EstudianteRepositoryImpl();
	}

	public Estudiante altaEstudiante(Estudiante estudiante) {
		Estudiante est = this.estudianteRepository.insertarEstudiante(estudiante);	
		return(est);
	}

	public List<Estudiante> listarEstudiantesOrdenadosporEdad() {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesOrdenadosPorEdad();
		System.out.println("\nLista de estudiantes ordenados por edad:");
		for (Estudiante estudiante : estudiantes) {
			System.out.println(estudiante.toString());
		}
		return estudiantes;
	}

	public List<Estudiante> listarEstudiantesOrdenados(String criterioOrdenamiento) {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesOrdenados(criterioOrdenamiento);
		return estudiantes;
	}
	
	public Estudiante obtenerEstudianteporLibreta(int LU) {
		Estudiante estudiante = this.estudianteRepository.obtenerEstudiantePorLibreta(LU);	
		if(estudiante != null) {
			System.out.println("\nEstudiante con LU " + LU + "\n" + estudiante.toString());
		} else {
			System.out.println("\\nNo existe un estudiante con LU " + LU);
		}
		return estudiante;
	}

	public List<Estudiante> listarEstudiantesporGenero(String genero) {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesPorGenero(genero);
		return estudiantes;	
	}

	public List<Estudiante> listarEstudiantesporCarrerayCiudad(Carrera carrera, String ciudad) {
		List<Estudiante> estudiantes = this.estudianteRepository.obtenerEstudiantesPorCarrerayCiudad(carrera, ciudad);
		return estudiantes;
	}

}
