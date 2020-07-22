/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *Клас, що представляє користувача
 * Має такі поля, як баланс, ставка,
 * значення руки, тощо...
 * @author alexa
 */
public class Player {
    MyFrame frame;
    ArrayList<Card> hand = new ArrayList<>();
    JPanel panel;
    JPanel buttonPanel;
    JPanel cardPanel;
    Random r;
    int money = 1000;
    int handValue = 0;
    int bet = 0;
    JButton restart;
    int indexes[];
    int currentIndex = 0;
    JButton hit;
    JButton stand;
    
    public Player(MyFrame frame){
        this.frame = frame;
        panel = new JPanel();
        cardPanel = new JPanel();
        
//        cardPanel.setLayout(new FlowLayout());
        buttonPanel = new JPanel();
        panel.setOpaque(false);
        cardPanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout());
        panel.setSize(400,180);
        panel.setLayout(new BorderLayout());
//        panel.setOpaque(false);
panel.setBackground(Color.white);

//        panel.setLocation(0, 200);
hit = new JButton("  hit  ");
hit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(handValue<21){
                    hit();
                }
                else if (handValue==21){
                    
                }
                else if (handValue>21){
                    
                }
            }
        });
stand = new JButton("stand");
stand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFrame.dealer.fullHit();
                if (handValue>MyFrame.dealer.handValue || MyFrame.dealer.handValue > 21){
                    money+=bet*2;
                    JOptionPane.showMessageDialog(frame, "YOU WON " + bet*2);
                    frame.resetPanel();
                }
                else if(handValue<MyFrame.dealer.handValue && MyFrame.dealer.handValue<=21){
                    JOptionPane.showMessageDialog(frame, "YOU LOST");
                    frame.resetPanel();
                }
                else if (handValue == MyFrame.dealer.handValue){
                    JOptionPane.showMessageDialog(frame, "IT'S A DRAW");
                    money+=bet;
                    frame.resetPanel();
                }
            }
        });

restart = new JButton("restart");
restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){                
                frame.writer.println("restart");
//                restart();
            } 
        });
buttonPanel.add(hit);
buttonPanel.add(stand);
panel.add(cardPanel, BorderLayout.CENTER);
panel.add(buttonPanel, BorderLayout.SOUTH);
        r = new Random();
    }
    /**
     * Відображає скільки карт
     * відведено користувачу
     * на дану ігрову сесію
     * @param n 
     */
    public void createIndexes(int n){
        if(indexes == null){
//            System.out.println("INDEXES NOT EXIST");
            indexes = new int[n];
        }
        else{
            int[] array = new int[n];
            indexes = array;
        }
    }
    /**
     * Почати гру заново
     */
    public void restart(){
    try {
                    frame.deck = new Deck();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                handValue = 0;
                currentIndex = 0;
                MyFrame.dealer.handValue = 0;
                MyFrame.dealer.currentIndex = 0;
                cardPanel.removeAll();
                buttonPanel.remove(restart);
                buttonPanel.add(hit);
                buttonPanel.add(stand);
                frame.player.makeBet();
                frame.player.hit();
                MyFrame.dealer.panel.removeAll();
                MyFrame.dealer.hit();
                panel.repaint();
                panel.revalidate();               
                MyFrame.dealer.panel.repaint();
                MyFrame.dealer.panel.revalidate();
    }
    /**
     * Взяти нову карту з колоди
     */
    public void hit(){
       hand.add(frame.deck.deck.get(indexes[currentIndex]));
//       deck.deck.remove(indexes[currentIndex]);
       cardPanel.add(hand.get(hand.size()-1).cardLbl);
       handValue+=hand.get(hand.size()-1).value.getValue();
       if(hand.get(hand.size()-1).value.getValue() == 1 && handValue<=11){
           handValue+=10;
       }
       panel.revalidate();
       System.out.println(handValue);
       currentIndex++;
    }
    /**
     * Зробити ставку
     */
    public void makeBet(){
        while(bet == 0){
                        try{
                            int b  = Integer.parseInt(JOptionPane.showInputDialog(frame, "Make your bets"));
                            if(b<=money){
                                bet = b;
                                money-=b;
                            }
                            else{
                                JOptionPane.showMessageDialog(frame, "Not enough money!");
                                money += Integer.parseInt(JOptionPane.showInputDialog(frame, "Make deposit"));
                                if(b<=money){
                                    bet=b;
                                    money-=b;
                                }
                            }
                        }
                        catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(frame, "Input correct number!");
                        }
                    }
        System.out.println("BET: " + bet + " Money: " + money);
    }
    
    
          
}
