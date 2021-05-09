
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
import potplayer.FileStreams;
import potplayer.FileDialog;
import potplayer.PlayerData;


public class PlayListEvent implements Initializable {
    @FXML
    private ListView<Object> listView = new ListView<Object>();
    private ObservableList<Object> listItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(listItems);
        listView.setEditable(true);

        /**
         * image can add to ListView
         * ImageView imageView = new ImageView();
         * Image image = new Image("example.jpg");
         * imageView.setImage(image);
         */
        PlayerData pd = new PlayerData();
        String playedPath = "./src/potplayer/data/played.txt";
        int l = pd.getFileTotalNumOfRow(playedPath);
        
        for (int i = l; i >= (l - 50); i--) {
            String fn = FileStreams.readAnyLine(playedPath, i);
            FileDialog fd = new FileDialog();
            fn = fd.readFileNames(fn);
            listItems.add(fn);
        }

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                // Object is data type
                // Can also be Int String Char
                System.out.println(newValue);
            }
        });

        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                // Object is data type
                // Can also be Int String Char
                System.out.println(newValue);
            }
        });
    }
}
