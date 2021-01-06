package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Joueur;


public class ControleurPiocher {
	
	public ControleurPiocher(Joueur joueurPioche, JButton bouton){
	
		bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPioche.piocher();
			}
		});
	}
}
