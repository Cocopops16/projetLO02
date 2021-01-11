package Controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Jeu;

import View.MonInterfacePlateau;

public class ControleurDeplacer {
	
	private JButton btnDeplacer;
	private MonInterfacePlateau monInterface;
	
	public ControleurDeplacer(Jeu jeu, JButton bouton, MonInterfacePlateau interfacePlateau) {
		this.btnDeplacer = bouton;
		this.monInterface = interfacePlateau;
		
		btnDeplacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().deplacer(monInterface.getFirstCaractereCaseDeDepart(), monInterface.getSecondCaractereCaseDeDepart(), monInterface.getFirstCaractereCaseDArrivee(), monInterface.getSecondCaractereCaseDArrivee());
			}
		});
	}

}
