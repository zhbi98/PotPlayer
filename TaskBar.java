
package potplayer;

import java.awt.Taskbar;
import java.awt.Taskbar.State;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class TaskBar {
    public static void main(String[] args) {

        // JavaDoc:
        // https://docs.oracle.com/javase/9/docs/api/java/awt/Taskbar.html

        // MSDNDoc:
        // https://msdn.microsoft.com/en-us/library/dd391692(VS.85).aspx
        if (Taskbar.isTaskbarSupported() == false) {
            return;
        }

        JFrame dialog = new JFrame("Test - 50%");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);    

        Taskbar taskbar = Taskbar.getTaskbar();
        taskbar.setWindowProgressState(dialog, State.ERROR);
        taskbar.setWindowProgressValue(dialog, 50);     
    }
}
