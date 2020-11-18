package projetLO02;

public class Card {
	private CardType1 type1;
	private CardType2 type2;
	private CardType3 type3;
	
	public Card(CardType1 type1, CardType2 type2, CardType3 type3) {
		this.type1 = type1;
		this.type2 = type2;
		this.type3 = type3;
	}
	
	public CardType1 getType1() {
		return this.type1;
	}
	
	public CardType2 getType2() {
		return this.type2;
	}
	
	public CardType3 getType3() {
		return this.type3;
	}
}
