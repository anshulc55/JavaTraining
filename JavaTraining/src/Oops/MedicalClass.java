package Oops;

public class MedicalClass implements Student{

	
	public void displayName() {
		System.out.println("Hi, We are from Medical Department");
		
	}

	
	public void getStudentNumber() {
		System.out.println("We are 140 Students");
		
	}

	
	public void getStandard() {
		System.out.println("We are from MBBS");
		
	}
	
	public void getUniversity(){
		System.out.println("University name is London University");
	}
	
	public static void main (String[] args){
		EngineeringClass eng = new EngineeringClass();
		MedicalClass med = new MedicalClass();
		
		eng.displayName();
		eng.getStandard();
		eng.getStudentNumber();
		eng.getUniversity();
		
		System.out.println("****************************");
		
		med.displayName();
		med.getStandard();
		med.getStudentNumber();
		med.getUniversity();
		
		
	}


	@Override
	public void getInterfaceName() {
		// TODO Auto-generated method stub
		
	}

}
