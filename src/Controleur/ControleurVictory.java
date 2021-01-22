package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Jeu;
/**
 * La classe ControleurVictory récupère les données utilisateurs quant au choix de la victoryCard pour le mode personnalisé.
 * La classe ControleurVictory gère l'interaction entre le modèle et la vue
 * @author Fabien Gallet
 * @version 1.0
 *
 */
public class ControleurVictory implements Runnable {
	/**
	 * Les attributs ci dessous permettent à la classe ControleurVictory d'atteindre les objets graphiques de la Vue.
	 */
	private JButton btnChangeVictory;
	private JButton btnSetVictory;
	/**
	 * L'attribut jeu permet à la classe ControleurVictory d’atteindre les objets du Modèle à partir d'un jeu.
	 */
	private Jeu jeu;
	
	private Thread thread;
	
	/**
	 * Instancie un nouveau controleur Victory.
	 * @param jeu pour le jeu en cours.
	 * @param btnChangeVictory bouton pour changer la carte Victory affichée.
	 * @param btnSetVictory bouton pour séléctionner la carte Victory affichée.
	 */
	public ControleurVictory(Jeu jeu, JButton btnChangeVictory, JButton btnSetVictory) {

		this.btnChangeVictory = btnChangeVictory;
		this.btnSetVictory = btnSetVictory;
		
		this.jeu = jeu;
		
		listenButtons();
	}
	
	/**
	 * Permet de récupérer l'action réalisée sur les boutons btnChangeVictory et btnSetVictory.
	 * Dans le cas où une action est réalisée sur le bouton btnChangeVictory, il appelle la méthode setVictory() de la classe Joueur pour changer la carte.
	 * Dans le cas où une action est réalisée sur le bouton btnSetVictory, il appelle la méthode setVictory() de la classe ControleurVictory.
	 */
	private void listenButtons() {
		btnChangeVictory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().setVictory();
			}
		});
		
		btnSetVictory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVictory();
			}
		});
	}
	
	/**
	 * Permet de lancer l'exécution du thread avec l'appelle de sa méthode start()
	 */
	private void setVictory() {
		this.thread = new Thread(this);
		this.thread.start();
	}
	
    /**
     * Permet de contenir les traitements à exécuter 
     */
	public void run() {
		jeu.setPlayersVictory();
	}
}
