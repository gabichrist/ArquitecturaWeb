package com.example.entregable3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entregable3.dtos.InscripcionDTO;
import com.example.entregable3.exception.ExpectableException;
import com.example.entregable3.model.Carrera;
import com.example.entregable3.model.Estudiante;
import com.example.entregable3.model.Inscripcion;
import com.example.entregable3.model.InscripcionId;
import com.example.entregable3.repository.CarreraRepository;
import com.example.entregable3.repository.EstudianteRepository;
import com.example.entregable3.repository.InscripcionRepository;

@Service("inscripcionServicio")
public class InscripcionServicio implements BaseService<Inscripcion> {

	@Autowired
	private InscripcionRepository inscripcionRepository;

	@Autowired
	private CarreraRepository carreraRepository;

	@Autowired
	private EstudianteRepository estudianteRepository;

	@Override
	public List<Inscripcion> findAll() throws Exception {
//		Sort sort = Sort.by(Sort.Direction.ASC, "nombre");
		return inscripcionRepository.findAll();
	}
	
	@Override
	public Inscripcion findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
//        try{
//            Optional<Inscripcion> estudianteBuscado = inscripcionRepository.findById(id);
//            return estudianteBuscado.get();
//        }catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
	}

	@Override
	public Inscripcion save(Inscripcion entity) throws Exception {
		try {
			return inscripcionRepository.save(entity);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Inscripcion save(InscripcionDTO inscripcionDto) throws Exception {
		System.out.println(inscripcionDto.getLu());
		
		Optional<Estudiante> optEstudiante = estudianteRepository.findById(inscripcionDto.getLu());
		if (!optEstudiante.isPresent())
			throw new ExpectableException("{\"error\":\"Error. Estudiante no encontrado.\"}");
		Estudiante estudiante = optEstudiante.get();
		
		Optional<Carrera> optCarrera = carreraRepository.findById(inscripcionDto.getIdCarrera());
		if (!optCarrera.isPresent())
			throw new ExpectableException("{\"error\":\"Error. Carrera no encontrada.\"}");
		Carrera carrera = optCarrera.get();
			
		InscripcionId id = new InscripcionId(estudiante, carrera);
		
		Optional<Inscripcion> optInscripcion = inscripcionRepository.findById(id);
		Inscripcion inscripcion = null;
		if (optInscripcion.isPresent()) {
			inscripcion = optInscripcion.get();
		} else {
			inscripcion = new Inscripcion(estudiante, carrera);
		}

		if (inscripcionDto.isEsGraduado() != null)
			inscripcion.setEsGraduado(inscripcionDto.isEsGraduado());

		if (inscripcionDto.getFechaEgreso() != null)
			inscripcion.setFechaEgreso(inscripcionDto.getFechaEgreso());
		
		if (inscripcionDto.getFechaIngreso() != null)
			inscripcion.setFechaIngreso(inscripcionDto.getFechaIngreso());
		
		try {
			return inscripcionRepository.save(inscripcion);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	@Override
	public Inscripcion update(Long id, Inscripcion entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
//		if (inscripcionRepository.existsById(id)) {
//			try {
//				inscripcionRepository.deleteById(id);
//				return true;
//			} catch (Exception e) {
//				throw new Exception(e.getMessage());
//			}
//		} else {
//			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
//		}
	}
}
