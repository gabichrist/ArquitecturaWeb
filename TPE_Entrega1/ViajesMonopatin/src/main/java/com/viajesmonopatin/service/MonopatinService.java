package com.viajesmonopatin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajesmonopatin.dto.MonopatinDto;
import com.viajesmonopatin.enums.EstadoMonopatinEnum;
import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Monopatin;
import com.viajesmonopatin.model.Parada;
import com.viajesmonopatin.repository.MonopatinRepository;
import com.viajesmonopatin.repository.ParadaRepository;

@Service("monopatinServicio")
public class MonopatinService implements BaseService<Monopatin> {

	@Autowired
	private MonopatinRepository monopatinRepository;

	@Autowired
	private ParadaRepository paradaRepository;

	@Override
	public List<Monopatin> findAll() throws Exception {
		return monopatinRepository.findAll();
	}

	@Override
	public Monopatin findById(Long id) throws Exception {
		try {
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
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Monopatin save(MonopatinDto entity) throws Exception {
		Monopatin monopatin = new Monopatin(entity);
		if (entity.getIdParada() != null) {
			Optional<Parada> optParada = paradaRepository.findById(entity.getIdParada());
			if (optParada.isEmpty())
				throw new ExpectableException("No existe una parada con el id especificado");
			monopatin.setParada(optParada.get());
		}
		try {
			return this.monopatinRepository.save(monopatin);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
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
		if (entity.getIdParada() != null) {
			Optional<Parada> optParada = paradaRepository.findById(entity.getIdParada());
			if (optParada.isEmpty())
				throw new ExpectableException("No existe una parada con el id especificado");
			monopatin.setParada(optParada.get());
		}
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

	public List<Monopatin> getMonopatinesOperativos() {
		List<Monopatin> monopatinesEnUso = monopatinRepository.getMonopatinesByEstado(EstadoMonopatinEnum.EN_USO);
		List<Monopatin> monopatinesDisponibles = monopatinRepository
				.getMonopatinesByEstado(EstadoMonopatinEnum.DISPONIBLE);
		monopatinesDisponibles.addAll(monopatinesEnUso);
		return monopatinesDisponibles;
	}

	public List<Monopatin> getMonopatinesEnMantenimiento() {
		return monopatinRepository.getMonopatinesByEstado(EstadoMonopatinEnum.EN_MANTENIMIENTO);
	}

	public Monopatin registrarMonopatinEnMantenimiento(Long id) throws Exception {
		if (monopatinRepository.existsById(id)) {
			try {
				Monopatin m = monopatinRepository.getById(id);
				m.setEstado(EstadoMonopatinEnum.EN_MANTENIMIENTO);
				return monopatinRepository.save(m);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		} else {
			throw new ExpectableException("No existe una monopatin con el id indicado");
		}

	}
	
	public List<Monopatin> getMonopatinesDisponiblesEnLaZona(Float latitud, Float longitud){
		return monopatinRepository.getMonopatinesDisponiblesEnLaZona(latitud, longitud);
	}
}
