import javax.swing.JOptionPane;
import java.io.File;
import java.awt.Robot;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenRecoder
{
    public static void main(String[] args) throws Exception
    {
        while(true)
        {
            if(JOptionPane.showConfirmDialog(null, "Press YES to record...", "Screen Recorder", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
            { 
                ScreenRecord(Integer.parseInt(JOptionPane.showInputDialog(null, "Number of frames?", "Frames Count", JOptionPane.QUESTION_MESSAGE))); 
                makeVideo();
            }
            else 
            { 
                JOptionPane.showMessageDialog(null, "Thank you for using my app", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    public static void deleteDirectory(File file)
    {
        for(File subfile : file.listFiles()) 
        {
            if(subfile.isDirectory()) { deleteDirectory(subfile); }
            subfile.delete();
        }
    }

    public static void cleanDirectory(String folderName)
    {
        File file = new File(folderName);
        if(Files.exists(Paths.get(folderName)) && file.isDirectory()) { deleteDirectory(file); }
        else { file.mkdir(); }
    }

    public static void ScreenRecord(int num_frames)
    {
        try
        {
            Robot robo = new Robot();
            Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            
            cleanDirectory("img");
            cleanDirectory("ScreenRecording");
            
            for(int i = 0; i < num_frames; ++i) { ImageIO.write(robo.createScreenCapture(rect), "jpeg", new File("img\\capture" + i + ".jpeg")); }

            FileWriter file_num_frames = new FileWriter("img\\frame_data.txt", false);
            file_num_frames.write(Integer.toString(num_frames));
            file_num_frames.close();

            JOptionPane.showMessageDialog(null, "Screen Captured...\nLocation: ScreenRecording", "DONE MAN....", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e) { System.out.println(e); }
    }

    public static void makeVideo() throws Exception { Runtime.getRuntime().exec("cmd /c start cmd.exe /k \"python video_maker.py && exit\"").waitFor(); }
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
