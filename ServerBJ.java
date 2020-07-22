/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *Головний клас серверу
 * Чекає на підключення клієнтів
 * Та створює окремі потоки
 * для роботи з кожним з них
 * @author alexa
 */
public class ServerBJ {

    /**
     * @param args the command line arguments
     */
    
    static ArrayList<Socket> sockets;
    static ArrayList<ConnectionHandler> handlers;
    public static boolean gameGoing = false;
    
    public static void main(String[] args) throws IOException {
        
        sockets = new ArrayList<>();
        handlers = new ArrayList<>();
                
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server ready");
           while(true){              
               if(handlers.size() < 4){
                Socket socket = server.accept();
                sockets.add(socket);
                ConnectionHandler handler = new ConnectionHandler(socket);
                handlers.add(handler);
                handler.start();
                ConnectionHandler.count++;
               }
               else{
                   
               }
           }
        
//        toAll();
//        server.close();
    }
    
    public static void toAll(){
        for (ConnectionHandler h : handlers){
           h.writer.println(ConnectionHandler.count);
        }
    }
    
}
