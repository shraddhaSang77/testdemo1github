package extentReports;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentDemo {

	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;

	@BeforeTest
	public void startReport() {
		htmlReporter = new ExtentSparkReporter("ExtentReportDemo.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

		// add environment details
		reports.setSystemInfo("machine", "hp");
		reports.setSystemInfo("os", "window 11");
		reports.setSystemInfo("user", "shraddha");
		reports.setSystemInfo("browser", "chrome");

		// configuration to change look and feel
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE,MMMM dd, yyyy, hh:mm a'('ZZZ')'");
	}

	@Test

	public void launBrowserAndOpenUrl() {
		// create test
		test = reports.createTest("Launch browser and open url");
		Assert.assertTrue(true);// test passed
	}

	@Test
	public void verifyTitle() {
		// create test
		test = reports.createTest("verify Title");
		Assert.assertTrue(true);// test passed
	}

	@Test
	public void VerifyLogo() {
		// create test
		test = reports.createTest("verify Logo");
		Assert.assertTrue(true);// test passed
	}

	@Test
	public void verifyEmail() {
		// create test
		test = reports.createTest("verify Email");
		throw new SkipException("skipping this test case with exception..");
	}

	@Test
	public void verifyUserName() {
		// create test
		test = reports.createTest("verify Username");
		Assert.assertTrue(true);// test pass

	}

	@AfterMethod
	public void getTestResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "FAILED", ExtentColor.PINK));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "PASSED", ExtentColor.GREEN));
		}
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "SKIP", ExtentColor.RED));
		}

	}

	@AfterTest
	public void tearDown() {
		reports.flush();
	}
}
