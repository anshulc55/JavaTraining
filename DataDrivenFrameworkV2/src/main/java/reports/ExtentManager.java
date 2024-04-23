package reports;

import java.io.File;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports reports;
	public static String screenShotPath;

	// Initialize Report and create Report
	public static ExtentReports getReports() {
		if (reports == null) {
			reports = new ExtentReports();

			Date date = new Date();

			String reportFolderName = date.toString().replaceAll(":", "_").replaceAll(" ", "-");
			String reportPath = System.getProperty("user.dir") + "/reports/" + reportFolderName;
			screenShotPath = reportPath + "/screenshots";

			File file = new File(screenShotPath);
			file.mkdirs();

			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setTheme(Theme.STANDARD);
			reporter.config().setReportName("Production Test Report");
			reporter.config().setDocumentTitle("Data Driven Test Report");
			reporter.config().setEncoding("utf-8");

			reports.attachReporter(reporter);

		}
		return reports;
	}

}
