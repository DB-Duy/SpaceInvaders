package db.game.Sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public static Clip currentLoop;
    public static Clip currentSoundEffect;

    public static void playSoundLoop(String path) {
        try {
            File music = new File(path);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);

            Clip clip = AudioSystem.getClip();
            currentLoop = clip;
            clip.open(audioInput);

            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-2.0f);

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

    public float getVolume() {
        FloatControl control = (FloatControl) currentLoop.getControl(FloatControl.Type.MASTER_GAIN);
        return control.getValue();
    }

    public static void playSound(String path) {
        try {
            File music = new File(path);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
            Clip clip = AudioSystem.getClip();
            currentSoundEffect = clip;
            clip.open(audioInput);

            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-7.0f);

            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopLoop() {
        if (currentLoop != null) {
            currentLoop.stop();
        }
    }

    public static void setVol(float vol, Clip clip) {
        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(vol);
    }
}
