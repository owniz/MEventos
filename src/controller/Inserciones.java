package controller;

import org.hibernate.Session;

import models.Ciudad;
import models.Evento;
import models.EventoSuscrito;
import models.Usuario;
import utils.ConexionBaseDatos;

/*
 * Clase para realizar las inserciones a la BBDD
 */

public class Inserciones {
	
	// inserta un evento a la tabla de eventos suscritos
	public static void insertarEventoSuscrito(Usuario usuario, Evento evento) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		
		EventoSuscrito eventoSuscrito = new EventoSuscrito();
		
		eventoSuscrito.setEvento(evento);
		eventoSuscrito.setUsuario(usuario);
		eventoSuscrito.setPath(evento.getPath());
		
		sesion.beginTransaction();
		sesion.persist(eventoSuscrito);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	// insertamos un nuevo usuario
	public static void insertarUsuario(String nombre, String apellidos, int edad, String telefono, String email, String password) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre(nombre);
		usuario.setApellidos(apellidos);
		usuario.setEdad(edad);
		usuario.setTelefono(telefono);
		usuario.setEmail(email);
		usuario.setPassUsuario(password);
		usuario.setAdmin(false);
		
		sesion.beginTransaction();
		sesion.persist(usuario);
		sesion.getTransaction().commit();
		sesion.close();
	}
}
