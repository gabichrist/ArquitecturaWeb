package com.viajesmonopatin.repository;

import java.util.List;

import com.viajesmonopatin.model.Parada;

public interface ParadaRepository extends RepoBase<Parada, Long>{
	static List<String> sorteableFields = List.of("id", "latitud","longitud","direccion");
}
