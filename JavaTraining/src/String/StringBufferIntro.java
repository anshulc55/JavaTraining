package String;

public class StringBufferIntro {

	public static void main(String[] args) {
		
		String name = "Anshul";
		name.concat(" Chauhan");
		
		//System.out.println(name);
		
		StringBuffer name1 = new StringBuffer("AABBAA");
		//name1.append(" Chauhan");
		
		//name1.insert(4, "Chauhan");
		
		//name1.replace(1, 3, "chauhan");
		
		//name1.delete(1, 10);
		
		name1.reverse();
		System.out.println(name1);

	}

}
