package base;

import org.openqa.selenium.WebDriver;

import mercuryUI.MercuryUIService;

/**
 * Provide all possible services given by Mercury UI.
 */

public class MercuryService {

    /**
     * Provides a new instance of MercuryUIService class.
     * @param driver WebDriver driver variable passed as input parameter.
     * @return
     */
	public static MercuryUIService mercuryUIService(WebDriver driver){
		return new MercuryUIService(driver);
	}
}
