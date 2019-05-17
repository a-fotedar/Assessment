package webAutomation;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class validateUser {

	public static void main(String[] args) throws InterruptedException {
		// Defined path where ChromeDriver.exe is stored
		String driverPath = "C:\\chromedriver.exe";
		
		// Setting Chrome WebDriver to System Properties
		System.setProperty("webdriver.chrome.driver", driverPath); 
		
		// Create a new instance of the Chrome driver
		WebDriver driver = new ChromeDriver();
		
		//Launch the Way2Automation Web-site
		driver.get("http://way2automation.com/angularjs-protractor/webtables/");
		
		//Define Variables
		List<WebElement> tableRows = driver.findElements(By.xpath("//tr[@class='smart-table-data-row ng-scope']"));
		boolean flag = false;
		
		//Validate User is on Table
		for(WebElement rowData : tableRows) {
			if(rowData.getText().contains("Akshaye")){
				flag = true;
			}
		}
		
		//Print Validation Result
		if(flag) {
			System.out.println("Found User");
		}
		else {
			System.out.println("User not Found");
		}

		//Add a User
		
		//Click on Add a User Link
		driver.findElement(By.xpath("//button[@class='btn btn-link pull-right']")).click();
		
		//Enter First Name
		driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("FName1");
		
		//Enter Last Name
		driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("LName1");
		
		//Enter User Name
		driver.findElement(By.xpath("//input[@name='UserName']")).sendKeys("User1");
		
		//Enter a Password
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Pass1");
		
		//Select Customer Company
		//List<WebElement> customerRadioBtn = driver.findElements(By.xpath("//input[@name='optionsRadios']"));

		List<WebElement> customerRadioBtn = driver.findElements(By.xpath("//label[@class='radio ng-scope ng-binding']"));
		int options = customerRadioBtn.size();
 
		// Start the loop from first Radio Option to last Radio Option
		for(int i=0; i < options ; i++ ){
		// Store the Option name to the string variable, using 'innerText' attribute
			String optionValue = customerRadioBtn.get(i).getAttribute("innerText");
			
			// Select the Option if the value is what you are looking for
			if (optionValue.equalsIgnoreCase("Company AAA")){
				customerRadioBtn.get(i).click();
				// This will take the execution out of for loop
				break;
			}
		}

		
		// Select User Role
		Select userRole = new Select(driver.findElement(By.xpath("//select[@name='RoleId']")));
		userRole.selectByVisibleText("Admin");
		
		//Enter E-mail Address
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("admin@mail.com");
		
		// Enter Mobile Number
		driver.findElement(By.xpath("//input[@name='Mobilephone']")).sendKeys("082555");
		
		//Submit User Details
		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		
		//Check UserName is Unique
		//reset flag to false
		flag = false;
		String userName = null;
		//Iterate through table checking each UserName
		for (int i=1;i<=tableRows.size()+1;i++) {
			userName = driver.findElement(By.xpath("//tbody/tr["+ i +"]/td[3]")).getText();
			for (int j=i+1;j<=tableRows.size()+1;j++) {
				if (driver.findElement(By.xpath("//tbody/tr["+ j +"]/td[3]")).getText().contentEquals(userName)){
					flag = true;
					break;
				}
				//Print UserName Comparison
				System.out.println("Comparing \"" + userName + "\" to \"" + driver.findElement(By.xpath("//tbody/tr["+ j +"]/td[3]")).getText()+ "\"");
			}
			if (flag) {
				break;				
			}
		}
		if (flag) {
			System.out.println("User Name \"" + userName + "\" is not unique!");
		}
		else {
			System.out.println("All User Names are unique!");
		}
		
		//Ensure Users are added to List
		/*
		 * CODE TO BE SPLIT INTO DIFFERENT CLASSES AND CALLED SEPERATELY.
		 */
		//Wait for 5 Sec
		Thread.sleep(5000);
		 
		// Close the driver
		driver.quit();
	}

}
