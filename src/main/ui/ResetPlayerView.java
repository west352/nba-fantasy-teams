package ui;

import model.Player;
import model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a reset player window for the user to reset an existing player in the selected team.
public class ResetPlayerView extends JFrame implements ActionListener {
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 40;
    private JTextField playerNameField;
    private JTextField playerPointPerGameField;
    private JTextField playerAssistPerGameField;
    private JTextField playerReboundPerGameField;
    private PlayerView playerView;
    private Team team;
    private int playerIndex;
    private static final String FINISH_ACTION = "FINISH_ACTION";


    // EFFECTS: set up a reset player window for the user to reset an existing player in the team.
    public ResetPlayerView(PlayerView playerView, Team team, int playerIndex) {
        super("Reset a player");
        this.playerView = playerView;
        this.team = team;
        this.playerIndex = playerIndex;
        this.setWindow();

        this.setLabelsFieldsButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // EFFECTS: construct a window to reset players.
    private void setWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    // EFFECTS: set up the instruction labels for resetting an existing player, input fields, and the finish button
    //          once all the data of the existed player has been updated.
    private void setLabelsFieldsButtons() {
        setLabel("Reset name of the player", 50, 40, 800, 20);
        playerNameField = new JTextField(30);
        playerNameField.setBounds(50, 70, 300, 20);
        add(playerNameField);

        setLabel("Reset Point Per Game of the player", 50, 100, 1000, 20);
        playerPointPerGameField = new JTextField(30);
        playerPointPerGameField.setBounds(50, 130,300,20);
        add(playerPointPerGameField);

        setLabel("Reset Assist Per Game of the player", 50, 160, 1000, 20);
        playerAssistPerGameField = new JTextField(30);
        playerAssistPerGameField.setBounds(50, 190, 300, 20);
        add(playerAssistPerGameField);

        setLabel("Reset Rebound Per Game of the player", 50, 220, 1000, 20);
        playerReboundPerGameField = new JTextField(30);
        playerReboundPerGameField.setBounds(50, 250, 300, 20);
        add(playerReboundPerGameField);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(310,310,BUTTON_WIDTH,BUTTON_HEIGHT);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);
    }

    // EFFECTS: set up the instruction labels for updating an existing player.
    private void setLabel(String labelName, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelName);
        label.setBounds(x, y, width, height);
        add(label);
        label.setForeground(Color.darkGray);
    }

    @Override
    // REQUIRES: the user has to input all the updated information of the existing player correctly in the fields
    //           before clicking the finish button.
    // MODIFIES: this, PlayerView.
    // EFFECTS: updating an existing player's information in the player view window
    //          after the user clicked the finish button.
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(FINISH_ACTION)) {
            String playerName = playerNameField.getText();
            String pointPerGame = playerPointPerGameField.getText();
            String assistPerGame = playerAssistPerGameField.getText();
            String reboundPerGame = playerReboundPerGameField.getText();
            team.getPlayer(playerIndex).resetName(playerName);
            team.getPlayer(playerIndex).resetPointPerGame(Double.parseDouble(pointPerGame));
            team.getPlayer(playerIndex).resetAssistPerGame(Double.parseDouble(assistPerGame));
            team.getPlayer(playerIndex).resetReboundPerGame(Double.parseDouble(reboundPerGame));
            playerView.dispose();
            new PlayerView(team);
            dispose();
        }
    }
}

