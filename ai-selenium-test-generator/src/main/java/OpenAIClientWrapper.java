import java.util.logging.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.ChatModel;

/**
 * 
 * This class connects to the OpenAI API to generate Selenium + TestNG Java test
 * cases automatically from user stories or requirements.
 */

public class OpenAIClientWrapper {
	private static final Logger LOGGER = Logger.getLogger(OpenAIClientWrapper.class.getName());
	private static OpenAIClient client;

	public OpenAIClientWrapper() {
		String apiKey = Config.getApiKey();
		if (apiKey == null || apiKey.isBlank()) {
			throw new IllegalArgumentException("API key must not be blank");
		}
		this.client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();
		System.out.println(client);
	}

	private static void initClientIfNeeded() {
		if (client == null) {
			synchronized (OpenAIClientWrapper.class) {
				if (client == null) {
					String apiKey = Config.getApiKey();
					if (apiKey == null || apiKey.isBlank()) {
						throw new IllegalArgumentException("API key must not be blank");
					}
					// This is compatible with openai-java 4.6.1 style you used earlier
					client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();
					LOGGER.info("OpenAI client initialized.");
				}
			}
		}
	}

	// Generates Java source code for a Selenium + TestNG test based on the given
	// user story.
	public static String generateCodeFromStory(String userStory) throws IOException {
		if (userStory == null || userStory.isBlank()) {
			throw new IllegalArgumentException("userStory must not be blank");
		}
		
		initClientIfNeeded();

		// Construct the system prompt
		String systemPrompt = """
				You are an expert Test Automation Engineer. Generate a clean, maintainable Selenium + TestNG test in Java.
				Provide full Java source code with imports, class, method, ChromeDriver setup and teardown, descriptive naming, and assertions.
				""";

		// Create chat completion parameters
		ChatCompletionCreateParams params = ChatCompletionCreateParams.builder().model(ChatModel.GPT_5)
				.addSystemMessage(systemPrompt).addUserMessage("User Story / Acceptance Criteria:\n\n" + userStory)
				.build();

		// Call the API
		ChatCompletion result = client.chat().completions().create(params);

		// Extract content
		String code = result.choices().get(0).message().content()
				.orElseThrow(() -> new IOException("No content in OpenAI response"));

		return code.trim();
	}
	
	
	// Generates Java source code for a Selenium + TestNG + Extent Report based on the given
	// user story.
	public static String generateCodeFromStoryWithReport(String userStory) throws IOException {
		if (userStory == null || userStory.isBlank()) {
			throw new IllegalArgumentException("userStory must not be blank");
		}
		
		initClientIfNeeded();

		// Construct the system prompt
        String systemPrompt = """
                You are an expert Test Automation Engineer. Generate a clean, maintainable Selenium + TestNG test class in Java
                that includes full Extent Reports integration. 
                Requirements:
                - Return ONLY valid Java source code (no explanations, no markdown).
                - Include package, all necessary imports, and a single public class.
                - Use ChromeDriver in @BeforeClass and quit driver in @AfterClass.
                - Initialize ExtentReports (ExtentSparkReporter) in @BeforeClass and call extent.flush() in @AfterClass.
                - Create an ExtentTest for the test and log each logical step using test.info(...), test.pass(...), and test.fail(...).
                - On exceptions, capture a screenshot, save it under a reports/screenshots folder, and attach it to the report using MediaEntityBuilder.
                - Implement the test steps derived from the provided input.
                - Use clear, descriptive class and method names. Keep one @Test method per logical test-case.
                - Include appropriate TestNG assertions (Assert.assertTrue / Assert.assertFalse / Assert.assertEquals).
                - Do not include any external library setup instructions or extra commentary — only the Java test class source.
                """;

		// Create chat completion parameters
		ChatCompletionCreateParams params = ChatCompletionCreateParams.builder().model(ChatModel.GPT_5)
				.addSystemMessage(systemPrompt).addUserMessage("User Story / Acceptance Criteria:\n\n" + userStory)
				.build();

		// Call the API
		ChatCompletion result = client.chat().completions().create(params);

		// Extract content
		String code = result.choices().get(0).message().content()
				.orElseThrow(() -> new IOException("No content in OpenAI response"));

		return code.trim();
	}

	// Saves the generated code to a .java file.
	public static void saveToFile(String code, String fileName) throws IOException {
		if (code == null || code.isBlank()) {
			throw new IllegalArgumentException("code must not be blank");
		}
		if (fileName == null || fileName.isBlank()) {
			throw new IllegalArgumentException("fileName must not be blank");
		}

		Path path = Path.of(fileName).toAbsolutePath();
		
		 // Ensure parent directories exist
		Path parent = path.getParent();
		if (parent != null && !Files.exists(parent)) {
	        Files.createDirectories(parent);
	    }
		
		Files.writeString(path, code, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		LOGGER.info("Saved generated code to: " + path);
	}

}
