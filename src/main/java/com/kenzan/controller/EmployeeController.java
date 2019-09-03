package com.kenzan.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kenzan.domain.Employee;
import com.kenzan.exception.ObjectFailedExceptionHandler;
import com.kenzan.exception.ObjectNotFoundExceptionHandler;
import com.kenzan.service.EmployeeRepository;

/**
 *<p> 
 * Class  : EmployeeController.java<br>
 * package: com.kenzan.controller<br>
 * Project: kenzan-rs<br>
 * Description: <i></i>
 * 
 * Created on Aug 31, 2019<br>
 * @author Hilda Medina Segovia <br>
 * 
 * @see<br>Revision History:<br>
 * <br>
 * Flag Date       Reason     Author   Remark<br>
 * ---- ---------- ---------- -------- ---------------------------------<br>
 *      Aug 31, 2019           hildam  New File
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController
{

   private static final String ACTIVE = "Active";
   public static final String PATH_FILE_UPLOAD =System.getProperty("user.dir")+"/uploads/";

   @Autowired
   EmployeeRepository employeeService;

   private Logger logger = LoggerFactory.getLogger(EmployeeController.class);


   @RequestMapping("/listEmployees")
   @ResponseBody
   public ResponseEntity<List<Employee>> getEmployees() throws Exception{
      logger.info("Retrieving all employees");
      List<Employee> employees = employeeService.listAll();
      return ResponseEntity.ok(employees);
   }

   @RequestMapping("/employeeById")
   @ResponseBody
   public Employee getEmployeeById(@RequestParam("employeeId") int employeeId) throws Exception{
      logger.info("Find employee by Id");
      return employeeService.getEmployeeById(employeeId)
	    .orElseThrow(() -> new ObjectNotFoundExceptionHandler(employeeId)); 
   }

   @RequestMapping("/saveEmployee")
   @ResponseBody
   public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws Exception{
      logger.info("Create employee");
      Employee emp = employeeService.saveAndFlush(employee);
      if(emp != null){
	 return  ResponseEntity.ok(emp);
      }else{
	 throw new ObjectFailedExceptionHandler("Create");
      }
   }

   @RequestMapping("/updateEmployee")
   @ResponseBody
   public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws Exception{
      logger.info("Update employee by Id");

      int response =  employeeService.updateEmployee(employee.getFirstName(),
	    employee.getMiddleInitial(),
	    employee.getLastName(),
	    employee.getBirthdate(),
	    employee.getStartDate(),
	    employee.getEmployeeId());
      if(response == 1){
	 return ResponseEntity.ok(employee);
      }else{
	 throw new ObjectFailedExceptionHandler("Update");
      }
   }

   @RequestMapping(value ="/deleteEmployee",method = RequestMethod.DELETE)
   @ResponseBody
   public ResponseEntity<String> deleteEmployee(@RequestParam("employeeId") int employeeId) throws Exception{
      logger.info("Delete employee by Id");

      int response = employeeService.changeEmployeeStatus(employeeId);

      if(response == 1){
	 return ResponseEntity.ok("Employee succesfully deleted");
      }else{
	 throw new ObjectNotFoundExceptionHandler(employeeId);
      }
   }

   @RequestMapping("/uploadEmployees")
   @ResponseBody
   public ResponseEntity<String> uploadEmployees() throws Exception{
      logger.info("Loading employees from file");
      
      String response =  loadEmployeesFromFile();

      return ResponseEntity.ok(response);

   }

   private String loadEmployeesFromFile()
   {
      int counterEmployee  = 0;
      int counterLines = 0;

      try (BufferedReader br = Files.newBufferedReader(Paths.get(PATH_FILE_UPLOAD+"employeesFile.csv"))) {

	 String line;
	 while ((line = br.readLine()) != null) {
	    counterEmployee += mapEmployee(line);
	    counterLines++;
	 }

      } catch (IOException e) {
	 System.err.format("IOException: %s%n", e);
      }

      return counterEmployee == 0 ? "Error while uploading employees, All fields are required." : counterEmployee+" out of"+counterLines+" employees loaded successfully";


   }

   private int mapEmployee(String s)
   {

      String[] properties = s.replaceAll("\\s+","").split(",");
      int insertedEmployee = 0;
      if(properties.length == 5){
	 Employee emp = new Employee();

	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	 emp.setFirstName(properties[0]);
	 emp.setMiddleInitial(properties[1]);
	 emp.setLastName(properties[2]);
	 emp.setStatus(ACTIVE); 
	 try
	 {
	    Date birthday = formatter.parse(properties[3]);
	    emp.setBirthdate(birthday);

	    Date startDate = formatter.parse(properties[4]);
	    emp.setStartDate(startDate);

	 }catch (ParseException e){
	    logger.info(e.getMessage());
	    e.printStackTrace();
	 }
	 employeeService.saveAndFlush(emp);
	 insertedEmployee = 1;
      }
      return insertedEmployee;
   }

}

