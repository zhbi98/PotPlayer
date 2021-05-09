
package potplayer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import potplayer.PotPlayer;
import potplayer.ButtonStyle;


public class FindEvent implements Initializable {
	@FXML
    private Button search = new Button();
    @FXML
    private TextField text = new TextField("text");

    private ButtonStyle buttonStyle = new ButtonStyle();

    @FXML
    void textEvent(ActionEvent event) {
        // key board enter key event
        System.out.println(text.getText());
    }

    @FXML
    void searchEvent(ActionEvent event) {
        buttonStyle.changeButtonColor(search);
        System.out.println(text.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Used to monitor character concent change
                // System.out.println(newValue);
            }
        });

        text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // Used to monitor key board key pressed
                // System.out.println("KeyPressed");
            }
        });
    }
}
