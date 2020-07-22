/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *Панель, що додається
 * до головного фрейму
 * @author alexa
 */
public class StartPanel extends JPanel{
    JButton start;
    MyFrame frame;
    public StartPanel(MyFrame frame){
        this.frame = frame;
        start = new JButton("Start Game");
        start.setSize(80, 50);
        this.setSize(150, 50);
        this.add(start);
        this.setOpaque(false);
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {                                  
                frame.writer.println("Start");
//                start();
            }
            
        });
    }
    
    public void start(){
                StartPanel.this.setVisible(false);
//                frame.player = new Player(frame);
                
                try{
                frame.deck = new Deck();
                }
                catch(IOException exception){
                    JOptionPane.showMessageDialog(frame, exception.getCause());
                }
                
                frame.player.makeBet();
                
                frame.getLayeredPane().add(frame.player.panel);
                frame.player.panel.setLocation(frame.SizeW/2-frame.player.panel.getSize().width/2, frame.SizeH-frame.player.panel.getSize().height*3/2);
                frame.getLayeredPane().add(frame.dealer.panel);
                frame.dealer.panel.setLocation(frame.SizeW/2-frame.player.panel.getSize().width/2, 20);
                frame.timer.start();               
                frame.player.hit();
                MyFrame.dealer.hit();
    }
    
}
