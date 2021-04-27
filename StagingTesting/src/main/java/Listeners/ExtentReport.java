package Listeners;

import Com.SheetColumnHeader;
import Password.ForgotPasswordScreen.SheetColumnHeader2;
import Password.EndToEndFunctionality.SheetColumnHeader1;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class ExtentReport implements IReporter {
    private ExtentReports extent1;
    private ExtentReports extent2;
    private static int testCaseCount = 0;
    private ExtentHtmlReporter extentHtmlReporter;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {
        Properties prop = new Properties();
        FileInputStream FileInputStream = null;
        String reportPath1 = null;
        String reportPath2 = null;

        try {
            FileInputStream = new FileInputStream(
                    new File("src//main//resources//object.properties"));
            prop.load(FileInputStream);
            reportPath1 = prop.getProperty("ReportPath1");
            reportPath2 =prop.getProperty("ReportPath2");

        } catch (IOException e) {
            e.printStackTrace();
        }

        extent1 = new ExtentReports(reportPath1, true);

        extent2 = new ExtentReports(reportPath2, true);
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        extent1.flush();
        extent1.close();
        extent2.flush();
        extent2.close();
    }

    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
        boolean isUsernameTest = false;

        if (tests.size() > 0) {
            for (ITestResult result1 : tests.getAllResults()) {
                //testCaseCount++;
                if(result1.getParameters().length >0){
                    if(result1.getParameters()[0] instanceof SheetColumnHeader){
                        isUsernameTest = true;
                        test = extent1.startTest(((SheetColumnHeader)result1.getParameters()[0]).getTestCaseDescription());
                        //test = extent1.startTest(C)
                    }
                    else if(result1.getParameters()[0] instanceof SheetColumnHeader1) {
                        test = extent2.startTest(((SheetColumnHeader1) result1.getParameters()[0]).getTestcaseDescription1());
                    }else if(result1.getParameters()[0] instanceof SheetColumnHeader2) {
                        test = extent2.startTest(((SheetColumnHeader2) result1.getParameters()[0]).getTestcaseDescription2());
                    }else {
                        test = extent2.startTest(result1.getName());
                    }
                }else{
                    test = extent2.startTest(result1.getName());
                }


                test.setStartedTime(getTime(result1.getStartMillis()));
                test.setEndedTime(getTime(result1.getEndMillis()));



                for (String group : result1.getMethod().getGroups())
                    test.assignCategory(group);
                if (result1.getThrowable() != null) {
                    test.log(status,  "  testcase failed ");
                } else {
                    test.log(LogStatus.PASS,   "  testcase passed");


                }
                if(isUsernameTest){
                    extent1.endTest(test);
                }else{
                    extent2.endTest(test);
                }

            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}