/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackgame;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *Цей клас відповідає
 * за зображення гральних карт
 * @author alexa
 */
public class LoadImages {
    final BufferedImage[] cards;
	
	LoadImages() throws IOException {
		  BufferedImage cardSheet = ImageIO.read(new File("cards.png"));
		  final int width = 73;
		  final int height= 98;
		  final int rows = 13;
		  final int columns = 4;
		  
		  cards = new BufferedImage[rows*columns];
		  
		  for(int i = 0; i < rows; i++) {
		         for(int j = 0; j < columns; j++) {
		            cards[(i * columns) + j] = cardSheet.getSubimage(i * width, j * height, width, height);
		         }
		      }
	}
}
