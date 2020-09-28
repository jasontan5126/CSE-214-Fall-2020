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
public class Planner {
    private static final int MAX_COURSES = 50;
    private Course courseList [];
    private int courseSize = 0; 
   
    
    /**
    * Default constructor where the courseList is equal
    * to number of courses in array fro
    */
   public Planner (){
       //Notice that MAX_COURSE is decrement by 1 since to indicate
       //there are maximum of 50 course objects to store in array as
       //indexes in array start from 0 to 49
       this.courseList = new Course [MAX_COURSES - 1];
   }
   
   /**
    * Setter method for the course size
    * @param size 
    *   Determines the size of number of courses
    *   in the planner
    */
   public void size(int size){
       this.courseSize = size;
   }
   
   /**
    * Particularly the getter method for 
    * the number of courses in the planner
    * 
    * <dt><b>Preconditions:</b></dt>
    *   The planner has been instantiated
    * 
    * @return 
    *   The number of courses in the planner
    */
   public int size(){
       if (this instanceof Planner){
           return courseSize;
       }
       return courseSize;
   }
   
   /**
    * Adds a course at a specified position
    * 
    * @param newCourse
    *   The object type where the new course will be added
    * @param position
    *   the position of the specific course
    * 
    *  <dt><b>Preconditions:</b></dt>
    *     course object is instantiate and position is
    *     1 or greater and less than or equal to size()+1. 
    *     Plus the Planner is less than the the maximum 
    *     number of courses
    * 
    * <dt><b>Postconditions:</b></dt>
    *    The course object is listed at the correct 
    *    position of list. 
    * 
    * @throws FullPlannerException 
    *   The planner is full
    * @throws IllegalArgumentException
    *   The position entered is invalid
    */
   public void addCourse(Course newCourse, int position) throws FullPlannerException{
       //Condition where the position is invalid
       if (position < 1 || position > this.size() + 1)
           throw new IllegalArgumentException("\nInvalid position number");
       //Condition where the Planner is full
       else if (this.size() >= MAX_COURSES)
           throw new FullPlannerException("\nNo more room to add courses");
       else{
           int i = size();
           while (i >= position - 1){
               //Elements are shifted right 1 index so that
               //the new Course object can be added in that 
               //specific position
                courseList[i + 1] = courseList[i];
                i--;
           }
           //To add the course object in that specified position - 1
           //as indexes are from 0 to 49
           courseList[position - 1] = newCourse;
           courseSize++;
       }
   }
   
   /**
    * Calls the method addCourse above with two parameters
    * 
    * @param newCourse
    *   The course to be added to planner
    * @throws FullPlannerException 
    *   The planner is full
    */
   public void addCourse(Course newCourse) throws FullPlannerException{
       this.addCourse(newCourse, this.courseSize + 1);
   }
   
   /**
    * Removes a course at a specified position
    * 
    * 
    * @param position 
    *   The specified position where the course 
    *   will be removed
    * 
    * <dt><b>Preconditions:</b></dt>
    *     course object is instantiate and position is
    *     1 or greater and less than or equal to size()+1. 
    *     Plus the Planner is less than the the maximum 
    *     number of courses
    * 
    * <dt><b>Postconditions:</b></dt>
    *    The course object at the specified position is removed
    *    which shifts all the course objects left one position for
    *    those that are greater than the removed course position
    * 
    * @throws IllegalArgumentException
    *   The position number entered is invalid
    */
   public void removeCourse(int position){
       //Condition where the position is invalid
       if (position < 1 || position > this.size() + 1)
           throw new IllegalArgumentException("\nInvalid position");
       else{
           //when that position input is specified, that
           //course content is removed in that specific element
           courseList[position - 1] = null;
           for(int i = position; i < size(); i++){
               //all the course elements are decrement by one index
               //after that course in a specific position is removed
               courseList[i - 1] = courseList[i];
           }
           this.courseSize--;
       }
   }
           
       
/**
 * Gets the course at the specified position
 * 
 * @param position
 *   The position of where that specific course
 *   is at in the planner
 * 
 * <dt><b>Preconditions:</b></dt>
 *   course object is instantiate and position is
 *   1 or greater and less than or equal to size()+1. 
 *   Plus the Planner is less than the the maximum 
 *   number of courses
 * 
 * @return 
 *   The Course object at the specific index:
 *   position - 1 of the courseList array
 * 
 * @exception IllegalArgumentException
 *   position is not in the valid range
 */
   public Course getCourse(int position){
       if (position < 1 && position >= MAX_COURSES)
           throw new IllegalArgumentException("\nInvalid position number");
       else 
           return courseList[position - 1];
   }
   
   /**
    * Filters the courses of that specific department
    * to be displayed in the planner
    * 
    * @param planner
    *   For the specific planner object that 
    *   contains the list of courses
    * @param department 
    *   The courses that will be displayed only for 
    *   specified department
    * 
    * <dt><b>Preconditions:</b></dt>
    *   The planner object is instantiated
    * 
    * <dt><b>Postconditions:</b></dt>
    *   The table is neatly displayed with the courses filter in the planner
    *   by one specific department
    * 
    */
   public static void filter(Planner planner, String department){
       if (planner instanceof Planner){
           System.out.println();
           System.out.printf("%-4s%-26s%-19s%-6s%-10s%-23s", "No.", "Course Name", "Department", 
              "Code", "Section", "Instructor");
           System.out.println("\n---------------------------------------------"
             + "------------------------------------------------------");  
           int i = 0;
           while(i < planner.size()){
               if(planner.courseList[i].getDepartment().equals(department)){
                   System.out.println(String.format("%-4s%-26s%-19s%-6s%-10s%-23s", (i+1), 
                     planner.courseList[i].getCourseName(), planner.courseList[i].getDepartment(), 
                     planner.courseList[i].getCode(), "0" + planner.courseList[i].getSection(), 
                     planner.courseList[i].getInstructor()));
               }
               i++;
           }
       }
   }
   
   /**
    * Create the copy of the Planner with all the content
    * preserved from the original
    * 
    * <dt><b>Preconditions:</b></dt>
    *    The planner object is instantiated
    * 
    * @return 
    *   The copy of the planner object with all the content from original 
    *   planner object
    */
   @Override  
   public Object clone() {
       Planner plannercopy = new Planner();
       
       //To copy an array of course objects in the original planner to the
       //cloned planner
       if (plannercopy instanceof Planner){ 
           int i = 0;
           while(i < size()){
               try{
                   plannercopy.addCourse((Course)courseList[i].clone());
               }
               catch (FullPlannerException e){
                   System.out.println("\nThe planner is full");
               }
               i++;
           }
       }
       return plannercopy;
   }
   
   /**
    * Use to check if the course exists which is used
    * in the case of "Look for Course" in PlannerManager
    * file
    * 
    * @param course
    *    The course object to be check if existent
    * @return 
    *   true if the course object in parameter is found
    *   in the courseList array. Otherwise return false
    *   if the course doesn't exists in the array
    */
   public boolean existsDuplicate(Course course){
        int i = 0;
        while(i < size()){
            if (courseList[i].equals(course)){
                return true;
            }
            i++;
        }     
        return false;
    }
   
   /**
    * Checks if the specific course object exists in the
    * planner
    * 
    * @param course
    *   The specified course to check if it exists
    *   in the planner
    * 
    * <dt><b>Preconditions:</b></dt>
    *    The planner and Course object are instantiated
    * 
    * @return 
    *   true with the specified position of where that
    *   course is located. Otherwise returns false if 
    *   that course doesn't exist
    */
    public boolean exists(Course course){
        int i = 0;
        while(i < size()){
            if (courseList[i].equals(course)){
                System.out.println("\n" + course.getDepartment() + " " + course.getCode() 
                        + ".0" + course.getSection() + " is found in the "
                                + "planner at position " + (i + 1));
                return true;
            }
            i++;
        }
        System.out.println("\nThis course does not exist in your planner");     
        return false; 
    }
    
   
    /**
     * Prints all the courses in a neatly formatted
     * table where all the course number, course name,
     * department, code, section and instructor are 
     * listed with the specified position
     * 
     * <dt><b>Preconditions:</b></dt>
     *    The planner is instantiated
     * 
     * <dt><b>Postconditions:</b></dt>
     *    All the courses are displayed in a neatly formatted table
     */
    public void printAllCourses(){
        if (this instanceof Planner)
            System.out.printf("%-4s%-26s%-19s%-6s%-10s%-23s", "No.", "Course Name", "Department", 
              "Code", "Section", "Instructor");
            System.out.println("\n---------------------------------------------"
              + "------------------------------------------------------"); 
            int i = 0;
            while(i < size()){
                System.out.println(String.format("%-4s%-26s%-19s%-6s%-10s%-23s", (i+1), courseList[i].getCourseName(), 
                    courseList[i].getDepartment(), courseList[i].getCode(), "0" + courseList[i].getSection(), 
                    courseList[i].getInstructor())); 
                i++;
            }
    }
    
   
    /**
     * Should have get the string representation of 
     * Planner object to print the table with all courses
     * However it has no use of purpose since the courses
     * have been printed in the printAllCourses
     * method above
     * 
     * @return 
     *   nothing since the neatly formatted table with
     *   course info are already printed in the 
     *   printAllCourses method above
     */
    
    @Override
    public String toString(){
        return null;
    }
}
