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
	private CarreraRepository careraRepository;

	@Override
	public List<Carrera> findAll() throws Exception {
		Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
		return careraRepository.findAll(sort);
	}
	
	@Override
	public Carrera findById(Long id) throws Exception {
        try{
            Optional<Carrera> estudianteBuscado = careraRepository.findById(id);
            return estudianteBuscado.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	public Carrera save(Carrera entity) throws Exception {
	try {
		System.out.println("entit" + entity);
		return careraRepository.save(entity);
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
		// TODO Auto-generated method stub
		return false;
	}
}
