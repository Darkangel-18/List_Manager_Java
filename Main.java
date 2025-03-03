import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class MoviesManagerGUI {

    private JFrame frame;
    private DefaultListModel<String> movieListModel;
    private JList<String> movieList;
    private JTextField movieInput;

    public MovieManagerGUI() {
        // Initialize the GUI components
        frame = new JFrame("Movie Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
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

        controlPanel.add(movieInput);
        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(sortButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Add button functionality
        addButton.addActionListener(e -> addMovie());
        removeButton.addActionListener(e -> removeSelectedMovie());
        sortButton.addActionListener(e -> sortMovies());

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieManagerGUI::new);
    }
}
