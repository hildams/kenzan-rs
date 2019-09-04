package com.kenzan;
/**
 *<p> 
 * Class  : KenzanTestApplication.java<br>
 * package: com.kenzan.config<br>
 * Project: kenzan-rs<br>
 * Description: <i>
 * Main Application class for Kenzan REST API
 * </i>
 * <br>
 * Created on Aug 30, 2019<br>
 * @author Hilda Medina <br>
 * 
 * @see<br>Revision History:<br>
 * <br>
 * Flag Date       Reason     Author   Remark<br>
 * ---- ---------- ---------- -------- ---------------------------------<br>
 *   
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.kenzan.*"})
public class KenzanTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KenzanTestApplication.class, args);
	}

}
