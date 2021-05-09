
package potplayer;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;


public class ValueConvert {
    // example [-50, 0] -> [0, 100]
    // negativeToPositive(-50, 100, -10) = 80
    public float negativeToPositive(float nMin, float pMax, float fVal) {
        float result;
        float p;

        nMin = -nMin;
        p = pMax / nMin;

        result = (fVal + nMin) * p;

        return result;
    }
}
