
package potplayer;

import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import potplayer.PotPlayer;


public class Find {
    // Alert aboutBox;
    Stage findStage;

    public Find() {
        // aboutBox = new Alert(AlertType.INFORMATION);
        // aboutBox.setTitle("about");
        // aboutBox.setHeaderText("PotPlayer");
        // aboutBox.setContentText("Im Yes");
        
        this.findStage = new Stage();
        this.findStage.setTitle("Find movie");
        this.findStage.getIcons().add(new Image("PotPlayer/logo/potplayer.jpg"));
        this.findStage.setWidth(300);
        this.findStage.setHeight(225);
        this.findStage.setResizable(false);
        this.findStage.initStyle(StageStyle.UTILITY);
        this.findStage.initModality(Modality.APPLICATION_MODAL);

        try {
            BorderPane findBorderPane = FXMLLoader.load(getClass().getResource("find.fxml"));
            Scene findScene = new Scene(findBorderPane, 300, 225);
            findScene.setFill(Color.BLACK);
            
            this.findStage.setScene(findScene);
        } catch (Exception e) {
            System.out.println("PARSE FIND FXML ERROR");
            System.exit(0);
        }
    }

    public void findShow() {
        this.findStage.show();
    }
}
