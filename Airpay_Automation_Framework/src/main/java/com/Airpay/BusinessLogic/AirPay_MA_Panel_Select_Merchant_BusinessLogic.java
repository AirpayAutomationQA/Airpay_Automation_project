package com.Airpay.BusinessLogic;


import java.util.List;

import javax.print.DocFlavor.STRING;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Airpay.PageObject.AirPay_Payment_MA_Panel_PageObject;
import com.Airpay.PageObject.Airpay_PaymentPage_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;

public class AirPay_MA_Panel_Select_Merchant_BusinessLogic extends Airpay_PaymentPage_PageObject {

	public WebDriver driver;
	public String TC_ID = "";
	public static String MA_URL = null;
	ElementAction Assert = new ElementAction();
	//AirPay_Payment_MA_Panel_PageObject MA = new AirPay_Payment_MA_Panel_PageObject();
	Log log = new Log();	
	public AirPay_MA_Panel_Select_Merchant_BusinessLogic(WebDriver driver, String TC_ID)
	{
		this.driver = driver;
		this.TC_ID=TC_ID;
		log = new Log();
	}
	/**
	 * @author sakole
	 * @throws Exception
	 */
	public void MA_Panel_Login() throws Exception{
		try{
			MA_URL = Excel_Handling.Get_Data(TC_ID, "MA_URL").trim();
			driver.navigate().to(MA_URL);
			Assert.waitForPageToLoad(driver);
			//Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MA_UserID, Excel_Handling.Get_Data(TC_ID, "MA_UserID").trim(), "MA Panel USer ID");
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MA_UserID, "USER ID")){
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MA_UserID, Excel_Handling.Get_Data(TC_ID, "MA_UserID").trim(), "MA Panel USer ID");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MA_PWD, Excel_Handling.Get_Data(TC_ID, "MA_Password").trim(), "");
				Extent_Reporting.Log_report_img("Login Details entered", "Passed", driver);
				Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MA_SubmitBtn, "Sign In button");
				Assert.waitForPageToLoad(driver);
			}else{
				
				Extent_Reporting.Log_Fail("MA Panel User field does not exist", "Failed", driver);
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("MA Panel User field does not exist", "Failed", driver);
			throw new Exception("MA panel page issue");
		}
	}
	
	public static String MerchantName =null;
	public void Select_Merchant() throws Exception{
		try{			
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_SelectMerchantSymbol, "Select Merchant ID")){			
				MerchantName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_SelectMerchantSymbol)).getAttribute("title").trim();
				if(MerchantName.equalsIgnoreCase("Select Merchant")){
					Extent_Reporting.Log_report_img("Select Merchant", "Passed", driver);
					Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_SelectMerchantSymbol , "Select Merchant");									
					Assert.waitForPageToLoad(driver);
					Assert.WaitUntilExist(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search);
				}else{
					Extent_Reporting.Log_Pass("Merchant already selected", "Passed");
					Extent_Reporting.Log_report_img("Merchant selected as: "+MerchantName, "Passed", driver);
				}				
			}else{
				
				Extent_Reporting.Log_Fail("MA Panel User field does not exist", "Failed", driver);
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("MA Panel User field does not exist", "Failed", driver);
			throw new Exception("MA panel page issue");
		}
	}
	
	
	public void Filter_Merchant() throws Throwable{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search, "Merchant Search"))
			{
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search, Excel_Handling.Get_Data(TC_ID, "Merchant_ID").trim(), "Merchant ID");
				Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search, "Search Entered");
				Thread.sleep(10000);
				Extent_Reporting.Log_report_img("Merchant ID Entered", "Passed", driver);
				Assert.Javascriptexecutor_forClick(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Search, "Merchant ID ");
				Thread.sleep(10000);
				Assert.waitForPageToLoad(driver);
				Assert.WaitUntilExist(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Select);	
			}else{
				Extent_Reporting.Log_Fail("Merchant ID search field does not exost", "Failed", driver);
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Merchant ID search field does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");
			
		}
	}
	
	
	public void Choose_Merchant() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_Select, "Merchant Select")){	
				String MerchantName=driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_Merchant_IDName)).getText().trim();
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MM_Merchant_SelectBtn+"["+1+"]", "Select Merchant");
				Assert.waitForPageToLoad(driver);
				Thread.sleep(20000);
				String SelectedMerchant = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_SelectMerchantSymbol)).getText().trim();
				if(MerchantName.equalsIgnoreCase(SelectedMerchant))
				{				
					Extent_Reporting.Log_Pass("Merchant selected as :"+SelectedMerchant, "Expected Merchant AS:"+MerchantName);
					Extent_Reporting.Log_report_img("Merchant selected", "Passed", driver);
					
				}else{
					Extent_Reporting.Log_Fail("Selected Merchant Name is: "+SelectedMerchant, "Updated Merchant name is:"+MerchantName, driver);
				}				
			}else{
				Extent_Reporting.Log_Fail("Merchant ID search field does not exost", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Merchant ID search field does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");
			
		}
	}
	
	
	public void Home_LHS_Dashboard(String MenuOption ) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, MenuOption, "Respective LHS Menu"))
			{	
				Assert.Clickbtn(driver, MenuOption, "Respective Menu");
				Assert.waitForPageToLoad(driver);
				Extent_Reporting.Log_report_img("Respective Menu Clicked", "Passed", driver);			
			}else{
				Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");
			
		}
	}
	
	
	public void VerifyText(String Xpath, String TextVerify) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, Xpath, "Respective Text Xpath"))
			{	
				String TextVal = driver.findElement(By.xpath(Xpath)).getText().trim();
				if(TextVal.equalsIgnoreCase(TextVerify)){
					Extent_Reporting.Log_Pass("Field name is: "+TextVal, "Expected as: "+TextVerify);
					Extent_Reporting.Log_report_img("Respective Menu Clicked", "Passed", driver);			

				}else{
					Extent_Reporting.Log_Fail("Respective text Does not exist as "+TextVal , "Failed", driver);
				}			
			}else{
				
				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");
			
		}
	}
	
	public void DetailsLinkClicked(String Xpath, String Deatils) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, Xpath, Deatils+" is"))
			{				
					Assert.Clickbtn(driver, Xpath, Deatils+" ");
					Extent_Reporting.Log_Pass("Field name is: "+Deatils, "Expected as: "+Deatils);
					Extent_Reporting.Log_report_img("Respective Link Clicked", "Passed", driver);								
			}else{				
				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective Link does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");			
		}
	}
	
	
	
	public static String flag = null;
	public void Verify_Button_Color(String Xpath) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, Xpath, "RespectiveButton"))
			{					
				WebElement switchLabel = driver.findElement(By.xpath(Xpath));
				String pseudo = ((JavascriptExecutor)driver)
				        .executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('right');",switchLabel).toString();		
				System.out.println("sudo:"+pseudo);
				if(pseudo.equalsIgnoreCase("47px")){
					System.out.println("OFF Save Card");
					Assert.Clickbtn(driver, Xpath, "OFF Clicked");
					Thread.sleep(5000);
					Extent_Reporting.Log_Pass("ON Activated", "Passed");
					Extent_Reporting.Log_report_img("ON Activate", "IMG", driver);
					flag= "ON";
				}else if(pseudo.equalsIgnoreCase("0px")){					
					System.out.println("ON Save Card");
					Assert.Clickbtn(driver, Xpath, "ON Clicked");
					Thread.sleep(5000);
					Extent_Reporting.Log_Pass("OFF Activated", "Passed");
					Extent_Reporting.Log_report_img("OFF Activate", "IMG", driver);
					flag = "OFF";
				}else{
					Extent_Reporting.Log_Fail("Save button does not exist", "Failed", driver);
				}						
			}else{
				
				Extent_Reporting.Log_Fail("Respective text Does not exist" , "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective menu does not exost", "Failed", driver);
			throw new Exception("Serch filter does not exist");
			
		}
	}
	
	public void SubmitBtnClick() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_SigleSubmitBtn, "Submit button"))
			{	
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MM_SigleSubmitBtn, "Submit button");
				Assert.waitForPageToLoad(driver);
				Thread.sleep(5000);
				Extent_Reporting.Log_report_img("Submit button", "Passed", driver);			
			}else{
				Extent_Reporting.Log_Fail("Submit button does not exost", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Submit button does not exost", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");
			
		}
	}
	
	
	
	public void ON_OFF_SaveCrad() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(flag.equalsIgnoreCase("ON"))
			{	
				boolean checkbxselected = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_PP_SaveCardchkbox)).isSelected();
				System.out.println(checkbxselected);
				if(checkbxselected==true)
				{
					Extent_Reporting.Log_Pass("Check box selected", "Passed");
					Extent_Reporting.Log_report_img("Submit button", "Passed", driver);
				}else{
					Extent_Reporting.Log_Fail("Check boc shuld be check", "Failed", driver);
				}
							
			}else if(flag.equalsIgnoreCase("OFF")){
				boolean checkbxselected = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_PP_SaveCardchkbox)).isSelected();
				if(checkbxselected==false)
				{
					Extent_Reporting.Log_Pass("Check box not selected", "Passed");
					Extent_Reporting.Log_report_img("Submit button", "Passed", driver);

				}else{
					Extent_Reporting.Log_Fail("Check boc shuld not be checked by default", "Failed", driver);
				}				
			}else{
				Extent_Reporting.Log_Fail("Save Card option does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Save Card option does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");
			
		}
	}
	
	public void Enable_Disable_SaveCardChkBox() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			if(flag.equalsIgnoreCase("ON"))
			{					
				try{
					if(Assert.isElementDisplay(driver, AirPay_Payment_MA_Panel_PageObject.MM_PP_SaveCardchkbox))
					{
						Extent_Reporting.Log_Pass("Save card check box is exist as expected ", "Passed");
						Extent_Reporting.Log_report_img("Save card check box", "Passed", driver);	
					}
				}catch(Exception t){
					
					Extent_Reporting.Log_Fail("Check box should be present for save Card but its not there", "Failed", driver);				
				}		
			}else if(flag.equalsIgnoreCase("OFF")){
				try{
					if(Assert.isElementDisplay(driver, AirPay_Payment_MA_Panel_PageObject.MM_PP_SaveCardchkbox))
					{
						Extent_Reporting.Log_Fail("Check box should Not be present for Save Card", "Failed", driver);						
					}
				}catch(Exception t){
					Extent_Reporting.Log_Pass("Save card check box is exist as expected ", "Passed");
					Extent_Reporting.Log_report_img("Save card check box", "Passed", driver);
					
				}					
			}else{
				Extent_Reporting.Log_Fail("Save Card option does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Save Card option does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");
			
		}
	}
	
	
	public static int Counter;
	public void Domain_URLFindOut() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails, "URL Detail Table"))
			{					
				List<WebElement> URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails));
				System.out.println(""+URLRows.size());
				for(int i=1;i<=URLRows.size();i=i+2)
				{
					String DomainName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+i+"]/td[2]")).getText().trim();
					String SuccessURL = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+i+"]/td[3]")).getText().trim();
					String IpnUrl = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+i+"]/td[4]")).getText().trim();
					if(DomainName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "Doamin_name").trim())
							&& SuccessURL.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "SuccessURL").trim())
							&& IpnUrl.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "IpnURL").trim())){
						
						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position", "Passed");
						Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+i+"]/td[7]/div[@class='btnlink']/a", "Details Button");
						Counter = i;
						break;						
					}
					if(i==URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
					}
				}
			}else{
				Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");
			
		}
	}
	
	
	
	
	
	public static int Maxval=1;
	public static int Minval=1;
	public void BANK_DetailsFindOut() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails, "URL Detail Table"))
			{					
				List<WebElement> Bank_URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
						+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL));
				System.out.println("Bank Rows: "+Bank_URLRows.size());
				for(int i=1;i<=Bank_URLRows.size();i++)
				{
					String BankName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[1]")).getText().trim();
					System.out.println("bankName:"+BankName);
					String MidType = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[2]")).getText().trim();
					System.out.println("bankName:"+MidType);
					String BankStatus = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[3]")).getText().trim();
					System.out.println("bankName:"+BankStatus);
					if(BankName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "BankName_MAPanel").trim())
							&& MidType.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "TransactionTypr").trim())
							&& BankStatus.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "Status").trim()))
					{						
						List<WebElement> priorityElement= driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
					                                  +AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"/td/input"));
						
						for(int j =1;j<priorityElement.size();j++)
						{						
							String priortival = driver.findElement(By.xpath("((//*[@class='table border_table']/tbody/tr)["+Counter+"]/td[7]/div[@class='btnlink']//following::tbody[1]/tr/td/input)["+j+"]")).getAttribute("value");
							int priIntval = Integer.parseInt(priortival);
							
							if(Minval>priIntval)
							{
								Minval = priIntval;	
								System.out.println("Minval: "+Minval);
								
							}
							if(Maxval<priIntval)
							{	
									Maxval = priIntval;	
									System.out.println("Maxval:"+Maxval);
							}
							if(j==priorityElement.size())
							{
								Extent_Reporting.Log_Fail("There is an issue with priority", "failed", driver);
							}																			
						}
						System.out.println("Maxium val: "+Maxval);
						System.out.println("minium val :"+Minval);		
						Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
                                +AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"/td/input[@value='"+Maxval+"']", Integer.toString(Maxval+2), "Max val");					
						Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
                                +AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"/td/input[@value='"+Minval+"']", Integer.toString(Maxval+1), "Minval");						
						Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td/input",Integer.toString(Minval), "Min val");						
						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position", "Passed state");
						Thread.sleep(10000);
						break;	
								
					}
					if(i==Bank_URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
					}									
					}
			}							
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}

	public void MerchantTransactionID() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_MerchantTrxID, "Merchant Transaction ID"))
			{
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.MM_MerchantTrxID, AirPay_PaymentPage_BusinessLogic.GetOrderID.trim(), "Merchant Transaction ID");
				Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_MerchantTrxID, "Merchant Transaction ID");
				Extent_Reporting.Log_Pass("Merchant ID Entered as:"+AirPay_PaymentPage_BusinessLogic.GetOrderID, "Passed");
				Extent_Reporting.Log_report_img("Merchant transaction id", "Passed", driver);
				
			}else{
				Extent_Reporting.Log_Fail("Transaction Record does not exist", "Failed", driver);
				
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Transaction Record does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}
	
	public void TransactionRecords() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_TransactionRecords, "Transaction records")){
				List<WebElement> transactionHeaders = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_TransactionRecords));					
					Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MM_TransactionRecords+"["+transactionHeaders.size()+"]/td[@class='status']","MMTransaction_Clicked");
					if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MMTransactiondetailsPage, "Transaction Details page"))
					{
					  	
					}else{
						Extent_Reporting.Log_Fail("Transaction Details Page does not exist", "failed", driver);
					}
			}else{
				Extent_Reporting.Log_Fail("Transaction Records does not exist", "Failed", driver);
				
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Transaction Record does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}
	
	
	public void Bank_DetailsURL(String active, String inactive ) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_BankDetails, "BANK URL Detail Table"))
			{					
				List<WebElement> URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails));
				System.out.println(""+URLRows.size());
				for(int i=1;i<=URLRows.size();i++)
				{
					String BankName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[1]")).getText().trim();
					String ChannelName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[2]")).getText().trim();
					String MidType = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[3]")).getText().trim();
					String Status = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[4]")).getText().trim();
					
					if(BankName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "BankName_MAPanel").trim())
							&& ChannelName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "ChannelName").trim())
							&& MidType.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "TransactionTypr").trim())
							&& (Status.equalsIgnoreCase(active)||Status.equalsIgnoreCase(inactive)))
					{
						
						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position", "Passed");
						Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[5]/span/a", "Details Button");
						Counter = i;
						break;						
					}
					if(i==URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective Bank name Status not matched", "Failed", driver);
					}
				}
			}else{
				Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");
			
		}
	}
	
	public void Bank_DetailsStatusActiveInactive(String active,String inactive) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_BankDetails, "BANK URL Detail Table"))
			{					
				List<WebElement> URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails));
				System.out.println(""+URLRows.size());
				for(int i=1;i<=URLRows.size();i++)
				{
					String BankName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[1]")).getText().trim();
					String ChannelName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[2]")).getText().trim();
					String MidType = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[3]")).getText().trim();
					String Status = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[4]")).getText().trim();
					
					if(BankName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "BankName_MAPanel").trim())
							&& ChannelName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "ChannelName").trim())
							&& MidType.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "TransactionTypr").trim())
							&& Status.equalsIgnoreCase(active)||Status.equalsIgnoreCase(inactive))
					{
						
						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position", "Passed");
						Assert.clickButton(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindBANKDetails+"["+i+"]/td[5]/span/a", "Details Button");
						Counter = i;
						break;						
					}
					if(i==URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective Bank name Status not matched", "Failed", driver);
					}
				}
			}else{
				Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			}				
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");
			
		}
	}
	
	
	
	public void BankDetails_Page() throws Exception{
		try{
			Thread.sleep(20000);
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MMBankdetailsPage, "Bank details Page")){			
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MMbankStatus, Excel_Handling.Get_Data(TC_ID, "Status").trim(), "Status Drop Down");			
				Extent_Reporting.Log_Pass("Bank Details Entered", "Passed");
				Extent_Reporting.Log_report_img("Bank Details", "Image", driver);
			}else{
				Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
				
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}
	public static String MINADDONE =null;
	public static String MINMINUSONE =null;
	public static String MAXADDONE =null;
	public static String MAXMINUSONE =null;


	public void BankDetails_MinumAndMaxAmt_Val() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.BankURLMiniumVal, "Bank details Page")){						
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.BankURLMiniumVal, Excel_Handling.Get_Data(TC_ID, "MinumAmtVal").trim(), "Minium Amout");
				Assert.inputText(driver, AirPay_Payment_MA_Panel_PageObject.BankURLMaxiumVal, Excel_Handling.Get_Data(TC_ID, "MaxAmtVal").trim(), "Maxium Amout");				
				Extent_Reporting.Log_Pass("Bank Details Entered", "Passed");
				Extent_Reporting.Log_report_img("Bank Details", "Image", driver);
				double MinVal =Double.parseDouble(Excel_Handling.Get_Data(TC_ID, "MinumAmtVal").trim());
				double MaxVal=Double.parseDouble(Excel_Handling.Get_Data(TC_ID, "MaxAmtVal").trim());
				double MinAddOne = MinVal+0.00;
				double MinMinusOne = MinVal-0.01;
				double MaxAddOne = MaxVal+0.01;
				double MaxMinusOne = MaxVal-0.00;
				MINADDONE = String.valueOf(MinAddOne);
				MINMINUSONE= String.valueOf(MinMinusOne);
				MAXADDONE = String.valueOf(MaxAddOne);
				MAXMINUSONE = String.valueOf(MaxMinusOne);			
			}else{
				Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
				
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}
	
	
	
	
	public void URLDetails_Page() throws Exception{
		try{
			Thread.sleep(20000);
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MMURLdetailsPage, "URL details Page")){			
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, AirPay_Payment_MA_Panel_PageObject.MMbankStatus, Excel_Handling.Get_Data(TC_ID, "Status").trim(), "Status Drop Down");			
				Extent_Reporting.Log_Pass("Bank Details Entered", "Passed");
				Extent_Reporting.Log_report_img("Bank Details", "Image", driver);
			}else{
				Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
				
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}
	
	
	public void BankDetails_PageSaveBtn() throws Exception{
		try{
			//Thread.sleep(20000);
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MMBankSaveBtn, "Bank details Save Button")){							
				Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MMBankSaveBtn, "Save Button");
				Extent_Reporting.Log_Pass("Save Button", "Passed");
				Extent_Reporting.Log_report_img("Save Button Clicked", "Passed", driver);
				Thread.sleep(2000);
				//Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.BankEditSuccMsg, "Edit successfully Done");			
				Thread.sleep(20000);
				Extent_Reporting.Log_report_img("Save Button Clicked", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
				
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}
	
	public void ConfigurationModifiedSuccess() throws Exception
	{
		try{
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.URLEditSuccMsg, "Edit successfully Done"))
			{	
				Extent_Reporting.Log_report_img("Modified Successfully", "Passed", driver);
				Extent_Reporting.Log_Pass("Respective message is dispalyed as expected", "Passed");
				Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.URLEditSuccMsg, "Edit successfully Done");			
			}else{
				Extent_Reporting.Log_Fail("Edit successfully not exist", "Failed", driver);
				
			}			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Bank details Page does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");			
		}
	}

	
	
	public static String bankName = null;
	public void Select_Bank_DropDown_Selection() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, SelectBank_DropDown, "Select Bank Drop Down" ))
			{         	
				WebElement selectDropBox = driver.findElement(By.xpath(Netbank_DropDown));
				Select select =new Select(selectDropBox);
				List<WebElement> optionValue = select.getOptions();
				System.out.println(optionValue.size());
				for(int i =1;i<optionValue.size();i++)
				{								
					WebElement selectDropBox1 = driver.findElement(By.xpath(Netbank_DropDown));
					Select select1 =new Select(selectDropBox1);
					Assert.selectDropBoxValue(driver, Netbank_DropDown, i, " Bank Name");//(driver, Netbank_DropDown, value[i], value[i+1]+" Bank ");			
					Thread.sleep(2000);
					bankName =  select1.getFirstSelectedOption().getAttribute("value").trim();
					Thread.sleep(2000);
					System.out.println("FetchBank Name:"+bankName);
					System.out.println(Excel_Handling.Get_Data(TC_ID, "BankName").trim());
					if(bankName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "BankName").trim()))
					{
						Extent_Reporting.Log_Pass("Bank Name Is exist as: "+bankName, "Passed");
						Extent_Reporting.Log_report_img("Bank Is exist as expected", "Passed", driver);
						break;
					}
					if(i==optionValue.size()-1)
					{
						Extent_Reporting.Log_Fail("Bank should be exist but not exist", "Passed", driver);
						break;
						
					}
				}   		
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	public void Select_Bank_DropDown_SelectionNotExist() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, SelectBank_DropDown, "Select Bank Drop Down" ))
			{         	
				WebElement selectDropBox = driver.findElement(By.xpath(Netbank_DropDown));
				Select select =new Select(selectDropBox);
				List<WebElement> optionValue = select.getOptions();
				for(int i =1;i<=optionValue.size();i++)
				{				
					WebElement selectDropBox1 = driver.findElement(By.xpath(Netbank_DropDown));
					Select select1 =new Select(selectDropBox1);
					Assert.selectDropBoxValue(driver, Netbank_DropDown, i, " Bank Name");//(driver, Netbank_DropDown, value[i], value[i+1]+" Bank ");			
					//Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, Netbank_DropDown, Excel_Handling.Get_Data(TC_ID, "BankName").trim(), " Bank Name");//(driver, Netbank_DropDown, value[i], value[i+1]+" Bank ");			
					Thread.sleep(2000);
					bankName =  select1.getFirstSelectedOption().getAttribute("value").trim();
					Thread.sleep(2000);
					System.out.println("Bank Name: "+bankName);
					if(bankName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "BankName").trim())){
						Extent_Reporting.Log_Fail("Bank Is exist but should not be exist", "Passed", driver);
						break;
					}
					if(i==optionValue.size()-1){
						Extent_Reporting.Log_Pass("Respective Bank is disabled as expeccted "+bankName, "Passed");
						Extent_Reporting.Log_report_img("Bank Disabled", "Passed", driver);
						break;
					}
				}   		
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	public void URL_Details(String active,String inactive) throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails, "URL Detail Table"))
			{					
				List<WebElement> Bank_URLRows = driver.findElements(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"
						+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL));
				System.out.println("Bank Rows: "+Bank_URLRows.size());
				for(int i=1;i<=Bank_URLRows.size();i++)
				{
					String BankName = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[1]")).getText().trim();
					System.out.println("bankName:"+BankName);
					String MidType = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[2]")).getText().trim();
					System.out.println("bankName:"+MidType);
					String BankStatus = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[3]")).getText().trim();
					System.out.println("bankName:"+BankStatus);
					if(BankName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "BankName_MAPanel").trim())
							&& MidType.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "TransactionTypr").trim())
							&& (BankStatus.equalsIgnoreCase(active)|| BankStatus.equalsIgnoreCase(inactive)))
					{
						Extent_Reporting.Log_Pass("Exact URL Row Find out at :"+i+"th Position is", "Passed");
						Assert.Clickbtn(driver, AirPay_Payment_MA_Panel_PageObject.MM_FindURLDetails+"["+Counter+"]/td[7]/div[@class='btnlink']"+AirPay_Payment_MA_Panel_PageObject.MM_Bank_URL+"["+i+"]/td[5]/span/a", "Edit button");						
						Counter = i;
						break;	
					}
					if(i==Bank_URLRows.size())
					{
						Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
					}									
					}
			}							
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective URL Does not exist", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");
			
		}
	}
	
	public void SummaryAmtVerify(String PassedVal) throws Exception{
		try{
			
			if(Assert.isElementDisplayed(driver, AirPay_Payment_MA_Panel_PageObject.TotAmt, "Passed Amount"))
			{
				String AmtVal = driver.findElement(By.xpath(AirPay_Payment_MA_Panel_PageObject.TotAmt)).getText().trim();
				if(AmtVal.equalsIgnoreCase(PassedVal)){
					Extent_Reporting.Log_report_img("Amount is exist", "Passed", driver);
					Extent_Reporting.Log_Pass("Amount is exist as expected"+AmtVal, "Max and Min Amount");
				}else{
					Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
				}
			}		
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Amount didn't match", "Failed", driver);
			throw new Exception("Submit buttondoes not exist");
			
		}
	}
	
	
	
}