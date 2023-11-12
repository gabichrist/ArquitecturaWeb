package com.mantenimiento.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.bson.types.ObjectId;
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

    public void cargarMantenimientosDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/mantenimiento/csv/mantenimientos.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Mantenimiento m = new Mantenimiento();
                m.setId(new ObjectId(csvRecord.get("id")));
                m.setIdMonopatin(Long.parseLong(csvRecord.get("id_monopatin")));
                m.setInicio(Instant.parse(csvRecord.get("inicio")));
                m.setFin(Instant.parse(csvRecord.get("fin")));
                m.setDescripcion(csvRecord.get("descripcion"));
                mantenimientoRepository.save(m);
            }
        }
    }
}
