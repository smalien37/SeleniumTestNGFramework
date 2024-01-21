package somoCompany.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static  ExtentReports report;
	public static  ExtentSparkReporter htmlReporter;

	public static ExtentReports getReportInstance() {

		report = new ExtentReports();
		String ppath = System.getProperty("user.dir")+"/Report/report_"+System.currentTimeMillis()+"Check.html";
		 htmlReporter = new ExtentSparkReporter(ppath);	
		 htmlReporter.config().setDocumentTitle("Dummy Test Reports");
			htmlReporter.config().setReportName("Dummy Test Report");
			htmlReporter.config().setTheme(null);
			
			report.setSystemInfo("Machine", "Som-mac-mini");
			report.setSystemInfo("Environment", "DevOps");
			report.setSystemInfo("Browser", "Chrome");
			report.setSystemInfo("OS", "MAC-OS");
			report.setSystemInfo("Owner", "Soumyajit");
			report.attachReporter(htmlReporter);
//			report.createTest(ppath);
		return report;
	}

}
