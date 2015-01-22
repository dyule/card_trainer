package com.example.card_trainer;

public enum CARD {
	ACE,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE,
	TEN,
	JACK,
	QUEEN,
	KING;
	
	public static CARD fromIndex(final int index) { // Aces are counted high
		switch (index) {
		case 0: return TWO;
		case 1: return THREE;
		case 2: return FOUR;
		case 3: return FIVE;
		case 4: return SIX;
		case 5: return SEVEN;
		case 6: return EIGHT;
		case 7: return NINE;
		case 8: return TEN;
		case 9: return ACE;
		default: throw new IllegalArgumentException("Card index must be from 0-9 inclusive.  Got " + index);
		}
	}
}
