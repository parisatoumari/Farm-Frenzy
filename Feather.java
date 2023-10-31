public class Feather {
    private int x;private int y;private int sec;
    public int getY() { return y; }public int getX() { return x; }public int getSec() { return sec; }
    public void setX(int x) { this.x = x; }public void setY(int y) { this.y = y; }
    public void setSec(int sec) { this.sec = sec; }
    public Feather(int x,int y,int sec) { this.sec=sec;this.x=x;this.y=y;}
    public void ReduceSec(){sec--;if(sec<=0){sec=0;}}
}
