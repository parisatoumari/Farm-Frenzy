import java.time.LocalDateTime;import java.time.format.DateTimeFormatter;
public class Accounts {
    private String username;private String password;private int level;private int coin;private String date;
    public Accounts(String username, String password, int level, int coin) { this.username = username;this.password = password;this.level = level;this.coin = coin;time_setter(); }
    public String getUsername() { return username; }
    public void time_setter() { LocalDateTime date_time=LocalDateTime.now();DateTimeFormatter date_time_formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd--HH:mm:ss");String formatted=date_time.format(date_time_formatter);this.date=formatted; }
    public String getPassword() { return password; }public String getDate() { return date; }
    public int getCoin() { return coin; }public int getLevel() { return level; }
    public void setCoin(int coin) { this.coin = coin; }public void setLevel(int level) { this.level = level; }
}