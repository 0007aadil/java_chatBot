package com.example.chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatbotUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton, clearButton, saveButton;
    private JPanel sidebarPanel;
    private List<String> chatHistory;
    private ChatHistoryDAO chatHistoryDAO;

    public ChatbotUI() {
        // Initialize chat history list and DAO for database operations
        chatHistory = new ArrayList<>();
        chatHistoryDAO = new ChatHistoryDAO();

        // Set up the main frame
        setTitle("Adorable Chatbot UI");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar for conversation history
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(220, getHeight()));
        sidebarPanel.setBackground(new Color(240, 248, 255));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel previousConversationLabel = new JLabel("Conversation History 🗓️");
        previousConversationLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        previousConversationLabel.setForeground(new Color(70, 70, 70));
        sidebarPanel.add(previousConversationLabel);

        add(sidebarPanel, BorderLayout.WEST);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        chatArea.setBackground(new Color(245, 245, 250));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Input field and buttons
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 250));

        inputField = new JTextField();
        inputField.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        inputPanel.add(inputField, BorderLayout.CENTER);

        sendButton = createStyledButton("Send", new Color(255, 160, 122));
        clearButton = createStyledButton("Clear History", new Color(255, 105, 180));
        saveButton = createStyledButton("Save History", new Color(135, 206, 250));
        
        sendButton.setPreferredSize(new Dimension(90, 40));
        clearButton.setPreferredSize(new Dimension(120, 40));
        saveButton.setPreferredSize(new Dimension(120, 40));

        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.add(clearButton, BorderLayout.WEST);
        inputPanel.add(saveButton, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event handling
        sendButton.addActionListener(e -> handleSendMessage());
        clearButton.addActionListener(e -> clearChatHistory());
        saveButton.addActionListener(e -> saveChatHistoryToDatabase());

        inputField.addActionListener(e -> handleSendMessage()); // Send on Enter key

        // Initial greeting message
        chatArea.append("Chatbot: Hello! I'm your adorable assistant! 😊\nHow can I help you today?\n");
    }

    private void handleSendMessage() {
        String userInput = inputField.getText().trim();
        if (!userInput.isEmpty()) {
            chatArea.append("You: " + userInput + "\n");
            String botResponse = generateResponse(userInput);
            chatArea.append("Chatbot: " + botResponse + "\n");

            // Add message to chat history and update sidebar
            addToChatHistory(userInput);

            inputField.setText("");
        }
    }

    private String generateResponse(String userInput) {
        // Greeting responses
        if (userInput.equalsIgnoreCase("hi") || userInput.equalsIgnoreCase("hello") || 
            userInput.equalsIgnoreCase("hey") || userInput.equalsIgnoreCase("how are you")) {
            return "Hello! 😊 How can I assist you today?";
        } else if (userInput.equalsIgnoreCase("good morning")) {
            return "Good morning! ☀️ How can I brighten your day?";
        } else if (userInput.equalsIgnoreCase("good afternoon")) {
            return "Good afternoon! 🌼 What can I do for you?";
        } else if (userInput.equalsIgnoreCase("good evening")) {
            return "Good evening! 🌙 How's your day going?";

        // Date response
        } else if (userInput.equalsIgnoreCase("date")) {
            return "Today's date is " + LocalDate.now() + ".";
        
        // Joke response
        } else if (userInput.equalsIgnoreCase("tell me a joke") || userInput.equalsIgnoreCase("joke")) {
            return getRandomJoke();
        
        // Weather response
        } else if (userInput.equalsIgnoreCase("weather")) {
            return "I can't fetch the weather right now, but it's always sunny in here! ☀️";
        
        // Mathematical operations
        } else if (userInput.matches("\\d+\\s*\\+\\s*\\d+")) {
            return "Result: " + calculateSum(userInput);
        } else if (userInput.matches("\\d+\\s*-\\s*\\d+")) {
            return "Result: " + calculateDifference(userInput);
        } else if (userInput.matches("\\d+\\s*\\*\\s*\\d+")) {
            return "Result: " + calculateProduct(userInput);
        } else if (userInput.matches("\\d+\\s*/\\s*\\d+")) {
            return "Result: " + calculateQuotient(userInput);
        }

        return "I'm here to help! 😊";
    }

    private String calculateSum(String input) {
        String[] numbers = input.split("\\+");
        int sum = Integer.parseInt(numbers[0].trim()) + Integer.parseInt(numbers[1].trim());
        return String.valueOf(sum);
    }

    private String calculateDifference(String input) {
        String[] numbers = input.split("-");
        int difference = Integer.parseInt(numbers[0].trim()) - Integer.parseInt(numbers[1].trim());
        return String.valueOf(difference);
    }

    private String calculateProduct(String input) {
        String[] numbers = input.split("\\*");
        int product = Integer.parseInt(numbers[0].trim()) * Integer.parseInt(numbers[1].trim());
        return String.valueOf(product);
    }

    private String calculateQuotient(String input) {
        String[] numbers = input.split("/");
        int divisor = Integer.parseInt(numbers[1].trim());
        if (divisor == 0) {
            return "Cannot divide by zero!";
        }
        int quotient = Integer.parseInt(numbers[0].trim()) / divisor;
        return String.valueOf(quotient);
    }

    private String getRandomJoke() {
        String[] jokes = {
            "Why don't scientists trust atoms? Because they make up everything!",
            "Why did the math book look sad? Because it had too many problems!",
            "What do you call fake spaghetti? An impasta!"
        };
        Random rand = new Random();
        return jokes[rand.nextInt(jokes.length)];
    }

    private void createSidebar() {
        sidebarPanel.removeAll();
        JLabel previousConversationLabel = new JLabel("Conversation History 🗓️");
        previousConversationLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        previousConversationLabel.setForeground(new Color(70, 70, 70));
        sidebarPanel.add(previousConversationLabel);

        // Show only the last 7 conversations
        int start = Math.max(0, chatHistory.size() - 7);
        for (int i = start; i < chatHistory.size(); i++) {
            JLabel label = new JLabel("• " + chatHistory.get(i));
            label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
            label.setForeground(new Color(60, 60, 60));
            sidebarPanel.add(label);
        }

        // Refresh the sidebar
        sidebarPanel.revalidate();
        sidebarPanel.repaint();
    }

    private void addToChatHistory(String message) {
        chatHistory.add(message);
        createSidebar();
    }

    private void clearChatHistory() {
        chatHistory.clear();
        chatArea.setText("");
        createSidebar();
    }

    private void saveChatHistoryToDatabase() {
        for (String entry : chatHistory) {
            String userInput = entry.startsWith("You: ") ? entry.substring(5) : entry;
            String botResponse = chatArea.getText().contains("Chatbot: ") ?
                    chatArea.getText().substring(chatArea.getText().indexOf("Chatbot: ") + 9).split("\n")[0] : "";
            chatHistoryDAO.saveChatEntry(userInput, botResponse);
        }
        JOptionPane.showMessageDialog(this, "Chat history saved to database!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatbotUI chatbotUI = new ChatbotUI();
            chatbotUI.setVisible(true);
        });
    }
}
