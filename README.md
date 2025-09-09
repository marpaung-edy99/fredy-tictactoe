Tic-Tac-Toe Game with Spring Boot 

This project is a simple implementation of a Tic-Tac-Toe game using Spring Boot. The application allows players to start a new game with a customizable board size, play, and detects a winner or a draw.

Key Features

Dynamic Game Board: The board size is customizable (e.g., 3x3, 4x4, etc.).

Game Logic: Manages player turns and detects winning conditions (horizontal, vertical, diagonal) or a draw.

Data Persistence: Uses Spring Data JPA and H2 Database to save game states.

User Interface: Built with Thymeleaf, providing a simple web interface for playing.

Technologies Used

Spring Boot: The main framework for building the application.

Spring Data JPA: For data access and persistence.

H2 Database: A lightweight, in-memory database for development.

Thymeleaf: The template engine for dynamic web pages.

Lombok: A library that reduces boilerplate code (such as getters, setters, and constructors).

Project Structure

src/main/java/project/fredy/tictactoe/TictactoeApplication.java: The main application class that starts the Spring Boot server.

src/main/java/project/fredy/tictactoe/model/entity/Game.java: The entity that maps the game state to the game table in the database.

src/main/java/project/fredy/tictactoe/repository/GameRepository.java: The data layer that provides methods for interacting with the database.

src/main/java/project/fredy/tictactoe/service/GameService.java: The business logic layer containing the game rules and flow.

src/main/java/project/fredy/tictactoe/controller/GameController.java: Manages web requests and serves HTML pages.

src/main/resources/templates/: Contains the Thymeleaf HTML files (home.html and game.html).

How to Run the Application

Make sure you have Java Development Kit (JDK) 17 or a newer version installed.

Open the project in IntelliJ IDEA.

Click the "Reload All Maven Projects" icon in the Maven panel to download all dependencies.

Run the TictactoeApplication.java class by clicking the green "Run" button next to it.

Open your browser and navigate to http://localhost:8080.