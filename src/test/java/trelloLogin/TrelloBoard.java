package trelloLogin;

	import java.io.FileInputStream;
	import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
	import org.openqa.selenium.Point;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class TrelloBoard { 
		WebDriver driver;
	@BeforeMethod
		public void setup() {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://trello.com/en/login");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	@Test
	public void Execute() throws Exception {
		
		String path="C:\\Users\\Dell\\eclipse-workspace\\Mproject\\Excel Data Provider\\Excel Sheet 1.xlsx";
		FileInputStream fis=new FileInputStream(path);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		String mailID = wb.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue();
		
	WebElement email=driver.findElement(By.xpath("//input[@inputmode='email']"));
	email.sendKeys(mailID);
	driver.findElement(By.xpath("//input[@id='login']")).click();
	WebDriverWait wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
	WebElement Password=driver.findElement(By.xpath("//input[@name='password']"));
	String pass = wb.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
	Password.sendKeys(pass);
		
	driver.findElement(By.xpath("//span[text()='Log in']")).click();
	driver.findElement(By.xpath("//p[text()='Create']")).click();
	driver.findElement(By.xpath("//span[text()='Create board']")).click();
	WebElement boardname=driver.findElement(By.xpath("//input[@data-testid='create-board-title-input']"));
	boardname.sendKeys("SarikaBoardTrello");
	driver.findElement(By.xpath("//button[text()='Create']")).click();
	WebElement listA=driver.findElement(By.xpath("//input[@class='list-name-input']"));
	listA.sendKeys("List A");
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	WebElement listB=driver.findElement(By.xpath("//input[@class='list-name-input']"));
	listB.sendKeys("List B");
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	driver.findElement(By.xpath("(//a[@class='open-card-composer js-open-card-composer'])[1]")).click();
	WebElement cardname=driver.findElement(By.xpath("//textarea[@class='list-card-composer-textarea js-card-title']"));
	cardname.sendKeys("Card A");
	driver.findElement(By.xpath("//input[@value='Add card']")).click();
	Actions Act=new Actions(driver);
	WebElement from=driver.findElement(By.xpath("//span[@class='list-card-title js-card-name']"));
	WebElement to =driver.findElement(By.xpath("(//div[@class='list-header-target js-editing-target'])[2]"));
	Act.dragAndDrop(from, to).build().perform();
	WebElement from1=driver.findElement(By.xpath("//span[@class='list-card-title js-card-name']"));
	Point Cord=from1.getLocation();
	int XCord=Cord.getX();
	int YCord=Cord.getY();
	System.out.println("X-coordinate = "+XCord +" , "+"Y-coordinate = "+YCord);
	driver.findElement(By.xpath("//span[@class='p6oJr7SHjK+vLr aqePx81u4BGHTH Glb3QqRGpd64YB']")).click();
	driver.findElement(By.xpath("//button[@data-testid='account-menu-logout']")).click();
	}
	@AfterMethod
	public void close() {
		driver.close();
	}
	}

