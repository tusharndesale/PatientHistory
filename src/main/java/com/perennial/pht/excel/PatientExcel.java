package com.perennial.pht.excel;

import com.perennial.pht.model.Patient;
import com.perennial.pht.repository.PatientRepository;
import com.perennial.pht.utilities.CommonUtility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PatientExcel {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static CommonUtility utility;
    static PatientRepository repository;
    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Patient> excelToPatient(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet("data");
            Iterator<Row> rows = sheet.iterator();
            List<Patient> recordList = new ArrayList<>();
            List<Patient> issueRecordsList = new ArrayList<>();
            String message= "";
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                Patient patient = new Patient();

                int cellNumber = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellNumber) {
                        case 0:
                            patient.setId((int) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            patient.setName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            patient.setMobileNo((long) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            patient.setGender(currentCell.getStringCellValue());
                            break;
                        case 4:
                            try {
                                String Sdate = currentCell.getStringCellValue();
                                patient.setDOB(formatter.parse(Sdate));
                            }catch (ParseException ex){
                                ex.printStackTrace();
                            }
                            break;
                        case 5:
                            patient.setAddress(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellNumber++;
                }
                //check Validatin
                boolean mobileNo = utility.isValidMobileNo(patient.getMobileNo());
                boolean checkExistance = repository.existsByNameAndMobileNo(patient.getName(),patient.getMobileNo());
                if(!checkExistance){
                    message = "Duplicate Record of "+patient.toString();
                    issueRecordsList.add(patient);
                }else if(!mobileNo) {
                    message = "Invalid Mobile No. "+patient.toString();
                    issueRecordsList.add(patient);
                }else{
                    recordList.add(patient);
                }
            }

            workbook.close();

            return recordList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
