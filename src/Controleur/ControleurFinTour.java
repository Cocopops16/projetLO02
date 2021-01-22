package Controleur;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import projetLO02.InvalidEndOfTurnException;
import projetLO02.Jeu;
/**
 * La classe ControleurFinTour récupère l'action d'un utilisateur pour passer la main à un autre joueur.
 * La classe ControleurFinTour gère l'interaction entre la Vue et le modèle.
 * @author Fabien Gallet
 * @version 1.0
 *
 */

public class ControleurFinTour implements Runnable {
	
	/**
	 * Les attributs ci dessous permettent à la classe ControleurFinTour d'atteindre les objets graphiques de la Vue. 
	 */
	private JButton btnFinTour;
	private Jeu jeu;
	private JFrame frameFinPartie;
	private JFrame framePlateau;
	private JLabel lblAffichageGagnant;	
	
	private Thread thread;
	/**
	 * L'attribut premier correspond au nom du joueur ayant gagné la partie.
	 */
	private String premier;
	
	/**
	 * Instancie le controleurFinTour.
	 * @param jeu pour le jeu en cours.
	 * @param bouton pour passer la main.
	 * @param framefinPartie interface fin de partie avec l'affichage du nom du gagnant.
	 * @param frameplateau interface plateau.
	 * @param lblAffichageGagnant label affichant le nom du gagnant.
	 */
	public ControleurFinTour( Jeu jeu, JButton bouton, JFrame framefinPartie, JFrame frameplateau, JLabel lblAffichageGagnant) {
		this.btnFinTour = bouton;
		this.jeu = jeu;
		this.frameFinPartie =framefinPartie;
		this.framePlateau = frameplateau;
		this.lblAffichageGagnant = lblAffichageGagnant;
	    passerTourSuivant();
	}
	

    /**
     * Permet de récupérer l'action réalisée par le joueur sur le bouton finTour.
	 * Et permet d'appeler la méthode unlockJoueur() dans la classe jeu si la manche n'est pas terminée.
	 * Dans le cas contraire, si la manche est terminée mais que le nombre de round est inférieur à 4 alors relance une manche.
	 * Sinon, affiche l'interface finPartie avec le nom du gagnant.
	 * @throws InvalidEndOfTurnException.
     */
    public synchronized void passerTourSuivant() {
		btnFinTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!jeu.checkEndGame()) {
					try {
						jeu.unlockJoueur();
					} catch (InvalidEndOfTurnException e1) {
						JLabel message = new JLabel(e1.getMessage());
						message.setFont(new Font("Tahoma", Font.PLAIN, 15));
						JOptionPane.showMessageDialog(null, message);
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
    
    /**
     *  Permet de lancer l'exécution du thread avec l'appelle de sa méthode start().
     */
    public void checkPremier() {
    	this.thread = new Thread(this);
		this.thread.start();
    }

    /**
     * Permet de contenir les traitements à exécuter.
     * Ici, l'affichage du gagnant.
     */
	public void run() {
		this.premier = jeu.getPremier();
		lblAffichageGagnant.setText(premier);
	}

}	
		