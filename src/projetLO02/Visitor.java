package projetLO02;

public interface Visitor {
	
	public Card visit(Plateau plateau);
	public Card visit(Joueur joueur);

}
