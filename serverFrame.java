/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverbj;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author alexa
 */
public class serverFrame extends JFrame{
    
    Socket socket;
    JTextField txt;
    PrintWriter writer;
    
    public serverFrame(Socket s, PrintWriter wr) {
        socket = s;
        writer = wr;
        JButton btn = new JButton("click");
        btn.setSize(80, 30);
        btn.setLocation(200, 150);
        txt = new JTextField();       
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        this.getContentPane().add(txt);
        this.getContentPane().add(btn);
        txt.setPreferredSize(new Dimension(100, 30));
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {               
                    writer.println(txt.getText());
                    writer.flush();                  
                    System.out.println("Send: "+txt.getText());                    
            }
        });
        
    }
    
}
