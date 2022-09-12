package ui;

import model.League;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Represent the team window for the graphical user interface of the NBA application.
public class TeamView extends JFrame implements ActionListener {
    private static final int BUTTON_POSITION = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 40;
    private League league;
    private PlayerView playerView;


    // EFFECTS: set up the team menu of the GUI.
    public TeamView(League league) {
        this.league = league;
        this.setWindow();
        this.setBackgroundImage();
        this.setUpLabelsAndButtons(league);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // EFFECTS: set up the team view window of the GUI.
    private void setWindow() {
        setPreferredSize(new Dimension(1000, 800));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    // EFFECTS: set up the background image for the team view window.
    private void setBackgroundImage() {
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("./data/nba.png"));
            setContentPane(new BackgroundImage(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: set up labels and buttons for the team view window.
    private void setUpLabelsAndButtons(League league) {
        JLabel selectOptionLabel = new JLabel("Please select a team: ", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 40);
        add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);

        for (int i = 0; i < league.leagueSize(); i++) {
            setUpButton(league, i);
        }
    }

    // EFFECTS: set up buttons for the team view window.
    private void setUpButton(League league, int i) {
        JButton button = new JButton(league.getTeam(i).getTeamName());
        button.setBounds(BUTTON_POSITION, (40 + i * 40), BUTTON_WIDTH, BUTTON_HEIGHT);
        add(button);
        button.setActionCommand(league.getTeam(i).getTeamName());
        button.addActionListener(this);
        button.setForeground(Color.black);
    }

    @Override
    // EFFECTS: process the action input from the user.
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        for (int i = 0; i < league.leagueSize(); i++) {
            if (action.equals(league.getTeam(i).getTeamName())) {
                playerView = new PlayerView(league.getTeam(i));
            }
        }
    }
}