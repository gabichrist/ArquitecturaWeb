package com.example.entregable3.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entregable3.model.Carrera;

@Repository("CarreraRepository")
public interface CarreraRepository extends RepoBase<Carrera, Long>{
 
	@Query("SELECT c FROM Carrera c WHERE c.idCarrera = :idCarrera")
	public Carrera getById(int idCarrera);	
}
