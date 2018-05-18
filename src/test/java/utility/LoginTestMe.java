package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTestMe {
	private WebDriver driver;
	@Test(dataProvider ="logindata",priority=0)
	public void test_me_login(String uname,String pass) throws Exception 
	{
		
		driver.findElement(By.id("userName")).clear();
		driver.findElement(By.id("userName")).sendKeys(uname);
		driver.findElement(By.id("userName")).clear();
		driver.findElement(By.cssSelector("#password")).sendKeys(pass);
		driver.findElement(By.cssSelector("body > main > div > div > div > form > fieldset > div:nth-child(8) > div > input.btn.btn-lg.btn-success.col-xs-4.col-md-offset-0")).click();
		driver.navigate().to("http://localhost:8083/TestMeApp/fetchcat.htm");

	}
	
	@Test(priority=2)
	public void select_category()
	{
		WebElement category = driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/a/span"));
		Actions act = new Actions(driver);
		act.moveToElement(category).click().build().perform();
		
		WebElement electronics = driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/ul/li[1]/a/span"));
		act.moveToElement(electronics).click().build().perform();
		
		WebElement headphones = driver.findElement(By.xpath("//*[@id=\"submenuul11290\"]/li[1]/a/span"));
		act.moveToElement(headphones).click().build().perform();
		
		Assert.assertEquals(driver.getTitle(), "Search");
	}
	
	@Test(priority=3)
	public void add_To_Cart()
	{
	
		WebElement cart = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a"));
		Actions act = new Actions(driver);
		act.moveToElement(cart).click().build().perform();
		
	}
	
	@DataProvider(name="logindata")
	public String[][] login_data() throws Exception
	{
		ExcelUtility.setExcelPath("Sheet1", "C:\\Users\\A06438_P5.Training\\Desktop\\drivers\\ExcelUtils.xlsx");
		String[][] tabledata = ExcelUtility.getExcelTable();
		return tabledata;
	}
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\A06438_P5.Training\\Desktop\\drivers\\chromedriver_win32\\chromedriver.exe" );
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8083/TestMeApp/login.htm");
	}

}
