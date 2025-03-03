Movie Manager
This project is a simple GUI application for managing a list of movies. It allows users to add, remove, sort, and save their movie list to a file.

Features
Add movies to the list.
Remove selected movies.
Sort movies alphabetically.
Save the movie list to a file.
Requirements
Java Development Kit (JDK) 8 or above.
Usage
Clone the repository:


git clone https://github.com/Darkangel-18/List_Manager_Java.git
cd List_Manager_Java
Compile the code:


javac Main.java
Run the application:


java Main
Code Overview
The main class in this project is MoviesManagerGUI, which sets up the Swing GUI components and handles the user interactions.

Main Components
JFrame for the main window.
DefaultListModel and JList for displaying the movie list.
JTextField for inputting movie names.
JButtons for adding, removing, sorting, and saving movies.
Key Methods
addMovie(): Adds a movie to the list.
removeSelectedMovie(): Removes the selected movie from the list.
sortMovies(): Sorts the movie list alphabetically.
saveToFile(): Saves the movie list to a file.
Event Handling
Buttons are linked to their respective methods using ActionListener:

Java
addButton.addActionListener(e -> addMovie());
removeButton.addActionListener(e -> removeSelectedMovie());
sortButton.addActionListener(e -> sortMovies());
saveButton.addActionListener(e -> saveToFile());
License
This project is licensed under the MIT License.

Feel free to contribute to this project by submitting issues or pull requests!

You can find the source code here.
