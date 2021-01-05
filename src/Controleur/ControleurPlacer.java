package Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import projetLO02.Joueur;

public class ControleurPlacer {
	
	private JButton btnA1;
	private JButton btnA2;
	private JButton btnA3;
	private JButton btnB1;
	private JButton btnB2;
	private JButton btnB3;
	private JButton btnC1;
	private JButton btnC2;
	private JButton btnC3;
	private JButton btnD1;
	private JButton btnD2;
	private JButton btnD3;
	private JButton btnE1;
	private JButton btnE2;
	private JButton btnE3;
	private Joueur joueurPlace;
	
	
	
	public ControleurPlacer(Joueur joueur, JButton btn) {
		
		btnA1 = btn;
		btnA2 = btn;
		btnA3 = btn;
		btnB1 = btn;
		btnB2 = btn;
		btnB3 = btn;
		btnC1 = btn;
		btnC2 = btn;
		btnC3 = btn;
		btnD1 = btn;
		btnD2 = btn;
		btnD3 = btn;
		btnE1 = btn;
		btnE2 = btn;
		btnE3 = btn;
		joueurPlace = joueur;
		
		btnA1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			    joueurPlace.placer(Card,'A', 1);
		    }
	    });
		
		btnA2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(Card,'A', 2);
			}
	   });
		
		btnA3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'A', 3);
			}
		});
		
		btnB1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'B', 1);
			}
		});
		
		btnB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'B', 2);
			}
		});
		
		btnB3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'B', 3);
			}
		});
		
		btnC1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'C', 1);				
			}
		});
		
		btnC2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'C', 2);
			}
		});
		
		btnC3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'C', 3);
			}
		});
		
		btnD1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'D', 1);
			}
		});
		
		btnD2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'D', 2);
			}
		});
		
		btnD3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'D', 3);
			}
		});
		
		btnE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'E', 1);
			}
		});
		
		btnE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'E', 2);
			}
		});
		
		btnE3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joueurPlace.placer(card, 'E', 3);
			}
		});
		
		
		
	}
	

}