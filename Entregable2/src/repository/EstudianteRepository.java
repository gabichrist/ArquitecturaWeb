package repository;

import java.util.List;

import entity.Carrera;
import entity.Estudiante;

public interface EstudianteRepository {

	public abstract Estudiante insertarEstudiante(Estudiante estudiante);
	
	public abstract List<Estudiante> obtenerEstudiantesOrdenadosporEdad();
	
	public abstract List<Estudiante> obtenerEstudiantesOrdenados(String criterioOrdenamiento);

	public abstract Estudiante obtenerEstudianteporLibreta(int LU);

	public abstract List<Estudiante> obtenerEstudiantesporGenero(String genero);

	public abstract List<Estudiante> obtenerEstudiantesporCarrerayCiudad(Carrera carrera, String ciudad);

}
