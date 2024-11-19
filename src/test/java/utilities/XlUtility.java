package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XlUtility {
    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    String path;
    //public static CellStyle style;

    public XlUtility(String path)
    {
        this.path=path;
    }


    public int getRowCount(String xlsheet) throws IOException {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        ws = wb.getSheet(xlsheet);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fis.close();
        return rowCount;
    }

    public int getCellCount(String xlsheet, int rowNum) throws IOException {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fis.close();
        return cellCount;
    }

    public String getCellData(String xlsheet, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);

        String data;

        //Using try catch block as if cell as no data, we'll get an exception, to handle it, we use try catch
        try {
            // data=cell.toString();   -- or use the below method
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);// Returns the formatted value of cell as a string
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fis.close();
        return data;
    }

    public void setCellData(String xlsheet, int rowNum, int colNum, String Data)
            throws IOException {
        File xlfile= new File(path);
        if (!xlfile.exists()) //If File does not exist
        {
            wb=new XSSFWorkbook();
            fos= new FileOutputStream(path);
            wb.write(fos);
        }
        fis = new FileInputStream(path);
        wb = new XSSFWorkbook(fis);

        if (wb.getSheetIndex(xlsheet)==-1)
        {
            wb.createSheet(xlsheet);
            ws=wb.getSheet(xlsheet);
        }
        if (ws.getRow(rowNum)==null) //If Row does not exist, create a new row
        {
            ws.createRow(rowNum);
            row=ws.getRow(rowNum);
        }
        /*ws = wb.getSheet(xlsheet);
        row = ws.getRow(rowNum);*/
        cell = row.getCell(colNum);
        cell=row.createCell(colNum);
        cell.setCellValue(Data);
        fos = new FileOutputStream(path);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }

    /*public static void fillGreenColor(String xlfile, String xlsheet, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fis);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rowNum);
        cell = row.getCell(colNum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fos = new FileOutputStream(xlfile);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }

    public static void fillRedColor(String xlfile, String xlsheet, int rownum, int column) throws IOException {
        fis = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fis);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(column);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fos = new FileOutputStream(xlfile);
        wb.write(fos);
        wb.close();
        fis.close();
        fos.close();
    }*/

}
