/**
 * A class implemented to have the user interact with the database by listing open 
 * auction, make bids on open auction, and create new auction for different items.
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 6
 * Recitation 1: Jian Xi Chen
 * 
 */
import java.util.Scanner;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;

public class AuctionSystem implements Serializable{
    private static AuctionTable test;
    private static AuctionTable auctionTable;
    private static String username;
    
    /**
    * The AuctionSystem is what runs the actual functionality of the
    * auction system by the user inputting the commands
    *
    * @param args 
    *   An array of sequence of character/strings passed
    *   to main function
    */
    public static void main(String args[]){
        DecimalFormat twoDecimalPlacesDuplicate = new DecimalFormat("#.00");
        Auction testOne = new Auction();
        Scanner stdin = new Scanner(System.in); 
        String selectionInput = "";
        String enterUsername = "";
        String getID = "";
        String addSomething = "";
        int enterInt = 0;
        boolean check = true;
        double enterBid = 0;
            try{
                FileInputStream file = new FileInputStream("auction.obj");
                ObjectInputStream inStream = new ObjectInputStream(file);
                System.out.println("Starting...");
                System.out.println("Loading previous Auction Table...   ");
                test = (AuctionTable) inStream.readObject();            
            }
            catch(FileNotFoundException e){
                System.out.println("Starting...");
                System.out.println("No previous auction table detected.");
                test = new AuctionTable();
                System.out.println("Creating new table...");
            }
            catch(IOException e){
                System.out.println("Starting...");
                System.out.println("No previous auction table detected.");
                test = new AuctionTable();
                System.out.println("Creating new table...");
            }
            catch(ClassNotFoundException e){
                System.out.println("Starting...");
                System.out.println("No previous auction table detected.");
                test = new AuctionTable();
                System.out.println("Creating new table...");
            }
            
                      
            check = true;
            System.out.print("\nPlease select a username: ");
            enterUsername = stdin.nextLine();
        
            while(check != false){
                System.out.println("\nMenu: "); 
                System.out.println("    (D) - Import Data from URL ");   
                System.out.println("    (A) - Create a New Auction ");    
                System.out.println("    (B) - Bid on an Item ");  
                System.out.println("    (I) - Get Info on Auction ");   
                System.out.println("    (P) - Print All Auctions ");       
                System.out.println("    (R) - Remove Expired Auctions ");   
                System.out.println("    (T) - Let Time Pass ");          
                System.out.println("    (Q) - Quit ");           
                System.out.println();
            
                System.out.print("\nPlease select an option: ");
                selectionInput = stdin.nextLine();
                if (selectionInput.equalsIgnoreCase("D")){
                    System.out.print("Please enter a URL: ");
                    selectionInput = stdin.next();
                    test = test.buildFromURL(selectionInput);
                    System.out.println();
                     
                    stdin.nextLine();           
                }       
                else if (selectionInput.equalsIgnoreCase("A")){
                    System.out.println("\nCreating new Auction as " + enterUsername);
                    try{
                        System.out.print("Please enter an Auction ID: ");
                        getID = stdin.nextLine().trim();
                        if(getID.length() < 9){
                            throw new StringIndexOutOfBoundsException("Enter less than or equal to "
                                + "9 digits");
                        }
                        System.out.print("Please enter an Auction time (hours): ");
                        enterInt = stdin.nextInt();
                        stdin.nextLine();
                        if(enterInt < 0){
                            throw new ArithmeticException("Hours cannot be negative");
                        }
                        System.out.print("Please enter some Item Info: ");
                        addSomething = stdin.nextLine().trim();
                        testOne = new Auction(enterInt, 0, getID, enterUsername, "", addSomething);
                        test.putAuction(getID, testOne);
                        System.out.println();
                        System.out.println("Auction " + getID + " inserted into table."); 
                    }
                    catch(ArithmeticException e){
                        System.out.println("\nThe amount of hours cannot be negative");
                        stdin.nextLine();
                    }
                    catch(StringIndexOutOfBoundsException e){
                        System.out.println("\nEnter auction ID less than or equal to 9 digits");
                    }
                    catch(InputMismatchException e){
                        System.out.println("\nEnter in correct inputs");
                    }
                    catch(NullPointerException e){
                        System.out.println("\nYou must load data first from valid URL");
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("\nThe auction ID already exist");
                    }
                }        
                else if (selectionInput.equalsIgnoreCase("B")){
                    try{
                        System.out.print("Please enter an Auction ID: ");
                        getID = stdin.nextLine().trim();
                        if(getID.length() < 9){
                            throw new StringIndexOutOfBoundsException("Enter less than or equal to "
                                + "9 digits");
                        }
                        if (test.getAuction(getID).getCurrentBid() == 0 && 
                            test.getAuction(getID).getTimeRemaining() != 0){
                            System.out.println();
                            System.out.println("Auction " + getID + " is OPEN");
                            System.out.println("    Current Bid: None " + "\n");
                            System.out.print("What would you like to bid?: ");
                            enterBid = stdin.nextDouble();
                            if(enterBid < 0){
                                throw new ArithmeticException("Cannot be negative");
                            }
                            test.getAuction(getID).newBid(enterUsername, enterBid);
                            System.out.println("Bid accepted.");
                            stdin.nextLine();
                        }
                        else if (test.getAuction(getID).getCurrentBid() == 0 && 
                                test.getAuction(getID).getTimeRemaining() == 0){
                            System.out.println();
                            System.out.println("Auction " + getID + " is CLOSED");
                            System.out.println("    Current Bid: None " + "\n");
                            System.out.println("You can no longer bid on this item.");
                            stdin.nextLine();
                        }
                        else if(test.getAuction(getID).getTimeRemaining() != 0){
                            System.out.println();
                            System.out.println("Auction " + getID + " is OPEN");
                            System.out.println("    Current Bid: $ " + 
                            twoDecimalPlacesDuplicate.format(test.getAuction(getID).getCurrentBid()) + "\n");
                            System.out.print("What would you like to bid?: ");
                            enterBid = stdin.nextDouble();
                            if(enterBid < 0){
                                throw new ArithmeticException("Cannot be negative");
                            }
                            test.getAuction(getID).newBid(enterUsername, enterBid);
                            System.out.println("Bid accepted.");
                            stdin.nextLine();
                        }
                        else if(test.getAuction(getID).getTimeRemaining() == 0){
                            System.out.println();
                            System.out.println("Auction " + getID + " is CLOSED");
                            System.out.println("    Current Bid: $ " + 
                              twoDecimalPlacesDuplicate.format(test.getAuction(getID).getCurrentBid()) + "\n");
                            System.out.println("You can no longer bid on this item.");
                        }           
                    }
                    catch(ArithmeticException e){
                        System.out.println("\nThe amount to bid cannot be negative");
                        stdin.nextLine();
                    }
                    catch(StringIndexOutOfBoundsException e){
                        System.out.println("\nEnter auction ID less than or equal to 9 digits");
                    }
                    catch(InputMismatchException e){
                        System.out.println("\nEnter in correct inputs");
                    }
                    catch(NullPointerException e){
                        System.out.println("\nThis ID does not exist in auction");
                    }
                    catch(ClosedAuctionException e){
                        System.out.println("\nThe auction is closed");
                    }
                }  
                else if (selectionInput.equalsIgnoreCase("I")){
                    try{
                        System.out.print("Please enter an Auction ID: ");
                        getID = stdin.nextLine().trim();
                        if(getID.length() < 9){
                            throw new StringIndexOutOfBoundsException("Enter less than or equal to "
                                + "9 digits");
                        }
                        System.out.println();
                        System.out.println("Auction " + getID);
                        System.out.println("    Seller: " + test.getAuction(getID).getSellerName());
                        System.out.println("    Buyer: " + test.getAuction(getID).getBuyerName());
                        System.out.println("    Time: " + test.getAuction(getID).getTimeRemaining() + " hours");
                        System.out.println("    Info: " + test.getAuction(getID).getItemInfo());
                    }
                    catch(StringIndexOutOfBoundsException e){
                        System.out.println("\nEnter auction ID less than or equal to 9 digits");
                    }
                    catch(InputMismatchException e){
                        System.out.println("\nEnter in correct inputs");
                    }
                    catch(NullPointerException e){
                        System.out.println("\nThis auction ID does not exist");
                    }
                }       
                else if (selectionInput.equalsIgnoreCase("P")){
                    try{
                        System.out.println();
                        test.printTable();    
                    }
                    catch(NullPointerException e){
                        System.out.println("\nYou must load data first from valid URL");
                    }
                }     
                else if (selectionInput.equalsIgnoreCase("R")){
                    try{
                        System.out.println();
                        System.out.println("Removing expired auctions...");
                        test.removeExpiredAuctions();
                        System.out.println("All expired auctions removed");
                    }
                    catch(NullPointerException e){
                        System.out.println("\nYou must load data first from valid URL");
                    }
                }       
                else if (selectionInput.equalsIgnoreCase("T")){
                    try{
                        System.out.print("How many hours should pass: ");
                        enterInt = stdin.nextInt();
                        test.letTimePass(enterInt);
                        System.out.println("\nTime passing...");
                        System.out.println("Auction times updated.");
                        stdin.nextLine();
                    }
                    catch(InputMismatchException e){
                        System.out.println("\nEnter in correct inputs");
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("\nThe number of hours cannot be negative");
                        stdin.nextLine();
                    }
                    catch(NullPointerException e){
                        System.out.println("\nYou must load data first from valid URL");
                        stdin.nextLine();
                    }
                }         
                else if (selectionInput.equalsIgnoreCase("Q")){
                    try{
                        System.out.println();
                        System.out.println("Writing Auction Table to file... ");
                        FileOutputStream file = new FileOutputStream("auction.obj");
                        ObjectOutputStream outStream = new ObjectOutputStream(file);
                        outStream.writeObject(test);
                        System.out.println("Done!");
                        System.out.println();
                        System.out.println("Goodbye.");
                        System.out.println();
                        System.out.println();
                        check = false;
                    }
                    catch (IOException e){
                        System.out.println("\nFailed to write auction table to file");
                    }
                }
                //if the letter command is invalid entered by the user
                else
                    System.out.println("\nPlease enter a valid command letter");               
            }        
    }
}