import java.awt.Color;
import java.awt.Graphics;

public class Apple {

    private int apple_x, apple_y, width, height;

    public Apple(int apple_x, int apple_y, int dotSize) {
        this.apple_x = apple_x;
        this.apple_y = apple_y;
        width = dotSize;
        height = dotSize;
    }
    public void tick() {

    }
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(apple_x * width , apple_y * height, width, height);
    }

    public int getApple_x() {
        return apple_x;
    }
    public void setApple_x(int apple_x) {
        this.apple_x = apple_x;
    }
    public int getApple_y() {
        return apple_y;
    }
    public void setApple_y(int apple_y) {
        this.apple_y = apple_y;
    }

}