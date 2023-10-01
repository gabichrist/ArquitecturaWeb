package com.example.entregable3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entregable3.exception.ExpectableException;
import com.example.entregable3.model.Carrera;
import com.example.entregable3.repository.CarreraRepository;

@Service("carreraServicio")
public class CarreraServicio implements BaseService<Carrera> {

	@Autowired
	private CarreraRepository carreraRepository;

	@Override
	public List<Carrera> findAll() throws Exception {
		Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
		return carreraRepository.findAll(sort);
	}
	
	@Override
	public Carrera findById(Long id) throws Exception {
        try{
            Optional<Carrera> estudianteBuscado = carreraRepository.findById(id);
            return estudianteBuscado.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	public Carrera save(Carrera entity) throws Exception {
	try {
		return carreraRepository.save(entity);
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
}

	@Override
	public Carrera update(Long id, Carrera entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		if (carreraRepository.existsById(id)) {
			try {
				carreraRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		} else {
			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
		}
	}
}
