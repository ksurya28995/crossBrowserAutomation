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

	public Map<String, String> readCsvData(String csvName) throws Exception {
		Map<String, String> tabArray = new HashMap<String, String>();
		filePath = getFilepath(csvName);
		tabArray = getCSVData(filePath);
		return tabArray;
	}

	public URI getFilepath(String csvName) throws Exception {
		URI uri;
		uri = (URI) (new File("src/test/resources/inputCSVs/" + csvName).exists()
				? new File("src/test/resources/inputCSVs/" + csvName)
				: null).toURI();
		if (uri == null)
			assert uri != null : "Input CSV file missing";
		return uri;
	}

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

	public void waitForElement(WebElement elem) {
		elem = new WebDriverWait(driver, waitTime).until(ExpectedConditions.visibilityOf(elem));
	}

	public void openURL(String url) throws Throwable {
		driver.get(url);
		getThreeSecWait();
	}

	public void getThreeSecWait() throws InterruptedException {
		Thread.sleep(3000);
	}

	public void getTwoSecWait() throws InterruptedException {
		Thread.sleep(2000);
	}

	public void getFiveSecWait() throws InterruptedException {
		Thread.sleep(5000);
	}

	public void hoverElement(WebElement elem) throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(elem).build().perform();
		getTwoSecWait();
	}

	public void clickElement(WebElement elem) throws InterruptedException {
		waitForElement(elem);
		elem.click();
		Thread.sleep(2000);
	}

	public void actionClickElement(WebElement elem) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elem);
	}
	
	public void threadsleep(int millisecs) throws InterruptedException {
		Thread.sleep(millisecs);
	}

	public void verify(WebElement elem) throws InterruptedException {
		waitForElement(elem);
		elem.isDisplayed();
	}

	public void setTextBox(WebElement elem, String value) throws InterruptedException {
		waitForElement(elem);
		//elem.clear();
		elem.sendKeys(value);
		Thread.sleep(1000);
	}

	public void setVirtualDropdown(WebElement elem, String value) throws InterruptedException {
		waitForElement(elem);
		elem.clear();
		elem.sendKeys(value);
		Thread.sleep(500);
		focusFirstInputField();
		getThreeSecWait();
	}

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

	public String generateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0
				: new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
						+ (int) Math.pow(10, charLength - 1));
	}

	public String getTextBox(WebElement elem) {
		waitForElement(elem);
		return elem.getText();
	}

	public WebElement findElemById(String path) {
		return driver.findElement(By.id(path));
	}

	public WebElement findElemByXpath(String path) {
		return driver.findElement(By.xpath(path));
	}

	public void clickElement(String path) {
		if (path.contains("//"))
			findElemByXpath(path).click();
		else
			findElemById(path).click();
	}

	public void offsetClickElement(WebElement elem) throws InterruptedException {
		waitForElement(elem);
		int elemWidth = elem.getSize().getWidth();
		int elemHeight = elem.getSize().getHeight();
		Actions action = new Actions(driver);
		action.moveToElement(elem, elemWidth - 2, elemHeight - 1).click().build().perform();
		Thread.sleep(1000);
	}
	
	public void quitDriver() {
		driver.quit();
	}
}
