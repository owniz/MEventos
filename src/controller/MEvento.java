package controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.LoginFrame;

/*
 * Clase principal
 */

public class MEvento {
	public static void main(String[] args) {
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}*/
		new LoginFrame();
	}
}
