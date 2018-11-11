
public class Students {
	
	public Students(){
		System.out.println("Hi, I am inside constructor");
		
		System.out.println("Hi, I am secound Method");
	}
	
	int rollNumber;
	String name;
	
	public Students(int i, String n){
		
		rollNumber = i;
		name =n;
		
	}
	
	public void display(){
		System.out.println("Roll Number is : " + rollNumber + " and Name is : " + name );
	}

	public static void main(String[] args) {
		Students std = new Students();
		
		Students std1 = new Students(10, "James");
		Students std2 = new Students(20, "Natalia");
		
		std1.display();
		std2.display();

	}

}
