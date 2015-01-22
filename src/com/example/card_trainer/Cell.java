package com.example.card_trainer;

public class Cell {
	private int handTotal;
	private CARD dealer_card;
	private CELL_TYPE type;
	private ACTION action;
	
	public Cell(int handTotal, CARD dealer_card, CELL_TYPE type, ACTION action) {
		super();
		this.handTotal = handTotal;
		this.dealer_card = dealer_card;
		this.type = type;
		this.action = action;
	}

	public int getHandTotal() {
		return handTotal;
	}

	public CARD getDealer_card() {
		return dealer_card;
	}

	public CELL_TYPE getType() {
		return type;
	}
	
	public ACTION getAction() {
		return action;
	}

	@Override
	public String toString() {
		return "Cell [handTotal=" + handTotal + ", dealer_card=" + dealer_card
				+ ", type=" + type + ", action=" + action + "]";
	}
	
	
}

enum CELL_TYPE {
	HARD,
	SOFT,
	PAIR
}