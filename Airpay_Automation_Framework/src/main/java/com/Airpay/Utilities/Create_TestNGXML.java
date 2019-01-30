package com.Airpay.Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.Airpay.Reporting.Report_Setup;
import com.Airpay.TestData.Excel_Handling;


public class Create_TestNGXML {
	
	
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";
	
	public List<XmlInclude> constructIncludes (String... methodNames) {
        List<XmlInclude> includes = new ArrayList<XmlInclude> ();
        for (String eachMethod : methodNames) {
            includes.add (new XmlInclude (eachMethod));
        }
        return includes;
    }

	
	@SuppressWarnings("deprecation")
	@Test     
    public void createXMLfile () throws Exception {
		
		Runtime.getRuntime().exec(Constants.deleteAllTempFileBatchlocation);		
		killProcessRunning("IEDriverServer.exe");
		killProcessRunning("iexplore.exe *32");
		killProcessRunning("iexplore.exe");
		killProcessRunning("ALM-Client.exe");
		killProcessRunning("chromedriver.exe");	
		killProcessRunning("chrome.exe");
		killProcessRunning("scalc.exe");
		
    	//calling out the excel datasheet instance to get all the "Y" data for setting up the testngxml
		// Excel sheet 1 st one.........................................	
    	
		
		Excel_Handling excel = new Excel_Handling();
		
		
		excel.ExcelReader(Constants.datasheetPath+"Datasheet.xlsx", "NoramlKit", Constants.datasheetPath+"Datasheet_Result.xlsx", "NoramlKit");
		try {
			excel.getExcelDataAll("NoramlKit", "Execute", "Y", "TC_ID");		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*// Excel sheet 2nd one.........................................
		excel.ExcelReader(Constants.datasheetPath+"Datasheet.xlsx", "InlineKit", Constants.datasheetPath+"Datasheet_Result.xlsx", "InlineKit");
		try {
			excel.getExcelDataAll("InlineKit", "Execute", "Y", "TC_ID");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Excel sheet 3rd one.........................................
		excel.ExcelReader(Constants.datasheetPath+"Datasheet.xlsx", "iFrameKit", Constants.datasheetPath+"Datasheet_Result.xlsx", "iFrameKit");
		try {
		excel.getExcelDataAll("iFrameKit", "Execute", "Y", "TC_ID");
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}*/
        @SuppressWarnings({ "rawtypes", "static-access" })
		Map<String, HashMap> map = excel.TestData;              
        for (String key: map.keySet()){
        	//creation of the testng xml based on parameters/data
        	TestNG testNG = new TestNG();
        	XmlSuite suite = new XmlSuite ();
            suite.setName (new Common_Functions_old().GetXMLTagValue(Constants.configPath+"Config.xml", "Regression_Suite_Name"));
	        if(Integer.parseInt(Excel_Handling.Get_Data(key, "Browser_Instance"))>1){
	        	suite.setParallel("tests");
        		suite.setThreadCount(Integer.parseInt(Excel_Handling.Get_Data(key, "Browser_Instance")));
	        	for(int i=1;i<=Integer.parseInt(Excel_Handling.Get_Data(key, "Browser_Instance"));i++){
	        		XmlTest test = new XmlTest (suite);        		
	        		test.setName (key+"_Instance_"+i);
	    	        test.setPreserveOrder("false");
	    	        test.addParameter("browserType", Excel_Handling.Get_Data(key, "Browser_Type"));
	    	        test.addParameter("tcID", key);
	    	        test.addParameter("appURL", new Common_Functions_old().GetXMLTagValue(Constants.configPath+"Config.xml", "AppUrl")); 	        
	        		test.addParameter("temp", "temp"+i);	        		
	        		XmlClass testClass = new XmlClass ();
	        		testClass.setName ("com.Airpay.Tests."+Excel_Handling.Get_Data(key, "Class_Name"));	        	
	    	        test.setXmlClasses (Arrays.asList (new XmlClass[] { testClass}));
	        	}        		
        	}else{
        		XmlTest test = new XmlTest (suite);
            	test.setName (key);            	
    	        test.setPreserveOrder ("true");
    	        test.addParameter("browserType", Excel_Handling.Get_Data(key, "Browser_Type"));
    	        test.addParameter("tcID", key);
    	        test.addParameter("appURL", new Common_Functions_old().GetXMLTagValue(Constants.configPath+"Config.xml", "AppUrl")); 	        
        		XmlClass testClass = new XmlClass ();
        		testClass.setName ("com.Airpay.Tests."+Excel_Handling.Get_Data(key, "Class_Name"));
    	        test.setXmlClasses (Arrays.asList (new XmlClass[] { testClass}));
        		
        	}
	        List<String> suites = new ArrayList<String>();
	        final File f1 = new File(Create_TestNGXML.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	        File f = new File(f1+"\\testNG.xml");
	        f.createNewFile();
	        FileWriter fw = new FileWriter(f.getAbsoluteFile());
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(suite.toXml());
	        bw.close();
	        
	        suites.add(f.getPath());
	        
	        testNG.setTestSuites(suites);
	        com.Airpay.Reporting.Report_Setup.InitializeReport(key);
	        testNG.run();
	        Report_Setup.extent.endTest(Report_Setup.test);
	        f.delete();
        }           
        Report_Setup.extent.flush();      
    }
	
	public boolean killProcessRunning(String serviceName) throws Exception {
		boolean flag = false;
		try
		{
			
			Process p = Runtime.getRuntime().exec(TASKLIST);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains(serviceName)) {
					Runtime.getRuntime().exec(KILL+serviceName);
					flag= true;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}



}
