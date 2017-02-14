package controller;

import org.hibernate.Session;

import models.CiudadEvento;
import models.Usuario;
import utils.ConexionBaseDatos;

/*
 * Clase para realizar los borrados de la BBDD
 */

public class Borrados {
	
	// borramos eventos disponibles
	public static void borrarEventoDisponible(int idEventoDisponible) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		
		CiudadEvento ciudadEvento = (CiudadEvento) sesion.get(CiudadEvento.class, idEventoDisponible);
		
		sesion.beginTransaction();
		sesion.delete(ciudadEvento);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	// borramos nuestro usuario
	public static void borrarUsuario(int idUsuario) {		
		Session sesion = ConexionBaseDatos.conectarBBDD();
		
		Usuario usuario = (Usuario) sesion.get(Usuario.class, idUsuario);
		
		sesion.beginTransaction();
		sesion.delete(usuario);
		sesion.getTransaction().commit();
		sesion.close();
	}
}
