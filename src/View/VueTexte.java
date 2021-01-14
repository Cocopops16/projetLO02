package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import Controleur.MsgBox;
import projetLO02.Jeu;
import projetLO02.Joueur;

@SuppressWarnings("deprecation")
public class VueTexte implements Observer, Runnable {
	
	public static String QUITTER = "Quit";
	public static String PROMPT = ">";
	public static String ADDJOUEUR = "AddJoueur";
	public static String ADDIA = "AddIA";
	public static String PLAY = "Play";
	public static String PIOCHER = "Piocher";
	public static String PLACER = "Placer";
	public static String DEPLACER = "Deplacer";
	public static String FINDETOUR = "Passer";
	private InputStream input;
	private PrintStream output;
	
	private Jeu jeu;
	
	public VueTexte(Jeu jeu, MsgBox msgBox) {
		this.jeu = jeu;
		input = System.in;
		output = System.out;
		Thread t = new Thread(this);
		t.start();
		new ThreadMsgBox(msgBox, this);
	}
	
	private class ThreadMsgBox extends Observable implements Runnable {
		String msg;
		MsgBox msgBox;
		
		public ThreadMsgBox(MsgBox msgBox, VueTexte observer) {
			this.msgBox = msgBox;
			this.addObserver(observer);
			Thread t = new Thread(this);
			t.start();
		}

		public void run() {
			while(true) {
				msg = this.msgBox.readMsg();
				this.setChanged();
				this.notifyObservers(this.msg);
			}
		}
	}
	
	public void run() {
		String saisie = null;
		boolean quitter = false;
		
		output.println( "Taper " + VueTexte.ADDJOUEUR + " pour ajouter un joueur ou " + VueTexte.ADDIA + " pour ajouter une IA. Taper " + VueTexte.PLAY + " pour jouer ; " + VueTexte.PIOCHER  + " pour piocher ; " + VueTexte.PLACER + " pour placer la carte ; " + VueTexte.DEPLACER + " pour déplacer la carte ; " + VueTexte.FINDETOUR + " pour passer la main ; " + VueTexte.QUITTER + " pour quitter.");
	
		do {
			saisie = this.lireChaine();
			
			if(saisie != null) {
				if(saisie.equals(VueTexte.ADDJOUEUR) == true) {
					Scanner name = new Scanner(input);
					output.println("Entrez le nom du joueur : ");
					String userName = name.nextLine();
					jeu.addJoueur(userName);								
			    }else if(saisie.equals(VueTexte.ADDIA) == true) {
			    	jeu.addIA();
			    }else if(saisie.equals(VueTexte.PLAY) == true) {
			    	jeu.start();
			    }else if(saisie.equals(VueTexte.PIOCHER) == true) {
			    	jeu.getJoueurEnCours().piocher();
			    }else if(saisie.equals(VueTexte.PLACER) == true) {
			    	jeu.getJoueurEnCours().placer(jeu.getJoueurEnCours().getHand().getCard(0));
			    }else if(saisie.equals(VueTexte.DEPLACER) == true) {
			    	jeu.getJoueurEnCours().deplacer();
			    }else if(saisie.equals(VueTexte.FINDETOUR) == true) {
			    	jeu.unlockJoueur();
			    }else if(saisie.equals(VueTexte.QUITTER) == true) {
					quitter = true;
				}else { output.println("Commande non reconnue ...");}
			}
			
		} while (quitter == false);
		System.exit(0);
		
	}
	
	private String lireChaine() {
		BufferedReader br = new BufferedReader (new InputStreamReader(input));
		String resultat = null;
		try {
			output.print(VueTexte.PROMPT);
		    resultat = br.readLine();
		} catch (IOException e) {
		    System.err.println(e.getMessage());
		}
		return resultat;	
	}
	
	public void afficher(String msg) {
		this.output.println(msg);
	}

	public void update(Observable o, Object arg) {
		if((o instanceof ThreadMsgBox)&&(arg instanceof String)) {
			afficher((String) arg);
		}
		if(o  instanceof Jeu) {
			Jeu jeu = (Jeu) o;
			output.println("Le joueur " +jeu.getJoueurEnCours()+ " a ");
			if(jeu.getJoueurEnCours().aPioche()) {
				output.println("pioché !");
			} else if(jeu.getJoueurEnCours().aPlace()) {
				output.println("placé !");
		    } else if(jeu.getJoueurEnCours().aDeplace()) {
		    	output.println("déplacé !");
		    }
			
		}
		
	}

}