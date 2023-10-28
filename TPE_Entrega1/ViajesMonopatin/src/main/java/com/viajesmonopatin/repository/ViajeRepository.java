package com.viajesmonopatin.repository;


import org.springframework.stereotype.Repository;

import com.viajesmonopatin.model.Viaje;

@Repository("ViajeRepository")
public interface ViajeRepository extends RepoBase<Viaje, Long>{	

}
