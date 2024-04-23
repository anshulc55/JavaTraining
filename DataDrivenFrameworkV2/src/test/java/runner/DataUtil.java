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
