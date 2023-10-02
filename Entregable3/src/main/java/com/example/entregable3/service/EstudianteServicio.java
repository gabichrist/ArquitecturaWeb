package com.example.entregable3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entregable3.exception.ExpectableException;
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

	public List<Estudiante> findAll(String sortBy) throws Exception {
		String field = sortBy.toLowerCase();
		if (EstudianteRepository.sorteableFields.contains(field)) {
			Sort sort = Sort.by(Sort.Direction.ASC, field);
			return estudianteRepository.findAll(sort);
		} else {
			throw new ExpectableException("Debe ingresar un valor válido para sortBy " + EstudianteRepository.sorteableFields);
		}
	}
	
	public List<Estudiante> findAll(String sortBy, String genre) throws Exception {
		if (genre.isBlank()) {
			return findAll(sortBy);
		}
		List<Estudiante> estudiantes = estudianteRepository.getByGenre(genre);
		
		return estudiantes;
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
	
	public List<Estudiante> findByCarreraYCiudad(int carrera, String ciudad) throws Exception{
		try {
			List<Estudiante> estudiantes = estudianteRepository.getByCarreraYCiudad(carrera,ciudad);
			return estudiantes;		
		} catch (Exception e) {
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
		if (estudianteRepository.existsById(id)) {
			try {
				estudianteRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		} else {
			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
		}
	}
}
