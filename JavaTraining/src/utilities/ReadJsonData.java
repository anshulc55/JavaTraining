package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJsonData {

	public static void main(String[] args) throws IOException, ParseException {
		
		String filepath = System.getProperty("user.dir") + "//testData.json";
		FileReader file = new FileReader(filepath);
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(file);
		
		System.out.println(json.toJSONString());
		
		JSONArray testData = (JSONArray) json.get("testdata");
//		System.out.println(testData.toJSONString());
//		
//		JSONObject logintest = (JSONObject) testData.get(0);
//		System.out.println(logintest.toJSONString());
//		System.out.println(logintest.get("testName"));
//		
//		JSONArray loginTestSet = (JSONArray) logintest.get("data");
//		JSONObject LoginTestSetData1 = (JSONObject) loginTestSet.get(1);
//		System.out.println(LoginTestSetData1.get("browser"));
		
		for (int i = 0; i < testData.size(); i++) {
			
			JSONObject testCase = (JSONObject) testData.get(i);
			System.out.println("Test Case Name is --  " + testCase.get("testName"));
			
			JSONArray testCaseData = (JSONArray) testCase.get("data");
			for (int j = 0; j < testCaseData.size(); j++) {
				
				JSONObject currentTestData = (JSONObject) testCaseData.get(j);
				Set<String> keys = currentTestData.keySet();
				Iterator<String> it = keys.iterator();
				while (it.hasNext()) {
					String key = it.next();
					String value = (String) currentTestData.get(key);
					System.out.println(key + " -- " + value);
				}
				
				
			}
			
			
		}
		
		
		
		
		
		
		

	}

}
