package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Joueur;
import View.MonInterfacePlateau;

public class ControleurDeplacer {
	
	private JButton btnDeplacer;
	private Joueur joueurEnCours;
	private MonInterfacePlateau monInterface;
	
	public ControleurDeplacer(Joueur joueur, JButton bouton, MonInterfacePlateau interfacePlateau) {
		this.btnDeplacer = bouton;
		this.joueurEnCours = joueur;
		this.monInterface = interfacePlateau;
		
		btnDeplacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurEnCours.deplacer(monInterface.getFirstCaractereCaseDeDepart(), monInterface.getSecondCaractereCaseDeDepart(), monInterface.getFirstCaractereCaseDArrivee(), monInterface.getSecondCaractereCaseDArrivee());
			}
		});
	}

}
