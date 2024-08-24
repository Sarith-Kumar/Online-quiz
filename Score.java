import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JFrame implements ActionListener {

    private String userName;
    private int userScore;

    // Constructor to initialize the Score screen
    public Score(String name, int score) {
        this.userName = name;
        this.userScore = score;

        setTitle("Quiz Score");
        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("score.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 200, 300, 250);
        add(image);

        JLabel heading = new JLabel("Thank you " + name + " for playing Simple Minds");
        heading.setBounds(45, 30, 700, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(heading);

        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(350, 200, 300, 30);
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(lblscore);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(380, 270, 120, 30);
        playAgainButton.setBackground(new Color(30, 144, 255));
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.addActionListener(this);
        add(playAgainButton);

        JButton showHistoryButton = new JButton("Show Score History");
        showHistoryButton.setBounds(380, 320, 200, 30);
        showHistoryButton.setBackground(new Color(30, 144, 255));
        showHistoryButton.setForeground(Color.WHITE);
        showHistoryButton.addActionListener(e -> new ScoreHistory(userName));
        add(showHistoryButton);

        setVisible(true);
        
        saveScore(); // Save the score when this frame is initialized
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Play Again")) {
            setVisible(false);
            new Quiz(userName); // Create a new Quiz instance with the same user name
        }
    }

    private void saveScore() {
        ScoreHistory history = new ScoreHistory(userName);
        history.addScore(userScore);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Score("User", 100)); // Example user and score
    }
}
