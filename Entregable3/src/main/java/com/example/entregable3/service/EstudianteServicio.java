package com.example.entregable3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entregable3.model.Estudiante;
import com.example.entregable3.repository.EstudianteRepository;

import jakarta.transaction.Transactional;

@Service
public class EstudianteServicio {

	@Autowired
	private EstudianteRepository estudianteRepository;

	@Transactional
	public Estudiante save(Estudiante entity) throws Exception {
		try {
			System.out.println("entit" + entity);
			return estudianteRepository.save(entity);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
