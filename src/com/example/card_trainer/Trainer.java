package com.example.card_trainer;

import java.util.Iterator;
import java.util.Random;

public class Trainer {

	
	private Iterator<Cell> cells;
	private DealDisplayer display;
	private Random random;
	private Cell currentCell;
	private ResultDisplayer result;
	
	public Trainer(CellSource source, DealDisplayer display, ResultDisplayer result) {
		this.cells = source.iterator();
		this.display = display;
		this.result = result;
		random = new Random();
		displayNextCell();
	}
	
	private CARD getFromPips(int pips) {
		switch (pips) {
		case 1:
		case 11: return CARD.ACE;
		case 2: return CARD.TWO;
		case 3: return CARD.THREE;
		case 4: return CARD.FOUR;
		case 5: return CARD.FIVE;
		case 6: return CARD.SIX;
		case 7: return CARD.SEVEN;
		case 8: return CARD.EIGHT;
		case 9: return CARD.NINE;
		default:
			int ten = random.nextInt(4);
			switch(ten) {
			case 0: return CARD.TEN;
			case 1: return CARD.JACK;
			case 2: return CARD.QUEEN;
			default: return CARD.KING;
			}
		}
			
	}
	
	private void displayNextCell() {
		currentCell = cells.next();
		CARD [] playerCards;
		switch(currentCell.getType()) {
		case HARD:
			// Find the lowest possible value that could be included in a pair which adds to playerTotal
			int lowest = Math.max(currentCell.getHandTotal() - 10, 2);
			int highest = Math.min(10, currentCell.getHandTotal() - 2);
			int card_one = random.nextInt(highest - lowest) + lowest;
			int card_two = currentCell.getHandTotal() - card_one;
			playerCards = new CARD[]{getFromPips(card_one), getFromPips(card_two)};
			break;
		case SOFT:
			playerCards = new CARD[]{getFromPips(currentCell.getHandTotal() - 11), CARD.ACE};
			break;
		default:
			playerCards = new CARD[]{getFromPips(currentCell.getHandTotal() / 2), getFromPips(currentCell.getHandTotal() / 2)};
			break;
		}
		display.setDeal(currentCell.getDealer_card(), playerCards);
	}
	
	public void didHit() {
		if (currentCell.getAction() == ACTION.HIT) {
			result.wasRight();
		} else {
			result.wasWrong();
		}
		displayNextCell();
	}
	
	public void didStay() {
		if (currentCell.getAction() == ACTION.STAY) {
			result.wasRight();
		} else {
			result.wasWrong();
		}
		displayNextCell();
	}
	
	public void didDouble() {
		if (currentCell.getAction() == ACTION.DOUBLE || currentCell.getAction() == ACTION.DOUBLE_HIT || currentCell.getAction() == ACTION.DOUBLE_STAY) {
			result.wasRight();
		} else {
			result.wasWrong();
		}
		displayNextCell();
	}
	
	public void didSplit() {
		if (currentCell.getAction() == ACTION.SPLIT) {
			result.wasRight();
		} else {
			result.wasWrong();
		}
		displayNextCell();
	}
	public void didSurrender() {
		if (currentCell.getAction() == ACTION.SURRENDER) {
			result.wasRight();
		} else {
			result.wasWrong();
		}
		displayNextCell();
	}
}
