package View;



import java.awt.EventQueue;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;

import Controleur.ControleurPiocher;
import Controleur.ControleurPlacer;
import projetLO02.Deck;
import projetLO02.Joueur;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.Icon;

public class InterfacePlateau implements Observer {

	//propriétés de la classe
	private JFrame frame;
	private Joueur joueurPioche;
	private JButton btnPiocher;
	private Joueur joueurPlace;
	private JButton btnPlacer;
	private JButton btnDeplacer;
	private JLabel lblCartePiochee;
	private JLabel lblVictoryCard;
	private JLabel lblJoueur;
	private JPanel panel;
	private JLabel lblcaseA3;
	private JLabel lblcaseB3;
	private JLabel lblcaseC3;
	private JLabel lblcaseD3;
	private JLabel lblcaseE3;
	private JLabel lblcaseA2;
	private JLabel lblcaseB2;
	private JLabel lblcaseC2;
	private JLabel lblcaseD2;
	private JLabel lblcaseE2;
	private JLabel lblcaseA1;
	private JLabel lblcaseB1;
	private JLabel lblcaseC1;
	private JLabel lblcaseD1;
	private JLabel lblcaseE1;
	private JLabel lblNomDuJoueur;
	private JLabel lblPlaceCartePiochee;
	private JLabel lblPlaceVictoryCard;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Joueur joueurPioche = new Joueur();
		Joueur joueurPlace = new Joueur();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePlateau window = new InterfacePlateau();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfacePlateau(Joueur joueur) {
		initialize();
		//Création du Controleur : lien entre le Modéle et la Vue
		new ControleurPiocher(joueurPioche, btnPiocher);
		new ControleurPlacer(joueurPlace, btnPlacer);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 992, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnPiocher = new JButton("Piocher");
		btnPiocher.setBounds(773, 87, 114, 30);
		frame.getContentPane().add(btnPiocher);
		
		btnDeplacer = new JButton("deplacer");
		btnDeplacer.setBounds(773, 173, 114, 30);
		frame.getContentPane().add(btnDeplacer);
		
		lblCartePiochee = new JLabel("Votre carte piochee :");
		lblCartePiochee.setBounds(701, 226, 176, 31);
		frame.getContentPane().add(lblCartePiochee);
		lblCartePiochee.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		lblVictoryCard = new JLabel("Votre Victory Card :");
		lblVictoryCard.setBounds(701, 412, 153, 30);
		frame.getContentPane().add(lblVictoryCard);
		lblVictoryCard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		lblJoueur = new JLabel("Joueur : ");
		lblJoueur.setBounds(735, 21, 74, 44);
		frame.getContentPane().add(lblJoueur);
		lblJoueur.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		panel = new JPanel();
		panel.setBounds(10, 10, 657, 697);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 5, 0, 0));
		
		lblcaseA3 = new JLabel("A3");
		panel.add(lblcaseA3);
		
		lblcaseB3 = new JLabel("B3");
		panel.add(lblcaseB3);
		
		lblcaseC3 = new JLabel("C3");
		panel.add(lblcaseC3);
		
		lblcaseD3 = new JLabel("D3");
		panel.add(lblcaseD3);
		
		lblcaseE3 = new JLabel("E3");
		panel.add(lblcaseE3);
		
		lblcaseA2 = new JLabel("A2");
		panel.add(lblcaseA2);
		
		lblcaseB2 = new JLabel("B2");
		panel.add(lblcaseB2);
		
		lblcaseC2 = new JLabel("C2");
		panel.add(lblcaseC2);
		
		lblcaseD2 = new JLabel("D2");
		panel.add(lblcaseD2);
		
		lblcaseE2 = new JLabel("E2");
		panel.add(lblcaseE2);
		
		lblcaseA1 = new JLabel("A1");
		panel.add(lblcaseA1);
		
		lblcaseB1 = new JLabel("B1");
		panel.add(lblcaseB1);
		
		lblcaseC1 = new JLabel("C1");
		panel.add(lblcaseC1);
		
		lblcaseD1 = new JLabel("D1");
		panel.add(lblcaseD1);
		
		lblcaseE1 = new JLabel("E1");
		panel.add(lblcaseE1);
		
		lblNomDuJoueur = new JLabel("nom");
		lblNomDuJoueur.setBounds(808, 28, 127, 34);
		frame.getContentPane().add(lblNomDuJoueur);
		
		lblPlaceCartePiochee = new JLabel("Carte piochee");
		lblPlaceCartePiochee.setBounds(701, 267, 161, 135);
		frame.getContentPane().add(lblPlaceCartePiochee);
		
		lblPlaceVictoryCard = new JLabel("Victory Card");
		lblPlaceVictoryCard.setBounds(711, 452, 153, 226);
		frame.getContentPane().add(lblPlaceVictoryCard);
		
		btnPlacer = new JButton("Placer");
		btnPlacer.setBounds(773, 127, 114, 30);
		frame.getContentPane().add(btnPlacer);
		
		
	}
	
	public void update(Observable instanceObservable, Object arg1) {
		
	}
}
