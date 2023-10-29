package com.viajesmonopatin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajesmonopatin.dto.MonopatinDto;
import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Monopatin;
import com.viajesmonopatin.repository.MonopatinRepository;

@Service("monopatinServicio")
public class MonopatinService implements BaseService<Monopatin> {

	@Autowired
	private MonopatinRepository monopatinRepository;

	@Override
	public List<Monopatin> findAll() throws Exception {
		return monopatinRepository.findAll();
	}

	@Override
	public Monopatin findById(Long id) throws Exception {
		try {
			System.out.println(id);
			Optional<Monopatin> monopatinBuscada = monopatinRepository.findById(id);
			if (monopatinBuscada.isEmpty())
				throw new ExpectableException("No se encontró una monopatin con el id solicitado");
			return monopatinBuscada.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Monopatin save(Monopatin entity) throws Exception {
		try {
			return this.monopatinRepository.save(entity);			
		}catch(Exception e) {
			throw new Exception (e.getMessage());
		}
	}

	public Monopatin save(MonopatinDto dto) throws Exception {
		try {
			return this.monopatinRepository.save(new Monopatin(dto));			
		}catch(Exception e) {
			throw new Exception (e.getMessage());
		}
	}

	@Override
	public Monopatin update(Long id, Monopatin entity) throws Exception {
		throw new Exception("Not implemented. Use MonopatinDto");
	}
	
	public Monopatin update(Long id, MonopatinDto entity) throws Exception {
		Optional<Monopatin> optionalMonopatin = monopatinRepository.findById(id);
		if (!optionalMonopatin.isPresent())
			throw new ExpectableException("No existe una entidad con el id especificado");
		Monopatin monopatin = optionalMonopatin.get();
		if (entity.getLatitud() != null)
			monopatin.setLatitud(entity.getLatitud());
		if (entity.getLongitud() != null)
			monopatin.setLongitud(entity.getLongitud());		
		if (entity.getEstado() != null)
			monopatin.setEstado(entity.getEstado());
		return this.monopatinRepository.save(monopatin);
	}

	@Override
	public boolean delete(Long id) throws Exception {
		if (monopatinRepository.existsById(id)) {
			monopatinRepository.deleteById(id);
			return true;
		} else {
			throw new ExpectableException("No existe una monopatin con el id indicado");
		}
	}
}
