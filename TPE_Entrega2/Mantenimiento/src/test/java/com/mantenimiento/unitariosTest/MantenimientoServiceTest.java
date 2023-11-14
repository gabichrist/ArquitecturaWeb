package com.mantenimiento.unitariosTest;

import static org.junit.Assert.assertNotNull;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mantenimiento.model.Mantenimiento;
import com.mantenimiento.service.MantenimientoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MantenimientoServiceTest {

	@Autowired
	private MantenimientoService mantenimientoService;
	
	@Test
	public void testFindByIdExistente() throws Exception {
		ObjectId objectId = new ObjectId("65514aaa0b5b817d2169cc6b");
		Mantenimiento mantenimiento = mantenimientoService.findById(objectId);
	    assertNotNull(mantenimiento);
	}

	@Test(expected = Exception.class)
	@DisplayName("Intenta buscar un mantenimiento que no existe")
	public void testFindByIdInexistente() throws Exception {		
        ObjectId objectId = new ObjectId("65514aaaaaaaa");
		 mantenimientoService.findById(objectId);
	}
	
	@Test(expected = Exception.class)
	@DisplayName("Intenta modificar el registro de un mantenimiento que no existe")
	public void testUpdateMantenimientoInexistente() throws Exception {		
		ObjectId objectId = new ObjectId("65514noexiste00");
		 Mantenimiento mantenimiento = new Mantenimiento();
		 mantenimiento.setId(objectId);
		 mantenimientoService.update(objectId, mantenimiento);
	}

	@Test(expected = Exception.class)
	@DisplayName("Intenta eliminar el registro de un mantenimiento que no existe")
	public void testDeleteMantenimientoInexistente() throws Exception {		
		ObjectId objectId = new ObjectId("65514noexiste00");
		mantenimientoService.delete(objectId);
	}
}
