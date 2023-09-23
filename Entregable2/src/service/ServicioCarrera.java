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
	
	
	public List<EstudiantesPorCarreraDTO> listarCarrerasOrdenadasporInscriptosDTO() {
		List<EstudiantesPorCarreraDTO> carreras = carreraRepository.obtenerCarrerasOrdenadasporInscriptosDTO();
		System.out.println("\nLista de carreras ordenados por cantidad de inscriptos:");
		for (EstudiantesPorCarreraDTO carrera : carreras) {
			System.out.println(carrera.toString());
		}
		return carreras;
	}
	
	public List<ReporteCarreraDTO> generarReporteCarrera(){
		List<ReporteCarreraDTO> reporte = carreraRepository.generarReporteCarrera();
		System.out.println("\n Reporte de carreras: ");
		for (ReporteCarreraDTO c : reporte) {
			System.out.println(c.toString());
		}
		return reporte;
	} 
	
}
