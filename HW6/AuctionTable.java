/**
 * A class where the Auction table is constructed
 * to represent the list of auctions listed in the
 * table
 * 
 * @author 
 *   Jason Tan, SBU ID: N/A
 * 
 * CSE 214 HW 6
 * Recitation 1: Jian Xi Chen
 * 
 */
import java.io.Serializable;
import big.data.*;
import java.util.HashMap;
import java.util.ArrayList;


public class AuctionTable extends HashMap <String, Auction> implements Serializable{
    
    /**
     * Connect to the data source located at the URL, read in all the data members 
     * for each record stored in the data source, construct new Auction objects based 
     * on the data, and insert the objects in to the table
     * @param URL
     *   The URL to locate
     * <dt><b>Preconditions:</b></dt>
     *    URL represents a data source which can be connected to using the 
     *    BigData library. The data source has proper syntax.
     * <dt><b>Postconditions:</b></dt>
     *    None
     * @return
     *   The AuctionTable constructed from the remote data source.
     * @throws IllegalArgumentException 
     *   Thrown if the URL does not represent a valid data source 
     *   (can't connect or invalid syntax).
     */
    public static AuctionTable buildFromURL(String URL) throws IllegalArgumentException{
        AuctionTable table = new AuctionTable();
        try{
            DataSource ds = DataSource.connect(URL).load();
            String[]dataSellerName = ds.fetchStringArray("listing/seller_info/seller_name");
            String[]dataCurrentBid = ds.fetchStringArray("listing/auction_info/current_bid");
            String[]dataTimeLeft = ds.fetchStringArray("listing/auction_info/time_left");
            String[]dataIdNum = ds.fetchStringArray("listing/auction_info/id_num");
            String[]dataBidderName = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
            String[]dataMemory = ds.fetchStringArray("listing/item_info/memory");
            String[]dataHardDrive = ds.fetchStringArray("listing/item_info/hard_drive");
            String[]dataCPU = ds.fetchStringArray("listing/item_info/cpu");
            double[]dataCurrentBi = new double[dataCurrentBid.length];
            
            int l = 0;
            while(dataIdNum.length > l){
                int i = 0;
                while(dataCurrentBid.length > i){
                    dataCurrentBi[i] = Double.parseDouble(dataCurrentBid[i].replace('$', '0').replaceAll(",", ""));
                    i++;
                }
                int[]dataTimeRemain = new int[dataTimeLeft.length];  
                int j = 0;
                while(dataTimeLeft.length > j){
                    int k = 0;
                    String [] dataTimeRemainin = dataTimeLeft[j].split(" ");
                    while(dataTimeRemainin.length > k){
                        if(dataTimeRemainin[k].contains("day")) {
                            dataTimeRemain[j] = dataTimeRemain[j] + Integer.parseInt(dataTimeRemainin[k-1]) * 24;
                        }
                        else if(dataTimeRemainin[k].contains("hour") || dataTimeRemainin[k].contains("hrs")) {
                            dataTimeRemain[j] = dataTimeRemain[j] + Integer.parseInt(dataTimeRemainin[k-1]);
                        }
                        k++;
                    }              
                    j++;
                }   
                String itemInfo = "";
                itemInfo = dataCPU[l] + " - " + dataMemory[l] + " -" + dataHardDrive[l];
                Auction auction = new Auction(dataTimeRemain[l], dataCurrentBi[l],dataIdNum[l],
                dataSellerName[l], dataBidderName[l], itemInfo);
                table.putAuction(dataIdNum[l], auction);
                l++;
            }
            System.out.println();
            System.out.println("Loading... ");          
            System.out.println("Auction data loaded successfully!");
        }
        catch (IllegalArgumentException | big.data.DataSourceException e) {
            System.out.println("\nThe URL does not represent a valid datasource");
            return null;
        }
        return table;
    }
	
    /**
     * To put the auction and place it in the table
     * @param auctionID
     *   The ID of the auction
     * @param auction
     *    The auction that is to be added
     * <dt><b>Preconditions:</b></dt>
     *     None
     * <dt><b>Postconditions:</b></dt>
     *     None
     * 
     * @throws IllegalArgumentException 
     *   The auction ID already exists in the table
     */
    public void putAuction(String auctionID, Auction auction) throws IllegalArgumentException{
        if(keySet().contains(auctionID)){
            throw new IllegalArgumentException("The given auctionID is already stored in the table");
        }
        else
            super.put(auctionID, auction);
    }
    
    /**
     * To get the auction from the table
     * @param auctionID
     *   The auction ID to be found
     * 
     * @return 
     *   the Auction to be searched
     */
    public Auction getAuction(String auctionID){
        return super.get(auctionID);
    }
    
    /**
     * Simulates the passing of time. Decrease the timeRemaining of all Auction 
     * objects by the amount specified. The value cannot go below 0.
     * @param numHours
     *   The number of hours to decrement the time remaining
     * <dt><b>Postconditions:</b></dt>
     *    All Auction in the table have their timeRemaining timer decreased. 
     *    If the original value is less than the decreased value, set the 
     *    value to 0.
     * 
     * @throws IllegalArgumentException 
     *    The number of hours is negative
     */
    public void letTimePass(int numHours) throws IllegalArgumentException{
        if(numHours < 0){
            throw new IllegalArgumentException("The number of hours to decrease is non-negative");
        }
        else{
            for(Auction auctions: values()){
                auctions.decrementTimeRemaining(numHours);    
            }
        }
    }
    
    /**
     * To remove the list of auction that expired
     * <dt><b>Postconditions:</b></dt>
     *    Only open Auction remain in the table.
     */
    public void removeExpiredAuctions(){
        ArrayList <String> expiredAuction = new ArrayList <String>(super.size());
        for(String expiredKey: keySet()){
            if(getAuction(expiredKey).getTimeRemaining() == 0){
                expiredAuction.add(expiredKey);
            }
        }      
        for(String expiredKey: expiredAuction){
            super.remove(expiredKey);
        }
    }
    
    
    
    /**
     * To print the table with the list of auction
     */
    public void printTable(){       
        System.out.println( "Auction ID |       Bid   |       Seller               |      Buyer       "
                + "       |    Time      |   Item Info");
        System.out.println("==========================================="
          + "========================================================================"
                + "========================");
        for(Auction auctions : values()){
            System.out.println(auctions.toString());
        }       
    }
}
