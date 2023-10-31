package com.administrador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrador.dto.CuentaDto;
import com.administrador.dto.DisponibilidadMonopatinesDto;
import com.administrador.dto.MonopatinDto;
import com.administrador.dto.TarifaDto;

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
	
}
