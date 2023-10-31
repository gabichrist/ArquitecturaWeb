package com.usuario.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.exception.ExpectableException;
import com.usuario.model.Cuenta;
import com.usuario.repository.CuentaRepository;

@Service("cuentaServicio")
public class CuentaService implements BaseService<Cuenta> {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public List<Cuenta> findAll() throws Exception {
		return cuentaRepository.findAll();
	}

	@Override
	public Cuenta findById(Long id) throws Exception {
		try {
			Optional<Cuenta> cuentaBuscada = cuentaRepository.findById(id);
			return cuentaBuscada.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Cuenta save(Cuenta entity) throws Exception {
		try {
			if (entity.getFecha_alta() == null) {
				Date now = new Date();
				entity.setFecha_alta(new Timestamp(now.getTime()));
			}
			if (entity.getHabilitada() == null) {
				entity.setHabilitada(true);
			}
			return cuentaRepository.save(entity);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Cuenta update(Long id, Cuenta entity) throws Exception {
		if (cuentaRepository.existsById(id)) {
			try {
				Cuenta cuenta = cuentaRepository.findById(id).get();
				if (entity.getFecha_alta() != null) {
					cuenta.setFecha_alta(entity.getFecha_alta());
				}
				if (entity.getHabilitada() != null) {
					cuenta.setHabilitada(entity.getHabilitada());
				}
				cuenta.setId_mercado_pago(entity.getId_mercado_pago());
				cuenta.setSaldo(entity.getSaldo());
				return cuentaRepository.save(cuenta);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		} else {
			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
		}
	}

	@Override
	public boolean delete(Long id) throws Exception {
		if (cuentaRepository.existsById(id)) {
			try {
				cuentaRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		} else {
			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
		}
	}

	public Cuenta cargarSaldo(Long id, Float importe) throws Exception {
		if (importe == null || importe <= 0) {
			throw new ExpectableException("{\"error\":\"Error. Importe debe ser un valor mayor a 0.\"}");
		}
		Optional<Cuenta> optCuenta = cuentaRepository.findById(id);
		if (optCuenta.isEmpty()) {
			throw new ExpectableException("{\"error\":\"Error. No existe la cuenta.\"}");
		}
		Cuenta cuenta = optCuenta.get();
		if (!cuenta.getHabilitada()) {
			throw new ExpectableException("{\"error\":\"Error. Cuenta deshabilitada.\"}");
		}
		try {
			cobrarConMercadopago(importe, cuenta.getId_mercado_pago());
			cuenta.setSaldo(cuenta.getSaldo() + importe);
			return cuentaRepository.save(cuenta);
		} catch (Error e) {
			throw new ExpectableException(
					"{\"error\":\"Error. No se pudo realizar el pago por MercadoPago, reintente nuevamente.\"}");
		}
	}

	public Cuenta descontarSaldo(Long id, Float importe) throws Exception {
		if (importe == null || importe <= 0) {
			throw new ExpectableException("{\"error\":\"Error. Importe debe ser un valor mayor a 0.\"}");
		}
		Optional<Cuenta> optCuenta = cuentaRepository.findById(id);
		if (optCuenta.isEmpty()) {
			throw new ExpectableException("{\"error\":\"Error. No existe la cuenta.\"}");
		}
		Cuenta cuenta = optCuenta.get();
		cuenta.setSaldo(cuenta.getSaldo() - importe);
		return cuentaRepository.save(cuenta);
	}

	/**
	 * cobrarConMercadopago Realiza cobro sincrónico a través de MercadoPago. Lanza
	 * excepción en caso de no poder concretarlo.
	 * 
	 * @param importe
	 * @param idMercadoPago
	 * @throws Exception
	 */
	private void cobrarConMercadopago(float importe, int idMercadoPago) throws Exception {
		// LÓGICA DE COBRO SINCRÓNICO A MERCADOPAGO QUE EXCEDE AL TRABAJO
	}

	public Cuenta anularCuenta(Long id_cuenta) throws ExpectableException {
		Optional<Cuenta> c = cuentaRepository.findById(id_cuenta);
		if (c.isEmpty()) {
			throw new ExpectableException("{\"error\":\"Error. No existe la cuenta.\"}");
		}
		c.get().setHabilitada(false);
		return cuentaRepository.save(c.get());
	}
}
