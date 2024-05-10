package runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRunner {

	// Implement Logic to read the classmethods.json

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		Map<String, String> classMethods = new DataUtil().loadClassMethods();

		String classPath = System.getProperty("user.dir") + "/src/test/resources/projectJSONs/testconfig.json";

		JSONParser parser = new JSONParser();
		JSONObject classJSON = (JSONObject) parser.parse(new FileReader(new File(classPath)));
		// System.out.println(classJSON.toString());

		String parallelsuites = (String) classJSON.get("parallelsuites");
		JSONArray testsuites = (JSONArray) classJSON.get("testsuites");

		TestNGRunner testNG = new TestNGRunner(Integer.parseInt(parallelsuites));

		for (int testID = 0; testID < testsuites.size(); testID++) {
			JSONObject testSuite = (JSONObject) testsuites.get(testID);

			String runmode = (String) testSuite.get("runmode");
			if (runmode.equalsIgnoreCase("Yes")) {
				String suiteName = (String) testSuite.get("name");
				String paralleltests = (String) testSuite.get("paralleltests");
				String testdatajsonfile = (String) testSuite.get("testdatajsonfile");
				String testdataxlsfile = (String) testSuite.get("testdataxlsfile");
				String suitefilename = (String) testSuite.get("suitefilename");

				boolean pTest = false;
				if (paralleltests.equalsIgnoreCase("Yes")) {
					pTest = true;
				}

				testNG.createSuite(suiteName, pTest);
				System.out.println("Executing Test Suite -- " + suiteName);

				String testsuitePath = System.getProperty("user.dir") + "/src/test/resources/projectJSONs/"
						+ suitefilename;

				JSONParser suiteParser = new JSONParser();
				JSONObject suiteJson = (JSONObject) suiteParser.parse(new FileReader(new File(testsuitePath)));

				JSONArray suiteTestCases = (JSONArray) suiteJson.get("testcases");
				for (int suiteTest = 0; suiteTest < suiteTestCases.size(); suiteTest++) {
					JSONObject suiteTestCase = (JSONObject) suiteTestCases.get(suiteTest);
					JSONArray parameternames = (JSONArray) suiteTestCase.get("parameternames");
					JSONArray executions = (JSONArray) suiteTestCase.get("executions");

					for (int execution = 0; execution < executions.size(); execution++) {
						JSONObject testCase = (JSONObject) executions.get(execution);
						String executionname = (String) testCase.get("executionname");
						String dataflag = (String) testCase.get("dataflag");

						// Number of DataSet -- Number of Test Case Execution against Test Data
						
						// Location for JSON DATA 
//						String testDataJSONPath = System.getProperty("user.dir") + "/src/test/resources/projectJSONs/"
//								+ testdatajsonfile;
//						int dataSets = new DataUtil().getDataSets(testDataJSONPath, dataflag);
						
						// Location for Excel Data
						String testDataJSONPath = System.getProperty("user.dir") + "/src/test/resources/projectJSONs/"
								+ testdataxlsfile;
						int dataSets = new ExcelReader().getDataSets(suiteName, dataflag, testDataJSONPath);

						for (int dataSetID = 0; dataSetID < dataSets; dataSetID++) {

							JSONArray parametervalues = (JSONArray) testCase.get("parametervalues");
							JSONArray methods = (JSONArray) testCase.get("methods");
							System.out.println("Executing Test Case :: " + executionname);

							testNG.addTest(suiteName + " : " + executionname + " " + (dataSetID + 1));
							for (int params = 0; params < parameternames.size(); params++) {
								testNG.addTestParameter((String) parameternames.get(params),
										(String) parametervalues.get(params));
							}

							testNG.addTestParameter("testdatajsonfile", testDataJSONPath);
							testNG.addTestParameter("dataflag", dataflag);
							testNG.addTestParameter("dataSetID", String.valueOf(dataSetID));
							testNG.addTestParameter("suiteName", suiteName);

							// Read Methods and associated Classes
							List<String> includeMethods = new ArrayList<String>();
							for (int methodId = 0; methodId < methods.size(); methodId++) {
								String methodName = (String) methods.get(methodId);
								String className = classMethods.get(methodName);

								if (methodId == methods.size() - 1
										|| !((String) classMethods.get((String) methods.get(methodId + 1)))
												.equals(className)) {
									// next method is from different class
									includeMethods.add(methodName);
									testNG.addTestClass(className, includeMethods);
									// System.out.println(className + " --- " + includeMethods.toString());
									includeMethods = new ArrayList<String>();
								} else {
									includeMethods.add(methodName);
								}
							}
						}
					}
				}

			}

		}
		testNG.run();
	}

}
