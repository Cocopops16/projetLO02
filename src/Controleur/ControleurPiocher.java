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
 * La classe ControleurPiocher récupère l'action réalisée pour piocher une carte.
 * La classe ControleurPiocher gère l'interaction entre la Vue et le modèle.
 * @author Fabien Gallet
 * @author Corentin Réault
 * @version 1.0
 *
 */
public class ControleurPiocher {
	
	/**
	 * L'attribut btnPiocher permet à la classe ControleurPiocher d'atteindre l'objet graphique de la Vue.
	 */
	private JButton btnPiocher;
	
	/**
	 * Instancie un nouveau controleur.
	 * Permet de récupérer l'action réalisée sur le bouton btnPiocher et de le faire parvenir au modèle en appelant la méthode piocher() de la classe Joueur.
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
