import javax.sound.sampled.*;import java.io.File;import java.io.IOException;
/*
by
parisa toumari
soraya charkas
s_Hossein Yaghoobi
group 16
 */
public class FrenzyFarm3 {
    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File file =new File("src\\r.wav");
        AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();clip.open(audioInputStream);clip.loop(300);clip.start();
        Manager manager=new Manager();InputProcessor inputProcessor=new InputProcessor(manager);inputProcessor.run();
    }
}