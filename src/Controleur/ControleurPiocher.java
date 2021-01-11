package Controleur;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Jeu;



public class ControleurPiocher {
	
	private JButton btnPiocher;
	
	public ControleurPiocher(Jeu jeu, JButton bouton){
		
		this.btnPiocher = bouton;
		
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().piocher();
			}
		});

	}
}
