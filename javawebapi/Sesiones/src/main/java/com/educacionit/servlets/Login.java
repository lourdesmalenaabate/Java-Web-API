package com.educacionit.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.educacionit.entidades.Usuario;

/**
 * Servlet implementation class Login
 */

@WebServlet(name = "login", urlPatterns = "/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Usuario> usuarios;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		llenarUsuarios();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean sesion = Boolean.parseBoolean(request.getParameter("sesion"));
		
		if (sesion) {
			request.getSession().invalidate();
			request.setAttribute("mensaje", "Se ha cerrado correctamente la sesion");
			request.getRequestDispatcher("formulario.jsp").forward(request, response);
		}
		
		response.sendRedirect("index.jsp");
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String correo = request.getParameter("correo");
		String clave = request.getParameter("clave");
		Usuario usuario = buscar(correo, clave);
		String pagina = "formulario.jsp";
		String mensaje = "Credenciales incorrectas";

		if (usuario != null) {
			HttpSession sesion = request.getSession();
			mensaje = "Bienvenido:";
			pagina = "index.jsp";
			sesion.setAttribute("mensaje", mensaje);
			sesion.setAttribute("usuario", usuario);
			sesion.setAttribute("id", sesion.getId());
		} else {
			request.setAttribute("mensaje", mensaje);
		}

		RequestDispatcher rd = request.getRequestDispatcher(pagina);
		rd.forward(request, response);

	}

	private Usuario buscar(String correo, String clave) {
		for (Usuario usuario : usuarios) {
			if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getClave().equals(clave)) {
				return usuario;
			}
		}

		return null;
	}

	private void llenarUsuarios() {
		usuarios = new ArrayList<>();
		usuarios.add(new Usuario("user1@gmail.com", "user1.1234"));
		usuarios.add(new Usuario("user2@gmail.com", "user2.1234"));
		usuarios.add(new Usuario("user3@gmail.com", "user3.1234"));
		usuarios.add(new Usuario("user4@gmail.com", "user4.1234"));
	}

}
