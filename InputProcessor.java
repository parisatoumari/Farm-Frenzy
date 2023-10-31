import javax.swing.*;import javax.swing.border.Border;import java.awt.*;import java.io.FileNotFoundException;import java.util.ArrayList;
public class InputProcessor extends JPanel { public static boolean a=true;public static JFrame jFrame=new JFrame();public static JFrame jFrame2=new JFrame();
    private TextField textField1,textField2;private ArrayList<String> Handle = new ArrayList<String>();public int find=-1;public Manager manager;
    public static ThreadHandling t1;
    public InputProcessor(Manager manager) {
        this.manager = manager;
    }public int timer = 0;
    public void run() { manager.SetLogger();manager.ReadTasksFile();jFrame=CreateFrame();ShowWelcomePage(); }
    public void FactoryTimeChecking(String split) {
        if (split.equalsIgnoreCase("powder")) {
            if (!Handle.contains("AddPowder") && manager.CheckIfThereIsEgg()) {
                Handle.add("AddPowder");
                Handle.add("4");
            } else if (Handle.contains("AddPowder")) {
                manager.ShowAlert("factory is busy...");
            }
        } else if (split.equalsIgnoreCase("cloth")) {
            if (!Handle.contains("AddCloth") && manager.CheckIfThereIsFeather()) {
                Handle.add("AddCloth");
                Handle.add("5");
            } else if (Handle.contains("AddCloth")) {
                {
                    manager.ShowAlert("factory is busy...");
                }
            }
        } else if (split.equalsIgnoreCase("Dress")) {
            if (!Handle.contains("AddDress") && manager.CheckIfThereIsCloth()) {
                Handle.add("AddDress");
                Handle.add("6");
            } else if (Handle.contains("AddDress")) {
                manager.ShowAlert("factory is busy...");
            }
        } else if (split.equalsIgnoreCase("bread")) {
            if (!Handle.contains("AddBread") && manager.CheckIfThereIsPowder()) {
                Handle.add("AddBread");
                Handle.add("5");
            } else if (Handle.contains("AddBread")) {
                manager.ShowAlert("factory is busy...");
            }
        } else if (split.equalsIgnoreCase("Milk")) {
            if (!Handle.contains("AddMilk") && manager.CheckIfThereIsMilk()) {
                Handle.add("AddMilk");
                Handle.add("6");
            } else if (Handle.contains("AddMilk")) {
                manager.ShowAlert("factory is busy...");
            }
        } else if (split.equalsIgnoreCase("AddIceCream")) {
            if (!Handle.contains("AddIceCream") && manager.CheckIfThereIsMilkForIceCream()) {
                Handle.add("AddIceCream");
                Handle.add("7");
            } else if (Handle.contains("AddIceCream")) {
                manager.ShowAlert("factory is busy...");
            }
        }
    }
    public void WellTimeChecking() { if (manager.CheckIfWellIsOk() && (!Handle.contains("AddWell"))) { Handle.add("AddWell");Handle.add("3"); }
        else if (manager.CheckIfWellIsOk() == false) { manager.ShowAlert("Well is not empty yet"); }
        else if (Handle.contains("AddWell")) { manager.ShowAlert("well is loading...wait");manager.logger.warning("well is loading...wait"); }
    }
    public void VehicleTimeChecking() {
        if (!Handle.contains("TruckGo")) {
            manager.TruckGo(manager.mainAccount);
            Handle.add("TruckGo");
            Handle.add("11");
        } else {
            System.out.println("truck is on the way now..try again later");
            manager.logger.warning("truck is on the way now..try again later");
        }
    }
    public void TimeHandlingMethod() {
        for (int i = 1; i < Handle.size(); i += 2) { for (int j = 1; j < Handle.size(); j += 2) { Handle.set(j, String.valueOf(Integer.valueOf(Handle.get(j)))); }
            if (Integer.parseInt(Handle.get(i)) == 0) { if (Handle.get(i - 1).equalsIgnoreCase("AddPowder")) { manager.WorkFactory("Powder");Handle.remove(i - 1);Handle.remove(i); }
                else if (Handle.get(i - 1).equalsIgnoreCase("AddCloth")) { manager.WorkFactory("Cloth");Handle.remove(i);Handle.remove(i - 1); }
                else if (Handle.get(i - 1).equalsIgnoreCase("AddDress")) { manager.WorkFactory("Dress");Handle.remove(i);Handle.remove(i - 1); }
                else if (Handle.get(i - 1).equalsIgnoreCase("AddBread")) { manager.WorkFactory("Bread");Handle.remove(i);Handle.remove(i - 1); }
                else if (Handle.get(i - 1).equalsIgnoreCase("AddMilk")) { manager.WorkFactory("Milk");Handle.remove(i);Handle.remove(i - 1); }
                else if (Handle.get(i - 1).equalsIgnoreCase("AddIceCream")) { manager.WorkFactory("IceCream");Handle.remove(i);Handle.remove(i - 1); }
                else if (Handle.get(i - 1).equalsIgnoreCase("Well")) { manager.ChargeWell();Handle.remove(i);Handle.remove(i - 1); }
                else if (Handle.get(i - 1).equalsIgnoreCase("TruckGo")) { manager.TruckGo(manager.mainAccount);Handle.remove(i);Handle.remove(i - 1); } } } timer++;
    }
    public void Check() { manager.UnCage();manager.walk();manager.walkForTarget();manager.TigerWalk();manager.TigerCollision();manager.TigerWalk();manager.AnimalEatGrass();manager.CatCatchProduct();manager.CatIntersectMeet();manager.GrassDanger();manager.WildAnimalCollision();manager.ReduceProduct();manager.CheckForAnimalsProduct();manager.CheckForAddWildAnimalsFromFile(timer);manager.Hurt();manager.ReduceTimeOfProducing();TimeHandlingMethod(); }
    public void Turn(int n) { for (int i = 0; i < n; i++) { Check(); } }
    public boolean CheckLevel() {
        if (manager.mainAccount.getLevel() == 1 && manager.CheckLevel1()) { manager.mainAccount.setLevel(manager.mainAccount.getLevel() + 1);manager.ShowAlert("you have passed level 1..now you come back to menu to choose your level");timer=0;return true; }
        else if (manager.mainAccount.getLevel() == 2 && manager.CheckLevel2()) { manager.mainAccount.setLevel(manager.mainAccount.getLevel() + 1);manager.ShowAlert("you have passed level 2..now you come back to menu to choose your level");timer=0;return true; }
        else if (manager.mainAccount.getLevel() == 3 && manager.CheckLevel3()) { manager.mainAccount.setLevel(manager.mainAccount.getLevel() + 1);manager.ShowAlert("you have passed level 3..now you come back to menu to choose your level");timer=0;return true; }
        else if (manager.mainAccount.getLevel() == 4 && manager.CheckLevel4()) { manager.mainAccount.setLevel(manager.mainAccount.getLevel() + 1);manager.ShowAlert("you have passed level 4..now you come back to menu to choose your level");timer=0;return true; }
        else if (manager.mainAccount.getLevel() == 5 && manager.CheckLevel5()) { manager.ShowAlert("Bravo... you have finished the game");timer=0;return true; }return false;}
    public boolean CheckLevelForStart(int a) { if (manager.mainAccount.getLevel() >= a) { manager.mainAccount.setLevel(a);return true; }return false; }
    public boolean CheckTimeLevel() {
        if (manager.mainAccount.getLevel() == 1 && timer >= 250) {
            manager.ShowAlert("you lost because of time...come back to menu");
            manager.logger.warning("you lost because of time...come back to menu");
            return true;
        } else if (manager.mainAccount.getLevel() == 2 && timer >= 300) {
            manager.ShowAlert("you lost because of time...come back to menu");
            manager.logger.warning("you lost because of time...come back to menu");
            return true;
        } else if (manager.mainAccount.getLevel() == 3 && timer >= 400) {
            manager.ShowAlert("you lost because of time...come back to menu");
            manager.logger.warning("you lost because of time...come back to menu");
            return true;
        } else if (manager.mainAccount.getLevel() == 4 && timer >= 500) {
            manager.ShowAlert("you lost because of time...come back to menu");
            manager.logger.warning("you lost because of time...come back to menu");
            return true;
        } else if (manager.mainAccount.getLevel() == 5 && timer >= 600) {
            manager.ShowAlert("you lost because of time...come back to menu");
            manager.logger.warning("you lost because of time...come back to menu");
            return true;
        }
        return false;
    }
    public JButton CreateButton(String text,int x,int y,int w,int l,int f) {
        JButton button;
        button=new JButton(text);
        button.setBounds(x,y,w,l);
        button.setFont(new Font("Courier",Font.ITALIC,f));
        button.setBackground(Color.CYAN);
        return button;
    }
    public JFrame CreateFrame() {
        JFrame frame=new JFrame();
        frame.setSize(1300,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }
    public JLabel CreateLabel(String text,int x,int y,int w,int l,int f){
        JLabel label=new JLabel(text);
        label.setBounds(x,y,w,l);
        label.setFont(new Font("Courier",Font.ITALIC,f));
        label.setVisible(true);
        return label;
    }
    public void ShowWelcomePage() {
        JFrame frame=CreateFrame();
        JButton jButton=CreateButton("Next Page",1100,550,200,100,20);
        ImageIcon imageIcon=new ImageIcon("src\\images\\Welcome.png");
        JLabel label=CreateLabel("",0,0,1300,700,0);
        label.setIcon(imageIcon);
        label.add(jButton);
        jButton.addActionListener(e -> {frame.dispose();ShowLoginSignupPage();});
        frame.add(label);
        frame.setVisible(true);
    }
    public void ShowLoginSignupPage() {
        JFrame frame=CreateFrame();frame.setBackground(Color.GREEN);
        JButton button1=CreateButton("Login",1000,200,200,100,40);
        JButton button2=CreateButton("Signup",1000,400,200,100,40);
        Border border=BorderFactory.createLineBorder(Color.GREEN,5);
        ImageIcon imageIcon=new ImageIcon("src\\images\\LSpage.png");
        JLabel label=CreateLabel("Login Or Signup",0,0,1300,700,50);label.setIcon(imageIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);label.setVerticalTextPosition(JLabel.BOTTOM);label.setBackground(Color.CYAN);
        label.setOpaque(true);label.setBorder(border);label.add(button1);label.add(button2);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);frame.add(label);frame.setVisible(true);
        button1.addActionListener(e -> {frame.dispose();Login();});
        button2.addActionListener(e -> {frame.dispose();Signup();});
    }
    public void Login(){
        JFrame frame=CreateFrame();frame.getContentPane().setBackground(Color.CYAN);Border border=BorderFactory.createLineBorder(Color.MAGENTA,5);
        JButton button3=CreateButton("Previous",100,500,200,100,40);frame.add(button3);
        JLabel label=CreateLabel("Enter Username:",0,0,400,100,40);frame.add(label);label.setBackground(Color.BLUE);label.setOpaque(true);label.setBorder(border);
        textField1=new TextField();textField1.setBounds(400,0,400,100);textField1.setFont(new Font("My Boli",Font.ITALIC,40));frame.add(textField1);
        JButton button1=CreateButton("Submit",800,0,400,100,40);frame.add(button1);
        JLabel label2=CreateLabel("Enter Password:",0,100,400,100,40);label2.setBackground(Color.BLUE);label2.setOpaque(true);label2.setBorder(border);frame.add(label2);label2.setVisible(false);
        textField2=new TextField();textField2.setBounds(400,100,400,100);textField2.setFont(new Font("My Boli",Font.ITALIC,40));frame.add(textField2);textField2.setVisible(false);
        JButton button2=CreateButton("Submit",800,100,400,100,40);frame.add(button2);button2.setVisible(false);
        frame.setLayout(null);frame.setBackground(Color.GREEN);frame.setVisible(true);
        button1.addActionListener(e -> {if(textField1.getText().length()<=5){manager.ShowAlert("Invalid Username");}else{textField2.setVisible(true);button2.setVisible(true);label2.setVisible(true);}});
        button3.addActionListener(e -> {frame.dispose();ShowLoginSignupPage();});
        button2.addActionListener(e -> {if(textField2.getText().length()<=5){manager.ShowAlert("Invalid Username");}
        else {manager.ReadAccountsFiles();
                for(int i=0;i<manager.accountsArrayList.size();i++){ if(manager.accountsArrayList.get(i).getUsername().equals(textField1.getText())&& manager.accountsArrayList.get(i).getPassword().equals(textField2.getText())) { find=i; } }
                if (find!=-1) {frame.dispose();Menu(find);} else{manager.ShowAlert("Wrong Username Or PassWord");} } });
    }
    public void Signup(){
        JFrame frame=CreateFrame();frame.getContentPane().setBackground(Color.CYAN);Border border=BorderFactory.createLineBorder(Color.GREEN,5);
        JButton button3=CreateButton("Previous",100,500,200,100,40);frame.add(button3);
        JLabel label=CreateLabel("Enter Username:",0,0,400,100,40);frame.add(label);label.setBackground(Color.BLUE);label.setOpaque(true);label.setBorder(border);
        textField1=new TextField();textField1.setBounds(400,0,400,100);textField1.setFont(new Font("My Boli",Font.ITALIC,40));frame.add(textField1);
        JButton button1=CreateButton("Submit",800,0,400,100,40);frame.add(button1);
        JLabel label2=CreateLabel("Enter Password:",0,100,400,100,40);label2.setBackground(Color.BLUE);label2.setOpaque(true);label2.setBorder(border);frame.add(label2);label2.setVisible(false);
        textField2=new TextField();textField2.setBounds(400,100,400,100);textField2.setFont(new Font("My Boli",Font.ITALIC,40));frame.add(textField2);textField2.setVisible(false);
        JButton button2=CreateButton("Submit",800,100,400,100,40);frame.add(button2);button2.setVisible(false);
        frame.setVisible(true);frame.setLayout(null);
        button1.addActionListener(e -> {if(textField1.getText().length()<=5){manager.ShowAlert("Invalid Username");}else{textField2.setVisible(true);button2.setVisible(true);label2.setVisible(true);}});
        button3.addActionListener(e -> {frame.dispose();ShowLoginSignupPage();});
        button2.addActionListener(e -> {if(textField2.getText().length()<=5){manager.ShowAlert("Invalid PAssword");}
        else
        {manager.ReadAccountsFiles();
            for(int i=0;i<manager.accountsArrayList.size();i++){
                if(manager.accountsArrayList.get(i).getUsername().equals(textField1.getText())&& manager.accountsArrayList.get(i).getPassword().equals(textField2.getText())) {
                    find=i;
                }
            }
            if (find!=-1) {manager.ShowAlert("this username already exist");}
            else{Accounts accounts=new Accounts(textField1.getText(),textField2.getText(),1,50000);manager.accountsArrayList.add(accounts);manager.mainAccount=accounts;frame.dispose();Menu(find);}
        }
        });
    }
    public void Menu(int find){
        if(find!=-1){manager.mainAccount=manager.accountsArrayList.get(find);}Border border=BorderFactory.createLineBorder(Color.GREEN,5);
        JFrame frame=CreateFrame();
        JLabel label=CreateLabel("Main Menu",0,0,1300,700,40);
        ImageIcon imageIcon=new ImageIcon("src\\images\\b1.png");
        label.setIcon(imageIcon);label.setHorizontalTextPosition(JLabel.RIGHT);label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);label.setVerticalAlignment(JLabel.TOP);
        label.setBackground(Color.CYAN);label.setOpaque(true);
        JButton button1=CreateButton("LEVEL 1",800,120,200,100,30);label.add(button1);button1.setVisible(true);
        JButton button2=CreateButton("LEVEL 2",800,220,200,100,30);label.add(button2);button2.setVisible(true);
        JButton button3=CreateButton("LEVEL 3",800,320,200,100,30);label.add(button3);button3.setVisible(true);
        JButton button4=CreateButton("LEVEL 4",800,420,200,100,30);label.add(button4);button4.setVisible(true);
        JButton button5=CreateButton("LEVEL 5",800,520,200,100,30);label.add(button5);button5.setVisible(true);
        JButton button6=CreateButton("Previous",50,520,200,100,30);label.add(button6);button6.setVisible(true);
        button1.addActionListener(e -> {if(CheckLevelForStart(1)){frame.dispose();jFrame.setVisible(true);Start();}else{manager.ShowAlert("this level is locked");}});
        button2.addActionListener(e -> {if(CheckLevelForStart(2)){frame.dispose();jFrame.setVisible(true);Start();}else{manager.ShowAlert("this level is locked");}});
        button3.addActionListener(e -> {if(CheckLevelForStart(3)){frame.dispose();jFrame.setVisible(true);Start();}else{manager.ShowAlert("this level is locked");}});
        button4.addActionListener(e -> {if(CheckLevelForStart(4)){frame.dispose();jFrame.setVisible(true);Start();}else{manager.ShowAlert("this level is locked");}});
        button5.addActionListener(e -> {if(CheckLevelForStart(5)){frame.dispose();jFrame.setVisible(true);Start();}else{manager.ShowAlert("this level is locked");}});
        button6.addActionListener(e -> {frame.dispose();ShowLoginSignupPage();});
        frame.add(label);frame.setLayout(null);frame.setVisible(true);
    }
    public void Choice(){
        jFrame.dispose();a=false;JFrame frame=CreateFrame();frame.setBackground(Color.CYAN);
        JButton button=CreateButton("Resume",100,100,300,100,40);frame.add(button);
        JButton jButton=CreateButton("Main Menu",100,200,300,100,40);frame.add(jButton);
        JButton button1=CreateButton("Save&Exit",100,300,300,100,40);frame.add(button1);
        JButton button2=CreateButton("Exit",100,400,300,100,40);frame.add(button2);
        JLabel label=CreateLabel("Game Paused",450,0,600,400,40);label.setVerticalAlignment(JLabel.TOP);label.setHorizontalAlignment(JLabel.RIGHT);
        label.setBackground(Color.CYAN);label.setOpaque(true);label.setHorizontalTextPosition(JLabel.LEFT);label.setVerticalTextPosition(JLabel.TOP);
        ImageIcon imageIcon=new ImageIcon("src\\images\\b.png");label.setIcon(imageIcon);frame.add(label);frame.setVisible(true);
        frame.setLayout(null);frame.setVisible(true);
        button.addActionListener(e -> {jFrame.dispose();frame.dispose();jFrame=CreateFrame();jFrame.setVisible(true);a=true;Start();});
        button2.addActionListener(e -> { try { manager.Done(); } catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace(); }System.exit(1);});
        jButton.addActionListener(e -> { try { manager.Done(); } catch (FileNotFoundException fileNotFoundException) { fileNotFoundException.printStackTrace(); }frame.dispose();a=true;manager.producerAnimalsArrayList=new ArrayList<>();manager.wildAnimalsArrayList=new ArrayList<>();manager.WildAnimalsCome=new ArrayList<>();manager.grassArrayList=new ArrayList<>();manager.watering_system=new Watering_system();manager.reservoir=new Reservoir();manager.vehicle=new Vehicle();manager.dogArrayList=new ArrayList<>();manager.catArrayList=new ArrayList<>();manager.SetLogger();timer=0;Menu(find); });
    }
    public void Start() {t1=new ThreadHandling(manager);t1.start();}
    public void TruckMethod(Manager m){ jFrame.dispose();jFrame2=CreateFrame();jFrame2.setVisible(true);jFrame2.add(new Truck(m)); }
    public void Begin() {jFrame.setVisible(true);jFrame.add(new Surface(manager)); }
}