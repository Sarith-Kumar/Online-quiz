import utils.ScoreRecorder;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreRecorder {

    public static void recordScore(String userName, int score) {
        try (FileWriter writer = new FileWriter(userName + "_history.txt", true)) {
            writer.write("Score: " + score + " - Date: " + new java.util.Date() + "\n");
        } catch (IOException e) {
            System.err.println("Error writing score to file: " + e.getMessage());
        }
    }
}

