package LiveDemoProject;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestClass extends BaseClass{
	
	@Test
	public void rediffMoneyLoginTest() throws InterruptedException {
		driver.get("https://money.rediff.com/index.html");
		
		//SingIn
		driver.findElement(By.linkText("Sign In")).click();
		Assert.assertEquals(driver.getTitle(), "Indian stock markets: Login to Portfolio");
		
		//Fill the SignIn Details
		driver.findElement(By.id("useremail")).sendKeys("anshulc55@rediffmail.com");
		driver.findElement(By.id("userpass")).sendKeys("Test@12345");
		
		//Code to Ask user Input for Captcha
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Captcha to fill in the text box: ");
		String inputText = scanner.nextLine();
		
		//Ask Captcha from user
		driver.findElement(By.id("captcha")).sendKeys(inputText);
//		driver.findElement(By.id("loginsubmit")).click();
//		Thread.sleep(10000);
		
		//Automate Keyboard EnterPress
		driver.findElement(By.id("userpass")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
		
		
		Assert.assertEquals(driver.getTitle(), "Rediff Moneywiz | My Portfolio(s)");
	}

}
