
package potplayer;

import java.io.File;
import java.util.List;
import javafx.application.Application;
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
import javafx.scene.input.MouseEvent;
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
import javafx.scene.layout.Pane;
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

import potplayer.PlayList;
import java.util.Map;
import potplayer.DisplayAreaSize;
import potplayer.PotPlayerConsts;
import potplayer.StageMaxMin;


public class StageActivity {
    private double mouseX = 0.0;
    private double mouseY = 0.0;
    private double stageX = 0.0;
    private double stageY = 0.0;

    private boolean onAbove = false;
    private boolean onBottom = false;
    private boolean onLeft = false;
    private boolean onRight = false;

    private boolean onLAbove = false;
    private boolean onLBottom = false;
    private boolean onRAbove = false;
    private boolean onRbottom = false;
    private boolean stageResize = false;

    public void adsorption(Stage beMoveStage) {
        Map<String, Double> screen = DisplayAreaSize.screenSize();
        double screenWidth = screen.get("width");
        double screenHeight = screen.get("height");
        
        double x = beMoveStage.getX();
        double y = beMoveStage.getY();

        double w = beMoveStage.getWidth();
        double h = beMoveStage.getHeight();

        final int adsorptionRange = PotPlayerConsts.ADSORPTION_RANGE;
        int missionBoard = DisplayAreaSize.missionBoarHeight();

        if ((y > 0) && (y < adsorptionRange)) {
            // adsorption above
            beMoveStage.setY(0);
        }
        if (((y + h) > (screenHeight - missionBoard - adsorptionRange)) && ((y + h) < (screenHeight - missionBoard))) {
            // adsorption mission board above
            beMoveStage.setY((screenHeight - missionBoard) - h);
        }
        if (((y + h) > (screenHeight - missionBoard + adsorptionRange)) && ((y + h) < screenHeight)) {
            // adsorption bottom
            beMoveStage.setY(screenHeight - h);
        }

        if ((x > 0) && (x < adsorptionRange)) {
            // adsorption left
            beMoveStage.setX(0);
        }
        if (((x + w) > (screenWidth - adsorptionRange)) && ((x + w) < screenWidth)) {
            // adsorption right
            beMoveStage.setX(screenWidth - w);
        }
    }

    public void stageMove(Stage beMoveStage, Scene bePressedScene) {
        // mouse pressed handle
        bePressedScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {      
                // sava when mouse click, mouse and stage on screen coordinate
                mouseX = event.getScreenX();
                mouseY = event.getScreenY();

                stageX = beMoveStage.getX();
                stageY = beMoveStage.getY();

                StageMaxMin.saveDraggedMaxBeforeSize(beMoveStage.getX(), beMoveStage.getY(), 
                    beMoveStage.getWidth(), beMoveStage.getHeight());
            }                
        });

        // mouse dragged handle
        bePressedScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                if (stageResize == false) {
                    beMoveStage.setX(stageX + (event.getScreenX() - mouseX));
                    beMoveStage.setY(stageY + (event.getScreenY() - mouseY));
                    // System.out.println("Scene mouse pressed and move");

                    adsorption(beMoveStage);

                    PlayList.renewPlayListSize(beMoveStage.getX(), beMoveStage.getY(), 
                        beMoveStage.getWidth(), beMoveStage.getHeight());

                    StageMaxMin.draggedMax(beMoveStage, event);
                    StageMaxMin.draggedMin(beMoveStage, event);
                }
            }                                                
        });
    }

    public void stageCloseAction(Stage beCloseStage) {
        beCloseStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Stage closed");
            }
        });
    }

    // VBox or Pane, because VBox is part of Pane
    public void stageSizeChange(VBox vBox) {
        vBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("VBox height change" + newValue);
            }
        });

        vBox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("VBox width change" + newValue);
            }
        });
    }

    // VBox or Pane, because VBox is part of Pane
    public void stageResize(Stage stage, VBox vBox) {
        vBox.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double cursorX = event.getSceneX();
                double cursorY = event.getSceneY();
                double width = stage.getWidth();
                double height = stage.getHeight();
                Cursor cursorType = Cursor.DEFAULT;

                final int resizeRange = 6;

                /**
                 *             Cursor Type
                 * Cursor.E_RESIZE,   Cursor.W_RESIZE
                 * Cursor.S_RESIZE,   Cursor.N_RESIZE
                 * 
                 * Cursor.NE_RESIZE, Cursor.SE_RESIZE
                 * Cursor.NW_RESIZE, Cursor.SW_RESIZE
                 * 
                 * Cursor.H_RESIZE,   Cursor.V_RESIZE
                 */
                if (cursorX >= (width - resizeRange)) {
                    if (cursorY >= (height - resizeRange)) {
                        cursorType = Cursor.SE_RESIZE;
                        onRbottom = true;
                    }  else if (cursorY <= resizeRange) {
                        cursorType = Cursor.NE_RESIZE;
                        onRAbove = true;
                    } else {
                        cursorType = Cursor.E_RESIZE;
                        onRight = true;
                    }
                } else if (cursorX <= resizeRange) {
                    if (cursorY >= (height - resizeRange)) {
                        cursorType = Cursor.SW_RESIZE;
                        onLBottom = true;
                    } else if (cursorY <= resizeRange) {
                        cursorType = Cursor.NW_RESIZE;
                        onLAbove = true;
                    } else {
                        cursorType = Cursor.W_RESIZE;
                        onLeft = true;
                    }
                } else if (cursorY <= resizeRange) {
                    cursorType = Cursor.N_RESIZE;
                    onAbove = true;
                } else if (cursorY >= (height - resizeRange)) {
                    cursorType = Cursor.S_RESIZE;
                    onBottom = true;
                }
                vBox.setCursor(cursorType);
            }
        });

        vBox.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double mouseX = event.getScreenX();
                double mouseY = event.getScreenY(); 
                double cursorX = event.getSceneX();
                double cursorY = event.getSceneY();
                double stageX = stage.getX();
                double stageY = stage.getY();
                double stageWidth = stage.getWidth();
                double stageHeight = stage.getHeight();

                if (onAbove) {
                    stageResize = true;
                    stage.setY(mouseY);
                    if ((stageHeight - cursorY) <= PotPlayerConsts.PLAYER_MIN_H) {
                        stageHeight = PotPlayerConsts.PLAYER_MIN_H;
                        cursorY = 0;
                    }

                    stage.setHeight(stageHeight - cursorY);
                }
                if (onBottom) {
                    stageResize = true;
                    stageHeight = cursorY;
                    if (stageHeight <= PotPlayerConsts.PLAYER_MIN_H)
                        stageHeight = PotPlayerConsts.PLAYER_MIN_H;
                    stage.setHeight(stageHeight);
                }
                if (onLeft) {
                    stageResize = true;
                    stage.setX(mouseX);
                    if ((stageWidth - cursorX) <= PotPlayerConsts.PLAYER_MIN_W) {
                        stageWidth = PotPlayerConsts.PLAYER_MIN_W;
                        cursorX = 0;
                    }

                    stage.setWidth(stageWidth - cursorX);
                }
                if (onRight) {
                    stageResize = true;
                    stageWidth = cursorX;
                    if (stageWidth <= PotPlayerConsts.PLAYER_MIN_W)
                        stageWidth = PotPlayerConsts.PLAYER_MIN_W;
                    stage.setWidth(stageWidth);
                }

                if (onLAbove) {
                    stageResize = true;
                    stage.setX(mouseX);
                    stage.setY(mouseY);

                    if ((stageWidth - cursorX) <= PotPlayerConsts.PLAYER_MIN_W) {
                        stageWidth = PotPlayerConsts.PLAYER_MIN_W;
                        cursorX = 0;
                    }
                    if ((stageHeight - cursorY) <= PotPlayerConsts.PLAYER_MIN_H) {
                        stageHeight = PotPlayerConsts.PLAYER_MIN_H;
                        cursorY = 0;
                    }

                    stage.setWidth(stageWidth - cursorX);
                    stage.setHeight(stageHeight - cursorY);
                }
                if (onLBottom) {
                    stageResize = true;
                    stage.setX(mouseX);
                    stage.setWidth(stageWidth - cursorX);
                    stage.setHeight(cursorY);
                }
                if (onRAbove) {
                    stageResize = true;
                    stage.setY(mouseY);
                    stage.setWidth(cursorX);
                    stage.setHeight(stageHeight - cursorY); 
                }
                if (onRbottom) {
                    stageResize = true;
                    stage.setWidth(cursorX);
                    stage.setHeight(cursorY);
                }
                PlayList.renewPlayListSize(stage.getX(), stage.getY(), 
                                        stage.getWidth(), stage.getHeight());
            }
        });

        vBox.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onAbove = false;
                onBottom = false;
                onLeft = false;
                onRight = false;
                onLAbove = false;
                onLBottom = false;
                onRAbove = false;
                onRbottom = false;
                stageResize = false;

                Cursor cursorType = Cursor.DEFAULT;
                vBox.setCursor(cursorType); 
            }
        });
    }
}
