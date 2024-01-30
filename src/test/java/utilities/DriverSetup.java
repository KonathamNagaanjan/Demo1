package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testing.annotations.Test;

public class DriverSetup {
	
	
	public static WebDriver driver;
	public static WebDriver getWebDriver() throws IOException {
		
		
	Properties prop = new Properties();
	File file = new File("config.properties");
	FileInputStream fis = new FileInputStream(file);
	prop.load(fis);
	
	String browserName = prop.getProperty("browser");
	///////////////
	//String browserName = "edge";
	 WebDriver driver = null;
	 
	 if(browserName.equalsIgnoreCase("chrome")) {
		 driver = new ChromeDriver();
	 }
	 
	 else if(browserName.equalsIgnoreCase("edge")) {
		 driver = new EdgeDriver();	 
	 }
	 
	 else {
		 System.out.println("provide a valid browser");
	 }
	
	//WebDriver driver=new ChromeDriver();
	driver.get(" https://www.quora.com/profile/Quora");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	return driver;
	
	}
	
	

}
