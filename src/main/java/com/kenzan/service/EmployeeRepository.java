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
 * Description: <i></i
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


   @Query("Select e from Employee as e where e.status='Active'")
   List<Employee> listAll() throws Exception;

   @Query("Select e from Employee as e where e.employeeId = :employeeId and e.status='Active'")
   Optional<Employee> getEmployeeById(@Param("employeeId") int employeeId) throws Exception;
  
   @Transactional
   @Modifying
   @Query("update Employee e set e.firstName = :firstName, e.middleInitial = :middleInitial,e.lastName = :lastName,e.birthdate = :birthdate,e.startDate = :startDate where e.employeeId = :employeeId")
   int updateEmployee(@Param("firstName")String firstName,
	 @Param("middleInitial")String middleInitial, 
	 @Param("lastName")String lastName, 
	 @Param("birthdate")Date birthdate, 
	 @Param("startDate")Date startDate, 
      	 @Param("employeeId")int employeeId);
   
   @Transactional
   @Modifying
   @Query("update Employee e set e.status = 'Inactive' where e.employeeId = :employeeId")
   int changeEmployeeStatus(@Param("employeeId")int employeeId);
   
  
   
}


