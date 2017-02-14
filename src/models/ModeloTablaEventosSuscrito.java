package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import controller.Consultas;

/*
 * Clase que define el modelo de la tabla utilizada para mostrar los datos de los eventos
 * a los que se ha suscrito un usuario
 */

public class ModeloTablaEventosSuscrito extends DefaultTableModel {
	
	// tipo de objeto para cada columna
	Class tipos[] = {String.class, String.class};
	
	// modelo y recogida de datos
	public ModeloTablaEventosSuscrito(int idUsuario) {
		
		// número de columnas
		int numeroColumnas = 2;

		// realizamos la consulta y guardamos cada dato
		Iterator iter = Consultas.consultarEventoSuscrito(idUsuario);

		ArrayList<String> nombreEventos = new ArrayList<>();
		ArrayList<Date> fechaEventos = new ArrayList<>();
		
		while(iter.hasNext()) {
			EventoSuscrito eventoSuscrito = (EventoSuscrito) iter.next();
			
			nombreEventos.add(eventoSuscrito.getEvento().getDenominacion());
			fechaEventos.add((Date) eventoSuscrito.getEvento().getFecha());
		}
		
		// cabecera de la tabla
		String[] columnas = {"Nombre Evento", "Fecha"};

		Object[][] filas = new Object[nombreEventos.size()][numeroColumnas];
		
		// almacenamos cada fila con su dato corrrespondiente
		for(int i = 0; i < nombreEventos.size(); i++) {
			for(int j = 0; j < numeroColumnas; j++) {
				if(j == (nombreEventos.size() + j) - nombreEventos.size()) {
					if(j == 0)
						filas[i][j] = nombreEventos.get(i);
					if(j == 1)
						filas[i][j] = fechaEventos.get(i).toString();
				}
			}
		}
		
		// establecemos los datos de la tabla
		setDataVector(filas, columnas);		
	}
	
	// hacemos que no se pueda modificar la tabla
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	};
	
	// Si la tabla contiene mas de un tipo de objeto este método es necesario
	// para su correcta visualización
	@Override
	public Class getColumnClass(int indice) {
		return tipos[indice];
	}
}
