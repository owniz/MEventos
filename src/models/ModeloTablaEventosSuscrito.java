package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import controller.Consultas;

public class ModeloTablaEventosSuscrito extends DefaultTableModel {
	Class tipos[] = {String.class, String.class};
	
	public ModeloTablaEventosSuscrito(int idUsuario) {
		int numeroColumnas = 2;
		
		JFrame frame = new JFrame("DemoTablaEventoSuscrito");
		
		Iterator iter = Consultas.consultarEventoSuscrito(idUsuario);

		ArrayList<String> nombreEventos = new ArrayList<>();
		ArrayList<Date> fechaEventos = new ArrayList<>();
		
		while(iter.hasNext()) {
			EventoSuscrito eventoSuscrito = (EventoSuscrito) iter.next();
			
			nombreEventos.add(eventoSuscrito.getEvento().getDenominacion());
			fechaEventos.add((Date) eventoSuscrito.getEvento().getFecha());
		}
		
		String[] columnas = {"Nombre Evento", "Fecha"};

		Object[][] filas = new Object[nombreEventos.size()][numeroColumnas];
		
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
		
		setDataVector(filas, columnas);		
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	};
	
	// para pintar cada celda diferente
	@Override
	public Class getColumnClass(int indice) {
		return tipos[indice];
	}
}
