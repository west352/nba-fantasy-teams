package ui;

import model.Player;
import model.Team;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represent a team's players window for the graphical user interface of the NBA application.
public class PlayerView extends JFrame implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable table;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 40;
    private Team team;
    private static final String ADD_PLAYER_ACTION = "ADD_PLAYER_ACTION";
    private static final String RESET_PLAYER_ACTION = "RESET_PLAYER_ACTION";
    private AddPlayerView addPlayerView;
    private ResetPlayerView resetPlayerView;


    // EFFECTS: set up a players table of a team and the player view window of the GUI.
    public PlayerView(Team team) {
        this.team = team;
        final String[] columnLabels = new String[]{
                "Player Name",
                "PPG",
                "APG",
                "RPG"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {
        };
        table = new JTable(tableModel);
        this.populateTableRows();

        add(new JScrollPane(table));
        this.setButtons();
        setTitle(team.getTeamName());
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: construct a table for all the player in a team.
    private void populateTableRows() {
        for (int i = 0; i < team.teamSize(); i++) {
            Player player = team.getPlayer(i);
            Object[] tableRow = new Object[]{
                    player.getPlayerName(),
                    player.getPointPerGame(),
                    player.getAssistPerGame(),
                    player.getReboundPerGame()
            };
            tableModel.addRow(tableRow);
        }
    }

    //EFFECT: set up an add player button in the player view window.
    private void setButtons() {
        JButton addPlayerButton = new JButton(("Add a new player"));
        addPlayerButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        add(addPlayerButton);
        addPlayerButton.setActionCommand(ADD_PLAYER_ACTION);
        addPlayerButton.addActionListener(this);
        addPlayerButton.setForeground(Color.darkGray);

        JButton resetPlayerButton = new JButton(("reset a player"));
        resetPlayerButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        add(resetPlayerButton);
        resetPlayerButton.setActionCommand(RESET_PLAYER_ACTION);
        resetPlayerButton.addActionListener(this);
        resetPlayerButton.setForeground(Color.darkGray);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: process the action input of the user.
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_PLAYER_ACTION)) {
            addPlayerView = new AddPlayerView(this, team);
        } else if (action.equals(RESET_PLAYER_ACTION)) {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(null, "Please select a player in the table.");
                return;
            }
            resetPlayerView = new ResetPlayerView(this, team, selectedRowIndex);
        }
    }
}


