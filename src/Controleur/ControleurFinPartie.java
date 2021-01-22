package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * La classe ControleurFinPartie récupère l'action d'un utilisateur pour fermer l'interface graphique.
 * La classe ControleurFinPartie gère l'interaction entre la Vue et le modèle.
 * @author Fabien Gallet
 * @version 1.0
 *
 */

public class ControleurFinPartie {
	
	/**
	 * Les attributs ci dessous permettent à la classe ControleurFinPartie d'atteindre les objets graphiques de la Vue.
	 */
	private JButton btnFermer;
	private JFrame frameFinPartie;
	
	/**
	 * Instancie un nouveau controleurFinPartie.
	 * Récupére l'action réalisée par l'utilisateur sur le bouton btnFermer pour fermer l'interface. 
	 * @param boutonFermer pour fermer l'interface graphique.
	 * @param frameFinpartie interface graphique de fin de partie.
	 */
	public ControleurFinPartie(JButton boutonFermer, JFrame frameFinpartie) {
		
		
	    this.btnFermer= boutonFermer;
		this.frameFinPartie = frameFinpartie;
			
		
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameFinPartie.setVisible(false); 
			}
		});
		
	}
	
}