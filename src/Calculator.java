import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Calculator {
    private JFrame frame;
    private JPanel buttonPanel;
    private JTextArea textScreen;
    private CalcButton[] numbers = new CalcButton[10];
    private CalcButton[] operations = new CalcButton[4];
    private CalcButton[] mod = new CalcButton[3];
    private CalcButton[] misc = new CalcButton[3];
    private double prev, val;
    private String op;

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.initialize();
    }

    private void initialize() {
        prev = 0;
        val = 0;
        op = null;
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textScreen = new JTextArea(1, 1);
        textScreen.setPreferredSize(new Dimension(300, 100));
        textScreen.setLineWrap(true);
        textScreen.setEditable(false);
        textScreen.setFont(new Font("Arial", Font.PLAIN, 30));

        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(300, 400));
        buttonPanel.setLayout(new GridLayout(5, 4));

        createButtons();
        setActionListeners();
        setButtonPanel();

        frame.getContentPane().add(textScreen, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private void setActionListeners() {
        for (JButton button : numbers) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    val = val * 10 + Double.valueOf(button.getText());
                    if (val == (long) val)
                        textScreen.setText(String.format("%d", (long) val));
                    else
                        textScreen.setText(String.format("%f", val));
                }
            });
        }

        for (JButton button : operations) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (op == null)
                        prev = evaluate(prev, val, "+");
                    else
                        prev = evaluate(prev, val, op);
                    val = 0;
                    op = button.getText();
                }
            });
        }

        //Equal Button
        mod[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (op == null)
                    prev = val;
                else
                    prev = evaluate(prev, val, op);
                val = 0;
                op = null;
                if (prev == (long) prev)
                    textScreen.setText(String.format("%d", (long) prev));
                else
                    textScreen.setText(String.format("%f", prev));
            }
        });
    }

    private double evaluate(double prev, double val, String op) {
        if (op.equals("+")) {
            return prev + val;
        } else if (op.equals("-")) {
            return prev - val;
        } else if (op.equals("\u00D7")) {
            return prev * val;
        } else {
            return prev / val;
        }
    }

    private void createButtons() {
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = new CalcButton(Integer.toString(i));

        operations[0] = new CalcButton("+");
        operations[1] = new CalcButton("-");
        operations[2] = new CalcButton("\u00D7");
        operations[3] = new CalcButton("\u00F7");

        mod[0] = new CalcButton("\u00B1");
        mod[1] = new CalcButton(".");
        mod[2] = new CalcButton("=");

        misc[0] = new CalcButton("C");
        misc[1] = new CalcButton("CE");
        misc[2] = new CalcButton("\u2190");
    }

    private void setButtonPanel() {
        buttonPanel.add(misc[0]);
        buttonPanel.add(misc[1]);
        buttonPanel.add(misc[2]);
        buttonPanel.add(operations[3]);

        for (int i = 7; i <= 9; i++)
            buttonPanel.add(numbers[i]);
        buttonPanel.add(operations[2]);

        for (int i = 4; i <= 6; i++)
            buttonPanel.add(numbers[i]);
        buttonPanel.add(operations[1]);

        for (int i = 1; i <= 3; i++)
            buttonPanel.add(numbers[i]);
        buttonPanel.add(operations[0]);

        buttonPanel.add(mod[0]);
        buttonPanel.add(numbers[0]);
        buttonPanel.add(mod[1]);
        buttonPanel.add(mod[2]);
    }

}
