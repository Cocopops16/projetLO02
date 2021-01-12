package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.InvalidEndOfTurnException;
import projetLO02.Jeu;

public class ControleurFinTour {
	
	private JButton btnFinTour;
	private Jeu jeu;

	public ControleurFinTour( Jeu jeu, JButton bouton) {
		this.btnFinTour = bouton;
		this.jeu = jeu;
		
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
			}
	 });
    }

}	
		