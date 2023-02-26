import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.Stack;

public class Calculator extends JFrame
{
    JButton[] digits = {
            new JButton(" 0 "),
            new JButton(" 1 "),
            new JButton(" 2 "),
            new JButton(" 3 "),
            new JButton(" 4 "),
            new JButton(" 5 "),
            new JButton(" 6 "),
            new JButton(" 7 "),
            new JButton(" 8 "),
            new JButton(" 9 ")
    };

    JButton[] operators = {
            new JButton(" + "),
            new JButton(" - "),
            new JButton(" * "),
            new JButton(" / "),
            new JButton(" = "),
            new JButton(" C "),
            new JButton(" ( "),
            new JButton(" ) ")
    };

    String[] oper_values = {"+", "-", "*", "/", "=", "", "(", ")"};

    //String value;
    char operator;

    JTextArea area = new JTextArea(3, 5);

    public Calculator()
    {
        add(new JScrollPane(area), BorderLayout.NORTH);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());

        for (int i = 0; i < 10; i++)
            buttonpanel.add(digits[i]);

        for (int i = 0; i < 8; i++)
            buttonpanel.add(operators[i]);

        add(buttonpanel, BorderLayout.CENTER);
        area.setForeground(Color.BLACK);
        area.setBackground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        for (int i = 0; i < 10; i++)
        {
            int finalI = i;
            digits[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent)
                {
                    area.append(Integer.toString(finalI));
                }
            });
        }

        for (int i = 0; i < 8; i++)
        {
            int finalI = i;
            operators[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent)
                {
                    if (finalI == 5)
                        area.setText("");
                    else if (finalI == 4)
                    {
                        try
                        {
                            String expr = area.getText();
                            String pol = poloneza(expr);
                            double rez = evaluatePostfix(pol);
                            area.append(" = " + rez);
                        } catch (Exception e)
                        {
                            area.setText(" !!!Probleme!!! ");
                        }
                    }
                    else
                    {
                        area.append(oper_values[finalI]);
                        operator = oper_values[finalI].charAt(0);
                    }
                }
            });
        }
    }

    public static void main(String[] args)
    {
        Calculator calculator = new Calculator();
        calculator.setSize(230, 230);
        calculator.setTitle(" Java-Calc, PP Lab1 ");
        calculator.setResizable(false);
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    static int Ordine(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    static String poloneza(String exp)
    {
        String rezultat = new String("");
        Stack<Character> stiva = new Stack<>();
        for (int i = 0; i < exp.length(); ++i)
        {
            char c = exp.charAt(i);
            if (Character.isLetterOrDigit(c))
            {
                rezultat += c;
            }
            else if (c == '(')
                stiva.push(c);
            else if (c == ')')
            {
                rezultat += ";";
                while (!stiva.isEmpty() &&
                        stiva.peek() != '(')
                    rezultat += stiva.pop();

                stiva.pop();
            }
            else
            {
                rezultat += ";";
                while (!stiva.isEmpty() && Ordine(c) <= Ordine(stiva.peek()))
                {
                    rezultat += stiva.pop();
                }
                stiva.push(c);
            }

        }
        rezultat += ";";
        while (!stiva.isEmpty())
        {
            if (stiva.peek() == '(')
                return "Expresie invalida";
            rezultat += stiva.pop();
        }
        return rezultat;
    }

    static double evaluatePostfix(String exp)
    {
        Stack<Double> stiva = new Stack<>();
        System.out.println(exp);
        double n;

        for (int i = 0; i < exp.length(); i++)
        {
            char c = exp.charAt(i);

            if (Character.isDigit(c))
            {
                n = 0;
                while (Character.isDigit(c))
                {
                    n = n * 10 + c - '0';
                    i++;
                    c = exp.charAt(i);
                }
                i--;
                stiva.push(n);
            } else if (c != ';')
            {
                double val1 = stiva.pop();
                double val2 = stiva.pop();
                switch (c)
                {
                    case '+':
                        stiva.push(val2 + val1);
                        break;

                    case '-':
                        stiva.push(val2 - val1);
                        break;

                    case '/':
                        stiva.push(val2 / val1);
                        break;

                    case '*':
                        stiva.push(val2 * val1);
                        break;
                }

            }
        }
        return stiva.pop();
    }
}