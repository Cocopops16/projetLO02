package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Joueur;


public class ControleurPiocher {
	
	private Joueur joueurPioche;
	private JButton btnPiocher;
	
	public ControleurPiocher(Joueur pioche, JButton bouton) {
		joueurPioche = pioche;
		btnPiocher = bouton;
		
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPioche.piocher(Card);
			}
		});
	}
	
}
