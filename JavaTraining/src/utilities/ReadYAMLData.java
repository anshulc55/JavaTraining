package utilities;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

public class ReadYAMLData {

	public static void main(String[] args) throws FileNotFoundException, YamlException {
		
		String filepath = System.getProperty("user.dir") + "//testData.yml";
		FileReader file = new FileReader(filepath);
		
		YamlReader reader = new YamlReader(file);
		
		//Map - JSONObject
		//List - JSONArray
		
		Map testData = (Map)reader.read();
		
		List testCases = (List) testData.get("testdata");
		System.out.println(testCases);
		
		Map loginTestData = (Map) testCases.get(0);
		System.out.println(loginTestData.get("testName"));
		
		List loginTestDataData = (List) loginTestData.get("data");
		System.out.println(loginTestDataData);
		
	}

}
