/**
 * Jason Tan
 * SBU ID: 112319102
 * CSE 214 HW 2
 * Recitation 1: Jian Xi Chen
 * 
 */

/**
 *
 * @author jason
 */
public class EndOfDoubleLinkedListException extends Exception {
    
    /**
    * An exception where the message is written 
    * stating that the cursor is at the end of 
    * the double linked list
    * 
    * @param message 
    *   This String argument returns a message stating
    *   that the double linked list is at the end
    *   (cursor) is at the tail
    */
    public EndOfDoubleLinkedListException(String message){  
        super();
    }
}
