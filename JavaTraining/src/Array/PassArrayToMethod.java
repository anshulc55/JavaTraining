package Array;

public class PassArrayToMethod {
	
	public static void findMin(int arrayOne[]){
		int min = arrayOne[0];
		
		for(int i=1 ;i < arrayOne.length; i++){
			if(min>arrayOne[i]){
				min = arrayOne[i];
			}
		}
		
		System.out.println("Minimum Element is " + min);
	}
	
	public static int[] getArray(){
		
		return new int[] {12,13,56,78,90,12,34};
	}

	public static void main(String[] args) {
		
		PassArrayToMethod pass = new PassArrayToMethod();
		
		int arrayTest[] = {12,16,18,30,7,9};
		
		pass.findMin(arrayTest);
		
		int arrayThree[] = pass.getArray();
		
		System.out.println(arrayThree[2]);
	}

}
