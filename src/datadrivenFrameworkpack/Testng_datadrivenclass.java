package datadrivenFrameworkpack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Testng_datadrivenclass 
{
	public static WebDriver driver;
	
@BeforeSuite
public void launchbrowser() {
	System.setProperty("webdriver.chrome.driver", "D:\\new chromedriver\\chromedriver_win32\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
@Test(dataProvider = "dp")
public void login(String uname,String pass) 
{
	driver.get("https://opensource-demo.orangehrmlive.com");
	driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(uname);
	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(pass);
    driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
}
@DataProvider(name = "dp")
public Object[][] getcellvalue() throws IOException {
	Datadrivenclass value=new Datadrivenclass ();
	value.datasheet("C:\\Users\\krgpo\\Desktop\\OrangeHRMLogin.xlsx");
	int rowno=value.rowcount(0);
	Object[][] data=new Object[rowno][2];
	for(int i=0; i<rowno; i++) {
		data[i][0]=value.getcelldata(0, i, 0);
		data[i][1]=value.getcelldata(0, i, 1);	
	}return data;
}
@AfterSuite
public void driverclose() {
	driver.quit();
}
}
