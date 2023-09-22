package service;

import java.util.List;

import dto.EstudiantesPorCarreraDTO;
import dto.ReporteCarreraDTO;
import entity.Carrera;
import repository.CarreraRepository;
import repository.CarreraRepositoryImpl;

public class ServicioCarrera {
	
	private CarreraRepository carreraRepository;

	public ServicioCarrera() {
		this.carreraRepository = new CarreraRepositoryImpl();
	}

	public Carrera altaCarrera(String nombre) {
		Carrera c = new Carrera();
		c.setNombre(nombre);
		Carrera carrera = this.carreraRepository.insertarCarrera(c);	
		return carrera;
	}
	
	public List<Carrera> listarCarrerasOrdenadasporInscriptos() {
		List<Carrera> carreras = carreraRepository.obtenerCarrerasOrdenadasporInscriptos();
		return carreras;		
	}
	
	public List<EstudiantesPorCarreraDTO> listarCarrerasOrdenadasporInscriptosDTO() {
		List<EstudiantesPorCarreraDTO> carreras = carreraRepository.obtenerCarrerasOrdenadasporInscriptosDTO();
		return carreras;
	}
	
	public ReporteCarreraDTO generarReporteCarrera(Carrera carrera){
		ReporteCarreraDTO reporte = carreraRepository.generarReporteCarrera(carrera);
		return reporte;
	} 
	
}
