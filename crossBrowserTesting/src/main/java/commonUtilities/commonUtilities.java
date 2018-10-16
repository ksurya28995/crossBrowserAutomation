package commonUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class commonUtilities {
	static WebDriver driver;
	
	/**
	 * Method is used to load the capabilities to the webdriver
	 * 
	 * @param platform where the script gonna run
	 * 
	 * @param testmode to decide which run tool to run with(junit/testng). 
	 * 
	 * @author SuryaRay
	 */
	public WebDriver getDriver(String platform, String testMode) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName(PropertyManager.getPropertyVal("browser"));
		caps.setVersion(PropertyManager.getPropertyVal("browserversion"));
		//caps.setCapability(browserstack.debug=true);
		if(platform.equalsIgnoreCase("windows10"))
			caps.setPlatform(Platform.WIN10);
		else if(platform.equalsIgnoreCase("windows8"))
			caps.setPlatform(Platform.WIN8);
		else if(platform.equalsIgnoreCase("mac"))
			caps.setPlatform(Platform.MAC);
		if (testMode.equalsIgnoreCase("junit"))
			caps.setCapability("build", "Junit - Sample");
		else 
			caps.setCapability("build", "TestNG - Sample");
		String remoteUrl = "https://"+PropertyManager.getPropertyVal("username")+":"+PropertyManager.getPropertyVal("accesskey")+PropertyManager.getPropertyVal("huburl"); 
		System.out.println("remoteURL: "+remoteUrl);
		driver = new RemoteWebDriver(new URL(remoteUrl), caps);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("crossbrowser connection established");
		return driver;
	}

	
	URI filePath;
	/**
	 * Method is used to read the csv file and map the values to a mapping object
	 * 
	 * @param csvName Name of the CSV
	 * 
	 * @author SuryaRay
	 */
	public Map<String, String> readCsvData(String csvName) throws Exception {
		Map<String, String> tabArray = new HashMap<String, String>();
		filePath = getFilepath(csvName);
		tabArray = getCSVData(filePath);
		return tabArray;
	}
	
	/**
	 * Method is used to get the csv file path in the URI format
	 * 
	 * @param csvName Name of the CSV
	 * 
	 * @author SuryaRay
	 */
	public URI getFilepath(String csvName) throws Exception {
		URI uri;
		uri = (URI) (new File("src/test/resources/inputCSVs/" + csvName).exists()
				? new File("src/test/resources/inputCSVs/" + csvName)
				: null).toURI();
		if (uri == null)
			assert uri != null : "Input CSV file missing";
		return uri;
	}

	/**
	 * Method is used to break the csv line and map the values to the map object
	 * 
	 * @param filePath CSV file path in URI format
	 * 
	 * @author SuryaRay
	 */
	public Map<String, String> getCSVData(URI filePath) {
		Map<String, String> tabArray = new HashMap<String, String>();
		try {
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line1 = br.readLine();
			String line2 = br.readLine();
			br.close();
			String[] headers = line1.split(";", -1);
			String[] values = line2.split(";", -1);
			for (int i = 0; i < headers.length; i++) {
				tabArray.put(headers[i], values[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tabArray;
	}

	/**
	 * Method is used to set the values to any column in the csv file
	 * 
	 * @param csvName Name of the CSV
	 * @param clmnName Name of the column to which data to be updated
	 * @param value value to be added to the column
	 * 
	 * @author SuryaRay
	 */
	public void setCsvData(String csvName, String clmnName, String value) {
		try {
			String headers = "";
			String values = "";
			// for same order of csv columns @last
			filePath = getFilepath(csvName);
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String[] keysArray = br.readLine().split(";", -1);
			br.close();
			// replace the value in the column
			Map<String, String> arrData = readCsvData(csvName);
			arrData.replace(clmnName, value);
			// get ready the data to write in csv
			for (int i = 0; i < keysArray.length; i++) {
				headers = headers + keysArray[i];
				values = values + arrData.get(keysArray[i]);
				if (i < keysArray.length - 1) {
					headers = headers + ";";
					values = values + ";";
				}
			}
			// write data to csv
			PrintWriter pWrite = new PrintWriter(new FileWriter(file));
			pWrite.println(headers);
			pWrite.print(values);
			pWrite.close();
			for (int i = 0; i < keysArray.length; i++) {
				System.out.println("Head/Value: " + keysArray[i] + " | " + arrData.get(keysArray[i]));
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	final public int waitTime = 30;
	/**
	 * Method is used to wait for the element to appear on the UI
	 * 
	 * @param elem WebElement to wait for.
	 * 
	 * @author SuryaRay
	 */
	public void waitForElement(WebElement elem) {
		elem = new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOf(elem));
	}

	/**
	 * Method is used to open the url on the browser
	 * 
	 * @param url URL of the testing site
	 * 
	 * @author SuryaRay
	 */
	public void openURL(String url) throws Throwable {
		driver.get(url);
		getThreeSecWait();
	}

	/**
	 * Method is used to provide a wait time of 3 secs for loading
	 * 
	 * @author SuryaRay
	 */
	public void getThreeSecWait() throws InterruptedException {
		Thread.sleep(3000);
	}
	
	/**
	 * Method is used to provide a wait time of 2 secs for loading
	 * 
	 * @author SuryaRay
	 */
	public void getTwoSecWait() throws InterruptedException {
		Thread.sleep(2000);
	}

	/**
	 * Method is used to provide a wait time of 5 secs for loading
	 * 
	 * @author SuryaRay
	 */
	public void getFiveSecWait() throws InterruptedException {
		Thread.sleep(5000);
	}

	/**
	 * Method is used to hover on the element 
	 * 
	 * @param elem The element on which to be hovered
	 * 
	 * @author SuryaRay
	 */
	public void hoverElement(WebElement elem) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(elem).build().perform();
		getTwoSecWait();
	}

	/**
	 * Method is used to click on the element
	 * 
	 * @param elem The element on which CLick has to be made.
	 * 
	 * @author SuryaRay
	 */
	public void clickElement(WebElement elem) throws InterruptedException {
		waitForElement(elem);
		elem.click();
		Thread.sleep(2000);
	}

	/**
	 * Method is used to click on the element via javascript
	 * 
	 * @param elem to be clicked
	 * 
	 * @author SuryaRay
	 */
	public void actionClickElement(WebElement elem) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elem);
	}
	
	/**
	 * Method is used to wait 
	 * 
	 * @param millisecs how much time to wait for.
	 * 
	 * @author SuryaRay
	 */
	public void threadsleep(int millisecs) throws InterruptedException {
		Thread.sleep(millisecs);
	}

	/**
	 * Method is used to verify the element is appeared on the UI
	 * 
	 * @param element to appeared on the UI
	 * 
	 * @author SuryaRay
	 */
	public void verify(WebElement elem) throws InterruptedException {
		waitForElement(elem);
		elem.isDisplayed();
	}

	/**
	 * Method is used to enter the text to the field
	 * 
	 * @param elem to which the text has to be entered, value to be entered
	 * 
	 * @author SuryaRay
	 */
	public void setTextBox(WebElement elem, String value) throws InterruptedException {
		waitForElement(elem);
		//elem.clear();
		elem.sendKeys(value);
		Thread.sleep(1000);
	}

	/**
	 * Method is used to set the value in the dropdwon
	 * 
	 * @param elem to which the value has to be selected, value to be selected
	 * 
	 * @author SuryaRay
	 */
	public void setVirtualDropdown(WebElement elem, String value) throws InterruptedException {
		waitForElement(elem);
		elem.clear();
		elem.sendKeys(value);
		Thread.sleep(500);
		focusFirstInputField();
		getThreeSecWait();
	}

	/**
	 * Method is used to provide the first focus like clicking outside after filing the dropdown
	 * 
	 * @author SuryaRay
	 */
	public void focusFirstInputField() {
		try {
			List<WebElement> inputElems = driver.findElements(By.tagName("input"));
			for (WebElement eachInput : inputElems) {
				if (eachInput.isEnabled() && "text".equals(eachInput.getAttribute("type"))) {
					eachInput.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method is used to generate a random number
	 * 
	 * @param charLength length of the number generated
	 * 
	 * @author SuryaRay
	 */
	public String generateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0
				: new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
						+ (int) Math.pow(10, charLength - 1));
	}

	/**
	 * Method is used to get the text from the UI
	 * 
	 * @param elem from which the text has to be fetched
	 * 
	 * @author SuryaRay
	 */
	public String getTextBox(WebElement elem) {
		waitForElement(elem);
		return elem.getText();
	}

	/**
	 * Method is used to find the element by its ID
	 * 
	 * @param path Id of the element
	 * 
	 * @author SuryaRay
	 */
	public WebElement findElemById(String path) {
		return driver.findElement(By.id(path));
	}

	/**
	 * Method is used to find the element by its XPATH
	 * 
	 * @param path Xpath of the element
	 * 
	 * @author SuryaRay
	 */
	public WebElement findElemByXpath(String path) {
		return driver.findElement(By.xpath(path));
	}

	/**
	 * Method is used to click the element 
	 * 
	 * @param path of the element
	 * 
	 * @author SuryaRay
	 */
	public void clickElement(String path) {
		if (path.contains("//"))
			findElemByXpath(path).click();
		else
			findElemById(path).click();
	}

	/**
	 * Method is used to click on the element based on an offset of the element
	 * 
	 * @param elem to be clicked
	 * 
	 * @author SuryaRay
	 */
	public void offsetClickElement(WebElement elem) throws InterruptedException {
		waitForElement(elem);
		int elemWidth = elem.getSize().getWidth();
		int elemHeight = elem.getSize().getHeight();
		Actions action = new Actions(driver);
		action.moveToElement(elem, elemWidth - 2, elemHeight - 1).click().build().perform();
		Thread.sleep(1000);
	}
	
	/**
	 * Method is used to close the browser after the execution
	 * 
	 * @author SuryaRay
	 */
	public void quitDriver() {
		driver.quit();
	}
}
