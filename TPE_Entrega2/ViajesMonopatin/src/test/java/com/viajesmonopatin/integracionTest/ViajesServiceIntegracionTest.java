package com.viajesmonopatin.integracionTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.viajesmonopatin.dto.CuentaDto;
import com.viajesmonopatin.dto.ViajeMonopatinUsuarioDto;
import com.viajesmonopatin.enums.EstadoMonopatinEnum;
import com.viajesmonopatin.enums.EstadoViajeEnum;
import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Monopatin;
import com.viajesmonopatin.model.Parada;
import com.viajesmonopatin.model.Viaje;
import com.viajesmonopatin.repository.MonopatinRepository;
import com.viajesmonopatin.repository.ParadaRepository;
import com.viajesmonopatin.service.CuentaService;

import com.viajesmonopatin.service.ViajeService;

import jakarta.transaction.Transactional;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Transactional
public class ViajesServiceIntegracionTest {
	
	@Autowired
	private ViajeService viajeService;
	
	@Autowired
	private MonopatinRepository monopatinRepository;
	
	@Autowired
	private ParadaRepository paradaRepository;
	
	@Autowired
	private CuentaService cuentaService;
	
	private Monopatin monopatin;
    static Parada paradaInicio;
    static Parada paradaDestino;
    static CuentaDto cuenta;

    
	@Test
	@DisplayName("Intenta iniciar un viaje")
	public void testIniciarViaje() throws Exception {
		try {
			monopatin = new Monopatin();
		    monopatin.setId(22222L);
		    monopatin.setEstado(EstadoMonopatinEnum.DISPONIBLE);
		    monopatin.setKilometrosRecorridos(0.0F);
		    monopatin.setTiempoUsoConPausas(0.0F);
		    monopatin.setTiempoUsoSinPausas(0.0F);
		
		    paradaInicio = new Parada();
		    paradaInicio.setLatitud(1000.2F);
		    paradaInicio.setLongitud(-122.54F);   
		    paradaInicio.setDireccion("Inicio test");
		    paradaRepository.save(paradaInicio);
		    
		    monopatin.setParada(paradaInicio);
		    monopatin.setLatitud(paradaInicio.getLatitud());
		    monopatin.setLongitud(paradaInicio.getLongitud());
		    
		
		    paradaDestino = new Parada();
		    paradaDestino.setLatitud(6667.33F);
		    paradaDestino.setLongitud(123.56F);
		    paradaDestino.setDireccion("Destino test");
		    paradaRepository.save(paradaDestino);
		    
		    try {
				monopatinRepository.save(monopatin);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			} 
					
	        cuenta = cuentaService.findById(1L);

			ViajeMonopatinUsuarioDto viajeDto = new ViajeMonopatinUsuarioDto();
	        viajeDto.setIdMonopatin(monopatin.getId());
	        viajeDto.setIdParadaDestino(paradaDestino.getId());
	        viajeDto.setIdCuenta(cuenta.getId_cuenta());
	        viajeDto.setIdUsuario(1L);

	        Viaje viaje = viajeService.iniciarViaje(viajeDto);

	        assertThrows(ExpectableException.class, () -> viajeService.iniciarViaje(viajeDto));	 
	        
	        assertEquals(EstadoViajeEnum.ACTIVO, viaje.getEstado());
	        assertEquals(monopatin.getParada(), viaje.getParadaInicio());
	        assertEquals(paradaDestino, viaje.getParadaDestino());
	        assertEquals(monopatin.getId(), viaje.getMonopatin().getId());
	        assertEquals(cuenta.getId_cuenta(), viaje.getIdCuenta());
	        assertEquals(viajeDto.getIdUsuario(), viaje.getIdUsuario());       
	        
	        Optional<Monopatin> m = monopatinRepository.findById(monopatin.getId());
	        if (m.isEmpty()) {
				throw new ExpectableException("{\"error\":\"Error. No se pudo encontrar el monopatin\"}");
			}
	        Monopatin monopatinEnViaje = m.get();

			assertEquals(EstadoMonopatinEnum.EN_USO, monopatinEnViaje.getEstado());
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	
	
}
