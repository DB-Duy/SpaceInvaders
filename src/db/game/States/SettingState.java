package db.game.States;

import db.game.Display.Assets;
import db.game.Main.Handler;
import db.game.Sounds.Sound;
import db.game.UI.ImageButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SettingState extends State {

    private ImageButton back, musicUp, musicDown, soundUp, soundDown;
    private Sound sound;
    private BufferedImage musicImage, soundImage;
    private int a = 0, b = 0;
    private ArrayList<ImageButton> buttons;
    private boolean lastState = false;

    public SettingState(Handler handler) {
        super(handler);
        buttons = new ArrayList<>();
        musicImage = Assets.volumeBars.get(a);
        soundImage = Assets.volumeBars.get(b);
        sound = new Sound();

        back = new ImageButton(handler, Assets.back, 8, 30, 245, 40);
        musicUp = new ImageButton(handler, Assets.arrowButtons, 750, 233, 60, 30);
        musicDown = new ImageButton(handler, Assets.arrowButtons2, 390, 233, 60, 30);
        soundUp = new ImageButton(handler, Assets.arrowButtons, 750, 365, 60, 30);
        soundDown = new ImageButton(handler, Assets.arrowButtons2, 390, 365, 60, 30);

        buttons.add(back);
        buttons.add(musicUp);
        buttons.add(musicDown);
        buttons.add(soundUp);
        buttons.add(soundDown);
    }

    @Override
    public void tick() {
        if (!isLastState() && handler.getMouseManager().isLeftPressed()) {
            setLastState(true);
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).isHovering()) {
                    switch (i) {
                        case 0:
                            State.setState(handler.getGame().menuState);
                            break;
                        case 1:
                            if (a != 0) {
                                a--;
                                musicImage = Assets.volumeBars.get(a);
                                sound.setVol(sound.getVolume() + 2.5f, sound.currentLoop);
                            }
                            break;
                        case 2:
                            if (a != 7) {
                                a++;
                                musicImage = Assets.volumeBars.get(a);
                                sound.setVol(sound.getVolume() - 2.5f, sound.currentLoop);
                            }
                            break;
                        case 3:
                            if (b != 0) {
                                b--;
                                soundImage = Assets.volumeBars.get(b);
                                sound.setVol(sound.getVolume() + 2.5f, sound.currentSoundEffect);
                            }
                            break;
                        case 4:
                            if (b != 7) {
                                b++;
                                soundImage = Assets.volumeBars.get(b);
                                sound.setVol(sound.getVolume() - 2.5f, sound.currentSoundEffect);
                            }
                            break;
                    }
                }
            }
        }
        if (!handler.getMouseManager().isLeftPressed()) {
            setLastState(false);
        }
    }



    @Override
    public void render(Graphics g) {
        g.setColor(new Color(241, 217, 27));
        g.setFont(new Font("Minecraft", Font.PLAIN, 40));

        g.drawImage(Assets.settingScreen, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
        g.drawString("Music", 250, 268);
        g.drawImage(musicImage, 410, 200, 400, 100, null);
        g.drawString("Sound effect", 130, 400);
        g.drawImage(soundImage, 410, 330, 400, 100, null);


        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).render(g);
        }
    }
}
