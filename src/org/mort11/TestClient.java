/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mort11;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author admin
 */
public class TestClient
{
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException
    {
        MortUDP client = new MortUDP(4445,InetAddress.getByName("localhost"),5555);
        client.start();        
        System.out.println("client started");
        client.put("ready", "true");     
        System.out.println("sent");
    }
}