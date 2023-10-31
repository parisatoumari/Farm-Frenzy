public class ThreadHandling  extends Thread  {
    public InputProcessor inputProcessor;public ThreadHandling(Manager manager){this.inputProcessor=new InputProcessor(manager);InputProcessor.a=true;InputProcessor.jFrame.setVisible(true);}
    @Override public void run() {while (InputProcessor.a){inputProcessor.Begin();try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); } }}
}