
package potplayer;

import javafx.util.Duration;


public class TimeFormat {
    /**
     * Duartion datatype convert to 
     * [hour:minute:second] format,
     * elapsed : current played time
     * duration: total time
     */
    protected String formatTime(Duration elapsed, Duration duration) {
        // Duartion datatype convert to int second data type
        int eT = (int)Math.floor(elapsed.toSeconds());
        int eH = (eT / (60 * 60));
        int eM = (eT - (eH * 60 * 60)) / 60;
        int eS = (eT - (eH * 60 * 60)) - (eM * 60);

        // Duartion datatype convert to int second data type
        int dT = (int)Math.floor(duration.toSeconds());
        int dH = (dT / (60 * 60));
        int dM = (dT - (dH * 60 * 60)) / 60;
        int dS = (dT - (dH * 60 * 60)) - (dM * 60);        

        // duration no more than the 0(s)
        if (!duration.greaterThan(Duration.ZERO)) {
            if (eH > 0) {
                // [hour:minute:second]
                return (String.format(
                    "%02d:%02d:%02d / %02d:%02d:%02d", 
                        eH, eM, eS));
            } else {
                // [minute:second]
                return (String.format(
                    "%02d:%02d / %02d:%02d", 
                        eM, eS));
            }
        } else {
            if (dH > 0) {
                // [hour:minute:second]
                return (String.format(
                    "%02d:%02d:%02d / %02d:%02d:%02d", 
                        eH, eM, eS, dH, dM, dS));                
            } else {
                // [minute:second]
                return (String.format(
                    "%02d:%02d / %02d:%02d",
                        eM, eS, dM, dS));                
            }
        }
    }

    protected Double timeProgress(Duration elapsed, Duration duration) {
        /**
         * Duration is total public time(ms)
         * return the time percent, percent = current/sum
         * double data type [0.0 -> 1.0]
         */
        return elapsed.toMillis() / duration.toMillis();
    }
}
