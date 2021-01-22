package Controleur;

import java.awt.Font;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import projetLO02.InvalidPlayerActionException;
import projetLO02.Jeu;
import projetLO02.NoCardsAvailableException;
/**
 * 
 * La classe ControleurPiocher r�cup�re l'action r�alis�e pour piocher une carte.
 * La classe ControleurPiocher g�re l'interaction entre la Vue et le mod�le.
 * @author Fabien Gallet
 * @author Corentin R�ault
 * @version 1.0
 *
 */
public class ControleurPiocher {
	
	/**
	 * L'attribut btnPiocher permet � la classe ControleurPiocher d'atteindre l'objet graphique de la Vue.
	 */
	private JButton btnPiocher;
	
	/**
	 * Instancie un nouveau controleur.
	 * Permet de r�cup�rer l'action r�alis�e sur le bouton btnPiocher et de le faire parvenir au mod�le en appelant la m�thode piocher() de la classe Joueur.
	 * @param jeu pour le jeu en cours.
	 * @param bouton pour le bouton piocher.
	 */
	
	public ControleurPiocher(Jeu jeu, JButton bouton){
		
		this.btnPiocher = bouton;
		
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jeu.getJoueurEnCours().piocher();
				} catch (InvalidPlayerActionException | NoCardsAvailableException e1) {
					JLabel message = new JLabel(e1.getMessage());
					message.setFont(new Font("Tahoma", Font.PLAIN, 15));
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});

	}
}
