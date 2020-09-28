/**
 * Jason Tan
 * ID: Something
 * Homework 1
 * Recitation 1: Jian Xi Chen
 */
/**
 *
 * @author jason
 */


import java.util.Scanner;
import java.util.InputMismatchException;

public class PlannerManager{    
    /**
    * The Planner manager where the user enters
    * the command from the command table to perform
    * a specific function like add course, 
    * remove course, etc
    *
    * @param args 
    *   An array of sequence of character/strings passed
    *   to main function
    */
    public static void main(String [] args){
        Scanner stdin = new Scanner(System.in);
        String courseNameInput = "";
        String departmentInput = "";
        String selectionInput = "";
        byte sectionCodeInput = 0;
        int courseCodeInput = 0;
        int positionInput = 0;
        String courseInstructorInput = ""; 
        Planner planner1 = new Planner();
        Planner plannerCopy = new Planner();
                   
        while(!selectionInput.equalsIgnoreCase("Q")){
            System.out.println("\n(A) Add Course ");   
            System.out.println("(G) Get Course ");   
            System.out.println("(R) Remove Course ");  
            System.out.println("(P) Print Courses in Planner "); 
            System.out.println("(F) Filter by Department Code "); 
            System.out.println("(L) Look For Course "); 
            System.out.println("(S) Size ");  
            System.out.println("(B) Backup ");          
            System.out.println("(PB) Print Courses in Backup ");  
            System.out.println("(RB) Revert to Backup ");   
            System.out.println("(Q) Quit ");  
            System.out.println();
            System.out.print("\nEnter a selection: ");
            selectionInput = stdin.next();
            stdin.nextLine();
            
            if(selectionInput.equalsIgnoreCase("A")){
                try{ 
                    Course course = new Course(courseNameInput, departmentInput, 
                        courseCodeInput, sectionCodeInput, courseInstructorInput);
                    
                    System.out.print("\nEnter Course Name: ");
                    courseNameInput = stdin.nextLine();
                    course.setCourseName(courseNameInput);
                    System.out.print("Enter department: ");
                    departmentInput = stdin.nextLine().toUpperCase().trim();
                    course.setDepartment(departmentInput);
                    System.out.print("Enter course code: ");
                    courseCodeInput = stdin.nextInt();
                    course.setCode(courseCodeInput);
                    System.out.print("Enter course section: ");
                    sectionCodeInput = stdin.nextByte();
                    course.setSection(sectionCodeInput);
                    stdin.nextLine();
                    System.out.print("Enter instructor: ");
                    courseInstructorInput = stdin.nextLine();
                    course.setInstructor(courseInstructorInput);
                    System.out.print("Enter position: ");
                    positionInput = stdin.nextInt();
                 
                    //Checks whether the course does not exists in planner to add
                    //to the planner
                    if (!(planner1.existsDuplicate(course))){
                        planner1.addCourse(course, positionInput);
                        System.out.println("\n" + departmentInput + " " + courseCodeInput +
                          ".0" + sectionCodeInput + " successfully added to planner.");
                    } 
                    //if the same content exists, it's not added to planner
                    else if ((planner1.existsDuplicate(course)))
                        System.out.println("\n" + departmentInput + " " + courseCodeInput +
                          ".0" + sectionCodeInput + " is already in the planner.");
                }
                
                catch(InputMismatchException e){
                    System.out.println("\nEnter the inputs again correctly of their"
                      + " corresponding instance type");
                    stdin.nextLine();
                }  
                catch(IllegalArgumentException e){
                    System.out.println("\nEnter a valid position: "
                      + "1 ≤ position ≤ size() + 1");
                    stdin.nextLine();
                }
                catch(ArithmeticException e){
                    System.out.println("\nPlease enter a positive number/Code has to be "
                      + "a 3 digit number");
                    stdin.nextLine();
                }
                catch(ArrayIndexOutOfBoundsException  e){
                    System.out.println("\nEnter in the right index");
                    stdin.nextLine();
                }
                catch(StringIndexOutOfBoundsException e){
                    System.out.println("\nEnter correct number of characters for specific input");
                }
                catch(NullPointerException e){   
                }
                catch(FullPlannerException ex){
                    System.out.println("\nThe planner is full. Remove some courses "
                      + " to add more courses");
                    stdin.nextLine();
                }
            }       
            else if (selectionInput.equalsIgnoreCase("G")){
                try{
                    //gets the position in order to display the content of the course
                    //in a neatly formatted table with the specific position location
                    System.out.print("\nEnter position: ");
                    positionInput = stdin.nextInt();
                    Course course = planner1.getCourse(positionInput);
                    printCourseTable();
                    System.out.println(String.format("%-4s%-26s%-19s%-6s%-10s%-23s", positionInput, 
                      course.getCourseName(), course.getDepartment(), course.getCode(), 
                      "0"+course.getSection(), course.getInstructor()));  
                }
                catch (NullPointerException e){   
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    System.out.println("\nEnter correct position input");
                }
                catch (InputMismatchException ex){
                    System.out.println("\nEnter position input correctly");
                }
            }
            else if (selectionInput.equalsIgnoreCase("R")){
                Course course = new Course(courseNameInput, departmentInput, 
                   courseCodeInput, sectionCodeInput, courseInstructorInput);
                try{
                    System.out.print("\nEnter a position: ");
                    positionInput = stdin.nextInt();
                   
                    //if the course exists in the planner, removes it from the planner
                    //according to the specified position input
                    if (planner1.existsDuplicate(course)){
                        System.out.println("\n" + planner1.getCourse(positionInput).
                          getDepartment() + " " + planner1.getCourse(positionInput)
                          .getCode() + ".0" + planner1.getCourse(positionInput).getSection() + 
                          " has been successfully removed from the planner.");
                        planner1.removeCourse(positionInput);
                    }
                    //if the course doesn't exists in the planner, nothing is done
                    else{
                        System.out.println("\nNo course is in that position. "
                          + "Try again");
                    }
                }
                catch (IllegalArgumentException ex){
                    System.out.println("\nInput position Entered is invalid");
                    stdin.nextLine();
                }    
                catch (InputMismatchException ex){
                    System.out.println("\nEnter position input correctly");
                }
                catch (NullPointerException ex){ 
                }
            }
            else if (selectionInput.equalsIgnoreCase("P")){
                //prints all the courses in the planner which is in a table
                //shaped format
                try{
                    System.out.println("\nPlanner: ");
                    planner1.printAllCourses();
                }
                catch(NullPointerException e){    
                }               
            }
            else if (selectionInput.equalsIgnoreCase("F")){
                //Filters courses in that specific department based on the 
                //department input
                Course course = new Course();
                try{
                    System.out.print("\nEnter department Code: ");
                     departmentInput = stdin.nextLine();
                     course.setDepartment(departmentInput);
                     planner1.filter(planner1, departmentInput);
                }
                catch (NullPointerException ex){     
                }
                catch (InputMismatchException ex){
                    System.out.println("\nEnter department code correctly");
                }
            }         
            else if (selectionInput.equalsIgnoreCase("L")){
                //To look for the course in the planner and see where it's
                //located in the planner
                 Course search = new Course(courseNameInput, departmentInput, 
                    courseCodeInput,sectionCodeInput, courseInstructorInput);
                 try{
                     System.out.print("\nEnter Course Name: ");
                     courseNameInput = stdin.nextLine().trim();
                     search.setCourseName(courseNameInput);
                     System.out.print("Enter department: ");
                     departmentInput = stdin.nextLine().trim().toUpperCase();
                     search.setDepartment(departmentInput);
                     System.out.print("Enter course code: ");
                     courseCodeInput = stdin.nextInt();
                     search.setCode(courseCodeInput);
                     System.out.print("Enter course section: ");
                     sectionCodeInput = stdin.nextByte();
                     stdin.nextLine();
                     search.setSection(sectionCodeInput);
                     System.out.print("Enter instructor: ");
                     courseInstructorInput = stdin.nextLine().trim(); 
                     search.setInstructor(courseInstructorInput);
                     planner1.exists(search);
                 }
                 catch (InputMismatchException ex){
                    System.out.println("\nEnter inputs(s) correctly");
                 }
                 catch(ArithmeticException e){
                    System.out.println("\nPlease enter right number input");
                    stdin.nextLine();
                 }
                 catch(StringIndexOutOfBoundsException e){
                    System.out.println("\nEnter correct number of characters for specific input");
                 }
                 catch(NullPointerException e){   
                 }
            }           
            else if (selectionInput.equalsIgnoreCase("S")){    
                //to determine the number of courses in the planner
                System.out.println("\nThere are " + planner1.size() + 
                  " courses in your planner");               
            }         
            else if (selectionInput.equalsIgnoreCase("B")){
                //to create a backup copy of the planner
                boolean clonedPlanner = true;
                if (clonedPlanner == true){
                    plannerCopy = (Planner)planner1.clone();
                    System.out.println("\nCreated a backup of the current "
                      + "planner.");
                }
                else{
                    System.out.println("\nFAILED to created a "
                      + "backup of the current planner.");
                }   
            }
            
            //to print the content of the backup planner
            else if (selectionInput.equalsIgnoreCase("PB")){
                try{
                    System.out.println("\nPlanner (Backup): ");
                    plannerCopy.printAllCourses();
                }
                catch(NullPointerException e){
                }  
            }                                
            //to invert back to the original planner
            else if (selectionInput.equalsIgnoreCase("RB")){
                boolean revertedPlanner = true;
                if (revertedPlanner == true){
                   planner1 = (Planner)plannerCopy.clone();
                   System.out.println("\nPlanner successfully reverted "
                     + "to the backup copy.");
                }
                else
                    System.out.println("\nPlanner failed to successfully "
                      + "reverted to the backup copy.");
            }
            //to terminate the output console whenever the user feel like
            //he or she is done running the code
            else if (selectionInput.equalsIgnoreCase("Q")){
                System.out.println("\nProgram terminating successfully...");
                stdin.close();            
             }
            //if the letter command is invalid entered by the user
            else
                System.out.println("\nPlease enter a valid command letter");               
        } 
    }
    
    //this method is used inside the "G" letter command to display the top part
    //of the table
    public static void printCourseTable(){
        System.out.println("\nPlanner: ");
        System.out.printf("%-4s%-26s%-19s%-6s%-10s%-23s", "No.", "Course Name", "Department", 
          "Code", "Section", "Instructor");
        System.out.println("\n---------------------------------------------"
          + "------------------------------------------------------"); 
    }     
}                        
    
