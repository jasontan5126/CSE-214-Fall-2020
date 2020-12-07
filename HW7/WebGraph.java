/**
 * A class where the WebGraph is constructed for the list of WebPages
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 7
 * Recitation 1: Jian Xi Chen
 * 
 */
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;


public class WebGraph{
    public static final int MAX_PAGES = 40;
    private ArrayList<WebPage> pages = new ArrayList<WebPage>();
    private static ArrayList<String> keywordss = new ArrayList<String>();
    private static ArrayList<String> url = new ArrayList<String>();
    private int [][] edges = new int [this.MAX_PAGES][this.MAX_PAGES];
    
    /**
     * Constructs a WebGraph object using the indicated files as pages and edges.
     * @param pagesFile
     *   String of the relative path to the file containing the page information.
     * @param linksFile
     *   String of the relative path to the file containing the link information.
     * <dt><b>Preconditions:</b></dt>
     *    Both parameters reference text files which exist.
     *    The files follow proper format as outlined in the
     *    "Reading Graph from File" section below.
     * <dt><b>Postconditions:</b></dt>
     *    WebGraph is constructed based on the text file
     * @return
     *   The webgraph from text files
     * @throws IllegalArgumentException 
     *   Does not reference valid text file or the file(s) content is not 
     *   formatted correctly
     */
    public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException{
        WebGraph web = new WebGraph();
        try{
            File file = new File(pagesFile);
            Scanner stdin = new Scanner(file); 
            System.out.println("Loading WebGraph data...");
            System.out.println("Success!   "); 
            
            while(stdin.hasNextLine()){
                String search [] = stdin.nextLine().split(" ");
                if((!search[4].contains(".")) || (search[5].contains(".") || search[6].contains("."))){
                    stdin.close();
                    throw new IllegalArgumentException("The format for page.txt is wrong");
                }
                ArrayList <String> keyword = new ArrayList <String>();
                
                int i = 5;
                while(i < search.length){
                    keyword.add(search[i]);
                    keywordss.add(search[i]);  
                    i++;
                }
                web.addPageDuplicate(search[4], keyword);             
            }
            File fileOne = new File(linksFile);
            Scanner stdinOne = new Scanner(fileOne); 
            while(stdinOne.hasNextLine()){
                String search [] = stdinOne.nextLine().split(" ");
                if((!search[4].contains(".")) || !search[5].contains(".")){
                    stdinOne.close();
                    throw new IllegalArgumentException("The format for links.txt is wrong");
                }
                web.addLinkDuplicate(search[4], search[5]);             
            }       
        }
        catch(FileNotFoundException e){
            System.out.println("The file does not exist");
            return null;
        }
        catch(IllegalArgumentException e){
            System.out.println("The format is wrong for the text file being read");
        }
        return web;
    }
    
    /**
     * Add a page to the Webgraph
     * @param url
     *   The url of the webpage
     * @param keywords
     *   The keywords to be added with the webpage
     *  <dt><b>Preconditions:</b></dt>
     *    url is unique and does not exist as the URL of a WebPage 
     *    already in the graph. Url and keywords are not null.
     * <dt><b>Postconditions:</b></dt>
     *    The page has been added to pages at index 'i' and 
     *    links has been logically extended to include the new row 
     *    and column indexed by 'i'.
     * @throws IllegalArgumentException 
     *   The url is not different or url/keywords are null
     */
    public void addPage(String url, Collection<String> keywords) throws IllegalArgumentException{
        searchUrlDuplicate(url);
        if(url == null || keywords == null){
            System.out.println("The url/keywords are all empty when being added");
            throw new IllegalArgumentException();
        }
        else if(searchUrlDuplicate(url) != -1){
            System.out.println("\nError: " + url + " already exists in the WebGraph. Could not add new WebPage.");
            throw new IllegalArgumentException();
        }
        else if (pages.size() == 40){
            System.out.println("The number of webpages to be stored is already at maximum");
            throw new IllegalArgumentException();
        }
        else{
            pages.add(new WebPage(url, pages.size(), 0, keywords));
            this.keywordss.addAll(keywords);
            this.keywordss.add(url);
        }
        updatePageRanks();
    }
    
    /**
     * Add a page to the Webgraph
     * @param url
     *   The url of the webpage
     * @param keywords
     *   The keywords to be added with the webpage
     * <dt><b>Preconditions:</b></dt>
     *    url is unique and does not exist as the URL of a WebPage 
     *    already in the graph. Url and keywords are not null.
     * <dt><b>Postconditions:</b></dt>
     *    The page has been added to pages at index 'i' and 
     *    links has been logically extended to include the new row 
     *    and column indexed by 'i'.
     * @throws IllegalArgumentException 
     *   The url is not different or url/keywords are null 
     */
    public void addPageDuplicate(String url, Collection<String> keywords) throws IllegalArgumentException{
        try{
            if(url == null || keywords == null){
                System.out.println("The url/keywords are all empty when being added");
                throw new IllegalArgumentException();
            }
            else{
                pages.add(new WebPage(url, pages.size(), 0, keywords));
            }
            updatePageRanks();
        }
        catch(IllegalArgumentException e){
            System.out.println("");
        }
    }
    
    /**
     * Add a link between  URL source to the URL destination
     * @param source
     *   The source of the URL
     * @param destination
     *   The destination of the URL
     * <dt><b>Preconditions:</b></dt>
     *    Both the source and destination parameters reference WebPage in a graph
     * @throws IllegalArgumentException 
     *   If the URLs are null or the source/destination URL don't exist
     */
    public void addLink(String source, String destination) throws IllegalArgumentException{
        int index1 = 0;
        int index2 = 0;
        searchUrlDuplicate(source);
        searchUrlDuplicate(destination);
        if(source == null || destination == null){
            System.out.println("\nThe url/keywords are all empty when adding link");
            throw new IllegalArgumentException();
        }
        else if(searchUrlDuplicate(source) == -1 && searchUrlDuplicate(destination) == -1){
            System.out.println();
            System.out.println("Error: " + source + " and " + destination + " could not be found in the WebGraph.");
            throw new IllegalArgumentException();
        }
        else if(searchUrlDuplicate(source) == -1){
            System.out.println();
            System.out.println("Error: " + source + " could not be found in the WebGraph.");
            throw new IllegalArgumentException();
        }
        else if (searchUrlDuplicate(destination) == -1){
            System.out.println();
            System.out.println("Error: " + destination + " could not be found in the WebGraph.");
            throw new IllegalArgumentException();
        }
        else{
            int i = 0;
            while(i < pages.size()){
                if(pages.get(i).getUrl().equals(source)){
                    index1 = pages.get(i).getIndex();
                }             
                if (pages.get(i).getUrl().equals(destination)){
                    index2 = pages.get(i).getIndex();
                }
                i++;
            }              
            edges[index1][index2] = 1;
            updatePageRanks();   
        }          
    }
    
    /**
     * Add a link between  URL source to the URL destination
     * @param source
     *   The source of the URL
     * @param destination
     *   The destination of the URL
     * <dt><b>Preconditions:</b></dt>
     *    Both the source and destination parameters reference WebPage in a graph
     * @throws IllegalArgumentException 
     *   If the URLs are null or the source/destination URL don't exist
     */
     public void addLinkDuplicate(String source, String destination) throws IllegalArgumentException{
        int index1 = 0;
        int index2 = 0;
        try{
            if(source == null || destination == null){
                System.out.println("The url/keywords are all empty when adding link");
                throw new IllegalArgumentException();
            }
            else{
                int i = 0;
                while(i < pages.size()){                   
                    if(pages.get(i).getUrl().equals(source)){
                        index1 = pages.get(i).getIndex();
                    }             
                    if (pages.get(i).getUrl().equals(destination)){
                        index2 = pages.get(i).getIndex();
                    }
                    i++;
                }
                if(index1 == -1 || index2 == -1){
                    System.out.println("The url cannote be found");
                    throw new IllegalArgumentException();
                }
                else{
                    edges[index1][index2] = 1;
                    updatePageRanks();
                }
            }    
        }
        catch(IllegalArgumentException e){
            System.out.println();
        }
    }
    
    /**
     * To sort the table of webpages by url 
     */
    public void sortByUrl(){
        Collections.sort(pages, new URLComparator());
    }
    
    /**
     * To sort the table of webpages by index
     */
    public void sortByIndex(){
        Collections.sort(pages, new IndexComparator());
    }
    
    /**
     * To sort the table of webpages by rank for search operation at 
     * very left
     */
    public void sortByRegularRank(){
        Collections.sort(pages, new RegularRankComparator());
    }
    
    /**
     * To sort the table of webpages of rank
     */
    public void sortByRank(){
        Collections.sort(pages, new RankComparator());
    }
    
    /**
     * To sort the table of webpages by rank for search method
     */
    public void sortByRankForSearch(){
        Collections.sort(pages, new RankComparatorDuplicate());
    }
    
    /**
     * To remove the webpage from the WebGraph of the given URL
     * @param url 
     *   The url to remove from WebGraph
     * 
     * <dt><b>Postconditions:</b></dt>
     *    The WebPage with the indicated URL has been removed from the graph, and 
     *    it's corresponding row and column has been removed from the adjacency matrix.
     *    All pages that has an index greater than the index that was removed 
     *    should decrease their index value by 1.
     *    If url is null or could not be found in pages, the method ignores the 
     *    input and does nothing.
     * 
     */
    public void removePage(String url){
        int position = -1;
        int n = 0;
        while(n < pages.size()){
            WebPage page = pages.get(n);
            if(page.getUrl().equals(url)){
                position = n;
                break;
            }
            n++;
        }  
        if(position == -1){
            throw new IllegalArgumentException("This page does not exist");
        }
        else{
            int i = position;
            while(i < pages.size() - 1){
                int j = 0;
                while(j < pages.size()){
                    edges[j][i] = edges[j][i+1];
                    j++;
                }
                i++;
            }
            
            int k = position;
            while(k < pages.size() - 1){
                int j = 0;
                while(j < pages.size()){
                    edges[k][j] = edges[k + 1][j];
                    j++;
                }
                k++;
            }
            
            int l = position;
            while(l < pages.size()){
                edges[l][pages.size()] = 0;
                edges[pages.size()][l] = 0;
                l++;
            }                   
            pages.remove(position);          
            int m = position;
            while(m < pages.size()){
                pages.get(m).setIndex(m);
                m++;
            }
            updatePageRanks();
        }
    }
    
    /**
     * Removes the link between the given source URL and the destinatino URL
     * @param source
     *   The URL source
     * @param destination 
     *   The URL destination
     * <dt><b>Postconditions:</b></dt>
     *    The WebPage with the indicated URL has been removed from the graph, 
     *    and it's corresponding row and column has been removed from the adjacency matrix.
     *    All pages that has an index greater than the index that was removed should 
     *    decrease their index value by 1. If url is null or could not be found in pages, 
     *    the method ignores the input and does nothing.
     * 
     */
    public void removeLink(String source, String destination){
        int index1 = -1;
        int index2 = -1;
        
        int i = 0;
        while(i < pages.size()){
            if(searchUrlDuplicate(source) == -1 && searchUrlDuplicate(destination) == -1){
                System.out.println();
                System.out.println(source + " and " + destination + " do not exist in graph");
                throw new IllegalArgumentException();
            }
            else if(searchUrlDuplicate(source) == -1){
                System.out.println();
                System.out.println(source + " does not exist in graph");
                throw new IllegalArgumentException();
            }
            else if (searchUrlDuplicate(destination) == -1){
                System.out.println();
                System.out.println(destination + " does not exist in graph");
                throw new IllegalArgumentException();
            }
            if(pages.get(i).getUrl().equals(source)){
                index1 = pages.get(i).getIndex();
            }
            if (pages.get(i).getUrl().equals(destination)){
                index2 = pages.get(i).getIndex();
            }
            i++;
        }
        edges[index1][index2] = 0;
        updatePageRanks();
    }
    
    /**
     * To update the rankings of each webpage
     * <dt><b>Postconditions:</b></dt>
     *    All WebPages in the graph have been assigned their proper PageRank.
     */
    public void updatePageRanks(){
        pages.sort(new IndexComparator());      
        int i = 0;
        while(i < pages.size()){
            int rank = 0;
            int j = 0;
            while(j < pages.size()){
		if (edges[j][i] == 1) {
                    rank++;
		}
                j++;
            }
            pages.get(i).setRank(rank);
            i++;
        }
    }
    
    /**
     * To search the url
     * @param url
     *   The url to search
     * @return
     *   -1 if URL is not found
     * @throws IllegalArgumentException 
     *   The URL is not found
     */
    public int searchUrlDuplicate(String url) throws IllegalArgumentException{
        int i = 0;
        while(i < pages.size()){
            if(pages.get(i).getUrl().contains(url)){
                return pages.get(i).getIndex();
            }
            i++;
        }
        return -1;
    }
    
    /**
     * The keyword to search in all webpages
     * @param keyword 
     *    The keyword to search in all webpages
     */
    public void searchKeyWord(String keyword){
        WebPage page = new WebPage();
        int i = 1;
        if(!keywordss.contains(keyword)){
            System.out.println("\nNo search results found for the keyword " + keyword);
            return;
        }
        System.out.println("\nRank    PageRank               URL");
        System.out.println("-------------------------------------------");
        int j = 0;
        while(j < pages.size()){
            String str = pages.get(j).toStringOne();
            if(pages.get(j).getKeyword().contains(keyword)){
                System.out.println(page.getRegularRank() + "    |    " + str);
                page.setRegularRank(page.getRegularRank() + 1);
            }
            j++;
        }
    }
    
    /**
     * The table which will be printed in the GUI console
     * @return 
     *   The table that is printed to the GUI console
     */
    public String printTableDuplicate(){
        String display = "";
        int j = 0;
        while(j < pages.size()){
            String row = pages.get(j).toString();
            row = row.substring(0, row.length() - 1).replace("[", "").replaceAll("]", "");
            String spaces = "  ";
            int i = 0;
            while(i < pages.size()){
                if(edges[pages.get(j).getIndex()][i] != 0 && edges[pages.get(j).getIndex()][i] == 1) {
                    spaces += i + ", ";
                }
                i++;
            }
            spaces = spaces.substring(0, spaces.length() - 2);
            row = row.replace("*", String.format("%1s%-20s", "", spaces));
            display += row + "\n";
            j++;
	}   
        return display;
    }
    
    /**
     * Prints the WebGraph in tabular form of all the web pages
     */
    public void printTable(){
        System.out.println( "\nIndex        URL                  PageRank                Links             "
                + "          Keywords       ");
        System.out.println("---------------------------------------------------"
                + "------------------------------------------------------------");
        int j = 0;
        while(j < pages.size()){
            String row = pages.get(j).toString();
            row = row.substring(0, row.length() - 1).replace("[", "").replaceAll("]", "");
            String spaces = "";
            int i = 0;
            while(i < pages.size()){
                if(edges[pages.get(j).getIndex()][i] != 0 && edges[pages.get(j).getIndex()][i] == 1) {
                    spaces += i + ", ";
                }
                i++;
            }
            spaces = spaces.replaceAll(", $", "");
            row = row.replace("*", String.format("%1s%-20s", "", spaces));
            System.out.println(row);
            j++;
	}   
    }
}