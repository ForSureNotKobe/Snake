import javax.swing.JFrame;

public class Frame extends JFrame {
    public Frame(){
        JFrame frame = new JFrame();
        Board board = new Board();

        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SnakeGame");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
