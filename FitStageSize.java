
package potplayer;

import java.util.Map;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import potplayer.DisplayAreaSize;
import potplayer.PotPlayerConsts;


public class FitStageSize {
    boolean resize = false;

	public void stageSize(Stage primaryStage, Media media) {
        if (this.resize == true) {
            return;
        } else {
            this.resize = true;
        }

        double mediaWidth = media.getWidth(); 
        double mediaHeight = media.getHeight();

        if ((mediaWidth == 0) && (mediaHeight == 0)) {
            // this file is music
            primaryStage.setWidth(PotPlayerConsts.MUSIC_MODE_WIDTH);
            primaryStage.setHeight(PotPlayerConsts.MUSIC_MODE_HIGHT);
        } else {
            // this file is video

            // 97 Total height of other components
            // 40 mission board height
            final int componentsH = PotPlayerConsts.TOTAL_MODULE_HIGH;
            int missionBoardH = DisplayAreaSize.missionBoarHeight();

            Map<String, Double> screen = DisplayAreaSize.screenSize();
            double screenWidth = screen.get("width");
            double screenHeight = screen.get("height");

            double stageX = primaryStage.getX();
            double stageY = primaryStage.getY();

            double newStageWidth = mediaWidth;
            double newStageHeight = mediaHeight + componentsH;

            double overflowX = screenWidth - (stageX + newStageWidth);
            double overflowY = (screenHeight - missionBoardH) - (stageY + newStageHeight);

            if ((newStageWidth >= screenWidth) || (newStageHeight >= screenHeight)) {
                primaryStage.setX(0);
                primaryStage.setY(0);
                primaryStage.setWidth(screenWidth);
                primaryStage.setHeight(screenHeight - missionBoardH);

                return;
            }

            if (overflowX < 0) {
                primaryStage.setX(stageX + overflowX);
                primaryStage.setY(stageY);
            } 
            if (overflowY < 0) {
                primaryStage.setX(stageX);
                primaryStage.setY(stageY + overflowY);
            }
            if ((overflowX < 0) && (overflowY < 0)) {
                primaryStage.setX(stageX + overflowX);
                primaryStage.setY(stageY + overflowY);
            }

            primaryStage.setWidth(newStageWidth);
            primaryStage.setHeight(newStageHeight);

            PlayList.renewPlayListSize(primaryStage.getX(), primaryStage.getY(), 
                                    primaryStage.getWidth(), primaryStage.getHeight());
        }
	}
}
