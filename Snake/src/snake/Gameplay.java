/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Elena
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener{
    
        private ImageIcon titleImage;
        private ImageIcon snakeImage;
        private ImageIcon rightMouth;
        private ImageIcon leftMouth;
        private ImageIcon upMouth;
        private ImageIcon downMouth;
        private ImageIcon foodIcon;
        private int size = 25;
        private int snakeLength = 3;
        private int moves = 0;
        private int delay = 100;
        private int[] snakeX = new int[750];
        private int[] snakeY = new int[750];
        private  int score = 0;
        private boolean right = false;
        private boolean left = false;
        private boolean up = false;
        private boolean down = false;
        private  boolean isGameOver = false;
        private boolean stopGame = false;
        private boolean continueGame = false;
        private final Timer timer;
        private List<Integer> foodPosX = new ArrayList<Integer>();
        private List<Integer> foodPosY = new ArrayList<Integer>();
        private  Random random = new Random();
        private int randX = random.nextInt(34); // 850 / 25
        private int randY = random.nextInt(23); // 850 / 25
        
        
        public  Gameplay(){
          timer = new Timer(delay, this);
          timer.start();
            addKeyListener(this);
            setFocusable(true);
            
            //fill empty positions
            for (int i = 0; i < 850 / 25; i++) {
                foodPosX.add(25 + (25 * i));
            }
            for (int i = 0; i < (625 / 25) -2; i++) {
                foodPosY.add(75 + (25 * i));
            }
        }
        
    public void paint(Graphics g){
            // start  
        if (moves == 0){
          isGameOver = false;
          stopGame = false;
          continueGame = false;
            right = true;
            
            snakeX[0] = 100;
            snakeX[1] = 75;
            snakeX[2] = 50;
            
            snakeY[0] = 100;
             snakeY[1] = 100;
             snakeY[2] = 100;
            
        }
        // Title border
        g.setColor(Color.BLACK);
        g.drawRect(24, 10, 851, 55);
        
        // Title icon
        titleImage = new ImageIcon(getClass().getResource("/snake/assets/assets/snaketitle.jpg"));
        titleImage.paintIcon(this, g, 25, 11);
        
        // gameplay border
        g.setColor(Color.BLACK);
        g.drawRect(24, 74, 851, 577);
        
        // background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(25, 75, 850, 575);
        
        // draw info P
         g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 16));
        g.drawString("Press P for stop " , 40, 30);
        
        // draw info O
         g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 16));
        g.drawString("Press O for continue " , 40, 50);
        
        // draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 16));
        g.drawString("Scores: " + score, 780, 30);
        
               
        
        // draw snake
        rightMouth = new ImageIcon(getClass().getResource("/snake/assets/assets/rightmouth.png"));
        rightMouth.paintIcon(this, g, snakeX[0], snakeY[0]);
        
        for (int i = 0; i < snakeLength; i++) {
            
            
            if(i ==  0){
                if(right){
                    rightMouth = new ImageIcon(getClass().getResource("/snake/assets/assets/rightmouth.png"));
                    rightMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
        
                }
                if(left){
                    leftMouth = new ImageIcon(getClass().getResource("/snake/assets/assets/leftmouth.png"));
                    leftMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
        
                }
                if(up){
                    upMouth = new ImageIcon(getClass().getResource("/snake/assets/assets/upmouth.png"));
                    upMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
        
                }
                if(down){
                    downMouth = new ImageIcon(getClass().getResource("/snake/assets/assets/downmouth.png"));
                    downMouth.paintIcon(this, g, snakeX[i], snakeY[i]);
            
                }
                                
            }else{
              snakeImage = new ImageIcon(getClass().getResource("/snake/assets/assets/snakeimage.png"));
              snakeImage.paintIcon(this, g, snakeX[i], snakeY[i]);
           
            }
                        
           
        }
       // food
       foodIcon = new ImageIcon(getClass().getResource("/snake/assets/assets/food.png"));
       foodIcon.paintIcon(this, g, foodPosX.get(randX), foodPosY.get(randY));
       
       //collect food
       if(foodPosX.get(randX) == snakeX[0] && foodPosY.get(randY) == snakeY[0]){
          
           score++;
           snakeLength++;
          randX = random.nextInt(34);
          randY = random.nextInt(23);
       }
       
       //stop game
       if(stopGame ){
                  
         stopGame = true;
       timer.stop();   
       
           
       }
       
       //continue game
       if(continueGame){
           
           timer.start();
           
       }
       
            
       
       //collide with itself
        for (int i = 1; i < snakeLength; i++) {
            if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                right = false;
                left = false;
                up = false;
                down = false;
                
                g.setColor(Color.BLACK);
                g.setFont(new Font("arial", Font.BOLD, 45));
                g.drawString("Game Over", this.getWidth() / 3 + - 85 + g.getFontMetrics().stringWidth("Game Over") / 2, this.getHeight() / 2 - 40);
               
                g.setFont(new Font("arial", Font.BOLD, 30));
                g.drawString("Space to restart", this.getWidth() / 3 + 40, this.getHeight() / 2);
                
                isGameOver = true;
                timer.stop();
                
                
            }
        }
       g.dispose();
       
       
    }

    //keys pressed
    @Override
    public void keyPressed(KeyEvent e) {
    
        if(e.getKeyCode() == KeyEvent.VK_P){
                stopGame = true;
                moves = 0;
                timer.stop();
                
            
        }
        
        
        
         if(e.getKeyCode() == KeyEvent.VK_O){
            if(continueGame = true){
                moves++;
                timer.start();
                repaint();
                
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(isGameOver){
                moves = 0;
                score = 0;
                snakeLength = 3;
                timer.start();
                repaint();
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            right = true;
            if(!left){
                right = true;
            }else{
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            left = true;
            if(!right){
                left = true;
            }else{
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            up = true;
            if(!down){
                up = true;
            }else{
                up = false;
                down = true;
            }
            right = false;
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            down = true;
            if(!up){
                down = true;
            }else{
                down = false;
                up = true;
            }
            right = false;
            left = false;
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       timer.start();
       if(right){
           for(int i = snakeLength - 1; i >= 0; i--){
           snakeY[i + 1] = snakeY[i];
       }
           for (int i = snakeLength; i >= 0; i--) {
               if (i == 0) {
                   snakeX[i] = snakeX[i] + 25; 
                   
               } else{
                   snakeX[i] = snakeX[i -1];
               }
                if(snakeX[i] > 850){
                    snakeX[i] = 25;
                }
           }
          repaint();
       }
       
        if(left){
           for(int i = snakeLength - 1; i >= 0; i--){
           snakeY[i + 1] = snakeY[i];
       }
           for (int i = snakeLength; i >= 0; i--) {
               if (i == 0) {
                   snakeX[i] = snakeX[i] - 25; 
                   
               } else{
                   snakeX[i] = snakeX[i -1];
               }
                if(snakeX[i] < 25){
                    snakeX[i] = 850;
                }
           }
          repaint();
       }
         if(up){
           for(int i = snakeLength - 1; i >= 0; i--){
           snakeX[i + 1] = snakeX[i];
       }
           for (int i = snakeLength; i >= 0; i--) {
               if (i == 0) {
                   snakeY[i] = snakeY[i] - 25; 
                   
               } else{
                   snakeY[i] = snakeY[i -1];
               }
                if(snakeY[i] < 75){
                    snakeY[i] = 625;
                }
           }
          repaint();
       }
          if(down){
           for(int i = snakeLength - 1; i >= 0; i--){
           snakeX[i + 1] = snakeX[i];
       }
           for (int i = snakeLength; i >= 0; i--) {
               if (i == 0) {
                   snakeY[i] = snakeY[i] + 25; 
                   
               } else{
                   snakeY[i] = snakeY[i -1];
               }
                if(snakeY[i] > 625){
                    snakeY[i] = 75;
                }
           }
          repaint();
       }
        
             
    }

    @Override
    public void keyTyped(KeyEvent e) {
        

    }
    @Override
    public void keyReleased(KeyEvent e) {
        
                
         }

   
        
    
    
    
}
