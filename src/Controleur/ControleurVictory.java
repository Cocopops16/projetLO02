package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import projetLO02.Jeu;

public class ControleurVictory implements Runnable {
	
	private JFrame framePlateau;
	private JFrame frameVictory;
	private JButton btnChangeVictory;
	private JButton btnSetVictory;
	private JLabel lblPlaceVictoryCard;
	private JLabel lblNomDuJoueur;
	
	private Jeu jeu;
	private Thread thread;
	
	public ControleurVictory(Jeu jeu, JFrame framePlateau, JFrame frameVictory, JButton btnChangeVictory, JButton btnSetVictory, JLabel lblPlaceVictoryCard, JLabel lblNomDuJoueur) {
		
		this.framePlateau = framePlateau;
		this.frameVictory = frameVictory;
		this.btnChangeVictory = btnChangeVictory;
		this.btnSetVictory = btnSetVictory;
		this.lblPlaceVictoryCard = lblPlaceVictoryCard;
		this.lblNomDuJoueur = lblNomDuJoueur;
		
		this.jeu = jeu;
		
		listenButtons();
	}
	
	private void listenButtons() {
		btnChangeVictory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().setVictory();
			}
		});
		
		btnSetVictory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVictory();
			}
		});
	}
	
	private void setVictory() {
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void run() {
		jeu.setPlayersVictory();
	}
}
