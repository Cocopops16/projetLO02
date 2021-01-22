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
 * La classe ControleurPlacer r�cup�re les donn�es utilisateurs lors du choix du placement de la carte.
 * La classe ControleurPlacer g�re l'interaction entre la Vue et le mod�le.
 * @author Fabien Gallet
 * @version 1.0
 *
 */
public class ControleurPlacer {

	/**
	 * Les attributs ci dessous permettent � la classe ControleurPlacer d'atteindre les objets graphiques de la Vue. 
	 */
	private JToggleButton tglbtnPlacerDeplacer;
	private JRadioButton rdbtnCardNum1;
	private JRadioButton rdbtnCardNum2;
	private JRadioButton rdbtnCardNum3;
	private Map<String,JButton> cardPlateauButtons;
	/**
	 * L'attribut jeu permet � la classe ControleurPlacer d�atteindre les objets du Mod�le � partir d'un jeu.
   	 */
	private Jeu jeu;
	
	/**
	 * Instancie un nouveau Controleur placer.
	 * @param j pour le jeuen cours.
	 * @param tglbtnPlacerDeplacer bouton � deux �tats placer/d�placer.
	 * @param cardPlateauButtons ensemble des boutons du plateau contenu dans la map.
	 * @param rdbtnCardNumero1 bouton 1 pour s�l�ctionner une carte dans la main du joueur. Deux boutons ne peuvent pas �tre s�l�ctionn�s en m�me temps.
	 * @param rdbtnCardNumero2 bouton 2 pour s�lectionner une carte dans la main du joueur. Deux boutons ne peuvent pas �tre s�l�ctionn�s en m�me temps.
	 * @param rdbtnCardNumero3 bouton 2 pour s�lectionner une carte dans la main du joueur. Deux boutons ne peuvent pas �tre s�l�ctionn�s en m�me temps.
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
	 * Permet de r�cup�rer l'action r�alis�e sur le bouton placer/d�placer.
	 * Appelle la m�thode placer() dans la classe Joueur en y incluant en parametre la carte s�l�ctionn�e par le joueur. 
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