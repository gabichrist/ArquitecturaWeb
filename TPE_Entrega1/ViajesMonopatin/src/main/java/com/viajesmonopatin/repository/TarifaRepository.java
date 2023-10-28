package com.viajesmonopatin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viajesmonopatin.model.Tarifa;

@Repository("TarifaRepository")
public interface TarifaRepository extends RepoBase<Tarifa, Long>{
	static List<String> sorteableFields = List.of("id", "tarifa", "tarifaExtra", "validoDesde");
	
	@Query("SELECT t FROM Tarifa t WHERE validoDesde <= CURDATE() ORDER BY validoDesde DESC LIMIT 1")
	public Optional<Tarifa> getCurrentPrice();
	
	@Query("SELECT t FROM Tarifa t WHERE validoDesde = : validoDesde")
	public Optional<Tarifa> getPriceByDate();
}
