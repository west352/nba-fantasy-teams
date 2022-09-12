package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.exceptions.IncorrectTeamNameException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Console based NBA application.
public class NationalBasketballAssociation {
    private static final String JSON_STORE = "./data/league.json";
    private League league;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECT: runs the basketball league
    public NationalBasketballAssociation() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runLeague();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runLeague() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayLeagueMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for playing!");
        printLog(EventLog.getInstance());
    }

    // EFFECTS: display the menu options.
    public void displayLeagueMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("\tvt -> view all the teams in NBA");
        System.out.println("\tat -> add a new team in NBA");
        System.out.println("\tvp -> view all the players and their stats in a team");
        System.out.println("\tap -> add a new player in a team");
        System.out.println("\ts -> save league to file");
        System.out.println("\tl -> load league from file");
        System.out.println("q -> quit");
    }

    //MODIFIES: this
    //EFFECT: initialize the basketball league
    private void init() {
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

        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {
        if (command.equals("vt")) {
            viewTeams();
        } else if (command.equals("at")) {
            addTeam();
        } else if (command.equals("vp")) {
            try {
                viewPlayers();
            } catch (IncorrectTeamNameException e) {
                System.out.println("Please enter the correct team name from the list...");
            }
        } else if (command.equals("ap")) {
            try {
                addPlayer();
            } catch (IncorrectTeamNameException e) {
                System.out.println("Please enter the correct team name from the list...");
            }
        } else if (command.equals("s")) {
            saveLeague();
        } else if (command.equals("l")) {
            loadLeague();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: list all the names of the teams in NBA.
    public void viewTeams() {
        for (int i = 0; i < league.leagueSize(); i++) {
            System.out.println(league.getTeam(i).getTeamName());
        }
    }

    //MODIFIES: this
    //EFFECT: add a team in NBA based on the user's input team.
    public void addTeam() {
        String newTeamName;
        Team newTeam;
        int initialLeagueSize = league.leagueSize();
        System.out.println("Name of the new team or press 'r' to return:");
        newTeamName = input.next();
        newTeamName += input.nextLine();
        if (newTeamName.equals("r")) {
            return;
        }
        newTeam = new Team(newTeamName);
        league.addTeam(newTeam);
        if (league.leagueSize() == initialLeagueSize) {
            System.out.println("The team already exist in the league");
        }
    }

    //EFFECTS: list all the names of the players in NBA.
    @SuppressWarnings("methodlength")
    public void viewPlayers() throws IncorrectTeamNameException {
        Team chosenTeam = null;
        viewTeams();
        System.out.println("choose a team from the list above or press 'r' to return:");
        String teamName = input.next();
        teamName += input.nextLine();
        if (teamName.equals("r")) {
            return;
        }
        for (int i = 0; i < league.leagueSize(); i++) {
            if (teamName.equals(league.getTeam(i).getTeamName())) {
                chosenTeam = league.getTeam(i);
            }
        }

        if (chosenTeam == null) {
            throw new IncorrectTeamNameException();
        }

        if (chosenTeam.teamSize() == 0) {
            System.out.println("There is no player in the team yet...");
        }

        for (int i = 0; i < chosenTeam.teamSize(); i++) {
            System.out.println(chosenTeam.getPlayer(i).getPlayerName() + ":"
                    + "  PPG:" + chosenTeam.getPlayer(i).getPointPerGame()
                    + "  APG:" + chosenTeam.getPlayer(i).getAssistPerGame()
                    + "  RPG:" + chosenTeam.getPlayer(i).getReboundPerGame());
        }
    }


    //MODIFIES: this
    //EFFECT: add a player in the team based on the user's input team.
    @SuppressWarnings("methodlength")
    public void addPlayer() throws IncorrectTeamNameException {
        Team chosenTeam = null;
        viewTeams();
        System.out.println("choose a team from the list above or press 'r' to return:");
        String teamName = input.next();
        teamName += input.nextLine();
        if (teamName.equals("r")) {
            return;
        }
        for (int i = 0; i < league.leagueSize(); i++) {
            if (teamName.equals(league.getTeam(i).getTeamName())) {
                chosenTeam = league.getTeam(i);
            }
        }
        if (chosenTeam == null) {
            throw new IncorrectTeamNameException();
        }
        System.out.println("Name of the new player:");
        String newPlayerName = input.next();
        newPlayerName += input.nextLine();
        System.out.println("Point per game of the new player:");
        double newPlayerPointPerGame = input.nextDouble();
        System.out.println("Assist per game of the new player:");
        double newPlayerAssistPerGame = input.nextDouble();
        System.out.println("Rebound per game of the new player");
        double newPlayerReboundPerGame = input.nextDouble();
        Player newPlayer = new Player(newPlayerName, newPlayerPointPerGame,
                newPlayerAssistPerGame, newPlayerReboundPerGame);
        chosenTeam.addPlayer(newPlayer);
    }

    // EFFECTS: saves the league to file
    private void saveLeague() {
        try {
            jsonWriter.open();
            jsonWriter.write(league);
            jsonWriter.close();
            System.out.println("Saved " + league.getLeagueName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads league from file
    private void loadLeague() {
        try {
            league = jsonReader.read();
            System.out.println("Loaded " + league.getLeagueName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: print out the EventLog that records all the changes that have been made inside the league on the console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

}
