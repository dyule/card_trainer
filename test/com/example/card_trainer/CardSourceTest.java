package com.example.card_trainer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.TestCase;

public class CardSourceTest extends TestCase {

	@Test
	public void testExhaustive() throws FileNotFoundException {
		CellSource source = new FileCellSource(new FileInputStream(new File("WebContent/VAADIN/resources/basic_6deck_s17_DAS_SUR_PEEK.grid")));
		int count = 0;
		for (Cell cell : source) {
			count += 1;
		}
		assertEquals(280, count);
	}
	
}
