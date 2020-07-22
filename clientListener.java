/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Основний клас взаємодії з клієнтами
 * Безпосередньо приймає та надсилає
 * інформації від та до клієнтів
 * @author alexa
 */
public class clientListener extends Thread{
    
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    ConnectionHandler handler;
    ArrayList<String> allIndexes;
    Random r = new Random();
    
    public clientListener(Socket s, ConnectionHandler h){
        socket = s;
        handler = h;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.out.println("READER WRITER ERROR");
        }
    }
    
    @Override
    public void run(){
        try {
            String s;
            while((s = reader.readLine())!=null){
                System.out.println("LISTENING TO " +socket.getPort());
                if(s.equalsIgnoreCase("start")){
                    System.out.println(s);
                    if(handler.isReady == false){
                        ConnectionHandler.ready++;
                        handler.isReady = true;
                    }               
                    System.out.println("Game going: "+ServerBJ.gameGoing +" Ready: "+ConnectionHandler.ready+" Count: "+ConnectionHandler.count);
                    startGame();
                }
                else if(s.equals("restart")){
                    System.out.println(s);
                    if(handler.isReady==false){
                        ConnectionHandler.ready++;
                        handler.isReady=true;
                    }
                    System.out.println("Game going: "+ServerBJ.gameGoing +" Ready: "+ConnectionHandler.ready+" Count: "+ConnectionHandler.count);
                    startGame();
                }
            }
        } catch (Exception ex) {
            if(ex.getMessage().equals("Connection reset")){
                System.out.println(socket.getPort()+" disconnected");
                ConnectionHandler.count--;
                if(handler.isReady == true && ConnectionHandler.ready!=0){
                    ConnectionHandler.ready--;
                }
                ServerBJ.handlers.remove(handler);
                ServerBJ.sockets.remove(socket);
                System.out.println("handlers: "+ServerBJ.handlers.size());
                System.out.println("sockets: "+ServerBJ.sockets.size());
                startGame();
            }
        }
    }
    /**
     * Метод, що відповідає
     * за початок гри у всіх
     * підключених клієнтів
     */
    public void startGame(){
        if((ConnectionHandler.ready==ConnectionHandler.count) && (ServerBJ.gameGoing == false)){
            ServerBJ.gameGoing = true;
            
            int amount = 52/(ServerBJ.handlers.size() + 1);
            allIndexes = new ArrayList<>();
            for (int i = 0; i <52; i++){
                allIndexes.add(Integer.toString(i));
            }
            String dNumbers="";
            for (int i = 0; i <amount-1; i++){
                int rand = r.nextInt(allIndexes.size());
                dNumbers += allIndexes.get(rand)+"/";
                allIndexes.remove(rand);
            }
            int rand = r.nextInt(allIndexes.size());
            dNumbers += allIndexes.get(rand);
            allIndexes.remove(rand);
            
            for(ConnectionHandler h : ServerBJ.handlers){
                h.writer.println("startgame:"+amount+":"+dNumbers);
            }
            
            for (ConnectionHandler h : ServerBJ.handlers){
                
                for (int i = 0; i <amount-1; i++){
                    int random = r.nextInt(allIndexes.size());
                    h.numbers += allIndexes.get(random)+"/";
                    allIndexes.remove(random);
                }
               int random = r.nextInt(allIndexes.size());
               h.numbers += allIndexes.get(random);
               allIndexes.remove(random);
                
                
                h.writer.println("numbers:"+h.numbers);
                h.numbers = "";
                h.isReady = false;
            }
            ServerBJ.gameGoing = false;
            ConnectionHandler.ready = 0;
            
        }
    }
    
    public void restartGame(){
        if((ConnectionHandler.ready==ConnectionHandler.count) && (ServerBJ.gameGoing == false)){
            ServerBJ.gameGoing = true;
        }
        
    }
}
