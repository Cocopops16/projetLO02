package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JButton btnDeplacer = new JButton("deplacer vers");
		btnDeplacer.setBounds(773, 143, 114, 30);
		frame.getContentPane().add(btnDeplacer);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(701, 143, 62, 30);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(897, 143, 62, 30);
		frame.getContentPane().add(textPane_1);
		
		JLabel lblCartePiochee = new JLabel("Votre carte piochee :");
		lblCartePiochee.setBounds(701, 226, 176, 31);
		frame.getContentPane().add(lblCartePiochee);
		lblCartePiochee.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		JLabel lblVictoryCard = new JLabel("Votre Victory Card :");
		lblVictoryCard.setBounds(701, 439, 153, 30);
		frame.getContentPane().add(lblVictoryCard);
		lblVictoryCard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		JLabel lblNomJoueur = new JLabel("Joueur : ");
		lblNomJoueur.setBounds(773, 10, 74, 44);
		frame.getContentPane().add(lblNomJoueur);
		lblNomJoueur.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		
		
	}
}
