/**
 * A class for the exception to be thrown
 * if all child references of this directory are occupied.
 * @author 
 *   Jason Tan, SBU ID: N/A
 * 
 * CSE 214 HW 5
 * Recitation 1: Jian Xi Chen
 * 
 */
public class FullDirectoryException extends Exception {
    /**
    * An exception where the message is written 
    * stating that all child references of this directory are occupied
    * 
    * @param message 
    *   This String argument returns a message stating
    *   that all child references of this directory are occupied
    */
    public FullDirectoryException(String message){  
        super();
    }
}
