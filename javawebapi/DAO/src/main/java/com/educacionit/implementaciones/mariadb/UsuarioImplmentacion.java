package com.educacionit.implementaciones.mariadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.educacionit.entidades.Usuario;
import com.educacionit.interfaces.ConexionMariaDB;
import com.educacionit.interfaces.DAO;
import com.educacionit.interfaces.UtilidadesFecha;

public class UsuarioImplmentacion implements DAO<Usuario, String>, ConexionMariaDB {
	private PreparedStatement psBuscar;
	private PreparedStatement psInsertar;
	private PreparedStatement psEliminar;
	private PreparedStatement psActualizar;
	private PreparedStatement psListar;
	private final String KEY = "JavaWebAPI";

	@Override
	public Usuario buscar(String correo) { // octavio.robleto@gmail.com
		Usuario usuario = null;
		String query = "select AES_DECRYPT(clave,?) as clave ,fechaActualizacion,fechaCreacion from usuarios where correo = ?";

		try {

			if (null == psBuscar) {
				psBuscar = getConexion().prepareStatement(query);
			}

			psBuscar.setString(1, KEY);
			psBuscar.setString(2, correo);

			ResultSet resultado = psBuscar.executeQuery();

			if (resultado.next()) {
				usuario = new Usuario();
				usuario.setCorreo(correo);
				usuario.setClave(resultado.getString("clave"));
				usuario.setFechaCreacion(UtilidadesFecha.getStringAFecha(resultado.getString("fechaCreacion")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	@Override
	public Boolean eliminar(Usuario usuario) {
		String query = "delete from usuarios where correo = ?";
		try {
			if (null == psEliminar) {
				psEliminar = getConexion().prepareStatement(query);
			}

			psEliminar.setString(1, usuario.getCorreo());

			return psEliminar.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> listaUsuarios = new ArrayList<>();
		String query = "select correo, AES_DECRYPT(clave,?) as clave ,fechaActualizacion,fechaCreacion from usuarios";

		try {
			if (null == psListar) {
				psListar = getConexion().prepareStatement(query);
			}
			psListar.setString(1, KEY);
			ResultSet resultado = psListar.executeQuery();

			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setCorreo(resultado.getString("correo"));
				usuario.setClave(resultado.getString("clave"));
				usuario.setFechaCreacion(UtilidadesFecha.getStringAFecha(resultado.getString("fechaCreacion")));
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return listaUsuarios;
	}

	@Override
	public Boolean guardar(Usuario usuario) {
		Usuario usuarioAuxiliar = buscar(usuario.getCorreo());
		if (null == usuarioAuxiliar) {
			return insertar(usuario);
		} else {
			return actualizar(usuario);
		}
	}

	private Boolean insertar(Usuario usuario) {
		String query = "insert into usuarios (correo,clave,fechaCreacion) values (?,AES_ENCRYPT(?,?),?)";
		try {

			if (null == psInsertar) {
				psInsertar = getConexion().prepareStatement(query);
			}

			psInsertar.setString(1, usuario.getCorreo());
			psInsertar.setString(2, usuario.getClave());
			psInsertar.setString(3, KEY);
			psInsertar.setString(4, UtilidadesFecha.getFechaAString(new Date()));

			return psInsertar.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private Boolean actualizar(Usuario usuario) {
		String query = "update usuarios set clave = AES_ENCRYPT(?,?)  where correo = ?";
		try {

			if (null == psActualizar) {
				psActualizar = getConexion().prepareStatement(query);
			}
			psActualizar.setString(1, usuario.getClave());
			psActualizar.setString(2, KEY);
			psActualizar.setString(3, usuario.getCorreo());

			return psActualizar.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
