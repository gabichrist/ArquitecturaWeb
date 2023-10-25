package com.usuario.model;

import java.util.Set;

import org.hibernate.mapping.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_usuario;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Column(nullable = false)
	private String nro_celular;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private List roles;

	@ManyToMany(mappedBy = "usuarios")
	private Set<Cuenta> cuentas;

	public Usuario() {
		super();
	}

	public Usuario(int id, String nombre, String apellido, String nro_celular, String email, List roles) {
		super();
		this.id_usuario = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nro_celular = nro_celular;
		this.email = email;
		this.roles = roles;
	}

	public int getId() {
		return id_usuario;
	}

	public void setId(int id) {
		this.id_usuario = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNro_celular() {
		return nro_celular;
	}

	public void setNro_celular(String nro_celular) {
		this.nro_celular = nro_celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List getRoles() {
		return roles;
	}

	public void setRoles(List roles) {
		this.roles = roles;
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", nro_celular=" + nro_celular
				+ ", email=" + email + ", roles=" + roles + "]";
	}

}
