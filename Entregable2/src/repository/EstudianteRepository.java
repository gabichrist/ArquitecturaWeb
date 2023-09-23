package repository;

import java.util.List;

import entity.Carrera;
import entity.Estudiante;

public interface EstudianteRepository {

	public abstract Estudiante insertarEstudiante(Estudiante estudiante);
	
	public abstract List<Estudiante> obtenerEstudiantesOrdenadosPorEdad();
	
	public abstract List<Estudiante> obtenerEstudiantesOrdenados(String criterioOrdenamiento);

	public abstract Estudiante obtenerEstudiantePorLibreta(int LU);

	public abstract List<Estudiante> obtenerEstudiantesPorGenero(String genero);

	public abstract List<Estudiante> obtenerEstudiantesPorCarrerayCiudad(Carrera carrera, String ciudad);

}
