package com.Airpay.BusinessLogic;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Airpay.PageObject.Airpay_PaymentPage_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;

public class AirPay_Payment_Mode_EMI_Credit_Card_BusinessLogic extends Airpay_PaymentPage_PageObject {

	public WebDriver driver;
	public String TC_ID = "";
	ElementAction Assert = new ElementAction();
	Log log = new Log();	
	public AirPay_Payment_Mode_EMI_Credit_Card_BusinessLogic(WebDriver driver, String TC_ID)
	{
		this.driver = driver;
		this.TC_ID=TC_ID;
		log = new Log();
	}
	
	public void EMI_CardHolderName() throws Exception{
		try{		   		   
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, EmiBankNameSelectDropDown, Excel_Handling.Get_Data(TC_ID, "BankName").trim(), "Bank Selected for EMI");					
			Assert.inputText(driver, EMICardNumber, Excel_Handling.Get_Data(TC_ID, "ValidCardNumber").trim(), "EMI Card Number");
			Assert.inputText(driver, EMICardHolderName, Excel_Handling.Get_Data(TC_ID, "BuyerFirstName").trim(), "Card Holder name");
			Assert.inputText(driver, EMICardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "EMI Card Number Exp Date");
			Assert.inputText(driver, EMICardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), " card Number CVVCode");
			Assert.Clickbtn(driver, EMICardMakePaymtBtn, "EMI Card make payment button");		   
			Extent_Reporting.Log_report_img("Details has been Entered", "Passed", driver);

		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
		
	public void EMI_Invalid_card_Details() throws Exception{
		try{		   		   
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, EmiBankNameSelectDropDown, Excel_Handling.Get_Data(TC_ID, "BankName").trim(), "Bank Selected for EMI");					
			Assert.inputText(driver, EMICardNumber, Excel_Handling.Get_Data(TC_ID, "InvalidCardNumber").trim(), "Invalid EMI Card Number");
			Assert.inputText(driver, EMICardHolderName, Excel_Handling.Get_Data(TC_ID, "BuyerFirstName").trim(), "Card Holder name");
			Assert.inputText(driver, EMICardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "EMI Card Number Exp Date");
			Assert.inputText(driver, EMICardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), " card Number CVVCode");
			Extent_Reporting.Log_report_img("Details has been Entered", "Passed", driver);
			Assert.Clickbtn(driver, EMICardMakePaymtBtn, "EMI Card make payment button");		   
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	public void EMI_Bank_Selection_withEMiType() throws Exception{
		try{
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, EmiBankNameSelectDropDown, Excel_Handling.Get_Data(TC_ID, "BankName").trim(), "Bank Selected for EMI");					
				List<WebElement> radiobtn = driver.findElements(By.xpath(EMIActive_radio_verfiy));
				List<WebElement> Month = driver.findElements(By.xpath(EMIMonthname));			
				for(int i =1;i<radiobtn.size();i++)
				{
					radiobtn.get(i).click();
					String Month_period = Month.get(i).getText();
					Extent_Reporting.Log_report_img("Checking Radio button", "Passed", driver);
					Extent_Reporting.Log_Pass("Radio button checked for the EMI"+Month_period, "Passed");
				}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("EMI option table not in proper", "Failed", driver);   
			Log.error("EMI Option issue");
			e.printStackTrace();
			throw new Exception("EMI Option Issue");
		}
	}

	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	 */
	public static String bankName = null;
	public void EMI_bank_Selection_verification() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, EmiBankNameSelectDropDown, "Select Bank Drop Down" ))
			{         	
				WebElement selectDropBox = driver.findElement(By.xpath(EmiBankNameSelectDropDown));
				Select select =new Select(selectDropBox);
				List<WebElement> optionValue = select.getOptions();
				for(int i =1;i<optionValue.size();i++)
				{				
					WebElement selectDropBox1 = driver.findElement(By.xpath(EmiBankNameSelectDropDown));
					Select select1 =new Select(selectDropBox1);
					Assert.selectDropBoxValue(driver, EmiBankNameSelectDropDown, i, " Bank Name");//(driver, Netbank_DropDown, value[i], value[i+1]+" Bank ");			
					bankName =  select1.getFirstSelectedOption().getText();
					Extent_Reporting.Log_report_img(bankName+" Bank Selected", "Passed", driver);					
				}   		
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail("Bank Name selection issue",	"Failed",driver);
				Log.error("Bank Name selection does not displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" Bank option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	 */
	public void EMI_Blank_val_RedLineErrNotification() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");
			if(Assert.isElementDisplayed(driver, EMICardMakePaymtBtn, "Make payment button" ))
			{  
				Extent_Reporting.Log_report_img("It's entering without filling any details", "Passed", driver);
				Assert.Clickbtn(driver, EMICardMakePaymtBtn, "EMI Card make payment button");
				List<WebElement> RedLineErr = driver.findElements(By.xpath(EMIRedLineError));
				for(int i =1;i<=RedLineErr.size();i++)
				{
					String fieldBlnakName = driver.findElement(By.xpath("("+EMIRedLineError+"//label)["+i+"]")).getText().trim();				
					Extent_Reporting.Log_Pass("Red Line error is exist for "+fieldBlnakName, "Passed");
					Extent_Reporting.Log_report_img("Red line Error exist", fieldBlnakName, driver);				
				}   		
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail("Bank Name selection issue",	"Failed",driver);
				Log.error("Bank Name selection does not displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" Bank option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	
	public void BharatQRMakePayBtn() throws Exception{
		try{		   		   			
			Extent_Reporting.Log_report_img("Bharat QR option is exist", "Passed", driver);
			Assert.Clickbtn(driver, BharatOrMakepaymtBtn, "Bharat QR make payment");		   
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Bharat QR option does not disp", "Failed", driver);   
			Log.error("Bharat QR option issue");
			e.printStackTrace();
			throw new Exception("Bharat QR option");
		}
	}	
	
	
	
	public void Verify_BharatQR_BarCodeImg() throws Exception{
		try{	
			Thread.sleep(5000);
			if(Assert.isElementDisplayed(driver, BharatQRbarCodeImg, "Bharat QR code"))
			{
				Assert.Verify_Image(driver, BharatQRbarCodeImg, "Bar code");
				Extent_Reporting.Log_report_img("Bharat QR code is exist", "Passed", driver);
			}else{			
				Extent_Reporting.Log_Fail("Bharat QR Popup does not exist","failed",driver);   
				throw new Exception("Test failed due to local host page not displayed");
			}	   
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Bar code issue", "Failed", driver);   
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}	
	
}