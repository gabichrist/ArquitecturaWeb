package com.viajesmonopatin.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.viajesmonopatin.model.Tarifa;
import com.viajesmonopatin.repository.TarifaRepository;

@Component
public class CargaDeDatos {
	private final TarifaRepository tarifaRepository;
	
	public CargaDeDatos(TarifaRepository tarifaRapository) {
		this.tarifaRepository = tarifaRapository;
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
}
