
public class GenerateTestFromExternalizeStory {
	
	public static void main(String[] args) {
//		String story = UserStoryReader.readFromClasspath("user-story.txt");
//		System.out.println(story);
		
		String resourcePath = "user-story.txt";
		
		try {
			System.out.println("Reading user story from resource: " + resourcePath);
			String story = UserStoryReader.readFromClasspath(resourcePath);
			
			System.out.println("User story content:\n" + story + "\n");
			System.out.println("Sending user story to AI...");
			
			String generatedCode = OpenAIClientWrapper.generateCodeFromStory(story);
			System.out.println("\nAI Generated Test Code:\n");
			
			System.out.println(generatedCode);
		}catch(Exception e) {
			
		}
	}

}
