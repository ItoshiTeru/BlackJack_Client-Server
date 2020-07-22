/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbj;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *Проміжковий клас, 
 * передає керування клієнтом далі
 * @author alexa
 */
public class ConnectionHandler extends Thread{
    
    Socket socket;
    PrintWriter writer;
    clientListener listener;
    public static int count;
    public static int ready;
    boolean isReady = false;
    String numbers = "";
    
    public ConnectionHandler(Socket s){
        socket = s;
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception ex) {
            System.out.println("writer error: "+ex.getMessage());
        }
        listener = new clientListener(socket, this);
        
    }
    
    @Override
    public void run(){
        listener.start();
        String s = "Accepted: "+socket.getPort();
        System.out.println(s);       
        try {
//            OutputStream out = socket.getOutputStream();
//            DataOutputStream dos = new DataOutputStream(out);
//            dos.writeBytes(s);
//            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
//            writer.write(s);
//            writer.close();

//        serverFrame frm = new serverFrame(socket, writer);
//        frm.setSize(400, 300);
//        frm.setTitle(socket.getPort()+"");
//        frm.setVisible(true);
    

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void send(){
        
    }
    
}
