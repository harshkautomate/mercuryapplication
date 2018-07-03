TEST EXECUTION - Perform below steps

1. Download the source code for the project MercuryProject.
2. Add all the jar files present in lib folder in Eclipse.
3. Update the excel sheet 'mercury.xls' present under data folder.
   Update sheet name 'UserDetails' and update last 3 columns for username, password and confirm password.
   For example: set the username and password values to say mercuryusertest4
   (See present column values containing mercuryusertest3).
4. Select MercuryUITest.xml file under TestFileSet and select to Run As->TestNG.
   This will start execution of the two tests MercuryTours_RegisterUser and MercuryTours_VerifyTicketBooking.
5. After execution is complete, find the reports in "workspace/MercuryProject/reports/test-output/emailable-report.html" file
   and also output return to file "workspace\MercuryProject\data\resultsfile.txt" as part of first test case "MercuryTours_RegisterUser" for User Registration.


FRAMEWORK AND COMPONENTS

1. There are three main packages in the src folder:
A. base
B. framework
C. mercuryUI

A. base package: This package contains file for: 
                 1. BaseTestCase.java that contains @BeforeTest, @BeforeMethods of TestNG to initiate browser and URL values. 
                 Other Methods includes reading values from properties file.
                 
                 2. DataProvidersMethods.java file contains data provider methods required to pass date to TestNG tests documented in MercuryUITest.java under 
                    mercuryUI package.

B. framework package contains files as under:
                 1. DefaultLocators.java file that helps in reading locators created in "MercuryProject\data\locatorsAll.xml". 
                 2. ExcelReader.java file contains methods to read data from excel file.
                 3. FrameworkReporter.java file contains methods related to Pass, Fail and Log results for tests executed. 
		 4. Navigator file contains methods related to launching of browser based on browser types (Ex: Firefox, Chrome and IE)

C. mercuryUI package contains MercuryUITest.java file containing TestNG tests, and MercuryUIService.java contains business specific methods catering to the tests executed
   in MercuryUITest.java file.

D. Data folder: This folder contains four files as below:
                1. locatorsAll.xml: This contains all the locators used by Selenium.
 		2. mercury.xls: Excel file containing data required for tests.
		3. messages.properties: This file contains expected messages to be verified during test execution. 
		4. resultsfile.txt: This file contains results written after performing "MercuryTours_RegisterUser" for User Registration.

E. TestFileSet folder: This contains MercuryUITest.xml TestNG xml file required to perform test suite execution.