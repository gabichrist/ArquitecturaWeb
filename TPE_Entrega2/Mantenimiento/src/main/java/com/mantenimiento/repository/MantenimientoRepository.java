package com.mantenimiento.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mantenimiento.model.Mantenimiento;

@Repository
public interface MantenimientoRepository extends MongoRepository<Mantenimiento, ObjectId> {

	@Query("SELECT m FROM Mantenimiento m WHERE m.id = :id")
	public Mantenimiento getById(ObjectId id);
	
	@Query("SELECT m FROM Mantenimiento m WHERE m.idMonopatin = :id AND m.fin IS NULL")
	public Mantenimiento getMantenimientoEnCurso(Long id);
	
}