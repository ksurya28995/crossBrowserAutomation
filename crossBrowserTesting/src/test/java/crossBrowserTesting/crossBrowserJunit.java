package crossBrowserTesting;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import commonUtilities.PropertyManager;
import commonUtilities.commonUtilities;
import commonUtilities.phpTravelActions;

/**
 * This Class is used for opening the site and check some points in the site using JUNIT
 * 
 * @author surya.k.kumaresan
 */
public class crossBrowserJunit {
	private static WebDriver driver = null;
	private static commonUtilities commObj = null;
	
	@BeforeClass
	public static void init() throws Throwable {
		commObj = new commonUtilities();
		driver = commObj.getDriver("windows8", "junit");
		commObj.openURL(PropertyManager.getPropertyVal("phptravelurl"));
	}

	@Test
	public void appValidate() throws Throwable {
		phpTravelActions phpObj = new phpTravelActions(driver);
		phpObj.searchHotel();
	}

	@AfterClass
	public static void terminate() {
		commObj.quitDriver();
	}

}
