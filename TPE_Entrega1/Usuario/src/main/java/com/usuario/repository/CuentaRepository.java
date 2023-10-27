package com.usuario.repository;

import java.util.List;

import com.usuario.model.Cuenta;

public interface CuentaRepository extends RepoBase<Cuenta, Long> {
	static List<String> sorteableFields = List.of("id_cuenta", "fecha_alta", "id_mercado_pago", "saldo", "usuarios");
}
