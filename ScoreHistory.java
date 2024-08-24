import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class ScoreHistory extends JFrame {
    private JTextArea historyArea;
    private String userName;
    
    public ScoreHistory(String userName) {
        this.userName = userName;

        setTitle("Score History");
        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Score History for " + userName, JLabel.CENTER);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(heading, BorderLayout.NORTH);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        add(new JScrollPane(historyArea), BorderLayout.CENTER);

        JButton close = new JButton("Close");
        close.addActionListener(e -> setVisible(false));
        add(close, BorderLayout.SOUTH);

        loadHistory();
        
        setVisible(true);
    }

    private void loadHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(userName + "_history.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                historyArea.append(line + "\n");
            }
        } catch (IOException e) {
            historyArea.setText("No history found for user: " + userName);
        }
    }

    public void addScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userName + "_history.txt", true))) {
            writer.write("Score: " + score);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving score: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScoreHistory("User"));
    }
}
