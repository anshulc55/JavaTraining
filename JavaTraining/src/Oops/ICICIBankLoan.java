package Oops;

public class ICICIBankLoan extends BankLoan{

	public static void main(String[] args) {
		 BankLoan loan = new BankLoan();
		 
		 loan.setAge(26);
		 loan.setName("Micahel John");
		 loan.setAmount(100000);
		 
		 System.out.println("Name is : " +loan.getName());
		 System.out.println("Age is : " + loan.getAge());
		 System.out.println("Amount is : " +loan.getAmount());

	}

}
