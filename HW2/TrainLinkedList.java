/**
 * Jason Tan
 * SBU ID: Something
 * CSE 214 HW 2
 * Recitation 1: Jian Xi Chen
 * 
 */

/**
 *
 * @author jason
 */
import java.text.DecimalFormat;

public class TrainLinkedList {
    private TrainCarNode head;
    private TrainCarNode tail;
    private TrainCarNode cursor;
    private int numberOfTrainCars;
    private double totalLength;
    public double totalWeight; 
    public int numberOfDangerCount;
    private int numberOfDangerCountUpdate;
    public double totalValue;
    private DecimalFormat twoDecimalPlaces = new DecimalFormat("###,###,##0.00");
    private DecimalFormat oneDecimalPlaces = new DecimalFormat("###,###,##.0");
    private DecimalFormat oneDecimalPlacesDuplicate = new DecimalFormat("###,###,##0.0");
    
    /**
    * The default constructor for TrainLinkedList which initializes the
    * head, tail and cursor to null
    */
    
    public TrainLinkedList(){
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }
    
    /**
    * A method to get the data at the cursor's position of train car
    * 
    * <dt><b>Postconditions:</b></dt>
    *    The cursor reference to the train car to get data
    * 
    * @return 
    *   The data of the cursor at the current train car
    *    
    */
    public TrainCar getCursorData(){
        if (cursor == null)
            return null;
        else
            return cursor.getCar();
    }
    
   /**
    * A method to set the cursor data to that specific train car
    * @param car 
    *   The train car of the train
    * 
    * <dt><b>Preconditions:</b></dt>
    *    The list is not empty and the cursor is not null
    * 
    * <dt><b>Postconditions:</b></dt>
    *    The cursor now references that train car to get data
    */
    public void setCursorData(TrainCar car){
        if (cursor != null)
            cursor.setCar(car);
    }
    
   /**
    * A method to be able to move the cursor forward to next train node
    * 
    * <dt><b>Preconditions:</b></dt>
    *    The list is not empty and the cursor does not reference the 
    *    tail of the list
    * 
    * <dt><b>Postconditions:</b></dt>
    *    The cursor advances forward to the next TrainCarNode
    * 
    * @throws EndOfDoubleLinkedListException 
    *   Indicates that the cursor has come to the tail of the 
    *   train and that there are no more train cars after that
    */
    public void cursorForward() throws EndOfDoubleLinkedListException{
        if (cursor.getNext() == null)
            throw new EndOfDoubleLinkedListException("\nNo more train cars nodes after tail");
        else if (cursor.getNext() != null){       
            cursor = cursor.getNext();
            System.out.println("\nCursor moved forward");
        }
    }
    
   /**
    * A method to be able to move the cursor backwards to previous train node
    * 
    * <dt><b>Preconditions:</b></dt>
    *    The list is not empty and the cursor does not reference the 
    *    head of the list
    * 
    * <dt><b>Postconditions:</b></dt>
    *    The cursor advances backwards to the previous TrainCarNode
    * 
    * @throws EndOfDoubleLinkedListException 
    *   Indicates that the cursor is at the head and that
    *   it cannot move back any further
    */
    public void cursorBackward() throws EndOfDoubleLinkedListException{
        if (cursor.getPrev() == null)
            throw new EndOfDoubleLinkedListException("\nNo more train cars nodes before head");
        else if (cursor.getPrev() != null){
            cursor = cursor.getPrev();
            System.out.println("\nCursor moved backward");
        }
    }

   /**
    * A method to insert a new train car after the cursor in the train
    * double linked list
    * 
    * @param newCar
    *   A new train car to be added to the train
    * 
    * <dt><b>Preconditions:</b></dt>
    *    The TrainCarNode object is instantiated
    * 
    * <dt><b>Postconditions:</b></dt>
    *    The new train car is inserted in the train after the cursor
    *    The order is still preserved for the train after the
    *    train car is inserted
    * 
    * @throws IllegalArgumentException 
    *   Indicates that the new train car to be added is empty
    */
    public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException{     
        //create new TrainCarNode
        TrainCarNode newTrainCar = new TrainCarNode(newCar);
        //if newCar is empty       
        if (newCar == null)
            throw new IllegalArgumentException("The new train car is empty"); 
        
        //cursor is null
        if(cursor == null){
            head = newTrainCar;
            tail = newTrainCar;
            cursor = newTrainCar;
            this.totalLength += newTrainCar.getCar().getLengthInMeters();
            this.totalWeight += newTrainCar.getCar().getWeightInTons();
        }
        //check the tail
        else if(cursor.getNext() == null){
            cursor.setNext(newTrainCar);
            newTrainCar.setPrev(cursor);
            cursor = newTrainCar;
            this.totalLength += newTrainCar.getCar().getLengthInMeters();
            this.totalWeight += newTrainCar.getCar().getWeightInTons();
        }
        //check the middle which is not head and tail
        else{
            cursor.getNext().setPrev(newTrainCar);
            newTrainCar.setNext(cursor.getNext());
            cursor.setNext(newTrainCar);
            newTrainCar.setPrev(cursor);
            cursor = newTrainCar;
            this.totalLength += newTrainCar.getCar().getLengthInMeters();
            this.totalWeight += newTrainCar.getCar().getWeightInTons();
        } 
        this.numberOfTrainCars++;
        cursor = newTrainCar;
        System.out.println("\nNew train car " + newTrainCar.getCar().getLengthInMeters() + " meters " 
          +  newTrainCar.getCar().getWeightInTons() + " tons inserted into train\n");   
    }
        
   /**
    * A method which the train car at where the cursor currently at
    * is removed from the train
    * @return
    *   the removed train car of the train
    * 
    * @throws EmptyLinkedListException
    *   Indicates that there is no train cars to remove
    */
    public TrainCar removeCursor() throws EmptyLinkedListException{
        TrainCarNode totalAddedLoadAtCurrentCursor = head;
        TrainCar removed = cursor.getCar();
        //if the cursor is null
        if (cursor == null){
            throw new EmptyLinkedListException("There's nothing to remove");
        }   
        //if the cursor is at head
        if (cursor.getPrev() == null){
            head = cursor.getNext();
            cursor = head;
            this.totalLength -= removed.getLengthInMeters();
            this.totalWeight -= removed.getWeightInTons();
            this.totalWeight -= removed.getLoadReference().getWeightInTons();
            this.totalValue -= removed.getLoadReference().getValueInDollars();
        }           
        //if cursor is at tail
        else if (cursor.getNext() == null){
            cursor.getPrev().setNext(null);
            cursor = cursor.getPrev();
            this.totalLength -= removed.getLengthInMeters();
            this.totalWeight -= removed.getWeightInTons();
            this.totalWeight -= removed.getLoadReference().getWeightInTons();
            this.totalValue -= removed.getLoadReference().getValueInDollars();
        }
        //if the cursor is at middle (neither head or tail)
        else if(cursor != head && cursor != tail){
            cursor.getNext().setPrev(cursor.getPrev());
            cursor.getPrev().setNext(cursor.getNext());
            cursor = cursor.getNext();
            this.totalLength -= removed.getLengthInMeters();
            this.totalWeight -= removed.getWeightInTons();
            this.totalWeight -= removed.getLoadReference().getWeightInTons();
            this.totalValue -= removed.getLoadReference().getValueInDollars();
        }
        //check if the dangerous cart is dangerous to decrement count
        if(removed.getLoadReference().getIsDangerous()){ 
            this.numberOfDangerCount--;
        }
        this.numberOfTrainCars--;
        return removed;
    }
    
    
   /**
    * Is the number of train cars on the train
    * @return 
    *   The number of train cars of the train
    */
    public int size(){
        return this.numberOfTrainCars;
    }
    
    
   /**
    * Getter method to get the total length of the train car
    * Completes in O(1) time.
    * 
    * @return 
    *   The total length of the train car
    */
    public double getLength(){
        return this.totalLength;
    }
    
   /**
    * Gets the value of the train car
    * Completes in O(1) time.
    * 
    * @return 
    *   The value of that certain train car
    */
    public double getValue(){
        return this.totalValue;
    }
    
   /**
    * Get the weight of that train car node
    * Completes in O(1) time.
    * 
    * @return 
    *   The weight that train car node
    */
    public double getWeight(){
        return this.totalWeight;
    }
    
     
    
   /**
    * Getter method to check if the train car node is dangerous
    * by number of danger counts. Completes in O(1) time.
    * @return 
    *   If number of dangerous cars is at least 1, then the train
    *   is dangerous. Otherwise return false if train has 0 dangerous
    *   count 
    */
    public boolean isDangerous(){
       return (this.numberOfDangerCount >= 1) ? true : false; 
    }
        
   /**
    * Purpose is to find the matching product in the train by name
    * and see if it exists or not
    * @param name 
    *    The name of the product to search for in the train
    */
    public void findProduct(String name){
        TrainCarNode nodePointer = head;
        String isDangerous = "";
        double weight = 0;
        double value = 0;
        int count = 0;
        
        while(nodePointer != null){
            //Assuming the product is case insensitive
            if(name.equalsIgnoreCase(nodePointer.getCar().getLoadReference().getProductName())){
                weight += nodePointer.getCar().getLoadReference().getWeightInTons();
                value += nodePointer.getCar().getLoadReference().getValueInDollars();
                count += 1;
                if (nodePointer.getCar().getLoadReference().getIsDangerous())
                    isDangerous = "YES";
                else
                    isDangerous = "NO";              
            }       
            nodePointer = nodePointer.getNext();         
        }
        if(count >= 1){
            System.out.println("\nThe following products were found on " + count + 
              " cars:\n");
            System.out.println("Name        Weight (t)   Value ($)     Dangerous");
            System.out.println("=====================================================");
            System.out.println(String.format("%-15s%6s%12s%15s", name, weight,
                twoDecimalPlaces.format(value), isDangerous));
        }
        else{
            System.out.println("\nNo record of " + name + " on board train.");
        }
    }
       
   /**
    * Displays the manifest of the entire train and it will update
    * when the train car is removed, new car is inserted, cursor is moved
    * forward or backwards and etc.
    * 
    */ 
    public void printManifest(){
        String status;
        int i = 1;
        TrainCarNode nodePointer = head;
        System.out.println("\nCAR:                                          LOAD:");
        System.out.println( "\tNum  Length (m)  Weight (t)        |          Name         Weight (t)    "
          + "Value ($)  Dangerous");
        System.out.println("==========================================="
          + "+==========================================================");  
        
        while(nodePointer != null){  
            if(cursor == nodePointer)
                status = "->";
            else
                status = "   ";
            //display an empty train car
            if(nodePointer.getCar().getLoadReference() == null){
                System.out.printf("%-3s%7s%12s%12s%11s%15s%16s%14s%11s", status, (i), 
                  nodePointer.getCar().getLengthInMeters(), nodePointer.getCar().getWeightInTons(),
                  " | ", "Empty" , 0.0, twoDecimalPlaces.format(0.00), "NO");
                System.out.println();
            }
            //display a dangerous train car   
            if(nodePointer.getCar().getLoadReference() != null && nodePointer.getCar().getLoadReference().getIsDangerous()) {
                System.out.printf("%-3s%7s%12s%12s%11s%15s%16s%14s%11s", status, (i), 
                  nodePointer.getCar().getLengthInMeters(), nodePointer.getCar().getWeightInTons(),
                  " | ", nodePointer.getCar().getLoadReference().getProductName(),
                  nodePointer.getCar().getLoadReference().getWeightInTons(),
                  twoDecimalPlaces.format(nodePointer.getCar().getLoadReference().getValueInDollars()),
                  "YES");
                System.out.println();
            } 
            //display a non-dangerous train car 
            if (nodePointer.getCar().getLoadReference() != null && !nodePointer.getCar().getLoadReference().getIsDangerous()){
                System.out.printf("%-3s%7s%12s%12s%11s%15s%16s%14s%11s", status, (i), 
                  nodePointer.getCar().getLengthInMeters(), nodePointer.getCar().getWeightInTons(),
                  " | ", nodePointer.getCar().getLoadReference().getProductName(),
                  nodePointer.getCar().getLoadReference().getWeightInTons(),
                  twoDecimalPlaces.format(nodePointer.getCar().getLoadReference().getValueInDollars()),
                  "NO");
                System.out.println();
            }
            nodePointer = nodePointer.getNext();
            i++;
        }
    }
    
   /**
    * Removes all the train cars in the entire train
    * that are dangerous
    * 
    * <dt><b>Postconditions:</b></dt>
    *    All the dangerous train cars are removed
    *    and the order is the same for the non-dangerous
    *    train cars
    * 
    */
    public void removeDangerousCars(){  
        TrainCarNode removeDangerous = head;
        int dangerCountRemoved = 0;
        while(removeDangerous != null){
            if (removeDangerous.getCar().getLoadReference().getIsDangerous() && this.numberOfDangerCount >= 1 && 
                removeDangerous.getCar().getLoadReference() != null){
                cursor = removeDangerous;
                //check the head
                if (cursor == head){      
                    head = head.getNext();
                    cursor = cursor.getNext();  
                    this.numberOfTrainCars--;
                    dangerCountRemoved++;
                    this.totalLength -= removeDangerous.getCar().getLengthInMeters();
                    this.totalWeight -= removeDangerous.getCar().getWeightInTons();
                    this.totalWeight -= removeDangerous.getCar().getLoadReference().getWeightInTons();
                    this.totalValue -= removeDangerous.getCar().getLoadReference().getValueInDollars();
                }  

                //to check the tail
                else if (cursor.getNext() == null){
                    cursor.getPrev().setNext(cursor.getNext());
                    cursor = cursor.getPrev();
                    this.numberOfTrainCars--;
                    dangerCountRemoved++;
                    this.totalLength -= removeDangerous.getCar().getLengthInMeters();
                    this.totalWeight -= removeDangerous.getCar().getWeightInTons();
                    this.totalWeight -= removeDangerous.getCar().getLoadReference().getWeightInTons();
                    this.totalValue -= removeDangerous.getCar().getLoadReference().getValueInDollars();
                }
                //to check the middle
                else if (cursor.getPrev() != null && cursor.getNext() != null){
                    cursor.getPrev().setNext(cursor.getNext());
                    cursor.getNext().setPrev(cursor.getPrev());
                    cursor = cursor.getNext();
                    this.numberOfTrainCars--;
                    dangerCountRemoved++;
                    this.totalLength -= removeDangerous.getCar().getLengthInMeters();
                    this.totalWeight -= removeDangerous.getCar().getWeightInTons();
                    this.totalWeight -= removeDangerous.getCar().getLoadReference().getWeightInTons();
                    this.totalValue -= removeDangerous.getCar().getLoadReference().getValueInDollars();
                }
            }
            removeDangerous = removeDangerous.getNext();
        }
        //to compute the updated number of dangerous cars to update to
        //correct display of entire train as being non-dangerous since
        //all train cars are removed
        numberOfDangerCountUpdate = this.numberOfDangerCount - dangerCountRemoved;
        //to reset the dangerousCountRemoved and numberOfDangerCount to 0
        if (this.numberOfDangerCount == dangerCountRemoved){
            dangerCountRemoved = 0;
            this.numberOfDangerCount = 0;
        }
        System.out.println("\nDangerous cars successfully removed from the train.");     
    }
    
    
   /**
    * Displays the entire train info which displays the
    * total number of train cars, total length, total
    * weight, total value and if it's dangerous or not
    * 
    * 
    */
    public void displayTrain(){
        TrainCarNode totalAddedLoadAtCurrentCursor = cursor;
        
        while(totalAddedLoadAtCurrentCursor != null){
            totalAddedLoadAtCurrentCursor = totalAddedLoadAtCurrentCursor.getNext();
        }
        //To display the non-dangerous train
        if (!isDangerous()){
            if (this.numberOfDangerCountUpdate == 0){
                System.out.println("Train: " + this.numberOfTrainCars + " cars, " + this.totalLength + " meters, " + 
                  oneDecimalPlacesDuplicate.format(this.totalWeight) + " tons, $" 
                  + twoDecimalPlaces.format(this.totalValue) + " value, not dangerous");
            }
        }
        //To display the dangerous train
        else if (isDangerous() || this.numberOfDangerCount >= 1)
            System.out.println("Train: " + this.numberOfTrainCars + " cars, " + this.totalLength + " meters, " + 
              oneDecimalPlacesDuplicate.format(this.totalWeight) + " tons, $" + twoDecimalPlaces.format(this.totalValue) 
              + " value, DANGEROUS");
    }
    
    /**
     * Should have get the string representation of 
     * displaying the entire train with total number of
     * train cars, total length, total weight, total value,
     * and if it's dangerous or not
     * However it has no use of purpose since the train
     * has been printed in displayTrain 
     * method above
     * 
     * @return 
     *   nothing since the train has already been displayed in 
     *   displayTrain method above
     *  
     */ 
    @Override
    public String toString(){
        return null;  
    }
}
