import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Board extends JPanel implements Runnable, KeyListener {

    public  static final int WIDTH = 400, HEIGHT = 450, DOT_SIZE = 10;
    private static final int START_X = 10, START_Y = 10;

    private int snake_x = 10, snake_y = 10, size = 1, ticks = 0;

    private SnakeBodyPart snakeBP;
    private ArrayList<SnakeBodyPart> snake;

    private int score = 0, hscore = 0;

    private Apple apple;
    private ArrayList<Apple> allApples;

    private Random r;
    private Thread thread;
    private boolean running = false;
    private boolean moved = false;

    private boolean right = true;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    public Board() {
        initBoard();
    }

    private void initBoard() {

        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        r = new Random();

        snake = new ArrayList<SnakeBodyPart>();
        allApples = new ArrayList<Apple>();

        initGame();
    }

    private void initGame() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void runGame() {
        if (snake.size() == 0)
        {
            snakeBP = new SnakeBodyPart(snake_x, snake_y, DOT_SIZE);
            snake.add(snakeBP);
        }

        if (allApples.size() == 0)
        {
            int apple_x = r.nextInt(39);
            int apple_y = r.nextInt(39);

            apple = new Apple(apple_x,apple_y, DOT_SIZE);
            allApples.add(apple);
        }


        for (int i = 0; i < allApples.size(); i++)
        {
            if(snake_x == allApples.get(i).getApple_x() &&
                    snake_y == allApples.get(i).getApple_y())
            {
                size++;
                allApples.remove(i);
                i++;
                score += 10;
                if (hscore < score) hscore = score;
            }
        }

        for (int i =0; i < snake.size(); i++)
        {
            if(snake_x == snake.get(i).getSnakeBody_x() &&
                    snake_y == snake.get(i).getSnakeBody_y())
            {
                if(i != snake.size() - 1)
                {
                    stop();
                }
            }
        }
        if (snake_x < 0 || snake_y > 39 || snake_y < 0 || snake_x > 39)
        {
            stop();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        {
            if(right) snake_x++;
            if(left) snake_x--;
            if(up) snake_y--;
            if(down) snake_y++;
            moved = false;

            ticks = 0;

            snakeBP = new SnakeBodyPart(snake_x, snake_y, 10);
            snake.add(snakeBP);

            if(snake.size() > size) {
                snake.remove(0);
            }
        }
    }

    private void stop() {
        running = false;
        try
        {
            thread.join();
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() {
        while (running)
        {
            runGame();
            repaint();
        }
    }

    private void restartGame() {
        snake.clear();
        size = 1;
        score = 0;
        snake_x = START_X;
        snake_y = START_Y;
        right = true;
        left = false;
        up = false;
        down = false;
        initGame();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT - 50);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT - 50);
        g.setColor(Color.BLACK);
        g.fillRect(0, HEIGHT - 50 , WIDTH, HEIGHT);

        for (int i = 0; i < WIDTH / 10; i++)
        {
            g.drawLine(i * 10, 0, i * 10, HEIGHT - 50);
        }

        for (int i = 0; i < (HEIGHT - 40) / 10; i++)
        {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }

        for (int i = 0; i < snake.size(); i++)
        {
            snake.get(i).draw(g);
        }

        for(int i = 0; i < allApples.size(); i++)
        {
            allApples.get(i).draw(g);
        }

        String currentScore = "Score: " + score;
        String highScore = "Highest Score: " + hscore;

        g.setColor(Color.GREEN);
        g.drawString(currentScore, 5, HEIGHT - 25);
        g.drawString(highScore, 5, HEIGHT - 10);

        if (!running){
            String gameOverString = "GAME OVER";
            String restartString = "PRESS ENTER TO RESTART";

            g.setColor(Color.RED);
            g.setFont(new Font("Times Roman", Font.BOLD, 60));
            g.drawString(gameOverString, 20, 200);

            g.setColor(Color.GREEN);
            g.setFont(new Font("Times Roman", Font.PLAIN, 26));
            g.drawString(restartString, 21, 230);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left && !right && !moved) {
            up = false;
            down = false;
            right = true;
            moved = true;
            System.out.println("Pressed right");
        }
        if(key == KeyEvent.VK_LEFT && !right && !left && !moved) {
            up = false;
            down = false;
            left = true;
            moved = true;
            System.out.println("Pressed left");
        }
        if(key == KeyEvent.VK_UP && !down && !up && !moved) {
            left = false;
            right = false;
            up = true;
            moved = true;
            System.out.println("Pressed up");
        }
        if(key == KeyEvent.VK_DOWN && !up && !down && !moved) {
            left = false;
            right = false;
            down = true;
            moved = true;
            System.out.println("Pressed down");
        }
        if(key == KeyEvent.VK_ENTER && !running) {
            restartGame();
            System.out.println("Pressed ENTER");
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
