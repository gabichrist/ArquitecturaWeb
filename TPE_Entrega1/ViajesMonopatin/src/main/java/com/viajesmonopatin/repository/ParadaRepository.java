package com.viajesmonopatin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.viajesmonopatin.model.Parada;

public interface ParadaRepository extends RepoBase<Parada, Long>{
	static List<String> sorteableFields = List.of("id", "latitud","longitud","direccion");
	
	@Query("SELECT p FROM Parada p WHERE p.latitud = :latitud AND p.longitud = :longitud")
	Parada getByLatitudLongitud(Float latitud, Float longitud);

}
