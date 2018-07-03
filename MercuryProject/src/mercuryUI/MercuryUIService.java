package mercuryUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SoftAssert;

import static framework.FrameworkReporter.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import base.BaseTestCase;


/**
 * This class contains all the business specific methods that are required by the test scenarios executed in MercuryUITest class.
 */
public class MercuryUIService extends BaseTestCase{

	private WebDriver webDriver;
	
	static int waitTime = 60;  //Wait time variable for explicit wait conditions. 
	static int implicitWaitInSecs = 20;   //Wait time variable for implicit wait conditions. 
	WebDriverWait wait; //WebDriverWait variable defined for explicit wait conditions.
	
	Actions action;
	
	/**
	 * Constructor to initialize the variables for WebDriver, Actions, and WebDriverWait
	 * @param accepts WebDriver driver
	 */
	 public MercuryUIService(WebDriver driver){
		 webDriver = driver;
		 action = new Actions(webDriver);
		 wait=new WebDriverWait(webDriver,waitTime);
	 }
		
	 public MercuryUIService selectRegisterLinkInHomePage() {
		 webDriver.findElement(By.linkText("REGISTER")).click();
		 
		 try {
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get("registerationpage"))));
		 }
		 catch(Exception e) {
			 throw new RuntimeException("FAILED: Registration page is not displayed within stipulated time. Please try again!");
		 }
		 return this;
	 }
	
	 /**
	  * Method to enter registration details.
	  */
	 public MercuryUIService enterRegistrationDetails(String data) {
		 		 
		 String[] dataStorage = data.split("-");   	

	      String firstName = dataStorage[0];
	      String lastName = dataStorage[1];
	      String phone = dataStorage[2];
	      String email = dataStorage[3];
	      String address = dataStorage[4];
	      String city = dataStorage[5];
	      String state = dataStorage[6];
	      String postalCode = dataStorage[7];
	      String country = dataStorage[8];
	      String username = dataStorage[9];
	      String password = dataStorage[10];
	      String confirmPassword = dataStorage[11];
	      
	      webDriver.findElement(By.xpath(locator.get("registration.firstname"))).sendKeys(firstName);
	      webDriver.findElement(By.xpath(locator.get("registration.firstname"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("registration.lastname"))).sendKeys(lastName);
	      webDriver.findElement(By.xpath(locator.get("registration.lastname"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("registration.phone"))).sendKeys(phone);
	      webDriver.findElement(By.xpath(locator.get("registration.phone"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("registration.email"))).sendKeys(email);
	      webDriver.findElement(By.xpath(locator.get("registration.email"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("registration.address"))).sendKeys(address);
	      webDriver.findElement(By.xpath(locator.get("registration.address"))).sendKeys(Keys.TAB);
	      webDriver.findElement(By.xpath(locator.get("registration.address"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("registration.city"))).sendKeys(city);
	      webDriver.findElement(By.xpath(locator.get("registration.city"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("registration.state"))).sendKeys(state);
	      webDriver.findElement(By.xpath(locator.get("registration.state"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("registration.postalcode"))).sendKeys(postalCode);
	      webDriver.findElement(By.xpath(locator.get("registration.postalcode"))).sendKeys(Keys.TAB);
	      
	      WebElement countryElement = webDriver.findElement(By.xpath(locator.get("registration.country")));
	      
	      Select select = new Select(countryElement);
	       
	      for(WebElement elt: select.getOptions()) {
	    	  if(elt.getText().equals(country.toUpperCase())) {
	    		  elt.click();
	    		  webDriver.manage().timeouts().implicitlyWait(implicitWaitInSecs, TimeUnit.SECONDS);
	    		  break;
	    	  }
	      }
	      
	      try {
	  		Thread.sleep(5000);
	  		}
	  		catch(Exception e) {
	  			e.printStackTrace();
	  		}
	      
	      webDriver.findElement(By.xpath(locator.get("registration.username"))).sendKeys(username);
	      webDriver.findElement(By.xpath(locator.get("registration.username"))).sendKeys(Keys.TAB);
	      
	      try {
		  		Thread.sleep(5000);
		  		}
		  		catch(Exception e) {
		  			e.printStackTrace();
		  		}
	      
	      webDriver.findElement(By.xpath(locator.get("registration.password"))).sendKeys(password);
	      webDriver.findElement(By.xpath(locator.get("registration.password"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("registration.confirmpassword"))).sendKeys(confirmPassword);
	      webDriver.findElement(By.xpath(locator.get("registration.confirmpassword"))).sendKeys(Keys.TAB);
		 return this;
	 }
	 
	 /**
	  * Click Submit button in Registration page.
	  */
	 public MercuryUIService clickSubmitButton() {
		 webDriver.findElement(By.xpath(locator.get("registration.submitbutton"))).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get("registration.confirmusername"))));
		 return this;
	 }
	 
	 /**
	  * Get UserName from Registration Confirmation page.
	  */
	 public String getUserNameFromRegistrationConfirmation() {
		 return webDriver.findElement(By.xpath(locator.get("registration.confirmusername"))).getText();
	 }
	 
	 /**
	  * Verify Sign-In link in Registration confirmation page.
	  */
	 public boolean verifySignInLink() {
		 return webDriver.findElement(By.xpath(locator.get("registration.signinlinkconfirmmsg"))).isDisplayed() ? true: false;
	 }
	 
	 /**
	  * Method to write results to a file after performing User Registration.
	  */
	 public MercuryUIService writeToFile(String... resultData) {
		 
		 BufferedWriter bufferWrite = null;
		 
		 try {
			 
			 	String pathSeperator = File.separator;
				String projectPath = System.getProperty("user.dir") + pathSeperator; 
				String filePath = String.format("%sdata%s", projectPath , pathSeperator)+"resultsfile.txt";
				
				File ff = new File(filePath);
				try {
		     		if(ff.delete()) {
					ff.createNewFile();
			        }
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				FileWriter fileWrite = new FileWriter(filePath);
				bufferWrite = new BufferedWriter(fileWrite);
				
				for(String data: resultData) {
				bufferWrite.write(data);
				reportLog("Results are written to file.");
			  }
		 }
		 catch(IOException ie) {
			 ie.printStackTrace();
		 }
		 finally {
			 try {
				 if(bufferWrite!=null)
					 bufferWrite.close();
			 }
			 catch(Exception ex) {
				 System.out.println("Failed to close the BufferedWriter: "+ex);
			 }
		 } //end of finally
		 
		 return this;
	 }
	 
	 /**
	  * Method to perform sign-in operation by accepting username and password.
	  */
	 public MercuryUIService performSignIn(String username, String password) {
		  webDriver.findElement(By.xpath(locator.get("homepage.username"))).sendKeys(username);
	      webDriver.findElement(By.xpath(locator.get("homepage.username"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("homepage.password"))).sendKeys(password);
	      webDriver.findElement(By.xpath(locator.get("homepage.password"))).sendKeys(Keys.TAB);
	      
	      webDriver.findElement(By.xpath(locator.get("homepage.signin"))).click();
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get("homepage.waitforflightdetailspage"))));
		 
		 return this;
	 }
	 
	 /**
	  * Method to select Continue button in Flight Finder page.
	  */
	 public MercuryUIService selectContinueInFlightFinder() {
		 webDriver.findElement(By.xpath(locator.get("flightfinder.continue"))).click();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get("reserveflights.continue"))));
		 return this;
	 }
	 
	 /**
	  * Method to select Continue button in Select Flight page.
	  */
	 public MercuryUIService selectContinueInSelectFlight() {
		 webDriver.findElement(By.xpath(locator.get("reserveflights.continue"))).click();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get("summary.securepurchase"))));
		 return this;
	 }
	 
	 /**
	  * Method to get Total Cost from Summary page.
	  */
	 public String getTotalCostInSummary() {
		 return webDriver.findElement(By.xpath(locator.get("summary.totalcost"))).getText();
	 }
	 
	 /**
	  * Method to get Source and Destination information from Summary page.
	  */
	 public String getSourceAndDestinationFromSummary() {
		 return webDriver.findElement(By.xpath(locator.get("summary.sourcetodestination"))).getText();
	 }
	 
	 /**
	  * Method to get Onward Flight Name from Summary page.
	  */
	 public String getOnwardFlightNameFromSummary() {
		 return webDriver.findElement(By.xpath(locator.get("summary.getflightname"))).getText();
	 }
	 
	 /**
	  * Method to select Continue button in Book Flight page.
	  */
	 public MercuryUIService selectContinueInBookFlight() {
		 webDriver.findElement(By.xpath(locator.get("summary.securepurchase"))).click();
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.get("flightconfirmation.confirmmsg"))));
		 return this;
	 }
	 
	 /**
	  * Method to get Ticket Confirmation Message.
	  */
	 public String getTicketConfirmationMessage() {
		 return webDriver.findElement(By.xpath(locator.get("flightconfirmation.confirmmsg"))).getText();
	 }
	 
	 /**
	  * Method to get Ticket Number.
	  */
	 public String getTicketNumber() {
		 return webDriver.findElement(By.xpath(locator.get("flightconfirmation.ticketnumber"))).getText();
	 }
	 
	 /**
	  * Method to get Source and Destination from Confirmation page.
	  */
	 public String getSourceDestinationInConfirmation() {
		 return webDriver.findElement(By.xpath(locator.get("flightconfirmation.getsourcedest"))).getText();
	 }
	 
	 /**
	  * Method to get Flight Name from Confirmation page.
	  */
	 public String getFlightNameInConfirmation() {
		 return webDriver.findElement(By.xpath(locator.get("flightconfirmation.getflightname"))).getText();
	 }
	 
	 /**
	  * Method to get Total Cost from Confirmation page.
	  */
	 public String getTotalCostInConfirmation() {
		 return webDriver.findElement(By.xpath(locator.get("flightconfirmation.totalprice"))).getText();
	 }
}
