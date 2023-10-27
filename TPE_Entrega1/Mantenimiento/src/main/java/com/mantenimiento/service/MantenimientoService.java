package com.mantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantenimiento.exception.ExpectableException;
import com.mantenimiento.model.Mantenimiento;
import com.mantenimiento.repository.MantenimientoRepository;

@Service("MantenimientoService")
public class MantenimientoService implements BaseService<Mantenimiento>{

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

	

}
