package dataprovider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

// Separate Data providers for Each Test Suite
// External Data Source -- JSON, Excel, YAML, XML

public class TestDataProvider {

	@DataProvider
	public static Object[][] dataProviderSuiteA(Method method) {
		// Separate Data for Each Test
		System.out.println("Test Method name -- " + method.getName());

		Object data[][] = null;
		if (method.getName().toUpperCase().equals("TESTA")) {
			data = new Object[2][2];

			data[0][0] = "UserName 1";
			data[0][1] = "Password 1";

			data[1][0] = "UserName 2";
			data[1][1] = "Password 2";
		} else if (method.getName().toUpperCase().equals("TESTAA")) {
			data = new Object[2][2];

			data[0][0] = "UserName 11";
			data[0][1] = "Password 11";

			data[1][0] = "UserName 22";
			data[1][1] = "Password 22";
		}

		return data;
	}
	
	@DataProvider
	public static Object[][] dataProviderSuiteB(Method method) {
		// Separate Data for Each Test
		System.out.println("Test Method name -- " + method.getName());

		Object data[][] = null;
		if (method.getName().toUpperCase().equals("TESTB")) {
			data = new Object[1][2];

			data[0][0] = "UserName 100";
			data[0][1] = "Password 100";

		} else if (method.getName().toUpperCase().equals("TESTBB")) {
			data = new Object[2][2];

			data[0][0] = "UserName 111";
			data[0][1] = "Password 111";

			data[1][0] = "UserName 222";
			data[1][1] = "Password 222";
		}

		return data;
	}

}
