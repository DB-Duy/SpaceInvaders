package db.game.db.game.display;
import javax.swing.JFrame;
import java.awt.*;

public class  Display {
     private JFrame frame;
     private String title;
     private int width,height;
     private Canvas canvas;

     public Display(String title, int width, int height){
         this.title=title;
         this.width=width;
         this.height=height;

         createDisplay();
     }
     private void createDisplay(){
         frame = new JFrame(title);
         frame.setSize(width,height);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLocationRelativeTo(null);
         frame.setVisible(true);

         canvas = new Canvas();
         canvas.setPreferredSize(new Dimension(width,height));

         frame.add(canvas);
         frame.pack();
         frame.setResizable(false);
     }
     public Canvas getCanvas(){
         return canvas;
     }
}
