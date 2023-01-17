package com.educacionit.DAO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.mariadb.UsuarioImplmentacion;
import com.educacionit.interfaces.UtilidadesFecha;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws ParseException {
		UsuarioImplmentacion implementacion = new UsuarioImplmentacion();

		Usuario usuario1 = new Usuario("user1@gmail.com", "12345", new Date(),
				UtilidadesFecha.getStringAFecha("2022-01-27"));

		System.out.println(implementacion.guardar(usuario1));

		implementacion.guardar(
				new Usuario("user2@gmail.com", "12345", new Date(), UtilidadesFecha.getStringAFecha("2022-01-27")));

		List<Usuario> lista = implementacion.listar();

		lista.forEach(System.out::println);

	}
}
