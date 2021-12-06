package com.perennial.pht.excel;

import com.perennial.pht.model.Patient;
import com.perennial.pht.repository.PatientRepository;
import com.perennial.pht.utilities.CommonUtility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class ExcelToList {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static CommonUtility utility;
    @Autowired
    static PatientRepository repository;
  //  static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<List<Patient>> excelToPatient(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet("data");
            Iterator<Row> rows = sheet.iterator();
            List<Patient> recordList = new ArrayList<>();
            List<Patient> issueRecordList = new ArrayList<>();
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
                double id = 0;
                int cellNumber = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellNumber) {
                        case 0:
                          id = currentCell.getNumericCellValue();
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
                                String Sdate = currentCell.getStringCellValue();
                                LocalDate date = LocalDate.parse(Sdate, formatter);
                                patient.setDOB(date);
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
                boolean mobileNoChecked = utility.isValidMobileNo(patient.getMobileNo());

                    List<Patient> existanceInDBList = repository.existsByNameAndMobileNo(patient.getName(),patient.getMobileNo());
               List<Patient> existanceInRecordList = recordList.stream().filter(
                        ele -> (patient.getMobileNo()==ele.getMobileNo())
                                || (patient.getName().equals(ele.getName()))
                ).collect(Collectors.toList());

                List<Patient> existanceInIssueRecordList= issueRecordList.stream().filter(
                        ele -> (patient.getMobileNo()==ele.getMobileNo())
                            || (patient.getName().equals(ele.getName()))
                ).collect(Collectors.toList());

                if(!mobileNoChecked || !existanceInDBList.isEmpty() || !existanceInRecordList.isEmpty() || !existanceInIssueRecordList.isEmpty()) {
                    message = "Invalid Mobile No. "+patient.toString();
                    patient.setId((int) id);
                    issueRecordList.add(patient);
                }else{
                    recordList.add(patient);
                }
            }

            workbook.close();

            List<List<Patient>> result= new ArrayList<>();
            result.add(recordList);
            result.add(issueRecordList);
            return result;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
