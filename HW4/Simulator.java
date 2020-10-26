import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

/**
 * A class where the simulator is executed to test the process when packets
 * are created which are sent to dispatcher, routers, then destination
 * where the user has to enter arrival probability, number of immediate routers,
 * maximum buffer size, minimum size of packet, maximum size of packet, 
 * bandwidth size, and simulation duration
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 4
 * Recitation 1: Jian Xi Chen
 * 
 */
public class Simulator extends ArrayList <Packet> {
    private Router dispatcher = new Router();        
    private static ArrayList<Router> routers;  
    private int totalServiceTime;    
    private int totalPacketsArrived;   
    private int packetsDropped;     
    private double arrivalProb;     
    private int numIntRouters;       
    private int maxBufferSize;      
    private int minPacketSize;        
    private int maxPacketSize;        
    private int bandwidth;         
    private int duration;    
    public static final int MAX_PACKETS = 3;
    private final DecimalFormat twoDecimalPlaces = new DecimalFormat("##.##");
    
    private int bufferSize;
    private int minSizePacket;
    private int maxSizePacket;
    private int bandwidthSize;
    private int simDuration;
    private int numImmRouters;
     
    /**
     * To simulate the packets that are sent from the dispatcher to the routers and
     * then from routers to the destination. There are conditions that apply when
     * sending from dispatcher to routers and from routers to destination
     * @param immRouters
     *   The ArrayList of routers where it'll store the packets
     * @param arrivalProb
     *   The arrival probability to send packet to a random router
     * @param maxBufferSize
     *   The max number of packets that can be stored for the routers
     * @param minPacketSize
     *   The minimum packet size
     * @param maxPacketSize
     *   The maximum packet size
     * @param bandwidth
     *   The maximum number of Packets the Destination router can accept 
     *   at a given simulation unit
     * @param duration
     *   Number of simulation unit
     * @return 
     *   Zero which really does not have a significant contribution since I
     *   don't know what should actually be returned
     */
    public double simulate(ArrayList<Router> immRouters, double arrivalProb, int maxBufferSize, 
        int minPacketSize, int maxPacketSize, int bandwidth, int duration){
        this.arrivalProb = arrivalProb;
        this.duration = duration;
        this.minPacketSize = minPacketSize;
        this.maxPacketSize = maxPacketSize;
        this.routers = immRouters;
        this.bandwidth = bandwidth;
        this.maxBufferSize = maxBufferSize;
        try{
            int packetID = 1;
            int currentSecond;
            int timeLeftInRouter = 0;
            Packet newPacket = null;
            if (this.numIntRouters < 0 || this.arrivalProb < 0.0 || this.arrivalProb > 1.0 || this.duration < 0 ||
                this.maxBufferSize < 0 || this.minPacketSize < 0 || this.maxPacketSize < 0 || this.bandwidth < 0){
                System.out.println("NO SIMULATION");
            }
            else{        
                //checks if the packet has arrived
                //each time elapse in seconds
                for(currentSecond = 1; currentSecond <= this.duration; currentSecond++){
                    System.out.println("Time: " + currentSecond);
                    //2 packets at a time to send to router
                    for(int id = 1; id <= MAX_PACKETS; id++){
                        if(Math.random() < this.arrivalProb){
                            newPacket = new Packet(packetID, randInt(this.minPacketSize, this.maxPacketSize), currentSecond);
                            dispatcher.enqueue(newPacket);
                            System.out.println("Packet " + newPacket.getId() + " arrives at dispatcher with size " 
                              + newPacket.getPacketSize());
                            packetID++;               
                        }     
                    }            
                    if(dispatcher.isEmpty()){
                        System.out.println("No packets arrived.");
                    }     
                    // EVENT 2: can we take a packet off the
                    // queue and put it in the destination?
                    while(!dispatcher.isEmpty()){
                        if(timeLeftInRouter == 0 && (!dispatcher.isEmpty())){
                            Packet removed = dispatcher.dequeue();
                            try{
                                int routeNumber = Router.sendPacketTo(routers, this.maxBufferSize);
                                routers.get(routeNumber).enqueue(removed);
                                System.out.println("Packet " + removed.getId() + " sent to Router " + (routeNumber+1));
                            }
                            //buffer is full so store packets in fake queue
                            catch(FullBufferException e){
                                enqueueFake(removed);
                                packetsDropped++;
                                System.out.println("Network is congested. Packet " + removed.getId() + " is dropped.");
                            }
                        }
                    }  
                    //to send the packets to the destination which is dependent on 
                    //the size of the bandwidth and if time to destination for a
                    //certain packet is 0
                    int sentPackets = 0;
                    for(int i = 0; i < routers.size(); i++){
                        newPacket = routers.get(i).peek();
                        if(newPacket != null){
                            if (newPacket.getTimeToDestination() > 0){
                                newPacket.timeToDest -= 1;
                            }
                            if(newPacket.getTimeToDestination() == 0 && sentPackets < this.bandwidth){
                                sentPackets++;
                                totalPacketsArrived++;
                                totalServiceTime += (currentSecond - newPacket.getTimeArrive()); 
                                System.out.println("Packet " + newPacket.getId() + " has successfully reached its "
                                  + "destination: + " + (currentSecond - routers.get(i).dequeue().getTimeArrive()));        
                            }
                        }
                    }
                    
                    //To display the routers with the contained packets
                    for(int i = 0; i < routers.size(); i++){
                        System.out.println("R" + (i + 1) + ": " + routers.get(i).toString());
                    }            
                }
                System.out.println("\nSimulation ending...");
            }          
            double avgWaitTime = (double)totalServiceTime/totalPacketsArrived;
            System.out.println("Total service time: " + totalServiceTime);
            System.out.println("Total packets served: " + totalPacketsArrived);
            System.out.println("Average service time per packet: " + twoDecimalPlaces.format(avgWaitTime));
            System.out.println("Total packets dropped: " + packetsDropped);      
        }
        catch(ArithmeticException e){
            System.out.println("Total service time: " + totalServiceTime);
            System.out.println("Total packets served: " + 0);
            System.out.println("Average service time per packet: " + twoDecimalPlaces.format(0.00));
            System.out.println("Total packets dropped: " + packetsDropped);
        }
        return 0;
    }
    
    /**
     * Generate a random number between minVal and maxVal, inclusively. 
     * Return that randomly generated number.
     * @param minVal
     *   The minimum value size
     * @param maxVal
     *   The maximum value size
     * @return 
     *   The random size between the minimum and maximum size
     */
    private int randInt(int minVal, int maxVal){
        return (int)(Math.random() * (maxVal - minVal + 1)) + minVal;
    }
    
    /**
     * To store the dropped packets that failed to get transferred from router
     * to destination because of limited bandwidth
     * @param packet 
     *   The dropped packets that will be stored in the fake queue
     */
    public void enqueueFake(Packet packet){
        super.add(packet);
    }
    
    /**
     * Setter method to setting the simulation duration and throws an exception
     * if simulation duration is less than 0
     * @param simDuration 
     *   The duration of the simulation
     * @throws IllegalArgumentException
     *   The simulation duration entered by the user is negative
     */
    public void setSimulationDuration(int simDuration){
        if (simDuration < 0)
            throw new IllegalArgumentException ("Enter positive value for buffer size");
        else
            this.simDuration = simDuration;
    }
    
    /**
     * Setter method of setting the minimum size of the packet and throws an exception
     * if minimum size of packet is less than 0
     * @param minSizePacket 
     *   The minimum size of the packet
     * @throws IllegalArgumentException
     *   The minimum size of the packet entered by the user is negative
     *   
     */
    public void setMinSizePacket(int minSizePacket){
        if (minSizePacket < 0)
            throw new IllegalArgumentException ("Enter positive value for minimum size packet");
        else
            this.minSizePacket = minSizePacket;
    }
    
    /**
     * Setter method of setting the maximum size of the packet and throws an exception
     * if maximum size of packet is less than 0
     * @param maxSizePacket
     *   The maximum size of the packet
     * @throws IllegalArgumentException
     *   The maximum size of the packet entered by the user is negative
     */
    public void setMaxSizePacket(int maxSizePacket){
        if (maxSizePacket < 0)
            throw new IllegalArgumentException ("Enter positive value for maximum size packet");
        else
            this.maxSizePacket = maxSizePacket;
    }
    
    /**
     * Setter method of setting the bandwidth size of the packet and throws an exception
     * if bandwidth size of packet is less than 0
     * @param bandwidthSize 
     *   The bandwidth size of how many can be sent to the destination
     * @throws IllegalArgumentException
     *   The bandwidth size entered by the user is negative
     */
    public void setBandwidthSize(int bandwidthSize){
        if (bandwidthSize < 0)
            throw new IllegalArgumentException ("Enter positive value for bandwidth size");
        else
            this.bandwidthSize = bandwidthSize;
    }
    
    /**
     * The setter method to setting the maximum buffer size and throws an exception
     * if maximum buffer size of packet is less than 0
     * @param maxBufferSize 
     *   The maximum size of the buffer
     * @throws IllegalArgumentException
     *   The maximum buffer size entered by the user is negative
     */
    public void setMaxBufferSize(int maxBufferSize){
        if (this.maxBufferSize < 0)
            throw new IllegalArgumentException ("Enter positive value for maximum buffer size");
        else
            this.maxBufferSize = maxBufferSize;
    }
    
    /**
     * Setter method to setting the number of immediate routers
     * @param numImmRouters 
     *   The number of immediate routers
     * @throws IllegalArgumentException
     *   The number of immediate router entered by the user is negative or 0
     */
    public void setNumImmRouters(int numImmRouters){
        if (numImmRouters <= 0)
            throw new IllegalArgumentException ("Enter non-zero positive value for buffer size");
        else
            this.numImmRouters = numImmRouters;
    }
    
    /**
     * To check if the minimum size of the packet is greater than or
     * equal to maximum size of packet to throw an exception
     * @param minSizePacket
     *   The minimum size of the packet
     * @param maxSizePacket
     *   The maximum size of the packet
     * @throws MinAndMaxPacketSizeComparisonException
     *   if the minimum size of the packet is greater than or
     *   equal to maximum size of packet, throw an exception
     */
    public void checkMinAndMaxPacketSize(int minSizePacket, int maxSizePacket) throws 
            MinAndMaxPacketSizeComparisonException{
        if(minSizePacket >= maxSizePacket){
             throw new MinAndMaxPacketSizeComparisonException ("Enter maximum size of packet greater than"
                     + "minimum size");
        }
        else{
            this.minSizePacket = minSizePacket;
            this.maxSizePacket = maxSizePacket;
        }
    }
    
   /**
    * This is the main program is where the user will enter all the
    * inputs required to start the simulation
    *
    * @param args 
    *   An array of sequence of character/strings passed
    *   to main function
    */
    public static void main(String[] args){
        //Important note: For "As buffer length increases, average service time 
        //increases and packet drop rate decreases:", make sure to increase the
        //duration input to see the correct trend
        boolean check = true;
        boolean checkOne = true;
        int numOfRouters;
        Scanner stdin = new Scanner(System.in);  
        Simulator process = new Simulator();
        System.out.println("Starting Simulator...\n");
        while(check == true){
            checkOne = true;
            try{
                System.out.print("Enter the number of Intermediate routers: ");
                numOfRouters = stdin.nextInt();
                process.setNumImmRouters(numOfRouters);
                routers = new ArrayList<Router>(numOfRouters);
                for(int i = 0; i < numOfRouters; i++){
                    routers.add(new Router());
                }
                System.out.print("\nEnter the arrival probability of a packet: ");
                double arrivalProb = stdin.nextDouble();
                System.out.print("\nEnter the maximum buffer size of a router: ");
                int bufferSize = stdin.nextInt();
                process.setMaxBufferSize(bufferSize);
                System.out.print("\nEnter the minimum size of a packet: ");
                int minSizePacket = stdin.nextInt();
                process.setMinSizePacket(minSizePacket);
                System.out.print("\nEnter the maximum size of a packet: ");
                int maxSizePacket = stdin.nextInt();
                process.setMaxSizePacket(maxSizePacket);
                process.checkMinAndMaxPacketSize(minSizePacket, maxSizePacket);
                System.out.print("\nEnter the bandwidth size: ");
                int bandwidthSize = stdin.nextInt();
                process.setBandwidthSize(bandwidthSize);
                System.out.print("\nEnter the simulation duration: ");
                int simDuration = stdin.nextInt();
                process.setSimulationDuration(simDuration);
                System.out.println();
                process.simulate(routers, arrivalProb, bufferSize, minSizePacket, maxSizePacket, bandwidthSize, simDuration);          
                while(checkOne == true){
                    System.out.print("\nDo you want to try another simulation? (y/n): ");
                    switch(stdin.next().toLowerCase()){
                        case "n":
                        case "N":
                            System.out.println("Program terminating successfully...\n");
                            stdin.close();
                            System.exit(0);
                            break;
                        case "y":
                        case "Y":
                            process.totalServiceTime = 0;
                            process.totalPacketsArrived = 0;
                            process.packetsDropped = 0;
                            System.out.println("Starting Simulator again\n");
                            checkOne = false;
                            check = true;
                            break;
                        default:
                            System.out.println("Enter 'n' or 'y' again");       
                            checkOne = true;
                            stdin.nextLine();
                            break;
                    } 
                }
            }
            catch(InputMismatchException e){
                System.out.println("\nNO SIMULATION");
                System.out.println("\nPlease enter the numerical values again\n\n");
                stdin.nextLine();
                System.out.println("Starting Simulator again\n");
            }
            catch(IllegalArgumentException e){
                System.out.println("\nNO SIMULATION");
                System.out.println("\nEnter a non-negative number\n\n"); 
                System.out.println("Starting Simulator again\n");
            }
            catch(MinAndMaxPacketSizeComparisonException e){
                System.out.println("\nNO SIMULATION");
                System.out.println("\nEnter maximum size of packet greater than"
                     + "minimum size or minimum size less than maximum size");
                System.out.println("\nStarting Simulator again\n");
            }
        }
    } 
}