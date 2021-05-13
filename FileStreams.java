
package potplayer;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.FileWriter;


public class FileStreams {
    InputStream inputStream;
    OutputStream outputStream;

    String filePath;
    String defaultPath = "./potplayer.play";

    public FileStreams(String path) {
        /**
         * [C:\example\com\java\video\hello.txt]
         * Specify the file to be read and written
         */
        this.filePath = path;
    }

    public String readFile() {
        int flength;

        try {
            this.inputStream = new FileInputStream(this.filePath);

            if (this.inputStream != null) {
                flength = (int)(new File(this.filePath).length());
                byte[] bytes = new byte[flength];
                inputStream.read(bytes);

                return new String(bytes);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeFile(String content) {
        try {
            this.outputStream = new FileOutputStream(this.filePath);

            if (this.outputStream != null) {
                this.outputStream.write(content.getBytes());
                this.outputStream.close();
            } else {
                this.outputStream = new FileOutputStream(this.defaultPath);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFiles(String content) {
        File files = new File(this.filePath);
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWrite = null;

        try {
            // If the file does not exist, create this file
            if (!files.exists()) {
                // create new file success
                if (files.createNewFile()) {
                    fileOutputStream = new FileOutputStream(files);
                }
            } else {
                // The file already exists
                fileOutputStream = new FileOutputStream(files, true);
            }
            outputStreamWrite = new OutputStreamWriter(fileOutputStream, "utf-8");
            outputStreamWrite.write(content);
            outputStreamWrite.write("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStreamWrite != null) {
                    outputStreamWrite.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Boolean checkdir(String dir) {
        /**
         * dir type [./example/hello.mp3]
         * dir type [./example/"]
         * or type  ["C:\\example\\hello.mp3]
         */
        File check = new File(dir);

        if (check != null) {
            if (!check.exists()) {
                // The directory not exists
                return false;
            } else {
                // The directory exists
                return true;
            }
        } else {
            // new file object error
            return false;
        }
    }

    public static Boolean mkdir(String dir) {
        // dir type [./example/hello]
        File make = new File(dir);

        if (make != null) {
            // If the directory does not exist, 
            // create this directory
            if (!make.exists()) {
                make.mkdir();
                // directory make success
                return true;
            } else {
                // The directory already exists
                return true;
            }
        } else {
            // new file object error
            return false;
        }
    }

    public static Boolean emptyFile(String fileName) {
        File file = new File(fileName);

        if (file.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean deleteFile(String dir) {
        File file = new File(dir);
        try {
            if (file.delete()) {
                // delete success
                return true;
            } else {
                // delete fail
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteFileContent(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int totalNumberOfRows(String fileName) {
        int lines = 0;

        try {
            File f = new File(fileName);

            FileReader read = new FileReader(f);
            LineNumberReader lineReader = new LineNumberReader(read);
            String string = lineReader.readLine();

            while (string != null) {
                lines++;
                string = lineReader.readLine();
            }

            lineReader.close();
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static String readAnyLine(String fileName, int lineNumber) {
        // file begin from 1 line
        int lines = 0;
        String string;

        try {
            File f = new File(fileName);

            FileReader read = new FileReader(f);
            LineNumberReader lineReader = new LineNumberReader(read);

            while (lineReader != null) {
                string = lineReader.readLine();
                lines++;

                if (lines == lineNumber) {
                    lineReader.close();
                    read.close();

                    return string;
                }
                if (string == null) {
                    // No content, exit the loop
                    return null;
                }
            }

            lineReader.close();
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
