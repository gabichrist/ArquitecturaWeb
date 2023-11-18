package com.viajesmonopatin.unitariosTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.viajesmonopatin.model.Parada;
import com.viajesmonopatin.model.Viaje;
import com.viajesmonopatin.service.ParadaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParadaServiceTest {

	@Autowired
	private ParadaService paradaService;
	
	@Test
	public void testFindByIdExistente() throws Exception {
	    Long id = (long) 1;
	    Parada parada = paradaService.findById(id);
	    assertNotNull(parada);
	}
	
	@Test
	public void testGetByLaitudLongitud() throws Exception {
	    Float latitud = (float) -11.8515;
	    Float longitud = (float) -0.1552;
	    Parada parada = paradaService.getByLatitudLongitud(latitud, longitud);
	    assertNotNull(parada);
	}
}
