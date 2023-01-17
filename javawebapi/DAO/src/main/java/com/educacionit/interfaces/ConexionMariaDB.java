package com.educacionit.interfaces;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public interface ConexionMariaDB {

	default Connection getConexion() {
		//
		Connection conexion = null;
		Properties propiedades = new Properties();
		try {
			// .jar
			propiedades.load(new FileInputStream(
					new File("src" + File.separator + "resources" + File.separator + "database.properties")));
			String URL = propiedades.getProperty("url");
			String USER = propiedades.getProperty("user");
			String PASS = propiedades.getProperty("pass", "1234");

			Class.forName(propiedades.getProperty("driver"));
			conexion = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();// no encuentro
		} catch (IOException e) {
			e.printStackTrace(); // archivo corrupto bloqueado
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conexion;

	}

}
