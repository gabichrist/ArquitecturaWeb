package com.usuario.model;

import java.sql.Timestamp;
import java.util.HashSet;
//import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
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

	@Column(nullable = false)
	private Boolean habilitada;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "usuarios_cuenta",
			joinColumns = @JoinColumn(name = "id_cuenta"),
			inverseJoinColumns = @JoinColumn(name = "id_usuario")
	)
	private Set<Usuario> usuarios;

//	@JsonProperty("idUsuarios")
//	public List<Long> idUsuarios() {
//		return this.usuarios.stream().map(usuario -> usuario.getId()).toList();
//	}
	
	@Override
	public String toString() {
		return "Cuenta [id_cuenta=" + id_cuenta + ", fecha_alta=" + fecha_alta + ", id_mercado_pago=" + id_mercado_pago
				+ ", saldo=" + saldo + ", usuarios=" + usuarios + "]";
	}

	public Cuenta() {
		super();
		this.usuarios = new HashSet<>();
	}
	
	public Boolean getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(Boolean habilitada) {
		this.habilitada = habilitada;
	}

	public Cuenta(Long id_cuenta, Timestamp fecha_alta, int id_mercado_pago, float saldo, Boolean habilitada,
			Set<Usuario> usuarios) {
		super();
		this.id_cuenta = id_cuenta;
		this.fecha_alta = fecha_alta;
		this.id_mercado_pago = id_mercado_pago;
		this.saldo = saldo;
		this.habilitada = habilitada;
		this.usuarios =  new HashSet<>(usuarios);
	}

	public Cuenta(Long id_cuenta, Timestamp fecha_alta, int id_mercado_pago, float saldo, Set<Usuario> usuarios) {
		super();
		this.id_cuenta = id_cuenta;
		this.fecha_alta = fecha_alta;
		this.id_mercado_pago = id_mercado_pago;
		this.saldo = saldo;
		this.usuarios = usuarios;
		this.habilitada = true;
	}
	
	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

}
