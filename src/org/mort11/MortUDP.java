package org.mort11;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Sahit Chintalapudi
 */
public class MortUDP extends Thread
{    
    private DatagramPacket packet;
    private DatagramSocket socket;    
    private InetAddress clientAddress;
    private int clientPort;    
    private HashMap values;
    /**
     * Constructor
     * @param port the port this machine is using to communicate
     * @param clientAdress the address of the machine that is talked to
     * @param clientPort the port of the machine this is trying to talk to
     * @throws SocketException  if the socket is in use, this is thrown
     */
    public MortUDP(int port,InetAddress clientAdress,int clientPort) throws SocketException {
        socket = new DatagramSocket(port);
        this.clientAddress = clientAdress;
        this.clientPort = clientPort;        
        byte[] buf = new byte[256];
        packet = new DatagramPacket(buf,buf.length);
        values = new HashMap();
    }
    /**
     * add a new value to the map
     * @param key the key that can be used to access the key
     * @param val the value that is being stored and sent
     * @throws IOException 
     */        
    public void put(String key,String val) throws IOException {                
        values.put(key, val);        
        String message = key+"~"+val;
        byte[] buffer = message.getBytes();
        packet = new DatagramPacket(buffer,buffer.length,clientAddress,clientPort);        
        socket.send(packet);
    }
    /**
     * 
     * @param key they key the desired val is accessed by
     * @param  defaultReturn 
     * @return the desired value
     */
    public String get(String key,String defaultReturn) {
        if(values.get(key) == null) {
            return defaultReturn;
        } else {
            return values.get(key).toString();
        }
    }
    //keep updating the hashmap
    @Override
    public void run(){           
        while(true) {
            try {                
                socket.receive(packet);
            } catch (IOException ex) {
                ex.printStackTrace();               
            }
            String message = new String(packet.getData());            
            System.out.println(message);
            int escapeIndex = message.indexOf("~");                                                        
            String key = message.substring(0, escapeIndex);
            String val = message.substring(escapeIndex+1, message.length());                        
            values.put(key, val);            
        }
    }     
}    
