package testcasesrediffPortfolio;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testbase.BaseTest;

public class ManageStocksTest extends BaseTest {

	@Parameters({ "action" })
	@Test
	public void modifyStock(String action, ITestContext context) {
		JSONObject data = (JSONObject) context.getAttribute("testData");
		String companyName = (String) data.get("companyName");
		String modifiedQuantity = (String) data.get("stockQuantity");
		String stockPrice = (String) data.get("stockPrice");
		String selectionDate = (String) data.get("selectionDate");

		app.logInfo("Modifying Quantity : " + modifiedQuantity + " of Stock :: " + companyName);

		int quantityBeforeSelling = app.findCurrentStockQuantity(companyName);
		context.setAttribute("quantityBeforeSelling", quantityBeforeSelling);

		app.goToBuySell(companyName);
		if(action.equals("addStock")) {
			app.selectByVisibleText("equityaction_id", "Buy");
		}else {
			app.selectByVisibleText("equityaction_id", "Sell");
		}
		
		app.click("buySellCalendar_id");
		app.selectDateFromCalendar(selectionDate);
		app.type("buysellqty_id", modifiedQuantity);
		app.type("buysellprice_id", stockPrice);
		app.click("buySellStockButton_id");
		app.waitforWebPageToLoad();
		app.logInfo("Stock :: " + companyName + " modified Successfully....");
	}

	@Parameters({ "action" })
	@Test
	public void verifyStockQuantity(String action, ITestContext context) {
		JSONObject data = (JSONObject) context.getAttribute("testData");
		String companyName = (String) data.get("companyName");
		int modifiedQuantity = Integer.parseInt((String)data.get("stockQuantity"));
		int expectedModifiedQuantity = 0;

		app.logInfo("Verify Stock Quantity After Action :: " + action);
		int quantity = app.findCurrentStockQuantity(companyName);

		int quantityBeforeSelling = (int) context.getAttribute("quantityBeforeSelling");

		if (action.equals("sellStock")) {
			expectedModifiedQuantity = quantityBeforeSelling - quantity;
		} else if (action.equals("addStock")) {
			expectedModifiedQuantity = quantity - quantityBeforeSelling;
		}

		app.logInfo("Earlier Stock Quantity : " + quantityBeforeSelling);
		app.logInfo("New Stock Quantity : " + quantity);

		if (expectedModifiedQuantity != modifiedQuantity) {
			app.reportFailure("Expected Modified Quantity is not matching", true);
		}

		app.logInfo("Stock Quantity Changed as per expected :: " + expectedModifiedQuantity);

	}

	@Parameters({ "action" })
	@Test
	public void verifyTransactionHistory(String action, ITestContext context) {
		JSONObject data = (JSONObject) context.getAttribute("testData");
		String companyName = (String) data.get("companyName");
		String modifiedQuantity = (String) data.get("stockQuantity");

		app.logInfo("Verify Stock Transaction History After Operation :: " + action);
		app.openTrasactionHistory(companyName);
		String quantityDisplayed = app.getText("trasactionTable_xpath");
		
		if(!modifiedQuantity.equals(quantityDisplayed)) {
			app.reportFailure("Got changed quantity in transaction history as " + quantityDisplayed, true);
		}

		if (action.equals("sellStock")) {
			quantityDisplayed = "-" + quantityDisplayed;
		}
		
		app.logInfo("Latest Change in Stock : " + companyName + " is :: " + quantityDisplayed);
	}

	@Test
	public void addStockTest(ITestContext context) {
		JSONObject data = (JSONObject) context.getAttribute("testData");
		String companyName = (String) data.get("companyName");
		String stockQuantity = (String) data.get("stockQuantity");
		String stockPrice = (String) data.get("stockPrice");
		String selectionDate = (String) data.get("selectionDate");

		app.logInfo("Selecting Stocks in Portfolio");
		
		int quantityBeforeSelling = app.findCurrentStockQuantity(companyName);
		context.setAttribute("quantityBeforeSelling", quantityBeforeSelling);

		app.click("addStock_id");
		app.type("addstockname_id", companyName);
		app.wait(2);
		app.clickEnterKey("addstockname_id");
		app.click("stockPurchaseDate_id");
		app.selectDateFromCalendar(selectionDate);
		app.type("addstockqty_id", stockQuantity);
		app.type("addstockprice_id", stockPrice);
		app.click("addStockButton_id");
		app.waitforWebPageToLoad();

		app.logInfo("Stock Added Successfully....");

	}

	@Test
	public void verifyStockIsPresent(ITestContext context) {
		JSONObject data = (JSONObject) context.getAttribute("testData");
		String companyName = (String) data.get("companyName");

		app.logInfo("Verifying Added Stock in Portfolio...");
		int rowNum = app.getRowNumWithCellData("stockTable_id", companyName);

		if (rowNum == -1) {
			app.reportFailure(companyName + " is not present in Stock List!!! ", true);
		}
		app.logInfo(companyName + " -- Found in Portfolio Stocks");
	}

	@Test
	public void modifyStockTest() {
		System.out.println("Modifying Stocks in Portfolio...");
	}

}
