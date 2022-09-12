package ui;

import model.Event;
import model.EventLog;
import model.League;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


// Represents the main window for the graphical user interface of the NBA application.
public class MainView extends JFrame implements ActionListener {
    private static final int BUTTON_POSITION = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 40;
    private static final String JSON_STORE = "./data/league.json";
    private static final String ADD_OR_RESET_PLAYER_ACTION = "ADD_OR_RESET_PLAYER_ACTION";
    private static final String LOAD_LEAGUE_ACTION = "LOAD_LEAGUE_ACTION";
    private static final String SAVE_LEAGUE_ACTION = "SAVE_LEAGUE_ACTION";
    private static final String QUIT_APP_ACTION = "QUIT_APP_ACTION";
    private League league = new League();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private TeamView teamView;


    // EFFECTS: set up the main menu of the GUI.
    public MainView() {
        super("National Basketball Association");
        this.setWindow();
        this.setBackgroundImage();
        initLeague();
        this.setUpLabelsAndButtons();
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // EFFECTS: set up the main window for the GUI.
    private void setWindow() {
        setPreferredSize(new Dimension(1000, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    // EFFECTS: set up the background image for the main window.
    private void setBackgroundImage() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("./data/nba.png"));
            setContentPane(new BackgroundImage(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECT: initialize the basketball league
    private void initLeague() {
        Player lebron = new Player("Lebron James", 25.5, 8.7, 8.5);
        Player anthony = new Player("Anthony Davis", 28.3, 5.0, 3.5);
        Player russell = new Player("Russell Westbrook", 23.0, 12.2, 10.5);
        Player giannis = new Player("Giannis Antetokounmpo",
                30.5, 5.4, 12.5);
        Player kris = new Player("Kris Middleton", 23.5, 6.1, 7.3);
        Player holiday = new Player("True Holiday", 20.0, 7.8, 6.5);

        Team lakers = new Team("Los Angles Lakers");
        Team bucks = new Team("Milwaukee Bucks");

        lakers.addPlayer(lebron);
        lakers.addPlayer(anthony);
        lakers.addPlayer(russell);

        bucks.addPlayer(giannis);
        bucks.addPlayer(kris);
        bucks.addPlayer(holiday);

        league = new League();
        league.addTeam(lakers);
        league.addTeam(bucks);
    }

    // EFFECTS: set up labels and buttons for the main window.
    private void setUpLabelsAndButtons() {
        JLabel selectOptionLabel = new JLabel("Please select an option: ", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 40);
        add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);

        setUpButton("Add or reset a player in a team", ADD_OR_RESET_PLAYER_ACTION, 40);
        setUpButton("Load league from file", LOAD_LEAGUE_ACTION, 80);
        setUpButton("Save league to file", SAVE_LEAGUE_ACTION, 120);
        setUpButton("Quit NBA Application", QUIT_APP_ACTION, 240);
    }

    // EFFECTS: set up buttons for the main window.
    private void setUpButton(String buttonName, String action, int y) {
        JButton button = new JButton(buttonName);
        button.setBounds(BUTTON_POSITION, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(button);
        button.setActionCommand(action);
        button.addActionListener(this);
        button.setForeground(Color.black);
    }

    @Override
    // EFFECTS: process user's action input
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_OR_RESET_PLAYER_ACTION)) {
            teamView = new TeamView(league);
        } else if (action.equals(LOAD_LEAGUE_ACTION)) {
            loadLeague();
        } else if (action.equals(SAVE_LEAGUE_ACTION)) {
            saveLeague();
        } else if (action.equals(QUIT_APP_ACTION)) {
            if (teamView != null) {
                teamView.dispose();
            }
            printLog(EventLog.getInstance());
            dispose();
        }
    }

    // EFFECTS: print out the EventLog that records all the changes that have been made inside the league on the console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads league from file
    private void loadLeague() {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            league = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded " + league.getLeagueName() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: save the league to file.
    private void saveLeague() {
        try {
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(league);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Saved " + league.getLeagueName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_STORE);
        }
    }
}
