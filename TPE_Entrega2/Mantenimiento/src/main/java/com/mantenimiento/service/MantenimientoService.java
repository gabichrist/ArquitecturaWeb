package com.mantenimiento.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantenimiento.dto.MonopatinUsoDto;
import com.mantenimiento.dto.RegistrarMonopatinMantemientoDTO;
import com.mantenimiento.exception.ExpectableException;
import com.mantenimiento.model.Mantenimiento;
import com.mantenimiento.repository.MantenimientoRepository;

@Service("MantenimientoService")
public class MantenimientoService {
	
		@Autowired
		private MonopatinUsoService monopatinUsoService;
	
		@Autowired
		private MantenimientoRepository mantenimientoRepository;

		public List<Mantenimiento> findAll() throws Exception {
			return mantenimientoRepository.findAll();
		}

		public Mantenimiento findById(ObjectId id) throws Exception {
			try {
				Optional<Mantenimiento> mantenimientoBuscado = mantenimientoRepository.findById(id);
				return mantenimientoBuscado.get();
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}

		public Mantenimiento save(Mantenimiento entity) throws Exception {
			try {
				return mantenimientoRepository.save(entity);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}

		public Mantenimiento update(ObjectId id, Mantenimiento entity) throws Exception {
			if (mantenimientoRepository.existsById(id)) {
				try {
					Mantenimiento mantenimiento = mantenimientoRepository.findById(id).get();
					if (entity.getIdMonopatin() != null) {
						mantenimiento.setIdMonopatin(entity.getIdMonopatin());
					}				
					if (entity.getInicio() != null) {
						mantenimiento.setInicio(entity.getInicio());
					}
					if (entity.getFin() != null) {
						mantenimiento.setFin(entity.getFin());
					}
					if (entity.getDescripcion() != null) {
						mantenimiento.setDescripcion(entity.getDescripcion());
					}
					
					return mantenimientoRepository.save(mantenimiento);

				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}
			} else {
				throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
			}
		}


		public boolean delete(ObjectId id) throws Exception {
			if (mantenimientoRepository.existsById(id)) {
				try {
					mantenimientoRepository.deleteById(id);
					return true;
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}
			} else {
				throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
			}
		}


		public MonopatinUsoDto registrarMonopatinEnMantenimiento(Long id, RegistrarMonopatinMantemientoDTO descripcion) throws Exception {
			if (mantenimientoRepository.getMantenimientoEnCurso(id) != null) {
				throw new ExpectableException("Ya hay un mantenimiento en curso para el monopatín");
			}
			try {
				Mantenimiento m = new Mantenimiento();
				m.setIdMonopatin(id);
				m.setInicio(Instant.now());
				m.setDescripcion(descripcion.getDescripcion());
				mantenimientoRepository.save(m);
				return monopatinUsoService.registrarMonopatinEnMantenimiento(id);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		
		public MonopatinUsoDto finalizarMantenimientoMonopatin(Long id, RegistrarMonopatinMantemientoDTO descripcion) throws Exception {
			if (mantenimientoRepository.getMantenimientoEnCurso(id) == null) {
				throw new ExpectableException("No se encontró un mantenimiento en curso para el monopatín");
			}
			try {
				Mantenimiento m = mantenimientoRepository.getMantenimientoEnCurso(id); 
				Date now = new Date();
				m.setFin(Instant.now());
				if (descripcion.getDescripcion() != null)
					m.setDescripcion(descripcion.getDescripcion());
				mantenimientoRepository.save(m);
				return monopatinUsoService.finalizarMantenimientoMonopatin(id);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
}
