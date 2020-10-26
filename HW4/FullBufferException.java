/**
 * A class for the exception to be thrown
 * if the routers are full so that no more
 * packets can be sent from the dispatcher to
 * the routers. This would consequentially lead
 * to packets being congested or dropped.
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 4
 * Recitation 1: Jian Xi Chen
 * 
 */
public class FullBufferException extends Exception{
    /**
    * An exception where the message is written 
    * stating that all the routers are full
    * 
    * @param message 
    *   This String argument returns a message stating
    *   that all the routers are full
    */
    public FullBufferException(String message){  
        super();
    }
}
