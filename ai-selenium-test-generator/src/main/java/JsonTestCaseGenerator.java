import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.responses.ResponseCreateParams;

public class JsonTestCaseGenerator {
	
	public static void main(String[] args) {
		String resourcePath = "user-story.txt";
		
		System.out.println("Reading user story from resource: " + resourcePath);
		String userStory = UserStoryReader.readFromClasspath("user-story.txt");
		
		System.out.println("\nSending user story to AI for JSON Test Case generation...\n");
		
		try {
			String jsonOutput = generateJsonTestCase(userStory);
			//System.out.println("AI generated structured JSON Test Case:\n" + jsonOutput);
			
			// build timestamped filename and save
			String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
			String fileName = "src/test/resources/AI_Generated_JSON_Test.json";
			OpenAIClientWrapper.saveToFile(jsonOutput, fileName);
			System.out.println("\nSaved generated File: " + fileName);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static String generateJsonTestCase(String userStory) throws IOException{
		
        String systemPrompt = """
                You are an expert Test Automation Engineer.
                Convert the following user story or requirement into a structured JSON Test Case.
                Use clear fields: testCaseName, description, and steps (each step should include
                step number, action, target, and any input values or expected validations).
                Do NOT include code. Only return valid JSON.
                """;
        
        String apiKey = Config.getApiKey();
		OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();
		
		// Create chat completion parameters
		ChatCompletionCreateParams params = ChatCompletionCreateParams.builder().model(ChatModel.GPT_5)
				.addSystemMessage(systemPrompt).addUserMessage("User Story / Acceptance Criteria:\n\n" + userStory)
				.build();

		// Call the API
		ChatCompletion result = client.chat().completions().create(params);

		// Extract content
		String JsonTestCase = result.choices().get(0).message().content()
				.orElseThrow(() -> new IOException("No content in OpenAI response"));

		return JsonTestCase.trim();
		
	}

}
