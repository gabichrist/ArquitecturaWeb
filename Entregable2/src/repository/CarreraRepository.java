package repository;

import java.util.List;

import dto.EstudiantesPorCarreraDTO;
import dto.ReporteCarreraDTO;
import entity.Carrera;

public interface CarreraRepository {
	
	public abstract Carrera insertarCarrera(Carrera carrera);
	
	public abstract Carrera obtenerCarreraPorNombre(String nombre);

	public abstract List<Carrera> obtenerCarrerasOrdenadasporInscriptos();

	public abstract List<EstudiantesPorCarreraDTO> obtenerCarrerasOrdenadasporInscriptosDTO();
	
	public abstract ReporteCarreraDTO generarReporteCarrera(Carrera carrera);
}
