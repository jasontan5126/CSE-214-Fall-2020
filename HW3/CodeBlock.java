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
public class CodeBlock {
    public static final String[] BLOCK_TYPES = {"def", "for", "while", "if", "else", "elif"};
    public static final int DEF = 0, FOR = 1, WHILE = 2, IF = 3, ELIF = 4, ELSE = 5;
    private Complexity blockComplexity;
    private Complexity highestSubComplexity;
    private String name;
    private String loopVariable;
    private String blockNumber;
    Complexity default1 = new Complexity();
    /**
     * The default constructor in case other constructors aren't used
     */
    public CodeBlock(){
        this.blockComplexity = default1;
        this.highestSubComplexity = default1;
    }
    
    /**
     * The constructor that has four parameters
     * @param blockComplexity
     *    The complexity of that block of code
     * @param highestSubComplexity
     *    The highest sub complexity of the code
     * @param name
     *    The keyword name of the python code
     * @param loopVariable 
     *    The loop variable of the code used for the while loop
     */
    public CodeBlock(Complexity blockComplexity, Complexity highestSubComplexity, String name, String loopVariable){
        this.blockComplexity = blockComplexity;
	this.highestSubComplexity = highestSubComplexity;
        this.name = name;
        this.loopVariable = loopVariable;
    }
    
    /**
     * The constructor with three parameters
     * @param blockComplexity
     *   The block complexity of the code block
     * @param name
     *   The name of the keyword to be check from input file
     * @param blockNumber 
     *   The number block/ the nested block number which is concatenated by ".1"
     */
    public CodeBlock(Complexity blockComplexity, String name, String blockNumber){
        this.blockComplexity = blockComplexity;
        this.highestSubComplexity = default1;
        this.name = name;
        this.blockNumber = blockNumber;
    }
    
    /**
     * The constructor with two parameters
     * @param blockComplexity
     *   The block complexity of the code block
     * @param highestSubComplexity 
     *   The highest sub complexity of the code
     */
    public CodeBlock(Complexity blockComplexity, Complexity highestSubComplexity){
        this.blockComplexity = blockComplexity;
	this.highestSubComplexity = highestSubComplexity;
    }
	
    /**
     * The constructor with two parameters
     * @param blockComplexity
     *    The block complexity of the code block
     * @param name 
     *    The name of codeBlock to be executed
     */
    public CodeBlock(Complexity blockComplexity, String name) {
        this.blockComplexity = blockComplexity;
	this.highestSubComplexity = default1;
        this.name = name;
	}
    
    /**
     * Getter method to get the block number
     * @return 
     *   The block number for the certain block of code
     */
    public String getBlockNumber(){
        return this.blockNumber;
    }
    
    /**
     * Setter method for the block number with the block number
     * in reference variable
     * @param blockNumber 
     *   The block number for the certain block of code in python input
     *    
     */
    public void setBlockNumber(String blockNumber){
        this.blockNumber = blockNumber;
    }
	
    /**
    * Getter method to get the block complexity
    * @return 
    *   The block complexity of the code of python
    */
    public Complexity getBlockComplexity(){
	return blockComplexity;
    }
        
    /**
    * Setter method to set the block complexity
    * @param blockComplexity 
    *   The block complexity of the code of python
    */
    public void setBlockComplexity(Complexity blockComplexity) {
	this.blockComplexity = blockComplexity;
    }
	
    /**
    * Getter method to get the highest sub complexity
    * @return 
    *   The highest sub complexity
    */
    public Complexity getHighestSubComplexity() {
	return highestSubComplexity;
    }
        
    /**
    * Setter method to set the highest sub complexity
    * @param highestComplexity 
    *    The highest complexity of the python code or code block
    */
    public void setHighestSubComplexity(Complexity highestComplexity) {
	this.highestSubComplexity = highestComplexity;
    }
	
    /**
    * Getter method to get the name or keyword
    * @return 
    *   The name or keyword of the python code
    */
    public String getName() {
	return name;
    }
        
    /**
    * Setter method to set the name
    * @param name 
    *   The name of keyword of the python code
    */
    public void setName(String name) {
	this.name = name;
    }
	
  /**
    * Getter method of the loop variable
    * @return 
    *   The loop variable to be used only for while loop block
    */
    public String getLoopVariable() {
        return loopVariable;
    }
	
   /**
    * Setter method to set the loop variable
    * @param loopVariable       
    *   The loop variable to be used only for while loop block
    */
    public void setLoopVariable(String loopVariable) {
	this.loopVariable = loopVariable;
    }
}
