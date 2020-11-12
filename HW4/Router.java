import java.util.ArrayList;
import java.util.Collection;


/**
 * A class where the the queue is implemented using ArrayList
 * API and it is extended as a subclass. Plus the display of the
 * routers are implemented whether empty, filled with packets.
 * Plus one method sendPacketTo is implemented where the packet
 * is sent to a router where it's most empty to ensure fairness
 * 
 * @author 
 *   Jason Tan, SBU ID: N/A
 * 
 * CSE 214 HW 4
 * Recitation 1: Jian Xi Chen
 * 
 */
public class Router extends ArrayList <Packet>{
    private Collection <Packet> packet = new ArrayList <Packet>();
    
    /**
     * The default constructor where the ArrayList of the Packet class objects
     * are instantiated with the Collection class
     */
    public Router(){
        packet = new ArrayList <Packet>();
    }
    
    /**
     * Adds the packet into the queue which is represent using ArrayList
     * @param p
     *   The packet object to be added to the queue
     */
    public void enqueue(Packet p){
        super.add(p);
    }
    
    /**
     * Dequeues the packet from the queue which is represented using ArrayList
     * @return 
     *   The result after the packet is removed from the queue
     */
    public Packet dequeue(){
        return super.remove(0);     
    }
    
    /**
     * The peek of the queue which was implemented as an ArrayList
     * @return 
     *   The peek of the queue which was implemented as an ArrayList
     */
    public Packet peek(){
        if(isEmpty())
            return null;
        else
            return super.get(0);
    }
    
    /**
     * The size of the queue which was implemented as an ArrayList
     * @return 
     *   The size of the queue
     */
    @Override
    public int size(){
        return super.size();
    }
    
    /**
     * Checks if the queue is empty
     * @return 
     *   True if the queue is empty. Otherwise false if the queue
     *   is not empty
     */
    @Override
    public boolean isEmpty(){
        return super.isEmpty();
    }
    
    /**
     * A toString method that returns all the routes with packets that are to 
     * be stored in specific routes
     * @return 
     *   The routes with the packets inside
     */
    @Override
    public String toString(){
        String representRouter = "{";
        if(isEmpty()){
            representRouter += "}";
            return representRouter;
        }
        else{
            int i = 0;
            while(i < size()){    
                representRouter += (i == size() - 1) ? super.get(i).toString() : super.get(i) + ", ";   
                i++;
            }
            representRouter += "}";
        }
        return representRouter;
    }
    
    /**
     * Send the packet to a specific route depending on which packet is more
     * empty in terms of number of packets in the specific route
     * @param routers
     *   The list of routers where the packets will be stored
     * @param maxBufferSize
     *   The max number of packets that can be stored for the routers
     * @return
     *   The index which the packet will be sent to the specific router
     *   start from least index and then in sequential order from least to
     *   greatest
     * @throws FullBufferException 
     *   The buffers is full for the number of routers
     */
    public static int sendPacketTo(Collection <Router> routers, int maxBufferSize) throws FullBufferException{
        int i = 1;
        int lowIndex = 0;
        if (!routers.isEmpty()){
            int minimum = ((ArrayList<Router>)routers).get(0).size();
            while(i < routers.size()){
                //get the most empty router starting from smallest router number
                if(((ArrayList<Router>)routers).get(i).size() < minimum){
                    minimum = ((ArrayList<Router>)routers).get(i).size();
                    lowIndex = (i);
                }
                i++;
            }
            //check if all the routers are full
            if (minimum == maxBufferSize){
                throw new FullBufferException("The router buffers are full");
            }
        }
        return lowIndex;
    }
}
