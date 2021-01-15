package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import projetLO02.Jeu;

public class ControleurVictory implements Runnable {
	
	private JButton btnChangeVictory;
	private JButton btnSetVictory;
	
	private Jeu jeu;
	private Thread thread;
	
	public ControleurVictory(Jeu jeu, JButton btnChangeVictory, JButton btnSetVictory) {

		this.btnChangeVictory = btnChangeVictory;
		this.btnSetVictory = btnSetVictory;
		
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
