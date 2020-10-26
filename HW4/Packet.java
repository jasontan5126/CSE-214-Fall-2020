/**
 * A class where the structure of the packet
 * is implemented and how it would be displayed
 * to the user.This will be used when sending 
 * to dispatcher, routers, destination. 
 * 
 * @author 
 *   Jason Tan, SBU ID: 112319102
 * 
 * CSE 214 HW 4
 * Recitation 1: Jian Xi Chen
 * 
 */
public class Packet {
    private static int packetCount;
    private int id;
    private int packetSize;
    private int timeArrive;
    public int timeToDest;
    
    /**
     * Default Packet constructor which uses no parameters
     */
    public Packet(){
    }
    
    /**
     * Constructor that has 5 parameters
     * @param packetCount
     *   The number of packets
     * @param id
     *   The unique identifier for the packet
     * @param packetSize
     *   The size of the packet that is being sent
     * @param timeArrive
     *   The time unit that a certain packet is created
     * @param timeToDest 
     *   The number of simulation units that it takes for a packet to arrive at the destination router.
     */
    public Packet (int packetCount, int id, int packetSize, int timeArrive, int timeToDest){
        this.packetCount = packetCount;
        this.id = id;
        this.packetSize = packetSize;
        this.timeArrive = timeArrive;
        this.timeToDest = timeToDest;
    }
    
    /**
     * Constructor with three parameters
     * @param id
     *   The unique identifier for the packet
     * @param packetSize
     *   The size of the packet that is being sent
     * @param timeArrive 
     *   The time unit that a certain packet is created
     */
    public Packet (int id, int packetSize, int timeArrive){
        this.id = id;
        this.packetSize = packetSize;
        this.timeArrive = timeArrive;
        timeToDest = packetSize/100;
        
    }
    
    /**
     * The constructor with two parameters
     * @param packetSize
     *   The size of the packet that is being sent
     * @param timeToDest 
     *   The number of simulation units that it takes for a packet to arrive at the destination router.
     */
    public Packet (int packetSize, int timeToDest){
        this.packetSize = packetSize;
        this.timeToDest = timeToDest;
    }
    
    /**
     * The getter method towards getting the packet count
     * @return 
     *   The number of packets
     */
    public int getPacketCount(){
        return packetCount;
    }
    
    /**
     * The setter method towards setting the packet count
     * @param packetCount 
     *   The packet count for the number of packets
     */
    public void setPacketCount(int packetCount){
        this.packetCount = packetCount;
    }
    
    /**
     * Getter method to getting the unique ID of the packet
     * @return 
     *   The ID of the packet
     */
    public int getId(){
        return id;
    }
    
    /**
     * Setter method to setting the unique ID of the packet while being incremented
     * @param id 
     *   The unique ID of the packet
     */
    public void setId(int id){
        this.id = id;
        ++this.id;
    }
    
    /**
     * Getter method to getting the packet size
     * @return 
     *   The packet size of the certain packet
     */
    public int getPacketSize(){
        return this.packetSize;
    }
    
    /**
     * The setter method of setting the packet size
     * @param packetSize 
     *   The packet size of the certain packet
     */
    public void setPacketSize(int packetSize){
        this.packetSize = packetSize;
    }
    
    /**
     * Getter method to getting the time arrived for the certain packet created
     * @return 
     *   The time arrived for the certain packet created
     */
    public int getTimeArrive(){
        return this.timeArrive;
    }
    
    /**
     * Setter method to setting the time arrived for the certain packet created
     * @param timeArrive 
     *   Time arrived for the certain packet created
     */
    public void setTimeArrive(int timeArrive){
        this.timeArrive = timeArrive;
    }
    
    /**
     * Getter method to getting the time that a certain packet is sent to the 
     * destination
     * @return 
     *   The time that a certain packet is sent to the destination
     */
    public int getTimeToDestination(){
        return this.timeToDest;
    }
    
    /**
     * Setter method that the time that a certain packet is sent to the destination
     * @param timeToDest 
     *   Time that a certain packet is sent to the destination
     */
    public void setTimeToDestination(int timeToDest){
        this.timeToDest = timeToDest;
    }
    
    
    /**
     * A method where a string representation of the buffer is returned in the format of 
     * [id, timeArrive, timeToDest]
     * @return 
     *   A string representation of the buffer is returned in the format of  
     *   [id, timeArrive, timeToDest]
     */
    @Override
    public String toString(){
        String packetObject = "";       
        packetObject += "[" + this.id + ", " + this.timeArrive + ", " + this.timeToDest + "]";
        return packetObject;
    }   
}
