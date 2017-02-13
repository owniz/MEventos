package controller;

import java.util.Iterator;

import org.hibernate.Session;

import utils.ConexionBaseDatos;

public class Consultas {
	public static Iterator consultarCiudad() {		
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from Ciudad").iterate();	
	
		return iter;
	}
	
	public static Iterator consultarCiudadEvento() {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from CiudadEvento").iterate();		
		
		return iter;
	}
	
	public static Iterator consultarEvento() {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from Evento where fecha > sysdate()").iterate();
		
		return iter;
	}
	
	public static Iterator consultarEventoSuscrito(int idUsuario) {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from EventoSuscrito where id_usuario = " + idUsuario).iterate();		
		
		return iter;
	}
	
	public static Iterator consultarUsuario() {	
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from Usuario").iterate();
		
		return iter;
	}
	
	public static Iterator consultarLogin(String email, String pass) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		Iterator iter = sesion.createQuery("from Usuario where email like '" + email + "' and pass_usuario like '" + pass + "'").iterate();

		return iter;
	}
	
	public static Iterator consultarSiEstasuscrito(int idUsuario, int idEvento) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		Iterator iter = sesion.createQuery("from EventoSuscrito where id_usuario = " + idUsuario + " and id_evento = " + idEvento).iterate();
		
		return iter;
	}
}
