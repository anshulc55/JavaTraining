package Oops;

public class CalculateInterest {

	public static void main(String[] args) {
		
		AmericanExpress am = new AmericanExpress();
		BankOfAmerica ba = new BankOfAmerica();
		ICICIBank icici = new ICICIBank();
		
		System.out.println(am.getInterest());
		System.out.println(ba.getInterest());
		System.out.println(icici.getInterest());

	}

}
