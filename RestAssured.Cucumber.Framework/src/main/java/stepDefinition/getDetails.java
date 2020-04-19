package stepDefinition;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import util.Excelop;


public class getDetails {
	 Excelop excelObj;
	 RequestSpecification request;  
	 public ExtentHtmlReporter htmlreporter;
	 public ExtentReports extent;
	 public ExtentTest test;
	 public ExtentTest parentTest;
	 public ExtentTest childTest;
	 
	
	 @Before
	public void ini() throws IOException
	{
		 excelObj = new Excelop("/Users/rahultiwari/git/RestCucumber/RestAssured.Cucumber.Framework/target/Workbook.xlsx");
		 
		 ///Users/rahultiwari/Downloads/Cucumber.RA.framework-master/RestAssured.Cucumber.Framework/target/Workbook.xlsx
		  request= RestAssured.given();
		  htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/report.html");
		  htmlreporter.config().setDocumentTitle("Automation-Report"); // title of report
		  htmlreporter.config().setReportName("Sprint-Report");
		  htmlreporter.config().setTheme(Theme.DARK);
		  
		  extent = new ExtentReports();
		  extent.attachReporter(htmlreporter);
		  extent.setSystemInfo("Hostname", "My-PC");
		  extent.setSystemInfo("OS", "Windows");
		  extent.setSystemInfo("SDET Name", "Rahul Tiwari");
	}
	 
	 @After
		public void tearDown() throws IOException
		{
		 
		 	 extent.flush();
		 
			 excelObj.closeWorkbook();
			  
		}
	 
	 
	 public void tearDown(ITestResult result) throws IOException {
		  if (result.getStatus() == ITestResult.FAILURE) {
		   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
		   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
		   
		   
		  } else if (result.getStatus() == ITestResult.SKIP) {
		   test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		  }
		  else if (result.getStatus() == ITestResult.SUCCESS) {
		   test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		  }
	}
	
	
	static Logger log = Logger.getLogger(getDetails.class);

    //public BaseExcel excel= new BaseExcel(); //To create an object of BaseExcel Class

    

            Response response=null;

             // ValidaworksheetleResponse json;

              
	
	@Given("^I want to execute service \"([^\"]*)\"$")
	public void i_want_to_execute_service(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//test = extent.createTest("Execution of service Started");
		parentTest = extent.createTest("Parent test started");
		log.info("Executing service" + arg1);
		childTest = parentTest.createNode("Node created for I want to execute service \\\"([^\\\"]*)");
		childTest.log(Status.PASS, MarkupHelper.createLabel("Execution started childtestlog", ExtentColor.BLUE));
		
	    
	}

	@When("^I submit the GET request as per the data in Excel Worksheet \"([^\"]*)\"$")
	public void i_submit_the_GET_request_as_per_the_data_in_Excel_Worksheet(String arg1) throws Throwable {
		//test = extent.createTest("Get request in action");
		//test.info("I submit the GET request as per the data in Excel Worksheet ");
		childTest.log(Status.FAIL, MarkupHelper.createLabel("I submit the GET request as per the data in Excel Worksheet", ExtentColor.BLUE));
				
		String endpoint=excelObj.readData(arg1, 1, 0).toString();
		String qparamname=excelObj.readData(arg1, 1, 1).toString();
		String qparamval=excelObj.readData(arg1, 1, 2).toString();
		
		System.out.println(endpoint);
		
		response = request.given().contentType("application/json").when().relaxedHTTPSValidation().body(qparamval).post("http://localhost:8081/topics/kafka");
		
	}

	@SuppressWarnings("deprecation")
	@When("^I validate status code$")
	public void i_validate_status_code() throws Throwable {
		//test = extent.createTest("Status code is validated");
		//test.info("I validate status code$");
		
		Assert.assertEquals(200, response.getStatusCode());
		
		//test.pass("test Passed");
	    
	}

	@When("^I validate mandatory tag in response from Excel Worksheet \"([^\"]*)\"$")
	public void i_validate_mandatory_tag_in_response_from_Excel_Worksheet(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//test = extent.createTest("Mandate tag in response check");
		//test.info("I validate mandatory tag in response from Excel Worksheet \\\"([^\\\"]*)\\\"$");
		//excelObj.writeData(arg1, 1, 9, response.prettyPrint());
		
	    
	}

	@When("^I validate response content$")
	public void i_validate_response_content() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//test = extent.createTest("i_validate_response_content");
		//test.info("I validate response content");
		
	}

	@When("^I validate tag values in response from Excel Worksheet \"([^\"]*)\"$")
	public void i_validate_tag_values_in_response_from_Excel_Worksheet(String arg1) throws Throwable {
		//test = extent.createTest("i_validate_tag_values_in_response_from_Excel");
	    // Write code here that turns the phrase above into concrete actions
		//test.info("I validate tag values in response from Excel Worksheet");
		
	}

	@When("^I validate header parameter in response$")
	public void i_validate_header_parameter_in_response() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//test = extent.createTest("I validate header parameter in response");
		//test.info("I validate header parameter in response");
		
	}
	
	

}
