
package potplayer;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.concurrent.Task;

import potplayer.ButtonStyle;
import potplayer.DateAndTime;
import potplayer.FileStreams;
import potplayer.MediaPlayers;
import potplayer.PlayerData;
import potplayer.PotPlayer;
import potplayer.TimeFormat;
import potplayer.ValueConvert;
import potplayer.StageMaxMin;
import potplayer.CheckFileType;


public class PotPlayerEvent implements Initializable {
    @FXML
    private Button top = new Button();
    @FXML
    private Button min = new Button();
    @FXML
    private Button max = new Button();
    @FXML
    private Button full = new Button();
    @FXML
    private Button close = new Button();
    @FXML // the following is player button
    private Button play = new Button();
    @FXML
    private Button stop = new Button();
    @FXML
    private Button prev = new Button();
    @FXML
    private Button next = new Button();
    @FXML
    private Button open = new Button();
    @FXML
    private Button find = new Button();
    @FXML
    private Button about = new Button();
    @FXML
    private Button more = new Button();
    @FXML
    private Button mute = new Button();
    @FXML
    private Button reserved = new Button();
    @FXML
    private Label nameLabel = new Label();
    @FXML
    private Label timeLabel = new Label();
    @FXML // the following is player sliders
    private Slider sliderTime = new Slider();
    @FXML
    private ProgressBar progressTime = new ProgressBar();
    @FXML
    private Slider sliderVolume = new Slider();
    @FXML
    private ProgressBar progressVolume = new ProgressBar();
    @FXML
    private BorderPane videoPane = new BorderPane();
    @FXML
    private MediaView mediaView = new MediaView();
    @FXML
    private HBox titleHBox = new HBox();
    @FXML
    private HBox sliderHBox = new HBox();
    @FXML
    private HBox playHBox = new HBox();
    // FXML test
    // private FindEvent FindEvents;

    // playerMain is main class object used to 
    // quote variable from main file
    private PotPlayer playerMain = new PotPlayer();
    private MediaPlayers potPlayer;
    private boolean noFile = true;

    public void printInfo(String info) {
        System.out.println("On Potplayer Event:" + info);
        // playerMain.fileDialog.fileDialogShow(playerMain.mainStage);
        // http://vfx.mtime.cn/Video/2018/05/22/mp4/180522165517679320.mp4
        // http://vfx.mtime.cn/Video/2020/11/18/mp4/201118203739607528.mp4

        // newMediaObject(info);
    }

    public void mediaPlaying(MediaPlayers player) {
        // read video play change event
        player.mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, 
                Duration oldValue, Duration newValue) {

                moduleRenew();
            }
        });

        player.mediaPlayer.setAudioSpectrumListener(new AudioSpectrumListener() {
            @Override
            public void spectrumDataUpdate(double timestamp, double duration, 
                float[] magnitudes, float[] phases) {

                ValueConvert valueConvert = new ValueConvert();
                // System.out.println((int)valueConvert.negativeToPositive(-60, 100, magnitudes[0]));
            }
        });
    }

    private void playAndPause(MediaPlayers player) {
        if (potPlayer.checkMedia() == false)
            return;

        MediaPlayer.Status mediaStatus = player.getPlayerStatus();
        if ((mediaStatus == MediaPlayer.Status.HALTED) || 
            (mediaStatus == MediaPlayer.Status.UNKNOWN))
            return;

        if ((mediaStatus == MediaPlayer.Status.PAUSED) || (mediaStatus == MediaPlayer.Status.READY) || 
            (mediaStatus == MediaPlayer.Status.STOPPED) || potPlayer.isMediaEnd()) {

            if (potPlayer.isMediaEnd()) {
                potPlayer.seekStart();
                /**
                 * seek file later set the mediaEnd 
                 * and mediaReplay flags off
                 */
                potPlayer.setMediaEnd(false);
            }
            potPlayer.play();
            ButtonStyle buttonStyle = new ButtonStyle();
            buttonStyle.changeButtonImage(play, "./logo/pauseing.jpg", 18, 18);

            mediaPlaying(potPlayer);
        } else {
            potPlayer.pause();
            // the video now playing
            ButtonStyle buttonStyle = new ButtonStyle();
            buttonStyle.changeButtonImage(play, "./logo/playing.jpg", 18, 18);
        }
    }

    private void playerStop(MediaPlayers player) {
        if ((player.checkMedia() == false) || 
            (player.getPlayerStatus() == MediaPlayer.Status.STOPPED)) {
            return;
            // media unopen or media already stoped
        } else {
            if (potPlayer.isMediaEnd()) {
                return;
                /**
                 * if media play finished, player will automatically 
                 * stop, so canot once again carried out stop
                 */
            } else {
                potPlayer.stop();
                ButtonStyle buttonStyle = new ButtonStyle();
                buttonStyle.changeButtonImage(play, "./logo/playing.jpg", 18, 18);
            }
        }
    }

    @FXML
    void topEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(top);
        boolean toping = StageMaxMin.stageOntop(playerMain.mainStage);

        if (toping == true) {
            playerMain.playList.PlayListOntop(true);
        } else {
            playerMain.playList.PlayListOntop(false);
        }
    }

    @FXML
    void minEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(min);
        StageMaxMin.minimize(playerMain.mainStage);

        playerMain.playList.playListClose();
    }

    @FXML
    void maxEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(max);
        /**
         * if (playerMain.mainStage.isMaximized() == false) {
         *     playerMain.mainStage.setMaximized(true);
         * } else {
         *     playerMain.mainStage.setMaximized(false);
         * }
         */

        StageMaxMin.stageMax(playerMain.mainStage);
    }

    @FXML
    void fullEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(full);
        playerMain.mainStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        if (StageMaxMin.stageFull == false) {
            playerMain.mainStage.setFullScreen(true);

            titleHBox.setVisible(false);
            titleHBox.setVisible(false);
            sliderHBox.setVisible(false);
            playHBox.setVisible(false);
            StageMaxMin.stageFull = true;       
        } else {
            playerMain.mainStage.setFullScreen(false);

            titleHBox.setVisible(true);
            sliderHBox.setVisible(true);
            playHBox.setVisible(true);
            StageMaxMin.stageFull = false; 
        }
    }

    @FXML
    void closeEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(close);
        potPlayer.releaseMemory();
        playerMain.mainStage.close();
        System.exit(0);
    }

    @FXML
    void playEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(play);

        if (noFile == true) {
            openFiles();
        } else {
            playAndPause(potPlayer);
        }
    }

    @FXML
    void stopEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(stop);

        playerStop(potPlayer);
    }

    @FXML
    void prevEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(prev);
    }

    @FXML
    void nextEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(next);
    }

    public void newMediaObject(String file) {
        if (file != null) {
            if (CheckFileType.checkType(file) == false) {
                System.out.println("File format not support");
                return;
            }

            potPlayer.stop();
            potPlayer = new MediaPlayers(file);

            potPlayer.setVolume(potPlayer.getLastVolume());
            sliderVolume.setValue((int)Math.round(potPlayer.getVolume() * 100));

            playerMain.fitSize.resize = false;
            potPlayer.play();
            ButtonStyle buttonStyle = new ButtonStyle();
            buttonStyle.changeButtonImage(play, "./logo/pauseing.jpg", 18, 18);

            mediaPlaying(potPlayer);
        } else {
            System.out.println("No files are opened");
        }        
    }

    public void openFiles() {
        playerMain.fileDialog.fileDialogShow(playerMain.mainStage);
        String file = playerMain.fileDialog.filePathURL();

        if (file != null) {
            String opendFile = playerMain.fileDialog.filePathString();
            PlayerData playerData = new PlayerData();
            String playedPath = "./src/potplayer/data/played.txt";
            playerData.savePlayedFileName(playedPath, opendFile);


            int line = playerData.getFileTotalNumOfRow(playedPath);
            System.out.println(DateAndTime.readDateAndTime() + "-played.txt have-" + line);
            String lastTimePlay = FileStreams.readAnyLine(playedPath, (line - 1));
            System.out.println("Last time played:" + lastTimePlay);
        }
        if (file != null) {
            noFile = false;
        }

        newMediaObject(file);
    }

    @FXML
    void openEvent(ActionEvent event) throws MalformedURLException {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(open);

        openFiles();
    }

    @FXML
    void findEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(find);

        // pop up the find bar will cancle the on top
        StageMaxMin.disableStageOntop(playerMain.mainStage);
        playerMain.findBar.findShow();
    }

    @FXML
    void reservedEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(reserved);
    }

    @FXML
    void aboutEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(about);

        // pop up the about will cancle the on top
        StageMaxMin.disableStageOntop(playerMain.mainStage);
        playerMain.aboutInfo.aboutShow();
    }

    @FXML
    void moreEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(more);

        playerMain.playList.playListOnAction();
    }

    @FXML
    void muteEvent(ActionEvent event) {
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.changeButtonColor(mute);

        if (potPlayer.checkMedia() == false)
            return;

        if (potPlayer.getVolume() > 0) {
            potPlayer.saveMuteBefore(potPlayer.getVolume());
            sliderVolume.setValue(0);
        } else {
            potPlayer.setVolume(potPlayer.getMuteBefore());
            sliderVolume.setValue(potPlayer.getMuteBefore() * 100);
        }
    }

    private void playerInitialization() {
        potPlayer = new MediaPlayers("http://potplayer.init");       
    }

    private void sliderPreset() {
        progressTime.setProgress(0);
        progressVolume.setProgress(0);
        sliderTime.setValue(0);
        sliderVolume.setValue(0);
    }

    private void sliderTimeReset() {
        progressTime.setProgress(0);
        sliderTime.setValue(0);
    }

    private void sliderVolumeReset() {
        progressVolume.setProgress(0);
        sliderVolume.setValue(0);
    }

    private void mediaViewAction() {
        // click MediaView to play or pause
        mediaView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if (noFile == true) {
                    openFiles();
                } else {
                    playAndPause(potPlayer);
                }
            }
        });
    }

    public void fullScreenAction() {
        mediaView.setOnMouseMoved(event -> {
            if (StageMaxMin.stageFull != true)
                return;

            double cursorY = event.getScreenY();
            double y = playerMain.mainStage.getY();
            double h = playerMain.mainStage.getHeight();

            if ((cursorY - y) <= 100) {
                titleHBox.setVisible(true);
                sliderHBox.setVisible(false);
                playHBox.setVisible(false);
            } else if (((y + h) - cursorY) <= 100) {
                titleHBox.setVisible(false);
                sliderHBox.setVisible(true);
                playHBox.setVisible(true);  
            } else {
                titleHBox.setVisible(false);
                sliderHBox.setVisible(false);
                playHBox.setVisible(false); 
            }
        });
    }

    private void moduleRenew() {
        if (potPlayer.getPlayerStatus() == MediaPlayer.Status.UNKNOWN)
            return;

        TimeFormat format   = new TimeFormat();
        Duration   duration = potPlayer.getDuration();
        Duration   playTime = potPlayer.getPlaytime();
        Double     progress = format.timeProgress(playTime, duration);

        nameLabel.setText("  " + playerMain.fileDialog.readFileName());
        timeLabel.setText("  " + format.formatTime(playTime, duration));
        sliderTime.setValue(progress * 100);
        sliderVolume.setValue((int)Math.round(potPlayer.getVolume() * 100));

        if (progress >= 0.999) {
            potPlayer.setMediaEnd(true);

            ButtonStyle buttonStyle = new ButtonStyle();
            buttonStyle.changeButtonImage(play, "./logo/playing.jpg", 18, 18);

            timeLabel.setText("  " + format.formatTime(Duration.ZERO, duration));
            sliderTimeReset();
        }

        mediaView.setMediaPlayer(potPlayer.mediaPlayer);
        videoPane.setCenter(mediaView);

        // According to media size setting stage size
        playerMain.fitSize.stageSize(playerMain.mainStage, potPlayer.media);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sliderPreset();
        mediaViewAction();
        fullScreenAction();
        playerInitialization();

        sliderTime.valueProperty().addListener(new ChangeListener<Number>() {
            // read video time slider change
            @Override
            public void changed(ObservableValue<? extends Number> observable, 
                Number oldValue, Number newValue) {

                if (potPlayer.checkMedia() == false)
                    return;

                // renew the progressTime color
                progressTime.setProgress(newValue.doubleValue() / 100);

                Duration duration = potPlayer.getDuration();
                if (sliderTime.isValueChanging() && duration != null) {
                    potPlayer.setProgress(sliderTime.getValue() / 100.0);

                    if (noFile == false) {
                        ButtonStyle buttonStyle = new ButtonStyle();
                        buttonStyle.changeButtonImage(play, "./logo/pauseing.jpg", 18, 18);                        
                    }
                }

                if (newValue.intValue() > oldValue.intValue()) {
                    System.out.println("play:" + newValue.intValue() + "%");
                }
            }
        });

        sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
            // read video volume slider change
            @Override
            public void changed(ObservableValue<? extends Number> observable, 
                Number oldValue, Number newValue) {

                if (potPlayer.checkMedia() == false)
                    return;
                // renew the progressVolume color
                progressVolume.setProgress(newValue.doubleValue() / 100);

                potPlayer.setVolume(newValue.doubleValue() / 100);
                potPlayer.saveLastVolume(potPlayer.getVolume());

                if (newValue.intValue() > oldValue.intValue()) {
                    System.out.println("volume:" + newValue.intValue() + "%");
                }
            }
        });

        /** 
         * sliderTime.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
         *     read time slider whether released, if slider released start player
         *     @Override
         *     public void handle(MouseDragEvent event) {
         *         System.out.println("setOnMouseDragReleased");
         *         // mediaPlayer.play();
         *     }
         * });
         */
    }
}
