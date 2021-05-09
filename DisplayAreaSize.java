
package potplayer;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;


public class DisplayAreaSize {
    public static int missionBoarHeight() {
        JFrame jFrame = new JFrame();
        java.awt.Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(jFrame.getGraphicsConfiguration());

        return screenInsets.bottom;
    }

    public static Map<String, Double> screenSize() {
        Map<String, Double> map = new HashMap<String, Double>();

        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double screenWidth = screenRectangle.getWidth();
        double screenHeight = screenRectangle.getHeight();

        Double width = new Double(screenWidth);
        Double height = new Double(screenHeight);

        map.put("width", width);
        map.put("height", height);

        /**
         * get screen size
         * Map<String, Double> map = DisplayAreaSize.screenSize()
         * w = map.get("width");
         * h = map.get("height");
         * w = 1920
         * h = 1080
         */
        return map;
    }
}
