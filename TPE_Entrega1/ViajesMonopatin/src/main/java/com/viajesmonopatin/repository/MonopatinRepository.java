package com.viajesmonopatin.repository;

import java.util.List;

import com.viajesmonopatin.model.Monopatin;

public interface MonopatinRepository extends RepoBase<Monopatin, Long>{
	static List<String> sorteableFields = List.of("id", "latitud","longitud","direccion");
}
