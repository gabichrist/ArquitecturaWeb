package com.mantenimiento.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mantenimiento.model.Mantenimiento;

@Repository("MantenimientoRepository")
public interface MantenimientoRepository extends RepoBase<Mantenimiento, Long> {

	@Query("SELECT m FROM Mantenimiento m WHERE m.id = :id")
	public Mantenimiento getById(Long id);
	
	@Query("SELECT m FROM Mantenimiento m WHERE m.idMonopatin = :id AND m.fin IS NULL")
	public Mantenimiento getMantenimientoEnCurso(Long id);
	
}

