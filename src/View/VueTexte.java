package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

import Controleur.MsgBox;
import projetLO02.Jeu;
import projetLO02.Joueur;

@SuppressWarnings("deprecation")
public class VueTexte implements Observer, Runnable {
	
	public static String QUITTER = "Quit";
	public static String PROMPT = ">";
	private InputStream input;
	private PrintStream output;
	
	public VueTexte(Jeu jeu, MsgBox msgBox) {
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
		
		output.println( VueTexte.QUITTER + " pour quitter.");
	
		do {
			saisie = this.lireChaine();
			
			if(saisie != null) {
								
				if(saisie.equals(VueTexte.QUITTER) == true) {
					quitter = true;
				} else { output.println("Commande non reconnue ...");}
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
		
	}

}