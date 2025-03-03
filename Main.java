import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class MoviesManagerGUI {

    private JFrame frame;
    private DefaultListModel<String> movieListModel;
    private JList<String> movieList;
    private JTextField movieInput;

    public MoviesManagerGUI() {
        // Initialize the GUI components
        frame = new JFrame("Movie Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 300);
        frame.setLayout(new BorderLayout());

        movieListModel = new DefaultListModel<>();
        movieList = new JList<>(movieListModel);
        JScrollPane scrollPane = new JScrollPane(movieList);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        movieInput = new JTextField(20);
        JButton addButton = new JButton("Add Movie");
        JButton removeButton = new JButton("Remove Selected");
        JButton sortButton = new JButton("Sort Alphabetically");
        JButton saveButton = new JButton("Save to File");

        controlPanel.add(movieInput);
        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(sortButton);
        controlPanel.add(saveButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Add button functionality
        addButton.addActionListener(e -> addMovie());
        removeButton.addActionListener(e -> removeSelectedMovie());
        sortButton.addActionListener(e -> sortMovies());
        saveButton.addActionListener(e -> saveToFile());

        frame.setVisible(true);
    }

    private void addMovie() {
        String movieName = movieInput.getText().trim();
        if (!movieName.isEmpty()) {
            movieListModel.addElement(movieName);
            movieInput.setText("");
            JOptionPane.showMessageDialog(frame, "\"" + movieName + "\" has been added.", "Movie Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a movie name.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeSelectedMovie() {
        int selectedIndex = movieList.getSelectedIndex();
        if (selectedIndex != -1) {
            String removedMovie = movieListModel.getElementAt(selectedIndex);
            movieListModel.remove(selectedIndex);
            JOptionPane.showMessageDialog(frame, "\"" + removedMovie + "\" has been removed.", "Movie Removed", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a movie to remove.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortMovies() {
        ArrayList<String> movies = Collections.list(movieListModel.elements());
        Collections.sort(movies);
        movieListModel.clear();
        for (String movie : movies) {
            movieListModel.addElement(movie);
        }
        JOptionPane.showMessageDialog(frame, "Movies have been sorted alphabetically.", "Sorted", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Movie List");
        int userSelection = fileChooser.showSaveDialog(frame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            // Check if the file already exists
            if (fileToSave.exists()) {
                int overwriteChoice = JOptionPane.showConfirmDialog(
                    frame,
                    "The file \"" + fileToSave.getName() + "\" already exists. Do you want to overwrite it?",
                    "File Already Exists",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (overwriteChoice != JOptionPane.YES_OPTION) {
                    return; // Cancel saving
                }
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                for (int i = 0; i < movieListModel.size(); i++) {
                    writer.write(movieListModel.getElementAt(i));
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(frame, "Movie list saved to file: " + fileToSave.getAbsolutePath(), "File Saved", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving to file: " + ex.getMessage(), "File Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MoviesManagerGUI::new);
    }
}
