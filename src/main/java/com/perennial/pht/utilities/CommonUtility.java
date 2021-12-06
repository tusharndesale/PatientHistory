package com.perennial.pht.utilities;

import com.perennial.pht.model.Patient;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
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

    public static List<String> getHeaderList(InputStream inputStream)  {
        List<String> headerList = new ArrayList<>();
        try{
       Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("data");
        Iterator<Row> rows = sheet.iterator();
        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();
            Iterator<Cell> cellsInRow = currentRow.iterator();
            if (rowNumber == 0) {
                while (cellsInRow.hasNext()) {
                    Cell headerCell = cellsInRow.next();
                    int headerCellNumber = 0;
                    switch (headerCellNumber) {
                        case 0:
                            if(headerCell.getCellType() == CellType.NUMERIC)
                            headerList.add(0, String.valueOf(headerCell.getNumericCellValue()));
                            else
                                headerList.add(0, headerCell.getStringCellValue());
                            break;
                        case 1:
                            headerList.add(1, headerCell.getStringCellValue());
                            break;
                        case 2:
                            headerList.add(2, headerCell.getStringCellValue());
                            break;
                        case 3:
                            headerList.add(3, headerCell.getStringCellValue());
                            break;
                        case 4:
                            headerList.add(4, headerCell.getStringCellValue());
                            break;
                        case 5:
                            headerList.add(5, headerCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
                continue;
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return headerList;
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
