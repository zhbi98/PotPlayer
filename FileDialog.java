
package potplayer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import potplayer.FileStreams;


public class FileDialog {
    FileChooser fileChooser;
    File fileName;

    // already opened files
    String openedName = null;
    String lastPath = null;

    String settingPath = "./src/potplayer/data/setting.play";

    public FileDialog() {
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Open");

        this.fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All",   "*.*"),
            new FileChooser.ExtensionFilter("Music", "*.mp3"),
            new FileChooser.ExtensionFilter("Video", "*.mp4"));
    }

    public void fileDialogShow(Stage primaryStage) {
        /**
         * Each time you open the file chooser, read the saved path
         * that was opened last time, and use this path to initialize
         * the file selector to enter the path where it was last time.
         *
         * testing does it exist ["./src/potplayer/data"] directory,
         * if directory not exist, create the directory 
         */

        FileStreams initLastPath = new FileStreams(this.settingPath);
        // read the path file, use next time init file dialog
        this.initFileChooser(this.fileChooser, initLastPath, this.settingPath);

        /**
         * showOpenMultipleDialog object return 
         * is not a file, return is a file list
         * can use (List<File>)object.get(0...n)
         * to get the one of file
         * 
         * (List<File>)fileName = this.fileChooser.showOpenMultipleDialog(primaryStage);
         * example:this.fileName = this.fileChooser.showOpenMultipleDialog(primaryStage).get(0);
         */
        this.fileName = this.fileChooser.showOpenDialog(primaryStage);

        // List<File> fileList = this.fileChooser.showOpenMultipleDialog(primaryStage);
        // System.out.println("size" + fileList.size());
        // this.fileName = fileList.get(0);

        if (this.fileName != null) {
            saveDifferentPath(this.lastPath, this.fileName.toString());
        }
    }

    public File filePath() {
        // File data type
        if (this.fileName != null) {
            return this.fileName;
        } else {
            return null;
        }
    }

    public String filePathString() {
        if (this.fileName != null) {
            return this.fileName.toString();
        } else {
            return null;
        }
    }

    public String filePathURL() {
        /**
         * String path = C:\example\hello.mp4
         * return [file:/C:/example/hello.mp4]
         */
        if (this.fileName != null) {
            return this.fileName.toURI().toString();
        } else {
            return null;
        }
    }

    public String readFileName() {
        /**
         * String path = C:\example\com\java\video\hello.mp4
         * i = readFileName(path);
         * return [hello.mp4]
         */

        if (this.fileName != null) {
            String path = this.fileName.toString();
            File tFile = new File(path.trim());
            String fname = tFile.getName();

            openedName = fname;
            return fname;
        } else {
            /** 
             * if not select a file then return last time already
             * opened file name, if openedName is null, return " "
             * so title will not display "null"
             */
            if (openedName == null)
                return " ";
            return openedName;
        }
    }

    public String readFileNames(String path) {
        /**
         * String path = C:\example\com\java\video\hello.mp4
         * i = readFileName(path);
         * return [hello.mp4]
         */

        if (path != null) {
            File tFile = new File(path.trim());
            String fname = tFile.getName();
            return fname;
        } else {
            return null;
        }
    }

    public String readFileExtension(String fileName) {
        /**
         * String path = C:\example\com\java\video\hello.mp4
         * i = readFileExtension(path);
         * return [mp4]
         */
        int index = fileName.lastIndexOf(".");
        if (index == -1)
            return null;
        String exten = fileName.substring(index + 1);
        return exten;
    }

    public String readFilePath(String fileName) {
        /**
         * String fileName = C:\example\com\java\video\hello.mp4
         * readFilePath(fileName);
         * return [C:\example\com\java\video]
         */

        if (fileName != null) {
            File tFile = new File(fileName);
            String fpath = tFile.getParent();

            return fpath;
        } else {
            return null;
        }
    }

    public String pathWindowsToLinux(String path) {
        /**
         * [windows]:C:\example\java\hello.mp4
         *                   ||
         * [linux]  :C:/example/java/hello.mp4
         */
        path = path.replaceAll("\\\\", "/");

        return path;
    }

    public String pathWindowsToURL(String path) {
        /**
         * [windows]:C:\example\java\hello.mp4
         *                   ||
         * [URL]    :file:/C:/example/java/Ava%20Max-Sweet%20but%20Psycho.mp4
         */
        try {
            File tFile = new File(path);
            String url = tFile.toURI().toURL().toString();
            return url;            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isSamePath(String path1, String path2) {
        /**
         * path1: C:\example\java
         * path2: C:\example\hello
         */
        if (path1.equals(path2) == true) {
            return true;
        } else {
            return false;
        }
    }

    public void saveOpendFilePath(FileStreams fStream, String path) {
        /**
         * save the last file name, by file name get file path, 
         * file path use to next time init file dialog
         */ 
        if (path != null) {
            fStream.writeFile(path);
        } else {
            return;
        }
    }

    public void saveDifferentPath(String oldPath, String newPath) {
        /**
         * save the current file name, by file name get file path, 
         * file path use to next time init file dialog
         */ 

        String tNewPath = null;
        String tOldPath = null;

        if (newPath != null) {
            tNewPath = this.readFilePath(newPath);
            tOldPath = this.readFilePath(oldPath);

            if (isSamePath(tNewPath, tOldPath) == false) {
                FileStreams fSavePath = new FileStreams(this.settingPath);
                this.saveOpendFilePath(fSavePath, newPath);

                this.lastPath = newPath;
                System.out.println("Will save the path");
            } else {
                System.out.println("Do not save the path");
            }
        }
    }

    public void initFileChooser(FileChooser chooser, FileStreams fStream, String beCheckDir) {
        if (fStream.emptyFile(beCheckDir) == true) {
            System.out.println("setting file is empty");
            return;                
        }

        // read last time file path to init file dialog directory
        if (FileStreams.mkdir(beCheckDir) == true) {
            // directory create success
            String names = fStream.readFile();

            if ((names != null) && (FileStreams.checkdir(names) == true)) {
                names = this.readFilePath(names);
                names = this.pathWindowsToLinux(names);

                chooser.setInitialDirectory(new File(names));

                System.out.println("Init file chooser" + names);
            }
        }
    }
}
