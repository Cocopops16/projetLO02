package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Jeu;
/**
 * La classe ControleurVictory r�cup�re les donn�es utilisateurs quant au choix de la victoryCard pour le mode personnalis�.
 * La classe ControleurVictory g�re l'interaction entre le mod�le et la vue
 * @author Fabien Gallet
 * @version 1.0
 *
 */
public class ControleurVictory implements Runnable {
	/**
	 * Les attributs ci dessous permettent � la classe ControleurVictory d'atteindre les objets graphiques de la Vue.
	 */
	private JButton btnChangeVictory;
	private JButton btnSetVictory;
	/**
	 * L'attribut jeu permet � la classe ControleurVictory d�atteindre les objets du Mod�le � partir d'un jeu.
	 */
	private Jeu jeu;
	
	private Thread thread;
	
	/**
	 * Instancie un nouveau controleur Victory.
	 * @param jeu pour le jeu en cours.
	 * @param btnChangeVictory bouton pour changer la carte Victory affich�e.
	 * @param btnSetVictory bouton pour s�l�ctionner la carte Victory affich�e.
	 */
	public ControleurVictory(Jeu jeu, JButton btnChangeVictory, JButton btnSetVictory) {

		this.btnChangeVictory = btnChangeVictory;
		this.btnSetVictory = btnSetVictory;
		
		this.jeu = jeu;
		
		listenButtons();
	}
	
	/**
	 * Permet de r�cup�rer l'action r�alis�e sur les boutons btnChangeVictory et btnSetVictory.
	 * Dans le cas o� une action est r�alis�e sur le bouton btnChangeVictory, il appelle la m�thode setVictory() de la classe Joueur pour changer la carte.
	 * Dans le cas o� une action est r�alis�e sur le bouton btnSetVictory, il appelle la m�thode setVictory() de la classe ControleurVictory.
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
	 * Permet de lancer l'ex�cution du thread avec l'appelle de sa m�thode start()
	 */
	private void setVictory() {
		this.thread = new Thread(this);
		this.thread.start();
	}
	
    /**
     * Permet de contenir les traitements � ex�cuter 
     */
	public void run() {
		jeu.setPlayersVictory();
	}
}
