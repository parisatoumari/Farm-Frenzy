public class Reservoir {
    private int Egg;private int Feather;private int In_milk;private int Powder;private int Cloth;private int Out_milk;private int Dress;private int IceCream;private int Bread;private int Meet;
    public int getEgg() { return Egg; }public int getFeather() { return Feather; }public int getCloth() { return Cloth;}public int getPowder() { return Powder; }public int getIn_milk() { return In_milk;}public int getOut_milk() { return Out_milk; }public int getDress() { return Dress; }public int getBread() { return Bread; }public int getMeet() { return Meet; }public int getIceCream() { return IceCream; }
    public Reservoir() { Egg = 0;Powder = 0;Feather = 0;Cloth = 0;Out_milk = 0;In_milk = 0;Dress = 0;IceCream = 0;Meet = 0;Bread = 0; }
    public boolean CheckIfReservoirIsFull(int a) { if (a + Egg + Powder + Feather + Cloth + Out_milk + In_milk + Dress + IceCream + Meet <= 30) { return true; }return false; }
    public void IncreaseEgg() { if (CheckIfReservoirIsFull(1)) { Egg++; } }public void IncreaseFeather() { if (CheckIfReservoirIsFull(1)) { Feather++; } }
    public void IncreaseInMilk() { if (CheckIfReservoirIsFull(1)) { In_milk++; } }public void IncreasePowder() { if (CheckIfReservoirIsFull(2)) { Powder++; } }public void IncreaseCloth() { if (CheckIfReservoirIsFull(2)) { Cloth++; } }
    public void IncreaseOutMilk() { if (CheckIfReservoirIsFull(2)) { Out_milk++; } }public void IncreaseDress() { if (CheckIfReservoirIsFull(4)) { Dress++; } }public void IncreaseBread() { if (CheckIfReservoirIsFull(4)) { Bread++; } }
    public void IncreaseIceCream() { if (CheckIfReservoirIsFull(4)) { IceCream++; } }public void IncreaseMeet() { if (CheckIfReservoirIsFull(15)) { Meet++; } }public void DecreaseEgg() { Egg--; }
    public void DecreaseFeather() { Feather--; }public void DecreaseInMilk() { In_milk--;}public void DecreasePowder() { Powder--; }
    public void DecreaseCloth() { Cloth--; }public void DecreaseOutMilk() { Out_milk--; }public void DecreaseDress() { Dress--; }
    public void DecreaseBread() { Bread--; }public void DecreaseIceCream() { IceCream--; }public void DecreaseMeet() { Meet--; }
    public void ProducePowder() { if (Egg != 0) { Egg--;IncreasePowder(); } else { System.out.println("Egg is not enough or there is not a PowderProducerFactory"); } }
    public void ProduceCloth() { if (Feather != 0) { Feather--;IncreaseCloth(); } else { System.out.println("Feather is not enough or there is not a ClothProducerFactory"); } }
    public void ProduceMilk() { if (In_milk != 0) { In_milk--;IncreaseOutMilk(); } else { System.out.println("milk is not enough or there is not a MilkProducerFactory"); } }
    public void ProduceBread() { if (Powder != 0) { Powder--;IncreaseBread(); } else { System.out.println("powder is not enough or there is not a BreadProducerFactory"); } }
    public void ProduceDress() { if (Cloth != 0) { Cloth--;IncreaseDress(); } else { System.out.println("Cloth is not enough or there is not a DressProducerFactory"); } }
    public void ProduceIceCream() { if (Out_milk != 0) { Out_milk--;IncreaseIceCream(); } else { System.out.println("milk is not enough or there is not an IceCreamProducerFactory"); } }
}