package view;


import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Consultas;
import controller.Inserciones;
import models.Usuario;

public class LoginFrame extends JFrame implements ActionListener {
	private JLabel jlEmail, jlPassword, jlImagen;
	private JTextField jtfEmail;
	private JPasswordField jtfPassword;
	private JButton botonOK, botonRegistrar;
	
	public LoginFrame() {
		super("MEvento Login");
		iniciarGUI();
	}
	
	public void iniciarGUI() {
		setLayout(null); 
		getContentPane().setBackground(Color.WHITE);
		
		/*Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo.png"));
		ImageIcon icon = new ImageIcon();
		setIconImage(icon.getImage());*/
		
        jlEmail = new JLabel("Email: ", JLabel.RIGHT); // coloca la etiqueta a la derecha
        jlEmail.setBounds(115, 150, 80, 20);
        jtfEmail = new JTextField();
        jtfEmail.setBounds(240, 150, 100, 20);

        add(jlEmail);
        add(jtfEmail);
        
        jlPassword = new JLabel("Contrase�a: ", JLabel.RIGHT); // coloca la etiqueta a la derecha
        jlPassword.setBounds(150, 180, 80, 20);
        jtfPassword = new JPasswordField();
        jtfPassword.setBounds(240, 180, 100, 20);
        add(jlPassword);
        add(jtfPassword);
         
        jlImagen = new JLabel();
        jlImagen.setBounds(120, 10, 250, 120);
        ImageIcon imagen= new  ImageIcon(getClass().getResource("/img/logo.png"));
        Icon iconImagen = new ImageIcon(imagen.getImage().getScaledInstance(jlImagen.getWidth(), jlImagen.getHeight(), Image.SCALE_DEFAULT));
        jlImagen.setIcon(iconImagen);
        add(jlImagen);
        
        botonOK = new JButton("OK");
        botonOK.setBackground(new Color(5, 60, 70));
        botonOK.setForeground(Color.WHITE);
        botonOK.setBounds(300, 220, 100, 25);
        add(botonOK);
        botonOK.addActionListener(this);
        
        botonRegistrar = new JButton("Registrar");
        botonRegistrar.setBounds(100, 220, 100, 25);
        botonRegistrar.setBackground(new Color(5, 60, 70));
        botonRegistrar.setForeground(Color.WHITE);
        add(botonRegistrar);
        botonRegistrar.addActionListener(this);

        setSize(500, 300);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
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
