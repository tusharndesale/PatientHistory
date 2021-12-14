package com.perennial.pht.utilities;

import com.perennial.pht.model.Patient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class CommonUtility {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "sheet";
     Workbook workbook = new XSSFWorkbook();
     ByteArrayOutputStream out = new ByteArrayOutputStream();
    public static boolean isValidMobileNo(long mobileNo)
    {
        String phoneNumber = String.valueOf(mobileNo);
        String regex = "(0/91)?[7-9][0-9]{9}";
        boolean result = phoneNumber.matches(regex);
        return result;
    }


    public ByteArrayInputStream createExcelFromList(List<String> headerList, List<Patient> issueRecordList) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headerList.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headerList.get(i));
            }
            int rowIdx = 1;
            for (Patient record : issueRecordList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(record.getId());
                row.createCell(1).setCellValue(record.getName());
                row.createCell(2).setCellValue(record.getMobileNo());
                row.createCell(3).setCellValue(record.getGender());
                row.createCell(4).setCellValue(record.getDOB());
                row.createCell(5).setCellValue(record.getAddress());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
