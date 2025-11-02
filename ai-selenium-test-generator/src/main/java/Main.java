import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class Main {
    public static void main(String[] args) {
    	
    	//System.out.println("Key: " + System.getenv("OPENAI_API_KEY"));
    	
        // Create client from environment variables
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        ResponseCreateParams params = ResponseCreateParams.builder()
                .input("Hey, You are free to use the Internet. now tell me, what is the current time and temprature of New Jersey, Use city - Jersey City")
                .model("gpt-5")
                .build();

        Response response = client.responses().create(params);
        System.out.println(response.output());
    }
}