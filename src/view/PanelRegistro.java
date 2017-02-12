package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class PanelRegistro extends JPanel implements ActionListener {
	private JFormattedTextField campoNombre, campoApellidos, campoTelefono, campoEmail, campoPassword;
	private JComboBox jcbEdad;
	private int edadSeleccionada;

	public PanelRegistro() {
		iniciarGUI();
	}
	
	public String getNombre() {
		return campoNombre.getText();
	}

	public String getApellidos() {
		return campoApellidos.getText();
	}

	public String getEdad() {
		return String.valueOf(edadSeleccionada = (edadSeleccionada == 0) ? edadSeleccionada + 14 : edadSeleccionada);
	}

	public String getTelefono() {
		return campoTelefono.getText();
	}

	public String getEmail() {
		return campoEmail.getText();
	}

	public String getPassword() {
		return campoPassword.getText();
	}
	
	public void iniciarGUI() {
		setLayout(new GridLayout(6, 2, 8, 8));
		
		JLabel jlNombre = new JLabel("Nombre: ", JLabel.RIGHT);
		try {
			MaskFormatter formatter = new MaskFormatter("***********************"
												+ "***************************");
			campoNombre = new JFormattedTextField(formatter);	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		add(jlNombre);
		add(campoNombre);
		
		JLabel jlApellidos = new JLabel("Apellidos: ", JLabel.RIGHT);
		try {
			MaskFormatter formatter = new MaskFormatter("***********************"
												+ "***************************");
			campoApellidos = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		add(jlApellidos);
		add(campoApellidos);
		
		JLabel jlEdad = new JLabel("Edad: ", JLabel.RIGHT);
		String[] edades = {"14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
							"24", "25", "26", "27", "28", "29", "30", "31", "32", "33",
							"34", "35", "36", "37", "38", "39", "40", "41", "42", "43",
							"44", "45", "46", "47", "48", "50", "51", "52", "53", "54",
							"55", "56", "57", "58", "59", "60", "61", "62", "63", "64",
							"65", "66", "67", "68", "69", "70", "71", "72", "73", "74",
							"75", "76", "77", "78", "79", "80", "81", "82", "83", "84",
							"85", "86", "87", "88", "89", "90", "91", "92", "93", "94",
							"95", "96", "97", "98", "99", "100"};
		jcbEdad = new JComboBox(edades);
		jcbEdad.setSelectedIndex(0);
		jcbEdad.addActionListener(this);
		add(jlEdad);
		add(jcbEdad);
		
		JLabel jlTelefono = new JLabel("Tel�fono: ", JLabel.RIGHT);
		try {
			MaskFormatter formatter = new MaskFormatter("*********");
			campoTelefono = new JFormattedTextField(formatter);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		add(jlTelefono);
		add(campoTelefono);
		
		JLabel jlEmail = new JLabel("Email: ", JLabel.RIGHT);
		try {
			MaskFormatter formatter = new MaskFormatter("********************");
			campoEmail = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		add(jlEmail);
		add(campoEmail);
		
		JLabel jlPassword = new JLabel("Contrase�a: ", JLabel.RIGHT);
		try {
			MaskFormatter formatter = new MaskFormatter("****************************************");
			campoPassword = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		add(jlPassword);
		add(campoPassword);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		edadSeleccionada = jcbEdad.getSelectedIndex() + 14;
	}
}
