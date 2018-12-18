package ExceptionHandling;

public class MultiCatch {

	public static void main(String[] args) {
		
		try{
		int value =100;
		int result = value/0;
		
		String text = null;
		int length = text.length();

		}catch(ArithmeticException e){
			System.out.println("Declared Specific Exception");
			e.printStackTrace();
		} catch(Exception e){
			
			System.out.println("Generic Catch Block");
		}

	}

}
