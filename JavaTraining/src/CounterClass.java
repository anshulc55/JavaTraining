
public class CounterClass {
	
	int i = 0;
	static int j = 0;
	
	public CounterClass(){
		i++;
		j++;
		
		System.out.println("Value of i : " + i);
		System.out.println("Value of j : " + j);
	}

	
	
	public static void main(String args[]){
		
		CounterClass counter = new CounterClass();
		CounterClass counter2 = new CounterClass();
		CounterClass counter3 = new CounterClass();
		
		/*System.out.println(counter.i);
		System.out.println(counter2.i);
		System.out.println(counter3.i);*/
		
		/*System.out.println(CounterClass.j);*/
		
		
		
	}

}
