package Controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import projetLO02.Jeu;


public class ControleurDeplacer {
	
	private JToggleButton tglbtnPlacerDeplacer;
	private JLabel lblDe;
	private JLabel lblPositionOrigine;
	private JLabel lblVers;
	private JLabel lblPositionArrivee;
	private Map<String,JButton> cardPlateauButtons;
	private Jeu jeu;
		
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
	
	private void listenButtons() {
		this.tglbtnPlacerDeplacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPositionOrigine.setText(null);
				lblPositionArrivee.setText(null);
				if(tglbtnPlacerDeplacer.isSelected()) {
					tglbtnPlacerDeplacer.setText("Déplacer");
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
						jeu.getJoueurEnCours().deplacer(lblPositionOrigine.getText().charAt(0), Integer.parseInt(lblPositionOrigine.getText().substring(1, 2)), lblPositionArrivee.getText().charAt(0), Integer.parseInt(lblPositionArrivee.getText().substring(1, 2)));
					}
				}     	
			});
		}
	}

}
