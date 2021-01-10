package Controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Joueur;


public class ControleurPiocher {
	
	private Joueur joueurEnCours;
	private JButton btnPiocher;
	
	public ControleurPiocher(Joueur joueur, JButton bouton){
		
		this.joueurEnCours = joueur;
		this.btnPiocher = bouton;
		
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurEnCours.piocher();
			}
		});

	}
}
