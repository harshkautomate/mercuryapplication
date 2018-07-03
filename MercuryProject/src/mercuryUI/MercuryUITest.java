package mercuryUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import base.MercuryService;
import base.BaseTestCase;
import base.DataProviderMethods;
import framework.ExcelReader;


import static framework.FrameworkReporter.*;
import static framework.FrameworkReporter.reportPass;
import static framework.FrameworkReporter.assertTest;
import static framework.FrameworkReporter.takeScreenshot;

/**
 * This class contains all the test methods of TestNG class to perform UI tests.
 * The test methods that require data are executed by accessing DataProvider methods written in DataProviderMethods class in base package.
 * The data received as input parameter in tests is further processed to cater to individual test scenarios.
 * 
 * This class extends BaseTestCase class to inherit BaseTestCase methods. 
 *
 */
public class MercuryUITest extends BaseTestCase
{
	private String username = null;
	private String password = null;

	/**
	 * Test to perform User Registration.
	 */
     @Test(dataProvider = "registerUser", dataProviderClass = DataProviderMethods.class, priority=0)
     public void MercuryTours_RegisterUser(String data){
    	 
    	 String[] dataStorage = data.split("-");   	
	     username = dataStorage[9];
	     password = dataStorage[10];

    	 String actualMessage, expectedMessage;
    	 expectedMessage = getExpectedMessageUserNameInRegConfirmation(username);
    	 
      actualMessage = MercuryService.mercuryUIService(driver).selectRegisterLinkInHomePage().enterRegistrationDetails(data).clickSubmitButton().getUserNameFromRegistrationConfirmation();
    	 
      if(actualMessage.equals(expectedMessage)){
	 		 reportPass(String.format("Verified Actual and Expected message are same for the User Registration. Actual { %s } | Expected { %s }",
	 				actualMessage, expectedMessage));
	 	 }
	 	 else{
	 		reportFail(String.format("Actual and Expected message are not same for the User Registration. Actual { %s } | Expected { %s }",
	 				actualMessage, expectedMessage));
	 	 }
      
      if(MercuryService.mercuryUIService(driver).verifySignInLink())
    	  reportPass("Verified sign-in link is present in the Registration Confirmation window.");
      else
    	  reportFail("There is no sign-in link present in the Registration Confirmation window.");
      
      MercuryService.mercuryUIService(driver).writeToFile("Registration successful->", actualMessage);
  
        takeScreenshot();
		assertTest();
   }
	 
     /**
 	 * Test to perform Ticket Booking using username and password created from previous test.
 	 */
     @Test(dependsOnMethods={"MercuryTours_RegisterUser"}, dataProvider = "bookUserTicket", dataProviderClass = DataProviderMethods.class, priority=1)
     public void MercuryTours_VerifyTicketBooking(String data){
    	 
    	 String[] dataStorage = data.split("-");   	
    	 
	     String actualMessage, expectedMessage;
	     String totalCostInSummary, totalCostInTicketConfirmation;
	     String sourceDestinationInSummary, sourceDestinationConfirmation;
	     String flightNameInSummary, flightNameInConfirmation;
	     
	     String ticketNumber;
	     
	     expectedMessage = getExpectedMessageInTicketConfirmation();
	     totalCostInSummary = MercuryService.mercuryUIService(driver).performSignIn(username, password).selectContinueInFlightFinder().selectContinueInSelectFlight().getTotalCostInSummary();    		 
	     sourceDestinationInSummary = MercuryService.mercuryUIService(driver).getSourceAndDestinationFromSummary();
	     flightNameInSummary = MercuryService.mercuryUIService(driver).getOnwardFlightNameFromSummary();
	     
	     actualMessage = MercuryService.mercuryUIService(driver).selectContinueInBookFlight().getTicketConfirmationMessage();
        
	     if(actualMessage.equals(expectedMessage)){
	 		 reportPass(String.format("Verified Actual and Expected message are same for the Ticket Confirmation. Actual { %s } | Expected { %s }",
	 				actualMessage, expectedMessage));
	 	 }
	 	 else{
	 		reportFail(String.format("Actual and Expected message are not same for the Ticket Confirmation. Actual { %s } | Expected { %s }",
	 				actualMessage, expectedMessage));
	 	 }
	     
	     ticketNumber = MercuryService.mercuryUIService(driver).getTicketNumber();
	     reportInfo("Flight Ticket Number is: "+ticketNumber);
	     
	     sourceDestinationConfirmation = MercuryService.mercuryUIService(driver).getSourceDestinationInConfirmation();
	     flightNameInConfirmation = MercuryService.mercuryUIService(driver).getFlightNameInConfirmation();
	     totalCostInTicketConfirmation = MercuryService.mercuryUIService(driver).getTotalCostInConfirmation();
	     
	     if(sourceDestinationConfirmation.contains(sourceDestinationInSummary))
	    	 reportPass("Verified Source to Destination for onward ticket is same in Summary Page and Confirmation Page: "+sourceDestinationConfirmation);
	     else
	    	 reportFail("Source to Destination for onward ticket is not same in Summary Page and Confirmation Page: "+sourceDestinationConfirmation);
	     
	     if(flightNameInConfirmation.contains(flightNameInSummary))
	    	 reportPass("Verified Flight Name for onward ticket is same in Summary Page and Confirmation Page: "+flightNameInSummary);
	     else
	    	 reportFail("Flight Name for onward ticket is not same in Summary Page and Confirmation Page: "+flightNameInSummary);
	     
	     if(totalCostInTicketConfirmation.contains(totalCostInSummary))
	    	 reportPass("Verified Total Cost is same in Summary Page and Confirmation Page: "+totalCostInTicketConfirmation);
	     else
	    	 reportFail("Total Cost is not same in Summary Page and Confirmation Page: "+flightNameInSummary);
	     
        takeScreenshot();
		assertTest();
   }
     
		//Demo AutoIT.
		@Test(enabled=false)
		public void demoAutoIt() {
			driver.findElement(By.linkText("3.13.0")).click();
			
			try {
				Thread.sleep(5000);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			
			try {
				Runtime.getRuntime().exec("D:/CloseSeleniumDialog.exe");
				reportPass("Successfully closed the Selenium download dialog...");
			}
			catch(IOException e) {
				reportFail("Failed to close the dialog.");
				e.printStackTrace();
			}
		}
		
	/**	
	 * Below are private methods that return expected message for individual scenarios.
	 * 
	 */
	private String getExpectedMessageUserNameInRegConfirmation(String username){
		String expectedMessage = null;

				expectedMessage = getMessage("confirmUserNameInRegistrationMsg", username);
		return expectedMessage;
	}
	
	private String getExpectedMessageInTicketConfirmation(){
		String expectedMessage = null;

				expectedMessage = getMessage("ticketConfirmationMessage");
		return expectedMessage;
	}
	
}
