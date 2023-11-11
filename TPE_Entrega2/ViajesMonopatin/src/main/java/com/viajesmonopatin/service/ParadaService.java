package com.viajesmonopatin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Parada;
import com.viajesmonopatin.repository.ParadaRepository;

@Service("paradaServicio")
public class ParadaService implements BaseService<Parada> {

	@Autowired
	private ParadaRepository paradaRepository;

	@Override
	public List<Parada> findAll() throws Exception {
		return this.paradaRepository.findAll();
	}

	@Override
	public Parada findById(Long id) throws Exception {
		try {
			Optional<Parada> paradaBuscada = paradaRepository.findById(id);
			if (paradaBuscada.isEmpty())
				throw new ExpectableException("No se encontró una parada con el id solicitado");
			return paradaBuscada.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Parada getByLatitudLongitud(Float latitud, Float longitud) throws Exception {
		try {
			Parada paradaBuscada = paradaRepository.getByLatitudLongitud(latitud, longitud);
			if (paradaBuscada == null)
				throw new ExpectableException("No se encontró una parada en las coordenadas especificadas");
			return paradaBuscada;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
	}

	@Override
	public Parada save(Parada entity) throws Exception {
		try {
			return paradaRepository.save(entity);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Parada update(Long id, Parada entity) throws Exception {
		Optional<Parada> optionalParada = paradaRepository.findById(id);
		if (!optionalParada.isPresent())
			throw new ExpectableException("No existe una entidad con el id especificado");
		Parada parada = optionalParada.get();
		if (entity.getLatitud() != null)
			parada.setLatitud(entity.getLatitud());
		if (entity.getLongitud() != null)
			parada.setLongitud(entity.getLongitud());		
		if (entity.getDireccion() != null)
			parada.setDireccion(entity.getDireccion());		
		return this.paradaRepository.save(parada);
	}

	@Override
	public boolean delete(Long id) throws Exception {
		if (paradaRepository.existsById(id)) {
			paradaRepository.deleteById(id);
			return true;
		} else {
			throw new ExpectableException("No existe una parada con el id indicado");
		}
	}
}
