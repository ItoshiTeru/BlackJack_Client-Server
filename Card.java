/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*;

/**
 *Клас, що імітує гральну карту
 * Реалізує всі основні властивості
 * карти
 * @author alexa
 */
public class Card {
    
Value value;
Suit suit; 
BufferedImage image;
public JLabel cardLbl = new JLabel();


public Card(Value value, Suit suit, BufferedImage img){
    this.value = value;
    this.suit = suit;
    this.image = img;
    cardLbl.setSize(100, 100);
    cardLbl.setIcon(new ImageIcon(img));
}

public void setLocation(int x, int y){
    cardLbl.setLocation(x, y);
    
}

}
