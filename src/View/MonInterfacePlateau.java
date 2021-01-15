package View;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import Controleur.ControleurDeplacer;
import Controleur.ControleurFinPartie;
import Controleur.ControleurFinTour;
import Controleur.ControleurMenu;
import Controleur.ControleurPiocher;
import Controleur.ControleurPlacer;
import Controleur.ControleurVictory;
import projetLO02.Card;
import projetLO02.InvalidModeException;
import projetLO02.InvalidNbrOfPlayersException;
import projetLO02.Jeu;
import projetLO02.Joueur;
import projetLO02.Mode;
import projetLO02.Plateau;

import java.awt.Color;
import javax.swing.JToggleButton;

@SuppressWarnings("deprecation")
public class MonInterfacePlateau implements Observer{

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
	
	private JFrame frameVictory;
	private JButton btnChangeVictory;
	private JButton btnSetVictory;
	
	private JFrame framePlateau;
	private JButton btnPiocher;
	private JToggleButton tglbtnPlacerDeplacer;
	private JLabel lblDe;
	private JLabel lblPositionOrigine;
	private JLabel lblVers;
	private JLabel lblPositionArrivee;
	private JButton btnFinTour;
	private JLabel lblCartePiochee;
	private JLabel lblVictoryCard;
	private JLabel lblJoueur;
	private JPanel panel;
	private JLabel lblNomDuJoueur;
	private JLabel lblPlaceCartePiocheeNumero1;
	private JLabel lblPlaceCartePiocheeNumero2;
	private JLabel lblPlaceCartePiocheeNumero3;
	private JRadioButton rdbtnCardNum1;
	private JRadioButton rdbtnCardNum2;
	private JRadioButton rdbtnCardNum3;
	private JLabel lblPlaceVictoryCard;
	private JButton btnC1;
	private JButton btnB1;
	private JButton btnA1;
	private JButton btnE3;
	private JButton btnD2;
	private JButton btnC2;
	private JButton btnB2;
	private JButton btnA2;
	private JButton btnE2;
	private JButton btnD3;
	private JButton btnA3;
	private JButton btnC3;
	private JButton btnB3;
	private JButton btnD1;
	private JButton btnE1;
	private JButton btnF2;
	private JButton btnF3;
	private JButton btnA0;
	private JButton btnC0;
	private JButton btnD0;
	private JButton btnE0;
	private JButton btnF1;
	private JButton btnB0;
	private JButton btnZ3;
	private JButton btnZ2;
	private JButton btnZ1;
	private JButton btnA4;
	private JButton btnB4;
	private JButton btnC4;
	private JButton btnD4;
	private JButton btnE4;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private JPanel panel_4;
	
	private JLabel lblFinPartie;
	private JLabel lblAnnonceGagnant;
	private JLabel lblAffichageGagnant;
	private JButton btnFermer;
	private JFrame frameFinPartie;
	
	protected Jeu jeu;

  	ImageIcon SQUARE_SOLID_BLUE = new ImageIcon(MonInterfacePlateau.class.getResource("/image/SQUARE_SOLID_BLUE.PNG"));
  	ImageIcon SQUARE_HOLLOW_BLUE = new ImageIcon(MonInterfacePlateau.class.getResource("/image/SQUARE_HOLLOW_BLUE.PNG"));
  	ImageIcon SQUARE_SOLID_RED = new ImageIcon(MonInterfacePlateau.class.getResource("/image/SQUARE_SOLID_RED.PNG"));
   	ImageIcon SQUARE_HOLLOW_RED = new ImageIcon(MonInterfacePlateau.class.getResource("/image/SQUARE_HOLLOW_RED.PNG"));
   	ImageIcon SQUARE_SOLID_GREEN= new ImageIcon(MonInterfacePlateau.class.getResource("/image/SQUARE_SOLID_GREEN.PNG"));
   	ImageIcon SQUARE_HOLLOW_GREEN = new ImageIcon(MonInterfacePlateau.class.getResource("/image/SQUARE_HOLLOW_GREEN.PNG"));
   	ImageIcon CIRCLE_SOLID_BLUE = new ImageIcon(MonInterfacePlateau.class.getResource("/image/CIRCLE_SOLID_BLUE.PNG"));
   	ImageIcon CIRCLE_HOLLOW_BLUE = new ImageIcon(MonInterfacePlateau.class.getResource("/image/CIRCLE_HOLLOW_BLUE.PNG"));
   	ImageIcon CIRCLE_SOLID_RED = new ImageIcon(MonInterfacePlateau.class.getResource("/image/CIRCLE_SOLID_RED.PNG"));
   	ImageIcon CIRCLE_HOLLOW_RED = new ImageIcon(MonInterfacePlateau.class.getResource("/image/CIRCLE_HOLLOW_RED.PNG"));
   	ImageIcon CIRCLE_SOLID_GREEN = new ImageIcon(MonInterfacePlateau.class.getResource("/image/CIRCLE_SOLID_GREEN.PNG"));
   	ImageIcon CIRCLE_HOLLOW_GREEN  = new ImageIcon(MonInterfacePlateau.class.getResource("/image/CIRCLE_HOLLOW_GREEN.PNG"));
   	ImageIcon TRIANGLE_SOLID_BLUE = new ImageIcon(MonInterfacePlateau.class.getResource("/image/TRIANGLE_SOLID_BLUE.PNG"));
  	ImageIcon TRIANGLE_HOLLOW_BLUE= new ImageIcon(MonInterfacePlateau.class.getResource("/image/TRIANGLE_HOLLOW_BLUE.PNG"));
   	ImageIcon TRIANGLE_SOLID_RED  = new ImageIcon(MonInterfacePlateau.class.getResource("/image/TRIANGLE_SOLID_RED.PNG"));
   	ImageIcon TRIANGLE_HOLLOW_RED = new ImageIcon(MonInterfacePlateau.class.getResource("/image/TRIANGLE_HOLLOW_RED.PNG"));
   	ImageIcon TRIANGLE_SOLID_GREEN  = new ImageIcon(MonInterfacePlateau.class.getResource("/image/TRIANGLE_SOLID_GREEN.PNG"));
    ImageIcon TRIANGLE_HOLLOW_GREEN = new ImageIcon(MonInterfacePlateau.class.getResource("/image/TRIANGLE_HOLLOW_GREEN.PNG"));
  
 
	private Map<String,JButton> cardPlateauButtons;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonInterfacePlateau window = new MonInterfacePlateau();
					window.frameMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws InvalidNbrOfPlayersException 
	 * @throws InvalidModeException 
	 * @wbp.parser.entryPoint
	 */
	public MonInterfacePlateau() {
		cardPlateauButtons = new TreeMap<String, JButton>();
		initialize();
		this.jeu = new Jeu(this);
		new VueTexte(this.jeu); 
				
		new ControleurMenu(this.jeu, this.textPane, this.lblJoueur1, this.lblJoueur2, this.lblJoueur3, this.btnSaveJoueur, this.btnAddIA, this.rdbtnModeClassique, this.rdbtnModeAvance, this.rdbtnModePerso, this.btnLancerPartie, this.frameMenu, this.framePlateau, this.frameVictory, this.lblPlaceVictoryCard, this.lblNomDuJoueur);
		new ControleurVictory(this.jeu, this.framePlateau, this.frameVictory, this.btnChangeVictory, this.btnSetVictory, this.lblPlaceVictoryCard, this.lblNomDuJoueur);
	    new ControleurPiocher(this.jeu, this.btnPiocher);
	    new ControleurPlacer(this.jeu, this.tglbtnPlacerDeplacer , this.cardPlateauButtons, this.rdbtnCardNum1, this.rdbtnCardNum2, this.rdbtnCardNum3);
	    new ControleurDeplacer(this.jeu, this.tglbtnPlacerDeplacer, this.cardPlateauButtons, this.lblDe, this.lblPositionOrigine, this.lblVers, this.lblPositionArrivee);
	    new ControleurFinTour(this.jeu, this.btnFinTour, this.frameFinPartie, this.framePlateau, this.lblAffichageGagnant);
	    new ControleurFinPartie(this.btnFermer, this.frameFinPartie);	  

	  
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
		
		///////////////////////////////////////////
		
		frameVictory = new JFrame();
		frameVictory.setVisible(false);
		frameVictory.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frameVictory.setBounds(100, 100, 498, 304);
		frameVictory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVictory.getContentPane().setLayout(null);
		
		btnChangeVictory = new JButton("ChangeVictory");
		btnChangeVictory.setBounds(287, 82, 103, 29);
		frameVictory.getContentPane().add(btnChangeVictory);
		
		btnSetVictory = new JButton("SetVictory");
		btnSetVictory.setBounds(287, 138, 103, 29);
		frameVictory.getContentPane().add(btnSetVictory);
		
		lblPlaceVictoryCard = new JLabel("Victory Card");
		lblPlaceVictoryCard.setBounds(1253, 235, 135, 144);
		frameVictory.getContentPane().add(lblPlaceVictoryCard);
		
		lblNomDuJoueur = new JLabel("");
		lblNomDuJoueur.setBounds(1030, 8, 74, 44);
		frameVictory.getContentPane().add(lblNomDuJoueur);	
		
		
		///////////////////////////////////////////
		framePlateau = new JFrame();
		framePlateau.setVisible(false);
		framePlateau.setBounds(100, 100, 1484, 770);
		framePlateau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePlateau.getContentPane().setLayout(null);
		
		btnPiocher = new JButton("Piocher");
		btnPiocher.setBounds(1000, 61, 127, 30);
		framePlateau.getContentPane().add(btnPiocher);
		
	    tglbtnPlacerDeplacer = new JToggleButton("Placer");
	    tglbtnPlacerDeplacer.setBounds(1000, 98, 127, 30);
	    framePlateau.getContentPane().add(tglbtnPlacerDeplacer);
	    
	    lblDe = new JLabel("De : ");
	    lblDe.setEnabled(false);
	    lblDe.setBounds(1137, 106, 30, 14);
	    framePlateau.getContentPane().add(lblDe);
	    
	    lblPositionOrigine = new JLabel("");
	    lblPositionOrigine.setEnabled(false);
	    lblPositionOrigine.setBounds(1170, 106, 35, 14);
	    framePlateau.getContentPane().add(lblPositionOrigine);
	    
	    lblVers = new JLabel("Vers : ");
	    lblVers.setEnabled(false);
	    lblVers.setBounds(1213, 106, 35, 14);
	    framePlateau.getContentPane().add(lblVers);
	    
	    lblPositionArrivee = new JLabel("");
	    lblPositionArrivee.setEnabled(false);
	    lblPositionArrivee.setBounds(1250, 106, 35, 14);
	    framePlateau.getContentPane().add(lblPositionArrivee);

		btnFinTour = new JButton("Terminer tour");
		btnFinTour.setBounds(1000, 138, 127, 30);
		framePlateau.getContentPane().add(btnFinTour);
				
		lblCartePiochee = new JLabel("Votre carte piochee :");
		lblCartePiochee.setBounds(961, 194, 176, 31);
		framePlateau.getContentPane().add(lblCartePiochee);
		lblCartePiochee.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

		lblPlaceCartePiocheeNumero1 = new JLabel("Carte piochee numero 1");
		lblPlaceCartePiocheeNumero1.setBounds(961, 229, 128, 144);
		framePlateau.getContentPane().add(lblPlaceCartePiocheeNumero1);		
		
		lblPlaceCartePiocheeNumero2 = new JLabel("Carte piochee numero 2");
		lblPlaceCartePiocheeNumero2.setBounds(961, 383, 128, 144);
		framePlateau.getContentPane().add(lblPlaceCartePiocheeNumero2);
		    
		lblPlaceCartePiocheeNumero3 = new JLabel("Carte piochee numero 3");
		lblPlaceCartePiocheeNumero3.setBounds(961, 547, 128, 144);
		framePlateau.getContentPane().add(lblPlaceCartePiocheeNumero3);
		
		ButtonGroup rdbnGroupe2 = new ButtonGroup();
		
		rdbtnCardNum1 = new JRadioButton("Carte n\u00B01");
		rdbtnCardNum1.setSelected(true);
	    rdbtnCardNum1.setBounds(1109, 290, 79, 23);
	    framePlateau.getContentPane().add(rdbtnCardNum1);
	    rdbnGroupe2.add(rdbtnCardNum1);
	    
	    rdbtnCardNum2 = new JRadioButton("Carte n\u00B02");
	    rdbtnCardNum2.setBounds(1109, 444, 79, 23);
	    framePlateau.getContentPane().add(rdbtnCardNum2);
	    rdbnGroupe2.add(rdbtnCardNum2);
	    
	    rdbtnCardNum3 = new JRadioButton("Carte n\u00B03");
	    rdbtnCardNum3.setBounds(1109, 608, 79, 23);
	    framePlateau.getContentPane().add(rdbtnCardNum3);
	    rdbnGroupe2.add(rdbtnCardNum3);
		
		lblVictoryCard = new JLabel("Votre Victory Card :");
		lblVictoryCard.setBounds(1253, 194, 153, 30);
		framePlateau.getContentPane().add(lblVictoryCard);
		lblVictoryCard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		lblJoueur = new JLabel("Joueur : ");
		lblJoueur.setBounds(961, 7, 74, 44);
		framePlateau.getContentPane().add(lblJoueur);
		lblJoueur.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		panel = new JPanel();
		panel.setBounds(141, 148, 642, 433);
		framePlateau.getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 5, 0, 0));
		
		btnA3 = new JButton("A3");
		panel.add(btnA3);
		cardPlateauButtons.put("A3",btnA3);
		
		btnB3 = new JButton("B3");
		panel.add(btnB3);
		cardPlateauButtons.put("B3",btnB3);
		
		btnC3 = new JButton("C3");
		panel.add(btnC3);
		cardPlateauButtons.put("C3",btnC3);
		
		btnD3 = new JButton("D3");
		panel.add(btnD3);
		cardPlateauButtons.put("D3",btnD3);
		
		btnE3 = new JButton("E3");
		panel.add(btnE3);
		cardPlateauButtons.put("E3",btnE3);
		
		btnA2 = new JButton("A2");
		panel.add(btnA2);
		cardPlateauButtons.put("A2",btnA2);
		
		btnB2 = new JButton("B2");
		panel.add(btnB2);
		cardPlateauButtons.put("B2",btnB2);
		
		btnC2 = new JButton("C2");
		panel.add(btnC2);
		cardPlateauButtons.put("C2",btnC2);
		
		btnD2 = new JButton("D2");
		panel.add(btnD2);
		cardPlateauButtons.put("D2",btnD2);
		
		btnE2 = new JButton("E2");
		panel.add(btnE2);
		cardPlateauButtons.put("E2",btnE2);
		
		btnA1 = new JButton("A1");
		panel.add(btnA1);
		cardPlateauButtons.put("A1",btnA1);
		
		btnB1 = new JButton("B1");
		panel.add(btnB1);
		cardPlateauButtons.put("B1",btnB1);
		
		btnC1 = new JButton("C1");
		panel.add(btnC1);
		cardPlateauButtons.put("C1",btnC1);
		
		btnD1 = new JButton("D1");
		panel.add(btnD1);
		cardPlateauButtons.put("D1",btnD1);
		
		btnE1 = new JButton("E1");
		panel.add(btnE1);
		cardPlateauButtons.put("E1",btnE1);
					
		panel_1 = new JPanel();
		panel_1.setBounds(782, 148, 128, 433);
		framePlateau.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(3, 0, 0, 0));
		
		btnF3 = new JButton(">3");
		panel_1.add(btnF3);
		cardPlateauButtons.put(">3",btnF3);
		    
		btnF2 = new JButton(">2");
		panel_1.add(btnF2);
		cardPlateauButtons.put(">2",btnF2);
		
		btnF1 = new JButton(">1");
		panel_1.add(btnF1);
		cardPlateauButtons.put(">1",btnF1);
		   
		panel_2 = new JPanel();
		panel_2.setBounds(141, 579, 642, 144);
		framePlateau.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 5, 0, 0));
		    
		btnA0 = new JButton("A0");
		panel_2.add(btnA0);
		cardPlateauButtons.put("A0",btnA0);
		   
		btnB0 = new JButton("B0");
		panel_2.add(btnB0);
		cardPlateauButtons.put("B0",btnB0);
		    
		btnC0 = new JButton("C0");
		panel_2.add(btnC0);
		cardPlateauButtons.put("C0",btnC0);
		   
		btnD0 = new JButton("D0");
		panel_2.add(btnD0);
		cardPlateauButtons.put("D0",btnD0);
		  
		btnE0 = new JButton("E0");
		panel_2.add(btnE0);
		cardPlateauButtons.put("E0",btnE0);
		
		panel_3 = new JPanel();
		panel_3.setBounds(10, 148, 128, 433);
		framePlateau.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(3, 0, 0, 0));
		    
		btnZ3 = new JButton("<3");
		panel_3.add(btnZ3);
		cardPlateauButtons.put("<3",btnZ3);
		    
		btnZ2 = new JButton("<2");
		panel_3.add(btnZ2);
		cardPlateauButtons.put("<2",btnZ2);
		    
		btnZ1 = new JButton("<1");
		panel_3.add(btnZ1);
		cardPlateauButtons.put("<1",btnZ1);
		    
		panel_4 = new JPanel();
		panel_4.setBounds(141, 7, 642, 144);
		framePlateau.getContentPane().add(panel_4);
		panel_4.setLayout(new GridLayout(0, 5, 0, 0));
		    
		btnA4 = new JButton("A4");
		panel_4.add(btnA4);
		cardPlateauButtons.put("A4",btnA4);
		   
		btnB4 = new JButton("B4");
		panel_4.add(btnB4);
		cardPlateauButtons.put("B4",btnB4);
		    
		btnC4 = new JButton("C4");
		panel_4.add(btnC4);
		cardPlateauButtons.put("C4",btnC4);
		  
		btnD4 = new JButton("D4");
		panel_4.add(btnD4);
		cardPlateauButtons.put("D4",btnD4);
		    
		btnE4 = new JButton("E4");
		panel_4.add(btnE4);
		cardPlateauButtons.put("E4",btnE4);
		   
		//interface fin de partie
		frameFinPartie = new JFrame();
		frameFinPartie.setVisible(false);
		frameFinPartie.setBounds(100, 100, 891, 548);
		frameFinPartie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFinPartie.getContentPane().setLayout(null);
				
		lblFinPartie = new JLabel("La partie est termin\u00E9e !");
		lblFinPartie.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFinPartie.setBackground(Color.BLACK);
		lblFinPartie.setBounds(271, 24, 338, 59);
		frameFinPartie.getContentPane().add(lblFinPartie);
				
		lblAnnonceGagnant = new JLabel("a gagn\u00E9 !   Bravo");
		lblAnnonceGagnant.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAnnonceGagnant.setBounds(461, 156, 244, 59);
		frameFinPartie.getContentPane().add(lblAnnonceGagnant);
				
		lblAffichageGagnant = new JLabel("");
		lblAffichageGagnant.setBackground(new Color(0, 0, 0));
		lblAffichageGagnant.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAffichageGagnant.setBounds(317, 156, 134, 59);
		frameFinPartie.getContentPane().add(lblAffichageGagnant);
				
		btnFermer = new JButton("FERMER");
		btnFermer.setBounds(380, 381, 118, 51);
		frameFinPartie.getContentPane().add(btnFermer);
	}	 
	
	private void givePlayerCards(Joueur player) {
		String currentPlayer = player.getName(); //l'idee est d'afficher le nom du joueur et changer à chaque fois que c'est au joueur suivant de jouer. 
		lblNomDuJoueur.setText(currentPlayer);
		lblPlaceCartePiocheeNumero1.setIcon(null);
		lblPlaceCartePiocheeNumero2.setIcon(null);
		lblPlaceCartePiocheeNumero3.setIcon(null);
		for(int i=0; i<player.getHand().checkNombreCartes(); i++) {
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+player.getHand().getCard(i).getType1().toString()+"_"+player.getHand().getCard(i).getType2().toString()+"_"+player.getHand().getCard(i).getType3().toString()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(135, 144, Image.SCALE_DEFAULT)); 
		  	switch(i) {
		  		case(0):
		  			lblPlaceCartePiocheeNumero1.setIcon(newImage);
		  			break;
		  		case(1):
		  			lblPlaceCartePiocheeNumero2.setIcon(newImage);
	  				break;
		  		case(2):
		  			lblPlaceCartePiocheeNumero3.setIcon(newImage);
	  				break;
		  	}
		}
		if(this.jeu.getMode()!=Mode.Avancé) {
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+player.getVictory().getType1().toString()+"_"+player.getVictory().getType2().toString()+"_"+player.getVictory().getType3().toString()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(135,144, Image.SCALE_DEFAULT)); 
		  	lblPlaceVictoryCard.setIcon(newImage);
		}
	}

	public void update(Observable o, Object arg) {
		if((o instanceof Jeu)&&(arg instanceof Joueur)) {
			Joueur player = (Joueur) arg;
			givePlayerCards(player);
			tglbtnPlacerDeplacer.setText("Placer");
			tglbtnPlacerDeplacer.setSelected(false);
			lblPositionOrigine.setText(null);
			lblPositionArrivee.setText(null);
			lblDe.setEnabled(false);
			lblPositionOrigine.setEnabled(false);
			lblVers.setEnabled(false);
			lblPositionArrivee.setEnabled(false);
		}
		
		if((o instanceof Jeu)&&(arg instanceof Integer)) {
			switch((int)arg) {
	  		case(1):
	  			lblJoueur1.setText(jeu.getPlayerName(1));
	  			break;
	  		case(2):
	  			lblJoueur2.setText(jeu.getPlayerName(2));
  				break;
	  		case(3):
	  			lblJoueur3.setText(jeu.getPlayerName(3));
  				break;
			}
		}
		
		if((o instanceof Jeu)&&(arg instanceof Boolean)) {
			if(this.jeu.getHasStarted()) {
				if((jeu.getMode()==Mode.Personnalisé)&&(jeu.getNbrJoueurs()>0)) {
					frameVictory.setVisible(true);
					frameMenu.setVisible(false);
				}
				else {
					lblNomDuJoueur.setBounds(1030, 8, 74, 44);
					framePlateau.getContentPane().add(lblNomDuJoueur);
					lblPlaceVictoryCard.setBounds(1253, 235, 135, 144);
					framePlateau.getContentPane().add(lblPlaceVictoryCard);
					framePlateau.setVisible(true); //permet d'ouvrir l'interface graphique du Plateau
					frameMenu.setVisible(false);
				}
			}
			else {
				if(jeu.getNbrVictoryCardChoosen()==jeu.getNbrJoueurs()) {
					lblNomDuJoueur.setBounds(1030, 8, 74, 44);
					framePlateau.getContentPane().add(lblNomDuJoueur);
					lblPlaceVictoryCard.setBounds(1253, 235, 135, 144);
					framePlateau.getContentPane().add(lblPlaceVictoryCard);
					framePlateau.setVisible(true);
					frameVictory.setVisible(false);
				}
			}
		}
		
		
		if((o instanceof Joueur)&&(arg instanceof Joueur)) {
			Joueur player = (Joueur) arg;
			givePlayerCards(player);
		}
		
		if((o instanceof Plateau)&&(arg instanceof Map<?, ?>)) {
			for(Iterator<String> it = cardPlateauButtons.keySet().iterator(); it.hasNext();) {
				String key = it.next();
				if(((Map<?, ?>)arg).containsKey(key)) {
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+((Card) ((Map<?, ?>) arg).get(key)).getType1().toString()+"_"+((Card) ((Map<?, ?>) arg).get(key)).getType2().toString()+"_"+((Card) ((Map<?, ?>) arg).get(key)).getType3().toString()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(135,144, Image.SCALE_DEFAULT)); 
				  	cardPlateauButtons.get(key).setIcon(newImage);
				}
				else {
					cardPlateauButtons.get(key).setIcon(null);
				}
			}
		}
		
	}
}