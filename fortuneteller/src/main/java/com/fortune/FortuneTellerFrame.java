package com.fortune;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FortuneTellerFrame extends JFrame {
    private JTextArea fortuneArea;
    private JTextField questionField;
    private ArrayList<String> fortunes;
    private int lastFortuneIndex = -1;

    public FortuneTellerFrame() {
        // Set frame title
        super("Chris Vu's Magic 8-Ball");

        // Set the layout
        setLayout(new BorderLayout());

        // Top panel (Title, ImageIcon, and Question Input)
        JPanel topPanel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Chris Vu's Magic 8-Ball", JLabel.CENTER);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("AteBall.png"));
        titleLabel.setIcon(icon);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        topPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Text field for the user to input their question
        questionField = new JTextField("Ask your question here...", 30);
        questionField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        topPanel.add(questionField, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);

        // (JTextArea with JScrollPane)
        fortuneArea = new JTextArea(10, 30);
        fortuneArea.setEditable(false);
        fortuneArea.setFont(new Font("Serif", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(fortuneArea);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel();
        JButton fortuneButton = new JButton("Shake the 8-Ball!"); //submits questions
        JButton quitButton = new JButton("Quit"); //exits

        fortuneButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        quitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));

        bottomPanel.add(fortuneButton);
        bottomPanel.add(quitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Frame and size, centered
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width * 3 / 4, screenSize.height * 3 / 4);
        setLocationRelativeTo(null); // Center the frame

        // Initialize fortunes (8-ball responses)
        initFortunes();

        // Action listener "Shake the 8-Ball!" button
        fortuneButton.addActionListener((ActionEvent e) -> showFortune());

        // Action listener "Quit" button
        quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    }

    // Method: initialize all responses for 8 ball
    private void initFortunes() {
        fortunes = new ArrayList<>();
        fortunes.add("Yes");
        fortunes.add("No");
        fortunes.add("Ask again later");
        fortunes.add("Cannot predict now");
        fortunes.add("Definitely");
        fortunes.add("Very doubtful");
        fortunes.add("Outlook not so good");
        fortunes.add("Signs point to yes");
        fortunes.add("Without a doubt");
        fortunes.add("Better not tell you now");
        fortunes.add("My sources say no");
        fortunes.add("It is certain");
    }

    // Method: display fortune
    private void showFortune() {
        Random random = new Random();
        int newFortuneIndex;

        // random response selector
        do {
            newFortuneIndex = random.nextInt(fortunes.size());
        } while (newFortuneIndex == lastFortuneIndex);

        // question input
        String question = questionField.getText().trim();
        
        if (question.isEmpty() || question.equalsIgnoreCase("Ask your question here...")) {
            fortuneArea.append("Please ask a valid question.\n");
        } else {
            // output question and response
            String newFortune = fortunes.get(newFortuneIndex);
            fortuneArea.append("You asked: " + question + "\n");
            fortuneArea.append("8-Ball says: " + newFortune + "\n\n");

            // Update prev fortune index
            lastFortuneIndex = newFortuneIndex;

            // question clear
            questionField.setText("");
        }
    }
}
