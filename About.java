
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


public class About {
    // Alert aboutBox;
    Stage aboutStage;

    public About() {
        // aboutBox = new Alert(AlertType.INFORMATION);
        // aboutBox.setTitle("about");
        // aboutBox.setHeaderText("PotPlayer");
        // aboutBox.setContentText("Im Yes");
        
        this.aboutStage = new Stage();
        this.aboutStage.setTitle("About");
        this.aboutStage.getIcons().add(new Image("PotPlayer/logo/potplayer.jpg"));
        this.aboutStage.setWidth(325);
        this.aboutStage.setHeight(170);
        this.aboutStage.setResizable(false);
        this.aboutStage.initStyle(StageStyle.UTILITY);
        this.aboutStage.initModality(Modality.APPLICATION_MODAL);

        try {
            BorderPane aboutBorderPane = FXMLLoader.load(getClass().getResource("about.fxml"));
            Scene aboutScene = new Scene(aboutBorderPane, 1024, 635);
            aboutScene.setFill(Color.BLACK);
            
            this.aboutStage.setScene(aboutScene);
        } catch (Exception e) {
            System.out.println("PARSE ABOUT FXML ERROR");
            System.exit(0);
        }
    }

    public void aboutShow() {
        // aboutBox.showAndWait();
        this.aboutStage.show();
    }
}
