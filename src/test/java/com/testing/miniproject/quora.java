package com.testing.miniproject;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import utilities.Excel;

import utilities.*;
;

public class quora {

	public static WebDriver driver;
	
	String file = System.getProperty("user.dir")+"/src/test/resources/testdata/data.xlsx";
	
	public void setup() throws IOException {
		driver=DriverSetup.getWebDriver();
	}
	
	
	public void searchbox() throws IOException {
		
//		
		String Test = Excel.getCellData(file, "input_data", 1, 0);
//		

		driver.findElement(By.xpath("//input[@placeholder='Search for questions, people, and topics']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search for questions, people, and topics']")).sendKeys(Test);
	}
	
	public void selectoption() {
		driver.findElement(By.xpath("//div[@id='selector-option-0']//div//div[contains(@class,'q-text qu-dynamicFontSize--regular')]")).click();
	}
	
	public void compare() throws IOException {
		String text = driver.findElement(By.xpath("//div[@class='q-box qu-borderBottom qu-pt--small qu-pb--small qu-bg--raised']//div[@class='q-text qu-dynamicFontSize--regular qu-medium qu-color--gray_dark qu-passColorToLinks']")).getText();
		//System.out.println(text);
		
		//int row=Excel.getRowCount(file,"text_verification");
		//for(int i=1;i<row;i++) {
		String expectedtitle  = Excel.getCellData(file,"text_verification",1,0);
		
		
		if(text.equals(expectedtitle)) 
			{
				System.out.println("Results for testing : Text present");
				Excel.setCellData(file,"text_verification", 1, 1, "Pass");
				Excel.fillGreenColor(file, "text_verification", 1, 1);
				
			}
			else {
				System.out.println("Results for testing : Text not present");
				Excel.setCellData(file,"text_verification", 1, 1, "fail");
				Excel.fillRedColor(file, "text_verification", 1, 1);
				
			}
	}
	//}
	
	
	public void sign_in() {
		 driver.findElement(By.xpath("//div[@class='q-flex qu-ml--small qu-alignItems--center']//button[@role='button']")).click();
	}
	
	public void sign_email() {
		driver.findElement(By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(1) > span:nth-child(4) > div:nth-child(1)")).click();
	}
	
	public void check_button() throws IOException {
	
        
        WebElement button3 = driver.findElement(By.cssSelector(".q-click-wrapper.qu-active--textDecoration--none.qu-focus--textDecoration--none.qu-disabled.qu-borderRadius--pill.qu-alignItems--center.qu-justifyContent--center.qu-whiteSpace--nowrap.qu-userSelect--none.qu-display--inline-flex.qu-bg--blue.qu-tapHighlight--white.qu-textAlign--center.qu-cursor--pointer.qu-hover--textDecoration--none.ClickWrapper___StyledClickWrapperBox-zoqi4f-0.iyYUZT.base___StyledClickWrapper-lx6eke-1.hIqLpn.puppeteer_test_modal_submit"));
        boolean buttonvaluees = button3.isEnabled();
        System.out.println("is the sign up enabled ? " +buttonvaluees);
        if (buttonvaluees==true){
        	Excel.setCellData(file,"signup_validation", 1, 1, "yes");  
        	Excel.fillGreenColor(file, "signup_validation", 1, 1);
        }
        else {
        	Excel.setCellData(file,"signup_validation", 1, 1, "no"); 
        	Excel.fillRedColor(file, "signup_validation", 1, 1);
        	
        }
        
        
	}
	
	public void mail_validation() throws IOException {
		String email=Excel.getCellData(file, "input_data", 1, 2);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        System.out.print("email verfication : ");
        System.out.println(driver.findElement(By.xpath("//div[@class='q-text qu-dynamicFontSize--small qu-color--red_error']")).getText());      
	}
	
	public void screenshot() throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
        File src=ts.getScreenshotAs(OutputType.FILE);
        File trg=new File("C:\\Users\\2304127\\eclipse-workspace\\miniproject\\miniproject\\screenshot\\image.png");
        
        FileUtils.copyFile(src,trg);
        System.out.println("screenshot captured");
	}
	
	public void exit(){
		
		driver.quit();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		
		quora qu= new quora();
		
		qu.setup(); //launch the browser
		
		qu.searchbox(); //go to search box & give input
		
		qu.selectoption(); //select the search option
		
		qu.compare(); //compare the expected title & actual title
		
		qu.sign_in(); //click on sign in
       
        qu.sign_email(); //click on sign in with email
        
        qu.check_button(); //check the sign in button enabled or disabled
        
        qu.mail_validation();//send the invalid email & print the msg displayed
             
        qu.screenshot(); //screenshot capture
        
        qu.exit(); //close the browser
        
        
      
	}

}
