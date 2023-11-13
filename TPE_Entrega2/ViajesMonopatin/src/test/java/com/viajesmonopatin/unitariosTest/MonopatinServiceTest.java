package com.viajesmonopatin.unitariosTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.viajesmonopatin.dto.MonopatinDto;
import com.viajesmonopatin.enums.EstadoMonopatinEnum;
import com.viajesmonopatin.model.Monopatin;
import com.viajesmonopatin.service.MonopatinService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MonopatinServiceTest {
	
	@Autowired
	private MonopatinService monopatinService;
	
	@Test
	public void testFindByIdExistente() throws Exception {
	    Long id = 1L;
	    Monopatin monopatin = monopatinService.findById(id);
	    assertNotNull(monopatin);
	}
	
	@Test(expected = Exception.class)
	@DisplayName("Intenta buscar un monopatin que no existe")
	public void testFindByIdInexistente() throws Exception {		
		 Long id = 1000L;
		 monopatinService.findById(id);
	}
	
	
	@Test(expected = Exception.class)
	@DisplayName("Intenta modificar un monopatin que no existe")
	public void testUpdateMonopatinNoExistente() throws Exception {		
		 Long id = 1000L;
		 MonopatinDto monopatinDto = new MonopatinDto();
		 monopatinDto.setId(id);
		 monopatinService.update(id, monopatinDto);
	}
	
	@Test
	@DisplayName("Intenta registrar un monopatin en mantenimiento y que cambie el estado")
	public void testRegistrarMonopatinEnMantenimiento() throws Exception {   
		Monopatin monopatin = monopatinService.findById(Long.parseLong("1"));
	    Monopatin monopatinEnMantenimiento = monopatinService.registrarMonopatinEnMantenimiento(monopatin.getId());	 
	    assertNotNull(monopatinEnMantenimiento);
	    assertEquals(EstadoMonopatinEnum.EN_MANTENIMIENTO, monopatinEnMantenimiento.getEstado());
	}
	
	@Test
	@DisplayName("Intenta finalizar el mantenimiento de un monopatin y que cambie el estado")
	public void testRegistrarFinMantenimientoMonopatin() throws Exception {
	    Long id = 1L;
	    Monopatin monopatin = monopatinService.registrarFinMantenimientoMonopatin(id);
	    assertNotNull(monopatin);
	    assertEquals(EstadoMonopatinEnum.DISPONIBLE, monopatin.getEstado());
	}
	
	@Test
	public void testGetMonopatinesOperativos() throws Exception {
	    List<Monopatin> monopatinesOperativos = monopatinService.getMonopatinesOperativos();
	    assertNotNull(monopatinesOperativos);
	    for ( Monopatin monopatin : monopatinesOperativos) {
			assertNotEquals(EstadoMonopatinEnum.EN_MANTENIMIENTO, monopatin.getEstado());
		}
	}
	
}