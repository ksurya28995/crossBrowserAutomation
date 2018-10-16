package commonUtilities;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class phpTravelActions extends phpTravelObjects {

	WebDriver driver;
	Map<String, String> arrData;
	commonUtilities commObj;
	
	/**
	 * Method is used as a constructor
	 * 
	 * @param Webdriver driver
	 * 
	 * @author surya.k.kumaresan
	 */
	public phpTravelActions(WebDriver driverObj) throws Exception {
		driver = driverObj;
		commObj = new commonUtilities();
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method is used to search some points on the site
	 * 
	 * @author surya.k.kumaresan
	 */
	public void searchHotel() throws Throwable {
		arrData = commObj.readCsvData("phpInputs.csv");
		commObj.setTextBox(checkInDateFld, arrData.get("Check In Date"));
		commObj.setTextBox(checkOutDateFld, arrData.get("Check Out Date"));
		commObj.clickElement(noOfPeopleFld);
		commObj.clickElement(addAdultBtn);
		commObj.clickElement(addAdultBtn);
		commObj.clickElement(addChildBtn);
		commObj.clickElement(searchBtn);
		commObj.threadsleep(5000);
	}
}
