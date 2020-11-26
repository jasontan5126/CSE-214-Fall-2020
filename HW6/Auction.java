/**
 * A class where the Auction class is implemented
 * to represent an active auction
 * 
 * @author 
 *   Jason Tan, SBU ID: N/A
 * 
 * CSE 214 HW 6
 * Recitation 1: Jian Xi Chen
 * 
 */
import java.io.Serializable;
import java.text.DecimalFormat;

public class Auction implements Serializable{
    DecimalFormat twoDecimalPlacesDuplicate = new DecimalFormat("###,###.00");
    private int timeRemaining;
    private double currentBid;
    private String auctionID;
    private String sellerName;
    private String buyerName;
    private String itemInfo;
    private String username;
    
    /**
     * Default constructor with no parameters
     */
    public Auction(){    
    }
    
    /**
     * The constructor with 6 parameters
     * @param timeRemaining
     *   The amount of time remaining for auction
     * @param currentBid
     *   The current bid amount for that auction
     * @param auctionID
     *   The ID for that auction
     * @param sellerName
     *   The name of that seller for the auction
     * @param buyerName
     *   The name of that buyer for the auction
     * @param itemInfo 
     *   The info about the item from the auction
     */
    public Auction(int timeRemaining, double currentBid, String auctionID, String sellerName, String buyerName, String itemInfo){
        this.username = getBuyerName();
        this.auctionID = auctionID;
        this.currentBid = currentBid;
        this.sellerName = sellerName;
        this.buyerName = buyerName;
        this.itemInfo = itemInfo;
        this.timeRemaining = timeRemaining;
    }
    
    /**
     * Getter method to getting the username
     * @return 
     *   The username of the user
     */
    public String getUsername(){
        return this.username;
    }
    
    
    /**
     * Getter method for the time remaining
     * @return 
     *   The amount of time remaining
     */
    public int getTimeRemaining(){
        return this.timeRemaining;
    }
   
    /**
     * Getter method to getting the current bid amount
     * @return 
     *   The current bid amount for the auction
     */
    public double getCurrentBid(){
        return this.currentBid;
    }
    
    
    /**
     * Getter method to getting the auction ID
     * @return 
     *   The Auction ID of the auction
     */
    public String getAuctionID(){
        return this.auctionID;
    }
    
    
    /**
     * Getter method to getting the seller name
     * @return 
     *   The seller name of that auction
     */
    public String getSellerName(){
        return this.sellerName;
    }
    
    /**
     * Getter method to getting the buyer name
     * @return 
     *   The buyer name of that auction
     */
    public String getBuyerName(){
        return this.buyerName;
    }
    
  
    
    /**
     * Getter method to getting the item info
     * @return 
     *   The item info of that auction
     */
    public String getItemInfo(){
        return this.itemInfo;
    }
    
    /**
     * To decrement the amount of time remaining by the amount of time
     * @param time 
     *   The amount of time to decrement for time remaining
     *
     * <dt><b>Postconditions:</b></dt>
     *    timeRemaining has been decremented by the indicated amount and is
     *    greater than or equal to 0.
     * 
     */
    public void decrementTimeRemaining(int time){
        if(time > this.timeRemaining){
            this.timeRemaining = 0;
        }
        else{
            this.timeRemaining -= time;
        }
    }
    
    /**
     * To place a new bid for that auction
     * @param bidderName
     *   The name of the bid
     * @param bidAmt
     *   The amount to place for the bidder name
     * <dt><b>Preconditions:</b></dt>
     *    The auction is not closed (i.e. timeRemaining > 0).
     * <dt><b>Postconditions:</b></dt>
     *    currentBid Reflects the largest bid placed on this object. 
     *    If the auction is closed, throw a ClosedAuctionException.
     * @throws ClosedAuctionException 
     *   Indicates that the auction is closed
     */
    public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException{
        if(this.timeRemaining > 0){
            if(bidAmt > this.currentBid){
                this.currentBid = bidAmt;
                this.buyerName = bidderName;
            }
        }
        if(this.timeRemaining == 0){
            throw new ClosedAuctionException("The auction is closed. No more bids can be placed");     
        }
    }
    

    
    /**
     * Prints an auction which will then be used in a table to print a list of auction
     * @return 
     *   The print auction to be displayed in a table
     */
    @Override
    public String toString(){
        String printString = "";
        if(this.currentBid == 0){
            printString += String.format("%s  | $%10s | %-26s | %-23s | %6s hours | %22s", this.auctionID, 
                "", this.sellerName, this.buyerName, this.timeRemaining,
                this.itemInfo.substring(0, Math.min(this.itemInfo.length(), 42)));
            return printString;
        }
        else{
            printString += String.format("%s  | $%10s | %-26s | %-23s | %6s hours | %22s", this.auctionID, 
                    this.twoDecimalPlacesDuplicate.format(this.currentBid), 
                    this.sellerName, this.buyerName, this.timeRemaining,
                    this.itemInfo.substring(0, Math.min(this.itemInfo.length(), 42)));
                    return printString;
        }
    }
}
