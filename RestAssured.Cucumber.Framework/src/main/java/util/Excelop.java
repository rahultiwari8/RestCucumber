package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelop {
	
	String pathofexcel;
	static XSSFWorkbook wb;
	static FileOutputStream fos;
	
	
    public Excelop(String pathofexcel) throws IOException {
		this.pathofexcel=pathofexcel;
		
		File file = new File(pathofexcel);
		FileInputStream fis =new FileInputStream(file);
		 fos =new FileOutputStream(new File("/Users/rahultiwari/git/RestCucumber/RestAssured.Cucumber.Framework/target/Workbook1.xlsx"));;
		 wb =new XSSFWorkbook(fis);
				
	}
    
    public void closeWorkbook() throws IOException {
    	wb.close();
    }
    
    public Object readData(String sheetName,int row,int col)
    {
    	
    	Object  value=wb.getSheet(sheetName).getRow(row).getCell(col);
		return value;
    	
    }
    
    public void writeData(String sheetName , int row,int col ,String data) throws IOException
    {
    	
    	XSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(col);
    	try
    	{
    	System.out.println(cell.hashCode());
    	}
    	catch(Exception e )
    	{
    		System.out.println("got error while writing data , Finding alternate way");
    		
    		XSSFCell cell1=wb.getSheet(sheetName).getRow(row).createCell(col,CellType.STRING);
    		
    		//cell1.setCellValue("hi");
    		
    	}
    	finally {
    		wb.getSheet(sheetName).getRow(row).getCell(col).setCellValue(data);
       	 wb.write(fos);
   		  fos.close();
   		  wb.close();
			
		}
    	
    }
    
    public static void main(String[] args) throws IOException {
    	
    	Excelop a =new Excelop("/Users/rahultiwari/eclipse-workspace/RestAssured.Cucumber.Framework/target/Workbook.xlsx");    			
    	XSSFCell cell=wb.getSheet("first").getRow(1).getCell(9);
    	try
    	{
    	System.out.println(cell.hashCode());
    	}
    	catch(Exception e )
    	{
    		System.out.println("got error while writing data , Finding alternate way");
    		
    		XSSFCell cell1=wb.getSheet("first").getRow(1).createCell(9,CellType.STRING);
    		
    		//cell1.setCellValue("hi");
    		  
    		  a.writeData("first", 1, 9, "hello");
    		  wb.write(fos);
    		  fos.close();
    		  wb.close();
    		
    	}
	}
}
