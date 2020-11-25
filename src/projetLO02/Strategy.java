package projetLO02;

import java.util.Map;

public interface Strategy {
	public String searchBestPosition(Map<String, Object> positions, Card victoryCard);
}
