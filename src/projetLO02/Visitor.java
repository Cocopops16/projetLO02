package projetLO02;

import java.util.Map;

public interface Visitor {
	
	public Map<String, Object> visit(Plateau plateau);
	public Card visit(Joueur joueur);

}
