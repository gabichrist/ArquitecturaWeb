package service;

import java.sql.Timestamp;
import java.util.Date;

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
		Inscripcion inscripcion = inscripcionRespository.obtenerInscripcion(estudiante, carrera);
		if (inscripcion == null) {
			return inscripcionRespository.insertarInscripcion(new Inscripcion(estudiante, carrera));
		} else {
			return inscripcion;
		}
	}
	
	public Inscripcion graduarEstudiante(Estudiante estudiante, Carrera carrera) {
		Timestamp now = new Timestamp((new Date()).getTime());
		return this.graduarEstudiante(estudiante, carrera, now);
	}

	public Inscripcion graduarEstudiante(Estudiante estudiante, Carrera carrera, Timestamp fechaEgreso) {
		Inscripcion inscripcion = inscripcionRespository.obtenerInscripcion(estudiante, carrera);
		inscripcion.setEsGraduado(true);
		inscripcion.setFecha_egreso(fechaEgreso);
		inscripcionRespository.insertarInscripcion(inscripcion);
		return inscripcionRespository.obtenerInscripcion(inscripcion.getEstudiante(), inscripcion.getCarrera());
	}
}
