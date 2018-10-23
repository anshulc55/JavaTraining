
public class SecoundJavaClass {

	public static void main(String[] args) {
		
		String str1 = "Hello";
		String str2 = "Team";
		
		int a = 200;
		int b =300;
		
		System.out.println(str1 + " " + str2);
		System.out.println( a + b);
		
		System.out.println( a + b + str1 + str2);
		System.out.println(str1 + str2  + a + b);
		
		
		if(a>1000){
			System.out.println("A is greater then 1000");
		} else {
			System.out.println("A is less then 1000");
		}
		
		if(a==100){
			System.out.println("A is 100");
		}else if (a==200) {
			System.out.println("A is 200");
		}else if (a==300) {
			System.out.println("A is 300");
		}else{
			System.out.println("Value doesn't present");
		}
		
		int day =4;
		
		switch (day) {
		case 1:
			System.out.println("Today is Monday");
			break;
		case 2:
			System.out.println("Today is Tuesday");
			break;
		case 3:
			System.err.println("Today is Wednesday");
			break;
		case 4:
			System.out.println("Today is Thrusday");
			break;
		case 5:
			System.out.println("Today is Friday");
			break;

		default:
			System.out.println("Heyyyy!!!! It's weekend");
			break;
		}

	}

}
