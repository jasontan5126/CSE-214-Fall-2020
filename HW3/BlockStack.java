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

import java.util.ArrayList;

public class BlockStack {
    private ArrayList <CodeBlock> pythonCodeBlock = new ArrayList <CodeBlock>();
    
    /**
     * Default constructor for the BlockStack where pythonCodeBlock is
     * initialized to empty ArrayList of CodeBlock objects
     */
    public BlockStack(){
        pythonCodeBlock = new ArrayList <CodeBlock>();
    }
    
    /**
     * Constructor with one parameter to set the stack to the reference variable
     * pythonCodeBlock
     * @param stack 
     *   An ArrayList type with empty CodeBlock objects
     */
    public BlockStack(ArrayList stack){
        this.pythonCodeBlock = stack;
    }
    
    
    /**
     * The size of the stack for pythonCodeBlock
     * @return 
     *   The size of pythonCodeBlock stack
     */
    public int size(){
        return pythonCodeBlock.size();
    }
    
    /**
     * Check if the pythonCodeBlock stack is empty
     * @return 
     *   That the stack is empty for pythonCodeBlock
     */
    public boolean isEmpty(){   
        return pythonCodeBlock.isEmpty();
    }
    
    /**
     * Pop from the stack:pythonCodeBlock starting from the top
     * @return 
     *   The pythonCodeBlock where the element is removed from the stack
     */
    public CodeBlock pop()  {
        return pythonCodeBlock.remove(size() - 1);     
    }
    
    /**
     * The pythonCodeBlock object is pushed to the stack
     * from bottom of stack to the top of stack
     * @param code 
     *   The python code part to be pushed into the stack
     */
    public void push(CodeBlock code){
        pythonCodeBlock.add(code);          
    }
    
    /**
     * The top of the pythonCodeBlock 
     * @return 
     *    The top of the pythonCodeBlock
     */
    public CodeBlock peek(){
        return pythonCodeBlock.get(size() - 1);
    }
}
