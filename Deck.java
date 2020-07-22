/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import java.io.IOException;
import java.util.ArrayList;

/**
 *Даний клас реалізує гральну колоду
 * На практиці ж виступає
 * колекцією екземплярів
 * класу Card
 * @author alexa
 */
public class Deck {
    ArrayList<Card> deck;
    
    /**
     * Створення нової повної колоди
     * @throws IOException 
     */
    public Deck() throws IOException{
        LoadImages load = new LoadImages();
        deck = new ArrayList<>();
        Card card;
        for (int i = 0; i<13; i++){
            for (int j = 0; j<4; j++){
                card = new Card(Value.values()[i], Suit.values()[j], load.cards[i*4+j]);
                deck.add(card);
            }
        }
        
    }
}
