package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostiveTests {
	
	@Test
	public void logInTest() { 
		System.out.println("Starting logIn test");
		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		// Open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened.");
		
		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		// enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		// push log in button
		WebElement logInButton = driver.findElement(By.className("radius"));
		try {
			wait1.until(ExpectedConditions.elementToBeClickable(logInButton));
		} catch (TimeoutException e) {
			// few steps here
		}
		logInButton.click();
		
		// verifications
		// new url
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		
		// log out button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "logOutButton is not visible.");
		
		// Successful log in message
		WebElement successMessage = driver.findElement(By.id("flash"));
		String expectedSuccessMessage = "You logged into a secure area!";
		String actualSuccessMessage = successMessage.getText();
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
						+ expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);
		sleep(3000);
		// Close browser
		driver.quit();
	}
	
	/** Static sleep */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
}

	


		
	


