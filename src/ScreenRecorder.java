package src;
import javax.swing.JOptionPane;
import java.io.File;
import java.awt.Robot;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenRecorder
{
    public ScreenRecorder() throws Exception
    {
        while(true)
        {
            if(JOptionPane.showConfirmDialog(null, "Press YES to record...", "Screen Recorder", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
            { 
                try { ScreenRecord(Integer.parseInt(JOptionPane.showInputDialog(null, "Number of frames?", "Frames Count", JOptionPane.QUESTION_MESSAGE))); }
                catch(Exception ex) { break; }
                makeVideo();
            }
            else { break; }
        }

        JOptionPane.showMessageDialog(null, "Thank you for using my app", "Thank You", JOptionPane.INFORMATION_MESSAGE);
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
            
            cleanDirectory("Screen-Shots");
            cleanDirectory("Screen-Recording");
            
            for(int i = 0; i < num_frames; ++i) { ImageIO.write(robo.createScreenCapture(rect), "jpeg", new File("Screen-Shots\\capture" + i + ".jpeg")); }

            FileWriter file_num_frames = new FileWriter("Screen-Shots\\frame_data.txt", false);
            file_num_frames.write(Integer.toString(num_frames));
            file_num_frames.close();

            JOptionPane.showMessageDialog(null, "Screen Captured...\nLocation: ScreenRecording", "DONE MAN....", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e) { System.out.println(e); }
    }

    public static void makeVideo() throws Exception 
    { 
        Runtime.getRuntime().exec("cmd /c start cmd.exe /k \"python src/video_maker.py && exit\"").waitFor(); 
    }
}
