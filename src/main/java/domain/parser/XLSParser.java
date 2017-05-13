package domain.parser;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imant on 13.05.17.
 */
public class XLSParser {

    protected File file;
    private static final int FIRST_COLUMN = 0;

    public XLSParser(File file) {
        this.file = file;
    }

    public List<String> getColumnNames(){
        List<String> columnList = new ArrayList<String>();
        try {
            FileInputStream xlsIS = new FileInputStream(file);
            Workbook workbook = new HSSFWorkbook(xlsIS);

            Sheet sheet = workbook.getSheetAt(0);
            Row firstRow = sheet.getRow(FIRST_COLUMN);
            int columnNumbers = firstRow.getPhysicalNumberOfCells();

            for (int position = 0; position < columnNumbers; position++) {
                columnList.add(firstRow.getCell(position).getStringCellValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnList;
    }
}
