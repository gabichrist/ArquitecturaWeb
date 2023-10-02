package com.example.entregable3.repository;


//import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.example.entregable3.dtos.CarreraConInscriptosDTO;
import com.example.entregable3.model.Carrera;

@Repository("CarreraRepository")
public interface CarreraRepository extends RepoBase<Carrera, Long>{
 
	@Query("SELECT c FROM Carrera c WHERE c.idCarrera = :idCarrera")
	public Carrera getById(int idCarrera);

	//@Query("SELECT NEW CarreraConInscriptosDTO(c.nombre, (SELECT COUNT(i.estudiante) as cantidadEstudiantes "
	//		+ "FROM Inscripcion i "
	//		+ "JOIN i.estudiante e "
	//		+ "WHERE i.carrera.idCarrera = c.idCarrera AND i.estudiante.lu = e.lu) "
	//		+ "FROM Carrera c")
	//public List<CarreraConInscriptosDTO> findByInscriptos();	
	
}
