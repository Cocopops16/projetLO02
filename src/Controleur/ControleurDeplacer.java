package Controleur;

import java.awt.Font;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import projetLO02.InvalidPlayerActionException;
import projetLO02.Jeu;

/**
 * La classe ControleurDeplacer r�cup�re les donn�es utilisateurs lors du choix du d�placement de la carte.
 * La classe ControleurDeplacer g�re l'interaction entre la Vue et le mod�le.
 * @author Corentin R�ault
 * @author Fabien Gallet
 * @version 1.0
 *
 */

public class ControleurDeplacer {
	
	/**
	 * Les attributs ci dessous permettent � la classe ControleurDeplacer d'atteindre les objets graphiques de la Vue.
	 */
	private JToggleButton tglbtnPlacerDeplacer;
	private JLabel lblDe;
	private JLabel lblPositionOrigine;
	private JLabel lblVers;
	private JLabel lblPositionArrivee;
	private Map<String,JButton> cardPlateauButtons;
	
	/**
	 * L'attribut jeu permet � la classe ControleurDeplacer d�atteindre les objets du Mod�le � partir d'un jeu.
   	 */
	private Jeu jeu;
		
	/**
	 * Instancie un nouveau ControleurDeplacer
	 * @param jeu pour le jeu en cours.
	 * @param tglbtnPlacerDeplacer bouton � deux �tats placer/d�placer
	 * @param cardPlateauButtons  ensemble des boutons du plateau contenu dans la map.
	 * @param lblDe label affichant le mot "De".
	 * @param lblPositionOrigine label affichant la case d'origine.
	 * @param lblVers label affichant le mot "Vers".
	 * @param lblPositionArrivee label affichant la case d'arriv�e.
	 */
	public ControleurDeplacer(Jeu jeu, JToggleButton tglbtnPlacerDeplacer, Map<String,JButton> cardPlateauButtons, JLabel lblDe, JLabel lblPositionOrigine, JLabel lblVers, JLabel lblPositionArrivee) {
		
		this.tglbtnPlacerDeplacer = tglbtnPlacerDeplacer;
		this.lblDe = lblDe;
		this.lblPositionOrigine = lblPositionOrigine;
		this.lblVers = lblVers;
		this.lblPositionArrivee = lblPositionArrivee;
		this.cardPlateauButtons = cardPlateauButtons;
		this.jeu = jeu;
				
		listenButtons();
	}
	
	/**
	 * Permet de r�cup�rer l'action r�alis�e sur le bouton placer/d�placer.
	 * Appelle la m�thode d�placer() dans la classe Joueur en y incluant en parametre la carte s�l�ctionn�e par le joueur ainsi que les position d'origine et d'arriv�e. 
	 * @throws NumberFormatException
	 * @throws InvalidPlayerActionException
	 */
	private void listenButtons() {
		this.tglbtnPlacerDeplacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPositionOrigine.setText(null);
				lblPositionArrivee.setText(null);
				if(tglbtnPlacerDeplacer.isSelected()) {
					tglbtnPlacerDeplacer.setText("D�placer");
					lblDe.setEnabled(true);
					lblPositionOrigine.setEnabled(true);
					lblVers.setEnabled(true);
					lblPositionArrivee.setEnabled(true);
				}
				else {
					tglbtnPlacerDeplacer.setText("Placer");
					lblDe.setEnabled(false);
					lblPositionOrigine.setEnabled(false);
					lblVers.setEnabled(false);
					lblPositionArrivee.setEnabled(false);
				}
			}     	
		});
		
		for(Iterator<String> it = cardPlateauButtons.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			this.cardPlateauButtons.get(key).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if((lblPositionOrigine.getText()==null)&&tglbtnPlacerDeplacer.isSelected()) {
						lblPositionOrigine.setText(key);
					}
					else if((lblPositionArrivee.getText()==null)&&tglbtnPlacerDeplacer.isSelected()) {
						lblPositionArrivee.setText(key);
					}
					if((lblPositionOrigine.getText()!=null)&&(lblPositionArrivee.getText()!=null)&&tglbtnPlacerDeplacer.isSelected()) {
						try {
							jeu.getJoueurEnCours().deplacer(lblPositionOrigine.getText().charAt(0), Integer.parseInt(lblPositionOrigine.getText().substring(1, 2)), lblPositionArrivee.getText().charAt(0), Integer.parseInt(lblPositionArrivee.getText().substring(1, 2)));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidPlayerActionException e1) {
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
