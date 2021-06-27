import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Robot;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.FileWriter;

public class Screen_recoder
{
    public static void main(String[] args) throws Exception
    {
        int option = JOptionPane.YES_OPTION;
        
        do
        {
            option = JOptionPane.showConfirmDialog(null, "Press YES to record...", "Screen Recorder", JOptionPane.YES_NO_OPTION);

            if(option == JOptionPane.YES_OPTION) { ScreenRecord(Integer.parseInt(JOptionPane.showInputDialog(null, "Number of frames?", "Frames Count", JOptionPane.QUESTION_MESSAGE))); makeVideo(); Thread.sleep(5000);}
            else {break;}
        } while(option != JOptionPane.NO_OPTION);

        JOptionPane.showMessageDialog(null, "Thank you for using my app", "Thank You", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void ScreenRecord(int num_frames)
    {
        try
        {
            Robot robo = new Robot();
            Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            
            BufferedImage arr[] = new BufferedImage[num_frames];
            
            for(int i = 0; i < num_frames; ++i) { arr[i] = robo.createScreenCapture(rect);}

            Runtime.getRuntime().exec("cmd /c start cmd.exe /k  \"rd/s/q img && rd/s/q ScreenRecording && mkdir ScreenRecording && exit\"  ");
            Thread.sleep(1500);

            Runtime.getRuntime().exec("cmd /c start cmd.exe /k  \"mkdir img && exit\"  ");
            Thread.sleep(500);

            for(int j = 0; j < num_frames; ++j) { ImageIO.write(arr[j], "jpeg", new File("img\\capture" + j + ".jpeg")); }
            
            FileWriter file_num_frames = new FileWriter("frame_data.txt", false);
            file_num_frames.write(Integer.toString(num_frames));
            file_num_frames.close();

            JOptionPane.showMessageDialog(null, "Screen Captured...\nLocation: ScreenRecording", "DONE MAN....", JOptionPane.INFORMATION_MESSAGE);
        }

        catch(Exception e) {System.out.println(e);}
    }

    public static void makeVideo() throws Exception { Runtime.getRuntime().exec("cmd /c start cmd.exe /k \"video_maker.py && exit\""); }
}

/*

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
public class FullScreenCaptureExample {
 
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
 
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
            File file = new File("screen-capture.png");
            boolean status = ImageIO.write(bufferedImage, "png", file);
            System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());
 
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }
}

*/
