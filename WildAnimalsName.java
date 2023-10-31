public enum WildAnimalsName {
    Lion(1, 4, "Lion", 4), Bear(1, 3, "Bear", 3), Tiger(2, 4, "Tiger", 4);
    private int speed;private int cage;private String type;private int MaxCage;
    WildAnimalsName(int speed, int cage, String type, int MaxCage) { this.speed = speed;this.cage = cage;this.type = type;this.MaxCage = MaxCage; }
    public int getCage() { return cage; }public void setCage(int cage) { this.cage = cage; }
    public void CageDamage() { if (this.cage > 0) { this.cage--; } else { this.cage = 0; } }
    public String getType() { return type; }public int getMaxCage() { return MaxCage; }
}