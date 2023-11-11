package com.administrador.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrador.dto.CuentaDto;
import com.administrador.dto.DisponibilidadMonopatinesDto;
import com.administrador.dto.FacturacionAnualDto;
import com.administrador.dto.MonopatinDto;
import com.administrador.dto.ParadaDto;
import com.administrador.dto.TarifaDto;
import com.administrador.dto.ViajeMonopatinDto;
import com.administrador.exception.ExpectableException;

@Service
public class AdministradorService {

	@Autowired 
	private CuentaService cuentaService;
	
	@Autowired
	private ViajesMonopatinService viajesMonopatinService;
	
	public CuentaDto anularCuentaUsuario(Long id) {
		return this.cuentaService.anularCuentaUsuario(id);
	}
	
	public DisponibilidadMonopatinesDto obtenerDisponibilidadMonopatines() {
		try {
			List<MonopatinDto> monopatinesOperativos = viajesMonopatinService.obtenerMonopatinesOperativos();
			List<MonopatinDto> monopatinesMantenimiento =  viajesMonopatinService.obtenerMonopatinesEnMantenimiento();
			DisponibilidadMonopatinesDto reporteDisponibilidad = new DisponibilidadMonopatinesDto();
			
			reporteDisponibilidad.setMonopatinesOperativos(monopatinesOperativos.size());
			reporteDisponibilidad.setMonopatinesEnMantenimiento(monopatinesMantenimiento.size());
			
			return reporteDisponibilidad;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public TarifaDto ajustarPrecios(TarifaDto tarifa) {
		try {
			return this.viajesMonopatinService.crearTarifa(tarifa);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ViajeMonopatinDto> obtenerReportePorCantidadMinimaDeViajesAnual(int anio, Long cantidad) {
		try {
			return this.viajesMonopatinService.obtenerPorCantidadMinimaDeViajesAnual(anio, cantidad);
		} catch (Exception e) {
			 e.printStackTrace();
			throw e;
		}
	}

	public FacturacionAnualDto obtenerFacturacion(int anio, int mesDesde, int mesHasta) {
		try {
			FacturacionAnualDto dto = new FacturacionAnualDto();
			dto.setAnio(anio);
			dto.setDesdeMes(mesDesde);
			dto.setHastaMes(mesHasta);
			dto.setTotalFacturado(this.viajesMonopatinService.obtenerFacturacion(anio, mesDesde, mesHasta));

			return dto;
		} catch (Exception e) {
			 e.printStackTrace();
			throw e;
		}
	}
	
	public ParadaDto agregarParada(ParadaDto parada) {
		try{
			return this.viajesMonopatinService.agregarParada(parada);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public ParadaDto actualizarParada(Long id, ParadaDto parada) {
		try{
			return this.viajesMonopatinService.actualizarParada(id, parada);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public boolean quitarParada(Long id) {
		return this.viajesMonopatinService.quitarParada(id);
	}
	
	public MonopatinDto agregarMonopatin(MonopatinDto monopatin) {
		try{
			return this.viajesMonopatinService.agregarMonopatin(monopatin);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public MonopatinDto actualizarMonopatin(Long id, MonopatinDto monopatin) throws ExpectableException {
		try{
			return this.viajesMonopatinService.actualizarMonopatin(id, monopatin);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public boolean quitarMonopatin(Long id) {
		return this.viajesMonopatinService.quitarMonopatin(id);
	}
}
