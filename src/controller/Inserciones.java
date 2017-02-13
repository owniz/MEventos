package controller;

import org.hibernate.Session;

import models.Ciudad;
import models.Evento;
import models.EventoSuscrito;
import models.Usuario;
import utils.ConexionBaseDatos;

public class Inserciones {
	public static void insertarCiudad(String nombre, String provincia) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		
		Ciudad ciudad = new Ciudad();
		
		ciudad.setNombreCiudad(nombre);
		ciudad.setProvincia(provincia);
		
		sesion.beginTransaction();
		sesion.persist(ciudad);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
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
