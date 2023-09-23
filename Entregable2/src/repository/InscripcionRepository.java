package repository;

import entity.Carrera;
import entity.Estudiante;
import entity.Inscripcion;

public interface InscripcionRepository {
	public abstract Inscripcion matricular(Estudiante estudiante, Carrera carrera);
}
