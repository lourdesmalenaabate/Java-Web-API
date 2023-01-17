package com.educacionit.RestClient;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		// URL
		URI URL_SERVICIOS = UriBuilder.fromUri("http://localhost:8080/Rest/servicios").build();
		ClientConfig config = new ClientConfig();
		config.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);

		Client cliente = ClientBuilder.newClient(config);

		Usuario usuario = new Usuario();
		usuario.setCorreo("user13@gmail.com");
		usuario.setClave("1234");

		WebTarget URL_USUARIOS = cliente.target(URL_SERVICIOS).path("usuarios");

		// agregar usuarios

		Response respuestaAgregar = URL_USUARIOS.path("agregar").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(usuario, MediaType.APPLICATION_JSON_TYPE));

		System.out.println(respuestaAgregar);

		System.out.println(respuestaAgregar.getStatus());

		Response respuestaUsuarioURN = URL_USUARIOS.path("buscar").path("user12@gmail.com")
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		System.out.println(respuestaUsuarioURN);

		// reflexion

		Usuario usuarioURN = respuestaUsuarioURN.readEntity(Usuario.class);
		System.out.println(usuarioURN);

		Response RespuestaLista = URL_USUARIOS.path("listar").request(MediaType.APPLICATION_JSON_TYPE).get();

		List<Usuario> listaUsuarios = (List<Usuario>) RespuestaLista.readEntity(List.class);

		System.out.println(listaUsuarios);

		Response eliminarUsuario = URL_USUARIOS.path("eliminar").request(MediaType.APPLICATION_JSON_TYPE).method("DELETE",
				Entity.entity(usuario, MediaType.APPLICATION_JSON_TYPE));

	}
}
