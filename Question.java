import java.util.ArrayList;
import java.util.List;

public class Question {
    private String title;
    private List<String> options;
    private String correctAnswer;

    // Constructor
    public Question(String title, List<String> options, String correctAnswer) {
        this.title = title;
        this.options = new ArrayList<>(options);
        this.correctAnswer = correctAnswer;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
