package View;



import java.awt.EventQueue;


import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;

import Controleur.ControleurPiocher;
import Controleur.ControleurPlacer;
import projetLO02.Deck;
import projetLO02.Jeu;
import projetLO02.Joueur;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private Deck joueurPioche;
	private JButton btnPiocher;
	
	private JButton btnDeplacer;
	private JButton btnFinTour;
	private JLabel lblCartePiochee;
	private JLabel lblVictoryCard;
	private JLabel lblJoueur;
	private JPanel panel;
	private JLabel lblNomDuJoueur;
	private JLabel lblPlaceCartePiochee;
	private JLabel lblPlaceVictoryCard;
	private Joueur joueurPlace;
	private JButton btnC1;
	private JButton btnB1;
	private JButton btnA1;
	private JButton btnE2;
	private JButton btnD2;
	private JButton btnC2;
	private JButton btnB2;
	private JButton btnA2;
	private JButton btnE3;
	private JButton btnD3;
	private JButton btnA3;
	private JButton btnC3;
	private JButton btnB3;
	private JButton btnD1;
	private JButton btnE1;
	
	private Menu ajoutJoueurs = new Menu(); 
	static ArrayList<String>  player = new ArrayList<>();
	ArrayList<JButton> cardPlateauButtons = new ArrayList<JButton>();
	ArrayList<String> cardIds;
	Jeu jeu;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Deck joueurPioche = new Deck(); 
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePlateau window = new InterfacePlateau(player);
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
	public InterfacePlateau(ArrayList<String> playerIds) {
		initialize();
		player = playerIds;
		setName();
		 
		//Création du Controleur : lien entre le Modéle et la Vue
		new ControleurPiocher(joueurPioche, btnPiocher);
		new ControleurPlacer(joueurPlace, );
				
	}
	
	
	public void remplirArrayList() {
	  	cardPlateauButtons.add(btnA1);
	  	cardPlateauButtons.add(btnA2);
	  	cardPlateauButtons.add(btnA3);
	  	cardPlateauButtons.add(btnB1);
	  	cardPlateauButtons.add(btnB2);
	  	cardPlateauButtons.add(btnB3);
	  	cardPlateauButtons.add(btnC1);
	  	cardPlateauButtons.add(btnC2);
	  	cardPlateauButtons.add(btnC3);
	  	cardPlateauButtons.add(btnD1);
	  	cardPlateauButtons.add(btnD2);
	  	cardPlateauButtons.add(btnD3);
	  	cardPlateauButtons.add(btnE1);
	  	cardPlateauButtons.add(btnE2);	  	
	  	cardPlateauButtons.add(btnE3);
		
	}
	
	public void setName(){
		String name = player.get(0);
		lblNomDuJoueur.setText(name);
	 }
	
		
	public JFrame getFrame() {
		return this.frame;
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
		btnDeplacer.setBounds(773, 140, 114, 30);
		frame.getContentPane().add(btnDeplacer);
		
		btnFinTour = new JButton("Passer la main");
		btnFinTour.setBounds(773, 193, 114, 30);
		frame.getContentPane().add(btnFinTour);
				
		lblCartePiochee = new JLabel("Votre carte piochee :");
		lblCartePiochee.setBounds(701, 226, 176, 31);
		frame.getContentPane().add(lblCartePiochee);
		lblCartePiochee.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		lblVictoryCard = new JLabel("Votre Victory Card :");
		lblVictoryCard.setBounds(701, 478, 153, 30);
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
		
		btnA3 = new JButton("A3");
		panel.add(btnA3);
		
		btnB3 = new JButton("B3");
		panel.add(btnB3);
		
		btnC3 = new JButton("C3");
		panel.add(btnC3);
		
		btnD3 = new JButton("D3");
		panel.add(btnD3);
		
		btnE3 = new JButton("E3");
		panel.add(btnE3);
		
		btnA2 = new JButton("A2");
		panel.add(btnA2);
		
		btnB2 = new JButton("B2");
		panel.add(btnB2);
		
		btnC2 = new JButton("C2");
		panel.add(btnC2);
		
		btnD2 = new JButton("D2");
		panel.add(btnD2);
		
		btnE2 = new JButton("E2");
		panel.add(btnE2);
		
		btnA1 = new JButton("A1");
		panel.add(btnA1);
		
		btnB1 = new JButton("B1");
		panel.add(btnB1);
		
		btnC1 = new JButton("C1");
		panel.add(btnC1);
		
		btnD1 = new JButton("D1");
		panel.add(btnD1);
		
		btnE1 = new JButton("E1");
		panel.add(btnE1);
		
		lblNomDuJoueur = new JLabel("");
		lblNomDuJoueur.setBounds(808, 28, 127, 34);
		frame.getContentPane().add(lblNomDuJoueur);
		
		lblPlaceCartePiochee = new JLabel("Carte piochee");
		lblPlaceCartePiochee.setBounds(701, 267, 161, 201);
		frame.getContentPane().add(lblPlaceCartePiochee);
		
		lblPlaceVictoryCard = new JLabel("Victory Card");
		lblPlaceVictoryCard.setBounds(709, 522, 153, 201);
		frame.getContentPane().add(lblPlaceVictoryCard);
		
		
	}
	
	public void update(Observable instanceObservable, Object arg1) {
		
	}
}
