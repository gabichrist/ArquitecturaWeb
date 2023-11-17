package com.usuario;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.usuario.unitariosTest.CuentaServiceTest;
import com.usuario.unitariosTest.UsuarioServiceTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	UsuarioServiceTest.class,
	CuentaServiceTest.class
})

public class TestSuite {
	// TestSuite
}
