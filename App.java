import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        // GAME WINDOW
        int boardWidth = 360; // PIXELS
        int boardHeight = 640; // PIXELS
 
        JFrame frame = new JFrame("Flappy Bird");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack(); // SETS THE WINDOW TO THE SPECIFIED DIMENSIONS REGARDLES OF TOP WINDOW
        flappyBird.requestFocus();
        frame.setVisible(true);




    }
}
