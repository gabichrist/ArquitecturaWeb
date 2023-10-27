package com.mantenimiento.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mantenimiento.model.Mantenimiento;

@Repository("MantenimientoRepository")
public interface MantenimientoRepository extends RepoBase<Mantenimiento, Integer> {

	@Query("SELECT m FROM Mantenimiento m WHERE m.id = :id")
	public Mantenimiento getById(int id);

	
}

