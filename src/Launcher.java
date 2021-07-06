import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;

public class Launcher implements ActionListener{

    JFrame frame = new JFrame();
    JButton startButton = new JButton("Hi JoEll");


    Launcher(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(startButton);

        startButton.setBounds(100, 160, 200, 40);
        startButton.setFocusable(false);
        startButton.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        
    }
}
