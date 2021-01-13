package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class ControleurFinPartie {
	
	
	private JButton btnFermer;
	private JFrame frameFinPartie;
	
		
	public ControleurFinPartie(JButton boutonFermer, JFrame frameFinpartie) {
		
		
	    this.btnFermer= boutonFermer;
		this.frameFinPartie = frameFinpartie;
			
		
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameFinPartie.setVisible(false); // permet de fermer l'interface fin de partie
			}
		});
		
	}
	
}