package Controleur;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import projetLO02.InvalidModeException;
import projetLO02.InvalidNbrOfPlayersException;
import projetLO02.Jeu;

public class ControleurMenu implements Runnable {

	private JTextPane textPane;
	private JButton btnSaveJoueur;
	private JButton btnAddIA;
	private JRadioButton rdbtnModeClassique;
	private JRadioButton rdbtnModeAvance;
	private JRadioButton rdbtnModePerso;
	private JButton btnLancerPartie;
	
	private Jeu jeu;
	private Thread thread;
	
	private int nbrPlayers;
	
	public ControleurMenu(Jeu jeu, JTextPane textPane, JButton btnSaveJoueur, JButton btnAddIA, JRadioButton rdbtnModeClassique, JRadioButton rdbtnModeAvance, JRadioButton rdbtnModePerso, JButton btnLancerPartie) {
		this.textPane = textPane;
		this.btnSaveJoueur = btnSaveJoueur;
		this.btnAddIA = btnAddIA;
		this.rdbtnModeClassique = rdbtnModeClassique;
		this.rdbtnModeAvance = rdbtnModeAvance;
		this.rdbtnModePerso = rdbtnModePerso;
		this.btnLancerPartie = btnLancerPartie;
		
		this.jeu = jeu;
		
		this.nbrPlayers = 0;
		
		listenButtons();
	}
	
	private void listenButtons() {		
		btnSaveJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textPane.getText().isEmpty()) {
					JLabel message = new JLabel("Entrez un nom!");
					message.setFont(new Font("Tahoma", Font.PLAIN, 15));
					JOptionPane.showMessageDialog(null, message);
				}
				else { 
					String name = textPane.getText().trim(); //copie le texte présent dans le Jtext
					try {
						jeu.addJoueur(name);
						nbrPlayers++;
					} catch (InvalidNbrOfPlayersException e1) {
						JLabel message = new JLabel(e1.getMessage());
						message.setFont(new Font("Tahoma", Font.PLAIN, 15));
						JOptionPane.showMessageDialog(null, message);
						nbrPlayers--;
					}
				
					if(jeu.getPlayerName(nbrPlayers).equals(name)) {
						JLabel message = new JLabel("Le nom a été enregistré avec succès !");
						message.setFont(new Font("Tahoma", Font.PLAIN, 15));
						JOptionPane.showMessageDialog(null, message);
					}
					textPane.setText(""); //réinitialise la zone de texte 
				}
			}
		});
		
		btnAddIA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jeu.addIA();
					nbrPlayers++;
				} catch (InvalidNbrOfPlayersException e1) {
					JLabel message = new JLabel(e1.getMessage());
					message.setFont(new Font("Tahoma", Font.PLAIN, 15));
					JOptionPane.showMessageDialog(null, message);
					nbrPlayers--;
				}
			}
		});
			
		btnLancerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if(jeu.getMode() == null) {
					if(rdbtnModeClassique.isSelected()) {
						jeu.setMode(1);
					}
					else if(rdbtnModeAvance.isSelected()) {
						jeu.setMode(2);
					}
					else if(rdbtnModePerso.isSelected()) {
						jeu.setMode(3);
					} 
				}
				else {
					JLabel message = new JLabel("Mode déjà paramétré via la console!");
					message.setFont(new Font("Tahoma", Font.PLAIN, 15));
					JOptionPane.showMessageDialog(null, message);
				}
				lancerPartie();
			}
		});
	}
		
	private void lancerPartie() {
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void run() {	
		try {
			this.jeu.start();
		} catch (InvalidModeException e) {
			JLabel message = new JLabel(e.getMessage());
			message.setFont(new Font("Tahoma", Font.PLAIN, 15));
			JOptionPane.showMessageDialog(null, message);
		} catch (InvalidNbrOfPlayersException e) {
			JLabel message = new JLabel(e.getMessage());
			message.setFont(new Font("Tahoma", Font.PLAIN, 15));
			JOptionPane.showMessageDialog(null, message);
		}
	}
}
