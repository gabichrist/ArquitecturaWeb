package com.usuario.model;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enums.Roles;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Column(nullable = false)
	private String nro_celular;

	@Column(nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Roles rol;

	@ManyToMany(mappedBy = "usuarios", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Cuenta> cuentas;

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nombre, String apellido, String nro_celular, String email, Roles roles) {
		super();
		this.id_usuario = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nro_celular = nro_celular;
		this.email = email;
		this.rol = roles;
	}

	public Long getId() {
		return id_usuario;
	}

	public void setId(Long id) {
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
		if (!this.isValidEmail(email))
			throw new Error("Email no válido");
		this.email = email;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", nro_celular="
				+ nro_celular + ", email=" + email + ", rol=" + rol + "]";
	}
	
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
