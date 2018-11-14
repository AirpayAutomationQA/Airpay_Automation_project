package com.Airpay.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelDemo
{ 
    public static void main(String[] args) throws IOException  
    { 
           
    		FileInputStream file = new FileInputStream(new File("C:\\Users\\User\\Downloads\\testdata.xlsx"));  
    		List sheetData = new ArrayList();

    		XSSFWorkbook workbook = new XSSFWorkbook(file); 

    		XSSFSheet sheet = workbook.getSheetAt(0);
    		ArrayList<Form> vipList = new ArrayList<Form>();
    		Iterator<Row> rowIterator = sheet.iterator();   
    		while (rowIterator.hasNext()) 
    		{            
    				Row row = rowIterator.next();

    				Iterator<Cell> cellIterator = row.cellIterator();   
    				List data = new ArrayList();

    				while (cellIterator.hasNext()) 
    				{ 

    					
    				}           
    				}

    		}  
    	}

