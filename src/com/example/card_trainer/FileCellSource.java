package com.example.card_trainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.atmosphere.cpr.Action;

public class FileCellSource implements CellSource {
	
	private ACTION pairGrid[][] = new ACTION[10][10];
	private ACTION softGrid[][] = new ACTION[8][10];
	private ACTION hardGrid[][] = new ACTION[10][10];
	
	private static ACTION convertString(String actionString) {
		switch (actionString) {
		case "H":
			return ACTION.HIT;
		case "S":
			return ACTION.STAY;
		case "Dh":
			return ACTION.DOUBLE_HIT;
		case "P":
			return ACTION.SPLIT;
		case "Ds":
			return ACTION.DOUBLE_STAY;
		case "Rh":
			return ACTION.SURRENDER;
		default:
			throw new IllegalArgumentException("Unknown action type: " + actionString);
		}
	}
	
	public FileCellSource(InputStream source) {
		Scanner sc = new Scanner(source);
		for (int pair = 0; pair < 10; pair += 1) {
			for (int dealer = 0; dealer < 10; dealer += 1) {
				String actionString = sc.next();
				pairGrid[pair][dealer] = convertString(actionString);
			}
			sc.nextLine();
		}
		for (int total = 0; total < 8; total += 1) {
			for (int dealer = 0; dealer < 10; dealer += 1) {
				String actionString = sc.next();
				softGrid[total][dealer] = convertString(actionString);
			}
			sc.nextLine();
		}
		for (int total = 0; total < 10; total += 1) {
			for (int dealer = 0; dealer < 10; dealer += 1) {
				String actionString = sc.next();
				hardGrid[total][dealer] = convertString(actionString);
			}
			sc.nextLine();
		}
		sc.close();
		
	}
	
	class CellIterator implements Iterator<Cell> {
		private boolean pairDone[][] = new boolean[10][10];
		private boolean softDone[][] = new boolean[8][10];
		private boolean hardDone[][] = new boolean[10][10];
		
		private int completed = 0;
		
		@Override
		public boolean hasNext() {
			return completed < 280;
		}
		@Override
		public Cell next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int maxRow;
			ACTION [][] grid;
			boolean [][] done;
			int row;
			int col;
			int type;
			do {
				type = (int) (Math.random() * 3);
				switch (type) {
				case 0:
					maxRow = 10;
					grid = pairGrid;
					done = pairDone;
					break;
				case 1:
					maxRow = 8;
					grid = softGrid;
					done = softDone;
					break;
				default:
					maxRow = 10;
					grid = hardGrid;
					done = hardDone;
					break;
				
				}
				row = (int)(Math.random() * maxRow);
				col = (int)(Math.random() * 10);
			} while (done[row][col]);
			done[row][col] = true;
			completed += 1;
			return createCellFor(type, row, col, grid[row][col]);
		}
		
		private Cell createCellFor(int type, int playerIndex, int dealerIndex, ACTION action) {
			int total;
			switch (type) {
			case 0:
				if (playerIndex == 9) { // Aces are stored as "high"
					total = 2;
				} else {
					total = 2  * (playerIndex + 2);
				}
				return new Cell(total, CARD.fromIndex(dealerIndex), CELL_TYPE.PAIR, action);
			case 1:
				return new Cell(13 + playerIndex, CARD.fromIndex(dealerIndex), CELL_TYPE.SOFT, action);
			default:
				return new Cell(8 + playerIndex, CARD.fromIndex(dealerIndex), CELL_TYPE.HARD, action);
				
			}
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
	}

	@Override
	public Iterator<Cell> iterator() {
		return new CellIterator();
	}


}
