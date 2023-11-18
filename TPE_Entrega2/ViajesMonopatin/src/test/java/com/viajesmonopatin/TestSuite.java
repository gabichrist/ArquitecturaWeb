package com.viajesmonopatin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.viajesmonopatin.integracionTest.ViajesServiceIntegracionTest;
import com.viajesmonopatin.unitariosTest.MonopatinServiceTest;
import com.viajesmonopatin.unitariosTest.ViajesServiceTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	MonopatinServiceTest.class,
	ViajesServiceTest.class,
	ViajesServiceIntegracionTest.class
})

public class TestSuite {
	// TestSuite
}
