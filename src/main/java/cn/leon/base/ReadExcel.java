package cn.leon.base;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
/**
 * 封装读excel表
 */
public class ReadExcel {
    Workbook workbook;

    /**
     * 构造函数传递需要读取的文件名称
     * @param path
     * @throws IOException
     * @throws InvalidFormatException
     */
    public ReadExcel(String path) throws IOException, InvalidFormatException {
        File file = new File(path);
        workbook = WorkbookFactory.create(file);
    }

    /**
     * 获得文件一共多少sheet页
     * @return
     */
    public int getSheetNum(){
        return workbook.getNumberOfSheets();
    }

    /**
     * 获得文件sheet页的名称（传递需要获取名称的sheet序列）
     * @param num
     * @return
     */
    public String getSheetname(int num){
        return workbook.getSheetName(num);
    }

    /**
     * 通过名称和序列获得sheet
     * @param num
     * @return
     */
    public Sheet getSheet(int num){
        return workbook.getSheetAt(num);
    }
    public Sheet getSheet( String  name) {
        return workbook.getSheet(name);
    }

    /**
     * 获得行数（将需要查询的sheet传递进去）
     * @param sheet
     * @return
     */
    public int getRowNum(Sheet sheet){
        return sheet.getLastRowNum()+1;
    }

    /**
     * 获得行，传递sheet和要等到行的行数
     * @param sheet
     * @param num
     * @return
     */
    public Row getRow(Sheet sheet, int num){
        return sheet.getRow(num);
    }

    /**
     * 获得列数，通过需要获得列数的行
     * @param row
     * @return
     */
    public int getColNum(Row row){
        return row.getLastCellNum();
    }

    /**
     * 对行和列数获得值
     * @param row
     * @param col
     * @return
     */
    public String getCellValue(Row row,int col){
        return row.getCell(col).getStringCellValue();
    }
    /**
     * 获得值，传递sheetname 和行数和列数即可获得相应值
     * @param sheetname
     * @param row
     * @param col
     * @return
     */
    public String getEasyValue(String sheetname,int row,int col){
        Sheet sheet = workbook.getSheet(sheetname);
        return sheet.getRow(row).getCell(col).getStringCellValue();
    }
    /**
     * 为案例提供Object[][]类型数据，为@DataProvider使用
     * @param sheetname 读取的sheet名
     * @param startRow 开始行（指定哪一行就从那一行开始读）
     * @param endRow 结束行
     * @param startCol 开始列
     * @param endCol 结束列
     * @return
     */
    public Object[][] getBatchValues(String sheetname, int startRow, int endRow, int startCol, int endCol)  {
        //调用自己的getSheet方法
        Sheet sheet = this.getSheet(sheetname);
        Object[][] datas = new Object[endRow - startRow + 1][endCol - startCol + 1];//确定几行几列
        for (int i = startRow; i <= endRow; i++) {
            Row row = sheet.getRow(i - 1);
            for (int j = startCol; j <= endCol; j++) {
                String value = row.getCell(j - 1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                datas[i - startRow][j - startCol] = value;
            }
        }
        return datas;
    }
}