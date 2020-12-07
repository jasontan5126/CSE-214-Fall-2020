/**
 * A class where the index of the webpages will be compared
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 7
 * Recitation 1: Jian Xi Chen
 * 
 */
import java.util.*;
public class IndexComparator implements Comparator<Object>{
    
    /**
     * Default constructor for IndexComparator
     */
    public IndexComparator(){
    }
    
    /**
     * To compare the indexes and sort them in proper order
     * @param o1
     *   For object 1
     * @param o2
     *   For object 2
     * @return 
     *   A value indicating if Two indexes are equal, greater or less than, or
     *   none of the above
     *   
     */
    @Override
    public int compare(Object o1, Object o2) {
        WebPage e1 = (WebPage) o1;
        WebPage e2 = (WebPage) o2;
        if (e1.getIndex()== e2.getIndex())
            return 0;
        else if (e1.getIndex() > e2.getIndex())
            return 1;
        else
            return -1;
    }
}