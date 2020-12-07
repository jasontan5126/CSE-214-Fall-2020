/**
 * A class where the rank of the webpages will be compared just for the search
 * operation at the very left
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 7
 * Recitation 1: Jian Xi Chen
 * 
 */
import java.util.Comparator;


public class RegularRankComparator implements Comparator<Object>{
    /**
     * Default constructor for RankComparator
     */
    public RegularRankComparator(){
        
    }
    
    /**
     * To compare the ranks for search operation at very left
     * and sort them in proper order
     * @param o1
     *   For object 1
     * @param o2
     *   For object 2
     * @return 
     *   A value indicating if two ranks are equal, greater or less than, or
     *   none of the above
     *   
     */
    @Override
    public int compare(Object o1, Object o2) {
        WebPage e1 = (WebPage) o1;
        WebPage e2 = (WebPage) o2;
        if (e1.getRegularRank() == e2.getRegularRank())
            return 0;
        else if (e1.getRegularRank() > e2.getRegularRank())
            return 1;
        else
            return -1;
    }
}