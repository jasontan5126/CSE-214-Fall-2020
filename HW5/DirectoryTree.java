/**
 * A class where a 3 child tree of the DirectoryNodes class are implemented
 * to imitate the Linux Server command
 * @author 
 *   Jason Tan, SBU ID: N/A
 * 
 * CSE 214 HW 5
 * Recitation 1: Jian Xi Chen
 * 
 */

public class DirectoryTree {
    private DirectoryNode root;
    private DirectoryNode cursor;
   
    /**
     * A default constructor for DirectoryTree class
     */
    public DirectoryTree(){
        root = new DirectoryNode("root", cursor);
        cursor = root;   
    }
    
    
   /**
    * To move the cursor to the root of the tree
    * 
    * <dt><b>Preconditions:</b></dt>
    *     None
    * 
    * <dt><b>Postconditions:</b></dt>
    *    The cursor references the root node of the tree
    */
    public void resetCursor(){
        cursor = root;
    }
   
    
    /**
     * To move the cursor to the directory with the indicated name
     * @param name
     *   The directory name where the cursor will be moved to 
     * <dt><b>Preconditions:</b></dt>
     *     'name' references the valid directory
     * 
     * <dt><b>Postconditions:</b></dt>
     *    The cursor now references the directory with the name
     * 
     * @throws NotADirectoryException
     *   Thrown if the node with the indicated name is a file, 
     *   as files cannot be selected by the cursor, or cannot be found.
     * @throws NonExistNameException
     *   Thrown if the directory tree does not have the name that is to
     *   be searched
     */
    public void changeDirectory(String name) throws NotADirectoryException, NonExistNameException{ 
        if(findNameOne(name) == false){
            throw new NonExistNameException();
        }   
        
        if(cursor.getLeft() != null && cursor.getLeft().getName().equals(name)){
            cursor = cursor.getLeft();   
        }
        else if (cursor.getLeft() != null && cursor.getLeft().getIsFile() == true){
            throw new NotADirectoryException ("ERROR: Cannot change directory into a file."); 
        }  
        
        if(cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name)){
            cursor = cursor.getMiddle();
        }
        else if (cursor.getMiddle() != null && cursor.getMiddle().getIsFile() == true){
            throw new NotADirectoryException ("ERROR: Cannot change directory into a file."); 
        }    
        
        if (cursor.getRight() != null && cursor.getRight().getName().equals(name)){
            cursor = cursor.getRight();
        }
        else if(cursor.getRight() != null && cursor.getRight().getIsFile() == true){
            throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
        }  
        
        if (cursor.getLeftOne()!= null && cursor.getLeftOne().getName().equals(name)){
            cursor = cursor.getLeftOne();
        }
        else if(cursor.getLeftOne() != null && cursor.getLeftOne().getIsFile() == true){
            throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
        }  
        
        if (cursor.getMiddleOne()!= null && cursor.getMiddleOne().getName().equals(name)){
            cursor = cursor.getMiddleOne();
        }
        else if(cursor.getMiddleOne() != null && cursor.getMiddleOne().getIsFile() == true){
            throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
        } 
        
        if (cursor.getRightOne()!= null && cursor.getRightOne().getName().equals(name)){
            cursor = cursor.getRightOne();
        }
        else if(cursor.getRightOne() != null && cursor.getRightOne().getIsFile() == true){
            throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
        } 
        
        if (cursor.getLeftTwo()!= null && cursor.getLeftTwo().getName().equals(name)){
            cursor = cursor.getLeftTwo();
        }
        else if(cursor.getLeftTwo() != null && cursor.getLeftTwo().getIsFile() == true){
            throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
        }  
        
        if (cursor.getMiddleTwo()!= null && cursor.getMiddleTwo().getName().equals(name)){
            cursor = cursor.getMiddleTwo();
        }
        else if(cursor.getMiddleTwo() != null && cursor.getMiddleTwo().getIsFile() == true){
            throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
        } 
        
        if (cursor.getRightTwo()!= null && cursor.getRightTwo().getName().equals(name)){
            cursor = cursor.getRightTwo();
        }
        else if(cursor.getRightTwo() != null && cursor.getRightTwo().getIsFile() == true){
            throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
        } 
        
        if (cursor.getLeftThree()!= null && cursor.getLeftThree().getName().equals(name)){
            cursor = cursor.getLeftThree();
        }
        else if(cursor.getLeftThree() != null && cursor.getLeftThree().getIsFile() == true){
            throw new NotADirectoryException("ERROR: Cannot change directory into a file."); 
        } 
       
        
    }
    
    /**
     * Returns the path of directory names from the root node to cursor
     * <dt><b>Preconditions:</b></dt>
     *     None
     * 
     * <dt><b>Postconditions:</b></dt>
     *    The cursor stays at same DirectoryNode
     * @return 
     *   String of path of directory names from the root node to cursor
     */
    public String presentWorkingDirectory(){
        if(root == null){
            return null;
        }
        else{
            DirectoryNode ptr = cursor;
            String pointerDirectory = ptr.getName();
            while(ptr.getPrevParent() != null) {
		pointerDirectory = ptr.getPrevParent().getName() + "/" + pointerDirectory;
		ptr = ptr.getPrevParent();
            }
            return pointerDirectory;  
        }
    }
    
    /**
     * To list of space separate list of names of all the child directories
     * or files
     * <dt><b>Preconditions:</b></dt>
     *     None
     * 
     * <dt><b>Postconditions:</b></dt>
     *    The cursor stays at the same DirectoryNode
     * @return 
     *   A list of space separate list of names of all the child directories
     *   or files
     */
    public String listDirectory(){
        String listDirectory = "";
        if(cursor.getLeft() != null) {
           listDirectory += cursor.getLeft().getName() + " ";
        }
        if(cursor.getMiddle() != null){
            listDirectory += cursor.getMiddle().getName() + " ";
        }
	if(cursor.getRight() != null){
            listDirectory += cursor.getRight().getName() + " "; 
        }
        
        if(cursor.getLeftOne() != null) {
           listDirectory += cursor.getLeftOne().getName() + " ";
        }
        if(cursor.getMiddleOne() != null){
            listDirectory += cursor.getMiddleOne().getName() + " ";
        }
	if(cursor.getRightOne() != null){
            listDirectory += cursor.getRightOne().getName() + " "; 
        }
        
        if(cursor.getLeftTwo() != null) {
           listDirectory += cursor.getLeftTwo().getName() + " ";
        }
        if(cursor.getMiddleTwo() != null){
            listDirectory += cursor.getMiddleTwo().getName() + " ";
        }
	if(cursor.getRightTwo() != null){
            listDirectory += cursor.getRightTwo().getName() + " "; 
        }
        
        if(cursor.getLeftThree() != null) {
           listDirectory += cursor.getLeftThree().getName() + " ";
        }
        return listDirectory;   
    }
    
    /**
     * Prints a nested list of names of the nodes in directory tree starting
     * from cursor
     * 
     * <dt><b>Preconditions:</b></dt>
     *     None
     * 
     * <dt><b>Postconditions:</b></dt>
     *    The cursor stays at the same DirectoryNode
     */
    public void printDirectoryTree(){
        printDirectoryTreeDuplicate(0, cursor);
    }
    
    /**
     * Helps mainly to prints a nested list of names of the nodes 
     * in directory tree starting from cursor
     * @param depth
     *   The depth of the directory tree
     * @param ptr 
     *   The pointer at the DirectoryNode
     * 
     * <dt><b>Preconditions:</b></dt>
     *     None
     * 
     * <dt><b>Postconditions:</b></dt>
     *    The cursor stays at the same DirectoryNode
     * 
     */
    public void printDirectoryTreeDuplicate(int depth, DirectoryNode ptr){
        String spaces = "";
        String output = ""; 
        int i = 0;
        while(i < depth){
            spaces += "    ";
            i++;
        }
     
        if(!ptr.getIsFile())
            output += spaces + "|- " + ptr.getName();
        else if(ptr.getIsFile()){
            output += spaces + "- " + ptr.getName();
        }
        
        System.out.println(output);
        if (ptr.getLeft() != null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getLeft());
        if (ptr.getMiddle()!= null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getMiddle());
        if (ptr.getRight() != null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getRight());
        
        if (ptr.getLeftOne() != null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getLeftOne());
        if (ptr.getMiddleOne()!= null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getMiddleOne());
        if (ptr.getRightOne() != null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getRightOne());
        
        if (ptr.getLeftTwo() != null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getLeftTwo());
        if (ptr.getMiddleTwo()!= null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getMiddleTwo());
        if (ptr.getRightTwo() != null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getRightTwo());
        
        if (ptr.getLeftThree() != null)
            printDirectoryTreeDuplicate(depth + 1, ptr.getLeftThree());
        
    }
    
    /**
     * Helps partially to prints a nested list of names of the nodes in directory 
     * tree starting from cursor
     * @param ptr
     *   The pointer at the DirectoryNode
     * <dt><b>Preconditions:</b></dt>
     *     None
     * 
     * <dt><b>Postconditions:</b></dt>
     *    The cursor stays at the same DirectoryNode
     * 
     * @return 
     *   zero as the default value
     * 
     */
    public int printDirectoryTreeDuplicateTwo(DirectoryNode ptr){
        if(root == null)
            return 0;
        else{
           int leftDepth = 0;
           printDirectoryTreeDuplicate(leftDepth, cursor);
           int middleDepth = 0;
           printDirectoryTreeDuplicate(middleDepth, cursor);
           int rightDepth = 0;
           printDirectoryTreeDuplicate(rightDepth, cursor);
           
           int leftDepth1 = 0;
           printDirectoryTreeDuplicate(leftDepth1, cursor);
           int middleDepth1 = 0;
           printDirectoryTreeDuplicate(middleDepth1, cursor);
           int rightDepth1 = 0;
           printDirectoryTreeDuplicate(rightDepth1, cursor);
           
           int leftDepth2 = 0;
           printDirectoryTreeDuplicate(leftDepth2, cursor);
           int middleDepth2 = 0;
           printDirectoryTreeDuplicate(middleDepth2, cursor);
           int rightDepth2 = 0;
           printDirectoryTreeDuplicate(rightDepth2, cursor);
           int leftDepth3 = 0;
           printDirectoryTreeDuplicate(leftDepth3, cursor);         
        }
        return 0;
    }


    /**
     * Creates a directory with the indicated name and adds to the 
     * children of cursor node
     * @param name
     *   The name of the directory that is to be added
     * <dt><b>Preconditions:</b></dt>
     *     'name' is legal with no spaces or forward slashes
     * 
     * <dt><b>Postconditions:</b></dt>
     *    New DirectoryNode is added to the children of the cursor or
     *    exception is thrown
     * @throws IllegalArgumentException
     *   Thrown if the 'name' argument is invalid.
     * @throws FullDirectoryException 
     *   Thrown if all child references of this directory are occupied.
     */
    public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException{
        DirectoryNode newDirectory = new DirectoryNode(name, cursor);
        if(name.contains("/")){
                throw new IllegalArgumentException("The directory name is invalid");
        }
        try{
            cursor.addChild(newDirectory);  
        }
        catch(NotADirectoryException e){
            System.out.println("This current node is a file");
        }
        catch(FullDirectoryException e){
            System.out.println("The directory is full");
        }
        catch(ExistingNameException e){
            System.out.println("The directory name being added already exists");
        }
    }
    
    /**
     * Creates a file with the indicated name and adds to children of the cursor oran
     * exception is thrown
     * @param name
     *   Name of the file to add
     * @throws IllegalArgumentException
     *   If the name argument is invalid
     * @throws FullDirectoryException 
     *   If all the child references are occupied
     */
    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException{
        DirectoryNode newFile = new DirectoryNode(name, cursor);
        newFile.setIsFile(true);
        if(name.contains("/")){
            throw new IllegalArgumentException("The directory name is invalid");
        }
        try{
            cursor.addChildFile(newFile);
        }
        catch(FullDirectoryException e){
            System.out.println("ERROR: Present directory is full.");
        }         
        catch(ExistingNameException e){
            System.out.println("The file name being added already exists");
        }
    }
    
    /**
     * Checks if the name does exist with returning boolean type
     * @param name
     *   The name to search for
     * @return 
     *   false as the default return type if name does not exist
     */
    public boolean findNameOne(String name){
        DirectoryNode ptrCheck = cursor.searchNode(name, root);
        DirectoryNode ptr = cursor.searchNode(name, root);
        if(ptrCheck == null){
            return false;
        }
        String pointerDirectory = ptr.getName();
        if(root == null){
            return false;
        }     
        else{
            while(ptr.getPrevParent() != null) {
		pointerDirectory = ptr.getPrevParent().getName() + "/" + pointerDirectory;
                return true;
            }  
        }
        return false;
    }
    
    /**
     * To search for the directory name in the binary tree
     * @param name
     *   The name of the node to be searched
     * @return 
     *   The name of the directory node
     */
    public String findName(String name){
        DirectoryNode ptrCheck = cursor.searchNode(name, root);
        DirectoryNode ptr = cursor.searchNode(name, root);
        if(ptrCheck == null){
            System.out.println("ERROR: No such file exits.");
            return "ERROR: No such file exits.";
        }
        String pointerDirectory = ptr.getName();
        if(root == null){
            return null;
        }     
        else{
            while(ptr.getPrevParent() != null) {
		pointerDirectory = ptr.getPrevParent().getName() + "/" + pointerDirectory;
                
		ptr = ptr.getPrevParent();
            }  
        }
        return pointerDirectory;
    }
 
    /**
     * To find the path separate by node names and print the path
     * @param name 
     *   The name of the path to search
     */
    public void findPath(String name) {
	if(findName(name.substring(name.lastIndexOf("/") + 1)).contains(name)){
            cursor = cursor.searchNode(name.substring(name.lastIndexOf("/") + 1), root);
            System.out.println(findName(name.substring(name.lastIndexOf("/") + 1)));
        }
    }
    
    /**
     * Duplicate to find the path separate by node names
     * @param name 
     *   The name of the path to search
     */  
    public void findPathDuplicate(String name) {
	if(findName(name.substring(name.lastIndexOf("/") + 1)).contains(name)){
            cursor = cursor.searchNode(name.substring(name.lastIndexOf("/") + 1), root);
        }
    }
 
    /**
     * Move the cursor up to the parent directory
     * 
     * <dt><b>Preconditions:</b></dt>
     *     None
     * 
     * <dt><b>Postconditions:</b></dt>
     *    If cursor is at root, display error message stating cursor
     *    is at root. Otherwise move cursor up to parent directory
     * 
     */
    public void cursorBackward(){
        if(cursor == root){
            System.out.println("ERROR: Already at root directory.");
        }
        else if (cursor.getPrevParent() != null){
            cursor = cursor.getPrevParent();
        }
    }  
}
