package com.viajesmonopatin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viajesmonopatin.enums.EstadoMonopatinEnum;
import com.viajesmonopatin.model.Monopatin;

@Repository("MonopatinRepository")
public interface MonopatinRepository extends RepoBase<Monopatin, Long>{
	static List<String> sorteableFields = List.of("id", "latitud","longitud","direccion");
	
	@Query("SELECT m FROM Monopatin m WHERE m.id = :id")
	public Monopatin getById(Long id);
	
	@Query("SELECT m FROM Monopatin m WHERE m.estado = :estado")
	public List<Monopatin> getMonopatinesByEstado(EstadoMonopatinEnum estado);
}
