package com.example.entregable3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entregable3.model.Estudiante;

@Repository("EstudianteRepository")
public interface EstudianteRepository extends RepoBase<Estudiante, Long>{
    static List<String> sorteableFields = List.of("lu", "nombres", "apellido", "edad", "genero", "dni", "ciudad");

	@Query("SELECT e FROM Estudiante e WHERE e.lu = :lu")
	public Estudiante getByLU(int lu);
	    
	@Query("SELECT e FROM Estudiante e WHERE e.genero = :genero")
	public List<Estudiante> getByGenre(String genero);
	
	@Query("SELECT e FROM Estudiante e, Inscripcion i, Carrera c "
			+ "WHERE c.idCarrera = i.id.carrera.idCarrera "
			+ "AND e.lu = i.id.estudiante.lu "
			+ "AND i.id.carrera.idCarrera = :carrera AND e.ciudad = :ciudad")
	public List<Estudiante> getByCarreraYCiudad(int carrera, String ciudad);

	
//	EJEMPLO DESDE PerroRepositorio
//@Query("SELECT p FROM Perro p WHERE p.habilidad = :habilidad ORDER BY p.edad ASC")
//public List<Perro> getPerrosPorHabilidadOrderByEdadAsc(String habilidad);
//    List<Perro> findByHabilidad(String habilidad);

}
