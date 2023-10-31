import javax.swing.*;import java.util.ArrayList;
public class Vehicle { public int Sum = 0;public int Price = 0;ArrayList<String> Burden;
    private int Egg;private int Feather;private int In_milk;private int Powder;private int Cloth;private int Out_milk;private int Dress;private int IceCream;private int Bread;private int Meat;
    public int getEgg() { return Egg; }public void setEgg(int n){ Egg=n; }public int getFeather() { return Feather; }public void setFeather(int n){ Feather=n; }public int getCloth() { return Cloth; }
    public void setCloth(int n){ Cloth=n; }public int getPowder() { return Powder; }
    public void setPowder(int n){ Powder=n; }public int getIn_milk() { return In_milk; }
    public void setInMilk(int n){ In_milk=n; }
    public int getOut_milk() { return Out_milk; }public void setOutMilk(int n){ Out_milk=n; }public int getDress() { return Dress; }
    public void setDress(int n){ Dress=n; }public int getBread() { return Bread; }public void setBread(int n){ Bread=n; }public int getMeet() { return Meat; }public void setMeat(int n){ Meat=n; }public int getIceCream() { return IceCream; }
    public void setIceCream(int n){ IceCream=n; }
    public Vehicle() { Sum = 0;Price = 0;Burden = new ArrayList<String>(); }
    public void AddObjectToVehicle(String Good, Reservoir reservoir) { switch (Good) { case "Egg": { if (Sum + 1 <= 15) { Sum++;Burden.add(Good);reservoir.DecreaseEgg(); } else { System.out.println("Vehicle Capacity is full..."); }return; }
            case "Feather": { if (Sum + 1 <= 15) { Sum++;Burden.add(Good);reservoir.DecreaseFeather(); } else { System.out.println("Vehicle Capacity is full..."); }return;}
            case "InMilk": { if (Sum + 1 <= 15) { Sum++;Burden.add(Good);reservoir.DecreaseInMilk(); } else { System.out.println("Vehicle Capacity is full..."); }return; }
            case "OutMilk": { if (Sum + 2 <= 15) { Sum += 2;Burden.add(Good);reservoir.DecreaseOutMilk(); } else { System.out.println("Vehicle Capacity is full..."); }return;}
            case "Powder": { if (Sum + 2 <= 15) { Sum += 2;Burden.add(Good);reservoir.DecreasePowder(); } else { System.out.println("Vehicle Capacity is full..."); }return; }
            case "Cloth": { if (Sum + 2 <= 15) { Sum += 2;Burden.add(Good);reservoir.DecreaseCloth(); } else { System.out.println("Vehicle Capacity is full..."); }return; }
            case "Dress": { if (Sum + 4 <= 15) { Sum += 4;Burden.add(Good);reservoir.DecreaseDress(); } else { System.out.println("Vehicle Capacity is full..."); }return;}
            case "IceCream": { if (Sum + 4 <= 15) { Sum += 4;Burden.add(Good);reservoir.DecreaseIceCream(); } else { System.out.println("Vehicle Capacity is full..."); }return; }
            case "Bread": { if (Sum + 4 <= 15) { Sum += 4;Burden.add(Good);reservoir.DecreaseBread(); } else { System.out.println("Vehicle Capacity is full..."); }return; }
            case "Meat": { if (Sum + 15 <= 15) { Sum += 15;Burden.add(Good);reservoir.DecreaseMeet(); } else { System.out.println("Vehicle Capacity is full..."); }return; }
        } }
    public void EliminateFromVehicle(String Good, Reservoir reservoir) { switch (Good) {
            case "Egg": { if (Burden.contains(Good)) { Sum--;Burden.remove(Good);reservoir.IncreaseEgg(); } else { System.out.println("Object not found..."); }return; }
            case "Feather": { if (Burden.contains(Good)) { Sum--;Burden.remove(Good);reservoir.IncreaseFeather(); } else { System.out.println("Object not found..."); }return; }
            case "InMilk": { if (Burden.contains(Good)) { Sum--;Burden.remove(Good);reservoir.IncreaseInMilk(); } else { System.out.println("Object not found..."); } }
            case "OutMilk": { if (Burden.contains(Good)) { Sum -= 2;Burden.remove(Good);reservoir.IncreaseOutMilk(); } else { System.out.println("Object not found..."); }return; }
            case "Powder": { if (Burden.contains(Good)) { Sum -= 2;Burden.remove(Good);reservoir.ProducePowder(); } else { System.out.println("Object not found..."); }return; }
            case "Cloth": { if (Burden.contains(Good)) { Sum -= 2;Burden.remove(Good);reservoir.IncreaseCloth(); } else { System.out.println("Object not found..."); }return; }
            case "Dress": { if (Burden.contains(Good)) { Sum -= 4;Burden.remove(Good);reservoir.IncreaseDress(); } else { System.out.println("Object not found..."); }return; }
            case "IceCream": { if (Burden.contains(Good)) { Sum -= 4;Burden.remove(Good);reservoir.IncreaseIceCream(); } else { System.out.println("Object not found..."); }return; }
            case "Bread": { if (Burden.contains(Good)) { Sum -= 4;Burden.remove(Good);reservoir.IncreaseBread(); } else { System.out.println("Object not found..."); }return; }
            case "Meat": { if (Burden.contains(Good)) { Sum -= 15;Burden.remove(Good);reservoir.IncreaseMeet(); } else { System.out.println("Object not found..."); }return; }
        } }
    public int CalculateSellPrice() { int Price = 0;for (int i = 0; i < Burden.size(); i++) { switch (Burden.get(i)) { case "Egg": { Price += 15;Egg++; break;} case "Feather": { Price += 20;Feather++;break; } case "InMilk": { Price += 25;In_milk++;break; } case "OutMilk": { Price += 40;Out_milk++;break; } case "Powder": { Price += 50;Powder++;break; } case "Cloth": { Price += 60;Cloth++;break; } case "Dress": { Price += 100;Dress++; break;} case "IceCream": { Price += 120;IceCream++;break; } case "Bread": { Price += 80;Bread++; break;} case "Meat": { Price += 300;Meat++;break; } } }return Price; }
    public void ShowAlert(String Alert) {
        JOptionPane.showMessageDialog(null, Alert);
    }
    public void SellEveryThing(Accounts acc) { int Price = CalculateSellPrice();acc.setCoin(acc.getCoin() + Price);Burden.clear();ShowAlert("truck went to sell everything");ShowAlert("sell was successfully your income was : " + Price + " and your coin now is : " + acc.getCoin()); }
}