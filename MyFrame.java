/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *Вікно, в якому відбуваються
 * всі події, головний елемент
 * графічного інтерфейсу
 * @author alexa
 */
public class MyFrame extends JFrame implements ActionListener{
    
    Player player;
    public static Dealer dealer;
    Deck deck;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    int gamesCount = 0;
    
    Timer timer;
    Toolkit kit = Toolkit.getDefaultToolkit();
    ImageIcon img = new ImageIcon("Jack.jpg");
    int SizeW = 800;
    int SizeH = 600;
    
    public MyFrame(Socket s) {
        timer = new Timer(60,this);
        dealer = new Dealer(this);
        player = new Player(this);
        socket = s;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception ex) {
            System.out.println("Reader writer error: "+ex.getMessage());
        }
//        player = new Player();
//       try{
//        deck = new Deck();
//       }
//       catch(IOException e){
//           JOptionPane.showInputDialog(e.getCause());
//       }
        JLabel bg = new JLabel();
        bg.setIcon(img);
        bg.setSize(SizeW, SizeH);
        bg.setLayout(null);
        
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setBounds(kit.getScreenSize().width/2-SizeW/2, kit.getScreenSize().height/2-SizeH/2, SizeW, SizeH);
      this.getContentPane().add(bg, -1);
      this.setResizable(false);
      this.setTitle("BlackJack the Game");
//      this.pack();
//      this.setVisible(true);

    }
/**
 * Метод оновлення
 * після рестарту
 */
    public void resetPanel(){
            player.handValue=0;
            player.buttonPanel.removeAll();
            player.buttonPanel.add(player.restart);
            player.buttonPanel.repaint();
            player.buttonPanel.revalidate();
            player.bet = 0;
    }
    
    public void gameResult(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(player.handValue>21){
            writer.println(socket.getLocalPort()+": "+player.handValue);
            System.out.println("LOST");
            JOptionPane.showMessageDialog(this, "Overdraw, you lost");
            resetPanel();
            
        }
        else if (player.handValue==21){
            System.out.println("BLACKJACK");
            MyFrame.dealer.fullHit();
            if(player.handValue>dealer.handValue || dealer.handValue>21){
                player.money+=player.bet*3;
                writer.println(socket.getLocalPort()+": "+player.handValue);
                JOptionPane.showMessageDialog(this, "BLACKJACK BET you won: " + player.bet*3);
            }
            else if(player.handValue==dealer.handValue){
                writer.println(socket.getLocalPort()+": "+player.handValue);
                player.money+=player.bet;
                JOptionPane.showMessageDialog(this, "BLACKJACK DRAW, money refounded");
            }
            
            resetPanel();
        }
    }
    
}
