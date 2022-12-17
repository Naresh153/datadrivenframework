package datadrivenFrameworkpack;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datadrivenclass 
{
	XSSFWorkbook wb=null;
	XSSFSheet sheet=null;
	XSSFRow row=null;
    XSSFCell cell=null;	
  
    public void datasheet(String xlsheet) throws IOException {
    	FileInputStream stream= new FileInputStream(xlsheet);
    	wb=new XSSFWorkbook(stream);
    	wb.close();
    }
    public int rowcount(int index) {
    	int rownum=wb.getSheetAt(index).getLastRowNum();
    	rownum++;
		return rownum;
    	
    }
    public String getcelldata(int shno,int rowno,int cno) {
    	cell=wb.getSheetAt(shno).getRow(rowno).getCell(cno);
    	try {
    		if(cell.getCellType()== CellType.STRING) {
    			return cell.getStringCellValue();
    		}
    		else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA ) {
    			String cellvalue= String.valueOf(cell.getNumericCellValue());
			  if(DateUtil.isCellDateFormatted(cell)) {
				  SimpleDateFormat df=new SimpleDateFormat("yyy-mm-ddd");
				  Date date= cell.getDateCellValue();
				  cellvalue=df.format(date);
			  }
			  return cellvalue;
			}
    		else if (cell.getCellType()==CellType.BLANK) {
    			return " ";	
			}
    		else {
    		     return String.valueOf(cell.getBooleanCellValue());
			}
    	
		} catch (Exception e) 
    	{
			e.printStackTrace();
			return "rownumber"+  rowno +"columnnumber"+ cno;
		}
		
    	
    }
}
