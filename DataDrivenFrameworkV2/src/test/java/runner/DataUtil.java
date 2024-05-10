package runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataUtil {

	public int getDataSets(String filePath, String dataflag) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(new FileReader(filePath));

		JSONArray testDataSet = (JSONArray) json.get("testdata");
		for (int dataSetID = 0; dataSetID < testDataSet.size(); dataSetID++) {
			JSONObject testData = (JSONObject) testDataSet.get(dataSetID);
			String dataFlag = (String) testData.get("flag");

			if (dataFlag.equalsIgnoreCase(dataflag)) {
				JSONArray dataSets = (JSONArray) testData.get("data");
				return dataSets.size();
			}
		}
		return -1;
	}

	public JSONObject getTestData(String filePath, String dataflag, int iterationNumber)
			throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(new FileReader(filePath));

		JSONArray testDataSet = (JSONArray) json.get("testdata");
		for (int dataSetID = 0; dataSetID < testDataSet.size(); dataSetID++) {
			JSONObject testData = (JSONObject) testDataSet.get(dataSetID);
			String dataFlag = (String) testData.get("flag");

			if (dataFlag.equalsIgnoreCase(dataflag)) {
				JSONArray dataSets = (JSONArray) testData.get("data");
				JSONObject data = (JSONObject) dataSets.get(iterationNumber);
				return data;
			}
		}
		return null;
	}

	public Map<String, String> loadClassMethods() throws FileNotFoundException, IOException, ParseException {
		Map<String, String> classMethodMap = new HashMap<String, String>();

		String classPath = System.getProperty("user.dir") + "/src/test/resources/projectJSONs/classmethods.json";

		JSONParser parser = new JSONParser();
		JSONObject classJSON = (JSONObject) parser.parse(new FileReader(new File(classPath)));

		// System.out.println(classJSON.toString());
		JSONArray classDetails = (JSONArray) classJSON.get("classdetails");
		for (int i = 0; i < classDetails.size(); i++) {
			JSONObject classDetail = (JSONObject) classDetails.get(i);
			// System.out.println(classDetail.toString());

			String className = (String) classDetail.get("class");
			JSONArray methods = (JSONArray) classDetail.get("methods");

			for (int j = 0; j < methods.size(); j++) {
				String methodName = (String) methods.get(j);
				classMethodMap.put(methodName, className);
			}
		}

		// System.out.println(classMethodMap);
		return classMethodMap;
	}

}
