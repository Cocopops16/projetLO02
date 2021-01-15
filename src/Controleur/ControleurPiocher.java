package Controleur;

import java.awt.Font;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import projetLO02.InvalidPlayerActionException;
import projetLO02.Jeu;



public class ControleurPiocher {
	
	private JButton btnPiocher;
	
	public ControleurPiocher(Jeu jeu, JButton bouton){
		
		this.btnPiocher = bouton;
		
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jeu.getJoueurEnCours().piocher();
				} catch (InvalidPlayerActionException e1) {
					JLabel message = new JLabel(e1.getMessage());
					message.setFont(new Font("Tahoma", Font.PLAIN, 15));
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});

	}
}
