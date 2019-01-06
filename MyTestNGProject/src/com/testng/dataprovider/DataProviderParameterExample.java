package com.testng.dataprovider;

import org.testng.annotations.Test;

public class DataProviderParameterExample {
	
	@Test(dataProvider = "scenarioData", dataProviderClass=DataProviderSource.class)
	public void scenario1(String scenarioData) {
		System.out.println("Scenario testing: Data(" + scenarioData + ")");
	}
	
	@Test(dataProvider = "scenarioData", dataProviderClass=DataProviderSource.class)
	public void scenario2(String scenarioData) {
		System.out.println("Scenario testing: Data(" + scenarioData + ")");
	}
	
	@Test(dataProvider = "scenarioData", dataProviderClass=DataProviderSource.class)
	public void commonScenarios(String scenarioData) {
		System.out.println("Common Scenarios testing: Data(" + scenarioData + ")");
	}

}
