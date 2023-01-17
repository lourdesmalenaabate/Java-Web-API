package com.educacionit.servicios;

import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.mariadb.UsuarioImplmentacion;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioControlador {
	private UsuarioImplmentacion implementacion = new UsuarioImplmentacion();

	@Path("/listar")
	@GET
	public List<Usuario> listar() {
		return implementacion.listar();
	}

	@POST
	@Path("/agregar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response agregar(Usuario usuario) {
		implementacion.guardar(usuario);
		return Response.ok(usuario).build();
	}

	@PUT
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Usuario usuario) {
		implementacion.guardar(usuario);
		return Response.ok(usuario).build();
	}

	// query param
	@GET
	@Path("/buscar")
	public Response buscarPorQP(@QueryParam("correo") String correo) {
		Usuario usuario = implementacion.buscar(correo);
		if (null == usuario) {
			return Response.status(404).build();
		}
		return Response.ok(usuario).build();
	}

	// recurso o URN
	@GET
	@Path("/buscar/{correo}")
	public Response buscarPorURN(@PathParam("correo") String correo) {
		Usuario usuario = implementacion.buscar(correo);
		if (null == usuario) {
			return Response.status(404).build();
		}
		return Response.ok(usuario).build();
	}
	
	
	@DELETE
	@Path("/eliminar")
	public Response eliminar(Usuario usuario) {
		return Response.ok(implementacion.eliminar(usuario)).build();
	}
	
	// agregar una lista completa
	@POST
	@Path("agregarLista")
	public List<Usuario> agregarLista(List<Usuario> usuarios){
		ListIterator<Usuario> it = usuarios.listIterator();
		
		while (it.hasNext()) {
			Usuario usuario =  it.next();
			if(null != implementacion.buscar(usuario.getCorreo())) {
				implementacion.guardar(usuario);
				it.remove();
			}else {
				implementacion.guardar(usuario);
			}
		}
		return usuarios;
	}
	
	
	@POST
	@Path("/agregarFormulario")
	public Response agregarFormulario(@FormParam("correo") String correo,@FormParam("clave") String clave) {
		Usuario usuario = new Usuario();
		usuario.setCorreo(correo);
		usuario.setClave(clave);
		implementacion.guardar(usuario);
		return Response.ok(usuario).build();
		
	}
	
	
	
	

}
