package com.viajesmonopatin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Tarifa;
import com.viajesmonopatin.repository.TarifaRepository;

@Service("tarifaServicio")
public class TarifaService implements BaseService<Tarifa>{
	
	@Autowired
	private TarifaRepository tarifaRepository;

	@Override
	public List<Tarifa> findAll() throws Exception {
		return tarifaRepository.findAll();
	}
	
	public List<Tarifa> findAll(String sortBy) throws Exception {
		String field = sortBy.toLowerCase();
		if (TarifaRepository.sorteableFields.contains(field)) {
			Sort sort = Sort.by(Sort.Direction.ASC, field);
			return tarifaRepository.findAll(sort);
		} else {
			throw new ExpectableException("Debe ingresar un valor válido para sortBy " + TarifaRepository.sorteableFields);
		}
	}

	@Override
	public Tarifa findById(Long id) throws Exception {
		try {
			Optional<Tarifa> tarifaBuscada = tarifaRepository.findById(id);
			return tarifaBuscada.get();
		}catch(Exception e) {
			throw new Exception (e.getMessage());
		}
	}

	@Override
	public Tarifa save(Tarifa entity) throws Exception {
		try {
			System.out.println("entity " + entity);
			return tarifaRepository.save(entity);
		}catch(Exception e) {
			throw new Exception (e.getMessage());
		}
	}

	@Override
	public Tarifa update(Long id, Tarifa entity) throws Exception {
		if(tarifaRepository.existsById(id)) {
			try {
				Tarifa tarifa = tarifaRepository.findById(id).get();
				if(entity.getTarifa() != null) {
					tarifa.setTarifa(entity.getTarifa());
				}
				if(entity.getTarifaExtra() != null) {
					tarifa.setTarifaExtra(entity.getTarifaExtra());
				}
				if(entity.getValidoDesde() != null) {
					tarifa.setValidoDesde(entity.getValidoDesde());
				}
				return tarifaRepository.save(tarifa);
			}catch(Exception e){
				throw new Exception (e.getMessage());
			}
		} else {
			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
		}
	}

	@Override
	public boolean delete(Long id) throws Exception {
		if(tarifaRepository.existsById(id)) {
			try {
				tarifaRepository.deleteById(id);
				return true;
			}catch(Exception e){
				throw new Exception(e.getMessage());
			}
		} else {
			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
		}
	}
}
