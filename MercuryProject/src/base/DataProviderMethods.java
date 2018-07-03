package base;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import framework.ExcelReader;

/**
 * This class contains all the DataProvider methods required to provide data for the various test methods defined in MercuryUITest class. 
 * The input data is obtained from an excel file mercury.xls located in workspace\MercuryProject\data folder. 
 * All the DataProvider methods access ExcelReader class file to read data from excel file.
 */
public class DataProviderMethods {

	/**
	 * This data provider method provides data related to User Registration Info. 
	 * @return iterator over object array prepared from the List collection of data received from getting all the rows from excel sheet.
	 */
	@DataProvider(name = "registerUser")
	public static Iterator<Object[]> RegisterUserDataProvider(){
		
		List<Object[]> dataCollector = new ArrayList<Object[]>();
	
		String pathSeperator = File.separator;
		String projectPath = System.getProperty("user.dir") + pathSeperator; 
		String filePath = String.format("%sdata%s", projectPath , pathSeperator)+"mercury.xls";
		
		ExcelReader er = new ExcelReader(filePath,"UserDetails");
				
		List<String> data = er.getAllRowsData();
		
		for (String str : data) {
			dataCollector.add(new Object[] { str });
	    }
		
		return dataCollector.iterator();
	}
	
	/**
	 * This data provider method provides data related to Ticket Booking Info. 
	 * @return iterator over object array prepared from the List collection of data received from getting all the rows from excel sheet.
	 */
	@DataProvider(name = "bookUserTicket")
	public static Iterator<Object[]> BookUserTicketDataProvider(){
		
		List<Object[]> dataCollector = new ArrayList<Object[]>();
	
		String pathSeperator = File.separator;
		String projectPath = System.getProperty("user.dir") + pathSeperator; 
		String filePath = String.format("%sdata%s", projectPath , pathSeperator)+"mercury.xls";
		
		ExcelReader er = new ExcelReader(filePath,"FlightDetails");
				
		List<String> data = er.getAllRowsData();
		
		for (String str : data) {
			dataCollector.add(new Object[] { str });
	    }
		
		return dataCollector.iterator();
	}
	
	
}
