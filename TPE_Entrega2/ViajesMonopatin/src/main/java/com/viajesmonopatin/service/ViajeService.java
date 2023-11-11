package com.viajesmonopatin.service;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viajesmonopatin.dto.CuentaDto;
import com.viajesmonopatin.dto.MonopatinDto;
import com.viajesmonopatin.dto.ViajeMonopatinDto;
import com.viajesmonopatin.dto.ViajeMonopatinUsuarioDto;
import com.viajesmonopatin.enums.EstadoMonopatinEnum;
import com.viajesmonopatin.enums.EstadoViajeEnum;
import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Monopatin;
import com.viajesmonopatin.model.Parada;
import com.viajesmonopatin.model.Tarifa;
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
		@Autowired
		private MonopatinService monopatinService;
		@Autowired
		private TarifaService tarifaService;
		

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
				
				if (monopatin.getParada() == null) {
					throw new ExpectableException("El monopatín debe tener una parada asiganda");
				}
				
				try {
//					Parada paradaInicio = paradaRepository.getByLatitudLongitud(monopatin.getLatitud(), monopatin.getLongitud());
					
					v.setParadaInicio(monopatin.getParada());
				} catch (Exception e) {			
					throw new ExpectableException("No se encontró la parada de inicio indicada");
				}
								
				Optional<Parada> paradaDestinoBuscada = paradaRepository.findById(viajeMonopatinUsuarioDTO.getIdParadaDestino());
				
				if (paradaDestinoBuscada.isEmpty())
					throw new ExpectableException("No se encontró la parada destino indicada");
				Parada paradaDestino = paradaDestinoBuscada.get();
				
				try {
					CuentaDto cuenta = this.cuentaService.findById(viajeMonopatinUsuarioDTO.getIdCuenta());					
					if (cuenta != null) {
						List<Long> idUsuariosCuenta = cuenta.getIdUsuarios();
						if (!idUsuariosCuenta.contains(viajeMonopatinUsuarioDTO.getIdUsuario()))
							throw new ExpectableException("El usuario no pertenece a la cuenta indicada");	
						if (!cuenta.getHabilitada()) 						
							throw new ExpectableException("La cuenta no esta habilitada");		
						if (cuenta.getSaldo() <= 0) 						
							throw new ExpectableException("La cuenta no tiene saldo disponible");				
					}else {
						throw new ExpectableException("No se encontro la cuenta indicada");
					}
				} catch(Exception e) {
					throw new ExpectableException("No se encontró la cuenta");
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
				int tiempoPermitido = 15 * 60 * 1000;
				
				if (diferenciaMiliSegundos > tiempoPermitido ) {			
					Instant tiempoPausaInicioNuevo = viaje.getTiempoPausaInicio().toInstant().plus(15, ChronoUnit.MINUTES);
					viaje.setTiempoPausaInicio(Timestamp.from(tiempoPausaInicioNuevo));				
				} 
				viajeRepository.save(viaje);				
			} else {
				throw new ExpectableException("El viaje ya se encuentra en curso");
			}	
			return viaje;
		}
		
		public Viaje finalizarViaje(Long id) throws Exception {
			Optional<Viaje> viajeBuscado = viajeRepository.findById(id);
			if (viajeBuscado.isEmpty())
				throw new ExpectableException("No se encontró el viaje indicado");			
			Viaje viaje = viajeBuscado.get();			
			if (viaje.getEstado().equals(EstadoViajeEnum.FINALIZADO)) 
				throw new ExpectableException("El viaje ya esta finalizado.");		
			
			Parada paradaDestino = viaje.getParadaDestino();
			Monopatin monopatin = viaje.getMonopatin();		
			
			try {				
				Long idMonopatin = monopatin.getId();
				Long idParadaDestino = paradaDestino.getId();
				Float latitud = paradaDestino.getLatitud();
				Float longitud = paradaDestino.getLongitud();
				
				MonopatinDto monopatinDto = new MonopatinDto(idMonopatin, latitud, longitud, EstadoMonopatinEnum.DISPONIBLE);
				monopatinDto.setIdParada(idParadaDestino);				
				monopatin = monopatinService.update(idMonopatin, monopatinDto);
				
			} catch (Exception e) {
				throw new ExpectableException("No se pudo actualizar el nuevo estado del monopatin");
			}
			
			viaje.setParadaDestino(monopatin.getParada());				
			viaje.setTiempoFin(Timestamp.valueOf(LocalDateTime.now()));
			
			Optional<Tarifa> tarifaBuscada = tarifaService.findCurrentPrice(); 
			if (tarifaBuscada.isEmpty())
				throw new ExpectableException("No se encontró la tarifa actual");
			Tarifa tarifa = tarifaBuscada.get();		
			
			Float tarifaActual = tarifa.getTarifa();	
			Float tarifaExtra = tarifa.getTarifaExtra();
		
			
			Timestamp tiempoInicio = null;
			tiempoInicio = viaje.getTiempoInicio();
			Timestamp tiempoFin = null;
			tiempoFin = viaje.getTiempoFin();
			Timestamp tiempoPausaInicio = null;
			tiempoPausaInicio = viaje.getTiempoPausaInicio();
			Timestamp tiempoPausaFin = null;
			tiempoPausaFin = viaje.getTiempoPausaFin();
			
		//	try {						
				if (tiempoInicio != null && tiempoFin != null) {											
					Float costoViaje = Float.valueOf("0");
					Long tiempoUsoTotal = tiempoFin.getTime() - tiempoInicio.getTime();
					Float tiempoTotal = tiempoUsoTotal.floatValue() / 3600000;
					monopatin.setTiempoUsoConPausas(tiempoTotal);
					
					if(tiempoPausaInicio != null && tiempoPausaFin != null) {
						Long tiempoPausa = tiempoPausaFin.getTime() - tiempoPausaInicio.getTime();			
						Float tiempoSinPausas = (tiempoTotal - tiempoPausa);						
						float tiempoUsoSinPausas= tiempoSinPausas / 3600000;
						monopatin.setTiempoUsoConPausas(tiempoUsoSinPausas);
												
						Long diferenciaMiliSegundos = viaje.getTiempoPausaFin().getTime() - viaje.getTiempoPausaInicio().getTime();
						int tiempoPermitido = 15 * 60 * 1000;
				
						if (diferenciaMiliSegundos >= tiempoPermitido ) {	
							Long tiempoTarifaNormal = tiempoPausaFin.getTime() - tiempoInicio.getTime();
							Long tiempoTarifaExtra = tiempoFin.getTime() - tiempoPausaFin.getTime();
		
							costoViaje = (tiempoTarifaNormal.floatValue() / 3600000) *  tarifaActual;
							costoViaje =+ (tiempoTarifaExtra.floatValue() / 3600000)* tarifaExtra;
						}
					} else {						
						costoViaje = (tiempoTotal.floatValue()/ 3600000) * tarifaActual;
					}
					
					viaje.setCostoViaje(costoViaje);
					
					CuentaDto cuenta;
					try {						
						cuenta = this.cuentaService.findById(viaje.getIdCuenta());
					} catch(Exception e) {
						throw new ExpectableException("No se encontro la cuenta indicada en el viaje");
					}
				
					if (cuenta != null) {
						try {
							cuentaService.descontarSaldo(viaje.getIdCuenta(), viaje.getCostoViaje());							
						} catch (Exception e) {
							System.out.println(e.getMessage());
							throw new ExpectableException("No se pudo descontar el saldo");
						}

					} else {							
						throw new ExpectableException("No se encontro la cuenta indicada en el viaje");
					}		
					
					double velocidadPromedio = 30.0;
				    double kilometrosRecorridos = velocidadPromedio * tiempoTotal;		     
				    Random random = new Random();
				    double kilometrosRandom = random.nextDouble() * 5.0;
				    kilometrosRecorridos = kilometrosRecorridos + kilometrosRandom;
					viaje.setKilometrosRecorridos((float)kilometrosRecorridos);					
					
					try {
						monopatinRepository.save(monopatin);
					} catch (Exception e) {
						e.getStackTrace();
						throw new ExpectableException("No se pudo actualizar el monopatin");
					}
											
				} else {
					throw new ExpectableException("Error en las fechas de inicio o de fin del viaje");
				}
			//} catch (Exception e) {
			//	e.getStackTrace();
			//	throw new ExpectableException("No se pudieron actualizar los datos de );
			//}
			
			try {
			
				viajeRepository.save(viaje);
			} catch (Exception e) {
				e.getStackTrace();
				throw new ExpectableException("No se pudo actualizar el viaje2");
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

		private Object obtenerFacturacion(Timestamp desde, Timestamp hasta) throws Exception {
			try {
				return viajeRepository.obtenerFacturacion(desde, hasta);
			} catch (Exception e) {
				 e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}

		public Object obtenerFacturacionAnual(Integer anio, Integer mesDesde, Integer mesHasta) throws Exception {

			LocalDateTime dDesde = LocalDate.of(anio, mesDesde, 1)
					.atStartOfDay();
	        Timestamp desde = Timestamp.valueOf(dDesde);
	        
			LocalDateTime dHasta = LocalDate.of(anio, mesHasta, 1)
					.plusMonths(1).atStartOfDay();
	        Timestamp hasta = Timestamp.valueOf(dHasta);

	        return this.obtenerFacturacion(desde, hasta);
		}
	
}