package com.kenzan.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *<p> 
 * Class  : AppicationSecurityConfig.java<br>
 * package: com.kenzan.config<br>
 * Project: Kenzan-rs<br>
 * Description: <i>
 * Security configuration class to define security level to endpoints.
 * </i>
 * 
 * <br>
 * Created on Sep 1, 2019<br>
 * @author Hilda Medina Segovia <br>
 * 
 * @see<br>Revision History:<br>
 * <br>
 * Flag Date       Reason     Author   Remark<br>
 * ---- ---------- ---------- -------- ---------------------------------<br>
 *      Sep 1, 2019               hildam  New File
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{

   /**
    *  Manager builder configuration to allow in memory building authentication:
    *  Basic Auth.
    */
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
       .withUser("admin")
       .password("adminPassword")
       .roles("ADMIN");
   }
    
   /**
    *  Configure http basic security to specific endpoints: DELETE
    */
   @Override
   protected void configure(HttpSecurity http) throws Exception { 
      http
      .httpBasic()
      .and()
      .authorizeRequests()
      .antMatchers(HttpMethod.DELETE, "/employee/**").hasRole("ADMIN")
      .and()
      .csrf().disable()
      .formLogin().disable();
   }
}

