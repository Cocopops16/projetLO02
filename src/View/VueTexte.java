package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class VueTexte implements Observer, Runnable {
	
	public static String QUITTER = "Quit";
	public static String PROMPT = ">";
	
	public VueTexte() {
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		String saisie = null;
		boolean quitter = false;
		
		System.out.println( VueTexte.QUITTER + " pour quitter.");
	
		do {
			saisie = this.lireChaine();
			
			if(saisie != null) {
								
				if(saisie.equals(VueTexte.QUITTER) == true) {
					quitter = true;
				} else { System.out.println("Commande non reconnue ...");}
			}
			
		} while (quitter == false);
		System.exit(0);
		
	}
	private String lireChaine() {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String resultat = null;
		try {
		    System.out.print(VueTexte.PROMPT);
		    resultat = br.readLine();
		} catch (IOException e) {
		    System.err.println(e.getMessage());
		}
		return resultat;	
	    }


	@Override
	public void update(Observable o, Object arg) {
		
		
	}

}