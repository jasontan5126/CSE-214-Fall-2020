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
public class ProductLoad {
    private String productName;
    private double weightInTons;
    private double valueInDollars;
    private boolean isDangerous;
    
   /**
    * A default constructor for ProductLoad in case the other constructor
    * with the parameters is not used
    */
    public ProductLoad(){
    }
    
   /**
    * Purpose is to create a specific product load with specified inputs
    * @param productName
    *    The name of the product
    * @param weightInTons
    *    The amount of weight for that specific product
    * @param valueInDollars
    *   The amount of value in dollars for that specific product
    * @param isDangerous 
    *   To check if the product is dangerous
    */
    public ProductLoad(String productName, double weightInTons, double valueInDollars, boolean isDangerous){
        this.productName = productName;
        this.weightInTons = weightInTons;
        this.valueInDollars = valueInDollars;
        this.isDangerous = isDangerous;
    }
    
    /**
    * Returns the String reference type for productNmae
    * Also a getter method for productName
    * @return 
    *    The name of the specific product
    */
    public String getProductName(){
        return this.productName;
    }
    
    /**
    * Setter method for setting the product name from the argument
    * with the course name from the reference type String. Additional
    * implementation is that the course name has to be 25 or less characters
    * for the course
    * @param productName 
    */
    
    public void setProductName(String productName){
        if (productName.length() > 25)
            throw new StringIndexOutOfBoundsException("Enter less than or equal to "
                + "25 characters");
        else
            this.productName = productName;
    }
    
    /**
    * Returns the double reference type for the amount of weight in tons
    * Also a getter method for weightInTons
    * @return 
    *    The amount of weight in tons for that product
    */
    public double getWeightInTons(){
        return this.weightInTons;
    }
    
    /**
    *  Setter method for setting the amount of weight in tons from the argument
    *  with the code from the reference type double.
    *  
    * @param weightInTons 
    *    A double primitive type that is the amount of weight 
    *    in tons
    * 
    * @exception ArithmeticException
    *     Indicates that the weight in tons is negative and that you must enter
    *     a positive number greater than 0
    * 
    */
    public void setWeightInTons(double weightInTons){
        if (weightInTons < 0)
            throw new ArithmeticException("Enter a positive number in tons");
        else
            this.weightInTons = weightInTons;
    }
    
    /**
    *  Returns the double reference type for the amount of value in dollars
    *  Also a getter method for valueInDollars
    *  @return 
    *     The amount of value in dollars
    */
    public double getValueInDollars(){
        return valueInDollars;
    }
    
   /**
    *  Setter method for setting the amount of value in dollars from the argument
    *  with the code from the reference type double.
    *  @param valueInDollars 
    *    The double primitive type which is the value in dollars
    * 
    *  @exception ArithmeticException
    *     Indicates that the value in dollars is negative and that you must enter
    *     a positive number greater than 0
    * 
    */
    public void setValueInDollars(double valueInDollars){
        if (valueInDollars < 0)
            throw new ArithmeticException("Enter a positive amount of dollars");
        else
            this.valueInDollars = valueInDollars; 
    }
    
    /**
    * Getter method to get if product is dangerous
    * 
    * @return 
    *    the boolean reference type of whether the specific
    *    product is dangerous
    */
    
    public boolean getIsDangerous(){
        return this.isDangerous;
    }
    
    /**
     * Setter method to setting the argument isDangerous with the reference type
     * this.isDangerous
     * 
     * @param isDangerous 
     *    boolean reference type which will check if the product is dangerous
     *
     */
    public void setIsDangerous(boolean isDangerous){
        this.isDangerous = isDangerous; 
    }    
}
