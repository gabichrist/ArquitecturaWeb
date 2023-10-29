package com.viajesmonopatin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.viajesmonopatin.model.Parada;

public interface ParadaRepository extends RepoBase<Parada, Long>{
	static List<String> sorteableFields = List.of("id", "latitud","longitud","direccion");
	
	@Query("SELECT p FROM Parada p WHERE p.latitud BETWEEN :latitud - 1.0 AND :latitud + 1.0 "
		    + "AND p.longitud BETWEEN :longitud - 1.0 AND :longitud + 1.0")
	Parada getByLatitudLongitud(Float latitud, Float longitud);

}
