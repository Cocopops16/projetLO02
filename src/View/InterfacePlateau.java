package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class InterfacePlateau {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	public InterfacePlateau() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 992, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnPiocher = new JButton("Piocher");
		btnPiocher.setBounds(773, 87, 114, 30);
		frame.getContentPane().add(btnPiocher);
		
		JButton btnDeplacer = new JButton("deplacer");
		btnDeplacer.setBounds(773, 143, 114, 30);
		frame.getContentPane().add(btnDeplacer);
		
		JLabel lblCartePiochee = new JLabel("Votre carte piochee :");
		lblCartePiochee.setBounds(701, 226, 176, 31);
		frame.getContentPane().add(lblCartePiochee);
		lblCartePiochee.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		JLabel lblVictoryCard = new JLabel("Votre Victory Card :");
		lblVictoryCard.setBounds(701, 412, 153, 30);
		frame.getContentPane().add(lblVictoryCard);
		lblVictoryCard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		JLabel lblJoueur = new JLabel("Joueur : ");
		lblJoueur.setBounds(735, 21, 74, 44);
		frame.getContentPane().add(lblJoueur);
		lblJoueur.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 657, 697);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 5, 0, 0));
		
		JLabel lblcaseA3 = new JLabel("A3");
		panel.add(lblcaseA3);
		
		JLabel lblcaseB3 = new JLabel("B3");
		panel.add(lblcaseB3);
		
		JLabel lblcaseC3 = new JLabel("C3");
		panel.add(lblcaseC3);
		
		JLabel lblcaseD3 = new JLabel("D3");
		panel.add(lblcaseD3);
		
		JLabel lblcaseE3 = new JLabel("E3");
		panel.add(lblcaseE3);
		
		JLabel lblcaseA2 = new JLabel("A2");
		panel.add(lblcaseA2);
		
		JLabel lblcaseB2 = new JLabel("B2");
		panel.add(lblcaseB2);
		
		JLabel lblcaseC2 = new JLabel("C2");
		panel.add(lblcaseC2);
		
		JLabel lblcaseD2 = new JLabel("D2");
		panel.add(lblcaseD2);
		
		JLabel lblcaseE2 = new JLabel("E2");
		panel.add(lblcaseE2);
		
		JLabel lblcaseA1 = new JLabel("A1");
		panel.add(lblcaseA1);
		
		JLabel lblcaseB1 = new JLabel("B1");
		panel.add(lblcaseB1);
		
		JLabel lblcaseC1 = new JLabel("C1");
		panel.add(lblcaseC1);
		
		JLabel lblcaseD1 = new JLabel("D1");
		panel.add(lblcaseD1);
		
		JLabel lblcaseE1 = new JLabel("E1");
		panel.add(lblcaseE1);
		
		JLabel lblNomDuJoueur = new JLabel("nom ");
		lblNomDuJoueur.setBounds(808, 28, 127, 34);
		frame.getContentPane().add(lblNomDuJoueur);
		
		JLabel lblPlaceCartePiochee = new JLabel("Carte piochee");
		lblPlaceCartePiochee.setBounds(701, 267, 161, 135);
		frame.getContentPane().add(lblPlaceCartePiochee);
		
		JLabel lblPlaceVictoryCard = new JLabel("Victory Card");
		lblPlaceVictoryCard.setBounds(711, 452, 153, 226);
		frame.getContentPane().add(lblPlaceVictoryCard);
		
		
	}
}
