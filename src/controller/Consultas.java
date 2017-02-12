package controller;

import java.util.Iterator;

import org.hibernate.Session;

import utils.ConexionBaseDatos;

public class Consultas {
	public static Iterator consultarCiudad() {		
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from Ciudad").iterate();	
		//sesion.close();		
		return iter;
	}
	
	public static Iterator consultarCiudadEvento() {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from CiudadEvento where fecha > sysdate()").iterate();		
		//sesion.close();		
		return iter;
	}
	
	public static Iterator consultarEvento() {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from Evento").iterate();
		//sesion.close();		
		return iter;
	}
	
	public static Iterator consultarEventoSuscrito() {
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from EventoSuscrito").iterate();		
		//sesion.close();		
		return iter;
	}
	
	public static Iterator consultarUsuario() {	
		Session sesion = ConexionBaseDatos.conectarBBDD();		
		Iterator iter = sesion.createQuery("from Usuario").iterate();
		//sesion.close();		
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
