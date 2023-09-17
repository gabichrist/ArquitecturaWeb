package repository;

import java.util.List;

import dto.CarreraDTO;
import dto.EstudianteDTO;

public interface EstudianteRepository {

	public abstract EstudianteDTO insertarEstudiante(EstudianteDTO estudiante);

	public abstract List<EstudianteDTO> obtenerEstudiantesOrdenadosporEdad(String edad);

	public abstract List<EstudianteDTO> obtenerEstudianteporLibreta(int LU);

	public abstract List<EstudianteDTO> obtenerEstudiantesporGenero(String genero);

	public abstract List<EstudianteDTO> obtenerEstudiantesporCarrerayCiudad(CarreraDTO carrera, String ciudad);
}
