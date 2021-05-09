
package potplayer;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;


public class ButtonStyle {
    // private can only be used class internally
    public void changeButtonColor(Button button) {
        ColorAdjust colorAdjust = new ColorAdjust();
        button.setOnMousePressed(event ->  {
            colorAdjust.setBrightness(0.3);
            button.setEffect(colorAdjust);
        });

        button.setOnMouseReleased(event -> {
            colorAdjust.setBrightness(0);
            button.setEffect(colorAdjust);
        });
    }

    public void changeButtonImage(Button button, String imageName, int width, int height) {
        imageName = getClass().getResource(imageName).toString();
        Image icon = new Image(imageName);
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        // imageView.setFitHeight((int)(30 * icon.getHeight() / icon.getWidth()));
        button.setGraphic(imageView);
    }
}
