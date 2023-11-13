package com.viajesmonopatin.unitariosTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.viajesmonopatin.model.Viaje;
import com.viajesmonopatin.service.ViajeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViajesServiceTest {
	
	@Autowired
	private ViajeService viajeService;
	
	@Test
	public void testFindByIdExistente() throws Exception {
	    Long id = 1L;
	    Viaje viaje = viajeService.findById(id);
	    assertNotNull(viaje);
	}
	
	@Test(expected = Exception.class)
	@DisplayName("Intenta buscar un viaje que no existe")
	public void findByIdInexistente() throws Exception {		
		 Long id = 1000L;
		 viajeService.findById(id);
	}
	

}
