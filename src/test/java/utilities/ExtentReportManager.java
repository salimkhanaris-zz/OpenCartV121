package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener
{
    public ExtentSparkReporter sparkReporter; //UI of the report
    public ExtentReports extent; //populate common info on the report
    public ExtentTest test; //creating tc entries in the report and update the status of the test methods

    String repName;

    public void onStart(ITestContext context) {
        String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //TimeStamp
        repName= "Test Report- "+timeStamp+".html";
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+".\\reports\\"+repName); //specify location of the report
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Uswre Name Name", System.getProperty("user.name"));

        String os=context.getCurrentXmlTest().getParameter("OS");
        extent.setSystemInfo("OS Name", os);
        String browser= context.getCurrentXmlTest().getParameter("Browser");
        extent.setSystemInfo("Browser Name", browser);

        List<String> includedgroups  = context.getCurrentXmlTest().getIncludedGroups();
        extent.setSystemInfo("Groups",includedgroups.toString());
    }

    public void onTestSuccess(ITestResult result)
    {
        test= extent.createTest(result.getTestClass().getName()); //Create a new entry in the report
        test.assignCategory(result.getMethod().getGroups());//to display
        test.log(Status.PASS,"Test case Passed is: "+result.getName()); //Update the Test Status


    }
    public void onTestFailure(ITestResult result)
    {
        test= extent.createTest(result.getTestClass().getName()); //Create a new entry in the report
        test.assignCategory(result.getMethod().getGroups());//to display group
        test.log(Status.FAIL,"Test case FAILED is: "+result.getThrowable());//Update the Test Status
        test.log(Status.INFO,result.getThrowable().getMessage());

        //Take SS In case of Failure

        try {
            String imgPath= new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); //Create a new entry in the report
        test.assignCategory(result.getMethod().getGroups());//to display group
        test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName()); //Update the Test Status
        test.log(Status.INFO,result.getThrowable().getMessage());
    }
    public void onFinish(ITestContext context)
    {
        extent.flush(); //Write all info from standard repo to their output view

        //To open the report automatically

        String pathOfExtentReport= System.getProperty("user.dir")+".\\reports\\"+repName;
        File extentReport= new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Email code?



    }
}
