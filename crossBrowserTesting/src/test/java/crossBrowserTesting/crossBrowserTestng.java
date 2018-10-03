package crossBrowserTesting;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonUtilities.PropertyManager;
import commonUtilities.commonUtilities;
import commonUtilities.phpTravelActions;

public class crossBrowserTestng {
	private static WebDriver driver = null;
	private static commonUtilities commObj = null;
	
	@BeforeClass
	public static void init() throws Throwable {
		commObj = new commonUtilities();
		driver = commObj.getDriver("windows8", "testng");
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
