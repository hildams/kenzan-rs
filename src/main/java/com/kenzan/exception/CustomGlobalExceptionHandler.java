package com.kenzan.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *<p> 
 * Class  : CustomGlobalExceptionHandler.java<br>
 * package: com.kenzan.exception<br>
 * Project: kenzan rs<br>
 * Description: <i>
 * 
 * Exception handler for custom message after exception
 * </i>
 * 
 * <br>
 * Created on Sep 1, 2019<br>
 * @author Hilda Medina Segovia<br>
 * 
 * @see<br>Revision History:<br>
 * <br>
 * Flag Date       Reason     Author   Remark<br>
 * ---- ---------- ---------- -------- ---------------------------------<br>
 *      Sep 1, 2019               hildam  New File
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

   /**
    * Custom exception for 404 not found
    * @param response
    * @throws IOException
    */
    @ExceptionHandler(ObjectNotFoundExceptionHandler.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
    
    
    /**
     * Custom exception for dependency fail.
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(ObjectFailedExceptionHandler.class)
    public void springHandleSaveException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FAILED_DEPENDENCY.value());
    }
}

