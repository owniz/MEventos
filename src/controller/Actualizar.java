package controller;

import org.hibernate.Session;

import models.EventoSuscrito;
import models.Usuario;
import utils.ConexionBaseDatos;

public class Actualizar {
	public static void actualizarNotaEvento(EventoSuscrito eventoSuscrito, String valoracion) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		
		EventoSuscrito eventoSus = (EventoSuscrito) sesion.get(EventoSuscrito.class, eventoSuscrito.getIdEventoSuscrito());
		
		eventoSus.setValoracion(valoracion);
		
		sesion.beginTransaction();
		sesion.persist(eventoSus);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public static void actualizarUsuario(Usuario usuario, String nombre, String apellidos, String edad,
													String telefono, String email, String passUsuario) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		
		Usuario usuarioActualizado = (Usuario) sesion.get(Usuario.class, usuario.getIdUsuario());
		
		usuarioActualizado.setNombre(nombre);
		usuarioActualizado.setApellidos(apellidos);
		usuarioActualizado.setEdad(Integer.valueOf(edad));
		usuarioActualizado.setTelefono(telefono);
		usuarioActualizado.setEmail(email);
		usuarioActualizado.setPassUsuario(passUsuario);
		
		sesion.beginTransaction();
		sesion.persist(usuarioActualizado);
		sesion.getTransaction().commit();
		sesion.close();
	}
}
