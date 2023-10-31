import java.util.Random;
public class ProducerAnimals {
    private int Hungriness;private int X;private int Y;Random random = new Random();private ProducerAnimalName name;
    public ProducerAnimals(ProducerAnimalName name) {
        this.name = name;
        this.X =300+75* (random.nextInt(6) + 1);
        this.Y = 100+75*(random.nextInt(6) + 1);
        this.Hungriness = 100;
    }
    public String CheckForProduct() {
        switch (name) {
            case Chicken:
                if (name.getTime() == 0) {
                    return "egg";
                }
                break;
            case Hen:
                if (name.getTime() == 0) {
                    return "feather";
                }
                break;
            case Buffalo:
                if (name.getTime() == 0) ;{
                return "milk";
                }
        }
        return null;
    }
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public int getHungriness() {
        return Hungriness;
    }
    public void setHungriness(int hungriness) {
        this.Hungriness = hungriness;
    }
    public void hurt() {
        Hungriness -= 10;
    }
    public boolean isAlive() {
        if (Hungriness != 0) {
            return true;
        }
        return false;
    }
    public void ChangeX(int x) {
        if (this.X + x < 375) {
            this.X += 75;
        } else if (this.X + x > 300+6*75) {
            this.X -=75;
        } else {
            this.X += x;
        }
    }
    public void ChangeY(int y) {
        if (this.Y + y < 175) {
            this.Y += 75 ;
        } else if (this.Y + y > 6*75+100) {
            this.Y -= 75;
        } else {
            this.Y += y;
        }
    }
    public ProducerAnimalName getName() {
        return name;
    }
    public void setX(int x) {
        X = x;
    }
    public void setY(int y) {
        Y = y;
    }
}