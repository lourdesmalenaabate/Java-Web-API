package com.educacionit.servicios;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.mariadb.UsuarioImplmentacion;

@WebService(name = "ServicioUsuario", portName = "ServicioUsuario")
public class UsuarioServicio {
	private static UsuarioImplmentacion implementacion = new UsuarioImplmentacion();

	/**
	 * 
	 * @param usuario
	 * @return boolean true si guardo, de lo contrario false
	 */

	@WebMethod(operationName = "guardarUsuario")
	@WebResult(name = "guardado")
	public boolean agregar(@WebParam(name = "usuario") Usuario usuario) {
		return implementacion.guardar(usuario);
	}

	@WebMethod(operationName = "eliminarUsuario")
	@WebResult(name = "eliminado")
	public boolean eliminar(@WebParam(name = "usuario") Usuario usuario) {
		return implementacion.eliminar(usuario);
	}

	@WebMethod(operationName = "buscarUsuario")
	@WebResult(name = "usuario")
	public Usuario buscar(@WebParam(name = "correo") String correo) {
		return implementacion.buscar(correo);
	}

	@WebMethod(operationName = "listarUsuarios")
	@WebResult(name = "usuarios")
	public Usuario[] listar() {
		// return implementacion.listar();
		List<Usuario> lista = implementacion.listar();
		Usuario[] usuarios = new Usuario[lista.size()];
		lista.toArray(usuarios);
		return usuarios;

	}

}
