import java.awt.Color;
import java.awt.Graphics;

public class SnakeBodyPart {

    private int snakeBody_x, snakeBody_y, width, height;

    public SnakeBodyPart(int snakeBody_x, int snakeBody_y, int dotSize) {
        this.snakeBody_x = snakeBody_x;
        this.snakeBody_y = snakeBody_y;
        width = dotSize;
        height = dotSize;
    }
    public void tick() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(snakeBody_x * width, snakeBody_y * height, width, height);
        //g.setColor(Color.GREEN);
        //g.fillRect(xCoor * width + 2, yCoor * height + 2, width -4, height-4);
    }

    public int getSnakeBody_x() {
        return snakeBody_x;
    }
    public void setSnakeBody_x(int snakeBody_x) {
        this.snakeBody_x = snakeBody_x;
    }
    public int getSnakeBody_y() {
        return snakeBody_y;
    }
    public void setSnakeBody_y(int snakeBody_y) {
        this.snakeBody_y = snakeBody_y;
    }

}