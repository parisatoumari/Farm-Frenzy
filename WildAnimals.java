import java.util.Random;
public class WildAnimals { private int X;private int Y;
    Random random = new Random();public WildAnimalsName wildAnimalsName;
    public WildAnimals(WildAnimalsName wildAnimalsName) { this.wildAnimalsName = wildAnimalsName;this.X =300+75* (random.nextInt(6));this.Y = 100+75*(random.nextInt(6)); }
    public int getY() { return Y; }public int getX() { return X; }
    public void ChangeX(int x) { if (this.X + x < 375) { this.X += 75; } else if (this.X + x > 6*75+300) { this.X -= 75; } else { this.X += x; } }
    public void ChangeY(int y) { if (this.Y + y < 175) { this.Y += 75; } else if (this.Y + y > 6*75+100) { this.Y -= 75; } else { this.Y += y; } }
    public WildAnimalsName getWildAnimalsName() { return wildAnimalsName; }
}