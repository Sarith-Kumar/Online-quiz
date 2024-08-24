import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminInterface extends JFrame implements ActionListener {

    private List<Question> questions;
    private JTextField questionField;
    private JTextField option1Field, option2Field, option3Field, option4Field;
    private JTextField correctAnswerField;
    private JButton addButton, deleteButton;

    public AdminInterface(List<Question> questions) {
        this.questions = questions;
        setBounds(50, 50, 600, 400);
        setLayout(new GridLayout(7, 2));

        questionField = new JTextField();
        option1Field = new JTextField();
        option2Field = new JTextField();
        option3Field = new JTextField();
        option4Field = new JTextField();
        correctAnswerField = new JTextField();

        add(new JLabel("Question:"));
        add(questionField);
        add(new JLabel("Option 1:"));
        add(option1Field);
        add(new JLabel("Option 2:"));
        add(option2Field);
        add(new JLabel("Option 3:"));
        add(option3Field);
        add(new JLabel("Option 4:"));
        add(option4Field);
        add(new JLabel("Correct Answer:"));
        add(correctAnswerField);

        addButton = new JButton("Add Question");
        deleteButton = new JButton("Delete Question");
        add(addButton);
        add(deleteButton);

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String title = questionField.getText();
            List<String> options = List.of(option1Field.getText(), option2Field.getText(), option3Field.getText(), option4Field.getText());
            String correctAnswer = correctAnswerField.getText();
            questions.add(new Question(title, options, correctAnswer));
            JOptionPane.showMessageDialog(this, "Question Added");
        } else if (e.getSource() == deleteButton) {
            // Implement deletion logic (e.g., removing the last question or providing a list to select from)
            if (!questions.isEmpty()) {
                questions.remove(questions.size() - 1);
                JOptionPane.showMessageDialog(this, "Last Question Removed");
            } else {
                JOptionPane.showMessageDialog(this, "No Questions to Remove");
            }
        }
    }
}

