package db.game.Sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    public static void playSoundLoop(String path){
        try {
            File music = new File(path);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            FloatControl control= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-25.0f);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    public static void playSound(String path){
        try{
            File music = new File(path);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
            Clip clip = AudioSystem.getClip();
            clip.open();
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
