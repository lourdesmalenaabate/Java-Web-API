package com.educacionit.principal;

import javax.xml.ws.Endpoint;

import com.educacionit.servicios.UsuarioServicio;

public class App {

	public static void main(String[] args) {
		// Endpoint.publish("http://localhost:8081/servicio/suma", new
		// ServicioBasico());

		Endpoint.publish("http://localhost:8081/servicio/usuario", new UsuarioServicio());
	}

}
