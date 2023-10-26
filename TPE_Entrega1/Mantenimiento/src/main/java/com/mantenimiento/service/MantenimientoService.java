package com.mantenimiento.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.mantenimiento.model.Mantenimiento;
import com.mantenimiento.repository.MantenimientoRepository;


public class MantenimientoService implements BaseService<Mantenimiento>{

		@Autowired
		private MantenimientoRepository mantenimientoRepository;

		@Override
		public List<Mantenimiento> findAll() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Mantenimiento findById(Long id) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Mantenimiento save(Mantenimiento entity) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Mantenimiento update(Long id, Mantenimiento entity) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean delete(Long id) throws Exception {
			// TODO Auto-generated method stub
			return false;
		}

	

}
