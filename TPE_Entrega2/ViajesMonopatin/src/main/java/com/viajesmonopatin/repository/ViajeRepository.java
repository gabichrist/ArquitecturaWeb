package com.viajesmonopatin.repository;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viajesmonopatin.dto.ViajeMonopatinDto;
import com.viajesmonopatin.model.Viaje;

@Repository("ViajeRepository")
public interface ViajeRepository extends RepoBase<Viaje, Long>{	

	@Query("SELECT new com.viajesmonopatin.dto.ViajeMonopatinDto(v.monopatin.id, COUNT(v.monopatin.id)) "
			+ "FROM Viaje v WHERE YEAR(v.tiempoInicio) = :anio "
			+ "GROUP BY v.monopatin.id "
			+ "HAVING COUNT(v.monopatin.id) >= :cantidad ")
	public List<ViajeMonopatinDto> getPorCantidadMinimaViajesMonopatinAnual(int anio, Long cantidad);
	
	@Query("SELECT SUM(v.costoViaje) FROM Viaje v WHERE v.tiempoInicio >= :desde AND v.tiempoInicio < :hasta")
	public Float obtenerFacturacion(Timestamp desde, Timestamp hasta);
}
