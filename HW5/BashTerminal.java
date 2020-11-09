import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * A class where the user is expected to execute the BashTerminal by entering
 * the valid commands
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 5
 * Recitation 1: Jian Xi Chen
 * 
 */
public class BashTerminal {
    /**
    * The BashTerminal is what enable the user to enter
    * the commands to runs a program which builds a DirectoryTree 
    * using the commands that exist.
    *
    * @param args 
    *   An array of sequence of character/strings passed
    *   to main function
    */
    
    
    public static void main(String[] args){
        /*
        Extra Credit functions implemented:
        File system can support up to 10 DirectoryNodes.
        find {name}
        cd ..
        cd {path}
        */      
        Scanner stdin = new Scanner(System.in);
        DirectoryTree testBashTerminal = new DirectoryTree();
        String commandInput = "";
        String splitSpaces[];
        System.out.println("Starting Bash Terminal.");
        while(!commandInput.equals("exit")){
            try{
                System.out.print("[jastan@MAKE_COMPUTER_SCIENCE_GREAT_AGAIN_2.0!!!]: $ ");
                splitSpaces = stdin.nextLine().split(" ");
                commandInput = splitSpaces[0].trim();
                if(commandInput.equals("pwd")){
                    System.out.println(testBashTerminal.presentWorkingDirectory());
                }
                else if(splitSpaces[0].equals("ls")){
                    if(splitSpaces.length == 2 && splitSpaces[1].equals("-R")){
                        System.out.println();
                        testBashTerminal.printDirectoryTree();
                        System.out.println();
                    }
                    else if(splitSpaces[0].equals("ls") && splitSpaces.length == 1){
                        System.out.println(testBashTerminal.listDirectory());
                    }
                }
                //cd /
                else if(splitSpaces[0].equals("cd") && splitSpaces[1].equals("/")){
                    testBashTerminal.resetCursor();
                }
                //cd ..
                else if(splitSpaces[0].equals("cd") && commandInput.length() == 2 && splitSpaces[1].equals("..")){
                    testBashTerminal.cursorBackward();
                }
                //cd directory name
                else if(splitSpaces[0].equals("cd") && !splitSpaces[1].contains("/")){
                    try{
                        testBashTerminal.changeDirectory(splitSpaces[1]);
                    }
                    catch(NotADirectoryException e){
                        System.out.println("ERROR: Cannot change directory into a file.");
                    }
                    catch(NonExistNameException e){
                        System.out.println("ERROR: No such directory named '" + splitSpaces[1] + "'");
                    }
                }
                //cd {path}
                else if(splitSpaces[0].equals("cd") && commandInput.length() >= 2 && splitSpaces[1].contains("/")){
                    testBashTerminal.findPathDuplicate(splitSpaces[1]);
                }
                else if(commandInput.equals("mkdir")){
                    if(splitSpaces.length > 2) { 
			throw new IllegalArgumentException("The directory name is invalid");
                    }
                    try{
                        testBashTerminal.makeDirectory(splitSpaces[1]);
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("The directory name is invalid");
                    }
                    catch(FullDirectoryException e){
                        System.out.println("ERROR: Present directory is full.");
                    }
                }
                else if(commandInput.equals("touch")){
                    if(splitSpaces.length > 2) { 
			throw new IllegalArgumentException("The directory name is invalid");
                    }
                    try{
                        testBashTerminal.makeFile(splitSpaces[1]);
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("The file name is invalid");
                    }
                    catch(FullDirectoryException e){
                        System.out.println("The directory is full");
                    }
                }
                else if (splitSpaces[0].equals("find") && splitSpaces[1].contains(splitSpaces[1])){
                    testBashTerminal.findPath(splitSpaces[1]);
                }
                else if (commandInput.equals("exit")){
                    System.out.println("Program terminating successfully");
                    stdin.close();
                    System.exit(0);
                }  
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a valid command");
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid command");
            }
            catch(IllegalArgumentException e){
                System.out.println("The directory name is invalid");
            }
        }       
    }
}