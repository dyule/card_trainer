package com.example.card_trainer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("card_trainer")
public class Card_trainerUI extends UI implements DealDisplayer, ResultDisplayer {

//	@WebServlet(value = "/*", asyncSupported = true)
//	@VaadinServletConfiguration(productionMode = false, ui = Card_trainerUI.class)
//	public static class Servlet extends VaadinServlet {
//	}
	

	private Layout dealerLayout;
	private Layout playerLayout;
	private CellSource cellSource;
	
	private final Resource[][] cards = {loadImagesForSuit("clubs"), loadImagesForSuit("diamonds"), loadImagesForSuit("hearts"), loadImagesForSuit("spades")};
	
	protected static Resource[] loadImagesForSuit(String suit) {
		Resource [] images = new Resource[13];
		for (int pips = 2; pips <= 10; pips += 1) {
			images[pips - 1] = new ThemeResource("card_imgs/" + Integer.toString(pips) + "_of_" + suit + ".png"); 
		}
		images[0] = new ThemeResource("card_imgs/ace_of_" + suit + ".png");
		images[10] = new ThemeResource("card_imgs/jack_of_" + suit + ".png");
		images[11] = new ThemeResource("card_imgs/queen_of_" + suit + ".png");
		images[12] = new ThemeResource("card_imgs/king_of_" + suit + ".png");
		return images;
	}
	
	public void setDeal(CARD dealerCard, CARD[] playerCards) {
		dealerLayout.removeAllComponents();
		Image dealerImage = new Image(null, getImageFor(dealerCard));
		dealerImage.setHeight("100%");
		dealerLayout.addComponent(dealerImage);
		
		
		playerLayout.removeAllComponents();
		for (CARD playerCard: playerCards) {
			Image playerImage = new Image(null, getImageFor(playerCard));
			playerImage.setHeight("100%");
			playerLayout.addComponent(playerImage);
		}
	}
	
	public void wasWrong() {
		Notification.show("WRONG", Notification.Type.WARNING_MESSAGE);
	}
	
	public void wasRight() {
		Notification.show("RIGHT", Notification.Type.HUMANIZED_MESSAGE);
	}
	
	private Resource getImageFor(CARD card) {
		int suit = (int) (Math.random() * 4);
		switch (card) {
		case ACE:
			return cards[suit][0];
		case TWO:
			return cards[suit][1];
		case THREE:
			return cards[suit][2];
		case FOUR:
			return cards[suit][3];
		case FIVE:
			return cards[suit][4];
		case SIX:
			return cards[suit][5];
		case SEVEN:
			return cards[suit][6];
		case EIGHT:
			return cards[suit][7];
		case NINE:
			return cards[suit][8];
		case TEN:
			return cards[suit][9];
		case JACK:
			return cards[suit][10];
		case QUEEN:
			return cards[suit][11];
		default:
			return cards[suit][12];
		
		}
	}

	@Override
	protected void init(VaadinRequest request) {
		ServletContext context = VaadinServlet.getCurrent().getServletContext();
		CellSource source = new FileCellSource(context.getResourceAsStream("/VAADIN/resources/basic_6deck_s17_DAS_SUR_PEEK.grid"));
		
	
	
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSizeFull();
		setContent(layout);
		
		dealerLayout = new HorizontalLayout();
		dealerLayout.setHeight("90%");
		playerLayout = new HorizontalLayout();
		playerLayout.setHeight("90%");
		
		layout.addComponent(dealerLayout);
		layout.addComponent(playerLayout);
		layout.setExpandRatio(dealerLayout, 4);
		layout.setExpandRatio(playerLayout, 4);
		
		final Trainer trainer = new Trainer(source, this, this);
		
		Button hit = new Button("Hit");
		hit.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				trainer.didHit();
				
			}
		});
		Button stay = new Button("Stay");
		stay.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				trainer.didStay();
				
			}
		});
		Button dbl = new Button("Double");
		dbl.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				trainer.didDouble();
				
			}
		});
		Button split = new Button("Split");
		split.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				trainer.didSplit();
				
			}
		});
		Button surrender = new Button("Surrender");
		surrender.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				trainer.didSurrender();
				
			}
		});
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		
		buttonLayout.addComponent(hit);
		buttonLayout.addComponent(stay);
		buttonLayout.addComponent(dbl);
		buttonLayout.addComponent(split);
		buttonLayout.addComponent(surrender);
		
		
		layout.addComponent(buttonLayout);

		
	}

}