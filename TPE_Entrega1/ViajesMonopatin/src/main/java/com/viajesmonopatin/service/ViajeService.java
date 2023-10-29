package com.viajesmonopatin.service;


//import java.sql.Timestamp;
//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.viajesmonopatin.dto.CuentaDto;
import com.viajesmonopatin.dto.ViajeMonopatinUsuarioDto;
//import com.viajesmonopatin.enums.EstadoMonopatinEnum;
//import com.viajesmonopatin.enums.EstadoViajeEnum;
import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Monopatin;
//import com.viajesmonopatin.model.Parada;
import com.viajesmonopatin.model.Viaje;
import com.viajesmonopatin.repository.MonopatinRepository;
//import com.viajesmonopatin.repository.ParadaRepository;
import com.viajesmonopatin.repository.ViajeRepository;

@Service("ViajeService")
public class ViajeService implements BaseService<Viaje>{

		@Autowired
		private ViajeRepository viajeRepository;
		@Autowired
		private MonopatinRepository monopatinRepository;
		//private ParadaRepository paradaRepository;
		//private CuentaService cuentaService;
		

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
		
		// Inicia un viaje en monopatin
		public Viaje iniciarViaje(ViajeMonopatinUsuarioDto viajeMonopatinUsuarioDTO) throws Exception {
		
			Viaje v = new Viaje();		
				
			try {
				Optional<Monopatin> monopatinBuscado = monopatinRepository.findById(viajeMonopatinUsuarioDTO.getIdMonopatin());
				System.out.println("mon" + monopatinBuscado);
				Monopatin monopatin = monopatinBuscado.get();
				v.setMonopatin(monopatin);   				
				System.out.println("Monopatin " + monopatin);
				//Parada paradaInicio = paradaRepository.getByLatitudLongitud(monopatin.getLatitud(), monopatin.getLongitud());	
				
				//CuentaDto cuenta = cuentaService.findById(viajeMonopatinUsuarioDTO.getIdCuenta());
				//System.out.println(cuenta.getSaldo());
				// VERIFICAR QUE LA CUENTA ESTA HABITADA
			
				
			//		Float saldo = cuenta.getSaldo();
			//		if (saldo > 0) {				
			//			v.setIdUsuario(viajeMonopatinUsuarioDTO.getIdUsuario());
			//			v.setIdCuenta(viajeMonopatinUsuarioDTO.getIdCuenta());
					//	if (paradaInicio != null) {
						//	if (paradaDestino != null) {
				//				EstadoMonopatinEnum estadoMonopatin = monopatin.getEstado();
				//				if (estadoMonopatin.equals(EstadoMonopatinEnum.DISPONIBLE)) {					
				//				//	v.setParadaInicio(paradaInicio);
				//				//	v.setParadaDestino(paradaDestino);
				//					v.setTiempoInicio(Timestamp.valueOf(LocalDateTime.now()));
				//					v.setKilometrosRecorridos(0);
				//					v.setEstado(EstadoViajeEnum.ACTIVO);
					//				v.setTiempoFin(null);
				//					v.setTiempoPausaInicio(null);
						//			v.setTiempoPausaFin(null);		
									
									//QUITAR MONOPATIN DE LA PARADA		
							//		monopatin.setEstado(EstadoMonopatinEnum.EN_USO);
						//			monopatinRepository.save(monopatin);
							//		viajeRepository.save(v);
					//				return v;			
						//		} else {
								//	throw new ExpectableException("El monopatin no esta disponible");
							//	}
				//			} else {
					//			throw new ExpectableException("La parada destino no existe");
						//	}
							
						//} else {
						//	throw new ExpectableException("La parada de inicio no existe");
					//	}				
				//	} else {
				//		throw new ExpectableException("La cuenta no tiene sado disponible");
				//	}	
			
			} catch (Exception e) {
				System.out.println(" e " + e);
				throw new ExpectableException("{\"error\":\"Error. No se encontró el monopatin.\"}");
			}
			 
			 	
	

			
		

		
			return null;
		
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