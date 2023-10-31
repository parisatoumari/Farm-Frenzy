public enum ProducerAnimalName {
    Chicken(100, 2, "Chicken"), Hen(200, 3, "Hen"), Buffalo(400, 5, "Buffalo");
    private int Cost;
    private int time;
    private String type;

    ProducerAnimalName(int cost, int time, String type) {
        this.Cost = cost;
        this.time = time;
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void ReduceTime() {
        time--;
        if (time < 0) {
            time = 0;
        }
    }
}