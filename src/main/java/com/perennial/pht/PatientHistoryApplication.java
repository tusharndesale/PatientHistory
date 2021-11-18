package com.perennial.pht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientHistoryApplication //implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(PatientHistoryApplication.class, args);
	}
/*

	@Autowired
	private PatientRepository patientRepository;
	@Override
	public void run(String... args) throws Exception{
		Patient patient = new Patient();
		patient.setId(1L);
		patient.setName("Tushar");
		patient.setAddress("Dhule");
		patient.setGender("Male");
		patient.setMobileNo(7798474419L);
		patientRepository.save(patient);
		Patient patient1 = new Patient();
		patient1.setId(2L);
		patient1.setName("Mayur");
		patient1.setAddress("Nashik");
		patient1.setGender("Male");
		patient1.setMobileNo(94036474737L);
		patientRepository.save(patient1);

	}
*/


}
