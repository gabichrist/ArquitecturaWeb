package com.viajesmonopatin.service;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Optional;

import org.hibernate.boot.model.source.internal.hbm.RelationalValueSourceHelper.AbstractColumnsAndFormulasSource;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajesmonopatin.dto.CuentaDto;
import com.viajesmonopatin.dto.ViajeMonopatinDto;
import com.viajesmonopatin.dto.ViajeMonopatinUsuarioDto;
import com.viajesmonopatin.enums.EstadoMonopatinEnum;
import com.viajesmonopatin.enums.EstadoViajeEnum;
import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Monopatin;
import com.viajesmonopatin.model.Parada;
import com.viajesmonopatin.model.Viaje;
import com.viajesmonopatin.repository.MonopatinRepository;
import com.viajesmonopatin.repository.ParadaRepository;
import com.viajesmonopatin.repository.ViajeRepository;

@Service("ViajeService")
public class ViajeService implements BaseService<Viaje>{

		@Autowired
		private ViajeRepository viajeRepository;
		@Autowired
		private MonopatinRepository monopatinRepository;
		@Autowired
		private ParadaRepository paradaRepository;
		@Autowired
		private CuentaService cuentaService;
		

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
						
				Optional<Monopatin> monopatinBuscado = monopatinRepository.findById(viajeMonopatinUsuarioDTO.getIdMonopatin());
				if (monopatinBuscado.isEmpty())
					throw new ExpectableException("No se encontró el monopatin indicado");
				Monopatin monopatin = monopatinBuscado.get();
				v.setMonopatin(monopatin);   		
	
				try {
					Parada paradaInicio = paradaRepository.getByLatitudLongitud(monopatin.getLatitud(), monopatin.getLongitud());	
					v.setParadaInicio(paradaInicio);
				} catch (Exception e) {			
					throw new ExpectableException("No se encontró la parada de inicio indicada");
				}
								
				Optional<Parada> paradaDestinoBuscada = paradaRepository.findById(viajeMonopatinUsuarioDTO.getIdParadaDestino());
				
				if (paradaDestinoBuscada.isEmpty())
					throw new ExpectableException("No se encontró la parada destino indicada");
				Parada paradaDestino = paradaDestinoBuscada.get();	
				
				System.out.println(viajeMonopatinUsuarioDTO.getIdCuenta());
				
		
				CuentaDto cuenta = this.cuentaService.findById(viajeMonopatinUsuarioDTO.getIdCuenta());
					
				if (cuenta != null) {
					List<Long> idUsuariosCuenta = cuenta.getIdUsuarios();
					if (!idUsuariosCuenta.contains(viajeMonopatinUsuarioDTO.getIdUsuario()))
						throw new ExpectableException("El usuario no pertenece a la cuenta indicada");	
					if (!cuenta.getHabilitada()) 						
						throw new ExpectableException("La cuenta no esta habilitada");		
					if (cuenta.getSaldo() <= 0) 						
						throw new ExpectableException("La cuenta no tiene sado disponible");				
				}else {
					throw new ExpectableException("No se encontro la cuenta indicada");
				}
								
				v.setIdUsuario(viajeMonopatinUsuarioDTO.getIdUsuario());
				v.setIdCuenta(viajeMonopatinUsuarioDTO.getIdCuenta());
						
				EstadoMonopatinEnum estadoMonopatin = monopatin.getEstado();
				if (estadoMonopatin.equals(EstadoMonopatinEnum.DISPONIBLE)) {					
					v.setParadaDestino(paradaDestino);
					v.setTiempoInicio(Timestamp.valueOf(LocalDateTime.now()));
					v.setKilometrosRecorridos(0);
					v.setEstado(EstadoViajeEnum.ACTIVO);
					v.setTiempoFin(null);
					v.setTiempoPausaInicio(null);
					v.setTiempoPausaFin(null);										
					monopatin.setEstado(EstadoMonopatinEnum.EN_USO);
					monopatinRepository.save(monopatin);
					viajeRepository.save(v);
					return v;			
				} else {
					throw new ExpectableException("El monopatin no esta disponible");
				}										
								 		
		}
		
		public Viaje pausarViaje(Long id) throws ExpectableException {
			Optional<Viaje> viajeBuscado = viajeRepository.findById(id);
			if (viajeBuscado.isEmpty())
				throw new ExpectableException("No se encontró el viaje indicado");
			Viaje viaje = viajeBuscado.get();
			
			if (viaje.getTiempoPausaInicio( )== null) {
				viaje.setTiempoPausaInicio(Timestamp.valueOf(LocalDateTime.now()));
				viajeRepository.save(viaje);
			} else {
				throw new ExpectableException("El viaje ya se encuentra pausado");
			}	
			return viaje;
		}
		
		public Viaje reanudarViaje(Long id) throws ExpectableException {
			Optional<Viaje> viajeBuscado = viajeRepository.findById(id);
			if (viajeBuscado.isEmpty())
				throw new ExpectableException("No se encontró el viaje indicado");
			Viaje viaje = viajeBuscado.get();
			
			if (viaje.getTiempoPausaInicio() != null && viaje.getTiempoPausaFin() == null) {
				viaje.setTiempoPausaFin(Timestamp.valueOf(LocalDateTime.now()));
				
				Long diferenciaMiliSegundos = viaje.getTiempoPausaFin().getTime() - viaje.getTiempoPausaInicio().getTime();
				int tiempoPermitido = 5 * 60 * 1000;
				
				if (diferenciaMiliSegundos > tiempoPermitido ) {			
					Instant tiempoPausaInicioNuevo = viaje.getTiempoPausaInicio().toInstant().plus(15, ChronoUnit.MINUTES);
					viaje.setTiempoPausaInicio(Timestamp.from(tiempoPausaInicioNuevo));				
				} else  {
					System.out.println("tiempo menor");
				}
				viajeRepository.save(viaje);				
			} else {
				throw new ExpectableException("El viaje ya se encuentra en curso");
			}	
			return viaje;
		}
		
		public List<ViajeMonopatinDto> obtenerReportePorCantidadMinimaDeViajesAnual(int anio, Long cantidad) throws Exception{
			try {
				List<ViajeMonopatinDto> cantidadViajesMonopatin = viajeRepository.getPorCantidadMinimaViajesMonopatinAnual(anio, cantidad);
				return cantidadViajesMonopatin;
			} catch (Exception e) {
				 e.printStackTrace();
				throw new Exception(e.getMessage());
			}
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