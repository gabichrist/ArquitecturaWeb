package com.mantenimiento.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


import com.mantenimiento.model.Mantenimiento;
import com.mantenimiento.repository.MantenimientoRepository;

@Component
public class CargaDeDatos {

	private final MantenimientoRepository mantenimientoRepository;
	
	@Autowired
	public CargaDeDatos(MantenimientoRepository mantenimientoRepository) {
		this.mantenimientoRepository = mantenimientoRepository;		
	}
	
	public void cargarMantenimientosDesdeCSV() throws FileNotFoundException, IOException {
		  File archivoCSV = ResourceUtils.getFile("src/main/java/com/mantenimiento/csv/mantenimientos.csv");

	        try (FileReader reader = new FileReader(archivoCSV);
	             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

	            for (CSVRecord csvRecord : csvParser) {
	                Mantenimiento m = new Mantenimiento();
	                m.setId(Long.parseLong(csvRecord.get("id")));
	                m.setIdMonopatin(Long.parseLong(csvRecord.get("id_monopatin")));
	                m.setInicio(Timestamp.valueOf(csvRecord.get("inicio")));
	                m.setFin(Timestamp.valueOf(csvRecord.get("fin")));     
	                m.setDescripcion(csvRecord.get("descripcion"));
	                mantenimientoRepository.save(m);
	            }
	        }
	}
}
