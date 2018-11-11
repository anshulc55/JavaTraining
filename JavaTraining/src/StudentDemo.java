
public class StudentDemo {
	
	int rollnumber;
	String name;
	String Standard;
	
	public StudentDemo(int rollnumber, String name, String Standard){
		this.rollnumber = rollnumber;
		this.name = name;
		this.Standard = Standard;
	}
	
	public void display(){
		System.out.println("Student Roll Number : " + rollnumber + " name is :" + name + " and in class :" + Standard);
	}

	public static void main(String[] args) {
		
		StudentDemo std = new StudentDemo(10, "Frank", "2nd");
		
		StudentDemo std1 = new StudentDemo(20, "Michael", "5th");
		
		std.display();
		std1.display();

	}

}
