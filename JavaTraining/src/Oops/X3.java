package Oops;

public class X3 extends BMW{
	
	static String model = "X3 ";

	public static void main(String[] args) {
		
		BMW bmw = new BMW();
		
		System.out.println("Model is " + model);
		System.out.println("Brand is " + bmw.name);
		
		System.out.println("Behaviour is ");
		bmw.run();

	}

}
