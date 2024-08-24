import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingWorkerExample extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SwingWorker Example");
            JButton button = new JButton("Start Task");
            JLabel label = new JLabel("Task not started");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setEnabled(false);
                    label.setText("Task started");

                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            // Simulate long-running task
                            Thread.sleep(3000);
                            return null;
                        }

                        @Override
                        protected void done() {
                            label.setText("Task completed");
                            button.setEnabled(true);
                        }
                    };

                    worker.execute();
                }
            });

            frame.setLayout(new FlowLayout());
            frame.add(button);
            frame.add(label);
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

