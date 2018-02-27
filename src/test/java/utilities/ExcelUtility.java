package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtility {
  public static Map<String, String> testDataMap = new HashMap<String, String>();

    public static Map<String, String> loadData() {     
     
        try {
          FileInputStream fileInputStream = new FileInputStream("TestData.xls");
          HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
          HSSFSheet worksheet = workbook.getSheet("Sheet1");
          int totalNoOfRows = worksheet.getPhysicalNumberOfRows();
          for (int rowCount = 1; rowCount < totalNoOfRows; rowCount++) {
            HSSFRow row = worksheet.getRow(rowCount);
            HSSFCell cellKeyword = row.getCell((short) 0);
            HSSFCell cellvalue = row.getCell((short) 1);
            testDataMap.put(cellKeyword.getStringCellValue(),cellvalue.getStringCellValue());
          }


        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return testDataMap;
      }
}