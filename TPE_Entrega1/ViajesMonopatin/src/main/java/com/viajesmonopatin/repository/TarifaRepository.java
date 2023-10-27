package com.viajesmonopatin.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.viajesmonopatin.model.Tarifa;

@Repository("TarifaRepository")
public interface TarifaRepository extends RepoBase<Tarifa, Long>{
	static List<String> sorteableFields = List.of("id", "tarifa", "tarifaExtra", "validoDesde");
	
//	@Query() el fecha desde now
}
