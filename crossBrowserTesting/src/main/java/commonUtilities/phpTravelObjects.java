package commonUtilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
/**
 * Class is used to hold the elements of the UI ready with their finding IDs
 * 
 * @author surya.k.kumaresan
 */
public class phpTravelObjects {
	
	@FindBy(how=How.XPATH, using = "//span[@id='__symantecPKIClientDetector']//following-sibling::div[2]//input")
	public WebElement hotellocationFld;
	
	@FindBy(how=How.XPATH, using = "//span[contains(text(),'Search by Hotel or City Name')]")
	public WebElement hotellocation;
	
	@FindBy(how=How.XPATH, using = "//input[@name='checkin']")
	public WebElement checkInDateFld;
	
	@FindBy(how=How.XPATH, using = "//input[@name='checkout']")
	public WebElement checkOutDateFld;
	
	@FindBy(how=How.XPATH, using = "//input[@name='travellers']")
	public WebElement noOfPeopleFld;
	
	@FindBy(how=How.XPATH, using = "//button[@id='adultPlusBtn']")
	public WebElement addAdultBtn;
	
	@FindBy(how=How.XPATH, using = "//button[@id='childPlusBtn']")
	public WebElement addChildBtn;
	
	@FindBy(how=How.XPATH, using = "//input[@name='slug']//following-sibling::button[@type='submit']")
	public WebElement searchBtn;
	
	@FindBy(how=How.XPATH, using = "//a[@title='Hotels']")
	public WebElement hotels;
	
	@FindBy(how=How.XPATH, using = "//a[@title='Flights']")
	public WebElement flights;
	
	@FindBy(how=How.XPATH, using = "//a[@title='Tours']")
	public WebElement tours;
	
	@FindBy(how=How.XPATH, using = "//a[@title='Cars']")
	public WebElement cars;
	
	@FindBy(how=How.XPATH, using = "//a[@title='Ivisa']")
	public WebElement visa;
	
	@FindBy(how=How.XPATH, using = "//a[@title='Offers']")
	public WebElement offers;
	
	@FindBy(how=How.XPATH, using = "//a[@title='Blog']")
	public WebElement blog;
	
	@FindBy(how=How.XPATH, using = "//li/a[@href='https://www.phptravels.net/']")
	public WebElement home;
	
	@FindBy(how=How.XPATH, using = "//div[@class='itemscontainer']//h2")
	public WebElement results;
	
	@FindBy(how=How.XPATH, using = "//*[contains(text(),'Available Flights')]")
	public WebElement flightsTxt;
	
	@FindBy(how=How.XPATH, using = "//*[contains(text(),'Calculate Visa Cost Before Applying')]")
	public WebElement visaTxt;
	
	@FindBy(how=How.XPATH, using = "//*[contains(text(),'More information')]")
	public WebElement offersTxt;
	
	@FindBy(how=How.XPATH, using = "//*[contains(text(),'Categories and Publications')]")
	public WebElement blogTxt;
	/*
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	
	@FindBy(how=How.XPATH, using = "")
	public WebElement ;
	*/
}
