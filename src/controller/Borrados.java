package controller;

import org.hibernate.Session;

import models.CiudadEvento;
import utils.ConexionBaseDatos;

public class Borrados {
	public static void borrarEventoDisponible(int idEventoDisponible) {
		Session sesion = ConexionBaseDatos.conectarBBDD();
		
		CiudadEvento ciudadEvento = (CiudadEvento) sesion.get(CiudadEvento.class, idEventoDisponible);
		
		sesion.beginTransaction();
		sesion.delete(ciudadEvento);
		sesion.getTransaction().commit();
		sesion.close();
	}
}
