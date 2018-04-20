/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Elena
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     // game window
        JFrame frame = new JFrame(); 
        Gameplay gameplay = new Gameplay();
      
        frame.setTitle("Snake");
        frame.add(gameplay);
        frame.setBounds(25, 25, 905, 735);
        frame.setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        
        
    }
    
}
