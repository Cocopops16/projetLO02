package Controleur;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import projetLO02.InvalidChosenCardException;
import projetLO02.InvalidPlayerActionException;
import projetLO02.Jeu;
/**
 * La classe ControleurPlacer récupère les données utilisateurs lors du choix du placement de la carte.
 * La classe ControleurPlacer gère l'interaction entre la Vue et le modèle.
 * @author Fabien Gallet
 * @version 1.0
 *
 */
public class ControleurPlacer {

	/**
	 * Les attributs ci dessous permettent à la classe ControleurPlacer d'atteindre les objets graphiques de la Vue. 
	 */
	private JToggleButton tglbtnPlacerDeplacer;
	private JRadioButton rdbtnCardNum1;
	private JRadioButton rdbtnCardNum2;
	private JRadioButton rdbtnCardNum3;
	private Map<String,JButton> cardPlateauButtons;
	/**
	 * L'attribut jeu permet à la classe ControleurPlacer d’atteindre les objets du Modèle à partir d'un jeu.
   	 */
	private Jeu jeu;
	
	/**
	 * Instancie un nouveau Controleur placer.
	 * @param j pour le jeuen cours.
	 * @param tglbtnPlacerDeplacer bouton à deux états placer/déplacer.
	 * @param cardPlateauButtons ensemble des boutons du plateau contenu dans la map.
	 * @param rdbtnCardNumero1 bouton 1 pour séléctionner une carte dans la main du joueur. Deux boutons ne peuvent pas être séléctionnés en même temps.
	 * @param rdbtnCardNumero2 bouton 2 pour sélectionner une carte dans la main du joueur. Deux boutons ne peuvent pas être séléctionnés en même temps.
	 * @param rdbtnCardNumero3 bouton 2 pour sélectionner une carte dans la main du joueur. Deux boutons ne peuvent pas être séléctionnés en même temps.
	 */
	public ControleurPlacer(Jeu j, JToggleButton tglbtnPlacerDeplacer, Map<String,JButton> cardPlateauButtons, JRadioButton rdbtnCardNumero1, JRadioButton rdbtnCardNumero2, JRadioButton rdbtnCardNumero3) {
		this.tglbtnPlacerDeplacer = tglbtnPlacerDeplacer;
		this.rdbtnCardNum1 = rdbtnCardNumero1;
		this.rdbtnCardNum2 = rdbtnCardNumero2;
		this.rdbtnCardNum3 = rdbtnCardNumero3;
		this.cardPlateauButtons = cardPlateauButtons;
		this.jeu = j;
		
		listenButtons();
	}	
	
	/**
	 * Permet de récupérer l'action réalisée sur le bouton placer/déplacer.
	 * Appelle la méthode placer() dans la classe Joueur en y incluant en parametre la carte séléctionnée par le joueur. 
	 * 
	 */
	private void listenButtons() {
		for(Iterator<String> it = cardPlateauButtons.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			this.cardPlateauButtons.get(key).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnCardNum1.isSelected()&&(!tglbtnPlacerDeplacer.isSelected())) {
						try {
							jeu.getJoueurEnCours().placer(jeu.getJoueurEnCours().getHand().getCard(0), key.charAt(0), Integer.parseInt(key.substring(1, 2)) );
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidPlayerActionException e1) {
							JLabel message = new JLabel(e1.getMessage());
							message.setFont(new Font("Tahoma", Font.PLAIN, 15));
							JOptionPane.showMessageDialog(null, message);
						} catch (InvalidChosenCardException e1) {
							JLabel message = new JLabel(e1.getMessage());
							message.setFont(new Font("Tahoma", Font.PLAIN, 15));
							JOptionPane.showMessageDialog(null, message);
						}
					}
					else if(rdbtnCardNum2.isSelected()&&(!tglbtnPlacerDeplacer.isSelected())) {
						try {
							jeu.getJoueurEnCours().placer(jeu.getJoueurEnCours().getHand().getCard(1), key.charAt(0), Integer.parseInt(key.substring(1, 2))) ;
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidPlayerActionException e1) {
							JLabel message = new JLabel(e1.getMessage());
							message.setFont(new Font("Tahoma", Font.PLAIN, 15));
							JOptionPane.showMessageDialog(null, message);
						} catch (InvalidChosenCardException e1) {
							JLabel message = new JLabel(e1.getMessage());
							message.setFont(new Font("Tahoma", Font.PLAIN, 15));
							JOptionPane.showMessageDialog(null, message);
						}
					}
					else if(rdbtnCardNum3.isSelected()&&(!tglbtnPlacerDeplacer.isSelected())) {
						try {
							jeu.getJoueurEnCours().placer(jeu.getJoueurEnCours().getHand().getCard(2), key.charAt(0), Integer.parseInt(key.substring(1, 2)) );
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidPlayerActionException e1) {
							JLabel message = new JLabel(e1.getMessage());
							message.setFont(new Font("Tahoma", Font.PLAIN, 15));
							JOptionPane.showMessageDialog(null, message);
						} catch (InvalidChosenCardException e1) {
							JLabel message = new JLabel(e1.getMessage());
							message.setFont(new Font("Tahoma", Font.PLAIN, 15));
							JOptionPane.showMessageDialog(null, message);
						}
					}
				}     	
			});
		}
		
	}
}