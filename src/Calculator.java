import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Calculator {
    private JPanel buttonPanel;
    private JTextField textScreen;
    private CalcButton[] numbers = new CalcButton[10];
    private CalcButton[] operations = new CalcButton[4];
    private CalcButton[] mod = new CalcButton[3];
    private CalcButton[] misc = new CalcButton[3];
    private double prev, val;
    private String num;
    private String op;
    private int dp; //Decimal places
    private boolean positive;

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.initialize();
    }

    private void initialize() {
        JFrame frame;
        prev = 0;
        val = 0;
        dp = -1;
        num = "0";
        op = null;
        positive = true;
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textScreen = new JTextField();
        textScreen.setPreferredSize(new Dimension(300, 100));
        textScreen.setEditable(false);
        textScreen.setFont(new Font("Arial", Font.PLAIN, 40));
        textScreen.setText(num);
        textScreen.setHorizontalAlignment(SwingConstants.RIGHT);

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
                    if (num.equals("0"))
                        num = button.getText();
                    else
                        num += button.getText();

                    val = Double.valueOf(num);
                    textScreen.setText(num);
                    if(dp >= 0)
                        dp++;
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
                    num = "";
                    val = 0;
                    op = button.getText();
                }
            });
        }

        //Plus-Minus Button
        mod[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(val != 0) {
                    if (positive) {
                        num = "-" + num;
                        val = 0 - val;
                        textScreen.setText(num);
                    } else {
                        num = num.substring(1);
                        val = 0 - val;
                        textScreen.setText(num);
                    }
                    positive = !positive;
                }
            }
        });

        //Dot Button
        mod[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dp < 0){
                    num += ".";
                    dp = 0;
                }
            }
        });

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

        //Clear Button
        misc[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prev = 0;
                val = 0;
                dp = -1;
                num = "0";
                op = null;
                textScreen.setText(num);
            }
        });

        //ClearEntry Button
        misc[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num = "0";
                val = 0;
                dp = -1;
                textScreen.setText(num);
            }
        });

        //Delete Button
        misc[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num = num.substring(0, num.length() - 1);

                if(dp > 0)
                    dp--;
                if(dp==0)
                    num = num.substring(0, num.length() - 1);

                val = Double.valueOf(num);
                textScreen.setText(num);

            }
        });
    }

    private double evaluate(double prev, double val, String op) {
        switch(op){
            case "+":
                return prev + val;
            case "-":
                return prev - val;
            case "\u00D7":
                return prev * val;
            case "\u00F7":
                return prev / val;
            default:
                return 0;
        }
    }

    private void createButtons() {
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = new CalcButton(Integer.toString(i));

        operations[0] = new CalcButton("+");
        operations[1] = new CalcButton("-");
        operations[2] = new CalcButton("\u00D7"); //Multiply
        operations[3] = new CalcButton("\u00F7"); //Division

        mod[0] = new CalcButton("\u00B1"); //Plus-minus
        mod[1] = new CalcButton(".");
        mod[2] = new CalcButton("=");

        misc[0] = new CalcButton("C");
        misc[1] = new CalcButton("CE");
        misc[2] = new CalcButton("\u2190"); //Delete
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
