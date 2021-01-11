package View;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import Controleur.ControleurDeplacer;
import Controleur.ControleurFinTour;
import Controleur.ControleurMenu;
import Controleur.ControleurPiocher;
import Controleur.ControleurPlacer;
import projetLO02.Hand;
import projetLO02.InvalidModeException;
import projetLO02.InvalidNbrOfPlayersException;
import projetLO02.Jeu;
import projetLO02.Joueur;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

@SuppressWarnings("deprecation")
public class MonInterfacePlateau implements Observer, ActionListener{

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
	protected Hand hand;
	

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
  
 
	private ArrayList<JButton> cardPlateauButtons = new ArrayList<JButton>();
	private JTextField textCaseDeDepart;
	private JTextField textCaseDArrivee;

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
	 */
	public MonInterfacePlateau() {
		initialize();
		jeu = new Jeu(this);
		
				//joueurEnCours.addObserver(this);
		
		new ControleurMenu(this.jeu, this.textPane, this.lblJoueur1, this.lblJoueur2, this.lblJoueur3, this.btnSaveJoueur, this.btnAddIA, this.rdbtnModeClassique, this.rdbtnModeAvance, this.rdbtnModePerso, this.btnLancerPartie, this.frameMenu, this.framePlateau, this.lblNomDuJoueur);
	    new ControleurPiocher(this.jeu, this.btnPiocher);
	    new ControleurPlacer(this.jeu, this.hand, this.btnA1, this.btnA2, this.btnA3, this.btnB1, this.btnB2, this.btnB3, this.btnC1, this.btnC2, this.btnC3, this.btnD1, this.btnD2, this.btnD3, this.btnE1, this.btnE2, this.btnE3);
	    new ControleurFinTour(this.jeu, this.btnFinTour, this.lblNomDuJoueur);
	    new ControleurDeplacer(this.jeu, this.btnDeplacer, this);
	    	  
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
		framePlateau.setBounds(100, 100, 1234, 770);
		framePlateau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePlateau.getContentPane().setLayout(null);
		
		btnPiocher = new JButton("Piocher");
		btnPiocher.setBounds(773, 40, 127, 30);
		framePlateau.getContentPane().add(btnPiocher);
		
		btnDeplacer = new JButton("deplacer vers");
		btnDeplacer.setBounds(773, 82, 127, 30);
		framePlateau.getContentPane().add(btnDeplacer);
		
		btnFinTour = new JButton("Passer la main");
		btnFinTour.setBounds(773, 122, 127, 30);
		framePlateau.getContentPane().add(btnFinTour);
				
		lblCartePiochee = new JLabel("Votre carte piochee :");
		lblCartePiochee.setBounds(683, 162, 176, 31);
		framePlateau.getContentPane().add(lblCartePiochee);
		lblCartePiochee.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				
		lblPlaceCartePiocheeNumero2 = new JLabel("Carte piochee numero 2");
		lblPlaceCartePiocheeNumero2.setBounds(864, 203, 142, 232);
		framePlateau.getContentPane().add(lblPlaceCartePiocheeNumero2);
		    
		lblPlaceCartePiocheeNumero3 = new JLabel("Carte piochee numero 3");
		lblPlaceCartePiocheeNumero3.setBounds(1029, 203, 142, 232);
		framePlateau.getContentPane().add(lblPlaceCartePiocheeNumero3);
		
		ButtonGroup rdbnGroupe2 = new ButtonGroup();
		
		rdbtnCardNum1 = new JRadioButton("Carte n\u00B01");
		rdbtnCardNum1.setSelected(true);
	    rdbtnCardNum1.setBounds(721, 442, 79, 23);
	    framePlateau.getContentPane().add(rdbtnCardNum1);
	    rdbnGroupe2.add(rdbtnCardNum1);
	    
	    rdbtnCardNum2 = new JRadioButton("Carte n\u00B02");
	    rdbtnCardNum2.setBounds(897, 442, 79, 23);
	    framePlateau.getContentPane().add(rdbtnCardNum2);
	    rdbnGroupe2.add(rdbtnCardNum2);
	    
	    rdbtnCardNum3 = new JRadioButton("Carte n\u00B03");
	    rdbtnCardNum3.setBounds(1060, 442, 79, 23);
	    framePlateau.getContentPane().add(rdbtnCardNum3);
	    rdbnGroupe2.add(rdbtnCardNum3);
		
		lblVictoryCard = new JLabel("Votre Victory Card :");
		lblVictoryCard.setBounds(693, 465, 153, 30);
		framePlateau.getContentPane().add(lblVictoryCard);
		lblVictoryCard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		lblJoueur = new JLabel("Joueur : ");
		lblJoueur.setBounds(683, 0, 74, 44);
		framePlateau.getContentPane().add(lblJoueur);
		lblJoueur.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		textCaseDeDepart = new JTextField();
	    textCaseDeDepart.setBounds(666, 83, 96, 30);
	    framePlateau.getContentPane().add(textCaseDeDepart);
	    textCaseDeDepart.setColumns(10);
	    
	    textCaseDArrivee = new JTextField();
	    textCaseDArrivee.setBounds(910, 82, 96, 30);
	    framePlateau.getContentPane().add(textCaseDArrivee);
	    textCaseDArrivee.setColumns(10);
		panel = new JPanel();
		panel.setBounds(10, 10, 657, 697);
		framePlateau.getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 5, 0, 0));
		
		btnA3 = new JButton("A3");
		panel.add(btnA3);
		btnA3.addActionListener(this);
		cardPlateauButtons.add(btnA3);
		
		btnB3 = new JButton("B3");
		panel.add(btnB3);
		btnB3.addActionListener(this);
		cardPlateauButtons.add(btnB3);
		
		btnC3 = new JButton("C3");
		panel.add(btnC3);
		btnC3.addActionListener(this);
		cardPlateauButtons.add(btnC3);
		
		btnD3 = new JButton("D3");
		panel.add(btnD3);
		btnD3.addActionListener(this);
		cardPlateauButtons.add(btnD3);
		
		btnE2 = new JButton("E2");
		panel.add(btnE2);
		btnE2.addActionListener(this);
		
		btnA2 = new JButton("A2");
		panel.add(btnA2);
		btnA2.addActionListener(this);
		cardPlateauButtons.add(btnA2);
		
		btnB2 = new JButton("B2");
		panel.add(btnB2);
		btnB2.addActionListener(this);
		cardPlateauButtons.add(btnB2);
		
		btnC2 = new JButton("C2");
		panel.add(btnC2);
		btnC2.addActionListener(this);
		cardPlateauButtons.add(btnC2);
		
		btnD2 = new JButton("D2");
		panel.add(btnD2);
		btnD2.addActionListener(this);
		cardPlateauButtons.add(btnD2);
		
		btnE3 = new JButton("E3");
		panel.add(btnE3);
		btnE3.addActionListener(this);
		
		btnA1 = new JButton("A1");
		panel.add(btnA1);
		btnA1.addActionListener(this);
		
		btnB1 = new JButton("B1");
		panel.add(btnB1);
		btnB1.addActionListener(this);
		
		btnC1 = new JButton("C1");
		panel.add(btnC1);
		btnC1.addActionListener(this);
		
		btnD1 = new JButton("D1");
		panel.add(btnD1);
		btnD1.addActionListener(this);
		
		btnE1 = new JButton("E1");
		panel.add(btnE1);
		btnE1.addActionListener(this);
		
		lblNomDuJoueur = new JLabel("");
		lblNomDuJoueur.setBounds(750, 0, 127, 34);
		framePlateau.getContentPane().add(lblNomDuJoueur);
		
		lblPlaceCartePiocheeNumero1 = new JLabel("Carte piochee numero 1");
		lblPlaceCartePiocheeNumero1.setBounds(693, 203, 142, 232);
		framePlateau.getContentPane().add(lblPlaceCartePiocheeNumero1);
		
		lblPlaceVictoryCard = new JLabel("Victory Card");
		lblPlaceVictoryCard.setBounds(683, 501, 142, 232);
		framePlateau.getContentPane().add(lblPlaceVictoryCard);
		
		cardPlateauButtons.add(btnA1);
	  	cardPlateauButtons.add(btnB1);
	  	cardPlateauButtons.add(btnC1);
	  	cardPlateauButtons.add(btnD1);
	  	cardPlateauButtons.add(btnE1);
	  	cardPlateauButtons.add(btnE2);	
	  	cardPlateauButtons.add(btnE3);	
	  	
	  	  	
	}
	
	public char getFirstCaractereCaseDeDepart() {
		String firstCaractereDeDepart = textCaseDeDepart.getText();
		char premierCaractereDepart = firstCaractereDeDepart.charAt(0);
		return premierCaractereDepart;
	}
	
	public int getSecondCaractereCaseDeDepart() {
		String secondCaractereDeDepart = textCaseDeDepart.getText();
		int secondCaractereDepart = secondCaractereDeDepart.charAt(1);
		return secondCaractereDepart;
	}
	
	public char getFirstCaractereCaseDArrivee() {
		String firstCaractereDArrivee = textCaseDArrivee.getText();
		char premierCaractereArrivee = firstCaractereDArrivee.charAt(0);
		return premierCaractereArrivee;
	}
	
	public int getSecondCaractereCaseDArrivee() {
		String secondCaractereDArrivee = textCaseDArrivee.getText();
		char secondCaractereArrivee = secondCaractereDArrivee.charAt(1);
		return secondCaractereArrivee;
	}
	
	public void setPlayerName() {
		String currentPlayer = this.jeu.getPlayerName(1); //l'idee est d'afficher le nom du joueur et changer à chaque fois que c'est au joueur suivant de jouer. 
		lblNomDuJoueur.setText(currentPlayer);            // je ne sais pas encore comment faire
	}
	 

	public void update(Observable o, Object arg) {
		if(o instanceof Jeu) {
			this.jeu = (Jeu) o;
		}
		
		if(o instanceof Joueur) {
			
			if(this.jeu.getJoueurEnCours().aPioche()) {
			  	ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
			  	Image newImage = image.getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT); 
				lblPlaceCartePiocheeNumero1.setIcon((Icon)newImage);
			}
			 //à completer, c'est un premier essai, je ne sais pas encore comment afficher l'image du bouton de depart à celui d'arrivée
			if(this.jeu.getJoueurEnCours().aDeplace()) {
				if(getFirstCaractereCaseDArrivee()=='A') {
					switch(getSecondCaractereCaseDArrivee()) {
				       case 1 : btnA1.setIcon(null); //dans setIcon() il faudrait récuperer l'image de la case de part pour que la méthode l'ajoute ensuite à la case d'arrivée
				       break;
				       case 2 : btnA2.setIcon(null);
				       break;
				       case 3 : btnA3.setIcon(null);
				       break; 
				   }
				}
				if(getFirstCaractereCaseDArrivee()=='B') {
					switch(getSecondCaractereCaseDArrivee()) {
				       case 1 : btnB1.setIcon(null);
				       break;
				       case 2 : btnB2.setIcon(null);
				       break;
				       case 3 : btnB3.setIcon(null);
				       break; 
					}
				}
				if(getFirstCaractereCaseDArrivee()=='C') {
					switch(getSecondCaractereCaseDArrivee()) {
				       case 1 : btnC1.setIcon(null);
				       break;
				       case 2 : btnC2.setIcon(null);
				       break;
				       case 3 : btnC3.setIcon(null);
				       break; 
					}
				}
				if(getFirstCaractereCaseDArrivee()=='D') {
					switch(getSecondCaractereCaseDArrivee()) {
				       case 1 : btnD1.setIcon(null);
				       break;
				       case 2 : btnD2.setIcon(null);
				       break;
				       case 3 : btnD3.setIcon(null);
				       break; 
					}
				}
				if(getFirstCaractereCaseDArrivee()=='E') {
					switch(getSecondCaractereCaseDArrivee()) {
				       case 1 : btnE1.setIcon(null);
				       break;
				       case 2 : btnE2.setIcon(null);
				       break;
				       case 3 : btnE3.setIcon(null);
				       break; 
					}
				}	
			}
									
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == btnA1) { //La méthode getSource() renvoie l'objet qui a généré l'événement
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/SQUARE_SOLID_BLUE.PNG"));//essai avec le carré bleu plein
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
			btnA1.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); //on réinitialise la pioche pour faire disparaitre la carte qui a été placée
		}
		if(e.getSource() == btnA2) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnA2.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnA3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnA3.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnB1) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage =new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnB1.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnB2) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnB2.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnB3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnB3.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnC1) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnC1.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnC2) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnC2.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnC3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnC3.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnD1) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
			btnD1.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnD2) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
			btnD2.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnD3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
			btnD3.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnE1) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnE1.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnE2) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnE2.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		if(e.getSource() == btnE3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		    ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnE3.setIcon(newImage); 
			lblPlaceCartePiocheeNumero1.setText("Carte piochee numero 1"); 
		}
		
		
		
	} 
}
