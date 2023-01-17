package com.educacionit.entidades;

import java.util.concurrent.atomic.AtomicLong;

public class Usuario {

	private Long id;
	private String correo;
	private String clave;
	private static AtomicLong contador = new AtomicLong(1);

	public Usuario() {
		id = contador.getAndIncrement();
	}

	public Usuario(String correo, String clave) {
		super();
		id = contador.getAndIncrement();
		this.correo = correo;
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", correo=" + correo + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
