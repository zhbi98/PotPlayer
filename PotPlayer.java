
package potplayer;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import potplayer.About;
import potplayer.DateAndTime;
import potplayer.FileDialog;
import potplayer.Find;
import potplayer.MediaPlayers;
import potplayer.PlayList;
import potplayer.PotPlayerConsts;
import potplayer.StageActivity;


public class PotPlayer extends Application {
    // mainStage Used to share other files
    // that require primaryStage
    static Stage mainStage;
    static Scene mainScene;
    static VBox  mainVbox;

    About aboutInfo;
    Find findBar;
    PlayList playList;
    FileDialog fileDialog;
    StageActivity stageActivity;
    FitStageSize fitSize;

    public PotPlayer() {
        aboutInfo = new About();
        findBar = new Find();
        playList = new PlayList();
        fileDialog = new FileDialog();

        stageActivity = new StageActivity();
        fitSize = new FitStageSize();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PotPlayer");
        primaryStage.getIcons().add(new Image("PotPlayer/logo/potplayer.jpg"));

        // primaryStage.setWidth(980);
        // primaryStage.setHeight(645);
        primaryStage.setWidth(PotPlayerConsts.PLAYER_DEFAULT_W);
        primaryStage.setHeight(PotPlayerConsts.PLAYER_DEFAULT_H);
        // cannot resize the stage size
        primaryStage.setResizable(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // primaryStage.initStyle(StageStyle.TRANSPARENT);
        // primaryStage.initStyle(StageStyle.UTILITY);

        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("./potplayer.fxml"));
            Scene scene = new Scene(vBox, PotPlayerConsts.PLAYER_DEFAULT_W, PotPlayerConsts.PLAYER_DEFAULT_H);
            scene.setFill(Color.BLACK);

            primaryStage.setScene(scene);
            primaryStage.show();

            // click the scence to move stage
            stageActivity.stageMove(primaryStage, scene);
            stageActivity.stageCloseAction(primaryStage);
            stageActivity.stageSizeChange(vBox);
            stageActivity.stageResize(primaryStage, vBox);

            // mainStage Used to share other files
            // that require primaryStage, vBox.
            mainStage = primaryStage;
            mainVbox = vBox;

            vBox.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    System.out.println("vBox.setOnDragDropped");

                    Dragboard data = event.getDragboard();
                    List<File> files = data.getFiles();
                    System.out.println(files.get(0).toURI().toString());

                    PotPlayerEvent e = new PotPlayerEvent();
                    e.newMediaObject(files.get(0).toURI().toString());
                    

                    event.setDropCompleted(true);
                }
            });

            vBox.setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getDragboard().hasFiles()) {
                        /**
                         * TransferMode.COPY_OR_MOVE
                         * TransferMode.COPY
                         * TransferMode.LINK
                         * TransferMode.ANY
                         */
                        Dragboard data = event.getDragboard();
                        event.acceptTransferModes(TransferMode.LINK);
                        event.consume();

                        // System.out.println(data.getString());
                        // System.out.println("setOnDragOver");

                        // List<File> files = data.getFiles();
                        // System.out.println(files);
                    }
                }
            });

            // vBox.setOnDragDetected(new EventHandler<MouseEvent>() {
            //     public void handle(MouseEvent event) {
            //         System.out.println("setOnDragDetected");       
            //          event.consume();
            //     }
            // });

            // vBox.setOnDragEntered(new EventHandler<DragEvent>() {
            //     public void handle(DragEvent event) {
            //         System.out.println("setOnDragEntered");       
            //          event.consume();
            //     }
            // });
            // vBox.setOnDragExited(new EventHandler<DragEvent>() {
            //     public void handle(DragEvent event) {
            //         System.out.println("setOnDragExited");       
            //          event.consume();
            //     }
            // });
            // vBox.setOnDragDone(new EventHandler<DragEvent>() {
            //     public void handle(DragEvent event) {
            //         System.out.println("setOnDragDone");       
            //          event.consume();
            //     }
            // });

            System.out.println("Thread name:" + Thread.currentThread().getName());
            System.out.println(DateAndTime.readDateAndTime());
            System.out.println(Platform.isFxApplicationThread());

            Task taskTime = new Task() {
                @Override
                protected Object call() throws Exception {
                    System.out.println("task time called");

                    for (int i = 0; i < 3600; i++) {
                        System.out.println("Thread name:" + Thread.currentThread().getName());
                        System.out.println(DateAndTime.readDateAndTime());
                        System.out.println("Web time:" + DateAndTime.readWebDateAndTime(null, "all"));
                        System.out.println(Platform.isFxApplicationThread());

                        Thread.sleep(2000);
                    }

                    return null;
                }
            };

            Task taskRandom = new Task() {
                @Override
                protected Object call() throws Exception {
                    System.out.println("task Random called");

                    double angle = 0;
                    for (int i = 0; i < 3600; i++) {
                        angle = Math.sin(i / 10) + Math.random();
                        System.out.println("Thread name:" + Thread.currentThread().getName());
                        System.out.println(angle);
                        System.out.println(Platform.isFxApplicationThread());

                        Thread.sleep(2000);
                    }

                    return null;
                }
            };

            Thread threadTime = new Thread(taskTime);
            Thread threadRandom = new Thread(taskRandom);
            threadTime.setDaemon(true);
            threadTime.start();
            threadRandom.setDaemon(true);
            threadRandom.start();
            System.out.println("PotPlayer running");
        } catch (Exception e) {
            System.out.println("PARSE FXML ERROR");
            System.exit(0);
        }
    }
}
