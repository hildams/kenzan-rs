package com.kenzan.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kenzan.domain.Employee;



/**
 *<p> 
 * Class  : ApplicationContextConfig.java<br>
 * package: com.kenzan.config<br>
 * Project: kenzan-rs<br>
 * Description: <i>Configuration Class</i>

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

@Configuration
@ComponentScan("com.kenzan")
@EnableTransactionManagement
public class ApplicationContextConfig
{
   
   
   private String driverClassName;
   
   private String url;
   
   private String userName;
   
   private String password;
   
   @Autowired
   Environment enviroment;
   


   @Bean(name = "dataSource")
   public DataSource getDataSource()
   {
    

      this.driverClassName ="com.mysql.jdbc.Driver"; 
      this.url= "jdbc:mysql://employeedb.cewzdb9ptmlq.us-east-2.rds.amazonaws.com:3306/employeedb";
      this.userName = "kenzan_admin"; 
      this.password = "2bv5BzWB7hzWFJk3rTbP";
      BasicDataSource dataSourcecDB2 = new BasicDataSource();
      dataSourcecDB2.setDriverClassName(driverClassName);
      dataSourcecDB2.setUrl(url);
      dataSourcecDB2.setUsername(userName);
      dataSourcecDB2.setPassword(password);
      return dataSourcecDB2;
   }
   
   private Properties getHibernateProperties()
   {
      Properties properties = new Properties();
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
      return properties;
   }
   
   @Autowired
   @Bean(name = "sessionFactory")
   public SessionFactory getSessionFactory(DataSource dataSource)
   {
      LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
      sessionBuilder.addProperties(getHibernateProperties());
      sessionBuilder.addAnnotatedClasses(Employee.class);
    
      return sessionBuilder.buildSessionFactory();
   }
   
   @Autowired
   @Bean(name = "transactionManager")
   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
   {
      HibernateTransactionManager transactionMgr = new HibernateTransactionManager(sessionFactory);
      return transactionMgr;
   }


}

