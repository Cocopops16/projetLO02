package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import projetLO02.InvalidEndOfTurnException;
import projetLO02.InvalidModeException;
import projetLO02.InvalidNbrOfPlayersException;
import projetLO02.InvalidPlayerActionException;
import projetLO02.Jeu;
import projetLO02.Joueur;
import projetLO02.Mode;
import projetLO02.Plateau;

@SuppressWarnings("deprecation")
public class VueTexte implements Observer, Runnable {
	
	public static String QUITTER = "Quit";
	public static String PROMPT = ">";
	public static String ADDPLAYER = "AddPlayer";
	public static String ADDIA = "AddIA";
	public static String SETMODE = "SetMode";
	public static String START = "Start";
	public static String CHANGEVICTORY = "ChangeVictory";
	public static String SELECTVICTORY = "SelectVictory";
	public static String PIOCHER = "Piocher";
	public static String SELECTCARD = "SelectCard";
	public static String PLACER = "Placer";
	public static String DEPLACER = "Deplacer";
	public static String FINTOUR = "FinTour";

	protected InputStream input;
	protected PrintStream output;
	
	private boolean menu;
	private boolean victory;
	private boolean partie;
	private boolean quitter;
	
	private Jeu jeu;
	
	public VueTexte(Jeu jeu) {
		this.jeu = jeu;
		jeu.addVueTexteObserver(this);
		input = System.in;
		output = System.out;
		this.menu = true;
		this.victory = true;
		this.partie = true;
		this.quitter = false;
		Thread t = new Thread(this);
		t.start();
	}
	
	private class ThreadStart implements Runnable {
		private Jeu jeu;
		
		public ThreadStart(Jeu jeu) {
			this.jeu = jeu;
			Thread t = new Thread(this);
			t.start();
		}
		
		public void run() {
			try {
				this.jeu.start();
			} catch (InvalidModeException e) {
				output.println(e.getMessage());
			} catch (InvalidNbrOfPlayersException e) {
				output.println(e.getMessage());
			}
		}
	}
	
	public String menu() {
		String saisie = null;
		
		output.println( VueTexte.QUITTER + " pour quitter.");
		output.println( VueTexte.ADDPLAYER + " pour ajouter un joueur.");
		output.println( VueTexte.ADDIA + " pour ajouter une IA.");
		output.println( VueTexte.SETMODE + " pour définir le type de partie.");
		output.println( VueTexte.START + " pour lancer la partie.");
	
		do {
			saisie = lireChaine();
			
			if(saisie != null) {
				if(saisie.equals(VueTexte.QUITTER) == true) {
					quitter = true;
				} 
				else if(saisie.equals(VueTexte.ADDPLAYER) == true) {
					output.println("Choisissez le nom du joueur :");
					try {
						this.jeu.addJoueur(lireChaine());
					} catch (InvalidNbrOfPlayersException e) {
						output.println(e.getMessage());
					}
				}
				else if(saisie.equals(VueTexte.ADDIA) == true) {
					try {
						this.jeu.addIA();
					} catch (InvalidNbrOfPlayersException e) {
						output.println(e.getMessage());
					}
				}
				else if(saisie.equals(VueTexte.SETMODE) == true) {
					output.println("Entrez le mode de jeu : \n- 1= Classique\n-2= Avancé (main de 3 cartes)\n- 3= personnalisé (choix de la VictoryCard) :");
					this.jeu.setMode(lireInt());
				}
				else if(saisie.equals(VueTexte.START) == true) {
					new ThreadStart(this.jeu);
				}
				else { 
					if(!this.menu) {
						return saisie;
					}
					output.println("Commande non reconnue ...");}
				}
		} while ((quitter == false)&&(this.menu));
		
		return null;
	}
	
	public String victory(String saisie) {
		if(this.jeu.getMode()==Mode.Personnalisé) {
			
			output.println( VueTexte.QUITTER + " pour quitter.");
			output.println( VueTexte.CHANGEVICTORY + " pour changer de Victory Card.");
			output.println( VueTexte.SELECTVICTORY + " pour accepter la Victory Card.");
		
			while ((quitter == false)&&(this.victory)) {
				if(saisie != null) {
									
					if(saisie.equals(VueTexte.QUITTER) == true) {
						quitter = true;
					} 
					else if(saisie.equals(VueTexte.CHANGEVICTORY) == true) {
						this.jeu.getJoueurEnCours().setVictory();
					}
					else if(saisie.equals(VueTexte.SELECTVICTORY) == true) {
						this.jeu.setPlayersVictory();
					}
					else { 
						if(!this.victory) {
							return saisie;
						}
						output.println("Commande non reconnue ...");
					}
				}
				
				saisie = lireChaine();
			}
			return saisie;
		}
		return saisie;
	}
	
	public void partie(String saisie) {
		
		output.println( VueTexte.QUITTER + " pour quitter.");
		output.println( VueTexte.PIOCHER + " pour ajouter une carte a votre main.");
		output.println( VueTexte.SELECTCARD + " pour choisir la carte a jouer.");
		output.println( VueTexte.PLACER + " pour placer une carte sur le plateau.");
		output.println( VueTexte.DEPLACER + " pour déplacer une carte du plateau.");
		output.println( VueTexte.FINTOUR + " pour finir votre tour.");
		
		while ((quitter == false)&&(this.partie)) {			
			if(saisie != null) {
								
				if(saisie.equals(VueTexte.QUITTER) == true) {
					quitter = true;
				} 
				else if(saisie.equals(VueTexte.PIOCHER) == true) {
					try {
						jeu.getJoueurEnCours().piocher();
					} catch (InvalidPlayerActionException e) {
						output.println(e.getMessage());
					}
					if(jeu.getJoueurEnCours().aPioche()) {
						output.println("pioché !");
					}
				}
				else if(saisie.equals(VueTexte.SELECTCARD) == true) {
					jeu.getJoueurEnCours().chooseCardToPlay();
				}
				else if(saisie.equals(VueTexte.PLACER) == true) {
					output.println("Entrez la colonne où placer la carte");
					char colonne = lireChaine().charAt(0);
					output.println("Entrez la ligne où placer la carte");
					int ligne = lireInt();
					
					if(('a'<=colonne)&&('z'>=colonne)) {
						colonne = (char)((int)colonne-32); //mise en majuscule
					}
					
					try {
						jeu.getJoueurEnCours().placer(jeu.getJoueurEnCours().getHand().getCard(0), colonne, ligne);
					} catch (InvalidPlayerActionException e) {
						output.println(e.getMessage());
					}
					
					if(jeu.getJoueurEnCours().aPlace()) {
						output.println("placé !");
				    }
				}
				else if(saisie.equals(VueTexte.DEPLACER) == true) {
					output.println("Entrez la colonne de la carte à déplacer");
					char colonne1 = lireChaine().charAt(0);
					output.println("Entrez la ligne de la carte à déplacer");
					int ligne1 = lireInt();
					if(('a'<=colonne1)&&('z'>=colonne1)) {
						colonne1 = (char)((int)colonne1-32); //mise en majuscule
					}
					
					output.println("Entrez la colonne où placer la carte");
					char colonne2 = lireChaine().charAt(0);
					output.println("Entrez la ligne où placer la carte");
					int ligne2 = lireInt();
					if(('a'<=colonne2)&&('z'>=colonne2)) {
						colonne2 = (char)((int)colonne2-32); //mise en majuscule
					}
					
					try {
						jeu.getJoueurEnCours().deplacer(colonne1, ligne1, colonne2, ligne2);
					} catch (InvalidPlayerActionException e) {
						output.println(e.getMessage());
					}
					
					if(jeu.getJoueurEnCours().aDeplace()) {
				    	output.println("déplacé !");
				    }
				}
				else if(saisie.equals(VueTexte.FINTOUR) == true) {
					try {
						jeu.unlockJoueur();
					} catch (InvalidEndOfTurnException e) {
						output.println(e.getMessage());
					}
				}
				else { output.println("Commande non reconnue ...");}
			}
			
			saisie = lireChaine();
		}
	}
	
	public void run() {		
		String commande;
		commande = menu();
		commande = victory(commande);
		partie(commande);
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
	
	public int lireInt() {
		InputStreamReader inr = new InputStreamReader(input);
		BufferedReader br = new BufferedReader(inr);
		int nbr = 0;
		try {
			output.print(VueTexte.PROMPT);
			nbr = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbr;
	}
	
	public void afficher(String msg) {
		this.output.println(msg);
	}
	
	private void givePlayerCards(Joueur player) {
		if(this.jeu.getMode()!=Mode.Avancé) {
			output.println("Victory Card :"+player.getVictory().toString());
		}
		output.println("Main du joueur : \n"+player.getHand().toString());
	}

	public void update(Observable o, Object arg) {
		if((o instanceof Jeu)&&(arg instanceof Joueur)) {
			Joueur player = (Joueur) arg;
			output.println("---------------------------------------------------------------");
			output.println("\nDébut du tour de : "+player.getName());
			if(!player.getIA()) {
				givePlayerCards(player);
			}
		}
		
		if((o instanceof Jeu)&&(arg instanceof Integer)) {
			output.println("Bienvenue "+this.jeu.getPlayerName((int) arg)+" !");
		}
		
		if((o instanceof Jeu)&&(arg instanceof Boolean)) {
			if(this.jeu.getHasStarted()) {
				output.println("Partie démarrée");
				output.println("Mode de jeu de la partie : "+this.jeu.getMode().toString());
				this.menu = false;
				if((jeu.getMode()==Mode.Personnalisé)&&(jeu.getNbrJoueurs()>0)) {
					output.println("Choix des VictoryCards :");
				}
			}
			else {
				if(jeu.getNbrVictoryCardChoosen()==jeu.getNbrJoueurs()) {
					output.println("Fin de la phase de choix des VictoryCards, place au jeu !");
					this.victory = false;
				}
			}
		}
		
		
		if((o instanceof Joueur)&&(arg instanceof Joueur)) {
			Joueur player = (Joueur) arg;
			if(!player.getIA()) {
				givePlayerCards(player);
			}
		}
		
		if((o instanceof Plateau)&&(arg instanceof Map<?, ?>)) {
			output.println(this.jeu.getPlateau().toString());
		}
	}

}