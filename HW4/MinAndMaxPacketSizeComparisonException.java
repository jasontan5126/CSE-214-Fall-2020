/**
 * A class for the exception where the message is written 
 * stating that the minimum size is greater than or
 * equal to maximum size of packet entered
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 4
 * Recitation 1: Jian Xi Chen
 * 
 */
public class MinAndMaxPacketSizeComparisonException extends Exception{
    /**
    * An exception where the message is written 
    * stating that the minimum size is greater than or
    * equal to maximum size of packet entered
    * 
    * @param message 
    *   This String argument returns a message stating
    *   that the minimum size is greater than or
    *  equal to maximum size of packet entered
    */
    public MinAndMaxPacketSizeComparisonException(String message){  
        super();
    }
}
