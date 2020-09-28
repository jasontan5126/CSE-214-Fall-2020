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
public class TrainCarNode {
    private TrainCarNode prev;
    private TrainCarNode next;
    private TrainCar car;
    
    /**
    *  A default constructor for TrainCarNode in case the other constructor
    *  with the parameters is not used
    */
    public TrainCarNode(){       
    }
    
    /**
     * A constructor for TrainCar with three specific inputs in parameter
     * @param car 
     *   The car reference of the TrainCar
     */
    public TrainCarNode(TrainCar car){
        this.car = car;
        this.prev = null;
        this.next = null;
    }
    
    /**
     * Getter method for getting the previous link to the other 
     * train car
     * @return 
     *   The previous link of the train car
     */
    public TrainCarNode getPrev(){
        return this.prev;
    }
    
    /**
     * Setter method for setting the link to the previous train car
     * @param prev
     *   Sets the previous link to a train car of the train
     */
    
    public void setPrev(TrainCarNode prev){
        this.prev = prev;
    }
    
    /**
     * Getter method for getting the link to next train car
     * @return 
     *   The link to the next train car node
     */
    public TrainCarNode getNext(){
        return this.next;
    }
    
    /**
     * Setter method for setting the link to the next train car node
     * @param next 
     *   Sets the next link to the train car of the train
     */
    public void setNext(TrainCarNode next){
        this.next = next;
    }
    
    /**
     * Getter method for getting the train car of the train
     * @return
     *    The train car node of the train
     */
    public TrainCar getCar(){
        return this.car;
    }
    
    /**
     * Setter method for setting the train car of the train 
     * @param car 
     *    The train car of the train
     */
    public void setCar(TrainCar car){
        this.car = car;
    }
}

