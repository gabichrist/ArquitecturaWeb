package com.example.entregable3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entregable3.model.Estudiante;
import com.example.entregable3.repository.EstudianteRepository;

@Service("estudianteServicio")
public class EstudianteServicio implements BaseService<Estudiante> {

	@Autowired
	private EstudianteRepository estudianteRepository;

	@Override
	public List<Estudiante> findAll() throws Exception {
		return estudianteRepository.findAll();
	}

	@Override
	public Estudiante findById(Long id) throws Exception {
        try{
            Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(id);
            return estudianteBuscado.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	public Estudiante save(Estudiante entity) throws Exception {
	try {
		System.out.println("entit" + entity);
		return estudianteRepository.save(entity);
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
}

	@Override
	public Estudiante update(Long id, Estudiante entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
