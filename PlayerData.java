
package potplayer;

import potplayer.FileDialog;
import potplayer.FileStreams;


/**
 *  if (FileStreams.mkdir("./src/potplayer/data") == true) {
 *      FileStreams fileIo = new FileStreams("./src/potplayer/data/setting.play");
 *      fileIo.writeFiles("C:\example\com\java\video\hello.mp4");
 *      String names = fileIo.readFile();
 *      names = fileDialog.readFilePath(names);
 *      names = names.replaceAll("\\\\", "/");
 *
 *      System.out.println(names);
 *  }
 */

public class PlayerData {
    public void savePlayedFileName(String path, String content) {
        FileDialog fileDialog = new FileDialog();

        // Check if the directory exists
        if (FileStreams.mkdir(fileDialog.readFilePath(path)) == true) {
            FileStreams saveFile = new FileStreams(path);
            saveFile.writeFiles(content);
        }
    }

    public int getFileTotalNumOfRow(String filePath) {
        // Check if the directory exists
        int rows = FileStreams.totalNumberOfRows(filePath);
        return rows;
    }
}
