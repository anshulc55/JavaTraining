package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	static ExtentReports extent;
	
	public static ExtentReports getReporter() {
		if (extent == null) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("TestNG Pilot Project");
			spark.config().setReportName("TestNG Extent Report");
			extent.attachReporter(spark);
		}
		
		return extent;
	}

}
