/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mort11;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author admin
 */
public class TestHost
{
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        MortUDP host = new MortUDP(5555,InetAddress.getByName("localhost"),4445);
        host.start();
        System.out.println("host started");
        while(true) {
            System.out.println(host.get("ready","false"));
        }
    }
}
