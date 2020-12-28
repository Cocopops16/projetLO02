package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Joueur;

public class ControleurPlacer {
	
	private Joueur joueurPlace;
	private JButton btnPlacer;
	
	public ControleurPlacer(Joueur place, JButton bouton) {
		joueurPlace = place; 
		btnPlacer = bouton;
		
		btnPlacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(Card);
			}
		});
		
		
	}

}
