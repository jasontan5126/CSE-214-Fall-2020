/**
 * Jason Tan
 * SBU ID: IDK
 * CSE 214 HW 3
 * Recitation 1: Jian Xi Chen
 * 
 */

/**
 *
 * @author jason
 */
public class Complexity {
    public int nPower;
    public int logPower;
    
    /**
     * A default constructor for the Complexity with no 
     * parameters
     */
    public Complexity(){
    }
    
    /**
     * The parameterized constructor for Complexity class
     * 
     * @param nPower
     *   The nPower in the big O notation
     * @param logPower 
     *   The logPower inside the big O notation
     */
    public Complexity(int nPower, int logPower) {
        this.nPower = nPower;
        this.logPower = logPower;
    }
	
    /**
     * Getter method get the nPower in bigO notation
     * @return 
     *    the nPower in the bigO notation
     */
    public int getNPower() {
	return this.nPower;
    }
        
    /**
     * Setter method to set the N power in the parameter to the nPower
     * as a reference variable
     * @param nPower 
     *   The nPower of the bigO notation
     *   
     */
    public void setNPower(int nPower) {
        this.nPower = nPower;
    }
    /**
     * Getter method to get the logPower of the BigO notation
     * @return 
     *   The log power of the BigO notation
     */
    public int getLogPower() {
	return this.logPower;
    }
    /**
     * Setter method for setting the log Power with the instance variable:
     * logPower
     * @param logPower 
     *    The logPower of the bigO notation
     */
    public void setLogPower(int logPower) {
	this.logPower = logPower;
    }
    
    /**
     * The method where the condition is checked on the value of nPower
     * and logPower to choose which bigO notation to print for block
     * complexity plus highest sub-complexity
     * @return 
     *   the printed bigO notation depending on the nPower and logPower value
     *   simultaneously
     */
    @Override
    public String toString() {
	String printO = "";
        if(nPower == 1 && logPower == 1){
            printO += "O(n * log(n))";
            return printO;
        }
        else if (nPower == 0 && logPower == 0){
            printO += "O(1)";
            return printO;
        }
        else if (nPower > 1 && logPower > 1){
            printO += "O(n^" +  nPower + " * log(n) ^" + logPower + ")";
            return printO;
        }
        else if (nPower == 0 && logPower == 1){
            printO += "O(log(n))";
            return printO;
        }
        else if (nPower == 1 && logPower == 0){
            printO += "O(n)";
            return printO;
        } 
        else if (nPower >= logPower){
            printO += "O(n^" + nPower + ")";
            return printO;
        }
        return printO;    
    }       
}
