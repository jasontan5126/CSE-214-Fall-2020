/**
 * A class where the WebPage class is constructed to 
 * represent one webpage
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 7
 * Recitation 1: Jian Xi Chen
 * 
 */
import java.util.Collection;

public class WebPage {
    private String url;
    private int index;
    private int rank;
    private int regularRank;
    private Collection<String> keywords;
    
    /**
     * The default constructor for WebPage class
     */
    public WebPage(){
        setRegularRank(1);
    }
    
    /**
     * The constructor with four parameters
     * @param url
     *   The url of the webpage
     * @param index
     *   The index of the webpage
     * @param rank
     *   The rank of the webpage
     * @param keywords 
     *   The keywords of the webpage
     */
    public WebPage(String url, int index, int rank, Collection<String> keywords){
        this.url = url;
        this.index = index;
        this.rank = rank;
        this.keywords = keywords;
        setRegularRank(1);
    }
    
    /**
     * The getter method for the url
     * @return 
     *   The url of the webpage
     */
    public String getUrl(){
        return this.url;
    }
    
    /**
     * Setter method for the url
     * @param url 
     *   The url of the webpage
     */
    public void setUrl(String url){
        this.url = url;
    }
    
    /**
     * The index of the webpage
     * @return 
     *   The index of the webpage
     */
    public int getIndex(){
        return this.index;
    }
    
    /**
     * Setter method for the index
     * @param index 
     *   The index of the webpage
     */
    public void setIndex(int index){
        this.index = index;
    }
    
    /**
     * The getter method for the rank of the webpage
     * @return 
     *   The rank of the webpage
     */
    public int getRank(){
        return this.rank;
    }
    
    /**
     * Setter method for the rank of the webpage
     * @param rank 
     *   The rank of the webpage
     */
    public void setRank(int rank){
        this.rank = rank;
    }
    
    /**
     * The getter method for the rank of the webpage at very left
     * for search operation
     * @return 
     *   The rank of the webpage at the very left for search operation
     */
    public int getRegularRank(){
        return this.regularRank;
    }
    
    /**
     * Setter method for the rank of the webpage at very left
     * for search operation
     * @param rank 
     *   The rank of the webpage at the very left for search operation
     */
    public void setRegularRank(int rank){
        this.regularRank = rank;
    }
    
    /**
     * The getter method for the keywords of the webpage
     * @return 
     *   the keywords of the webpage
     */
    public Collection getKeyword(){
        return this.keywords;
    }
    
    /**
     * The setter method for the keywords of the webpage
     * @param keyword 
     *   the keywords of the webpage
     */
    public void setKeyword(Collection keyword){
        this.keywords = keyword;
    }
    
    
    /**
     * The toString method where the webpage is printed where it'll
     * all be in the table list for WebGraph class
     * @return 
     *   The printed webpage for one
     */
    @Override
    public String toString(){
        String printString = "";
        
        printString += String.format("%1s    |  %-18s  |      %-4s      |   %-3s    |   %6s", this.index, 
        this.url, this.rank, "*", this.keywords);
        return printString;
    }
    
    /**
     * The method to support printing the table for the searched keyword
     * found in whichever websites
     * @return 
     *   The printed webpage corresponding to the found keyword
     */
    public String toStringOne(){
        String printString = "";
        printString += String.format("%3s        |      %-4s", 
        this.rank, this.url);
        return printString;
    }
}