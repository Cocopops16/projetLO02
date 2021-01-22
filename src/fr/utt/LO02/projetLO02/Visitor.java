package fr.utt.LO02.projetLO02;

import java.util.Map;

/**
 * Interface du patron de conception Visitor
 * 
 * @author Corentin Réault
 * @version 1.0
 * 
 * @see ScoreBodyVisitor
 * @see ScoreColorVisitor
 * @see ScoreShapeVisitor
 */
public interface Visitor {
	
	public Map<String, Object> visit(Map<String, Object> positions);
	public int visit(Map<String, Object> positions, Card card);
}
