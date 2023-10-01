package com.example.entregable3.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.example.entregable3.model.Carrera;
import com.example.entregable3.model.Estudiante;
import com.example.entregable3.repository.CarreraRepository;
import com.example.entregable3.repository.EstudianteRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CargaDeDatos {

    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;

    @Autowired
    public CargaDeDatos(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository) {
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
    }

    public void cargarEstudiantesDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/example/entregable3/csv/estudiantes.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Estudiante e = new Estudiante();
                e.setLu(Integer.parseInt(csvRecord.get("lu")));
                e.setDni(csvRecord.get("dni"));
                e.setNombres(csvRecord.get("nombres"));
                e.setApellido(csvRecord.get("apellido"));
                e.setEdad(Integer.parseInt(csvRecord.get("edad")));
                e.setGenero(csvRecord.get("genero"));
                e.setCiudad(csvRecord.get("ciudad"));
                estudianteRepository.save(e);
            }
        }
    }

    public void cargarCarrerasDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/example/entregable3/csv/carreras.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Carrera carrera = new Carrera(
                        Integer.parseInt(csvRecord.get("id_carrera")),
                        csvRecord.get("nombre")
                		);
                carreraRepository.save(carrera);
            }
        }
    }

}

