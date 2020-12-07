/**
 * A class where the SearchEngine is implemented to execute
 * the operation of WebGraph. Included is the extra credit 
 * GUI execution for sorting column for first click and if 
 * clicked again, reverses sorting.
 * 
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 7
 * Recitation 1: Jian Xi Chen
 * 
 */
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collection;
import javax.swing.*;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.regex.Pattern;
import javax.swing.table.JTableHeader;
import java.util.Stack;

public class SearchEngine extends MouseAdapter {
    public static final String PAGES_FILE = "pages.txt";
    public static final String LINKS_FILE = "links.txt";
    public static final int MAX_COLUMNS = 5;
    private JTable table;
    
    /**
     * Constructor with one parameter
     * @param table 
     *   The JTable which will contain the graph represented in a table
     */
    public SearchEngine(JTable table) {
        this.table = table;
    }
    
    /**
     * Response to when the certain column is clicked from GUI which will display
     * what column was clicked
     * @param event 
     *   Response to the column clicked from table
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        int column = table.columnAtPoint(point);    
        if(column >= 0){
            JOptionPane.showMessageDialog(table, "Column header #" + column + " is clicked");
        }
    }
    
    /**
     * Checks if the url entered by the user is correct by making sure
     * only letters from 'a' to 'z' are entered plus just the '.'
     * @param url
     *   The url entered by the user
     * @return 
     *   false if the url enter is invalid since it doesn't contain letters
     */
    public static boolean urlContainsOnlyLettersAndUrl(String url){
        char[] letterArray = url.toCharArray();
        for (int i = 0; i < letterArray.length; i++) {
            char letter = letterArray[i];
            if (!(letter >= 'a' && letter <= 'z' || letter == '.')) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Check the number of period for the url input entered by user 
     * for the purpose of checking whether the user has entered the 
     * valid url input
     * @param url
     *   The url entered by user
     * @return 
     *   The number of periods in the url entered by the user
     */
    public static int countUrlInputPeriods(String url){
        int count = 0;     
        for(int i = 0; i < url.length(); i++){
            if(url.charAt(i) == '.'){
                ++count;
            }
        }
        return count;
    }
    
    /**
    * The SearchEngine is what actually runs the commands of
    * executing functionality of the WebGraph. Included is an additional 
    * command for the extra credit portion for sorting column for first click of header column 
    * and if column header is clicked again, reverses sorting
    *
    * @param args 
    *   An array of sequence of character/strings passed
    *   to main function
    */
    public static void main(String args[]){        
        //Added a new letter command "G" for the extra credit part to access
        //the GUI and sort by that column when clicked first time and clicking again 
        //to reverse sorting order
        Scanner stdin = new Scanner(System.in); 
        String selectionInput = "";
        String enterInput = "";
        String enterDest = "";
        String enterUrl = "";
        String enterDest1 = "";
        String enterUrl1 = "";
        String enterUr = "";
        String getKeyword = "";
        String sepSpace[];
        WebGraph graph = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
        boolean check = true;                                    
        check = true;     
        while(check != false){
            System.out.println("\nMenu: "); 
            System.out.println("    (G)  - Open the GUI in which you can sort by that column when clicked. "
              + "Clicking again on that column reverses the sorting");
            System.out.println("    (AP) - Add a new page to the graph. ");   
            System.out.println("    (RP) - Remove a page from the graph. ");    
            System.out.println("    (AL) - Add a link between pages in the graph. ");  
            System.out.println("    (RL) - Remove a link between pages in the graph. ");   
            System.out.println("    (P)  - Print the graph. ");       
            System.out.println("    (S)  - Search for pages with a keyword. ");            
            System.out.println("    (Q) - Quit ");           
            System.out.println();
            
            System.out.print("\nPlease select an option: ");
            selectionInput = stdin.nextLine().trim();
            if (selectionInput.equalsIgnoreCase("P")){
                System.out.println("\n    (I) Sort based on index (ASC) ");   
                System.out.println("    (U) Sort based on URL (ASC) ");          
                System.out.println("    (R) Sort based on rank (DSC) \n");  
                System.out.print("Please select an option: ");
                enterInput = stdin.nextLine().trim();
                if(enterInput.equalsIgnoreCase("I")){
                    graph.sortByIndex();
                    graph.printTable();
                }
                else if(enterInput.equalsIgnoreCase("U")){
                    graph.sortByIndex();
                    graph.sortByUrl();
                    graph.printTable();
                }
                else if(enterInput.equalsIgnoreCase("R")){
                    graph.sortByIndex();
                    graph.sortByRankForSearch();
                    graph.printTable();
                }
                else{
                    System.out.println("\nPlease enter a valid command letter from the list");  
                }
                System.out.println();        
            }  
            else if (selectionInput.equalsIgnoreCase("G")){
                String tableOne = graph.printTableDuplicate();
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException | 
                       UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                String[] line = tableOne.split("\n");
                String[][] array = new String[line.length][MAX_COLUMNS];
                int i = 0;
                while(i < line.length){
                    array[i] = line[i].split(Pattern.quote("|"));  
                    i++;
                }
                String[] headers = {"Index", "URL", "PageRank", "Links", "Keywords"};
                JTable table = new JTable(array, headers);
                TableRowSorter<TableModel> tableSort = new TableRowSorter<TableModel>(table.getModel());
                JTableHeader header = table.getTableHeader();
                table.setAutoCreateRowSorter(true);
                Stack<RowSorter.SortKey> sortColumn = new Stack<>();      
                sortColumn.push(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                tableSort.setSortKeys(sortColumn);
                header.addMouseListener(new SearchEngine(table));
                JFrame frame = new JFrame("Extra credit Print Table Graph");            
                frame.add(new JScrollPane(table));
                frame.pack();
                table.setRowHeight(80);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }          
            else if (selectionInput.equalsIgnoreCase("S")){
                System.out.print("Search keyword: ");
                getKeyword = stdin.nextLine().trim().toLowerCase();
                graph.sortByRankForSearch();
                graph.sortByRegularRank();
                graph.searchKeyWord(getKeyword);
            }        
            else if (selectionInput.equalsIgnoreCase("AP")){
                try{
                    System.out.print("Enter a URL: ");
                    enterUrl = stdin.nextLine().trim().toLowerCase();
                    if(!enterUrl.contains(".") || countUrlInputPeriods(enterUrl) != 1 || 
                        urlContainsOnlyLettersAndUrl(enterUrl) == false){
                        System.out.println("\nYou entered the URL input incorrectly");
                        throw new IllegalArgumentException();
                    }
                    System.out.print("Enter keywords (space-separated): ");
                    getKeyword = stdin.nextLine().trim().toLowerCase();
                    sepSpace = getKeyword.split(" ");
                    char[] letterArray = getKeyword.toCharArray();
                    for (int i = 0; i < letterArray.length; i++) {
                        char letter = letterArray[i];
                        if(!(letter >= 'a' && letter <= 'z' || letter == ' ')){
                            System.out.println("\nYou entered the input keywords incorrectly");
                            throw new IllegalArgumentException();
                        }
                    }
                    Collection collection = Arrays.asList(sepSpace);
                    graph.addPage(enterUrl, collection);
                    System.out.println();
                    System.out.println(enterUrl + " successfully added to the WebGraph!");            
                }
                catch(IllegalArgumentException e){
                    System.out.println("");
                }
            }  
            else if (selectionInput.equalsIgnoreCase("RP")){
                try{
                    System.out.print("Enter a URL: ");
                    enterUr = stdin.nextLine().trim().toLowerCase();
                    graph.removePage(enterUr);
                    System.out.println();
                    System.out.println(enterUr + " has been removed from the graph!");
                }
                catch(IllegalArgumentException e){
                    System.out.println("\n" + enterUr + " does not exist in the graph");
                }
            }       
            else if (selectionInput.equalsIgnoreCase("AL")){
                try{
                    System.out.print("Enter a source URL: ");
                    enterUrl = stdin.nextLine().trim().toLowerCase();
                    System.out.print("Enter a destination file: ");
                    enterDest = stdin.nextLine().trim().toLowerCase();
                    graph.addLink(enterUrl, enterDest);
                    System.out.println("\nLink successfully added from " + enterUrl + " to " + enterDest + "!");
                }
                catch(IllegalArgumentException e){
                    System.out.println("");
                }
            }     
            else if (selectionInput.equalsIgnoreCase("RL")){
                try{
                    System.out.print("Enter a source URL: ");
                    enterUrl1 = stdin.nextLine().trim().toLowerCase();
                    System.out.print("Enter a destination file: ");
                    enterDest1 = stdin.nextLine().trim().toLowerCase();
                    System.out.println();
                    graph.removeLink(enterUrl1, enterDest1);
                    System.out.println("\nLink removed from " + enterUrl1 + " to " + enterDest1 + "!");
                }
                catch(IllegalArgumentException e){
                    System.out.println("");
                }
            }       
            else if (selectionInput.equalsIgnoreCase("Q")){
                System.out.println("\nGoodbye.");
                System.exit(0);
            }
            //if the letter command is invalid entered by the user
            else
                System.out.println("\nPlease enter a valid command letter given from the menu list");               
        }        
    }
}