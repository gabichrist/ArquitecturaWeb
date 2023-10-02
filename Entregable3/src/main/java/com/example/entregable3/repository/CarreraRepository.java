package com.example.entregable3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entregable3.dtos.CarreraConInscriptosDTO;
import com.example.entregable3.model.Carrera;

@Repository("CarreraRepository")
public interface CarreraRepository extends RepoBase<Carrera, Long>{
 
	@Query("SELECT c FROM Carrera c WHERE c.idCarrera = :idCarrera")
	public Carrera getById(int idCarrera);

	// Para la query nativa del último punto algo así:	
	// @Query( value= "SELECT new com.example.entregable3.dtos. c, count(*) FROM Carrera c JOIN Inscripcion i ON c.idCarrera = i.id.carrera.idCarrera", nativeQuery = true)

	@Query("SELECT distinct new com.example.entregable3.dtos.CarreraConInscriptosDTO(c) "
			+ "FROM Carrera c "
			+ "JOIN c.inscripcionSet")
	public List<CarreraConInscriptosDTO> findByInscriptos();

}
