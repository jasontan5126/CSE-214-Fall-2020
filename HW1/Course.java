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

public class Course {
    private String courseName;
    private String department;
    private int code;
    private byte section;
    private String instructor;
 
    /**
     * Just a default constructor in case the constructor 
     * with arguments below is not used
     */
    public Course(){    
    }
    
    /**
    * Purpose is to create a specific course with specified inputs
    * 
    * @param courseName
    *   A string type which is the name of the specific course
    * @param department
    *   A string type which is the name of the department
    * @param code
    *   A integer type which is the course code for the specific course
    * @param section
    *   A byte type which is the section number of the course
    * @param instructor 
    *   A String type which has the name of the instructor
    */
    public Course(String courseName, String department, int code, byte section, String instructor){
        this.courseName = courseName;
        this.department = department;
        this.code = code;
        this.section = section;
        this.instructor = instructor;
    }
    
    /**
    * Returns the String reference type for courseName
    * Also a getter method for CourseNmae
    * 
    * @return 
    *    The name of the course
    */
    public String getCourseName(){
        return courseName;
    }
    
    /**
    * Setter method for setting the course name from the argument
    * with the course name from the reference type String. Additional
    * implementation is that the course name has to be 25 or less characters
    * for the course
    * 
    * @param courseName 
    *    A string type that is the course name of the specific course
    * 
    * @exception StringIndexOutOfBoundsException
    *     Indicates that you enter the courseName greater than 25 characters
    */
    
    public void setCourseName(String courseName){
        if (courseName.length() > 25)
            throw new StringIndexOutOfBoundsException("Enter less than or equal to "
                    + "25 characters");
        else
            this.courseName = courseName;
    }
    
    
    /**
    * Returns the String reference type for department
    * Also a getter method for getting the department
    * 
    * 
    * @return 
    *    The name of the department
    */
    public String getDepartment(){
        return department;
    }
    
    /**
    * Setter method for setting the department from the argument
    * with the department from the reference type String. Additional 
    * implementation is that the departmentInput must be 3 characters 
    * since we see the sample runs show most department inputs are 3 
    * characters
    * 
    * @param department 
    *    A string type that is the department of the specific course
    * 
    * @exception StringIndexOutOfBoundsException
    *     Indicates that the department must be entered exactly three characters
    */
    public void setDepartment(String department){
        if (department.length() > 3 || department.length() < 3)
            throw new StringIndexOutOfBoundsException ("Enter 3 characters for department");
        else
            this.department = department;
    }
    
    /**
    * Returns the code of the specific course
    * 
    * 
    * @return 
    *    The course code of the course
    * 
    */
    public int getCode() {
        return code;
    }
    
    /**
    * Setter method for setting the code from the argument
    * with the code from the reference type integer.
    * Additional implementation is that the code has to be three digits 
    * and it cannot be negative
    * 
    * @param code 
    *    An integer primitive type that is the code of the 
    *    specific course
    * 
    * @exception ArithmeticException
    *     Indicates that the code is negative and that you must enter
    *     a positive number grater than 0
    * 
    * @exception ArithmeticException
    *     Indicates that code has to be 3 digit
    */
    public void setCode(int code) throws ArithmeticException{
        if (code <= 0 )
            throw new ArithmeticException ("Enter positive value for code");
        else if (code < 100 || code > 1000)
            throw new ArithmeticException ("Enter three digit code");
        else
            this.code = code;
    }
    
    /**
    * Getter method that returns the section
    * of the specific course
    * 
    * 
    * @return 
    *    The specific section of the course
    * 
    */
    public byte getSection(){
            return section;
    }
    
    /**
    * Setter method for setting the code from the argument
    * with the code from the reference type integer. Section
    * input is invalid if negative or if greater than or equal
    * to 100
    * 
    * @param section 
    *    A byte primitive type that is the section of the 
    *    specific course 
    * 
    * @exception ArithmeticException
    *   Indicates that the section is negative and that you must enter
    *   a positive number greater than 0
    * @exception ArithmeticException
    *   Indicates that the section number should be 2 digit
    */
    public void setSection(byte section){
        if (section < 0)
            throw new ArithmeticException ("Enter positive value for section");
        else if (section > 100)
            throw new ArithmeticException ("Enter less than 100 for section number");
        else
            this.section = section;
    }
    
    /**
    * Getter method that returns the instructor 
    * of the specific course
    * 
    * 
    * @return 
    *    The specific instructor of the course
    * 
    */
    public String getInstructor(){
        return instructor;
    }
    
    /**
    * Setter method for setting the instructor from the argument
    * with the code from the reference type string. Checks if
    * string is less than or equal to 25 characters for 
    * Instructor input to be valid
    * 
    * @param instructor 
    *    A String primitive type that is the instructor of the 
    *    specific course
    * 
    * @exception StringIndexOutOfBoundsException
    *     Indicates that the department must be entered less than or
    *     equal to 25 characters
    */
    public void setInstructor(String instructor){
        if (instructor.length() > 25)
            throw new StringIndexOutOfBoundsException("Enter less than or equal to "
                    + "25 characters");
        else
            this.instructor = instructor;
    }
    
    /**
    * Returns a copy of that specific course 
    * where all the content is still preserved
    * as the original
    * 
    * @return 
    *   The copy of the Course object
    */
    @Override
    public Object clone(){
        Course courseCopy = new Course(this.courseName, 
            this.department,this.code, this.section, this.instructor);
        return courseCopy;
    }
    
    /**
    * Checks if the Course object is equal to the 
    * attributes of the this Course. Note that letters are 
    * equals even in the case that they're case insensitive
    * 
    * @param obj
    *   An Object parameter that is presumed an instance of the
    *   Course object
    * @return 
    *   The return is true if the Course is equal to obj.
    *   Otherwise the return is false if they're not 
    *   equal
    */
    @Override   
    public boolean equals(Object obj){
        //Check if obj is instantiated with Course
        if(obj instanceof Course)
            //returns the fields to be equivalent while typecasted at the same
            //time. In this case, equalsIgnoreCase is used to ignore cases when
            //when comparing data fields of type String
            return ((this.courseName.equalsIgnoreCase(((Course)obj).courseName)) &&
                    (this.department.equalsIgnoreCase(((Course)obj).department)) &&
                    (this.code == ((Course)obj).code) &&
                    (this.section == ((Course)obj).section) &&
                    (this.instructor.equalsIgnoreCase(((Course)obj).instructor)));
        else
            //returns false if obj is not instanttiate of Course class
            return false;
    }           
}
