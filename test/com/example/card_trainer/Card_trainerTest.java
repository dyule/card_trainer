package com.example.card_trainer;

import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.ImageElement;
import com.vaadin.testbench.elements.NotificationElement;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class contains JUnit tests, which are run using Vaadin TestBench 4.
 *
 * To run this, first get an evaluation license from
 * https://vaadin.com/addon/vaadin-testbench and follow the instructions at
 * https://vaadin.com/directory/help/installing-cval-license to install it.
 *
 * Once the license is installed, you can run this class as a JUnit test.
 */
public class Card_trainerTest extends TestBenchTestCase {
	private TrainerPage page;
	
    @Rule
    public ScreenshotOnFailureRule screenshotOnFailureRule =
            new ScreenshotOnFailureRule(this, true);

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver(); // Firefox
        page = PageFactory.initElements(driver, TrainerPage.class);

        // To use Chrome, first install chromedriver.exe from
        // http://chromedriver.storage.googleapis.com/index.html
        // on your system path (e.g. C:\Windows\System32\)
        //   setDriver(new ChromeDriver()); // Chrome

        // To use Internet Explorer, first install iedriverserver.exe from
        // http://selenium-release.storage.googleapis.com/index.html?path=2.43/
        // on your system path (e.g. C:\Windows\System32\)
        //   setDriver(new InternetExplorerDriver()); // IE

        // To test headlessly (without a browser), first install phantomjs.exe
        // from http://phantomjs.org/download.html on your system path
        // (e.g. C:\Windows\System32\)
        //   setDriver(new PhantomJSDriver()); // PhantomJS headless browser
    }
    
    @After
    public void tearDown() throws Exception {
    	driver.quit();
    }

    @Test
    public void testHandDisplayed() throws Exception {
        
        page.open();
        
        //There should be one dealer card
        List<ImageElement> dealerImage = page.getDealerCards();
        assertEquals(1, dealerImage.size());
        
        //There should be two player cards
        List<ImageElement> playerImages = page.getPlayerCards();
        assertEquals(2, playerImages.size());
        
        //There should be five possible actions to take
        List<ButtonElement> actionButtons = page.getActionButtons();
        assertEquals(5, actionButtons.size());

    }
    
    @Test
    public void TestActionNotification() throws Exception {
    	page.open();
    	
    	List<ButtonElement> actionButtons = page.getActionButtons();
    	
    	//Each action button should result in a notification when pressed
    	for (ButtonElement button: actionButtons) {
    		testActionButton(button);
    	}
    }
    
    private void testActionButton(ButtonElement button) {
    	button.click();
    	NotificationElement notification = page.getNotification();
    	assertNotNull(notification);
    	notification.close();
    }
}