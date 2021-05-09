
package potplayer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import potplayer.PotPlayer;


public class PlayList {
    private Stage initStage;
    static Stage playListStage;
    static boolean listShow = false;

    public PlayList() {  
        this.initStage = new Stage();
        this.initStage.setWidth(0);
        this.initStage.setHeight(0);
        this.initStage.setResizable(false);
        this.initStage.initStyle(StageStyle.UTILITY);
        this.initStage.setOpacity(0);

        playListStage = new Stage();
        playListStage.initOwner(this.initStage);
        playListStage.setTitle("play list");
        playListStage.getIcons().add(new Image("PotPlayer/logo/potplayer.jpg"));
        playListStage.initStyle(StageStyle.UNDECORATED);

        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("playlist.fxml"));
            Background bg;
            bg = new Background(new BackgroundFill(Paint.valueOf("#FF0000"), new CornerRadii(30), new Insets(10)));
            vBox.setBackground(bg);
            Scene playListScene = new Scene(vBox, 314, 636);
            // playListScene.setFill(Color.BLACK);
            playListScene.setFill(Paint.valueOf("#FF000000"));

            this.playListStage.setScene(playListScene);
        } catch (Exception e) {
            System.out.println("PARSE Play List FXML ERROR");
            System.exit(0);
        }
    }

    public void playListShow() {
        double x = PotPlayer.mainStage.getX();
        double y = PotPlayer.mainStage.getY();
        double w = PotPlayer.mainStage.getWidth();
        double h = PotPlayer.mainStage.getHeight();

        playListStage.setWidth(314);
        playListStage.setHeight(h);
        playListStage.setX(x + w + 10);
        playListStage.setY(y);
        
        initStage.show();
        listShow = true;
        playListStage.show();
    }

    public void playListClose() {
        initStage.close();
        listShow = false;
        playListStage.close();
    }

    public void playListOnAction() {
        if (listShow == false) {
            this.playListShow();
        } else {
            this.playListClose();
        }
    }

    public static void renewPlayListSize(double x, double y, double mainStageWidth, double mainStageHeight) {
        if (listShow == true) {
            playListStage.setHeight(mainStageHeight);
            playListStage.setX(x + mainStageWidth + 10);
            playListStage.setY(y);
        } else {
            return;
        }
    }

    public static void PlayListOntop(boolean onTop) {
        playListStage.setAlwaysOnTop(onTop);
    }
}
