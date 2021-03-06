package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import models.SessionFactoryUtil;

/*
 * Clase utilizada para establecer la conexión con la BBDD
 */

public final class ConexionBaseDatos {
	public static Session conectarBBDD() {
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session sesion = sf.openSession();
		
		return sesion;
	}
}
