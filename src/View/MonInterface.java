package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import Controleur.ControleurMenu;
import projetLO02.Jeu;
import projetLO02.Joueur;

@SuppressWarnings("deprecation")
public class MonInterface implements Observer {

	private JFrame frameMenu;
	private JTextPane textPane;
	private JLabel lblMenu;
	private JLabel lblEntrezNomJoueur;
	private JLabel lblJoueur1;
	private JLabel lblJoueur2;
	private JLabel lblJoueur3;
	private JButton btnSaveJoueur;
	private JButton btnAddIA;
	private JLabel lblChooseMode;
	private JRadioButton rdbtnModeClassique;
	private JRadioButton rdbtnModeAvance;
	private JRadioButton rdbtnModePerso;
	private JButton btnLancerPartie;
	
	private JFrame framePlateau;
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
	
	protected Jeu jeu;
	private Joueur joueurEnCours;
	
	private ArrayList<JButton> cardPlateauButtons = new ArrayList<JButton>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonInterface window = new MonInterface();
					window.frameMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MonInterface() {
		initialize();
		jeu = new Jeu(this);
		
		new ControleurMenu(this.jeu, this.textPane, this.lblJoueur1, this.lblJoueur2, this.lblJoueur3, this.btnSaveJoueur, this.btnAddIA, this.rdbtnModeClassique, this.rdbtnModeAvance, this.rdbtnModePerso, this.btnLancerPartie, this.frameMenu, this.framePlateau);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameMenu = new JFrame();
		frameMenu.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frameMenu.setBounds(100, 100, 644, 520);
		frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMenu.getContentPane().setLayout(null);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane.setBounds(305, 111, 193, 28);
		frameMenu.getContentPane().add(textPane);
		
		lblMenu = new JLabel("Shape Up !");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblMenu.setBounds(195, 10, 210, 44);
		frameMenu.getContentPane().add(lblMenu);
		
		lblEntrezNomJoueur = new JLabel("Entrez le nom des joueurs :");
		lblEntrezNomJoueur.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEntrezNomJoueur.setBounds(10, 111, 283, 28);
		frameMenu.getContentPane().add(lblEntrezNomJoueur);
		
		lblJoueur1 = new JLabel("");
		lblJoueur1.setBounds(66, 203, 80, 28);
		frameMenu.getContentPane().add(lblJoueur1);
		
		lblJoueur2 = new JLabel("");
		lblJoueur2.setBounds(274, 203, 80, 28);
		frameMenu.getContentPane().add(lblJoueur2);
		
		lblJoueur3 = new JLabel("");
		lblJoueur3.setBounds(473, 203, 80, 28);
		frameMenu.getContentPane().add(lblJoueur3);
		
		btnSaveJoueur = new JButton("save");
		btnSaveJoueur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSaveJoueur.setBounds(508, 111, 112, 30);
		frameMenu.getContentPane().add(btnSaveJoueur);
		
		btnAddIA = new JButton("Add IA");
		btnAddIA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddIA.setBounds(274, 164, 80, 28);
		frameMenu.getContentPane().add(btnAddIA);
		
		lblChooseMode = new JLabel("Choisissez le mode de jeu :");
		lblChooseMode.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblChooseMode.setBounds(10, 242, 283, 28);
		frameMenu.getContentPane().add(lblChooseMode);
		
		ButtonGroup rdbnGroupe = new ButtonGroup();
		
		rdbtnModeClassique = new JRadioButton("Mode Classique");
		rdbtnModeClassique.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnModeClassique.setSelected(true);
		rdbtnModeClassique.setBounds(296, 250, 136, 23);
		frameMenu.getContentPane().add(rdbtnModeClassique);
		rdbnGroupe.add(rdbtnModeClassique);
		
		rdbtnModeAvance = new JRadioButton("Mode Avanc\u00E9");
		rdbtnModeAvance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnModeAvance.setBounds(296, 277, 123, 23);
		frameMenu.getContentPane().add(rdbtnModeAvance);
		rdbnGroupe.add(rdbtnModeAvance);
		
		rdbtnModePerso = new JRadioButton("Mode Personnalis\u00E9");
		rdbtnModePerso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnModePerso.setBounds(296, 303, 153, 23);
		frameMenu.getContentPane().add(rdbtnModePerso);
		rdbnGroupe.add(rdbtnModePerso);
		
		btnLancerPartie = new JButton("Jouer");
		btnLancerPartie.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLancerPartie.setBounds(245, 414, 129, 56);
		frameMenu.getContentPane().add(btnLancerPartie);	
		
		
		framePlateau = new JFrame();
		framePlateau.setVisible(false);
		framePlateau.setBounds(100, 100, 992, 770);
		framePlateau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePlateau.getContentPane().setLayout(null);
		
		btnPiocher = new JButton("Piocher");
		btnPiocher.setBounds(773, 87, 114, 30);
		framePlateau.getContentPane().add(btnPiocher);
		
		btnDeplacer = new JButton("deplacer");
		btnDeplacer.setBounds(773, 140, 114, 30);
		framePlateau.getContentPane().add(btnDeplacer);
		
		btnFinTour = new JButton("Passer la main");
		btnFinTour.setBounds(773, 193, 114, 30);
		framePlateau.getContentPane().add(btnFinTour);
				
		lblCartePiochee = new JLabel("Votre carte piochee :");
		lblCartePiochee.setBounds(701, 226, 176, 31);
		framePlateau.getContentPane().add(lblCartePiochee);
		lblCartePiochee.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		lblVictoryCard = new JLabel("Votre Victory Card :");
		lblVictoryCard.setBounds(701, 478, 153, 30);
		framePlateau.getContentPane().add(lblVictoryCard);
		lblVictoryCard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		lblJoueur = new JLabel("Joueur : ");
		lblJoueur.setBounds(735, 21, 74, 44);
		framePlateau.getContentPane().add(lblJoueur);
		lblJoueur.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		panel = new JPanel();
		panel.setBounds(10, 10, 657, 697);
		framePlateau.getContentPane().add(panel);
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
		framePlateau.getContentPane().add(lblNomDuJoueur);
		
		lblPlaceCartePiochee = new JLabel("Carte piochee");
		lblPlaceCartePiochee.setBounds(701, 267, 161, 201);
		framePlateau.getContentPane().add(lblPlaceCartePiochee);
		
		lblPlaceVictoryCard = new JLabel("Victory Card");
		lblPlaceVictoryCard.setBounds(709, 522, 153, 201);
		framePlateau.getContentPane().add(lblPlaceVictoryCard);
		
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

	public void update(Observable o, Object arg) {
		if(o instanceof Jeu) {
			this.jeu = (Jeu) o;
		}
	}
}
