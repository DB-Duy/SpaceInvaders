package db.game.db.game.display;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Assets {
    public static BufferedImage ship1,ship2,ship3,enemy1,enemy2,enemy3,enemy4,blast1,blast2,blast3,blast4,impact1,impact2,impact3,impact4,shot1,shot2,shot3;
    public static void init(){
    SpriteSheet sheet=new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));
    ship1=sheet.crop(0,0,44,52);
    ship2=sheet.crop(44,0,68,62);
    ship3=sheet.crop(112,0,53,63);
    enemy1=sheet.crop(165,0,59,56);
    enemy2=sheet.crop(224,0,52,60);
    enemy3=sheet.crop(276,0,56,59);
    enemy4=sheet.crop(333,0,56,56);
    impact1=sheet.crop(388,0,22,30);
    /*blast1=sheet.crop(410,0,89,94);
    blast2=sheet.crop(499,0,77,73);
    blast3=sheet.crop(576,0,76,70);
    blast4=sheet.crop(652,0,69,71);
    impact2=sheet.crop(721,0,24,26);
    impact3=sheet.crop(745,0,25,27);
    impact4=sheet.crop(770,0,25,27);*/

    }

}
