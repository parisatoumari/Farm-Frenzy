import java.util.Random;
public class Dog {
    Random random = new Random();private int x;private int y;public final int price = 100;
    public Dog() { this.x = 300+75*(random.nextInt(6) + 1);this.y = 100+75*(random.nextInt(6) + 1); }
    public int getX() { return x; }public int getY() { return y; }public void setX(int x) { this.x = x; }public void setY(int y) { this.y = y; }
    public void ChangeX(int x) { if (this.x + x < 375) { this.x += 75; } else if (this.x + x > 6*75+300) { this.x -= 75; } else { this.x += x; } }
    public void ChangeY(int y) { if (this.y + y < 175) { this.y += 75; } else if (this.y + y > 6*75+100) { this.y -= 75; } else { this.y += y; } }
}