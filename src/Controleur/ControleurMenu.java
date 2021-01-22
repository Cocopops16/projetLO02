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

/**
 * La classe ControleurMenu récupère les actions réalisées sur l'interface Menu.
 * La classe ControleurMenu gère l'interaction entre la Vue et le modèle.
 * @author Fabien Gallet
 * @author Corentin Réault
 * @version 1.0
 *
 */

public class ControleurMenu implements Runnable {

	/**
	 *  Les attributs ci dessous permettent à la classe ControleurMenu d'atteindre les objets graphiques de la Vue.
	 */
	private JTextPane textPane;
	private JButton btnSaveJoueur;
	private JButton btnAddIA;
	private JRadioButton rdbtnModeClassique;
	private JRadioButton rdbtnModeAvance;
	private JRadioButton rdbtnModePerso;
	private JButton btnLancerPartie;
	
	/**
	 * L'attribut jeu permet à la classe ControleurMenu d’atteindre les objets du Modèle à partir d'un jeu.
	 */
	private Jeu jeu;
	
	private Thread thread;
	/**
	 * L'attribut nbrPlayers permet de récupérer le nombre de joueur d'une partie.
	 */
	private int nbrPlayers;
	
	/**
	 * Instancie un nouveau controleur menu.
	 * @param jeu pour le jeu en cours.
	 * @param textPane zone de texte pour entrer le nom des joueurs.
	 * @param btnSaveJoueur bouton pour sauvergarder le nom du joueur rentré dans la zone de texte.
	 * @param btnAddIA bouton pour ajouter un joueur IA.
	 * @param rdbtnModeClassique bouton pour séléctionner le mode classique.
	 * @param rdbtnModeAvance bouton pour séléctionner le mode avancé.
	 * @param rdbtnModePerso bouton pour séléctionner le mode personalisé.
	 * @param btnLancerPartie bouton pour lancer la partie.
	 */
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
	
	/**
	 * Permet de récupérer les différentes actions réalisées sur l'interface Menu.
	 * Et permet d'appeler les méthodes dans le modèle pour enregistrer les informations rentrées par l'utilisateur : le nom du joueur, l'ajout d'une IA dans la partie, le choix du mode de jeu.
	 */
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
					textPane.setText(""); 
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
	
	/**
	 * Permet de lancer l'exécution du thread avec l'appelle de sa méthode start() 
	 */
	private void lancerPartie() {
		this.thread = new Thread(this);
		this.thread.start();
	}
     
	/**
	 * Permet de contenir les traitements à exécuter 
	 * @throws InvalidModeException
	 * @throws InvalidNbrOfPlayersException
	 */
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
