package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.Consultas;
import controller.Inserciones;
import models.Usuario;

/*
 * Clase de las vistas para el inicio de sesión del usaurio
 */

public class LoginFrame extends JFrame implements ActionListener {
	
	// componentes de la interfaz
	private JLabel jlEmail, jlPassword, jlImagen;
	private JTextField jtfEmail;
	private JPasswordField jtfPassword;
	private JButton botonOK, botonRegistrar;
	
	public LoginFrame() {
		super("MEvento Login");
		iniciarGUI();
	}
	
	// método que inicia la interfaz
	public void iniciarGUI() {
		
		// no definimos layout para poder colocar cada elemento por coordenadas
		setLayout(null); 
		getContentPane().setBackground(Color.WHITE);
		
		// icono de la aplicación
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/logo_icono.png"));
		setIconImage(icon.getImage());
		
		// email
        jlEmail = new JLabel("Email: ", JLabel.RIGHT); // coloca la etiqueta a la derecha
        jlEmail.setBounds(115, 150, 80, 20);
        jtfEmail = new JTextField();
        jtfEmail.setBounds(240, 150, 100, 20);
        
        add(jlEmail);
        add(jtfEmail);
        
        // password
        jlPassword = new JLabel("Contraseña: ", JLabel.RIGHT); // coloca la etiqueta a la derecha
        jlPassword.setBounds(150, 180, 80, 20);
        jtfPassword = new JPasswordField();
        jtfPassword.setBounds(240, 180, 100, 20);
        add(jlPassword);
        add(jtfPassword);
         
        // imagen con el logo
        jlImagen = new JLabel();
        jlImagen.setBounds(120, 10, 250, 120);
        ImageIcon imagen= new  ImageIcon(getClass().getResource("/img/logo.png"));
        Icon iconImagen = new ImageIcon(imagen.getImage().getScaledInstance(jlImagen.getWidth(), jlImagen.getHeight(), Image.SCALE_DEFAULT));
        jlImagen.setIcon(iconImagen);
        add(jlImagen);
        
        // boton aceptar
        botonOK = new JButton("OK");
        botonOK.setBackground(new Color(5, 60, 70));
        botonOK.setForeground(Color.WHITE);
        botonOK.setIgnoreRepaint(true);
        botonOK.setBounds(300, 220, 100, 25);
        add(botonOK);
        botonOK.addActionListener(this);
        
        // boton registrar nuevo usuario 
        botonRegistrar = new JButton("Registrar");
        botonRegistrar.setBounds(100, 220, 100, 25);
        botonRegistrar.setBackground(new Color(5, 60, 70));
        botonRegistrar.setForeground(Color.WHITE);
        add(botonRegistrar);
        botonRegistrar.addActionListener(this);

		// configuración ventana
        setSize(500, 300);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// comportamiento de los botones
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// si se pulsa sobre el boton de aceptar recuperamos el usuario 
		// y contraseña para realizar una consulta con la BBDD para 
		// comprobar si es correcto, avisando en caso contrario 
		if(e.getSource() == botonOK) {
			String email = jtfEmail.getText();
			String password = String.valueOf(jtfPassword.getPassword());
			
			Iterator iter = Consultas.consultarLogin(email, password);
			
			if(iter.hasNext() && !email.isEmpty() && !password.isEmpty()) {
				Usuario usuario = (Usuario) iter.next();
				setVisible(false);
				new PanelMEvento(usuario);
			} else {
				JOptionPane.showMessageDialog(this, "Usuario o contraseña mal introducidos");
			}
			
		// si pulsamos sobre el botón de registro nos aparecerá la opción de
		// registrar un nuevo usuario en la BBDD si rellenamos todos los 
		// campos disponibles, si no,nos avisa del error	
		} else if(e.getSource() == botonRegistrar) {
			PanelRegistro panelRegistro = new PanelRegistro();
			
			if(JOptionPane.showConfirmDialog(this, panelRegistro,  "Introduzca sus datos", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
				
				String nombre = panelRegistro.getNombre().trim();
				String apellidos = panelRegistro.getApellidos().trim();
				String edad = panelRegistro.getEdad();
				String telefono = panelRegistro.getTelefono().trim();
				String email = panelRegistro.getEmail().trim();
				String password = panelRegistro.getPassword().trim();
				
				if(nombre != null && apellidos != null && edad != null && telefono != null && email != null && password != null) {
					Inserciones.insertarUsuario(nombre, apellidos, Integer.valueOf(edad), telefono, email, password);
					JOptionPane.showMessageDialog(this, "Usuario introducido con éxito");
				} else {
					JOptionPane.showMessageDialog(this, "Por favor, introduce todos los datos");
				}
				
			}
		}
	}
}
