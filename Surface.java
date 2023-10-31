import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Surface extends JPanel implements MouseListener { public InputProcessor w;Manager manager;
    public Surface(Manager manager) { this.manager = manager;w = new InputProcessor(manager); }
    @Override public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);Graphics2D graphics2D = (Graphics2D) graphics;this.addMouseListener(this);
        graphics2D.drawImage(new ImageIcon("src\\images\\back.png").getImage(), 0, 0, 1300, 700, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Cat.png").getImage(), 0, 0, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Chicken.png").getImage(), 0, 100, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Buffalo.png").getImage(), 0, 200, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Hound.png").getImage(), 0, 300, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\powdermaker.png").getImage(), 0, 400, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\milkproducer.png").getImage(), 0, 500, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Icecream.png").getImage(), 0, 600, 100, 80, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Well.png").getImage(), 500, 0, 200, 150, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Truck.png").getImage(), 1000, 450, 300, 250, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\bf.png").getImage(), 110, 0, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\stringmaker.png").getImage(), 110, 100, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\producedress.png").getImage(), 110, 200, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Hen.png").getImage(), 110, 300, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\chickenmaker.png").getImage(), 110, 400, 100, 100, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Menu.png").getImage(), 1100, 350, 100, 50, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Turn.png").getImage(), 1100, 300, 100, 50, null);
        graphics2D.drawImage(new ImageIcon("src\\images\\Farm.png").getImage(), 900, 0, 300, 100, null);
        for (int i = 0; i < w.manager.producerAnimalsArrayList.size(); i++) { graphics2D.drawImage(new ImageIcon("src\\images\\" + w.manager.producerAnimalsArrayList.get(i).getName().getType() + ".png").getImage(), w.manager.producerAnimalsArrayList.get(i).getX(), w.manager.producerAnimalsArrayList.get(i).getY(), 75, 75, null); }
        for (int i = 0; i < w.manager.eggs.size(); i++) { graphics2D.drawImage(new ImageIcon("src\\images\\Egg.png").getImage(), w.manager.eggs.get(i).getX(), w.manager.eggs.get(i).getY(), 75, 75, null); }
        for (int i = 0; i < w.manager.milks.size(); i++) { graphics2D.drawImage(new ImageIcon("src\\images\\InMilk.png").getImage(), w.manager.milks.get(i).getX(), w.manager.milks.get(i).getY(), 45, 45, null); }
        for (int i = 0; i < w.manager.feathers.size(); i++) { graphics2D.drawImage(new ImageIcon("src\\images\\Feather.png").getImage(), w.manager.feathers.get(i).getX(), w.manager.feathers.get(i).getY(), 75, 75, null); }
        for (int i = 0; i < w.manager.wildAnimalsArrayList.size(); i++) { if(w.manager.wildAnimalsArrayList.get(i).getWildAnimalsName().getCage()!=w.manager.wildAnimalsArrayList.get(i).getWildAnimalsName().getMaxCage()){graphics2D.drawImage(new ImageIcon("src\\images\\" + w.manager.wildAnimalsArrayList.get(i).wildAnimalsName.getType() + "1.png").getImage(), w.manager.wildAnimalsArrayList.get(i).getX(), w.manager.wildAnimalsArrayList.get(i).getY(), 75, 75, null); }else{graphics2D.drawImage(new ImageIcon("src\\images\\" + w.manager.wildAnimalsArrayList.get(i).wildAnimalsName.getType() + ".png").getImage(), w.manager.wildAnimalsArrayList.get(i).getX(), w.manager.wildAnimalsArrayList.get(i).getY(), 75, 75, null); }}
        for (int i = 0; i < w.manager.catArrayList.size(); i++) { graphics2D.drawImage(new ImageIcon("src\\images\\Cat.png").getImage(), w.manager.catArrayList.get(i).getX(), w.manager.catArrayList.get(i).getY(), 75, 75, null); }
        for (int i = 0; i < w.manager.dogArrayList.size(); i++) { graphics2D.drawImage(new ImageIcon("src\\images\\Hound.png").getImage(), w.manager.dogArrayList.get(i).getX(), w.manager.dogArrayList.get(i).getY(), 75, 75, null); }
        for (int i = 0; i < w.manager.grassArrayList.size(); i++) { graphics2D.drawImage(new ImageIcon("src\\images\\Grass.png").getImage(), w.manager.grassArrayList.get(i).getX(), w.manager.grassArrayList.get(i).getY(), 75, 75, null); }
        w.Check();
        if (w.CheckLevel() == true) { InputProcessor.a=false;InputProcessor.jFrame.dispose();w.manager.producerAnimalsArrayList = new ArrayList<>();w.manager.wildAnimalsArrayList = new ArrayList<>();w.manager.WildAnimalsCome = new ArrayList<>();w.manager.grassArrayList = new ArrayList<>();w.manager.watering_system = new Watering_system();w.manager.reservoir = new Reservoir();w.manager.vehicle = new Vehicle();w.manager.dogArrayList = new ArrayList<>();w.manager.catArrayList = new ArrayList<>();w.manager.SetLogger();w.timer = 0;w.manager.eggs = new ArrayList<>();w.manager.feathers = new ArrayList<>();w.manager.milks = new ArrayList<>();w.Menu(w.find);this.addMouseListener(this); }
        if (w.CheckTimeLevel()==true) { w.jFrame.dispose();w.manager.producerAnimalsArrayList = new ArrayList<>();w.manager.wildAnimalsArrayList = new ArrayList<>();w.manager.WildAnimalsCome = new ArrayList<>();w.manager.grassArrayList = new ArrayList<>();w.manager.watering_system = new Watering_system();w.manager.reservoir = new Reservoir();w.manager.vehicle = new Vehicle();w.manager.dogArrayList = new ArrayList<>();w.manager.catArrayList = new ArrayList<>();w.manager.SetLogger();w.timer = 0;InputProcessor.a = false;w.manager.eggs = new ArrayList<>();w.manager.feathers = new ArrayList<>();w.manager.milks = new ArrayList<>();w.Menu(w.find); }
    }
    @Override public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < w.manager.wildAnimalsArrayList.size(); i++) { if (e.getX() >= w.manager.wildAnimalsArrayList.get(i).getX() && e.getX() <= w.manager.wildAnimalsArrayList.get(i).getX() + 75 && e.getY() <= w.manager.wildAnimalsArrayList.get(i).getY() + 75 && e.getY() >= w.manager.wildAnimalsArrayList.get(i).getY()) { w.manager.CageStrike(w.manager.wildAnimalsArrayList.get(i).getX(), w.manager.wildAnimalsArrayList.get(i).getY());this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; } }
        for (int i = 0; i < w.manager.eggs.size(); i++) { if (e.getX() >= w.manager.eggs.get(i).getX() && e.getX() <= w.manager.eggs.get(i).getX() + 75 && e.getY() <= w.manager.eggs.get(i).getY() + 75 && e.getY() >= w.manager.eggs.get(i).getY()) { w.manager.PickupProduct(w.manager.eggs.get(i).getX(), w.manager.eggs.get(i).getY());this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; } }
        for (int i = 0; i < w.manager.feathers.size(); i++) { if (e.getX() >= w.manager.feathers.get(i).getX() && e.getX() <= w.manager.feathers.get(i).getX() + 75 && e.getY() <= w.manager.feathers.get(i).getY() + 75 && e.getY() >= w.manager.feathers.get(i).getY()) { w.manager.PickupProduct(w.manager.feathers.get(i).getX(), w.manager.feathers.get(i).getY());this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; } }
        for (int i = 0; i < w.manager.milks.size(); i++) { if (e.getX() >= w.manager.milks.get(i).getX() && e.getX() <= w.manager.milks.get(i).getX() + 75 && e.getY() <= w.manager.milks.get(i).getY() + 75 && e.getY() >= w.manager.milks.get(i).getY()) { w.manager.PickupProduct(w.manager.milks.get(i).getX(), w.manager.milks.get(i).getY());this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; } }
        if (e.getX() > 0 && e.getX() < 100 && e.getY() > 0 && e.getY() < 100) { w.manager.BuyAnimals("Cat", w.manager.mainAccount);this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getX() > 0 && e.getX() < 100 && e.getY() > 100 && e.getY() < 200) { w.manager.BuyAnimals("Chicken", w.manager.mainAccount);this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getX() > 110 && e.getX() < 210 && e.getY() > 300 && e.getY() < 400) { w.manager.BuyAnimals("Hen", w.manager.mainAccount);this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getX() > 0 && e.getX() < 100 && e.getY() > 200 && e.getY() < 300) { w.manager.BuyAnimals("Buffalo", w.manager.mainAccount);this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getX() > 0 && e.getX() < 100 && e.getY() > 300 && e.getY() < 400) { w.manager.BuyAnimals("Dog", w.manager.mainAccount);this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getX() > 375 && e.getX() < 750 && e.getY() > 175 && e.getY() < 550) { w.manager.Plant(((int) ((e.getX() - 375) / 75)) * 75 + 375, (((int) ((e.getY() - 175) / 75))) * 75 + 175);this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 0 && e.getY() <= 100 && e.getX() >= 110 && e.getX() <= 210) { w.FactoryTimeChecking("Bread");w.manager.WorkFactory("bread");this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 0 && e.getY() <= 150 && e.getX() >= 500 && e.getX() <= 700) { w.manager.ChargeWell();w.WellTimeChecking();this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 100 && e.getY() <= 200 && e.getX() >= 110 && e.getX() <= 210) { w.FactoryTimeChecking("cloth");w.manager.WorkFactory("cloth");this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 200 && e.getY() <= 300 && e.getX() >= 110 && e.getX() <= 210) { w.FactoryTimeChecking("Dress");w.manager.WorkFactory("Dress");this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 600 && e.getY() <= 700 && e.getX() >= 0 && e.getX() <= 100) { w.FactoryTimeChecking("icecream");w.manager.WorkFactory("icecream");this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 400 && e.getY() <= 500 && e.getX() >= 0 && e.getX() <= 100) { w.FactoryTimeChecking("powder");w.manager.WorkFactory("powder");this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 300 && e.getY() <= 350 && e.getX() >= 1100 && e.getX() <= 1200) { w.Turn(1);this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 450 && e.getY() <= 700 && e.getX() >= 1000 && e.getX() <= 1300) { InputProcessor.a = false;InputProcessor.jFrame.dispose();w.TruckMethod(manager);this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 400 && e.getY() <= 500 && e.getX() >= 110 && e.getX() <= 210) { w.manager.ChickenMaker();this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
        else if (e.getY() >= 350 && e.getY() <= 400 && e.getX() >= 1100 && e.getX() <= 1200) { w.jFrame.dispose();w.Choice();this.removeMouseListener(this);this.invalidate();this.validate();this.repaint();return; }
    }@Override public void mousePressed(MouseEvent e) { }@Override public void mouseReleased(MouseEvent e) { }@Override public void mouseEntered(MouseEvent e) { }@Override public void mouseExited(MouseEvent e) { }
}