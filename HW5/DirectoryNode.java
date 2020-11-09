/**
 * A class where the actual DirectoryNode is implemented for the DirectoryTree
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 5
 * Recitation 1: Jian Xi Chen
 * 
 */
public class DirectoryNode {
    private String name;
    private DirectoryNode left;
    private DirectoryNode right;
    private DirectoryNode middle;
    private DirectoryNode leftOne;
    private DirectoryNode middleOne;
    private DirectoryNode rightOne;
    private DirectoryNode leftTwo;
    private DirectoryNode middleTwo;
    private DirectoryNode rightTwo;
    private DirectoryNode leftThree;
    private boolean isFile;
    private DirectoryNode parent;
    
    /**
     * The constructor with two parameters
     * @param name
     *   The name of the node in the tree
     * @param parent
     *   The parent of the node in the tree
     */
    public DirectoryNode(String name, DirectoryNode parent){
        this.name = name;
        left = null;
        right = null;
        middle = null;
        this.parent = parent;
    }
    
    /**
     * Getter method to getting the node reference to the left
     * @return 
     *   The node reference to the left
     */
    public DirectoryNode getLeftOne(){
        return leftOne;
    }
    
    
    /**
     * Getter method to getting the node reference to the middle
     * @return 
     *   The node reference to the middle
     */
    public DirectoryNode getMiddleOne(){
        return middleOne;
    }
    
    
    /**
     * Getter method to getting the node reference to the right
     * @return 
     *   The node reference to the right
     */
    public DirectoryNode getRightOne(){
        return rightOne;
    }
    

    
    /**
     * Getter method to getting the node reference to the left
     * @return 
     *   The node reference to the left
     */
    public DirectoryNode getLeftTwo(){
        return leftTwo;
    }
    
 
    
    /**
     * Getter method to getting the node reference to the middle
     * @return 
     *   The node reference to the middle
     */
    public DirectoryNode getMiddleTwo(){
        return middleTwo;
    }
    

    
    /**
     * Getter method to getting the node reference to the right
     * @return 
     *   The node reference to the right
     */
    public DirectoryNode getRightTwo(){
        return rightTwo;
    }
    

    
    /**
     * Getter method to getting the node reference to the left
     * @return 
     *   The node reference to the left
     */
    public DirectoryNode getLeftThree(){
        return leftThree;
    }
    

    
    
    /**
     * Getter method to getting the name of the node in the tree
     * @return 
     *   The name of the node in the tree
     */
    public String getName(){
        return name;
    }
    
    /**
     * Setter method to setting the name of the node in the tree
     * @param name 
     *   The name of the node in the tree
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Getter method to getting the parent node of the tree
     * @return 
     *   The parent node of the tree
     */
    public DirectoryNode getPrevParent(){
        return parent;
    }
    
    public void setPrevParent(DirectoryNode parent){
        this.parent = parent;
    }
    
    /**
     * Getter method to getting the node reference to left
     * @return 
     *   Node reference to the left
     */
    public DirectoryNode getLeft(){
        return left;
    }
    
    /**
     * Setter method to setting the node reference to the left
     * @param left 
     *   Node reference to the left
     */
    public void setLeft(DirectoryNode left){
        this.left = left;
    }
    
    /**
     * Getter method to getting the node reference to the right
     * @return 
     *   The node reference to the right
     */
    public DirectoryNode getRight(){
        return right;
    }
    
    /**
     * Setter method to setting the node reference to the right
     * @param right 
     *   Node reference to the right
     */
    public void setRight(DirectoryNode right){
        this.right = right;
    }
    
    /**
     * Getter method to getting the node reference to the middle
     * @return 
     *   Node reference to the middle
     */
    public DirectoryNode getMiddle(){
        return middle;
    }
    
    /**
     * Setter method to setting the node reference to the middle
     * @param middle 
     *   Node reference to the middle
     */
    public void setMiddle(DirectoryNode middle){
        this.middle = middle;
    }
    
    /**
     * Getter method to check if the node of the tree is a file
     * @return 
     *   True if it's a file but false if not a file
     */
    public boolean getIsFile(){
        return isFile;
    }
    
    /**
     * Setter method to check if the node of the tree is a file
     * @param isFile 
     *   Check if the node of the tree is a file or not
     */
    public void setIsFile(boolean isFile){
        this.isFile = isFile;
    }
    
    /**
     * Adds newChild directory to any of the open child positions of this node (left, middle, or right).
     * @param newChild
     *   The new node added to the binary tree which is a directory
     * @throws NotADirectoryException
     *   Thrown if the current node is a file, as files cannot contain DirectoryNode
     *   references (i.e. all files are leaves).
     * @throws FullDirectoryException 
     *   Thrown if all child references of this directory are occupied
     * @throws ExistingNameException
     *   Thrown if the directory name already exists
     */
    public void addChild(DirectoryNode newChild) throws NotADirectoryException, FullDirectoryException, ExistingNameException{
        if(newChild.getIsFile()){
            throw new NotADirectoryException ("This current node is a file");
        }
           
        if(left == null){
            left = newChild; 
        }
        else if(middle == null){
            if ((newChild.getName().equals(left.getName()))){
                throw new ExistingNameException("This directory name already exists");
            }
            middle = newChild;
        }
        else if(right == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName()))){
                throw new ExistingNameException("This directory name already exists");
            }
            right = newChild;  
        }
        else if(leftOne == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName()))){
                throw new ExistingNameException("This directory name already exists");
            }
            leftOne = newChild;  
        }
        else if(middleOne == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))) {
                throw new ExistingNameException("This directory name already exists");
            }
            middleOne = newChild;  
        }
        else if(rightOne == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName()))){
                throw new ExistingNameException("This directory name already exists");
            }
            rightOne = newChild; 
        }
        else if(leftTwo == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName())) || (newChild.getName().equals(rightOne.getName()))){
                throw new ExistingNameException("This directory name already exists");
            }
            leftTwo = newChild; 
        }
        else if(middleTwo == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName())) || (newChild.getName().equals(rightOne.getName()))
                || (newChild.getName().equals(leftTwo.getName()))){
                throw new ExistingNameException("This directory name already exists");
            }
            middleTwo = newChild; 
        }
        else if(rightTwo == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName())) || (newChild.getName().equals(rightOne.getName()))
                || (newChild.getName().equals(leftTwo.getName())) || (newChild.getName().equals(middleTwo.getName()))){
                throw new ExistingNameException("This directory name already exists");
            }
            rightTwo = newChild;
        }
        else if(leftThree == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName())) || (newChild.getName().equals(rightOne.getName()))
                || (newChild.getName().equals(leftTwo.getName())) || (newChild.getName().equals(middleTwo.getName()))
                || (newChild.getName().equals(rightTwo.getName()))){
                throw new ExistingNameException("This directory name already exists");
            }
            leftThree = newChild; 
        }
        else{
          throw new FullDirectoryException("All 10 children are Occupied");  
        }
    }
  
    /**
     * To search for the specific node in the binary tree by the indicated
     * name
     * @param name
     *   The name to search for
     * @param ptr
     *   The pointer which will reference that searched name for the pointer to
     *   then reference that the indicated name exist
     * @return 
     *   Nothing indicating that the name to be searched does not exist
     */
    public DirectoryNode searchNode(String name, DirectoryNode ptr){
        if(ptr != null){
            if(ptr.getName().equals(name)){
                return ptr;
            }
            else{
                DirectoryNode foundNode = searchNode(name, ptr.getLeft());
                if(foundNode == null){
                    foundNode = searchNode(name, ptr.getMiddle());
                }
                if(foundNode == null){
                    foundNode = searchNode(name, ptr.getRight());
                }
                if(foundNode == null){
                    foundNode = searchNode(name, ptr.getLeftOne());
                }
                if(foundNode == null){
                    foundNode = searchNode(name, ptr.getMiddleOne());
                }
                if(foundNode == null){
                    foundNode = searchNode(name, ptr.getRightOne());
                }
                if(foundNode == null){
                    foundNode = searchNode(name, ptr.getLeftTwo());
                }
                if(foundNode == null){
                    foundNode = searchNode(name, ptr.getMiddleTwo());
                }
                if(foundNode == null){
                    foundNode = searchNode(name, ptr.getRightTwo());
                }
                if(foundNode == null){
                    foundNode = searchNode(name, ptr.getLeftThree());
                }
                return foundNode;
            }
        }
        else{
            return null;
        }
    }
     
     
      
    /**
     * Adds newChild file to any of the open child positions of this node (left, middle, or right).
     * @param newChild
     *   The new node added to the binary tree which is a file
     * @throws FullDirectoryException 
     *   Thrown if all child references of this directory are occupied
     * @throws ExistingNameException
     *   Thrown if name already exists when adding file
     */
    public void addChildFile(DirectoryNode newChild) throws FullDirectoryException, ExistingNameException{  
        if(left == null){
            left = newChild; 
        }
        else if(middle == null){
            if ((newChild.getName().equals(left.getName()))){
                throw new ExistingNameException("This file name already exists");
            }
            middle = newChild;
        }
        else if(right == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName()))){
                throw new ExistingNameException("This file name already exists");
            }
            right = newChild;  
        }
        else if(leftOne == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName()))){
                throw new ExistingNameException("This file name already exists");
            }
            leftOne = newChild;  
        }
        else if(middleOne == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))) {
                throw new ExistingNameException("This directory name already exists");
            }
            middleOne = newChild;  
        }
        else if(rightOne == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName()))){
                throw new ExistingNameException("This file name already exists");
            }
            rightOne = newChild; 
        }
        else if(leftTwo == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName())) || (newChild.getName().equals(rightOne.getName()))){
                throw new ExistingNameException("This file name already exists");
            }
            leftTwo = newChild; 
        }
        else if(middleTwo == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName())) || (newChild.getName().equals(rightOne.getName()))
                || (newChild.getName().equals(leftTwo.getName()))){
                throw new ExistingNameException("This file name already exists");
            }
            middleTwo = newChild; 
        }
        else if(rightTwo == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName())) || (newChild.getName().equals(rightOne.getName()))
                || (newChild.getName().equals(leftTwo.getName())) || (newChild.getName().equals(middleTwo.getName()))){
                throw new ExistingNameException("This file name already exists");
            }
            rightTwo = newChild;
        }
        else if(leftThree == null){
            if ((newChild.getName().equals(left.getName())) || (newChild.getName().equals(middle.getName())) 
                || (newChild.getName().equals(right.getName())) || (newChild.getName().equals(leftOne.getName()))
                || (newChild.getName().equals(middleOne.getName())) || (newChild.getName().equals(rightOne.getName()))
                || (newChild.getName().equals(leftTwo.getName())) || (newChild.getName().equals(middleTwo.getName()))
                || (newChild.getName().equals(rightTwo.getName()))){
                throw new ExistingNameException("This file name already exists");
            }
            leftThree = newChild;
        }
        else{
            throw new FullDirectoryException("All 10 children are Occupied");  
        }
    }
}
