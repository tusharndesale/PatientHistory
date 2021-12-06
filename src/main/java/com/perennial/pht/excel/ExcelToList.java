package com.perennial.pht.excel;

import com.perennial.pht.controller.PatientController;
import com.perennial.pht.dao.daoInterfaces.IPatientDao;
import com.perennial.pht.model.Patient;
import com.perennial.pht.repository.PatientRepository;
import com.perennial.pht.utilities.CommonUtility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    @Autowired
    static IPatientDao patientDao;
    private static PatientController controller;
  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<List<Patient>> excelToPatient(InputStream inputStream, SessionFactory sessionfactory) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("data");
            Iterator<Row> rows = sheet.iterator();
            List<Patient> recordList = new ArrayList<>();
            List<Patient> issueRecordList = new ArrayList<>();
            List<String> headerList = new ArrayList<>();
            String message= "";
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
                                headerList.add(0,headerCell.getStringCellValue());
                                break;
                            case 1:
                                headerList.add(1,headerCell.getStringCellValue());
                                break;
                            case 2:
                                headerList.add(2,headerCell.getStringCellValue());
                                break;
                            case 3:
                                headerList.add(3,headerCell.getStringCellValue());
                                break;
                            case 4:
                                headerList.add(4,headerCell.getStringCellValue());
                                break;
                            case 5:
                                headerList.add(5,headerCell.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                    rowNumber++;
                    continue;
                }

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
                boolean mobileNoChecked = utility.isValidMobileNo(patient.getMobileNo());

                    List<Patient> existanceInDBList = checkInDB(patient.getName(),patient.getMobileNo(),sessionfactory);
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

    private static List<Patient> checkInDB(String fullName, long mobileNumber, SessionFactory sessionfactory) {
        String hql;
        List<Patient> result = new ArrayList<>();
        Patient patient ;
        String patientName = fullName.replace(' ','%') ;

        try{

            Session session = sessionfactory.openSession();
            hql = "FROM Patient WHERE name LIKE '"+patientName+"' AND mobileNo = "+mobileNumber;
            Query query = session.createQuery(hql);

            patient = (Patient) query.getSingleResult();
            if (patient == null) return null;
            else {
                result.add(patient);

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return  result;
    }

}
