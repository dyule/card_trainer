package com.example.card_trainer;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.HorizontalLayoutElement;
import com.vaadin.testbench.elements.ImageElement;
import com.vaadin.testbench.elements.NotificationElement;

public class TrainerPage extends TestBenchTestCase {
	
	public TrainerPage(WebDriver driver) {
		setDriver(driver);
	}
	
	public void open() {
		getDriver().get("http://localhost:8080/card_trainer");
	}
	
	public List<ImageElement> getDealerCards() {
		return $(HorizontalLayoutElement.class).first().$(ImageElement.class).all();
	}
	public List<ImageElement> getPlayerCards() {
		return $(HorizontalLayoutElement.class).get(1).$(ImageElement.class).all();
	}
	
	public List<ButtonElement> getActionButtons() {
		return $(HorizontalLayoutElement.class).get(2).$(ButtonElement.class).all();
	}
	
	public NotificationElement getNotification() {
		return $(NotificationElement.class).first();
	}

}
