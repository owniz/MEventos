package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import controller.Actualizar;
import controller.Borrados;
import controller.Consultas;
import controller.Inserciones;
import models.CiudadEvento;
import models.Evento;
import models.EventoSuscrito;
import models.ModeloTablaEventos;
import models.ModeloTablaEventosSuscrito;
import models.Usuario;

/*
 * Panel principal de la aplicación en el que podremos ver y gestionar
 * los eventos disponibles, a los que se suscribe el usuario y además
 * el panel de usuario 
 */

public class PanelMEvento2 extends JFrame implements ActionListener {
	
	// componentes visuales
	private JPanel jpGeneral, jpTabEventosDisponibles, jpTabEventosSuscritos, jpTabUsuario;
	private JMenuBar BarraMenu;
	private JMenu menuArchivo, menuAyuda;
	private JMenuItem jmiCerrarSesion, jmiSalir, jmiContenidoAyuda, jmiAcercaDe;
	private JTabbedPane jtpTabs;
	private JLabel jlTituloEventosDispo, jlTituloEventosSus, jlTituloUsuario; 
	private JLabel jlTituloDescripcion, jlTituloValorarion, jlImagenEvento, jlImagenEventoSuscrito;
	private JTextArea jtaDescripcion;
	private JFormattedTextField jtaValoracion;
	private JTable tablaEventosDisponibles, tablaEventosSuscrito;
	private JScrollPane scrollTablaEventosDisponibles, scrollTablaEventosSuscrito;
	private JButton botonSuscribirse, botonBorrarEvento, botonGuardarValoracion, botonBorrarUsuario, botonGuardarDatosUsuario;
	private PanelRegistro panelRegistro;
	
	// Arrays que almacenan los datos de las tablas
	private ArrayList<Evento> eventos;
	private ArrayList<CiudadEvento> ciudadEventos;
	private ArrayList<EventoSuscrito> arrayEventosSuscrito;	
	
	// modelos de tablas
	private ModeloTablaEventos modeloTablaEventosDisponibles;
	private ModeloTablaEventosSuscrito modeloTablaEventosSuscrito;

	// objetos de las tablas
	private Usuario usuario;
	private Evento evento;
	private EventoSuscrito eventoSuscrito;
	private CiudadEvento ciudadEvento;
	
	// almacemos las filas donde se pulsa en cada tabla
	private int filaDispo;
	private int filaSuscrito;
	
	public PanelMEvento2(Usuario usuario) {
		super("MEvento");
		this.usuario = usuario;
		iniciarGUI();
	}
	
	public void iniciarGUI() {		
		jpGeneral = new JPanel();
		
		// icono
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/logo_icono.png"));
		setIconImage(icon.getImage());
		
		// Barra de menú
		BarraMenu = new JMenuBar();
		setJMenuBar(BarraMenu);
		menuArchivo = new JMenu("Archivo");
		menuAyuda = new JMenu("Ayuda");
		
		jmiCerrarSesion = new JMenuItem("Cerrar Sesión");
		jmiSalir = new JMenuItem("Salir");
		jmiContenidoAyuda = new JMenuItem("Contenido Ayuda");
		jmiAcercaDe = new JMenuItem("Acerca De");
		
		menuArchivo.add(jmiCerrarSesion);
		menuArchivo.add(jmiSalir);
		menuAyuda.add(jmiContenidoAyuda);
		menuAyuda.add(jmiAcercaDe);
		BarraMenu.add(menuArchivo);
		BarraMenu.add(menuAyuda);
		
		jmiCerrarSesion.addActionListener(this);
		jmiSalir.addActionListener(this);
		jmiContenidoAyuda.addActionListener(this);
		jmiAcercaDe.addActionListener(this);		
		
		// paneles
		jpTabEventosDisponibles = new JPanel();
		jpTabEventosDisponibles.setLayout(null);
		jpTabEventosDisponibles.setBackground(Color.WHITE);
		jpTabEventosSuscritos = new JPanel();
		jpTabEventosSuscritos.setLayout(null);
		jpTabEventosSuscritos.setBackground(Color.WHITE);
		jpTabUsuario = new JPanel();
		jpTabUsuario.setLayout(null);
		jpTabUsuario.setBackground(Color.WHITE);
		
		add(jpGeneral).setBackground(Color.LIGHT_GRAY);
		
		jpGeneral.setLayout(new BorderLayout());
		
		jtpTabs = new JTabbedPane();
		jtpTabs.setTabPlacement(JTabbedPane.LEFT);
		jtpTabs.addTab("Disponibles", jpTabEventosDisponibles);
		jtpTabs.addTab("Suscrito", jpTabEventosSuscritos);
		jtpTabs.addTab("Usuario", jpTabUsuario);
		jpGeneral.add(jtpTabs);
		
		/* PESTAÑA EVENTOS DISPONIBLES*/		
		
		// título
		jlTituloEventosDispo = new JLabel("EVENTOS DISPONIBLES");
		jlTituloEventosDispo.setBounds(10, 10, 270, 20);
		jlTituloEventosDispo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		jlTituloEventosDispo.setForeground(new Color(5, 60, 80));
		jpTabEventosDisponibles.add(jlTituloEventosDispo);
		
		// botones
		botonSuscribirse = new JButton("Suscribirse");
		botonSuscribirse.setBounds(520, 10, 120, 25);
		botonSuscribirse.setBackground(Color.LIGHT_GRAY);	
		botonSuscribirse.setForeground(Color.WHITE);
		botonSuscribirse.setEnabled(false);
		botonSuscribirse.addActionListener(this);
        jpTabEventosDisponibles.add(botonSuscribirse);
        
        if(usuario.getAdmin()) { // sólo aparece si eres administrador
	        botonBorrarEvento = new JButton("Borrar Evento");
	        botonBorrarEvento.setBounds(320, 10, 140, 25);
	        botonBorrarEvento.setBackground(Color.LIGHT_GRAY);
	        botonBorrarEvento.setForeground(Color.WHITE);
	        botonBorrarEvento.setEnabled(false);
	        botonBorrarEvento.addActionListener(this);
	        jpTabEventosDisponibles.add(botonBorrarEvento);
        }
		
		// tabla de eventos diponibles
		modeloTablaEventosDisponibles = new ModeloTablaEventos();
		tablaEventosDisponibles = new JTable(modeloTablaEventosDisponibles);
		scrollTablaEventosDisponibles = new JScrollPane(tablaEventosDisponibles);
		tablaEventosDisponibles.getColumnModel().getColumn(1).setMaxWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(1).setMinWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(2).setMaxWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(2).setMinWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(3).setMaxWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(3).setMinWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(4).setMaxWidth(100);
		tablaEventosDisponibles.getColumnModel().getColumn(4).setMinWidth(100);
		tablaEventosDisponibles.addMouseListener(new MouseListener() {

			// comportamiento al pulsar sobre la tabla de eventos diponibles
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				// activamos los botones
				botonSuscribirse.setEnabled(true);
				botonSuscribirse.setBackground(new Color(5, 60, 70));
				
				// solo aparece si el usuario es adeministrador
				if(usuario.getAdmin()) {
					botonBorrarEvento.setEnabled(true);
					botonBorrarEvento.setBackground(Color.RED);
				}
				
				// mostramos los eventos disponibles
				Iterator iter = Consultas.consultarCiudadEvento();
				 
				ArrayList<String> descripcionEventos = new ArrayList<>();
				ArrayList<String> imagenEventos = new ArrayList<>();
				eventos = new ArrayList<>();
				ciudadEventos = new ArrayList<>();
				
				while(iter.hasNext()) {
					ciudadEvento = (CiudadEvento) iter.next();
					
					ciudadEventos.add(ciudadEvento);
					eventos.add(ciudadEvento.getEvento());
					
					descripcionEventos.add(ciudadEvento.getEvento().getDescripcion());
					imagenEventos.add(ciudadEvento.getEvento().getPath());
				}
				
				// guardamos la fila que es pulsada
				filaDispo = tablaEventosDisponibles.rowAtPoint(arg0.getPoint());
				
				// si es mayor o igual a 0 mostramos la información y la imagen
				// recuperamos además el evento
				if(filaDispo >= 0) {
					jtaDescripcion.setText(descripcionEventos.get(filaDispo + 1));
					ponerImagenEventoDispo(imagenEventos.get(filaDispo + 1));
					evento = eventos.get(filaDispo + 1);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		scrollTablaEventosDisponibles.setBounds(40, 50, 600, 250);
		jpTabEventosDisponibles.add(scrollTablaEventosDisponibles);		
		
		// titulo descripción
		jlTituloDescripcion = new JLabel("DESCRIPCIÓN");
		jlTituloDescripcion.setBounds(10, 315, 125, 20);
		jlTituloDescripcion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		jpTabEventosDisponibles.add(jlTituloDescripcion);
		
		// texto de la descripción
		jtaDescripcion = new JTextArea();
		jtaDescripcion.setLineWrap(true); // las líneas bajan al completar la fila
		jtaDescripcion.setWrapStyleWord(true); // no corta las palabras
		jtaDescripcion.setEditable(false);
		jtaDescripcion.setBounds(40, 340, 300, 60);
		jtaDescripcion.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		jpTabEventosDisponibles.add(jtaDescripcion);
		
		// imagen del evento
		jlImagenEvento = new JLabel();
		jlImagenEvento.setBounds(399, 300, 240, 140);
        jpTabEventosDisponibles.add(jlImagenEvento);
		
        /* PESTAÑA EVENTOS SUSCRITO */
        
		// título
        jlTituloEventosSus = new JLabel("EVENTOS SUSCRITO");
		jlTituloEventosSus.setBounds(10, 10, 270, 20);
		jlTituloEventosSus.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		jlTituloEventosSus.setForeground(new Color(5, 60, 80));
		jpTabEventosSuscritos.add(jlTituloEventosSus);
        
		// tabla de eventos diponibles
		modeloTablaEventosSuscrito = new ModeloTablaEventosSuscrito(usuario.getIdUsuario());
		tablaEventosSuscrito = new JTable(modeloTablaEventosSuscrito);
		scrollTablaEventosSuscrito = new JScrollPane(tablaEventosSuscrito);
		tablaEventosSuscrito.getColumnModel().getColumn(1).setMaxWidth(80);
		tablaEventosSuscrito.getColumnModel().getColumn(1).setMinWidth(80);
		tablaEventosSuscrito.addMouseListener(new MouseListener() {

			// comportamiento al pulsar sobre la tabla de eventos suscrito por el usuario
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// activamos los botones y el campo para las anotaciones
				jtaValoracion.setEnabled(true);
				botonGuardarValoracion.setEnabled(true);
				botonGuardarValoracion.setBackground(new Color(5, 60, 70));
				
				// mostramos los eventos suscritos
				Iterator iter = Consultas.consultarEventoSuscrito(usuario.getIdUsuario());
				
				ArrayList<String> valoracionEventos = new ArrayList<>();
				ArrayList<String> imagenEventos = new ArrayList<>();
				arrayEventosSuscrito = new ArrayList<>();
				
				while(iter.hasNext()) {
					eventoSuscrito = (EventoSuscrito) iter.next();

					valoracionEventos.add(eventoSuscrito.getValoracion());
					imagenEventos.add(eventoSuscrito.getEvento().getPath());
					arrayEventosSuscrito.add(eventoSuscrito);
				}
					
				// almacenamos la fila sobre la que se ha pusaldo
				filaSuscrito = tablaEventosDisponibles.rowAtPoint(e.getPoint());
				
				// si es mayor o igual a 0 mostramos la imagen y las notas del evento
				if(filaSuscrito >= 0) {
					jtaValoracion.setText(valoracionEventos.get(filaSuscrito));
					ponerImagenEventoSuscrito(imagenEventos.get(filaSuscrito));					
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
			
		});
		scrollTablaEventosSuscrito.setBounds(40, 50, 300, 250);
		jpTabEventosSuscritos.add(scrollTablaEventosSuscrito);		
		
		// imagen del evento
		jlImagenEventoSuscrito = new JLabel();
		jlImagenEventoSuscrito.setBounds(370, 90, 280, 180);
		jpTabEventosSuscritos.add(jlImagenEventoSuscrito);
		
		// titulo valoración
		jlTituloValorarion = new JLabel("NOTAS");
		jlTituloValorarion.setBounds(10, 315, 125, 20);
		jlTituloValorarion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		jpTabEventosSuscritos.add(jlTituloValorarion);
		
		// texto de la descripción
		try {
			MaskFormatter formatter = new MaskFormatter("****************************************"
														+ "****************************************");
			jtaValoracion = new JFormattedTextField(formatter);	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jtaValoracion.setEnabled(false);
		jtaValoracion.setBounds(40, 340, 600, 50);
		jtaValoracion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		jtaValoracion.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		jpTabEventosSuscritos.add(jtaValoracion);
		
		// botones
		botonGuardarValoracion = new JButton("Guardar");
		botonGuardarValoracion.setBounds(520, 405, 120, 25);
		botonGuardarValoracion.setBackground(Color.LIGHT_GRAY);	
		botonGuardarValoracion.setForeground(Color.WHITE);
		botonGuardarValoracion.setEnabled(false);
		botonGuardarValoracion.addActionListener(this);
		jpTabEventosSuscritos.add(botonGuardarValoracion);
        
		/* PESTAÑA USUARIO */	
		
		// título usuario
		jlTituloUsuario = new JLabel("DATOS USUARIO");
		jlTituloUsuario.setBounds(10, 10, 270, 20);
		jlTituloUsuario.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		jlTituloUsuario.setForeground(new Color(5, 60, 80));
		jpTabUsuario.add(jlTituloUsuario);
		
		// panel datos usuario
		panelRegistro = new PanelRegistro();
		panelRegistro.setBounds(20, 50, 450, 250);
		panelRegistro.setBackground(Color.WHITE);
		panelRegistro.setNombre(usuario.getNombre());
		panelRegistro.setApellidos(usuario.getApellidos());
		panelRegistro.setEdad(usuario.getEdad());
		panelRegistro.setTelefono(usuario.getTelefono());
		panelRegistro.setEmail(usuario.getEmail());
		panelRegistro.setPassword(usuario.getPassUsuario());
		jpTabUsuario.add(panelRegistro);
		
		// botones
		botonBorrarUsuario = new JButton("Borrar Usuario");
		botonBorrarUsuario.setBounds(90, 340, 170, 55);
		botonBorrarUsuario.setBackground(Color.RED);	
		botonBorrarUsuario.setForeground(Color.WHITE);
		botonBorrarUsuario.addActionListener(this);
        jpTabUsuario.add(botonBorrarUsuario);
		
		botonGuardarDatosUsuario = new JButton("Actualizar Cambios");
		botonGuardarDatosUsuario.setBounds(410, 340, 170, 55);
		botonGuardarDatosUsuario.setBackground(new Color(5, 60, 70));	
		botonGuardarDatosUsuario.setForeground(Color.WHITE);
		botonGuardarDatosUsuario.addActionListener(this);
        jpTabUsuario.add(botonGuardarDatosUsuario);
		
		// configuración ventana
		setSize(800, 500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// muestra la imagen del evento al pulsar sobre él
	public void ponerImagenEventoDispo(String imagenPath) {
		ImageIcon imagen= new  ImageIcon(getClass().getResource(imagenPath));
        Icon iconImagen = new ImageIcon(imagen.getImage().getScaledInstance(jlImagenEvento.getWidth(),
        											jlImagenEvento.getHeight(), Image.SCALE_DEFAULT));
        jlImagenEvento.setIcon(iconImagen);
	}

	// muestra la imagen del evento al pulsar sobre él
	public void ponerImagenEventoSuscrito(String imagenPath) {
		ImageIcon imagen= new  ImageIcon(getClass().getResource(imagenPath));
        Icon iconImagen = new ImageIcon(imagen.getImage().getScaledInstance(jlImagenEventoSuscrito.getWidth(),
        											jlImagenEventoSuscrito.getHeight(), Image.SCALE_DEFAULT));
        jlImagenEventoSuscrito.setIcon(iconImagen);
	}

	// comportamiento de los botones
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// cerrar sesión nos devuelve a la pantalla de login
		if(e.getSource() == jmiCerrarSesion) {
			setVisible(false);
			new LoginFrame();
			
		// al salir cierra la aplicación	
		} else if(e.getSource() == jmiSalir) {
			System.exit(0);
			
		// muestra el archivo de ayuda	
		} else if(e.getSource() == jmiContenidoAyuda) {
			try {
				String[] rutaAyuda = (getClass().getResource("/help/meventos_ayuda.chm").toString()).split("file:/");
				Runtime.getRuntime().exec("hh.exe " + rutaAyuda[1]);	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		// muestra un JOptionPane con información sobre la aplicación	
		} else if(e.getSource() == jmiAcercaDe) {
			JOptionPane.showMessageDialog(this, "Aplicación realizada para los módulos de:\n\t- Diseño de interfaces"
					+ "\n\t- Acceso a Datos\n\t- Sistemas de gestión empresarial\n\nPor los alumnos:"
					+ "\n\t- Javier Morales\n\t- Luis Morales");
			
		// confirmamo si quiere borrar un evento seleccionado en la BBDD para realizarlo en caso afirmativo	
		} else if(e.getSource() == botonBorrarEvento) {
			if(JOptionPane.showConfirmDialog(this, "Esta seguro que desea borrar el evento: " + evento.getDenominacion(),
														"Borrar evento", JOptionPane.OK_CANCEL_OPTION,
														JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
				Borrados.borrarEventoDisponible(ciudadEventos.get(filaDispo).getIdCiudadEvento());
				modeloTablaEventosDisponibles.removeRow(filaDispo);
				JOptionPane.showMessageDialog(this, "Evento \"" + evento.getDenominacion() + "\" ha sido borrado con exito");
			}
			
		// si un usuario no está ya suscrito lo suscribimos a un evento, si ya está se lo notificamos	
		} else if(e.getSource() == botonSuscribirse) {
			Iterator iter = Consultas.consultarSiEstasuscrito(usuario.getIdUsuario(), evento.getIdEvento());
			
			if(iter.hasNext()) {
				JOptionPane.showMessageDialog(this, "Ya estás suscrito al evento: " + evento.getDenominacion());
			} else {
				Inserciones.insertarEventoSuscrito(usuario, evento);
				JOptionPane.showMessageDialog(this, "Te has suscrito al evento: " + evento.getDenominacion());
				modeloTablaEventosSuscrito.addRow(new Object[] {evento.getDenominacion(), evento.getFecha()});
			}
			
		// guardamos la valoración escrita sobre un evento	
		} else if(e.getSource() == botonGuardarValoracion) {
			Actualizar.actualizarNotaEvento(arrayEventosSuscrito.get(filaSuscrito), jtaValoracion.getText());
			JOptionPane.showMessageDialog(this, "Anotaciones actualizadas para el evento: "
									+ arrayEventosSuscrito.get(filaSuscrito).getEvento().getDenominacion());
			
		// pregunta si realmente desea borrar como usuario, para hacerlo en caso afirmativo	
		} else if(e.getSource() == botonBorrarUsuario) {
			if(JOptionPane.showConfirmDialog(this, "Esta seguro que desea borrar su usuario", "Borrar Usuario", JOptionPane.OK_CANCEL_OPTION,
																JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					Borrados.borrarUsuario(usuario.getIdUsuario());
					setVisible(false);
					new LoginFrame();		
			}
			
		// actualiza los datos de usuario si ningun campo está vaco	
		} else if(e.getSource() == botonGuardarDatosUsuario) {
			String nombreActualizado = panelRegistro.getNombre().trim();
			String apellidosActualizado = panelRegistro.getApellidos().trim();
			String edadActualizada = panelRegistro.getEdad().trim();
			String telefonoActualizado = panelRegistro.getTelefono().trim();
			String emailActualizado = panelRegistro.getEmail().trim();
			String passwordActualizado = panelRegistro.getPassword().trim();
			
			if(nombreActualizado.isEmpty() || apellidosActualizado.isEmpty() || edadActualizada.isEmpty() 
					|| telefonoActualizado.isEmpty() || emailActualizado.isEmpty() || passwordActualizado.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Debes rellenar todos los datos");
			} else {
				Actualizar.actualizarUsuario(usuario, nombreActualizado, apellidosActualizado, edadActualizada,
												telefonoActualizado, emailActualizado, passwordActualizado);
				JOptionPane.showMessageDialog(this, "Usuario: " + usuario.getNombre().trim() + " actualizado");
			}
		}
	}
}
