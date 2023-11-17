package com.usuario.unitariosTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.usuario.model.Usuario;
import com.usuario.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {
	
	@Autowired 
	private UsuarioService usuarioService;
	
	@Test
	public void textFindByIdExistente() throws Exception {
		Long id = 1L;
		Usuario usuario = usuarioService.findById(id);
		assertNotNull(usuario);
	}
	
	@Test(expected = Exception.class)
	@DisplayName("Intenta buscar un usuario que no existe")
	public void textFindByIdInexistente() throws Exception {
		Long id = 22222222L;
		usuarioService.findById(id);
	}
	
	@Test(expected = Exception.class)
	@DisplayName("Intenta modificar un usuario que no existe")
	public void testUpdateMonopatinInexistente() throws Exception {		
		 Long id = 22222222L;
		 Usuario usuario = new Usuario();
		 usuario.setId(id);
		 usuarioService.update(id, usuario);
	}
}
