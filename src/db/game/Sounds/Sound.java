package db.game.Sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public static Clip currentLoop;
    public static Clip currentSoundEffect;
    private static FloatControl loopGain = null;
    private static FloatControl effectGain = null;
    private static float loopVolume;
    private static float effectVolume;
    public static void playSoundLoop(String path) {
        try {
            File music = new File(path);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);

            Clip clip = AudioSystem.getClip();
            currentLoop = clip;
            clip.open(audioInput);

            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-2.0f);
            if(loopGain != null){
                setLoopVol(loopVolume);
            }
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
            if(effectGain != null){
                setEffectVol(effectVolume);
            }
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

    public static void setLoopVol(float vol) {
        loopGain = (FloatControl) currentLoop.getControl(FloatControl.Type.MASTER_GAIN);
        loopVolume = vol;
        loopGain.setValue(vol);
    }
    public static void setEffectVol(float vol){
        effectGain = (FloatControl) currentSoundEffect.getControl(FloatControl.Type.MASTER_GAIN);
        effectVolume = vol;
        effectGain.setValue(vol);
    }
}
