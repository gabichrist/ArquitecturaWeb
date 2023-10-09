package com.example.entregable3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entregable3.dtos.CarreraConInscriptosDTO;
import com.example.entregable3.dtos.ReporteCarreraDTO;
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
            Optional<Carrera> carreraBuscada = carreraRepository.findById(id);
            return carreraBuscada.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	public List<CarreraConInscriptosDTO> findByInscriptos() throws Exception {
		try{
            return carreraRepository.findByInscriptos()
            		.stream()
            		.map(c -> new CarreraConInscriptosDTO(c))
            		.toList();
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
		if (carreraRepository.existsById(id)) {
			try {
				Carrera carrera = carreraRepository.findById(id).get();
				if(entity.getNombre() != null) {
					carrera.setNombre(entity.getNombre());
				}
				return carreraRepository.save(carrera);
			} catch (Exception e){
				throw new Exception(e.getMessage());
			}		
		} else {
			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
		}
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
	
	public List<ReporteCarreraDTO> getReporteCarreras() {
        List<Object[]> resultados = carreraRepository.getReporteCarreras();

        List<ReporteCarreraDTO> dtos = new ArrayList<>();
        for (Object[] resultado : resultados) {
            String carrera = (String) resultado[0];
            int anio = ((Number) resultado[1]).intValue();
            long cantidadInscriptos = ((Number) resultado[2]).longValue();
            long cantidadEgresados = ((Number) resultado[3]).longValue();
            dtos.add(new ReporteCarreraDTO(carrera, anio, cantidadInscriptos, cantidadEgresados));
        }
        return dtos;
	}
}
