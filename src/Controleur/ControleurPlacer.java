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

public class ControleurPlacer {
//public class ControleurPlacer implements ActionListener {
	
	private JToggleButton tglbtnPlacerDeplacer;
	private JRadioButton rdbtnCardNum1;
	private JRadioButton rdbtnCardNum2;
	private JRadioButton rdbtnCardNum3;
	private Map<String,JButton> cardPlateauButtons;
	private Jeu jeu;
	
	
	public ControleurPlacer(Jeu j, JToggleButton tglbtnPlacerDeplacer, Map<String,JButton> cardPlateauButtons, JRadioButton rdbtnCardNumero1, JRadioButton rdbtnCardNumero2, JRadioButton rdbtnCardNumero3) {
		this.tglbtnPlacerDeplacer = tglbtnPlacerDeplacer;
		this.rdbtnCardNum1 = rdbtnCardNumero1;
		this.rdbtnCardNum2 = rdbtnCardNumero2;
		this.rdbtnCardNum3 = rdbtnCardNumero3;
		this.cardPlateauButtons = cardPlateauButtons;
		this.jeu = j;
		
		listenButtons();
	}	
	
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