import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import java.util.List;

public class SampleTestOpenAI {

	public static void main(String[] args) {
		String apiKey = Config.getApiKey();
		OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();
				
		// Example user story to generate test cases from
		String userStory = "As a registered user, I want to reset my password so that I can regain access if I forget it.";

		// Build prompt: be explicit about required output format
		String prompt = "You are a software QA engineer. Given the following user story, generate 3 test cases in Gherkin format (Given/When/Then).\n"
				+ "Provide each test case with:\n" + "- Title\n" + "- Preconditions\n"
				+ "- Steps in Gherkin (Given/When/Then)\n" + "- Expected result summary\n\n" + "User story:\n"
				+ userStory
				+ "\n\nRespond only with a JSON array of objects with fields: title, preconditions, gherkin, expected.\n";

		ResponseCreateParams params = ResponseCreateParams.builder()
				.input(prompt).model("gpt-4.1").build();

		try {
			Response response = client.responses().create(params);
			// The SDK returns a Response object — print the model output(s)
			System.out.println("Raw response object: " + response);
		} catch (Exception e) {
			System.err.println("Error calling OpenAI: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
