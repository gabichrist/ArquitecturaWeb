package com.usuario.model;

import java.sql.Timestamp;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cuenta;

	@Column(nullable = false)
	private Timestamp fecha_alta;

	@Column(nullable = false)
	private int id_mercado_pago;

	@Column(nullable = false)
	private float saldo;

	@ManyToMany
	private Set<Usuario> usuarios;

	@Override
	public String toString() {
		return "Cuenta [id_cuenta=" + id_cuenta + ", fecha_alta=" + fecha_alta + ", id_mercado_pago=" + id_mercado_pago
				+ ", saldo=" + saldo + ", usuarios=" + usuarios + "]";
	}

	public Cuenta() {
		super();
	}

	public Cuenta(Long id_cuenta, Timestamp fecha_alta, int id_mercado_pago, float saldo, Set<Usuario> usuarios) {
		super();
		this.id_cuenta = id_cuenta;
		this.fecha_alta = fecha_alta;
		this.id_mercado_pago = id_mercado_pago;
		this.saldo = saldo;
		this.usuarios = usuarios;
	}

	public Long getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(Long id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	public Timestamp getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public int getId_mercado_pago() {
		return id_mercado_pago;
	}

	public void setId_mercado_pago(int id_mercado_pago) {
		this.id_mercado_pago = id_mercado_pago;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
