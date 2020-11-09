/**
 * A class for the exception to be thrown
 * if indicated name for directory or file exists
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 5
 * Recitation 1: Jian Xi Chen
 * 
 */
public class ExistingNameException extends Exception {
    /**
    * An exception where the message is written 
    * stating that the indicated name for directory or file exists
    * 
    * @param message 
    *   This String argument returns a message stating
    *   that the indicated name for directory or file exists
    */
    public ExistingNameException(String message){  
        super();
    }
}