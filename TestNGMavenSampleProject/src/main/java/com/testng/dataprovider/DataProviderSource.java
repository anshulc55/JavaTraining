package com.testng.dataprovider;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderSource {

	@DataProvider(name = "TestType")
	public static Object[][] getTestTypeData(ITestContext context) {
		String testName = context.getName();
		if ("IntegrationLevel".equals(testName)) {
			return new Object[][] { { "Integration test data" } };
		} else if ("AcceptanceLevel".equals(testName)) {
			return new Object[][] { { "Acceptance test data" } };
		} else {
			return new Object[][] { { "Common test data" } };
		}
	}

	@DataProvider(name = "scenarioData")
	public static Object[][] getScenarioData(Method method) {
		String testCase = method.getName();
		if ("scenario1".equals(testCase)) {
			return new Object[][] { { "Scenario1 data" } };
		} else if ("scenario2".equals(testCase)) {
			return new Object[][] { { "Scenario2 data" } };
		} else {
			return new Object[][] { { "Common scenario data" } };
		}
	}

}
