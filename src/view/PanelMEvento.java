package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import controller.Consultas;
import models.CiudadEvento;
import models.ModeloTablaEventos;

public class PanelMEvento extends JFrame implements ActionListener, MouseListener {
	private JPanel jpGeneral, jpTabEventosDisponibles, jpTabEventosSuscritos, jpTabUsuario;
	private JMenuBar BarraMenu;
	private JMenu menuArchivo, menuAyuda;
	private JMenuItem jmiCerrarSesion, jmiSalir, jmiContenidoAyuda, jmiAcercaDe;
	private JTabbedPane jtpTabs;
	private JLabel jlTitulo, jlTituloDescripcion;
	private JTextArea jtaDescripcion;
	private ModeloTablaEventos modeloTablaEventosDisponibles;
	private JTable tablaEventosDisponibles;
	private JScrollPane scrollTablaEventosDisponibles;
	
	public PanelMEvento() {
		super("MEvento");
		iniciarGUI();
	}
	
	public void iniciarGUI() {		
		jpGeneral = new JPanel();
		
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
		
		// Paneles
		jpTabEventosDisponibles = new JPanel();
		jpTabEventosDisponibles.setLayout(null);
		jpTabEventosDisponibles.setBackground(Color.WHITE);
		jpTabEventosSuscritos = new JPanel();
		jpTabEventosDisponibles.setLayout(null);
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
				
		// panel de eventos disponibles
		jlTitulo = new JLabel("EVENTOS DISPONIBLES");
		jlTitulo.setBounds(10, 10, 270, 20);
		jlTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		jlTitulo.setForeground(new Color(5, 60, 80));
		jpTabEventosDisponibles.add(jlTitulo);
		
		// tabla de eventos diponibles
		modeloTablaEventosDisponibles = new ModeloTablaEventos();
		tablaEventosDisponibles = new JTable(modeloTablaEventosDisponibles);
		scrollTablaEventosDisponibles = new JScrollPane(tablaEventosDisponibles);
		tablaEventosDisponibles.getColumnModel().getColumn(1).setMaxWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(1).setMinWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(2).setMaxWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(2).setMinWidth(80);
		tablaEventosDisponibles.getColumnModel().getColumn(3).setMaxWidth(100);
		tablaEventosDisponibles.getColumnModel().getColumn(3).setMinWidth(100);
		tablaEventosDisponibles.addMouseListener(this);
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
		jtaDescripcion.setEditable(false); // no es editable
		jtaDescripcion.setBounds(40, 340, 300, 60);
		jtaDescripcion.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		jpTabEventosDisponibles.add(jtaDescripcion);
		
		// configuracón ventana
		setSize(800, 500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jmiCerrarSesion) {
			setVisible(false);
			new LoginFrame();
		} else if(e.getSource() == jmiSalir) {
			System.exit(0);
		} else if(e.getSource() == jmiContenidoAyuda) {
			
		} else if(e.getSource() == jmiAcercaDe) {
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Iterator iter = Consultas.consultarCiudadEvento();
		
		ArrayList<String> descripciones = new ArrayList<>();
		
		while(iter.hasNext()) {
			CiudadEvento ciudadEvento = (CiudadEvento) iter.next();
			
			descripciones.add(ciudadEvento.getEvento().getDescripcion());
		}
		
		int filaDispo = tablaEventosDisponibles.rowAtPoint(arg0.getPoint());
		int columnaDispo = tablaEventosDisponibles.columnAtPoint(arg0.getPoint());
		
		if(filaDispo >= 0 && columnaDispo >= 0) {
			jtaDescripcion.setText(descripciones.get(filaDispo));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
