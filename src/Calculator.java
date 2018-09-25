import javax.swing.*;
import java.awt.*;

public class Calculator {
    private JFrame frame;
    private JPanel buttonPanel;
    private JTextArea textScreen;

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.initialize();
    }

    private void initialize(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textScreen = new JTextArea(1,1);
        textScreen.setPreferredSize(new Dimension(300,200));
        textScreen.setLineWrap(true);
        textScreen.setEditable(false);

        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(300,400));
        buttonPanel.setLayout(new GridLayout(4,3));

        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton buttonPlusMin = new JButton("+-");
        JButton buttonDot = new JButton(".");

        buttonPanel.add(button7); buttonPanel.add(button8); buttonPanel.add(button9);
        buttonPanel.add(button4); buttonPanel.add(button5); buttonPanel.add(button6);
        buttonPanel.add(button1); buttonPanel.add(button2); buttonPanel.add(button3);
        buttonPanel.add(buttonPlusMin); buttonPanel.add(button0); buttonPanel.add(buttonDot);

        frame.getContentPane().add(textScreen, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

}
