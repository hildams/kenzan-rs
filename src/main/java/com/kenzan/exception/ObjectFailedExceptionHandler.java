package com.kenzan.exception;

/**
 *<p> 
 * Class  : ObjectFailedHandler.java<br>
 * package: com.kenzan.exception<br>
 * Project: kenzan-rs<br>
 * Description: <i></i>
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
public class ObjectFailedExceptionHandler extends RuntimeException
{
   private static final long serialVersionUID = 1L;

   public ObjectFailedExceptionHandler(String operation) {
      super("Unable to process employee action:"+ operation);
  }
     
}

