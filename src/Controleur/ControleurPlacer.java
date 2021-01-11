package Controleur;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import projetLO02.Hand;
import projetLO02.Jeu;
import projetLO02.Joueur;
import projetLO02.Mode;

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
	
	private Hand hand;
	private Jeu jeu;
	
	
	public ControleurPlacer(Jeu jeu, Hand hnd, JButton boutonA1, JButton boutonA2, JButton boutonA3, JButton boutonB1, JButton boutonB2, JButton boutonB3,JButton boutonC1, JButton boutonC2, JButton boutonC3, JButton boutonD1, JButton boutonD2, JButton boutonD3, JButton boutonE1, JButton boutonE2, JButton boutonE3) {
		
		this.btnA1 = boutonA1;
		this.btnA2 = boutonA2;
		this.btnA3 = boutonA3;
		this.btnB1 = boutonB1;
		this.btnB2 = boutonB2;
		this.btnB3 = boutonB3;
		this.btnC1 = boutonC1;
		this.btnC2 = boutonC2;
		this.btnC3 = boutonC3;
		this.btnD1 = boutonD1;
		this.btnD2 = boutonD2;
		this.btnD3 = boutonD3;
		this.btnE1 = boutonE1;
		this.btnE2 = boutonE2;
		this.btnE3 = boutonE3;
		this.hand = hnd;
		this.jeu = jeu;
		
		
	if(this.jeu.getMode() == Mode.Classique && this.jeu.getMode() == Mode.Personnalisé) {
						
		btnA1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 1);
			    
		    }
	    });
		
		btnA2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 2);
			}
	   });
		
		btnA3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'A', 3);
			}
		});
		
		btnB1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'B', 1);
			}
		});
		
		btnB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'B', 2);
			}
		});
		
		btnB3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'B', 3);
			}
		});
		
		btnC1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'C', 1);				
			}
		});
		
		btnC2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'C', 2);
			}
		});
		
		btnC3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'C', 3);
			}
		});
		
		btnD1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'D', 1);
			}
		});
		
		btnD2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'D', 2);
			}
		});
		
		btnD3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'D', 3);
			}
		});
		
		btnE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'E', 1);
			}
		});
		
		btnE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'E', 2);
			}
		});
		
		btnE3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.getJoueurEnCours().placer(hand.getCard(0), 'E', 3);
			}
		});
	}
		
	if(this.jeu.getMode() == Mode.Avancé) {
			
		btnA1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 1);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'A', 1);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'A', 1);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 1);
		            	
		        }
			}
	    });
		
		btnA2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 2);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'A', 2);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'A', 2);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 2);
		            	
		        }
			}
	   });
		
		btnA3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 3);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'A', 3);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'A', 3);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 3);
		            	
		        }
			}
		});
		
		btnB1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'B', 1);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'B', 1);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'B', 1);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'B', 1);
		            	
		        }
			}
		});
		
		btnB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'B', 2);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'B', 2);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'B', 2);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'B', 2);
		            	
		        }
			}
		});
		
		btnB3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'B', 3);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'B', 3);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'B', 3);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'B', 3);
		            	
		        }
			}
		});
		
		btnC1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'C', 1);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'C', 1);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'C', 1);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'C', 1);
		            	
		        }				
			}
		});
		
		btnC2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'C', 2);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'C', 2);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'C', 2);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'C', 2);
		            	
		        }
			}
		});
		
		btnC3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'C', 3);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'C', 3);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'C', 3);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'C', 3);
		            	
		        }
			}
		});
		
		btnD1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'D', 1);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'D', 1);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'D', 1);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'D', 1);
		            	
		        }
			}
		});
		
		btnD2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'D', 2);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'D', 2);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'D', 2);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'D', 2);
		            	
		        }
			}
		});
		
		btnD3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'D', 3);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'D', 3);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'D', 3);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'D', 3);
		            	
		        }
			}
		});
		
		btnE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'E', 1);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'E', 1);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'E', 1);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'E', 1);
		            	
		        }
			}
		});
		
		btnE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'E', 2);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'E', 2);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'E', 2);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'E', 2);
		            	
		        }
			}
		});
		
		btnE3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel message = new JLabel("Choisissez votre carte à placer : 1 ou 2 ou 3 ");
				message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    	String numero = JOptionPane.showInputDialog(null, message);
		    	int resultatNumero = Integer.parseInt(numero); 
		          switch(resultatNumero) {
		            case 1 :  
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'E', 3);
		                break;
		            case 2 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(1),'E', 3);
		            	break;
		            case 3 : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(2),'E', 3);
		            	break;
		            default : 
		            	jeu.getJoueurEnCours().placer(hand.getCard(0),'E', 3);
		            	
		        }
			}
		});
	
	
	}
			
  
	
	}
		
}