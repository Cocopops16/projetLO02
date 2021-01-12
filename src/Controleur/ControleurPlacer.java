package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import projetLO02.Jeu;
import projetLO02.Joueur;

public class ControleurPlacer {
//public class ControleurPlacer implements ActionListener {
	
	private JRadioButton rdbtnCardNum1;
	private JRadioButton rdbtnCardNum2;
	private JRadioButton rdbtnCardNum3;
	private JLabel lblPlaceCartePiocheeNumero1;
	private JLabel lblPlaceCartePiocheeNumero2;
	private JLabel lblPlaceCartePiocheeNumero3;
	private Map<String,JButton> cardPlateauButtons;
	private Jeu jeu;
	
	
	public ControleurPlacer(Jeu j, Map<String,JButton> cardPlateauButtons, JRadioButton rdbtnCardNumero1, JRadioButton rdbtnCardNumero2, JRadioButton rdbtnCardNumero3, JLabel lblPlaceCartePiocheeNumero1, JLabel lblPlaceCartePiocheeNumero2, JLabel lblPlaceCartePiocheeNumero3) {
		
		this.rdbtnCardNum1 = rdbtnCardNumero1;
		this.rdbtnCardNum2 = rdbtnCardNumero2;
		this.rdbtnCardNum3 = rdbtnCardNumero3;
		this.lblPlaceCartePiocheeNumero1 = lblPlaceCartePiocheeNumero1;
		this.lblPlaceCartePiocheeNumero2 = lblPlaceCartePiocheeNumero2;
		this.lblPlaceCartePiocheeNumero3 = lblPlaceCartePiocheeNumero3;
		this.cardPlateauButtons = cardPlateauButtons;
		this.jeu = j;
		
		listenButtons();
	}	
	
	private void listenButtons() {
		for(Iterator<String> it = cardPlateauButtons.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			this.cardPlateauButtons.get(key).addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent e) {
					if(rdbtnCardNum1.isSelected()) {
						jeu.getJoueurEnCours().placer(jeu.getJoueurEnCours().getHand().getCard(0), key.charAt(0), Integer.parseInt(key.substring(1, 2)) );
						lblPlaceCartePiocheeNumero1.setIcon(null);
					}
					else if(rdbtnCardNum2.isSelected()) {
						jeu.getJoueurEnCours().placer(jeu.getJoueurEnCours().getHand().getCard(1), key.charAt(0), Integer.parseInt(key.substring(1, 2))) ;
						lblPlaceCartePiocheeNumero2.setIcon(null);
					}
					else if(rdbtnCardNum3.isSelected()) {
						jeu.getJoueurEnCours().placer(jeu.getJoueurEnCours().getHand().getCard(2), key.charAt(0), Integer.parseInt(key.substring(1, 2)) ); 
						lblPlaceCartePiocheeNumero3.setIcon(null);
					}
				  }     	
			});
		}
		
	}
}