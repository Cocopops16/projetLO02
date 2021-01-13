package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import projetLO02.InvalidEndOfTurnException;
import projetLO02.Jeu;

public class ControleurFinTour {
	
	private JButton btnFinTour;
	private Jeu jeu;
	private JFrame frameFinPartie;
	private JFrame framePlateau;
	private JLabel lblAffichageGagnant;	
	
	public ControleurFinTour( Jeu jeu, JButton bouton, JFrame framefinPartie, JFrame frameplateau, JLabel lblAffichageGagnant) {
		this.btnFinTour = bouton;
		this.jeu = jeu;
		this.frameFinPartie =framefinPartie;
		this.framePlateau = frameplateau;
		this.lblAffichageGagnant = lblAffichageGagnant;
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
					frameFinPartie.setVisible(true);
					framePlateau.setVisible(false);
					lblAffichageGagnant.setText(jeu.comptagePoints());
				}
			}
	 });
    }

}	
		