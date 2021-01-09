package Controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Jeu;
import projetLO02.Joueur;
import projetLO02.Mode;


public class ControleurPiocher {
	
	private Joueur joueurEnCours;
	private JButton btnPiocher;
	private Jeu jeu;
	
	public ControleurPiocher(Joueur joueur, Jeu jeu, JButton bouton){
		
		this.joueurEnCours = joueur;
		this.btnPiocher = bouton;
		this.jeu = jeu;
		
	if(this.jeu.getMode() == Mode.Classique && this.jeu.getMode() == Mode.Personnalisé) {
		
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurEnCours.piocher();
			}
		});
	}
	
	if(this.jeu.getMode()== Mode.Avancé) {
		
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurEnCours.piocher();
				joueurEnCours.piocher();
				joueurEnCours.piocher();
			}
		});

		
	}
	
		
	}
}
