
package potplayer;

import java.util.Map;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import potplayer.DisplayAreaSize;


public class StageMaxMin {
    public static double lastX = 0.0;
    public static double lastY = 0.0;
    public static double lastW = 0.0;
    public static double lastH = 0.0;

    public static boolean stageMax   = false;
    public static boolean stageOnTop = false;
    public static boolean stageFull  = false;

    public static boolean stageOntop(Stage topStage) {
        if (stageOnTop == false) {
            stageOnTop = true;
        } else {
            stageOnTop = false;
        }
        topStage.setAlwaysOnTop(stageOnTop);
        return stageOnTop;
    }

    public static void disableStageOntop(Stage topStage) {
        topStage.setAlwaysOnTop(false);
        stageOnTop = false;
    }

    public static void stageMax(Stage stage) {
        int missionBoardH = DisplayAreaSize.missionBoarHeight();

        Map<String, Double> screen = DisplayAreaSize.screenSize();
        double screenWidth = screen.get("width");
        double screenHeight = screen.get("height");
        
        double xy = stage.getX() + stage.getY();
        double size = stage.getWidth() + stage.getHeight();

        if ((xy != 0) || (size != screenWidth + screenHeight - missionBoardH)) {
            // Size or coordinates is changed, so disable max flag
            stageMax = false;
        }

        if (stageMax == false) {
            lastX = stage.getX();
            lastY = stage.getY();
            lastW = stage.getWidth();
            lastH = stage.getHeight();

            missionBoardH = DisplayAreaSize.missionBoarHeight();

            screen = DisplayAreaSize.screenSize();
            screenWidth = screen.get("width");
            screenHeight = screen.get("height");

            stage.setX(0);
            stage.setY(0);
            stage.setWidth(screenWidth);
            stage.setHeight(screenHeight - missionBoardH);

            stageMax = true;
        } else {
            stage.setX(lastX);
            stage.setY(lastY);
            stage.setWidth(lastW);
            stage.setHeight(lastH);

            stageMax = false;
        }
    }

    public static void minimize(Stage stage) {
        stage.setIconified(true);
    }

    public static void saveDraggedMaxBeforeSize(double saveX, double saveY, double saveW, double saveH) {
        // The size can only be saved when it is not maximized
        if (stageMax != true) {
            lastX = saveX;
            lastY = saveY;
            lastW = saveW;
            lastH = saveH;
        } else {
            return;
        }
    }

    public static void draggedMax(Stage stage, MouseEvent event) {
        if (event.getScreenY() <= 0) {
            int missionBoardH = DisplayAreaSize.missionBoarHeight();

            Map<String, Double> screen = DisplayAreaSize.screenSize();
            double screenWidth = screen.get("width");
            double screenHeight = screen.get("height");

            stage.setX(0);
            stage.setY(0);
            stage.setWidth(screenWidth);
            stage.setHeight(screenHeight - missionBoardH);

            stageMax = true;
        }
    }

    public static boolean isDownDragged(double nowY) {
        if ((nowY - 0) >= 3) {
            return true;
        } else {
            return false;
        }
    }

    public static void draggedMin(Stage stage, MouseEvent event) {
        if (isDownDragged(event.getScreenY()) == true) {
            if (stageMax == true) {
                stage.setX(event.getScreenX());
                stage.setY(event.getScreenY());
                stage.setWidth(lastW);
                stage.setHeight(lastH);

                stageMax = false;
            } else {
                return;
            }
        }
    }
}
