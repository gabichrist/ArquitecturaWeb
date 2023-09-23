package service;

import entity.Carrera;
import entity.Estudiante;
import entity.Inscripcion;
import repository.InscripcionRepository;
import repository.InscripcionRepositoryImpl;

public class ServicioInscripcion {

	private InscripcionRepository inscripcionRespository;
	
	public ServicioInscripcion() {
		this.inscripcionRespository = new InscripcionRepositoryImpl();		
	}

	public Inscripcion matricularEstudiante(Estudiante estudiante, Carrera carrera) { 
		return inscripcionRespository.matricular(estudiante, carrera);
	}
}
