import javax.swing.*;import java.io.*;import java.util.ArrayList;import java.util.Random;import java.util.Scanner;import java.util.logging.FileHandler;import java.util.logging.Logger;import java.util.logging.SimpleFormatter;
public class Manager {
    ArrayList<Grass> grassArrayList = new ArrayList<Grass>();
    int tasks[]=new int[1000];
    ArrayList<ProducerAnimals> producerAnimalsArrayList = new ArrayList<ProducerAnimals>();
    ArrayList<WildAnimals> wildAnimalsArrayList = new ArrayList<WildAnimals>();
    ArrayList<Cat> catArrayList = new ArrayList<Cat>();
    ArrayList<Dog> dogArrayList = new ArrayList<Dog>();
    ArrayList<Accounts> accountsArrayList = new ArrayList<Accounts>();
    ArrayList<String> WildAnimalsCome = new ArrayList<String>();
    ArrayList<Accounts> accounts = new ArrayList<>();
    Reservoir reservoir = new Reservoir();
    Watering_system watering_system = new Watering_system();
    Vehicle vehicle = new Vehicle();
    Accounts mainAccount;
    Logger logger;
    ArrayList<Egg> eggs = new ArrayList<>();
    ArrayList<Feather> feathers = new ArrayList<>();
    ArrayList<Milk> milks = new ArrayList<>();
    public void walk() {
        for (int i = 0; i < wildAnimalsArrayList.size(); i++) { if (!wildAnimalsArrayList.get(i).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(i).wildAnimalsName.getCage() != 0) { if (RandomXY2() == -1) { wildAnimalsArrayList.get(i).ChangeX(RandomXY()); } else { wildAnimalsArrayList.get(i).ChangeY(RandomXY()); } } }
        if (grassArrayList.size() == 0) { for (int i = 0; i < producerAnimalsArrayList.size(); i++) { if (RandomXY2() == -1) { producerAnimalsArrayList.get(i).ChangeX(RandomXY()); } else { producerAnimalsArrayList.get(i).ChangeY(RandomXY()); } } }
        if (eggs.size() == 0) { for (int i = 0; i < catArrayList.size(); i++) { if (RandomXY2() == -1) {catArrayList.get(i).ChangeX(RandomXY()); } else { catArrayList.get(i).ChangeY(RandomXY()); } } }
        if (wildAnimalsArrayList.size() == 0) {for (int i = 0; i < dogArrayList.size(); i++) { if (RandomXY2() == -1) { dogArrayList.get(i).ChangeX(RandomXY()); } else { dogArrayList.get(i).ChangeY(RandomXY()); } } }
    }

    public void walkForTarget() {
        ArrayList<Double> distance = new ArrayList<Double>();
        for (int i = 0; i < producerAnimalsArrayList.size(); i++) {
            for (int j = 0; j < grassArrayList.size(); j++) {
                distance.add(Math.sqrt(Math.pow(grassArrayList.get(j).getX() - producerAnimalsArrayList.get(i).getX(), 2) + Math.pow(grassArrayList.get(j).getY() - producerAnimalsArrayList.get(i).getY(), 2)));
            }
            if (minimum(distance) != -1) ProducerAnimalsMovingTarget(i, minimum(distance));
            distance = new ArrayList<Double>();
        }
        distance = new ArrayList<Double>();
        for (int i = 0; i < catArrayList.size(); i++) {
            for (int j = 0; j < eggs.size(); j++) {
                distance.add(Math.sqrt(Math.pow(eggs.get(j).getX() - catArrayList.get(i).getX(), 2) + Math.pow(eggs.get(j).getY() - catArrayList.get(i).getY(), 2)));
            }
            if (minimum(distance) != -1) CatMovingTarget(minimum(distance), i);
            distance = new ArrayList<Double>();
        }
        distance = new ArrayList<Double>();
        for (int i = 0; i < dogArrayList.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                distance.add(Math.sqrt(Math.pow(wildAnimalsArrayList.get(j).getX() - dogArrayList.get(i).getX(), 2) + Math.pow(wildAnimalsArrayList.get(j).getY() - dogArrayList.get(i).getY(), 2)));
            }
            if (minimum(distance) != -1) DogMovingTarget(i, minimum(distance));
            distance = new ArrayList<Double>();
        }
        distance = new ArrayList<Double>();
    }

    public void TigerWalk() {
        for (int i = 0; i < wildAnimalsArrayList.size(); i++) {
            if (wildAnimalsArrayList.get(i).wildAnimalsName.getType().equalsIgnoreCase("tiger")) {
                if (RandomXY2() == -1) {
                    wildAnimalsArrayList.get(i).ChangeX(RandomXY());
                } else {
                    wildAnimalsArrayList.get(i).ChangeY(RandomXY());
                }
            }
        }
    }

    public int RandomXY() {
        Random random = new Random();
        int rand = random.nextInt(2);
        if (rand == 0) {
            return -75;
        } else {
            return 75;
        }
    }

    public int RandomXY2() {
        Random random = new Random();
        int rand = random.nextInt(2);
        if (rand == 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public void AnimalEatGrass() {
        ArrayList<ProducerAnimals> producerAnimalsArrayList1 = new ArrayList<ProducerAnimals>();
        for (int i = 0; i < grassArrayList.size(); i++) {
            for (int j = 0; j < producerAnimalsArrayList.size(); j++) {
                if (producerAnimalsArrayList.get(j).getX() == grassArrayList.get(i).getX() && producerAnimalsArrayList.get(j).getY() == grassArrayList.get(i).getY() && producerAnimalsArrayList.get(j).getHungriness() <= 50) {
                    producerAnimalsArrayList1.add(producerAnimalsArrayList.get(j));
                }
            }
            if (!producerAnimalsArrayList1.isEmpty()) {
                int Min = MinHungriness(producerAnimalsArrayList1);
                int find = producerAnimalsArrayList.indexOf(producerAnimalsArrayList1.get(Min));
                producerAnimalsArrayList.get(find).setHungriness(100);
                grassArrayList.remove(i);
            } else {
                producerAnimalsArrayList1 = new ArrayList<ProducerAnimals>();
            }
        }
    }

    private int MinHungriness(ArrayList<ProducerAnimals> p) {
        if (CheckIfAllAnimalsAreSame(p)) {
            Random random = new Random();
            return random.nextInt(p.size());
        } else {
            int MinHunger = 500;
            for (int i = 0; i < p.size(); i++) {
                if (p.get(i).getHungriness() < MinHunger) {
                    MinHunger = p.get(i).getHungriness();
                }
            }
            return FindMinHunger(MinHunger, p);
        }
    }

    private boolean CheckIfAllAnimalsAreSame(ArrayList<ProducerAnimals> p) {
        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < p.size(); j++) {
                if (p.get(i).getHungriness() != p.get(j).getHungriness()) {
                    return false;
                }
            }
        }
        return true;
    }

    private int FindMinHunger(int MinHunger, ArrayList<ProducerAnimals> p) {
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getHungriness() == MinHunger) {
                return i;
            }
        }
        return 0;
    }

    public void CatMovingTarget(int indexOfProduct, int indexOfCat) {
        if (eggs.get(indexOfProduct).getX() >= catArrayList.get(indexOfCat).getX()) {
            if (eggs.get(indexOfProduct).getY() > catArrayList.get(indexOfCat).getY()) {
                catArrayList.get(indexOfCat).setY(catArrayList.get(indexOfCat).getY() + 75);
            } else {
                catArrayList.get(indexOfCat).setY(catArrayList.get(indexOfCat).getY() - 75);
            }
        } else {
            if (eggs.get(indexOfProduct).getX() > catArrayList.get(indexOfCat).getX()) {
                catArrayList.get(indexOfCat).setX(catArrayList.get(indexOfCat).getX() + 75);
            } else {
                catArrayList.get(indexOfCat).setX(catArrayList.get(indexOfCat).getX() - 75);
            }
        }
    }

    public void ProducerAnimalsMovingTarget(int indexOfProducerAnimal, int indexOfGrass) {
        if (grassArrayList.get(indexOfGrass).getX() == producerAnimalsArrayList.get(indexOfProducerAnimal).getX()) {
            if (grassArrayList.get(indexOfGrass).getY() > producerAnimalsArrayList.get(indexOfProducerAnimal).getY()) {
                producerAnimalsArrayList.get(indexOfProducerAnimal).setY(producerAnimalsArrayList.get(indexOfProducerAnimal).getY() + 75);
            } else {
                producerAnimalsArrayList.get(indexOfProducerAnimal).setY(producerAnimalsArrayList.get(indexOfProducerAnimal).getY() - 75);
            }
        } else {
            if (grassArrayList.get(indexOfGrass).getX() > producerAnimalsArrayList.get(indexOfProducerAnimal).getX()) {
                producerAnimalsArrayList.get(indexOfProducerAnimal).setX(producerAnimalsArrayList.get(indexOfProducerAnimal).getX() + 75);
            } else {
                producerAnimalsArrayList.get(indexOfProducerAnimal).setX(producerAnimalsArrayList.get(indexOfProducerAnimal).getX() - 75);
            }
        }
    }

    public void DogMovingTarget(int indexOfDog, int indexOfWildAnimlal) {
        if (dogArrayList.get(indexOfDog).getX() >= wildAnimalsArrayList.get(indexOfWildAnimlal).getX()) {
            if (dogArrayList.get(indexOfDog).getY() < wildAnimalsArrayList.get(indexOfWildAnimlal).getY()) {
                dogArrayList.get(indexOfDog).setY(dogArrayList.get(indexOfDog).getY() + 75);
            } else {
                dogArrayList.get(indexOfDog).setY(dogArrayList.get(indexOfDog).getY() - 75);
            }
        } else {
            if (dogArrayList.get(indexOfDog).getX() < wildAnimalsArrayList.get(indexOfWildAnimlal).getX()) {
                dogArrayList.get(indexOfDog).setX(dogArrayList.get(indexOfDog).getX() + 75);
            } else {
                dogArrayList.get(indexOfDog).setX(dogArrayList.get(indexOfDog).getX() - 75);
            }
        }
    }

    public void CatCatchProduct() {
        for (int i = 0; i < catArrayList.size(); i++) {
            for (int j = 0; j < eggs.size(); j++) {
                if (catArrayList.get(i).getX() == eggs.get(j).getX() && catArrayList.get(i).getY() == eggs.get(j).getY() && reservoir.CheckIfReservoirIsFull(1)) {
                    reservoir.IncreaseEgg();
                    ShowAlert("Egg Catched by Cat");
                    eggs.remove(j);
                }
            }
        }
        for (int i = 0; i < catArrayList.size(); i++) {
            for (int j = 0; j < feathers.size(); j++) {
                if (catArrayList.get(i).getX() == feathers.get(j).getX() && catArrayList.get(i).getY() == feathers.get(j).getY() && reservoir.CheckIfReservoirIsFull(1)) {
                    reservoir.IncreaseFeather();
                    ShowAlert("Feather Catched by Cat");
                    feathers.remove(j);
                }
            }
        }
        for (int i = 0; i < catArrayList.size(); i++) {
            for (int j = 0; j < milks.size(); j++) {
                if (catArrayList.get(i).getX() == milks.get(j).getX() && catArrayList.get(i).getY() == milks.get(j).getY() && reservoir.CheckIfReservoirIsFull(1)) {
                    reservoir.IncreaseInMilk();
                    ShowAlert("Milk Catched by Cat");
                    milks.remove(j);
                }
            }
        }
    }

    public void CatIntersectMeet() {
        for (int i = 0; i < catArrayList.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (catArrayList.get(i).getX() == wildAnimalsArrayList.get(j).getX() && catArrayList.get(i).getY() == wildAnimalsArrayList.get(j).getY() && wildAnimalsArrayList.get(j).wildAnimalsName.getCage() == 0) {
                    reservoir.IncreaseMeet();
                    ShowAlert("a wild animal catched by cat from (" + wildAnimalsArrayList.get(j).getX() + " " + wildAnimalsArrayList.get(j).getY() + ")");
                    logger.info("a wild animal catched by cat from (" + wildAnimalsArrayList.get(j).getX() + " " + wildAnimalsArrayList.get(j).getY() + ")");
                    wildAnimalsArrayList.remove(j);
                }
            }
        }
    }

    public void WildAnimalCollision() {
        for (int i = 0; i < grassArrayList.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (!wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == grassArrayList.get(i).getX() && wildAnimalsArrayList.get(j).getY() == grassArrayList.get(i).getY()) {
                    grassArrayList.remove(i);
                }
            }
        }
        for (int i = 0; i < dogArrayList.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (!wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getY() == dogArrayList.get(i).getY() && wildAnimalsArrayList.get(j).getX() == dogArrayList.get(i).getX()) {
                    wildAnimalsArrayList.remove(j);
                    dogArrayList.remove(i);
                }
            }
        }
        for (int i = 0; i < eggs.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (!wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == eggs.get(i).getX() && wildAnimalsArrayList.get(j).getY() == eggs.get(i).getY()) {
                    eggs.remove(i);
                }
            }
        }
        for (int i = 0; i < feathers.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (!wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == feathers.get(i).getX() && wildAnimalsArrayList.get(j).getY() == feathers.get(i).getY()) {
                    feathers.remove(i);
                }
            }
        }
        for (int i = 0; i < milks.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (!wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == milks.get(i).getX() && wildAnimalsArrayList.get(j).getY() == milks.get(i).getY()) {
                    milks.remove(i);
                }
            }
        }
        for (int i = 0; i < catArrayList.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (!wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == catArrayList.get(i).getX() && wildAnimalsArrayList.get(j).getY() == catArrayList.get(i).getY()) {
                    catArrayList.remove(i);
                }
            }
        }
    }

    public void TigerCollision() {
        for (int i = 0; i < grassArrayList.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == grassArrayList.get(i).getX() && wildAnimalsArrayList.get(j).getY() == grassArrayList.get(i).getY()) {
                    grassArrayList.remove(i);
                }
            }
        }
        for (int i = 0; i < eggs.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == eggs.get(i).getX() && wildAnimalsArrayList.get(j).getY() == eggs.get(i).getY()) {
                    eggs.remove(i);
                }
            }
        }
        for (int i = 0; i < feathers.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == feathers.get(i).getX() && wildAnimalsArrayList.get(j).getY() == feathers.get(i).getY()) {
                    feathers.remove(i);
                }
            }
        }
        for (int i = 0; i < milks.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == milks.get(i).getX() && wildAnimalsArrayList.get(j).getY() == milks.get(i).getY()) {
                    milks.remove(i);
                }
            }
        }
        for (int i = 0; i < dogArrayList.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getY() == dogArrayList.get(i).getY() && wildAnimalsArrayList.get(j).getX() == dogArrayList.get(i).getX()) {
                    wildAnimalsArrayList.remove(j);
                    dogArrayList.remove(i);
                }
            }
        }
        for (int i = 0; i < catArrayList.size(); i++) {
            for (int j = 0; j < wildAnimalsArrayList.size(); j++) {
                if (wildAnimalsArrayList.get(j).wildAnimalsName.getType().equalsIgnoreCase("tiger") && wildAnimalsArrayList.get(j).getX() == catArrayList.get(i).getX() && wildAnimalsArrayList.get(j).getY() == catArrayList.get(i).getY()) {
                    catArrayList.remove(i);
                }
            }
        }
    }
    public void PickupProduct(int x, int y) {
        for (int i = 0; i < eggs.size(); i++) { if (eggs.get(i).getX() == x && eggs.get(i).getY() == y && reservoir.CheckIfReservoirIsFull(1)) { reservoir.IncreaseEgg();ShowAlert("Egg Picked from " + eggs.get(i).getX() + " , " + eggs.get(i).getY()); }eggs.remove(i); }
        for (int i = 0; i < milks.size(); i++) { if (milks.get(i).getX() == x && milks.get(i).getY() == y && reservoir.CheckIfReservoirIsFull(1)) { reservoir.IncreaseInMilk();ShowAlert("Milk Picked from " + milks.get(i).getX() + " , " + milks.get(i).getY()); }milks.remove(i); }
        for (int i = 0; i < feathers.size(); i++) { if (feathers.get(i).getX() == x && feathers.get(i).getY() == y && reservoir.CheckIfReservoirIsFull(1)) { reservoir.IncreaseFeather();ShowAlert("Feather Picked from " + feathers.get(i).getX() + " , " + feathers.get(i).getY()); }feathers.remove(i); }
    }
    public void BuyAnimals(String animal, Accounts acc) {
        if (animal.equalsIgnoreCase("chicken")) {
            if (acc.getCoin() >= 100) {
                acc.setCoin(acc.getCoin() - 100);
                ProducerAnimalName producerAnimalName = ProducerAnimalName.Chicken;
                ProducerAnimals producerAnimals = new ProducerAnimals(producerAnimalName);
                producerAnimalsArrayList.add(producerAnimals);
                ShowAlert("You bought chicken");
                logger.info("You bought chicken");
            } else {
                ShowAlert("Coin is not enough");
                logger.warning("coin is not enough for buying chicken");
            }
        } else if (animal.equalsIgnoreCase("Hen")) {
            if (acc.getCoin() >= 200) {
                acc.setCoin(acc.getCoin() - 200);
                ProducerAnimalName producerAnimalName = ProducerAnimalName.Hen;
                ProducerAnimals producerAnimals = new ProducerAnimals(producerAnimalName);
                producerAnimalsArrayList.add(producerAnimals);
                ShowAlert("you bought Ostrich");
                logger.info("you bought Ostrich");
            } else {
                ShowAlert("Coin is not enough");
                logger.warning("coin is not enough for buying ostrich");
            }
        } else if (animal.equalsIgnoreCase("Buffalo")) {
            if (acc.getCoin() >= 400) {
                acc.setCoin(acc.getCoin() - 400);
                ProducerAnimalName producerAnimalName = ProducerAnimalName.Buffalo;
                ProducerAnimals producerAnimals = new ProducerAnimals(producerAnimalName);
                producerAnimalsArrayList.add(producerAnimals);
                ShowAlert("you bought Buffalo");
                logger.info("you bought Buffalo");
            } else {
                ShowAlert("Coin is not enough");
            }
        } else if (animal.equalsIgnoreCase("dog")) {
            if (acc.getCoin() >= 100) {
                acc.setCoin(acc.getCoin() - 100);
                Dog dog = new Dog();
                dogArrayList.add(dog);
                ShowAlert("you bought dog");
                logger.info("you bought dog");
            } else {
                ShowAlert("Coin is not enough");
                logger.warning("coin s not enough for buying dog");
            }
        } else if (animal.equalsIgnoreCase("cat")) {
            if (acc.getCoin() >= 150) {
                acc.setCoin(acc.getCoin() - 150);
                Cat cat = new Cat();
                catArrayList.add(cat);
                ShowAlert("you bought cat");
                logger.info("you bought cat");
            } else {
                ShowAlert("Coin is not enough");
                logger.warning("coin is not enough for buying cat");
            }
        }
    }
    public void Plant(int x, int y) {
        if (x > 6 * 75 + 300 || x < 375 || y > 6 * 75 + 100 || y < 175) { logger.warning("wrong input for plant");return; }
        if (!watering_system.IsEmpty()) { Grass grass = new Grass(x, y);grassArrayList.add(grass);watering_system.WaterConsume();ShowAlert("grass added at (" + x + " " + y + ")");logger.info("grass added at (" + x + " " + y + ")"); }
        else { ShowAlert("non existence of water");logger.warning("there is no water to plant a grass"); }
    }
    public void GrassDanger() { if (grassArrayList.size() == 0) { logger.warning("there is no grass at the yard ..plant one"); } }
    public void AddWildAnimal(String animal) {
        if (animal.equalsIgnoreCase("bear")) { WildAnimalsName wildAnimalsName = WildAnimalsName.Bear;WildAnimals wildAnimals = new WildAnimals(wildAnimalsName);wildAnimalsArrayList.add(wildAnimals);logger.info("bear added on random XY_cordinate based on missions file"); }
        else if (animal.equalsIgnoreCase("lion")) { WildAnimalsName wildAnimalsName = WildAnimalsName.Lion;WildAnimals wildAnimals = new WildAnimals(wildAnimalsName);wildAnimalsArrayList.add(wildAnimals);logger.info("lion added on random XY_cordinate based on missions file"); }
        else if (animal.equalsIgnoreCase("tiger")) { WildAnimalsName wildAnimalsName = WildAnimalsName.Tiger;WildAnimals wildAnimals = new WildAnimals(wildAnimalsName);wildAnimalsArrayList.add(wildAnimals);logger.info("tiger added on random XY_cordinate based on missions file"); }
    }
    public void CageStrike(int x, int y) { for (int i = 0; i < wildAnimalsArrayList.size(); i++) { if (wildAnimalsArrayList.get(i).getX() == x && wildAnimalsArrayList.get(i).getY() == y) { wildAnimalsArrayList.get(i).wildAnimalsName.CageDamage();ShowAlert("Cage done"); } } }
    public void UnCage() { for (int i = 0; i < wildAnimalsArrayList.size(); i++) { if (wildAnimalsArrayList.get(i).wildAnimalsName.getCage() < wildAnimalsArrayList.get(i).wildAnimalsName.getMaxCage()) { wildAnimalsArrayList.get(i).wildAnimalsName.setCage(wildAnimalsArrayList.get(i).wildAnimalsName.getCage() + 1);logger.info("cage hit "); } } }
    public void CheckForAnimalsProduct() {
        for (int i = 0; i < producerAnimalsArrayList.size(); i++) {
            if (producerAnimalsArrayList.get(i).CheckForProduct() != null) {
                switch (producerAnimalsArrayList.get(i).CheckForProduct()) {
                    case "egg": { final int X = producerAnimalsArrayList.get(i).getX();final int Y = producerAnimalsArrayList.get(i).getY();Egg egg = new Egg(X, Y, 3);eggs.add(egg);break; }
                    case "feather": { final int X = producerAnimalsArrayList.get(i).getX();final int Y = producerAnimalsArrayList.get(i).getY();Feather feather = new Feather(X, Y, 7);feathers.add(feather);break; }
                    case "milk": { final int X = producerAnimalsArrayList.get(i).getX();final int Y = producerAnimalsArrayList.get(i).getY();Milk milk = new Milk(X, Y, 10);milks.add(milk);break; }
                }
            }
        }
    }
    public void Hurt() { for (int i = 0; i < producerAnimalsArrayList.size(); i++) { producerAnimalsArrayList.get(i).hurt();if (!producerAnimalsArrayList.get(i).isAlive()) { producerAnimalsArrayList.remove(i); } } }
    public void ReduceProduct() {
        for (int i = 0; i < eggs.size(); i++) { eggs.get(i).ReduceSec();if (eggs.get(i).getSec() == 0) { eggs.remove(i); } }
        for (int i = 0; i < feathers.size(); i++) { feathers.get(i).ReduceSec();if (feathers.get(i).getSec() == 0) { feathers.remove(i); } }
        for (int i = 0; i < milks.size(); i++) { milks.get(i).ReduceSec();if (milks.get(i).getSec() == 0) { milks.remove(i); }}
    }
    public void ReduceTimeOfProducing() { for (int i = 0; i < producerAnimalsArrayList.size(); i++) { producerAnimalsArrayList.get(i).getName().ReduceTime(); } }
    public void WorkFactory(String factory) {
        if (factory.equalsIgnoreCase("Powder")) {
            reservoir.ProducePowder();
            ShowAlert("produce powder was ordered");
            logger.info("produce powder was ordered");
        } else if (factory.equalsIgnoreCase("Cloth")) {
            reservoir.ProduceCloth();
            ShowAlert("produce cloth was ordered");
            logger.info("produce cloth was ordered");
        } else if (factory.equalsIgnoreCase("Dress")) {
            reservoir.ProduceDress();
            ShowAlert("produce dress was ordered");
            logger.info("produce dress was ordered");
        } else if (factory.equalsIgnoreCase("Bread")) {
            reservoir.ProduceBread();
            ShowAlert("produce bread was ordered");
            logger.info("produce bread was ordered");
        } else if (factory.equalsIgnoreCase("Milk")) {
            reservoir.ProduceMilk();
            ShowAlert("produce milk was ordered");
            logger.info("produce milk was ordered");
        } else if (factory.equalsIgnoreCase("IceCream")) {
            reservoir.ProduceIceCream();
            ShowAlert("produce IceCream was ordered");
            logger.info("produce IceCream was ordered");
        } else {
            System.out.println("wrong input");
            logger.warning("wrong input for factory order");
        }
    }
    public void TruckLoad(String load) { vehicle.AddObjectToVehicle(load, reservoir);ShowAlert(load + " added to truck");logger.info(load + " added to truck"); }
    public void TruckGo(Accounts acc) { vehicle.SellEveryThing(acc);logger.info("truck went to sell everything"); }
    public boolean CheckIfThereIsEgg() { if (reservoir.getEgg() != 0) { return true; } else { return false; } }
    public boolean CheckIfThereIsFeather() { if (reservoir.getFeather() != 0) { return true; } else { return false; } }
    public boolean CheckIfThereIsCloth() { if (reservoir.getCloth() != 0) { return true; } else { return false; } }
    public boolean CheckIfThereIsPowder() { if (reservoir.getPowder() != 0) { return true; } else { return false; } }
    public boolean CheckIfThereIsMilk() { if (reservoir.getIn_milk() != 0) { return true; } else { return false; } }
    public boolean CheckIfThereIsMilkForIceCream() { if (reservoir.getOut_milk() != 0) { return true; } else { return false; } }
    public boolean CheckIfWellIsOk() { if (watering_system.IsEmpty()) { return false; } else { return true; }}
    public void ChargeWell() { watering_system.WaterWellCharging();logger.info("well charged "); }
    public boolean CheckLevel1() {
        if (vehicle.getEgg()>=tasks[0]&&vehicle.getIn_milk()>=tasks[1]&&vehicle.getFeather()>=tasks[2]&&vehicle.getPowder()>=tasks[3]&&vehicle.getOut_milk()>=tasks[4]&&vehicle.getCloth()>=tasks[5]&&vehicle.getBread()>=tasks[6]&&vehicle.getIceCream()>=tasks[7]&&vehicle.getDress()>=tasks[8]) {
            vehicle.setInMilk(0);vehicle.setEgg(0);vehicle.setEgg(0);vehicle.setBread(0);vehicle.setCloth(0);vehicle.setIceCream(0);vehicle.setOutMilk(0);vehicle.setPowder(0);vehicle.setDress(0);return true;
        }return false;
    }
    public boolean CheckLevel2() {
        if (vehicle.getEgg()>=tasks[9]&&vehicle.getIn_milk()>=tasks[10]&&vehicle.getFeather()>=tasks[11]&&vehicle.getPowder()>=tasks[12]&&vehicle.getOut_milk()>=tasks[13]&&vehicle.getCloth()>=tasks[14]&&vehicle.getBread()>=tasks[15]&&vehicle.getIceCream()>=tasks[16]&&vehicle.getDress()>=tasks[17]) {
            vehicle.setInMilk(0);vehicle.setEgg(0);vehicle.setEgg(0);vehicle.setBread(0);vehicle.setCloth(0);vehicle.setIceCream(0);vehicle.setOutMilk(0);vehicle.setPowder(0);vehicle.setDress(0);return true;
        }
        return false;
    }
    public boolean CheckLevel3() {
        if (vehicle.getEgg()>=tasks[18]&&vehicle.getIn_milk()>=tasks[19]&&vehicle.getFeather()>=tasks[20]&&vehicle.getPowder()>=tasks[21]&&vehicle.getOut_milk()>=tasks[22]&&vehicle.getCloth()>=tasks[23]&&vehicle.getBread()>=tasks[24]&&vehicle.getIceCream()>=tasks[25]&&vehicle.getDress()>=tasks[26]) {
            vehicle.setInMilk(0);vehicle.setEgg(0);vehicle.setEgg(0);vehicle.setBread(0);vehicle.setCloth(0);vehicle.setIceCream(0);vehicle.setOutMilk(0);vehicle.setPowder(0);vehicle.setDress(0);return true;
        }
        return false;
    }
    public boolean CheckLevel4() {
        if (vehicle.getEgg()>=tasks[27]&&vehicle.getIn_milk()>=tasks[28]&&vehicle.getFeather()>=tasks[29]&&vehicle.getPowder()>=tasks[30]&&vehicle.getOut_milk()>=tasks[31]&&vehicle.getCloth()>=tasks[32]&&vehicle.getBread()>=tasks[33]&&vehicle.getIceCream()>=tasks[34]&&vehicle.getDress()>=tasks[35]) {
            vehicle.setInMilk(0);vehicle.setEgg(0);vehicle.setEgg(0);vehicle.setBread(0);vehicle.setCloth(0);vehicle.setIceCream(0);vehicle.setOutMilk(0);vehicle.setPowder(0);vehicle.setDress(0);return true;
        }
        return false;
    }
    public boolean CheckLevel5() {
        if (vehicle.getEgg()>=tasks[36]&&vehicle.getIn_milk()>=tasks[37]&&vehicle.getFeather()>=tasks[38]&&vehicle.getPowder()>=tasks[39]&&vehicle.getOut_milk()>=tasks[40]&&vehicle.getCloth()>=tasks[41]&&vehicle.getBread()>=tasks[42]&&vehicle.getIceCream()>=tasks[43]&&vehicle.getDress()>=tasks[44]) {
            vehicle.setInMilk(0);vehicle.setEgg(0);vehicle.setEgg(0);vehicle.setBread(0);vehicle.setCloth(0);vehicle.setIceCream(0);vehicle.setOutMilk(0);vehicle.setPowder(0);vehicle.setDress(0);return true;
        }
        return false;
    }
    public void ReadAccountsFiles() {
        try { File file = new File("src\\Files\\Accounts.txt");Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) { String line = scanner.nextLine();String[] split = line.split(" ");Accounts account = new Accounts(split[0], split[1], Integer.parseInt(split[3]), Integer.parseInt(split[2]));accountsArrayList.add(account); }
            logger.info("Accounts.txt file read successfully");
        } catch (FileNotFoundException e) { e.printStackTrace();logger.warning("can't read Accounts.txt"); }
    }
    public void WriteOnAccountsFile() {
        try {
            FileWriter fileWriter = new FileWriter("src\\Files\\Accounts.txt");
            for (int i = 0; i < accountsArrayList.size()-1; i++) {
                fileWriter.write(accountsArrayList.get(i).getUsername() + " " + accountsArrayList.get(i).getPassword() + " " + accountsArrayList.get(i).getCoin() + " " + accountsArrayList.get(i).getLevel() + " " + accountsArrayList.get(i).getDate() + "\n");
            }fileWriter.write(accountsArrayList.get(accountsArrayList.size()-1).getUsername() + " " + accountsArrayList.get(accountsArrayList.size()-1).getPassword() + " " + accountsArrayList.get(accountsArrayList.size()-1).getCoin() + " " + accountsArrayList.get(accountsArrayList.size()-1).getLevel() + " " + accountsArrayList.get(accountsArrayList.size()-1).getDate());
            fileWriter.close();
            logger.info("write on Accounts.txt was ok");
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("can't write on Account.txt");
        }
    }
    public void ReadMissionFile() { int counter = 0;
        try { File file = new File("src\\Files\\missions.txt");Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) { String line = scanner.nextLine();String[] split = line.split(" ");WildAnimalsCome.add(split[3]);WildAnimalsCome.add(split[4]);WildAnimalsCome.add(Integer.toString(counter));counter++; }logger.info("missions.txt read successfully");
        } catch (FileNotFoundException e) { e.printStackTrace();logger.warning("can't read missions.txt"); }
    }
    public void CheckForAddWildAnimalsFromFile(int timer) { WildAnimalsCome = new ArrayList<>();ReadMissionFile();
        if (mainAccount.getLevel() == 1 && timer == Integer.parseInt(WildAnimalsCome.get(1))) { AddWildAnimal(WildAnimalsCome.get(0)); }
        else if (mainAccount.getLevel() == 2 && timer == Integer.parseInt(WildAnimalsCome.get(4))) { AddWildAnimal(WildAnimalsCome.get(3)); }
        else if (mainAccount.getLevel() == 3 && timer == Integer.parseInt(WildAnimalsCome.get(7))) { AddWildAnimal(WildAnimalsCome.get(6)); }
        else if (mainAccount.getLevel() == 4 && timer == Integer.parseInt(WildAnimalsCome.get(10))) { AddWildAnimal(WildAnimalsCome.get(9)); }
        else if (mainAccount.getLevel() == 5 && timer == Integer.parseInt(WildAnimalsCome.get(13))) { AddWildAnimal(WildAnimalsCome.get(12)); }
    }
    public void SetLogger() { logger = Logger.getLogger("MyLog");FileHandler fileHandler;try { fileHandler = new FileHandler("LogFile.log");logger.addHandler(fileHandler);SimpleFormatter formatter = new SimpleFormatter();fileHandler.setFormatter(formatter);logger.setUseParentHandlers(false); } catch (SecurityException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } }
    public int minimum(ArrayList<Double> distance) { double m = 100000000;for (int i = 0; i < distance.size(); i++) { if (distance.get(i) <= m) { m = distance.get(i); } }return distance.indexOf(m); }
    public void ShowAlert(String Alert) {
        JOptionPane.showMessageDialog(null, Alert);
    }
    public void ChickenMaker() { if (reservoir.getEgg() != 0) { reservoir.DecreaseEgg();ProducerAnimalName producerAnimalName = ProducerAnimalName.Chicken;ProducerAnimals producerAnimals = new ProducerAnimals(producerAnimalName);producerAnimalsArrayList.add(producerAnimals); } else { ShowAlert("egg is not enough for making chicken"); } }
    public void Clear() throws FileNotFoundException { File file=new File("src\\Files\\Accounts.txt");PrintWriter writer = null;try { writer = new PrintWriter(file); } catch (FileNotFoundException e) { e.printStackTrace(); }writer.print("");writer.close(); }
    public void Done()throws FileNotFoundException{ Clear();WriteOnAccountsFile();}
    public void ReadTasksFile() {int c=0;
        try { File file = new File("src\\Files\\tasks.txt");Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) { String line = scanner.nextLine();String[] split = line.split(" ");tasks[c]=Integer.parseInt(split[4]);tasks[c+1]=Integer.parseInt(split[6]);tasks[c+2]=Integer.parseInt(split[8]);tasks[c+3]=Integer.parseInt(split[10]);tasks[c+4]=Integer.parseInt(split[12]);tasks[c+5]=Integer.parseInt(split[14]);tasks[c+6]=Integer.parseInt(split[16]);tasks[c+7]=Integer.parseInt(split[18]);tasks[c+8]=Integer.parseInt(split[20]);c+=9; }
            logger.info("tasks.txt read successfully"); }
        catch (FileNotFoundException e) { e.printStackTrace();logger.warning("can't read tasks.txt"); }
    }
}