package com.Airpay.PageObject;

public class AirPay_Payment_MA_Panel_PageObject {
	
	//****************************************MA Panel Locators************************************
	
	public static final String MA_UserID = "//*[contains(@class,'sign-user')]";
	public static final String MA_PWD ="//*[contains(@class,'sign-password')]";
	public static final String MA_SubmitBtn ="//*[@class='login-submit']";
	
	
	public static final String MA_HomeMenu ="//*[@class='home-menu']";
	public static final String MerchantMember_NextBtn ="//*[@class='mm-next' and @href='#mm-1']";
	public static final String MM_Airpay_SettingNextBtn = "//*[@class='mm-next' and @href='#mm-3']";
	public static final String MM_Airpay_Setting ="//*[@href='airpaysettings.php']";
	
	
	
	
	public static final String MM_Merchant_Search ="//*[@class='clear-input required']";
	public static final String MM_Merchant_Select ="(//table[@class='table']/thead/tr/th)";
	public static final String MM_Merchant_IDName ="(//table[@class='table']/tbody/tr/td)[2]";
	public static final String MM_Merchant_SelectBtn ="(//span[contains(@class,'btnlink radius')])";
	public static final String MM_SaveCardFieldName ="(//label[@for='defaultsingleclick_flag'])[1]";
	public static final String MM_CheckbxColor ="(//label[@for='defaultsingleclick_flag' and @class='onoffswitch-label']/span//following::span)[1]";
	public static final String MM_SelectMerchantSymbol ="//*[@class='pull-right merchant-change']/a";
	
	public static final String MM_SigleSubmitBtn ="//*[@id='singlebutton']";
	public static final String MM_MAUpdatedMesg ="(//*[contains(@class,'img-block')]//following::p)[1]";
	public static final String MM_PP_SaveCardchkbox ="//*[@class='save-Card-Check']";
	
	public static final String MM_ONOFFCheckBoxSaveCard ="(//label[@for='singleclick_flag' and @class='onoffswitch-label']/span//following::span)[1]";
	
	
	
//******************************************MA Panle Payment Mode URL Details locaters *********************************
	
	public static final String MM_PaymentMode_NextBtn ="//*[@class='mm-next' and @href='#mm-2']";
	public static final String MM_PayMent_Mode_UrlLink ="//*[@href='payment_mode_urls.php']";
	public static final String MM_URLDetails ="//a[@href='#collapseTwo']";
	
	public static final String MM_FindURLDetails = "(//*[@class='table border_table']/tbody/tr)";
	public static final String MM_URLRowVerify ="(//*[@class='table border_table']/tbody/tr)[1]/td";
	public static final String MM_Bank_URL ="//following::tbody[1]/tr";
	
	public static final String MM_Transaction_History ="//a[@href='searchinner.php?f=1']";
	public static final String MM_MerchantTrxID = "//input[@class='clear-input merctransid']";
	public static final String MM_TransactionRecords = "(//table[@class='table']/tbody/tr)";
	
	//*[text()='Transaction Details']
	public static final String MMTransactiondetailsPage =  "//*[text()='Transaction Details']";
	public static final String MMTransacionMerchantTransactionID ="//*[text()='Merchant Transaction Id']/following-sibling::td";
	public static final String MMTransacionBank ="//*[text()='Bank']/following-sibling::td";
	public static final String MMTransacionBankType ="//*[text()='Type of Transaction']/following-sibling::td";
	public static final String MMTransacionIssuingBankName ="//*[text()='Issuing Bank Name']/following-sibling::td";
	public static final String MMTransacionIssuingBankCardType ="//*[text()='Card Type']/following-sibling::td";
	public static final String MMTransacionCardCategory ="//*[text()='Card Category']/following-sibling::td";

	public static final String MMTransacionAirapyMerchantID ="//*[text()='Airpay Merchant Id']/following-sibling::td";

	
	
	
	
	
	
	
	
	

}
