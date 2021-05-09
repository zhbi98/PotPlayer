
package potplayer;

import java.lang.reflect.InvocationTargetException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import potplayer.ResourcesJvm;


class MediaPlayerSetting {
    // Preset volume value is 0.5
    static double lastVolume = 0.5;
    static double muteBefore = 0.0;

    MediaPlayerSetting() {
        return;
    }

    static void setSaveVolume(double v) {
        lastVolume = v;
    }

    static double getSaveVolume() {
        return lastVolume;
    }

    static void setSaveMuteBefore(double v) {
        muteBefore = v;
    }

    static double getSaveMuteBefore() {
        return muteBefore;
    }
}


public class MediaPlayers {
    Media media;
    MediaPlayer mediaPlayer;

    Duration duration;
    Duration playTime;

    boolean mediaEnd = false;

    public MediaPlayers(String fileName) {
        this.media = new Media(fileName);
        this.mediaPlayer = new MediaPlayer(this.media);
    }

    public void seekStart() {
        this.mediaPlayer.seek(this.mediaPlayer.getStartTime());
    }

    public void play() {
        this.mediaPlayer.play();
    }

    public void pause() {
        this.mediaPlayer.pause();
    }

    public void stop() {
        this.mediaPlayer.stop();
    }

    public MediaPlayer.Status getPlayerStatus() {
        /**
         * HALTED, UNKNOWN, PAUSED
         * READY, STOPPED, PLAYING
         */
        return this.mediaPlayer.getStatus();
    }

    public boolean checkMedia() {
        if (this.media != null) {
            return true;
            // media is exists
        } else {
            return false;
            // media not exists
        }
    }

    public void setMediaEnd(boolean end) {
        if (end == true) {
            this.mediaEnd = true;
        } else {
            this.mediaEnd = false;
        }
    }

    public boolean isMediaEnd() {
        if (this.mediaEnd == true) {
            return true;
        } else {
            return false;
        }
    }

    public Duration getDuration() {
        return this.mediaPlayer.getMedia().getDuration();
    }

    public Duration getPlaytime() {
        return this.mediaPlayer.getCurrentTime();
    }

    public void setVolume(double volume) {
        // double data type [0.0 -> 1.0]
        this.mediaPlayer.setVolume(volume);
    }

    public double getVolume() {
        // double data type [0.0 -> 1.0]
        return this.mediaPlayer.getVolume();
    }

    public void setProgress(double progress) {
        // double data type [0.0 -> 1.0]
        Duration d = this.mediaPlayer.getMedia().getDuration();
        this.mediaPlayer.seek(d.multiply(progress));
        this.mediaPlayer.play();
    }

    public double getProgress() {
        // double data type [0.0 -> 1.0]
        // toMillis convert time to (ms)
        return (this.getPlaytime().toMillis() / this.getDuration().toMillis());
    }

    public void setAudioRenew(double speed) {
        // double data type [0.0 -> 1.0]
        // set float[] magnitudes renew time
        this.mediaPlayer.setAudioSpectrumInterval(speed);
    }

    public void releaseMemory() {
       if (this.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
            this.mediaPlayer.stop();
       }
       mediaPlayer.dispose();
       this.media = null;
       this.mediaPlayer = null;
       ResourcesJvm.noticeJVM();
    }

    public void saveLastVolume(double value) {
        // double data type [0.0 -> 1.0]
        // save last media play volume
        MediaPlayerSetting.setSaveVolume(value);
    }

    public double getLastVolume() {
        // double data type [0.0 -> 1.0]
        // get last media play volume
        return MediaPlayerSetting.getSaveVolume();
    }

    public void saveMuteBefore(double value) {
        // double data type [0.0 -> 1.0]
        // mute before save player volute
        MediaPlayerSetting.setSaveMuteBefore(value);
    }

    public double getMuteBefore() {
        // double data type [0.0 -> 1.0]
        // cancel player mute, get mute before volume,
        // to set player volume
        return MediaPlayerSetting.getSaveMuteBefore();
    }
}
