import javax.swing.*;public class Watering_system {
    private int WaterWellCapacity;
    public boolean IsEmpty() { if (WaterWellCapacity == 0) { return true; }return false; }
    public void WaterWellCharging() { if (WaterWellCapacity == 0) { WaterWellCapacity = 5;ShowAlert("well charged..."); } else { ShowAlert("Well is not empty yet"); }}
    public Watering_system() { WaterWellCapacity = 5; }
    public void WaterConsume() { if (WaterWellCapacity != 0) { WaterWellCapacity--; } else {ShowAlert("non existence of water"); } }
    public void ShowAlert(String Alert) { JOptionPane.showMessageDialog(null,Alert); }
}