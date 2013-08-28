package com.minecraftdimensions.bungeesuiteteleports.socket;

import java.io.*;
import java.net.*;

import org.bukkit.Bukkit;

/**
 * Very simple socket server client example written as static methods for simplicity
 * in adding to an existing project.
 */
public class Client {
    public static void send(String server, int port, ByteArrayOutputStream bytes) {
    	System.out.println("SENDING");
        try {
            Socket socket = new Socket(server, port);
    		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    		out.writeInt(Bukkit.getPort());
    		out.writeInt(bytes.toByteArray().length);
    		out.write(bytes.toByteArray());
            out.flush();
            out.close();
            socket.close();
        } 
        catch (Exception e) {
        	throw new RuntimeException(e);
        }     
    }
}