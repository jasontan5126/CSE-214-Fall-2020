/**
 * A class for the exception to be thrown
 * if the auction is closed
 *   Jason Tan, SBU ID: N/A
 * 
 * CSE 214 HW 6
 * Recitation 1: Jian Xi Chen
 * 
 */
public class ClosedAuctionException extends Exception {
    /**
    * To throw this exception if the auction is closed
    * because the time left is 0
    * 
    * @param message 
    *   The message to be thrown if the auction is closed
    *   
    */
    public ClosedAuctionException(String message){  
        super();
    }
}
