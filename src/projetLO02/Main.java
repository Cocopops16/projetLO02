package projetLO02;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jeu jeu = new Jeu();
		jeu.start();
		while(!jeu.checkEndGame()) {
			jeu.tourDeJeu();
		}
		if(jeu.checkEndGame()) {
			jeu.comptagePoints();
			System.out.println("End of the game");
		}
		else System.out.println("Probl�me critique dans le d�roulement de la partie");
	}

}
