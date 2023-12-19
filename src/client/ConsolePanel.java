package client;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class ConsolePanel extends JPanel {
    private JTextField inputField;
    private JTextPane logText;
    private Style logTextStyle;
    private StyledDocument logDocument;

    public ConsolePanel(int width, int height) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));

        inputField = new JTextField();
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.WHITE);
        inputField.setFont(new Font("Arial", Font.PLAIN, 30));
        inputField.setCaretColor(Color.YELLOW);
        logText = new JTextPane();
        logText.setFont(new Font("Arial", Font.PLAIN, 25));
        logText.setEditable(false);
        logText.setBackground(Color.BLACK);
        logText.setBorder(null);
        logDocument = logText.getStyledDocument();
        logTextStyle = logText.addStyle("Color Style", null);
        StyleConstants.setForeground(logTextStyle, Color.YELLOW);

        JScrollPane scrollPane = new JScrollPane(logText);
        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return new JButton() {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(0, 0);
                    }
                };
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return new JButton() {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(0, 0);
                    }
                };
            }
        });
        scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
        scrollPane.getVerticalScrollBar().setForeground(new Color(50, 50, 50));

        add(inputField, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

    }
    public void setInputFieldActionListener(ActionListener actionListener) {
        inputField.addActionListener(actionListener);
    }
    public void appendToLog(String logEntry) {
        try {
            if (logDocument != null) {
                logDocument.insertString(logDocument.getLength(), "[" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + logEntry + "\n", logTextStyle);
                System.out.println("[" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + logEntry);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public String getConsoleInput(){
        return inputField.getText();
    }

    public void clearInput(){
        inputField.setText("");
    }
}