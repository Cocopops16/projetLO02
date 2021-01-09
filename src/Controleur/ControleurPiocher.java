package Controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Jeu;
import projetLO02.Joueur;
import projetLO02.Mode;


public class ControleurPiocher {
	
	private Joueur joueurPioche;
	private JButton btnPiocher;
	private Jeu jeu;
	
	public ControleurPiocher(Joueur joueur, Jeu jeu, JButton bouton){
		
		this.joueurPioche = joueur;
		this.btnPiocher = bouton;
		this.jeu = jeu;
		
	if(this.jeu.getMode() == Mode.Classique && this.jeu.getMode() == Mode.Personnalis�) {
		
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPioche.piocher();
			}
		});
	}
	
	if(this.jeu.getMode()== Mode.Avanc�) {
		
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPioche.piocher();
				joueurPioche.piocher();
				joueurPioche.piocher();
			}
		});

		
	}
	
		
	}
}
