package PageClasses;

import java.util.List;

import javax.swing.LookAndFeel;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class MyPorfolioPage extends PageBaseClass{
	
	public TopMenuClass topmenu;
	
	public MyPorfolioPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topmenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topmenu);
	}
	
	@FindBy(xpath="//*[@id='headcontent']/div[1]/div[1]/a/span")
	public WebElement moneyBiz_text;
	
	@FindBy(id="createPortfolio")
	public WebElement createPortfolio_Btn;
	
	@FindBy(id="create")
	public WebElement createportfolio_textbox;
	
	@FindBy(id="createPortfolioButton")
	public WebElement submitCreatePortfolio_Btn;
	
	@FindBy(id="portfolioid")
	public WebElement myPortfolioList;
	
	@FindBy(id="deletePortfolio")
	public WebElement deletePortfolio_Btn;
	
	@FindBy(id="addStock")
	public WebElement addStock_Btn;
	
	@FindBy(id="addstockname")
	public WebElement stockName_TextBox;
	
	@FindBy(xpath="//*[@id='ajax_listOfOptions']/div[1]")
	public WebElement stockValue;
	
	@FindBy(id="addstockqty")
	public WebElement stockQualitity_txtBox;
	
	@FindBy(id="addstockprice")
	public WebElement stockPrice_TxtBox;
	
	@FindBy(id="addStockButton")
	public WebElement submitStock_Btn;
	
	@FindBy(id="stock")
	public WebElement StockTable;
	
	@FindBy(id="stockPurchaseDate")
	public WebElement stockPurchaseDate;
	
	
	public void clickStockPurchaseCalendar(){
		
		try {
			stockPurchaseDate.click();
			logger.log(Status.PASS, "Clicked the Stock purchase Calendar");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public MyPorfolioPage submitStock(){
		try {
			submitStock_Btn.click();
			logger.log(Status.PASS, "Submitted the Stock");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		MyPorfolioPage myportfolio = new MyPorfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;
	}
	
	public void verifyStock(String StockName){
		boolean flag= false;
		try {
			List<WebElement> tableRows = StockTable.findElements(By.xpath("/tbody/tr"));
			
			for (WebElement row : tableRows) {
				List<WebElement> tableColumsn = row.findElements(By.tagName("td"));
				
				for (WebElement column : tableColumsn) {
					if(column.getText().equalsIgnoreCase(StockName)){
						flag=true;
					}
				}
			}
			Assert.assertTrue(flag, "Given Stock : " +StockName + " is not present in this Portfolio");
			logger.log(Status.PASS, "Given Stock : " +StockName + " is not present in this Portfolio");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	
	public void enterStockPrice(String stockPrice){
		try {
			stockPrice_TxtBox.sendKeys(stockPrice);
			logger.log(Status.PASS, "Entered the Price : " + stockPrice);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	
	public void enterStockQuantity(String quantity){
		try {
			stockQualitity_txtBox.sendKeys(quantity);
			logger.log(Status.PASS, "Add the Quantity : " + quantity);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void enterStockName(String stockName){
		try {
			stockName_TextBox.sendKeys(stockName);
			waitForPageLoad();
			stockValue.click();
			logger.log(Status.PASS, "Typed Stock Name : " + stockName);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void clickAddStock(){
		try {
			addStock_Btn.click();
			logger.log(Status.PASS, "Clicked the Add Stock Button");
		} catch (Exception e) {
			reportFail(e.getMessage());;
		}
	}
	
	public MyPorfolioPage deletePortFolio(){
		try {
			deletePortfolio_Btn.click();
			acceptAlert();
			logger.log(Status.PASS, "Deleted the Portfolio");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		MyPorfolioPage myportfolio = new MyPorfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;
	}
	
	public MyPorfolioPage selectPortfolio(String Value){
		selectDropDownValue(myPortfolioList, Value);
		MyPorfolioPage myportfolio = new MyPorfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;
	}
	
	public void isPorfolioExists(String portfolio){
		boolean flag = false;
		try {
			List<WebElement> allOptions = getAllElementsOfDropDown(myPortfolioList);
			for (WebElement option : allOptions) {
				if (option.getText().equalsIgnoreCase(portfolio)){
					flag=true;
				}else{
					flag = false;
				}
			}
			Assert.assertTrue(flag);
			logger.log(Status.PASS, "Given Portfolio : " + portfolio + " , Present in Portfolio List");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	
	public void isPorfolioDeleted(String portfolio){
		boolean flag = false;
		try {
			List<WebElement> allOptions = getAllElementsOfDropDown(myPortfolioList);
			for (WebElement option : allOptions) {
				if (!option.getText().equalsIgnoreCase(portfolio)){
					flag=true;
				}else{
					flag = false;
				}
			}
			Assert.assertTrue(flag);
			logger.log(Status.PASS, "Given Portfolio : " + portfolio + " , is not Prsent in Portfolio List");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public MyPorfolioPage submitPortfolio(){
		try {
			submitCreatePortfolio_Btn.click();
			logger.log(Status.PASS, "Submitted the Portfolio");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
		MyPorfolioPage myportfolio = new MyPorfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;
		
	}
	
	public void enterPortfolioName(String portfolioName){
		try {
			createportfolio_textbox.clear();
			createportfolio_textbox.sendKeys(portfolioName);
			logger.log(Status.PASS, "Entered Portfolio Name : " + portfolioName);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	
	public void clickCreatePortfolio(){
		try {
			createPortfolio_Btn.click();
			logger.log(Status.PASS, "Clicked the Create Portfolio Button");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void verifyMoneyBiz(){
			moneyBiz_text.isDisplayed();
	}
	
	public TopMenuClass gettopMenu(){
		return topmenu;
	}
	
	

}
