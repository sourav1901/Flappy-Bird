import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FlappyPanel extends JPanel implements KeyListener, ActionListener{ 


    final int WIDTH = 525, HEIGHT = 550;
    final int wallXVelocity = 5;
    final int wallWidth = 50;
    int FlappyHeight = HEIGHT/4;
    int flappyV = 0, flappyA=4, flappyI =1;
    int Score = 0;
    int[] wallX = {WIDTH,WIDTH+WIDTH/2}; 
    int[] gap ={(int)(Math.random()* (HEIGHT -150) ),(int)(Math.random()* (HEIGHT -150) )}; 
    // int wallX = WIDTH + 10;
    // int gap = (int)(Math.random()* (HEIGHT -100) );
    boolean Gameover = false;
        
        Timer timer = new Timer(40,this); 
    
  
    public FlappyPanel(){ 
        setSize(WIDTH,HEIGHT);
        setFocusable(true);
        addKeyListener(this);

        setBackground(Color.BLACK);

    }  
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(!Gameover){

            g.setColor(Color.YELLOW);
            g.drawString("SCORE : "+ Score, WIDTH/2 ,10);
            logic();  
            drawWall(g); 
            drawFlappy(g);
        }else{
            g.setColor(Color.YELLOW);
            g.drawString("SCORE : "+ Score, WIDTH/2 ,10);

        }
    }

    public void drawFlappy(Graphics g){

        

        g.setColor(Color.white);

        

        g.fillRect(75, FlappyHeight + flappyV, 25, 25);

    }

    private void drawWall(Graphics g){

        for(int i = 0; i<2; i++){

            g.setColor(Color.RED);
    
            g.fillRect(wallX[i], 0, wallWidth,HEIGHT);
    
            g.setColor(Color.BLACK);
            g.fillRect(wallX[i], gap[i], wallWidth,100);
        }
    }

    private void logic(){
        for(int i = 0; i<2; i++){
            if(wallX[i] <=100 && wallX[i]+wallWidth >=100 || wallX[i] <=75 && wallX[i] + wallWidth >= 75 ){
                if((FlappyHeight +flappyV ) >=0 && (FlappyHeight + flappyV)<=gap[i] 
                || (FlappyHeight + flappyV +25 ) >= gap[i] +100 && (FlappyHeight +flappyV +25) <= HEIGHT){
                    Gameover =true;
                }
            }
            if(FlappyHeight +flappyV <=0 || FlappyHeight +flappyV +25 >= HEIGHT){
                Gameover = true;
            }

            if(75 > wallX[i] + wallWidth ){
                Score++;
            }



            if(wallX[i]+wallWidth <= 0){
                
                    wallX[i] =WIDTH;
                    gap[i]=(int)(Math.random()* (HEIGHT -150) );
                
            }
        }
        
    } 


    @Override
    public void actionPerformed(ActionEvent e) {
        flappyA += flappyI;
        flappyV += flappyA; 

        wallX[0] -= wallXVelocity;
        wallX[1] -= wallXVelocity;

        repaint();
        
    }  

    public void keyTyped(KeyEvent e){}
    
    @Override
    public void keyPressed(KeyEvent e) {


        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            flappyA= -10;
            
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            timer.start();
            
        }
        if(e.getKeyCode()==KeyEvent.VK_R){
            timer.stop();
            FlappyHeight = HEIGHT/4;
         
            flappyV = 0;
            flappyA=8;  
            Score = 0;
            wallX[0] = WIDTH;
            wallX[1] = WIDTH+WIDTH/2;  
            gap[0] = (int)(Math.random()* (HEIGHT -150) );
            gap[1]= (int)(Math.random()* (HEIGHT -150) );      
            Gameover = false;

            repaint();
        }
    } 
    @Override
    public void keyReleased(KeyEvent e) {
        
        
    }
}
