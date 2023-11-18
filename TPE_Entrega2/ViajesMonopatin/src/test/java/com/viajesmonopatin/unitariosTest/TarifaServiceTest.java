package com.viajesmonopatin.unitariosTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.viajesmonopatin.model.Parada;
import com.viajesmonopatin.model.Tarifa;
import com.viajesmonopatin.service.TarifaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TarifaServiceTest {

	@Autowired
	private TarifaService tarifaService;
	
	@Test
	public void testFindByIdExistente() throws Exception {
	    Long id = 1L;
	    Tarifa tarifa = tarifaService.findById(id);
	    assertNotNull(tarifa);
	}
	
	@Test
	public void testCurrentPrice() throws Exception {
	    Float tarifaEsperada = (float) 100;
	    Optional<Tarifa> tarifaActual = tarifaService.findCurrentPrice();
	    assertEquals(tarifaEsperada, tarifaActual);
	}
}
