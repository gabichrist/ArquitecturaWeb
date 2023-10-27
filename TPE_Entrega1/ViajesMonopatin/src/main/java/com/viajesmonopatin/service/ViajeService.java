package com.viajesmonopatin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajesmonopatin.dto.ViajeMonopatinUsuarioDto;
import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Viaje;
import com.viajesmonopatin.repository.ViajeRepository;

@Service("ViajeService")
public class ViajeService implements BaseService<Viaje>{

		@Autowired
		private ViajeRepository viajeRepository;

		@Override
		public List<Viaje> findAll() throws Exception {
			return viajeRepository.findAll();
		}

		@Override
		public Viaje findById(Long id) throws Exception {
			try {
				Optional<Viaje> viajeBuscado = viajeRepository.findById(id);
				return viajeBuscado.get();
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}

		@Override
		public Viaje save(Viaje entity) throws Exception {	
			try {
				return viajeRepository.save(entity);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		
		// Genera un viaje
		public Viaje generarViaje(ViajeMonopatinUsuarioDto viajeMonopatinUsuarioDTO) {
			Viaje v = new Viaje();	
			return v;			
		}

		@Override
		public Viaje update(Long id, Viaje entity) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean delete(Long id) throws Exception {
			if (viajeRepository.existsById(id)) {
				viajeRepository.deleteById(id);
				return true;
			} else {
				throw new ExpectableException("No existe un viaje con el id indicado");
			}
		}

		
}