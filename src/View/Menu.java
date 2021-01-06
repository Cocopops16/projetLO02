package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JRadioButton;

import Controleur.ControleurMenu;
import projetLO02.Jeu;

@SuppressWarnings("deprecation")
public class Menu implements Observer {

	private JFrame frame;
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
	
	protected Jeu jeu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
		jeu = new Jeu(this);
		
		new ControleurMenu(this.jeu, this.textPane, this.lblJoueur1, this.lblJoueur2, this.lblJoueur3, this.btnSaveJoueur, this.btnAddIA, this.rdbtnModeClassique, this.rdbtnModeAvance, this.rdbtnModePerso, this.btnLancerPartie);
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.setBounds(100, 100, 644, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane.setBounds(305, 111, 193, 28);
		frame.getContentPane().add(textPane);
		
		lblMenu = new JLabel("Shape Up !");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblMenu.setBounds(195, 10, 210, 44);
		frame.getContentPane().add(lblMenu);
		
		lblEntrezNomJoueur = new JLabel("Entrez le nom des joueurs :");
		lblEntrezNomJoueur.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEntrezNomJoueur.setBounds(10, 111, 283, 28);
		frame.getContentPane().add(lblEntrezNomJoueur);
		
		lblJoueur1 = new JLabel("");
		lblJoueur1.setBounds(66, 203, 80, 28);
		frame.getContentPane().add(lblJoueur1);
		
		lblJoueur2 = new JLabel("");
		lblJoueur2.setBounds(274, 203, 80, 28);
		frame.getContentPane().add(lblJoueur2);
		
		lblJoueur3 = new JLabel("");
		lblJoueur3.setBounds(473, 203, 80, 28);
		frame.getContentPane().add(lblJoueur3);
		
		btnSaveJoueur = new JButton("save");
		btnSaveJoueur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSaveJoueur.setBounds(508, 111, 112, 30);
		frame.getContentPane().add(btnSaveJoueur);
		
		btnAddIA = new JButton("Add IA");
		btnAddIA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddIA.setBounds(274, 164, 80, 28);
		frame.getContentPane().add(btnAddIA);
		
		lblChooseMode = new JLabel("Choisissez le mode de jeu :");
		lblChooseMode.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblChooseMode.setBounds(10, 242, 283, 28);
		frame.getContentPane().add(lblChooseMode);
		
		ButtonGroup rdbnGroupe = new ButtonGroup();
		
		rdbtnModeClassique = new JRadioButton("Mode Classique");
		rdbtnModeClassique.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnModeClassique.setSelected(true);
		rdbtnModeClassique.setBounds(296, 250, 136, 23);
		frame.getContentPane().add(rdbtnModeClassique);
		rdbnGroupe.add(rdbtnModeClassique);
		
		rdbtnModeAvance = new JRadioButton("Mode Avanc\u00E9");
		rdbtnModeAvance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnModeAvance.setBounds(296, 277, 123, 23);
		frame.getContentPane().add(rdbtnModeAvance);
		rdbnGroupe.add(rdbtnModeAvance);
		
		rdbtnModePerso = new JRadioButton("Mode Personnalis\u00E9");
		rdbtnModePerso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnModePerso.setBounds(296, 303, 153, 23);
		frame.getContentPane().add(rdbtnModePerso);
		rdbnGroupe.add(rdbtnModePerso);
		
		btnLancerPartie = new JButton("Jouer");
		btnLancerPartie.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLancerPartie.setBounds(245, 414, 129, 56);
		frame.getContentPane().add(btnLancerPartie);	
	}

	public void update(Observable o, Object arg) {
		if(o instanceof Jeu) {
			this.jeu = (Jeu) o;
		}
	}
}
