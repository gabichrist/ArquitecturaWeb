package com.usuario.dtos;


import enums.Roles;

public class UsuarioDTO {

	private int id_usuario;

	private String nombre;

	private String apellido;

	private String nro_celular;

	private String email;

	private Roles roles;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(int id_usuario, String nombre, String apellido, String nro_celular, String email, Roles roles) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nro_celular = nro_celular;
		this.email = email;
		this.roles = roles;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
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

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	
}
