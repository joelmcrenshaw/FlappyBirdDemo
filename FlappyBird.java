import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // STORES ALL THE PIPES
import java.util.Random;
import java.util.random.*; // GENERATES PIPES AT RANDOM
import javax.swing.*;

 public class FlappyBird extends JPanel implements ActionListener, KeyListener { 
    int boardWidth = 360;
    int boardHeight = 640;

    // IMAGES
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    // BIRD
    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;

        }
    }

    // PIPE CLASS
    int pipeX = boardWidth; // Right side of board
    int pipeY = 0;
    int pipeWidth = 64; // Scaled by 1/6
    int pipeHeight = 512; 

    // Pipe constructor 
    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false; // CHECKS IF BIRD HAS PASSED THROUGH THE PIPE

        Pipe(Image img) {
            this.img = img;
        }
    }

    // GAME LOGIC   
    
    Bird bird;
    int velocityY= -4; // MOVES BIRD LEFT/RIGHT (MOVES BIRD 4 PIXELS EVERY FRAME)
    int velocityX = 0; //MOVES PIPES TO THE LEFT SPEED (SIMULATES BIRD MOVING RIGHT)
    int gravity = 1; // APPLIES PHYSICS (GRAVITY) TO THE BIRD
    ArrayList<Pipe> pipes; // CREATES LIST OF PIPES
    Random random = new Random();
    
    Timer gameLoop;
    Timer placePipeTimer;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        // setBackground(Color.blue);
        setFocusable(true); 
        addKeyListener(this);


        // LOADS IMAGES 
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();          
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage(); 
        
        // Bird Constructor
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();
        
        // Place Pipes Timer
        placePipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        
        placePipeTimer.start();

        // Game Timer
            // UPDATES AT 60 TIMES PER SECOND
        gameLoop = new Timer(1000/60, this); // 1000/60 = 16.6
        gameLoop.start();

    }

    public void placePipes() {

        Pipe topPipe = new Pipe(topPipeImg);
        pipes.add(topPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }
    
    public void draw(Graphics g) {
        // System.out.println("draw"); // DEBUG STATEMENT

        // BACKGROUND 
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);


        // BIRD 
        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);

        // PIPES
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

        }  


    }

    public void move() {

        // BIRD
        velocityY += gravity; 
        bird.y += velocityY; // UPDATES EVERY FRAME
        bird.y = Math.max(bird.y, 0); // SETS A CEILING FOR THE BIRD

        // PIPES
        for (int i = 0; i < pipes.size(); i++ ) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();  
        
    }

    // THIS SECTION APPLIES KEY EVENTS
        
        // WHEN USER PRESSES A KEY  
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) // SETS THE SPACE BAR AS A KEY {
            velocityY = -9;
        }
    
    @Override
    public void keyTyped(KeyEvent e) {
      
    }
    // 
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
}
