# Chatbot Project

## Overview

The **Chatbot Project** is a Java-based application featuring a graphical user interface (GUI) for a friendly and interactive chatbot. The chatbot can respond to user queries, perform basic calculations, and save conversation history to a database. The project utilizes Swing for the UI and interacts with a MySQL database to store chat history.

## Features

- User-friendly GUI built with Java Swing.
- Conversation history displayed in a sidebar.
- Responds to greetings, tells jokes, and performs simple mathematical operations.
- Stores chat history in a MySQL database.
- Buttons for sending messages, clearing history, and saving chat history.

## Technologies Used

- Java
- Swing (for GUI)
- MySQL (for database)
- JDBC (for database connectivity)



## Setup Instructions

### Prerequisites

1. **Java Development Kit (JDK)**: Ensure that JDK is installed on your system. You can download it from [Oracle's official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. **MySQL Database**: Install MySQL and set up a database for storing chat history. Make sure to create the necessary tables as defined in `ChatHistoryDAO.java`.
3. **MySQL Connector**: Place the `mysql-connector-j-9.1.0.jar` file in the `lib` folder.

### Configuration

1. Open the `DatabaseConnection.java` file and configure the database connection settings (URL, username, and password) to match your MySQL setup.

### Running the Application

1. Compile the Java files if they are not already compiled:
   ```bash
   javac -cp "lib/mysql-connector-j-9.1.0.jar" src/com/example/chatbot/*.java
Run the application:
bash
Copy code
java -cp "lib/mysql-connector-j-9.1.0.jar:src" com.example.chatbot.ChatbotUI
Alternatively, you can run the provided ChatbotProject.bat file to compile and run the application in one step (if you're on Windows).

Usage
Type your message in the input field and click the Send button or press Enter to send it.
Use the Clear History button to clear the chat area and history.
Click Save History to store the current chat history in the database.
The sidebar displays the last seven messages of the conversation history.
Contributing
Contributions are welcome! If you have suggestions for improvements or want to add features, feel free to open an issue or submit a pull request.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Acknowledgments
Thanks to the contributors of the Java libraries and frameworks used in this project.
Special thanks to OpenAI for providing tools and resources for chatbot development.
