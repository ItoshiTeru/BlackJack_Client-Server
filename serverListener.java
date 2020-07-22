/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Клас, що реалізує
 * взаємодію клієнта
 * з сервером
 * @author alexa
 */
public class serverListener extends Thread{
    MyFrame frame;
    StartPanel startPanel;

    
    public serverListener(MyFrame frm, StartPanel p) throws IOException{
        frame = frm;
        startPanel = p;
    }
    
    @Override
    public void run(){        
        try {
            String s;
            while((s = frame.reader.readLine()) !=null){
                    if (s.contains("startgame")){
                        String[] split = s.split(":");
                        MyFrame.dealer.createIndexes(Integer.parseInt(split[1]));
                        frame.player.createIndexes(Integer.parseInt(split[1]));
                        System.out.println(MyFrame.dealer.indexes.length+" length");
                        String[] sSplit = split[2].split("/");                        
                        System.out.println(s);
                        for(int i=0; i<sSplit.length; i++){
                            MyFrame.dealer.indexes[i] = Integer.parseInt(sSplit[i]);
                        }
                    }
                    else if (s.contains(("numbers"))){
                        System.out.println(s);
                        String split[] = s.split(":");
                        String sSplit[] = split[1].split("/");
                        for(int i=0; i<sSplit.length; i++){
                            frame.player.indexes[i] = Integer.parseInt(sSplit[i]);
//                            System.out.println("DEALER NUMB: "+MyFrame.dealer.indexes[i]);
//                            System.out.println("PLAYER NUMB: "+frame.player.indexes[i]);
                        }
                        if(frame.gamesCount == 0){
                            startPanel.start();
                            frame.gamesCount++;
                        }
                        else{
                            frame.player.restart();
                        }
                                        
                    }
//                    else if (s.contains("restart")){
//                        
//                    }
            }
        } catch (Exception ex) {
           System.out.println("RUN: "+ex.getCause()+" : "+ex.getMessage()+ " "+ex.getClass());
        }

    }
    
}
