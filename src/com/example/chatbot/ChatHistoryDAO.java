// package com.example.chatbot;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

// public class ChatHistoryDAO {
//     private static final String INSERT_SQL = "INSERT INTO chat_history (user_input, bot_response) VALUES (?, ?)";

//     // Method to save chat history
//     public void saveChatHistory(String userInput, String botResponse) {
//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {
             
//             pstmt.setString(1, userInput);
//             pstmt.setString(2, botResponse);
//             pstmt.executeUpdate();
//         } catch (SQLException e) {
//             // Logging the exception instead of printing the stack trace can be better for production
//             System.err.println("Error saving chat history: " + e.getMessage());
//         }
//     }
// }






package com.example.chatbot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChatHistoryDAO {
    private static final String INSERT_SQL = "INSERT INTO chat_history (user_input, bot_response) VALUES (?, ?)";

    // Method to save chat history
    public void saveChatEntry(String userInput, String botResponse) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {
             
            pstmt.setString(1, userInput);
            pstmt.setString(2, botResponse);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving chat history: " + e.getMessage());
        }
    }
}
