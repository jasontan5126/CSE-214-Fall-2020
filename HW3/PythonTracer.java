/**
 * Jason Tan
 * SBU ID: 112319102
 * CSE 214 HW 3
 * Recitation 1: Jian Xi Chen
 * 
 */

/**
 *
 * @author jason
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class PythonTracer {
    public static final int SPACE_COUNT = 4;
    
    /**
     * This method will open the indicated file, trace through the code of the Python function
     * contained within the file, and output the details of the trace
     * and the overall complexity to the console
     * @param fileName
     *   The name of the file entered by user to input
     * @return
     *   The popped element from the stack 
     * @throws FileNotFoundException 
     *   The file name entered does not exist
     */
    public static Complexity traceFile(String fileName) throws FileNotFoundException{
        ArrayList <CodeBlock> pythonCodeBlock = new ArrayList <CodeBlock>();
        BlockStack pythonCodeBlock1 = new BlockStack(pythonCodeBlock);
        CodeBlock b = new CodeBlock();
        Complexity bl = new Complexity();       
        String line = null;       
        File file = new File(fileName);
	Scanner stdin = new Scanner(file); 
	String blockNum = "1";
        //to get and read each line of file
	while (stdin.hasNextLine()){ 
            line = stdin.nextLine(); 
            //makes sure that the line isn't empty and line doesn't start with
            // #
            if (!line.trim().isEmpty() && !line.trim().startsWith("#", 0)){ 
		int indents = line.indexOf(line.trim())/SPACE_COUNT; 
                while (indents < pythonCodeBlock1.size()){
                    //check each condition when indents is 0 and indents is
                    //not zero to be able to check for block complexity through
                    //comparison of logPower and nPower after exiting the block
                    if (indents == 0){
                        System.out.println("Leaving Block 1");
			stdin.close(); 
                        return new Complexity(pythonCodeBlock1.peek().getBlockComplexity().getNPower() + 
                            pythonCodeBlock1.peek().getHighestSubComplexity().getNPower(),
                            pythonCodeBlock1.peek().getBlockComplexity().getLogPower() + 
                            pythonCodeBlock1.peek().getHighestSubComplexity().getLogPower());
                    }
                    else{
			CodeBlock oldTop = pythonCodeBlock1.pop();
			blockNum = oldTop.getBlockNumber();
			Complexity oldTopComplexity = new Complexity(oldTop.getBlockComplexity().getNPower() 
                            + oldTop.getHighestSubComplexity().getNPower(),oldTop.getBlockComplexity().getLogPower() 
                            + oldTop.getHighestSubComplexity().getLogPower());
                        if(oldTopComplexity.getNPower() > pythonCodeBlock1.peek().getHighestSubComplexity().getNPower() ||
                          oldTopComplexity.getLogPower() > pythonCodeBlock1.peek().getHighestSubComplexity().getLogPower()){
                            pythonCodeBlock1.peek().setHighestSubComplexity(oldTopComplexity); 
                            System.out.println("Leaving block " + oldTop.getBlockNumber()
                              + ", updating block " + pythonCodeBlock1.peek().getBlockNumber() + ": ");
                            System.out.println("BLOCK " + pythonCodeBlock1.peek().getBlockNumber() + ":     " +
                              "block complexity = " + pythonCodeBlock1.peek().getBlockComplexity().toString()
			      + "        highest sub-complexity = " + 
                              pythonCodeBlock1.peek().getHighestSubComplexity().toString()+"\n");
			}
                        else{ 
                            System.out.println("Leaving block " + oldTop.getBlockNumber()
			      + ", nothing to update.");
                            System.out.println("BLOCK " + pythonCodeBlock1.peek().getBlockNumber() + ":     " +
                              "block complexity = " + pythonCodeBlock1.peek().getBlockComplexity().toString()
			      + "        highest sub-complexity = " + 
                              pythonCodeBlock1.peek().getHighestSubComplexity().toString() + "\n");
                        }
		    }
		}
                //checks if the line contains the keyword
                if (line.contains("def ") || line.contains("elif ") || 
                    line.contains("else ") || line.contains("for ") ||
                    line.contains("if ") || line.contains("while ")){
                    int size = 1;
		    String blockNum1 = "1";	 
                    if ((indents != blockNum.split("\\.").length-1)){
                        for(int i = 0 ; i < indents; i++){
                            blockNum1 = blockNum1 + ".1"; 
                        }
                    }
                    else if (line.indexOf(line.trim()) == SPACE_COUNT){ 
                        size = size + 1;
                        blockNum1 = blockNum1.concat("." + size);
                    }	
                    //Condition for for loop to check for order of complexity
                    if (line.contains("for")){
                        if (line.trim().split(" ")[3].equals("log_N:")){
                            Complexity forLoop = new Complexity (0, 1);
                            b = new CodeBlock(forLoop, " 'for':", blockNum1);
                            pythonCodeBlock1.push(b);
                            System.out.println("Entering block " + pythonCodeBlock1.peek().getBlockNumber() + " " 
                              + pythonCodeBlock1.peek().getName());
                            System.out.println("BLOCK " + blockNum1 + ":     " + "block complexity = " + 
                              pythonCodeBlock1.peek().getBlockComplexity().toString()+
                              "        highest sub-complexity = " + 
                              pythonCodeBlock1.peek().getHighestSubComplexity().toString()+"\n");    
                        }
                        else if (line.trim().split(" ")[3].equals("N:")){ 
                            Complexity for1 = new Complexity(1,0);
                            b = new CodeBlock(for1, " 'for':", blockNum1);
                            pythonCodeBlock1.push(b);
                            System.out.println("Entering block " + pythonCodeBlock1.peek().getBlockNumber() 
                              + " " + pythonCodeBlock1.peek().getName());
                            System.out.println("BLOCK " + blockNum1 + ":     " + "block complexity = " + 
                              pythonCodeBlock1.peek().getBlockComplexity().toString()+
                              "        highest sub-complexity = " + 
                              pythonCodeBlock1.peek().getHighestSubComplexity().toString()+"\n"); 
                        }
                        else{
                            b = new CodeBlock(bl, " 'for':", blockNum1);
                            pythonCodeBlock1.push(b);
                            System.out.println("Entering block " + pythonCodeBlock1.peek().getBlockNumber() 
                              + " " + pythonCodeBlock1.peek().getName());
                            System.out.println("BLOCK " + blockNum1 + ":     " + "block complexity = " + 
                              pythonCodeBlock1.peek().getBlockComplexity().toString()+
                              "        highest sub-complexity = " + 
                              pythonCodeBlock1.peek().getHighestSubComplexity().toString()+"\n");
                        }
                    }
                    //checks the condition for the while loop
                    else if (line.contains("while")){
                        b = new CodeBlock(bl, " 'while':" , blockNum1);
                        pythonCodeBlock1.push(b); 
                        pythonCodeBlock1.peek().setLoopVariable(line.trim().split(" ")[1]);
                        System.out.println("Entering block " + pythonCodeBlock1.peek().getBlockNumber() 
                              + " " + pythonCodeBlock1.peek().getName());
                        System.out.println("BLOCK " + blockNum1 + ":     " + "block complexity = " + 
                          pythonCodeBlock1.peek().getBlockComplexity().toString()+
                          "        highest sub-complexity = " + 
                          pythonCodeBlock1.peek().getHighestSubComplexity().toString()+"\n");
                    }
                    //checks the condition for the def
                    else if (line.contains("def")){
                        b = new CodeBlock(bl, " 'def' ", "1");
                        pythonCodeBlock1.push(b);
                        System.out.println("Entering block " + pythonCodeBlock1.peek().getBlockNumber() 
                              + " " + pythonCodeBlock1.peek().getName());
                        System.out.println("BLOCK " + blockNum1 + ":     " + "block complexity = " + 
                          pythonCodeBlock1.peek().getBlockComplexity().toString()+
                          "        highest sub-complexity = " + 
                          pythonCodeBlock1.peek().getHighestSubComplexity().toString()+"\n");
                    }
                    //checks the condition for elif
                    else if (line.contains("elif")){
                        b = new CodeBlock(bl, " 'elif' " ,blockNum1);
                        pythonCodeBlock1.push(b);
                        System.out.println("Entering block " + pythonCodeBlock1.peek().getBlockNumber() 
                          + " " + pythonCodeBlock1.peek().getName());
                        System.out.println("BLOCK " + blockNum1 + ":     " + "block complexity = " + 
                          pythonCodeBlock1.peek().getBlockComplexity().toString()+
                          "        highest sub-complexity = " + 
                          pythonCodeBlock1.peek().getHighestSubComplexity().toString()+"\n");  
                    }
                    //checks the condition for else
                    else if (line.contains("else")){
                        b = new CodeBlock(bl, " 'else' " ,blockNum1);
                        pythonCodeBlock1.push(b);
                        System.out.println("Entering block " + pythonCodeBlock1.peek().getBlockNumber() 
                          + " " + pythonCodeBlock1.peek().getName());
                        System.out.println("BLOCK " + blockNum1 + ":     " + "block complexity = " + 
                          pythonCodeBlock1.peek().getBlockComplexity().toString()+
                          "        highest sub-complexity = " + 
                          pythonCodeBlock1.peek().getHighestSubComplexity().toString()+"\n");  
                    }
                    //checks the condition for if statement
                    else if (line.contains("if")){
                        b = new CodeBlock(bl, " 'if' " ,blockNum1);
                        pythonCodeBlock1.push(b);
                        System.out.println("Entering block " + pythonCodeBlock1.peek().getBlockNumber() 
                          + " " + pythonCodeBlock1.peek().getName());
                        System.out.println("BLOCK " + blockNum1 + ":     " + "block complexity = " + 
                          pythonCodeBlock1.peek().getBlockComplexity().toString()+
                          "        highest sub-complexity = " + 
                          pythonCodeBlock1.peek().getHighestSubComplexity().toString()+"\n");  
                    }
		}
                //checks the condition for stack.top is a "while" block 
                //and line updates stack.top's loopVariable 
		else if (pythonCodeBlock1.peek().getLoopVariable() != null){
                    if(line.trim().split(" ")[0].equals(pythonCodeBlock1.peek().getLoopVariable())){
                        if (line.trim().split(" ")[1].equals("/=")){ 
                            bl = new Complexity(0,1);
                            pythonCodeBlock1.peek().setBlockComplexity(bl); 
                            System.out.println("Found update statement, updating block " + 
                              pythonCodeBlock1.peek().getBlockNumber() + ":");
                            System.out.println("BLOCK " + pythonCodeBlock1.peek().getBlockNumber() + ":    " 
                              + "block complexity = " + pythonCodeBlock1.peek().getBlockComplexity().toString() 
                              + "     highest sub-complexity = " 
                              + pythonCodeBlock1.peek().getHighestSubComplexity().toString()+ "\n");
                        }
                        else if (line.trim().split(" ")[1].equals("-=")) {
                            pythonCodeBlock1.peek().setBlockComplexity(new Complexity(1,0));                   
                            System.out.println("Found update statement, updating block " 
                              + pythonCodeBlock1.peek().getBlockNumber() + ":");
                            System.out.println("BLOCK "+pythonCodeBlock1.peek().getBlockNumber() 
                              + ":    " + "block complexity = " + 
                              pythonCodeBlock1.peek().getBlockComplexity().toString()+ "     highest sub-complexity = " 
                              + pythonCodeBlock1.peek().getHighestSubComplexity().toString()+ "\n");
                        }
                    }
                }
	    }
            else{
                continue;
            }
        }
	while (pythonCodeBlock1.size() > 1) {
            CodeBlock oldTop = pythonCodeBlock1.pop();
            Complexity oldTopComplexity = new Complexity(oldTop.getBlockComplexity().getNPower() 
                + oldTop.getHighestSubComplexity().getNPower(), oldTop.getBlockComplexity().getLogPower() 
                + oldTop.getHighestSubComplexity().getLogPower());
            if(oldTopComplexity.getNPower() > pythonCodeBlock1.peek().getHighestSubComplexity().getNPower() || 
               oldTopComplexity.getLogPower() > pythonCodeBlock1.peek().getHighestSubComplexity().getLogPower()){
		pythonCodeBlock1.peek().setHighestSubComplexity(oldTopComplexity); 
		System.out.println("Leaving block " + oldTop.getBlockNumber()
                  + ", updating block " + pythonCodeBlock1.peek().getBlockNumber() + ": ");
                System.out.println("BLOCK " + pythonCodeBlock1.peek().getBlockNumber() + ":     " + "block complexity = " + 
                  pythonCodeBlock1.peek().getBlockComplexity().toString() +
                  "        highest sub-complexity = " + 
                  pythonCodeBlock1.peek().getHighestSubComplexity().toString() + "\n");
            }
            else{
                System.out.println("Leaving block " + oldTop.getBlockNumber()
                  + ", nothing to update.");      
                System.out.println("BLOCK " + pythonCodeBlock1.peek().getBlockNumber() + ":     " + "block complexity = " + 
                  pythonCodeBlock1.peek().getBlockComplexity().toString() +
                  "        highest sub-complexity = " + 
                  pythonCodeBlock1.peek().getHighestSubComplexity().toString() + "\n");
            }			       
        }
        System.out.println("Leaving Block 1");        
	return pythonCodeBlock1.pop().getHighestSubComplexity();    
    }
    
  /**
    * This is the main program is where the user enters
    * the file name to compute the order of complexity from
    * the input file
    *
    * @param args 
    *   An array of sequence of character/strings passed
    *   to main function
    */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String enterFileName = "";
        
        while(!enterFileName.equals("quit")){
            try{
                System.out.print("Please enter a file name (or 'quit' to quit): ");
                enterFileName = stdin.nextLine().trim(); 
                System.out.println();
                if(enterFileName.equals("quit")){
                    System.out.println("Program terminating successfully...");
                    stdin.close();                
                }
                else {
                    System.out.println("\nOverall complexity of " + enterFileName.replace(".py", "") + ": " 
                      +  traceFile(enterFileName) + "\n");
                }
            }
            catch(FileNotFoundException ex){
                System.out.println("The file does not exist\n");
            }
        }
    }    
}