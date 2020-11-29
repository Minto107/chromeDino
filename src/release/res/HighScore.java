package release.res;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HighScore {

    private final String fileLocation;
    private int highScore;

    public HighScore(String fileLocation) {
        this.fileLocation = fileLocation;
        File newFile = new File(fileLocation);
        if (newFile.length() == 0) {
            try {
                FileWriter writer = new FileWriter(newFile);
                writer.write("0");
                writer.close();
                this.highScore = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.highScore = readHighScore();
        }
    }

    public String getFileLocation() {
        return fileLocation;
    }


    public int readHighScore() {
        try {
            String data = new String(Files.readAllBytes(Paths.get(fileLocation)));
            highScore = Integer.parseInt(data);
            return highScore;
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku w lokalizacji " + fileLocation);
            return -1;
        } catch (IOException e) {
            return -1;
        } catch (NumberFormatException e) {
            //
        }
        return -1;
    }

    public void saveHighScore(int score) {
        if (score > highScore) {
            String str = Integer.toString(score);
            System.out.println("New high score is " + str);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
                writer.write(str);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
