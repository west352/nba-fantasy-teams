package model;

import org.json.JSONObject;

//Represents a player having his name, point per game, assist per game, and rebound per game.
public class Player {
    private String name;
    private double pointPerGame;
    private double assistPerGame;
    private double reboundPerGame;

    // REQUIRES: the player's name can not be an empty string,
    //           point per game, assist per game and rebound per game have to be >= 0
    // EFFECTS: construct a player with his name, point per game, assist per game, and rebound per game
    public Player(String name, double pointPerGame, double assistPerGame, double reboundPerGame) {
        this.name = name;
        this.pointPerGame = pointPerGame;
        this.assistPerGame = assistPerGame;
        this.reboundPerGame = reboundPerGame;
    }

    // EFFECTS: get the name of the player
    public String getPlayerName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: reset the name of the player
    public void resetName(String name) {
        if (!this.name.equals(name)) {
            String prevName = this.name;
            this.name = name;
            EventLog.getInstance().logEvent(new Event("The name of the player: "
                    + prevName + " has been changed to " + this.name));
        }
    }

    // EFFECTS: get the stats of point per game of the player
    public double getPointPerGame() {
        return this.pointPerGame;
    }

    // MODIFIES: this
    // EFFECTS: reset the stats of point per game of the player
    public void resetPointPerGame(double pointPerGame) {
        if (this.pointPerGame != pointPerGame) {
            this.pointPerGame = pointPerGame;
            EventLog.getInstance().logEvent(new Event("The PPG of " + name
                    + " has been changed to " + this.pointPerGame));
        }
    }

    // EFFECTS: get the stats of assist per game of the player
    public double getAssistPerGame() {
        return this.assistPerGame;
    }

    // MODIFIES: this
    // EFFECTS: reset the stats of assist per game of the player
    public void resetAssistPerGame(double assistPerGame) {
        if (this.assistPerGame != assistPerGame) {
            this.assistPerGame = assistPerGame;
            EventLog.getInstance().logEvent(new Event("The APG of " + name
                    + " has been changed to " + this.assistPerGame));
        }
    }

    // EFFECTS: get the stats of rebound per game for the player
    public double getReboundPerGame() {
        return this.reboundPerGame;
    }

    // MODIFIES: this
    // EFFECTS: reset the stats of rebound per game of the player
    public void resetReboundPerGame(double reboundPerGame) {
        if (this.reboundPerGame != reboundPerGame) {
            this.reboundPerGame = reboundPerGame;
            EventLog.getInstance().logEvent(new Event("The RPG of " + name
                    + " has been changed to " + this.reboundPerGame));
        }

    }

    // EFFECTS: store the player's data into a JSONObject and return it.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("point per game", Double.toString(pointPerGame));
        json.put("assist per game", Double.toString(assistPerGame));
        json.put("rebound per game", Double.toString(reboundPerGame));
        return json;
    }

}
