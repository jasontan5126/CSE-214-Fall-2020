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
public class TrainCar {
    private double lengthInMeters;
    private double weightInTons;
    private ProductLoad loadReference = null;
    
    /**
    * A default constructor for TrainCar in case the other constructor
    *  with the parameters is not used
    */
    public TrainCar(){
    }
    
    /**
     * A constructor for TrainCar with three specific inputs in parameter
     * 
     * @param lengthInMeters
     *   The length of the train car in meters
     * @param weightInTons
     *   The amount of weight for that train car
     * @param loadReference 
     *   The load reference of that train car
     */

    public TrainCar(double lengthInMeters, double weightInTons, ProductLoad loadReference){
        this.lengthInMeters = lengthInMeters;
        this.weightInTons = weightInTons;
        this.loadReference = loadReference;
    }
    /**
     * A constructor for TrainCar with three specific inputs in parameter
     * @param lengthInMeters
     *   The length of the train car in meters
     * @param weightInTons 
     *   The amount of weight for that train car
     */
    public TrainCar(double lengthInMeters, double weightInTons){
        this.lengthInMeters = lengthInMeters;
        this.weightInTons = weightInTons;
    }
    
    /**
     * Getter method to get the length of the train car in meters
     * 
     * @return
     *   The length of that specific train car in meters
     */   

    public double getLengthInMeters(){
        return this.lengthInMeters;
    }
    
    public void setLengthInMeters(double lengthInMeters){
        if (lengthInMeters < 0)
            throw new ArithmeticException("Enter a positive amount of dollars");
        else
            this.lengthInMeters = lengthInMeters; 
    }

    /**
     * Getter method to get the weight of the train car in tons
     * @return 
     *    The amount of weight in tons for that train car
     */
    public double getWeightInTons(){
        return this.weightInTons;
    }
    
    public void setWeightInTons(double weightInTons){
        if (weightInTons < 0)
            throw new ArithmeticException("Enter a positive amount of dollars");
        else
            this.weightInTons = weightInTons; 
    }

    /**
     * Getter method to get the load reference of that train car
     * @return 
     *    The load reference of that train car
     */
    public ProductLoad getLoadReference(){
        return this.loadReference;
    }

    /**
    * Setter method for setting the load reference from the argument
    * with the load reference from the reference type Object. 
    * 
    * @param loadReference 
    *   The load reference of that train car
    */
    public void setLoadReference(ProductLoad loadReference){
        this.loadReference = loadReference;
    }
    
    /**
     * Checks if the train car is empty
     * @return 
     *   The load reference to be empty since it's initialized to be null
     */
    public boolean isEmpty(){
        return loadReference == null;
    }  
}
