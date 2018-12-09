package Oops;

public class Circle extends Shape{

	
	public void displayShape() {
		System.out.println("Shape is Circle");
		
	}
	
	public static void main(String[] args){
		
		Shape s = new Circle();
		Shape s1 = new Triagle();
		
		s.displayShape();
		s1.displayShape();
		
	}

}
