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

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class TrainManager {
    /**
    * The Train manager is where the user enters
    * the command from the command table to perform
    * a specific function like cursor forward, cursor
    * backwards, etc
    *
    * @param args 
    *   An array of sequence of character/strings passed
    *   to main function
    */
    public static void main(String [] args){
        DecimalFormat twoDecimalPlacesDuplicate = new DecimalFormat("0.00");
        Scanner stdin = new Scanner(System.in); 
        String selectionInput = "";
        double inputCarLength = 0;
        double inputCarWeight = 0;
        TrainLinkedList nodePointer = new TrainLinkedList();
    
        while(!selectionInput.equalsIgnoreCase("Q")){
            System.out.println("\n(F) Cursor Forward ");   
            System.out.println("(B) Cursor Backward ");    
            System.out.println("(I) Insert Car After Cursor ");  
            System.out.println("(R) Remove Car At Cursor ");   
            System.out.println("(L) Set Product Load ");       
            System.out.println("(S) Search For Product ");   
            System.out.println("(T) Display Train ");          
            System.out.println("(M) Display Manifest ");  
            System.out.println("(D) Remove Dangerous Cars ");
            System.out.println("(Q) Quit ");              
            System.out.println();
            
            System.out.print("\nEnter a selection: ");
            selectionInput = stdin.next();
            stdin.nextLine();
            
            //Command to make cursor move forward
            if (selectionInput.equalsIgnoreCase("F")){
                try{
                    nodePointer.cursorForward();
                }
                catch(EndOfDoubleLinkedListException e){
                    System.out.println("\nNo next car, cannot move cursor forward.\n");
                }
                catch(NullPointerException e){
                    System.out.println("\nThe list is empty");
                }
            }       
            //to have the cursor move backwards
            else if (selectionInput.equalsIgnoreCase("B")){
                try{
                    nodePointer.cursorBackward();
                }
                catch(EndOfDoubleLinkedListException e){
                    System.out.println("\nNo previous car, cannot move cursor backward.");
                }
                catch(NullPointerException e){
                    System.out.println("\nThe list is empty");
                }                
            }
            //to insert a new train car into the train
            else if (selectionInput.equalsIgnoreCase("I")){
                try{
                    TrainCar checkInputs = new TrainCar();
                    System.out.print("Enter car length in meters: ");
                    inputCarLength = stdin.nextDouble();
                    checkInputs.setLengthInMeters(inputCarWeight);
                    System.out.print("Enter car weight in tons: ");
                    inputCarWeight = stdin.nextDouble(); 
                    checkInputs.setWeightInTons(inputCarLength);
                    TrainCar newTrainNode = new TrainCar(inputCarLength, inputCarWeight);
                    nodePointer.insertAfterCursor(newTrainNode);  
                }
                catch(InputMismatchException e){
                    System.out.println("\nEnter the inputs again correctly of their"
                      + " corresponding instance type");
                    stdin.nextLine();
                }  
                catch(ArithmeticException e){
                    System.out.println("\nPlease enter a positive number");
                    stdin.nextLine();
                }
                catch(IllegalArgumentException e){
                    System.out.println("The new car is empty");
                }
            }
            //to remove a train car from the train
            else if (selectionInput.equalsIgnoreCase("R")){
                try{
                    TrainCar car = nodePointer.removeCursor();
                    System.out.println("\nCar successully unlinked. The following load has been removed from the train:\n");
                    System.out.println("Name        Weight (t)   Value ($)     Dangerous");
                    System.out.println("=====================================================");
                    if (!car.getLoadReference().getIsDangerous())
                        System.out.printf("%-15s%6s%12s%15s", car.getLoadReference().getProductName(),
                          car.getLoadReference().getWeightInTons(), 
                          twoDecimalPlacesDuplicate.format(car.getLoadReference().getValueInDollars()), "NO");
                    else if(car.getLoadReference().getIsDangerous())
                        System.out.printf("%-15s%6s%12s%15s", car.getLoadReference().getProductName(), 
                          car.getLoadReference().getWeightInTons(),
                          twoDecimalPlacesDuplicate.format(car.getLoadReference().getValueInDollars()), "YES");
                }
                catch(EmptyLinkedListException e){
                    System.out.println("\nList is empty.");
                }      
                catch(NullPointerException e){
                    System.out.printf("%-15s%6s%12s%15s", "Empty" , 0.0, twoDecimalPlacesDuplicate.format(0.00), "NO\n");
                }
            }
            //to load a train car to the train
            else if (selectionInput.equalsIgnoreCase("L")){
                boolean isDangerous = false;
                ProductLoad updateCurrentCar = new ProductLoad();
                TrainCar updateCar = new TrainCar(inputCarLength, inputCarWeight, updateCurrentCar);
                try{
                    System.out.print("\nEnter produce name: ");
                    String inputProduceName = stdin.nextLine();
                    updateCurrentCar.setProductName(inputProduceName);
                    System.out.print("Enter product weight in tons: ");
                    double inputWeight = stdin.nextDouble();
                    updateCurrentCar.setWeightInTons(inputWeight);
                    nodePointer.totalWeight += inputWeight;
                    System.out.print("Enter product value in dollars: ");
                    double inputValue = stdin.nextDouble();
                    updateCurrentCar.setValueInDollars(inputValue);
                    nodePointer.totalValue += inputValue;
                    System.out.print("Enter is product dangerous? (y/n): ");
                    char inputIsDangerous = stdin.next().charAt(0);
                    updateCurrentCar.setIsDangerous(isDangerous);
                
                    if(inputIsDangerous == 'y' || inputIsDangerous == 'Y'){
                        isDangerous = true;
                        updateCurrentCar = new ProductLoad(inputProduceName, inputWeight, inputValue, isDangerous);
                        nodePointer.getCursorData().setLoadReference(updateCurrentCar);
                
                        System.out.println("\n" + inputWeight + " tons of " + inputProduceName + " "
                          + "added to the current car");
                        nodePointer.numberOfDangerCount++;
                    }
                    else if (inputIsDangerous == 'n' || inputIsDangerous == 'N'){
                        isDangerous = false;
                        updateCurrentCar = new ProductLoad(inputProduceName, inputWeight, inputValue, isDangerous);
                        nodePointer.getCursorData().setLoadReference(updateCurrentCar);
                
                        System.out.println("\n" + inputWeight + " tons of " + inputProduceName + " "
                          + "added to the current car");
                    }
                    else{
                        System.out.println("\nPlease enter Y/N");   
                    }
                }
                catch(NullPointerException e){
                    System.out.println("\nThe list is empty");
                }           
                catch(InputMismatchException e){
                    System.out.println("\nEnter the inputs again correctly of their"
                      + " corresponding instance type");
                    stdin.nextLine();
                } 
                catch(ArithmeticException e){
                    System.out.println("\nPlease enter a positive number");
                    stdin.nextLine();
                }
                catch(StringIndexOutOfBoundsException e){
                    System.out.println("\nEnter correct number of characters for specific input");
                }
                
            }     
            // to search the product name in the train
            else if (selectionInput.equalsIgnoreCase("S")){
                try{
                    ProductLoad search = new ProductLoad();
                    System.out.print("\nEnter product name: ");
                    String inputProductName = stdin.nextLine();
                    search.setProductName(inputProductName);               
                    nodePointer.findProduct(inputProductName); 
                }
                catch(NullPointerException e){
                      System.out.println("\nThe list is empty");
                }
                catch(StringIndexOutOfBoundsException e){
                    System.out.println("\nEnter correct number of characters for specific input");
                }
            }       
            //to print the entire train info
            else if (selectionInput.equalsIgnoreCase("T")){ 
                try{
                    nodePointer.displayTrain();
                }
                catch(NullPointerException e){
                    
                }
            }         
            //print the manifest of the train
            else if (selectionInput.equalsIgnoreCase("M")){
                try{
                    nodePointer.printManifest();
                }
                catch(NullPointerException e){
                    System.out.println("\nEmpty list");
                }
            }          
            else if (selectionInput.equalsIgnoreCase("D")){
                try{
                    if (nodePointer.numberOfDangerCount == 0){
                        System.out.println("\nThere are no dangeorus train cars");
                    }
                    else{
                        nodePointer.removeDangerousCars();
                    }
                }
                catch(NullPointerException e){
                    System.out.println("\nEmpty list");
                }
                 
            }
                                                
            //to terminate the output console whenever the user feel like
            //he or she is done running the code
            else if (selectionInput.equalsIgnoreCase("Q")){
                System.out.println("\nProgram terminating successfully...");
                stdin.close();            
             }
            //if the letter command is invalid entered by the user
            else
                System.out.println("\nPlease enter a valid command letter");               
        } 
    }
}

