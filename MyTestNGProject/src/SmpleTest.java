import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SmpleTest {

	@BeforeSuite
	public void beforeSuiteMethod(){

		//Business logic should be here
		System.out.println("Executing Before Test Suite");
	}

	@AfterSuite
	public void afterSuiteMethod(){
		System.out.println("Executing After Test Suite");
	}

}
