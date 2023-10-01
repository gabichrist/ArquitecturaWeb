package com.example.entregable3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entregable3.model.Estudiante;

@Repository("EstudianteRepository")
public interface EstudianteRepository extends RepoBase<Estudiante, Long>{
    static List<String> sorteableFields = List.of("lu", "nombres", "apellido", "edad", "genero", "dni", "ciudad");

//	EJEMPLO DESDE PerroRepositorio
//@Query("SELECT p FROM Perro p WHERE p.habilidad = :habilidad ORDER BY p.edad ASC")
//public List<Perro> getPerrosPorHabilidadOrderByEdadAsc(String habilidad);
//    List<Perro> findByHabilidad(String habilidad);

}
