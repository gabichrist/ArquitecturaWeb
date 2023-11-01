package com.mantenimiento.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantenimiento.dto.MonopatinUsoDto;
import com.mantenimiento.dto.RegistrarMonopatinMantemientoDTO;
import com.mantenimiento.exception.ExpectableException;
import com.mantenimiento.model.Mantenimiento;
import com.mantenimiento.repository.MantenimientoRepository;

@Service("MantenimientoService")
public class MantenimientoService implements BaseService<Mantenimiento>{
	
		@Autowired
		private MonopatinUsoService monopatinUsoService;
	
		@Autowired
		private MantenimientoRepository mantenimientoRepository;

		@Override
		public List<Mantenimiento> findAll() throws Exception {
			return mantenimientoRepository.findAll();
		}

		@Override
		public Mantenimiento findById(Long id) throws Exception {
			try {
				Optional<Mantenimiento> mantenimientoBuscado = mantenimientoRepository.findById(id);
				return mantenimientoBuscado.get();
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}

		@Override
		public Mantenimiento save(Mantenimiento entity) throws Exception {
			try {
				return mantenimientoRepository.save(entity);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}

		@Override
		public Mantenimiento update(Long id, Mantenimiento entity) throws Exception {
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

		@Override
		public boolean delete(Long id) throws Exception {
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
			try {
				Mantenimiento m = new Mantenimiento();
				m.setIdMonopatin(id);
				Date now = new Date();
				m.setInicio(new Timestamp(now.getTime()));
				m.setDescripcion(descripcion.getDescripcion());
				mantenimientoRepository.save(m);
				return monopatinUsoService.registrarMonopatinEnMantenimiento(id);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		
		public MonopatinUsoDto finalizarMantenimientoMonopatin(Long id, RegistrarMonopatinMantemientoDTO descripcion) throws Exception {
			try {
				
				Mantenimiento m = mantenimientoRepository.getMantenimientoEnCurso(id); 
				Date now = new Date();
				m.setFin(new Timestamp(now.getTime()));
				if (descripcion.getDescripcion() != null)
					m.setDescripcion(descripcion.getDescripcion());
				mantenimientoRepository.save(m);
				return monopatinUsoService.finalizarMantenimientoMonopatin(id);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
}
