import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ReadDataFromExcel {

    public static Object[][] getTestData(String path, String sheetName) {
        Object[][] excelData = null;
        try{
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow rw = sh.getRow(0);
            int columNo = rw.getLastCellNum();
            int rowNo = sh.getLastRowNum();

            excelData=new Object[rowNo][columNo];

            for(int i=1; i<=rowNo; i++){
                for(int j=0; j<columNo; j++){
                    XSSFCell cell = sh.getRow(i).getCell(j);
                    if(cell.getCellType()== CellType.NUMERIC) {
                        excelData[i - 1][j] = cell.getNumericCellValue();
                    }else{
                        excelData[i - 1][j] = cell.getStringCellValue();
                    }
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }
        return excelData;
    }
}
