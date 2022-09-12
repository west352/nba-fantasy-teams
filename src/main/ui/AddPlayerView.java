package ui;

import model.Player;
import model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Represents an add player window for the user to add a new player in the selected team.
public class AddPlayerView extends JFrame implements ActionListener {
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 40;
    private JTextField playerNameField;
    private JTextField playerPointPerGameField;
    private JTextField playerAssistPerGameField;
    private JTextField playerReboundPerGameField;
    private PlayerView playerView;
    private Team team;
    private static final String FINISH_ACTION = "FINISH_ACTION";


    // EFFECTS: set up an add player window for the user to add a new player in the team.
    public AddPlayerView(PlayerView playerView, Team team) {
        super("Add a player");
        this.playerView = playerView;
        this.team = team;
        this.setWindow();

        this.setLabelsFieldsButtons();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // EFFECTS: construct a window to add players.
    private void setWindow() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

    // EFFECTS: set up the instruction labels for adding a new player, input fields and the finish button once all the
    //          data of the new player has been recorded.
    private void setLabelsFieldsButtons() {
        setLabel("Name of the new player", 50, 40, 800, 20);
        playerNameField = new JTextField(30);
        playerNameField.setBounds(50, 70, 300, 20);
        add(playerNameField);

        setLabel("Point Per Game of the new player", 50, 100, 1000, 20);
        playerPointPerGameField = new JTextField(30);
        playerPointPerGameField.setBounds(50, 130, 300, 20);
        add(playerPointPerGameField);

        setLabel("Assist Per Game of the new player", 50, 160, 1000, 20);
        playerAssistPerGameField = new JTextField(30);
        playerAssistPerGameField.setBounds(50, 190, 300, 20);
        add(playerAssistPerGameField);

        setLabel("Rebound Per Game of the new player", 50, 220, 1000, 20);
        playerReboundPerGameField = new JTextField(30);
        playerReboundPerGameField.setBounds(50, 250, 300, 20);
        add(playerReboundPerGameField);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(310, 310, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(finishButton);
        finishButton.setActionCommand(FINISH_ACTION);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);
    }

    // EFFECTS: set up the instruction labels for adding a new player.
    private void setLabel(String labelName, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelName);
        label.setBounds(x, y, width, height);
        add(label);
        label.setForeground(Color.darkGray);
    }

    @Override
    // REQUIRES: the user has to input all the information of the new player correctly in the fields.
    // MODIFIES: this, PlayerView.
    // EFFECTS: adding a new player to the player view window after the user clicked the finish button.
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(FINISH_ACTION)) {
            String playerName = playerNameField.getText();
            String pointPerGame = playerPointPerGameField.getText();
            String assistPerGame = playerAssistPerGameField.getText();
            String reboundPerGame = playerReboundPerGameField.getText();
            team.addPlayer(new Player(playerName, Double.parseDouble(pointPerGame), Double.parseDouble(assistPerGame),
                    Double.parseDouble(reboundPerGame)));
            playerView.dispose();
            new PlayerView(team);
            dispose();
        }
    }
}

