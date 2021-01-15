package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import projetLO02.InvalidEndOfTurnException;
import projetLO02.Jeu;

public class ControleurFinTour implements Runnable {
	
	private JButton btnFinTour;
	private Jeu jeu;
	private JFrame frameFinPartie;
	private JFrame framePlateau;
	private JLabel lblAffichageGagnant;	
	
	private Thread thread;
	private String premier;
	
	public ControleurFinTour( Jeu jeu, JButton bouton, JFrame framefinPartie, JFrame frameplateau, JLabel lblAffichageGagnant) {
		this.btnFinTour = bouton;
		this.jeu = jeu;
		this.frameFinPartie =framefinPartie;
		this.framePlateau = frameplateau;
		this.lblAffichageGagnant = lblAffichageGagnant;
	    passerTourSuivant();
	}
	


    public synchronized void passerTourSuivant() {
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
				else if((jeu.checkEndGame())&&(jeu.getNbrRounds()<4)) {
					jeu.setNextRound();
				}
				else { 
					frameFinPartie.setVisible(true);
					framePlateau.setVisible(false);
					checkPremier();	
				}
			}
		});
    }
    
    public void checkPremier() {
    	this.thread = new Thread(this);
		this.thread.start();
    }

	public void run() {
		this.premier = jeu.getPremier();
		lblAffichageGagnant.setText(premier);
	}

}	
		