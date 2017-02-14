package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import controller.Consultas;

/*
 * Clase que define el modelo de la tabla utilizada para mostrar los datos de los eventos disponibles
 */

public class ModeloTablaEventos extends DefaultTableModel {
	
	// tipo de objeto para cada columna
	Class tipos[] = {String.class, String.class, String.class, String.class};
	
	// modelo y recogida de datos
	public ModeloTablaEventos() {
		
		// número de columnas
		int numeroColumnas = 4;
		
		// realizamos la consulta y guardamos cada dato
		Iterator iter = Consultas.consultarEvento();

		ArrayList<String> nombrEventos = new ArrayList<>();
		ArrayList<String> horaInicio = new ArrayList<>();
		ArrayList<String> horaFin = new ArrayList<>();
		ArrayList<String> fechas = new ArrayList<>();
		
		while(iter.hasNext()) {
			Evento evento = (Evento) iter.next(); 
			
			// solo almacenamos eventos que no haya pasado la fecha
			nombrEventos.add(evento.getDenominacion());
			horaInicio.add(evento.getHoraInicio());
			horaFin.add(evento.getHoraFin());
			fechas.add(evento.getFecha().toString());		
		}
		
		
		// cabecera de la tabla
		String[] columnas = {"Nombre Evento", "Hora Inicio", "Hora Fin", "Fecha"};

		// almacenamos cada fila con su dato corrrespondiente
		Object[][] filas = new Object[nombrEventos.size()][numeroColumnas];
		
		for(int i = 0; i < nombrEventos.size(); i++) {
			for(int j = 0; j < numeroColumnas; j++) {
				if(j == (nombrEventos.size() + j) - nombrEventos.size()) {
					if(j == 0)
						filas[i][j] = nombrEventos.get(i);
					if(j == 1)
						filas[i][j] = horaInicio.get(i);	
					if(j == 2)
						filas[i][j] = horaFin.get(i);
					if(j == 3)
						filas[i][j] = fechas.get(i);
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
