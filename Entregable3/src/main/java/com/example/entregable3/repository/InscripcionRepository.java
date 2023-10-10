package com.example.entregable3.repository;

//import java.util.Optional;

//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.entregable3.model.Inscripcion;
import com.example.entregable3.model.InscripcionId;


@Repository("InscripcionRepository")
public interface InscripcionRepository extends RepoBase<Inscripcion, InscripcionId>{
	
}
