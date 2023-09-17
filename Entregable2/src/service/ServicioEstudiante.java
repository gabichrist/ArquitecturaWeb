package service;


import java.util.List;
import dto.CarreraDTO;
import dto.EstudianteDTO;

public class ServicioEstudiante {

	public EstudianteDTO altaEstudiante(EstudianteDTO estudiante) {
		return null;
	
	}

	public EstudianteDTO matricularEstudiante(EstudianteDTO estudiante, CarreraDTO carrera) { //VER SI NO VA EN CARRERA
		return null;
	}

	public List<EstudianteDTO> listarEstudiantesOrdenadosporEdad(int edad) {
		return null;
	}

	public List<EstudianteDTO> listarEstudianteporLibreta(int LU) {
		return null;
	}

	public List<EstudianteDTO> listarEstudiantesporGenero(String genero) {
		return null;
	}

	public List<EstudianteDTO> listarEstudiantesporCarrerayCiudad(CarreraDTO carrera, String ciudad) {
		return null;
	}
}
