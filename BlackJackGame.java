/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import javax.swing.*;

/**
 *Головний клас проекту
 *тут відбувається його запуск
 * та створення всіх необіхдних
 * для цього компонентів
 * @author alexa
 */
public class BlackJackGame {

    /**
     * Метод запуску програми
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{

        
        try{             
            Socket socket = new Socket("127.0.0.1", 5000); 
            
        MyFrame frame = new MyFrame(socket);
        StartPanel startP = new StartPanel(frame);
        serverListener servListener = new serverListener(frame, startP);
        servListener.start();
        frame.getLayeredPane().add(startP, 0);
        startP.setLocation(frame.SizeW/2-startP.getSize().width/2, frame.SizeH/2-startP.getSize().height/2);
        frame.setVisible(true);
        }       
        catch(Exception ex){
            System.out.println("Can't connect to the server");
            JOptionPane.showMessageDialog(new JFrame(),"Can't connect to the server");
            System.exit(0);
        }  

             
        
        
        
    }
    
}
