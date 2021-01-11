package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import projetLO02.InvalidEndOfTurnException;
import projetLO02.Jeu;

public class ControleurFinTour {
	
	private JButton btnFinTour;
	private JLabel lblNomDuJoueur;
	private Jeu jeu;

	public ControleurFinTour( Jeu jeu, JButton bouton, JLabel lblNomDuJoueur) {
		this.btnFinTour = bouton;
		this.jeu = jeu;
		this.lblNomDuJoueur = lblNomDuJoueur;
		
	    passerTourSuivant();
	}
	


    public void passerTourSuivant() {
	 btnFinTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!jeu.checkEndGame()) {
					try {
						jeu.unlockJoueur();
					} catch (InvalidEndOfTurnException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 
				else { 
					jeu.comptagePoints(); 
				}
				String currentPlayer = jeu.getPlayerName(1); //l'idee est d'afficher le nom du joueur et changer à chaque fois que c'est au joueur suivant de jouer. 
				lblNomDuJoueur.setText(currentPlayer); 
			}
	 });
    }

}	
		