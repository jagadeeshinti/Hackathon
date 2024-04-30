package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	public static FileInputStream file;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String[] readingExcel(String sheetName) throws IOException {
		String[] data=new String[10];
		String filepath=System.getProperty("user.dir")+"\\testdata\\Exceldata.xlsx";
		file=new FileInputStream(filepath);
		workbook=new XSSFWorkbook(file);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(0);
		for(int i=0;i<10;i++) {
			/*cell=row.getCell(i);
			data[i]=String.valueOf(cell);*/
			DataFormatter format=new DataFormatter();
			XSSFCell cell=row.getCell(i);
			data[i]=format.formatCellValue(cell);
		}
		return data;
		
	}
	
	
	public static void writeExcelData(String sheetName, int r, int c, String data) throws IOException {
		//Opening the excel file
		file=new FileInputStream(System.getProperty("user.dir")+"\\TestData\\Exceldata.xlsx");
		workbook=new XSSFWorkbook(file); 
		//Creating the sheet
		if(workbook.getSheetIndex(sheetName)==-1) {
		workbook.createSheet(sheetName);              
		}
		sheet=workbook.getSheet(sheetName);
		if(sheet.getRow(r)==null) {
		    sheet.createRow(r);
		}
		row =sheet.getRow(r);   
		// Creating cell
		XSSFCell cell=row.createCell(c); 
		//Setting the data in the column
		cell.setCellValue(data); 
		// Writing the data by fileOutput
		FileOutputStream fo=new FileOutputStream(System.getProperty("user.dir")+"\\TestData\\Exceldata.xlsx");
		workbook.write(fo);
		workbook.close();
		file.close();
		fo.close();
}

}
