
public class Dog {
	
	String name = "Jacky";
	String brred = "BullDog";
	int height = 4;
	
	public void barking(){
		System.out.println(name + " is barking");
	}
	
	public void eating(){
		System.out.println(name + " is eating");
	}

	public static void main(String[] args) {
		
		Dog dogclass = new Dog();
		Dog obj2 = new Dog();
		
		System.out.println(dogclass.height);
		
		dogclass.barking();
		
		obj2.barking();

	}

}
