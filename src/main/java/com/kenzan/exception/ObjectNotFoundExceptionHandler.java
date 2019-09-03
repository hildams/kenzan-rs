package com.kenzan.exception;

/**
 *<p> 
 * Class  : ObjectExceptionHandler.java<br>
 * package: com.kenzan.exception<br>
 * Project: Kenzan-rs<br>
 * Description: <i></i>

 * Created on Sep 1, 2019<br>
 * @author Hilda Medina Segovia <br>
 * 
 * @see<br>Revision History:<br>
 * <br>
 * Flag Date       Reason     Author   Remark<br>
 * ---- ---------- ---------- -------- ---------------------------------<br>
 *      Sep 1, 2019            hildam  New File
 */


public class ObjectNotFoundExceptionHandler extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public ObjectNotFoundExceptionHandler(int id) {
       super("Employee id not found : " + id);
   }

}
