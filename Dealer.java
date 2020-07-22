/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.util.*;

/**
 *Заміняє роботу дилера
 * комп'ютер, з яким
 * користувач грає
 * @author alexa
 */
public class Dealer {
    ArrayList<Card> hand = new ArrayList<>();
    JPanel panel;
    MyFrame frame;
    int[] indexes;
    int currentIndex = 0;
    
    Random r;
    int handValue = 0;
    
    public Dealer(MyFrame f){
        frame = f;
        panel = new JPanel();
        r = new Random();
        panel.setOpaque(false);
        panel.setSize(400, 180);
                
    }
    /**
     * Взяти карту
     */
    public void hit(){      
       hand.add(frame.deck.deck.get(indexes[currentIndex]));
//       frame.deck.deck.remove(indexes[currentIndex]);
       panel.add(hand.get(hand.size()-1).cardLbl);
       handValue+=hand.get(hand.size()-1).value.getValue();
       if(hand.get(hand.size()-1).value.getValue() == 1 && handValue<=11){
           handValue+=10;
       }
       panel.repaint();
       panel.revalidate();
       System.out.println("Dealer: " + handValue);
       currentIndex++;
    }
    /**
     * Правила гри для дилера
     * брати карту поки значення
     * руки менше 17
     */
    public void fullHit(){
                while(handValue<17){
                    hit();
                }              
    }
    /**
     * Скільки карт відведено
     * для дилера в поточній
     * ігровій сесії
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
    
}
