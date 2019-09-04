package com.kenzan.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.kenzan.domain.Employee;

/**
 *<p> 
 * Class  : EmployeeRepository.java<br>
 * package: com.kenzan.service<br>
 * Project: kenzan-rs <br>
 * Description: <i>
 *Database operations availables to employee entity. 
 *
 * </i
 * Created on Aug 30, 2019<br>
 * @author Hilda Medina <br>
 * 
 * @see<br>Revision History:<br>
 * <br>
 * Flag Date       Reason     Author   Remark<br>
 * ---- ---------- ---------- -------- ---------------------------------<br>
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long>
{

   /**
    * Retrieve all employees with active status
    * @param employeeId
    * @return Employee or empty list
    */
   @Query("Select e from Employee as e where e.status='Active'")
   List<Employee> listAll() throws Exception;

   /**
    * Retrieve employee by ID
    * @param employeeId
    * @return Employee or empty object
    */
   @Query("Select e from Employee as e where e.employeeId = :employeeId and e.status='Active'")
   Optional<Employee> getEmployeeById(@Param("employeeId") int employeeId) throws Exception;
  
   /**
    * Update employee to properties
    * @param employeeId
    * @return 1 success, 0 error
    */
   @Transactional
   @Modifying
   @Query("update Employee e set e.firstName = :firstName, e.middleInitial = :middleInitial,e.lastName = :lastName,e.birthdate = :birthdate,e.startDate = :startDate where e.employeeId = :employeeId")
   int updateEmployee(@Param("firstName")String firstName,
	 @Param("middleInitial")String middleInitial, 
	 @Param("lastName")String lastName, 
	 @Param("birthdate")Date birthdate, 
	 @Param("startDate")Date startDate, 
      	 @Param("employeeId")int employeeId);
   
   
   
   /**
    * Update employee to inactive status (soft Delete)
    * @param employeeId
    * @return 1 success, 0 error
    */
   @Transactional
   @Modifying
   @Query("update Employee e set e.status = 'Inactive' where e.employeeId = :employeeId")
   int changeEmployeeStatus(@Param("employeeId")int employeeId);
      
}


