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

/*
 * Panel utilizado para el registro y modificación de usuario
 */

public class PanelRegistro extends JPanel implements ActionListener {
	
	// componentes de la vista
	private JFormattedTextField campoNombre, campoApellidos, campoTelefono, campoEmail, campoPassword;
	private JComboBox jcbEdad;
	
	// selección de la edad en el JComboBox
	private int edadSeleccionada;

	public PanelRegistro() {
		iniciarGUI();
	}
	
	// getters y setters
	public String getNombre() {
		return campoNombre.getText().trim();
	}

	public String getApellidos() {
		return campoApellidos.getText().trim();
	}

	public String getEdad() {
		return String.valueOf(edadSeleccionada = (edadSeleccionada == 0) ? edadSeleccionada + 14 : edadSeleccionada).trim();
	}

	public String getTelefono() {
		return campoTelefono.getText().trim();
	}

	public String getEmail() {
		return campoEmail.getText().trim();
	}

	public String getPassword() {
		return campoPassword.getText().trim();
	}
	
	public void setNombre(String nombre) {
		campoNombre.setText(nombre);
	}
	
	public void setApellidos(String apellidos) {
		campoApellidos.setText(apellidos);
	}
	
	public void setEdad(int edadSeleccionada) {
		jcbEdad.setSelectedIndex(edadSeleccionada - 14);
	}
	
	public void setTelefono(String telefono) {
		campoTelefono.setText(telefono);
	}
	
	public void setEmail(String email) {
		campoEmail.setText(email);
	}
	
	public void setPassword(String password) {
		campoPassword.setText(password);
	}
	
	// iniciamos y declaramos los componentes
	public void iniciarGUI() {
		setLayout(new GridLayout(6, 2, 8, 8));
		
		// sección nombre
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
		
		// sección apellidos
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
		
		// sección edad
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
		
		// sección teléfono
		JLabel jlTelefono = new JLabel("Teléfono: ", JLabel.RIGHT);
		try {
			MaskFormatter formatter = new MaskFormatter("*********");
			campoTelefono = new JFormattedTextField(formatter);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		add(jlTelefono);
		add(campoTelefono);
		
		// sección email
		JLabel jlEmail = new JLabel("Email: ", JLabel.RIGHT);
		try {
			MaskFormatter formatter = new MaskFormatter("********************");
			campoEmail = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		add(jlEmail);
		add(campoEmail);
		
		// sección password
		JLabel jlPassword = new JLabel("Contraseña: ", JLabel.RIGHT);
		try {
			MaskFormatter formatter = new MaskFormatter("****************************************");
			campoPassword = new JFormattedTextField(formatter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		add(jlPassword);
		add(campoPassword);
	}

	// recoge la edad selecciona en el JComboBox
	@Override
	public void actionPerformed(ActionEvent e) {
		edadSeleccionada = jcbEdad.getSelectedIndex() + 14;
	}
}
