package com.administrador.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrador.dto.CuentaDto;
import com.administrador.dto.DisponibilidadMonopatinesDto;
import com.administrador.dto.FacturacionAnualDto;
import com.administrador.dto.MonopatinDto;
import com.administrador.dto.TarifaDto;
import com.administrador.dto.ViajeMonopatinDto;

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
	
}
