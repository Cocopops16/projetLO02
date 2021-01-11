package Controleur;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import View.MonInterfacePlateau;
import projetLO02.Hand;
import projetLO02.Jeu;

public class ControleurPlacer implements ActionListener {
	
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
	private JRadioButton rdbtnCardNum1;
	private JRadioButton rdbtnCardNum2;
	private JRadioButton rdbtnCardNum3;
	
	private Hand hand;
	private Jeu jeu;
	
	
	public ControleurPlacer(Jeu j, Hand hnd, JButton boutonA1, JButton boutonA2, JButton boutonA3, JButton boutonB1, JButton boutonB2, JButton boutonB3,JButton boutonC1, JButton boutonC2, JButton boutonC3, JButton boutonD1, JButton boutonD2, JButton boutonD3, JButton boutonE1, JButton boutonE2, JButton boutonE3, JRadioButton rdbtnCardNumero1, JRadioButton rdbtnCardNumero2, JRadioButton rdbtnCardNumero3 ) {
		
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
		this.rdbtnCardNum1 = rdbtnCardNumero1;
		this.rdbtnCardNum2 = rdbtnCardNumero2;
		this.rdbtnCardNum3 = rdbtnCardNumero3;
		btnA1.addActionListener(this);
		btnA2.addActionListener(this);
		btnA3.addActionListener(this);
		btnB1.addActionListener(this);
		btnB2.addActionListener(this);
		btnB3.addActionListener(this);
		btnC1.addActionListener(this);
		btnC2.addActionListener(this);
		btnC3.addActionListener(this);
		btnD1.addActionListener(this);
		btnD2.addActionListener(this);
		btnD3.addActionListener(this);
		btnE1.addActionListener(this);
		btnE2.addActionListener(this);
		btnE3.addActionListener(this);
		
		this.hand = hnd;
		this.jeu = j;
		
		
		
		btnA1.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			if(rdbtnCardNum1.isSelected()) {
				jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 1);
			}
			else if(rdbtnCardNum2.isSelected()) {
				jeu.getJoueurEnCours().placer(hand.getCard(1),'A', 1);
			}
			else if(rdbtnCardNum3.isSelected()) {
			    jeu.getJoueurEnCours().placer(hand.getCard(2),'A', 1); 
			}
		  }     	
		});
	
		
		btnA2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
							jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 2);
				}
				else if(rdbtnCardNum2.isSelected()) {
				     		jeu.getJoueurEnCours().placer(hand.getCard(1),'A', 2);
				}
				else if(rdbtnCardNum3.isSelected()) {
						    jeu.getJoueurEnCours().placer(hand.getCard(2),'A', 2); 
				}
		     }     	
		  });
		            	
		      
		btnA3.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
						jeu.getJoueurEnCours().placer(hand.getCard(0),'A', 3);
				}
				else if(rdbtnCardNum2.isSelected()) {
						jeu.getJoueurEnCours().placer(hand.getCard(1),'A', 3);
				}
				else if(rdbtnCardNum3.isSelected()) {
					    jeu.getJoueurEnCours().placer(hand.getCard(2),'A', 3); 
				}
		      }     	
		   });
		
		btnB1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
							jeu.getJoueurEnCours().placer(hand.getCard(0),'B', 1);
				}
				else if(rdbtnCardNum2.isSelected()) {
							jeu.getJoueurEnCours().placer(hand.getCard(1),'B', 1);
				}
				else if(rdbtnCardNum3.isSelected()) {
						    jeu.getJoueurEnCours().placer(hand.getCard(2),'B', 1);
			    }
		      }     	
	       });
		
		btnB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'B', 2);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'B', 2);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'B', 2); 
				}
			  }     	
		   });
		
		btnB3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'B', 3);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'B', 3);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'B', 3); 
				}
			  }     	
		   });
		
		btnC1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'C', 1);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'C', 1);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'C', 1); 
				}
			  }     	
		   });
		
		btnC2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'C', 2);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'C', 2);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'C', 2); 
				}
			  }     	
		   });
		
		btnC3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'C', 3);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'C', 3);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'C', 3); 
				}
			  }     	
		   });
		
		btnD1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'D', 1);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'D', 1);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'D', 1); 
				}
			  }     	
		   });
		
		btnD2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'D', 2);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'D', 2);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'D', 2); 
				}
			  }     	
		   });
		
		btnD3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'D', 3);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'D', 3);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'D', 3); 
				}
			  }     	
		   });
		
		btnE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'E', 1);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'E', 1);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'E', 1); 
				}
			  }     	
		   });
		
		btnE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'E', 2);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'E', 2);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'E', 2); 
				}
			  }     	
		   });
		
		btnE3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCardNum1.isSelected()) {
								jeu.getJoueurEnCours().placer(hand.getCard(0),'E', 3);
				}
				else if(rdbtnCardNum2.isSelected()) {
					     		jeu.getJoueurEnCours().placer(hand.getCard(1),'E', 3);
				}
				else if(rdbtnCardNum3.isSelected()) {
							    jeu.getJoueurEnCours().placer(hand.getCard(2),'E', 3); 
				}
			  }     	
		   });
	
	}
			
	public void actionPerformed(ActionEvent e) { 
      if(rdbtnCardNum1.isSelected()) {
				
		if(e.getSource() == btnA1) { //La méthode getSource() renvoie l'objet qui a généré l'événement
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));//essai avec le carré bleu plein
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
			btnA1.setIcon(newImage); 
			
		}
		if(e.getSource() == btnA2) { 
			
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnA2.setIcon(newImage); 
			
		}
		if(e.getSource() == btnA3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnA3.setIcon(newImage); 
			
		}
		if(e.getSource() == btnB1) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage =new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnB1.setIcon(newImage); 
			
		}
		if(e.getSource() == btnB2) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnB2.setIcon(newImage); 
			
		}
		if(e.getSource() == btnB3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnB3.setIcon(newImage); 
			
		}
		if(e.getSource() == btnC1) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnC1.setIcon(newImage); 
			
		}
		if(e.getSource() == btnC2) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnC2.setIcon(newImage); 
			
		}
		if(e.getSource() == btnC3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnC3.setIcon(newImage); 
			
		}
		if(e.getSource() == btnD1) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
			btnD1.setIcon(newImage); 
			
		}
		if(e.getSource() == btnD2) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
			btnD2.setIcon(newImage); 
		
		}
		if(e.getSource() == btnD3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
			btnD3.setIcon(newImage); 
			
		}
		if(e.getSource() == btnE1) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnE1.setIcon(newImage); 
		
		}
		if(e.getSource() == btnE2) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnE2.setIcon(newImage); 
		
		}
		if(e.getSource() == btnE3) { 
			ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(0).getType1()+"_"+hand.getCard(0).getType2()+"_"+hand.getCard(0).getType3()+".PNG"));
		    ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
			btnE3.setIcon(newImage); 
		}
	  }else if (rdbtnCardNum2.isSelected()) {
		  if(e.getSource() == btnA1) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
				btnA1.setIcon(newImage); 
				
			}
			if(e.getSource() == btnA2) { 
				
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnA2.setIcon(newImage); 
				
			}
			if(e.getSource() == btnA3) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnA3.setIcon(newImage); 
				
			}
			if(e.getSource() == btnB1) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage =new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnB1.setIcon(newImage); 
				
			}
			if(e.getSource() == btnB2) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnB2.setIcon(newImage); 
				
			}
			if(e.getSource() == btnB3) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnB3.setIcon(newImage); 
				
			}
			if(e.getSource() == btnC1) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnC1.setIcon(newImage); 
				
			}
			if(e.getSource() == btnC2) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnC2.setIcon(newImage); 
				
			}
			if(e.getSource() == btnC3) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnC3.setIcon(newImage); 
				
			}
			if(e.getSource() == btnD1) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
				btnD1.setIcon(newImage); 
				
			}
			if(e.getSource() == btnD2) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
				btnD2.setIcon(newImage); 
			
			}
			if(e.getSource() == btnD3) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
				btnD3.setIcon(newImage); 
				
			}
			if(e.getSource() == btnE1) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnE1.setIcon(newImage); 
			
			}
			if(e.getSource() == btnE2) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnE2.setIcon(newImage); 
			
			}
			if(e.getSource() == btnE3) { 
				ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(1).getType1()+"_"+hand.getCard(1).getType2()+"_"+hand.getCard(1).getType3()+".PNG"));
			    ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
				btnE3.setIcon(newImage); 
			}
		}else if (rdbtnCardNum3.isSelected()) {
				if(e.getSource() == btnA1) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
					btnA1.setIcon(newImage); 
					
				}
				if(e.getSource() == btnA2) { 
					
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnA2.setIcon(newImage); 
					
				}
				if(e.getSource() == btnA3) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnA3.setIcon(newImage); 
					
				}
				if(e.getSource() == btnB1) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage =new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnB1.setIcon(newImage); 
					
				}
				if(e.getSource() == btnB2) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnB2.setIcon(newImage); 
					
				}
				if(e.getSource() == btnB3) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnB3.setIcon(newImage); 
					
				}
				if(e.getSource() == btnC1) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnC1.setIcon(newImage); 
					
				}
				if(e.getSource() == btnC2) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnC2.setIcon(newImage); 
					
				}
				if(e.getSource() == btnC3) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnC3.setIcon(newImage); 
					
				}
				if(e.getSource() == btnD1) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
					btnD1.setIcon(newImage); 
					
				}
				if(e.getSource() == btnD2) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
					btnD2.setIcon(newImage); 
				
				}
				if(e.getSource() == btnD3) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT)); 
					btnD3.setIcon(newImage); 
					
				}
				if(e.getSource() == btnE1) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnE1.setIcon(newImage); 
				
				}
				if(e.getSource() == btnE2) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				  	ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnE2.setIcon(newImage); 
				
				}
				if(e.getSource() == btnE3) { 
					ImageIcon image = new ImageIcon(MonInterfacePlateau.class.getResource("/image/"+hand.getCard(2).getType1()+"_"+hand.getCard(2).getType2()+"_"+hand.getCard(2).getType3()+".PNG"));
				    ImageIcon newImage = new ImageIcon((image).getImage().getScaledInstance(142,232, Image.SCALE_DEFAULT));
					btnE3.setIcon(newImage); 
				}
			}
	} 
	
}