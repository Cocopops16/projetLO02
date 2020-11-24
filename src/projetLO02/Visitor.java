package projetLO02;

import java.util.Map;

public interface Visitor {
	
	public Map<String, Object> visit(Map<String, Object> positions);
	public int visit(Map<String, Object> positions, Card card);
}
