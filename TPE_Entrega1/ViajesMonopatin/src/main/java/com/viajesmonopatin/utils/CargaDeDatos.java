package com.viajesmonopatin.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.viajesmonopatin.enums.EstadoMonopatinEnum;
import com.viajesmonopatin.model.Monopatin;
import com.viajesmonopatin.model.Tarifa;
import com.viajesmonopatin.model.Viaje;
import com.viajesmonopatin.repository.MonopatinRepository;
import com.viajesmonopatin.repository.TarifaRepository;
import com.viajesmonopatin.repository.ViajeRepository;

@Component
public class CargaDeDatos {
	private final TarifaRepository tarifaRepository;
	private final MonopatinRepository monopatinRepository;
	private final ViajeRepository viajeRepository;

	
	public CargaDeDatos(TarifaRepository tarifaRapository, MonopatinRepository monopatinRepository, ViajeRepository viajeRepository ) {
		this.tarifaRepository = tarifaRapository;
		this.monopatinRepository = monopatinRepository;
		this.viajeRepository = viajeRepository;
	}
	
	public void cargarTarifasDesdeCSV() throws IOException{
		File archivoCSV = ResourceUtils.getFile("src/main/java/com/viajesmonopatin/csv/tarifas.csv");
		
		try (FileReader reader = new FileReader(archivoCSV);
			 CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
			
			for(CSVRecord csvRecord : csvParser) {
				Tarifa t = new Tarifa();
				t.setId(Integer.parseInt(csvRecord.get("id")));
				t.setTarifa(Float.parseFloat(csvRecord.get("tarifa")));
				t.setTarifaExtra(Float.parseFloat(csvRecord.get("tarifaExtra")));
				t.setValidoDesde(Timestamp.valueOf(csvRecord.get("validoDesde")));
				tarifaRepository.save(t);
			}
		}
	}
	
	public void cargarMonopatinesDesdeCSV() throws IOException {
		  File archivoCSV = ResourceUtils.getFile("src/main/java/com/viajesmonopatin/csv/monopatines.csv");

	        try (FileReader reader = new FileReader(archivoCSV);
	             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

	        	for (CSVRecord csvRecord : csvParser) {
	        		Monopatin m = new Monopatin();
	        		m.setId(Integer.parseInt(csvRecord.get("id")));
	        		m.setLatitud(Float.parseFloat(csvRecord.get("latitud")));
	        		m.setLongitud(Float.parseFloat(csvRecord.get("longitud")));
	        		String estado = String.valueOf(csvRecord.get("estado"));
	        		if(estado.equalsIgnoreCase("DISPONIBLE")) {
	        			m.setEstado(EstadoMonopatinEnum.DISPONIBLE);
	        		} else if(estado.equalsIgnoreCase("EN_USO")) {
	        			m.setEstado(EstadoMonopatinEnum.EN_USO);
	        		} else if(estado.equalsIgnoreCase("EN_MANTENIMIENTO")) {
	        			m.setEstado(EstadoMonopatinEnum.EN_MANTENIMIENTO);
	        		}	        		
	        		m.setKilometrosRecorridos(Float.parseFloat(csvRecord.get("kilometros_recorridos")));
	        		m.setTiempoUsoConPausas(Float.parseFloat(csvRecord.get("tiempo_uso_con_pausas")));
	        		m.setTiempoUsoSinPausas(Float.parseFloat(csvRecord.get("tiempo_uso_sin_pausas")));
	        		monopatinRepository.save(m);
	            }
	        }
	}
	
	public void cargarViajesDesdeCSV() throws IOException {
		  File archivoCSV = ResourceUtils.getFile("src/main/java/com/viajesmonopatin/csv/viajes.csv");

	        try (FileReader reader = new FileReader(archivoCSV);
	             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

	            for (CSVRecord csvRecord : csvParser) {
	                Viaje v = new Viaje();	                
	                v.setId(Long.parseLong(csvRecord.get("id")));
	                //monopatin
	                //idUsuario
	                //idCuenta
	                //Parada inicio
	                //Parada destino
	                v.setTiempoInicio(Timestamp.valueOf(csvRecord.get("tiempo_inicio")));     
	                v.setTiempoFin(Timestamp.valueOf(csvRecord.get("tiempo_fin")));   
	                v.setTiempoPausaInicio(Timestamp.valueOf(csvRecord.get("tiempo_pausa_inicio")));     
	                v.setTiempoPausaFin(Timestamp.valueOf(csvRecord.get("tiempo_pausa_fin")));  
	                v.setKilometrosRecorridos(Float.parseFloat(csvRecord.get("kilometros_recorridos")));
	                viajeRepository.save(v);
	            }
	        }
	}
	
}