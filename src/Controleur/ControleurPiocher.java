package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Deck;


public class ControleurPiocher {
	
	private Deck joueurPioche;
	private JButton btnPiocher;
	
	public ControleurPiocher(Deck pioche, JButton bouton){
	joueurPioche = pioche;
	btnPiocher = bouton;
	
	btnPiocher.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			joueurPioche.giveCard();
		}
	});
}
	
}
