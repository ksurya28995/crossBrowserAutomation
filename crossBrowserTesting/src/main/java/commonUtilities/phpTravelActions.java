package commonUtilities;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class phpTravelActions extends phpTravelObjects {

	WebDriver driver;
	Map<String, String> arrData;
	commonUtilities commObj;
	
	public phpTravelActions(WebDriver driverObj) throws Exception {
		driver = driverObj;
		commObj = new commonUtilities();
		PageFactory.initElements(driver, this);
	}

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
		//assertTrue("Results Page not found", results.getText().equalsIgnoreCase("No Results Found"));
		/*
		commObj.clickElement(flights);
		commObj.threadsleep(2000);
		assertTrue("Cars Page not found", flightsTxt.isDisplayed());
		commObj.clickElement(visa);
		commObj.threadsleep(2000);
		assertTrue("Visa Page not found", visaTxt.isDisplayed());
		commObj.clickElement(home);
		commObj.threadsleep(3000);
		*/
	}
}
