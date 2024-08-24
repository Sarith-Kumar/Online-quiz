import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz extends JFrame implements ActionListener {

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private JLabel qno, question, feedbackLabel;
    private JRadioButton opt1, opt2, opt3, opt4;
    private ButtonGroup groupoptions;
    private JButton next, submit, lifeline, adminButton;

    private Timer timer;
    private int countdown = 15;
    private int score = 0;
    private String name;

    public Quiz(String name) {
        this.name = name;
        setBounds(50, 0, 1440, 850);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("quiz.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 392);
        add(image);

        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        feedbackLabel = new JLabel();
        feedbackLabel.setBounds(150, 700, 900, 30);
        feedbackLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        feedbackLabel.setForeground(Color.RED);
        add(feedbackLabel);

        opt1 = new JRadioButton();
        opt1.setBounds(170, 520, 700, 30);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(170, 560, 700, 30);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(170, 600, 700, 30);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(170, 640, 700, 30);
        opt4.setBackground(Color.WHITE);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = new JButton("Next");
        next.setBounds(1100, 550, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(30, 144, 255));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        lifeline = new JButton("50-50 Lifeline");
        lifeline.setBounds(1100, 630, 200, 40);
        lifeline.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lifeline.setBackground(new Color(30, 144, 255));
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);
        add(lifeline);

        submit = new JButton("Submit");
        submit.setBounds(1100, 710, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        adminButton = new JButton("Admin");
        adminButton.setBounds(50, 710, 100, 40);
        adminButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        adminButton.setBackground(new Color(30, 144, 255));
        adminButton.setForeground(Color.WHITE);
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminInterface(questions);
            }
        });
        add(adminButton);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countdown > 0) {
                    countdown--;
                    repaint();
                } else {
                    timer.stop();
                    handleTimeUp();
                }
            }
        });

        initializeQuestionsAndAnswers();
        resetQuiz();

        setVisible(true);
    }

    private void initializeQuestionsAndAnswers() {
        questions = new ArrayList<>();
        questions.add(new Question("Which is used to find and fix bugs in the Java programs?", 
            Arrays.asList("JVM", "JDB", "JDK", "JRE"), "JDB"));
        questions.add(new Question("What is the return type of the hashCode() method in the Object class?", 
            Arrays.asList("int", "Object", "long", "void"), "int"));
        questions.add(new Question("Which package contains the Random class?", 
            Arrays.asList("java.util package", "java.lang package", "java.awt package", "java.io package"), "java.util package"));
        questions.add(new Question("An interface with no fields or methods is known as?", 
            Arrays.asList("Runnable Interface", "Abstract Interface", "Marker Interface", "CharSequence Interface"), "Marker Interface"));
        questions.add(new Question("In which memory a String is stored, when we create a string using new operator?", 
            Arrays.asList("Stack", "String memory", "Random storage space", "Heap memory"), "Heap memory"));
        questions.add(new Question("Which of the following is a marker interface?", 
            Arrays.asList("Runnable interface", "Remote interface", "Readable interface", "Result interface"), "Remote interface"));
        questions.add(new Question("Which keyword is used for accessing the features of a package?", 
            Arrays.asList("import", "package", "extends", "export"), "import"));
        questions.add(new Question("In java, jar stands for?", 
            Arrays.asList("Java Archive Runner", "Java Archive", "Java Application Resource", "Java Application Runner"), "Java Archive"));
        questions.add(new Question("Which of the following is a mutable class in java?", 
            Arrays.asList("java.lang.StringBuilder", "java.lang.Short", "java.lang.Byte", "java.lang.String"), "java.lang.StringBuilder"));
        questions.add(new Question("Which of the following option leads to the portability and security of Java?", 
            Arrays.asList("Bytecode is executed by JVM", "The applet makes the Java code secure and portable", "Use of exception handling", "Dynamic binding between objects"), "Bytecode is executed by JVM"));
    }

    private void resetQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        start(currentQuestionIndex);
        countdown = 15; // Reset the countdown for the first question
        timer.restart(); // Restart the timer for the first question
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            handleNextButton();
        } else if (ae.getSource() == lifeline) {
            handleLifelineButton();
        } else if (ae.getSource() == submit) {
            handleSubmitButton();
        }
    }

    private void handleNextButton() {
        String selectedOption = groupoptions.getSelection() != null ? groupoptions.getSelection().getActionCommand() : "";
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Provide feedback
        if (selectedOption.equals(currentQuestion.getCorrectAnswer())) {
            feedbackLabel.setText("Correct!");
            score += 10;
        } else {
            feedbackLabel.setText("Incorrect! Correct answer: " + currentQuestion.getCorrectAnswer());
        }

        // Move to next question
        if (currentQuestionIndex == questions.size() - 1) {
            next.setEnabled(false);
            submit.setEnabled(true);
        } else {
            currentQuestionIndex++;
            start(currentQuestionIndex);
            countdown = 15; // Reset the countdown for the next question
            timer.restart(); // Restart the timer for the next question
        }
    }

    private void handleLifelineButton() {
        // Implement lifeline logic if needed
        lifeline.setEnabled(false);
    }

    private void handleSubmitButton() {
        String selectedOption = groupoptions.getSelection() != null ? groupoptions.getSelection().getActionCommand() : "";
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Provide immediate feedback on the selected answer
        if (selectedOption.equals(currentQuestion.getCorrectAnswer())) {
            feedbackLabel.setText("Correct! Your score: " + (score + 10));
            score += 10;
        } else {
            feedbackLabel.setText("Incorrect! The correct answer was: " + currentQuestion.getCorrectAnswer());
        }

        // Disable the Submit and Next buttons to prevent further actions
        submit.setEnabled(false);
        next.setEnabled(false);

        // If it's the last question, show the final score
        if (currentQuestionIndex == questions.size() - 1) {
            Timer delayTimer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    new Score(name, score);
                }
            });
            delayTimer.setRepeats(false); // Ensure the timer runs only once
            delayTimer.start();
        } else {
            // Otherwise, prepare for the next question
            Timer delayTimer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentQuestionIndex++;
                    start(currentQuestionIndex);
                    countdown = 15; // Reset the countdown for the next question
                    timer.restart(); // Restart the timer for the next question
                    submit.setEnabled(true); // Re-enable the Submit button
                    next.setEnabled(true); // Re-enable the Next button
                }
            });
            delayTimer.setRepeats(false); // Ensure the timer runs only once
            delayTimer.start();
        }
    }

    private void handleTimeUp() {
        String selectedOption = groupoptions.getSelection() != null ? groupoptions.getSelection().getActionCommand() : "";
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Provide feedback for time up
        if (selectedOption.equals(currentQuestion.getCorrectAnswer())) {
            feedbackLabel.setText("Time's up! The answer was: " + currentQuestion.getCorrectAnswer());
            score += 10;
        } else {
            feedbackLabel.setText("Time's up! The correct answer was: " + currentQuestion.getCorrectAnswer());
        }

        if (currentQuestionIndex == questions.size() - 1) {
            setVisible(false);
            new Score(name, score);
        } else {
            currentQuestionIndex++;
            start(currentQuestionIndex);
            countdown = 15; // Reset the countdown
            timer.restart(); // Restart the timer
        }
    }

    public void start(int index) {
        Question currentQuestion = questions.get(index);
        qno.setText("" + (index + 1) + ". ");
        question.setText(currentQuestion.getTitle());
        List<String> options = currentQuestion.getOptions();
        opt1.setText(options.get(0));
        opt1.setActionCommand(options.get(0));
        opt2.setText(options.get(1));
        opt2.setActionCommand(options.get(1));
        opt3.setText(options.get(2));
        opt3.setActionCommand(options.get(2));
        opt4.setText(options.get(3));
        opt4.setActionCommand(options.get(3));

        groupoptions.clearSelection();
        feedbackLabel.setText(""); // Clear feedback label
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        String time = "Time left - " + countdown + " seconds";
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 25));
        g.drawString(time, 1100, 500);
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}