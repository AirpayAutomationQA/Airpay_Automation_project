package com.Airpay.PageObject;

public class Airpay_PaymentPage_PageObject {
	
	//*************************Local Host page locators *******************************************************
		public static final  String  BuyerMailId = "//input[@id='buyerEmail']";
		public static final  String  BuyerPhoneNumber = "//input[@id='buyerPhone']";
		public static final  String  BuyerFirstName = "//input[@id='buyerFirstName']";
		public static final  String  BuyerLastName = "//input[@id='buyerLastName']";
		public static final  String  BuyerPinCode = "//input[@id='buyerPinCode']";	
		public static final  String  Order_Id = "//input[@id='orderid']";
		public static final  String  Amount = "//input[@id='amount']";
		public static final  String  payHerebtn= "//button[@type='submit']";
		
	//*************************** Net Banking Page Locators ****************************
		
		public static final String SelectBank_DropDown = "//input[@name='bankName']";
		public static final String MakePaymentBtn ="(//input[@value='Make Payment' and @type='submit'])";
		public static final String Netbank_DropDown ="//select[@name='nbbank_select']";
		public static final String MakePaymentBtnForNetBankning = "(//input[@name='bankName']//following::div/input[@value='Make Payment'])[1]";
		public static final String NetbankingErrDropDropRedLine = "//*[@class='formDom form-group select-group errorvalue']";	
		public static final String Popularbanks ="(//*[@class='blocklist popularBanks'])/li";	
		public static final String PopulaUPIWallets ="(//*[@class='blocklist upilist'])/li";	

		
		
		
	//***************************Local Host Email Id error verify ************************************
		
		public static final String ErrverifyValidation = "//span[@class='alert alert-error']";
				
		
	//*************************** Credit Card payemtn page Locators ************************************
		public static final String CreditCardNoInput ="(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control cardNumber')])[1]";
		public static final String CreditCardExpDate = "(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control exp_date')])[1]";
		public static final String CreditCardCVVCode ="(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control cvv')])[1]";
		public static final String CreditCardMakePaymtBtn = "//input[@class='btn submit-credit-dtls']";
		public static final String CreditCardHolderName = "(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control cardname')])[1]";
		public static final String CreditCardErrField = "//div[@class='formDom form-group errorvalue']//label";
		public static final String CardInvalidErrMsgVerify = "(//*[@class='generic-error'])[1]";
		public static final String GenericSuccessMessage = "(//*[@class='generic-success'])[1]";
		public static final String PopupErrmShbBtn = "(//*[@class='msgclosebtn'])[1]";

		
		
		
		
		public static final String CancelCreditPayment = "(//*[text()='CANCEL' or text()='Cancel'])";
					
	//******************************** Payment page ****************************************************
		
		public static final String LogoPaymentPage = "(//div[@class='logo'])[1]";
		public static final String ImgLogo = "(//img)[1]";
		public static final String airPayFavIcon = "//div[@class='paylogo']";
		public static final String AirpayChannals = "//*[@class='menu-link']";
		public static final String SummerySecOrdID = "//*[@class='odrID']/strong";
		public static final String MerchantName = "//*[@class='merName']";
		public static final String AmtValueBlock = "//div[contains(@class,'default-amount-block')]";
		public static final String footerVerifyLink = "//div[@class='tnc']";
		public static final String CancelpaymentPage = "//div[@class='tnc']//a";
		
    //********************************** debit card payment page locators***************************************
		
		public static final String DebitCardNoInput ="(//div[@class='blockMain blockCards debit-tab']//following::input[contains(@class,'form-control cardNumber')])[1]";
		public static final String DebitCardExpDate = "(//div[@class='blockMain blockCards debit-tab']//following::input[contains(@class,'form-control exp_date')])[1]";
		public static final String DebitCardCVVCode ="(//div[@class='blockMain blockCards debit-tab']//following::input[contains(@class,'form-control cvv')])[1]";
		public static final String DebitCardMakePaymtBtn = "//*[@class='btn submit-debit-dtls']";
		public static final String DebitCardHolderName = "(//div[@class='blockMain blockCards debit-tab']//following::input[contains(@class,'form-control cardname')])[1]";
		public static final String DebitCardErrField = "//div[@class='formDom form-group errorvalue']//label";

	//********************************** EMI Locators *********************************************
		

	//************************************ MA Panel *************************************************
		
		public static final String ManangerDashBoard ="//*[@class='glyphicon glyphicon-th-list main-menu-icon']";
		public static final String MerchantMenutab = "//*[@class='glyphicon glyphicon-globe pri-menu']//preceding::a[1]";
		
		
	//********************************* Cash Test case ***********************************************
		public static final String CashPinCode = "//*[@class='form-control cash_pincode']";
		public static final String CashPincodeErrLine ="(//*[contains(@class,'formDom form-group errorvalue')])[1]";
		public static final String UPICommoneErrLine ="(//*[contains(@class,'formDom form-group errorvalue')])[2]";

		public static final String CashMakePayment = "//*[@class='btn cash-submit' and @data-sub-form-id='cash-form']";
        //public static final String UPIMakePayment ="(//*[@class='btn' and @data-sub-form-id='upi-form'])[1]";
        public static final String UPIMakePayment ="(//*[@class='btn' and @data-sub-form-id='upi-form'])[2]";

        //public static final String UPICommonPayment ="(//*[@class='btn' and @data-sub-form-id='upi-form'])[2]";
        public static final String UPICommonPayment ="(//*[@class='btn' and @data-sub-form-id='upi-form'])[3]";

        public static final String payTmChaanelInput ="//input[@class='form-control paytm-validation']";
        
        
      
        
		public static final String CashSuccessTransaction = "//*[text()='Success Transaction']";	
		
	//********************************* RTGS and NEFT **************************************************
		public static final String UTRCode = "//*[@class='form-control utr-validation']";
		public static final String UTRCashMakePayment = "//*[@class='btn rtgs-form']";
		
	//********************************** Moto transaction ****************************************
		//public static final String 
		
	//*********************************** Wallet Transaction ***************************************
		public static final String WalletErrDropDown ="//div[@class='formDom form-group select-group errorvalue' and @data-err-cod='W5']";
		public static final String WalletDropDown = "//*[@class='textbox icondrop validate walletName']";
		public static final String WalletMakePaymtBtn ="//*[@class='paygateway-submit btn wallet-submit']";
		public static final String PopularWalletLogo ="(//*[@class='blocklist popularWallets'])/li";
		public static final String WalletDropDownSection ="//*[@class='form-control wallet-options']";
		
		public static final String WalletMobileNumField ="//*[@class='form-control mobnumns']";
		public static final String WalletEmailIDField ="//*[@class='form-control emailV']";
		
   //************************************** UPI Channel Locators **********************************
		public static final String UPIAddressField ="//input[contains(@class,'form-control upi-validation')]";
		public static final String UPIAddressCommonField ="//input[contains(@class,'form-control other-upi-validation')]";

		
		public static final String SessionTimer ="//*[@class='timer']";
		public static final String UPIAddIcon ="//*[@class='input-group-addon icon iupi']";
		public static final String UPICrossCancelBtn ="//a[@class='cls-popup upibtnclose']";
		
	//*****************************************  EMI Channel Locators *************************************
		public static final String EMIActive_radio_verfiy = "(//*[@class='emitable active']/table/tbody/tr/td/input)";
		public static final String EMIMonthname = "(//*[@class='emitable active']/table/tbody/tr/td[contains(text(),'Months')])";
		public static final String EmiBankNameSelectDropDown = "//select[@class='form-control emiSelect']";
		public static final String EMICardHolderName = "(//div[@class='blockMain blockCards emi-tab']//following::input[contains(@class,'form-control cardname')])[1]";
		public static final String EMICardNumber = "(//div[@class='blockMain blockCards emi-tab']//following::input[contains(@class,'form-control cardNumber')])[1]";
		public static final String EMICardExpDate = "(//div[@class='blockMain blockCards emi-tab']//following::input[contains(@class,'form-control exp_date')])[1]";
		public static final String EMICardCVVCode ="(//div[@class='blockMain blockCards emi-tab']//following::input[contains(@class,'form-control cvv')])[1]";
		public static final String EMICardMakePaymtBtn = "//*[@class='btn emi-submit']";
		public static final String EMIBankDropDownRedLineErr ="//*[contains(@class,'formDom form-group select-group errorvalue')]";
		public static final String EMIRedLineError = "(//*[contains(@class,'errorvalue') and contains(@data-err-cod,'EM')])";
		
	 //************************************************* Bharat QR Locators ******************************************
		public static final String BharatOrMakepaymtBtn ="//*[@class='btn bharat-qr-submit']";
		public static final String BharatQRbarCodeImg ="//img[@class='bt-qr-img']";
		public static final String BharatQRBtnclose ="//*[@class='cls-popup bhaqrbtnclose']";
     //*******************************************Amex eZeclick ****************************************************
		
		public static final String AmexeZclickMakePaymentBtn ="//*[@class='btn vm-submit-btn disable-click']";
		public static final String virtualAccounttBtn = "//*[@class='btn virtual-acc-details rtgsfrm']";
		public static final String virtualBankAccountBtn = "//*[@class='btn virtual-bank-details rtgsfrm']";

		
		public static final String AccountDetails ="//*[contains(text(),'The Account Details for this transaction is as below :')]";
		public static final String AmazonMakePaymentBtn ="//*[@class='btn' and @data-sub-form-id='amazonpay-form']";
		
	//************************************************************Tez Channel ***************************
		public static final String TezIDField ="//*[@class='form-control tez-validation']";
		public static final String TezIDMakepayment ="//*[@class='btn' and @data-sub-form-id='upi-form']";
		public static final String TezDomainName ="//*[@class='form-control tez-upi-bank']";
		public static final String TezIdFieldErrRedline ="//*[@class='formDom form-group errorvalue']";		
		public static final String TezDomainRedLineErr= "//*[@class='formDom form-group select-group errorvalue']";
		public static final String PaytmlogoIcon ="//div[@class='paytm_logo icon']";

		
		
	//**************************************MA Panel ********************************************************
		
		
		
		// = session timeout
		
		
		//Password does not match.
		//Your account has been blocked.
		
		
		//  -- client rejected
		//= session timeout
		
		
		
		

		
		
		
		
		
		
		
		
		
	 	    
}
