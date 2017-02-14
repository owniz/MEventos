package controller;

import java.util.Iterator;

import org.hibernate.Session;

import utils.ConexionBaseDatos;

/*
 * Clase para realizar las consultas a la BBDD
 */

public class Consultas {
	
	// consultas a la tabla que contiene los datos de las ciudades
	public static Iterator consultarCiudad() {		
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from Ciudad").iterate();	
	
		return iter;
	}

	// consultas a la tabla que contiene los datos de las ciudades y los eventos
	public static Iterator consultarCiudadEvento() {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from CiudadEvento").iterate();		
		
		return iter;
	}
	
	// consultas a la tabla que contiene los datos de las ciudades y los eventos por la id del evento
	public static Iterator consultarCiudadEventoPorIdEvento(int idEvento) {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from CiudadEvento where id_evento = " + idEvento).iterate();		
		
		return iter;
	}
		
	// consultas a la tabla que contiene los datos de los eventos
	public static Iterator consultarEvento() {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from Evento where fecha > sysdate()").iterate();
		
		return iter;
	}
	
	// consultas a la tabla que contiene los datos de los eventos a los que se ha suscrito el usuario
	public static Iterator consultarEventoSuscrito(int idUsuario) {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from EventoSuscrito where id_usuario = " + idUsuario).iterate();		
		
		return iter;
	}
	
	// consultas a la tabla que contiene los datos de los eventos a los que se ha suscrito el usuario
	public static Iterator consultarEventoSuscritoPorIDEvento(int idEvento) {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from EventoSuscrito where id_evento = " + idEvento).iterate();		
		
		return iter;
	}
	
	// consultas a la tabla que contiene los datos de los usuarios
	public static Iterator consultarUsuario() {	
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from Usuario").iterate();
		
		return iter;
	}
	
	// consulta que comprueba si el usuario y constraseña es correcto
	public static Iterator consultarLogin(String email, String pass) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		Iterator iter = sesion.createQuery("from Usuario where email like '" + email + "' and pass_usuario like '" + pass + "'").iterate();

		return iter;
	}
	
	// consultas si un usuario esta suscrito ya a un evento
	public static Iterator consultarSiEstasuscrito(int idUsuario, int idEvento) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		Iterator iter = sesion.createQuery("from EventoSuscrito where id_usuario = " + idUsuario + " and id_evento = " + idEvento).iterate();
		
		return iter;
	}
}
