package com.example.card_trainer;

import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.TestCase;

public class CardSource_test extends TestCase {

	@Test
	public void testExhaustive() throws FileNotFoundException {
		CellSource source = new FileCellSource("WebContent/basic_6deck_s17_DAS_SUR_PEEK.grid");
		int count = 0;
		for (Cell cell : source) {
			count += 1;
		}
		assertEquals(280, count);
	}
	
}
