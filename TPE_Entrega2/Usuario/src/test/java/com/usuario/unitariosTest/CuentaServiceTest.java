package com.usuario.unitariosTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.usuario.model.Cuenta;
import com.usuario.service.CuentaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuentaServiceTest {
		
	@Autowired 
	private CuentaService cuentaService;
	
	@Test
	public void textFindByIdExistente() throws Exception {
		Long id = 1L;
		Cuenta cuenta = cuentaService.findById(id);
		assertNotNull(cuenta);
	}
	
	@Test(expected = Exception.class)
	@DisplayName("Intenta buscar una cuenta que no existe")
	public void textFindByIdInexistente() throws Exception {
		Long id = 22222222L;
		cuentaService.findById(id);
	}
	
	@Test(expected = Exception.class)
	@DisplayName("Intenta modificar una cuenta que no existe")
	public void testUpdateMonopatinInexistente() throws Exception {		
		 Long id = 22222222L;
		 Cuenta cuenta = new Cuenta();
		 cuenta.setId_cuenta(id);
		 cuentaService.update(id, cuenta);
	}

}
