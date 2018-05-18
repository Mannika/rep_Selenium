package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	private static XSSFWorkbook wbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;

	public static void setExcelPath(String SheetName,String path) throws Exception
	{
		FileInputStream fis = new FileInputStream(path);
		wbook = new XSSFWorkbook(fis);
		sheet = wbook.getSheet(SheetName);

	}

	public static String getCellData(int rownum,int colnum)
	{

		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		String cellStringData = cell.getStringCellValue();
		return cellStringData;


	}

	public static String[][] getExcelTable()
	{

		int  rowcount = sheet.getPhysicalNumberOfRows();
		int col= 2;
		String[][] table = new String[rowcount][col];
		for(int i=0;i<rowcount;i++)
		{
			for(int j = 0;j<col;j++)
			{
				table[i][j] = getCellData(i,j);
			}	
		}

		return table;


	}
	
	/*--public static void setExcelCell(int rownum,int colnum,String celldata) throws Exception
	{
		row = sheet.getRow(rownum);
		
		cell = row.getCell(colnum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
		if(cell==null)
		{
			cell = row.createCell(colnum);
			cell.setCellValue(celldata);
		}
		else
		{
			cell.setCellValue(celldata);
		}
		FileOutputStream fos = new FileOutputStream("C:\\\\Users\\\\A06438_P5.Training\\\\Desktop\\\\drivers\\\\ExcelUtils.xlsx");
		wbook.write(fos);
		fos.flush();
		fos.close();
				
				
	}--*/
	
	
}

