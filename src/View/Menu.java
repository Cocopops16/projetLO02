package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;
	public ArrayList<String> playerIds;

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
		playerIds = new ArrayList<>();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.setBounds(100, 100, 644, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPane.setBounds(305, 111, 193, 28);
		frame.getContentPane().add(textPane);
		
		JLabel lblMenu = new JLabel("Shape Up !");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblMenu.setBounds(195, 10, 210, 44);
		frame.getContentPane().add(lblMenu);
		
		JLabel lblEntrezNomJoueur = new JLabel("Entrez le nom des joueurs :");
		lblEntrezNomJoueur.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEntrezNomJoueur.setBounds(10, 111, 283, 28);
		frame.getContentPane().add(lblEntrezNomJoueur);
		
		JLabel lblJoueur1 = new JLabel("");
		lblJoueur1.setBounds(66, 203, 80, 28);
		frame.getContentPane().add(lblJoueur1);
		
		JLabel lblJoueur2 = new JLabel("");
		lblJoueur2.setBounds(274, 203, 80, 28);
		frame.getContentPane().add(lblJoueur2);
		
		JLabel lblJoueur3 = new JLabel("");
		lblJoueur3.setBounds(473, 203, 80, 28);
		frame.getContentPane().add(lblJoueur3);
		
		JButton btnSaveJoueur = new JButton("save");
		btnSaveJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textPane.getText().isEmpty()) {
					JLabel message = new JLabel("Entrez un nom!");
					message.setFont(new Font("Tahoma", Font.PLAIN, 15));
					JOptionPane.showMessageDialog(null, message);
				}
				else { String name = textPane.getText().trim(); //copie le texte présent dans le Jtext
				playerIds.add(name);
				
				  if(playerIds.size() == 1) { 
					lblJoueur1.setText(playerIds.get(0));
				  }
				  else if (playerIds.size() == 2) {
					lblJoueur1.setText(playerIds.get(0));
					lblJoueur2.setText(playerIds.get(1));
			      }
				  else if (playerIds.size() == 3) {
					lblJoueur1.setText(playerIds.get(0));
					lblJoueur2.setText(playerIds.get(1));
					lblJoueur3.setText(playerIds.get(2));
				  }
				  if(playerIds.size()>0 && playerIds.size()<4) {
					  JLabel message = new JLabel("Le nom  a été enregistré avec succès !");
					  message.setFont(new Font("Tahoma", Font.PLAIN, 15));
					  JOptionPane.showMessageDialog(null, message);
					  textPane.setText(""); //réinitialise la zone de text pour 
				  }
				  if(playerIds.size() == 4) {
					  playerIds.remove(name);
					  JLabel message = new JLabel("Il ne peut pas y avoir plus de 3 joueurs !");
					  message.setFont(new Font("Tahoma", Font.PLAIN, 15));
					  JOptionPane.showMessageDialog(null, message);
					  textPane.setText("");
				  }
				}
			}
		});
		
		btnSaveJoueur.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSaveJoueur.setBounds(508, 111, 112, 30);
		frame.getContentPane().add(btnSaveJoueur);
		
		JButton btnLancerPartie = new JButton("Jouer");
		btnLancerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				new InterfacePlateau().getFrame().setVisible(true); //permet d'ouvrir l'interface graphique "interfacePlateau"
			}
		});
		btnLancerPartie.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLancerPartie.setBounds(244, 334, 129, 56);
		frame.getContentPane().add(btnLancerPartie);
	}
}
