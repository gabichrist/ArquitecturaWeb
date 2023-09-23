package repository;

import entity.Carrera;
import entity.Estudiante;
import entity.Inscripcion;

public interface InscripcionRepository {
	public abstract Inscripcion insertarInscripcion(Inscripcion inscripcion);
	public abstract Inscripcion obtenerInscripcion(Estudiante estudiante, Carrera carrera);
}
