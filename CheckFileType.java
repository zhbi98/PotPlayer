
package potplayer;

import potplayer.FileDialog;


public class CheckFileType {
    public static boolean checkType(String filePath) {
        // Supported file type list
        String[] extension = new String[]{"mp3", "mp4"};
        boolean suitable = false;

        FileDialog extractExtension = new FileDialog();
        String exten = extractExtension.readFileExtension(filePath);

        // extension unified conversion to lower case
        exten = exten.toLowerCase();

        for (int i = 0; i < extension.length; i++) {
            if (exten.equals(extension[i]) == true) {
                suitable = true;
            }
        }

        // suitable = false, file type not support
        // suitable = true,  file type supported
        return suitable;
    }
}
