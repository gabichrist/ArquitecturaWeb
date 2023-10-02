package com.example.entregable3.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entregable3.dtos.CarreraConInscriptosDTO;
import com.example.entregable3.dtos.ReporteCarreraDTO;
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

	@Query(value = "select "
			+ "						c.nombre,"
			+ "       				COALESCE(year, 0) year,"
			+ "						(SUM(CASE WHEN(EXTRACT(year from i.fecha_ingreso) = year.year) THEN 1 ELSE 0 END)) as inscriptos,"
			+ "						(SUM(CASE WHEN(i.es_graduado = true and EXTRACT(year from i.fecha_egreso) = year.year) THEN 1 ELSE 0 END)) as egresados"
			+ "					"
			+ "					from Carrera c"
			+ "				    left join Inscripcion i on c.id_carrera = i.id_carrera"
			+ "				    left join ("
			+ "				        select id_carrera, extract(year from fecha_egreso) year from Inscripcion where fecha_egreso is not null"
			+ "				            union"
			+ "				        select id_carrera, extract(year from fecha_ingreso) year from Inscripcion"
			+ "				    ) year ON year.id_carrera = c.id_carrera"
			+ "				GROUP BY c.nombre, i.id_carrera, year.year"
			+ "				ORDER BY c.nombre, year.year"
			, nativeQuery = true)
	List<Object[]> getReporteCarreras();

}
