package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//Represents a team having a list of players and a name.
public class Team {
    private List<Player> team;
    private String teamName;

    // REQUIRES: the team name can not be an empty string.
    // EFFECTS: construct a team with a name
    public Team(String teamName) {
        this.teamName = teamName;
        team = new ArrayList<Player>();
    }

    //MODIFIES: this
    //EFFECT: add a new player to the team if the player
    //        is not already in the team
    public void addPlayer(Player player) {
        if (!containsPlayer(player)) {
            team.add(player);
            EventLog.getInstance().logEvent(new Event(player.getPlayerName()
                    + " has been added to " + this.getTeamName()));
        }
    }

    //MODIFIES: this
    //EFFECT: remove the player from the team
    public void removePlayer(Player player) {
        if (team.contains(player)) {
            team.remove(player);
            EventLog.getInstance().logEvent(new Event(player.getPlayerName()
                    + " has been removed from " + this.getTeamName()));
        }
    }

    //EFFECT : return true if the player is in the team, otherwise false.
    public boolean containsPlayer(Player player) {
        return team.contains(player);
    }

    //EFFECT: return the number of players in the team
    public int teamSize() {
        return team.size();
    }

    //EFFECT: get the name of the team
    public String getTeamName() {
        return teamName;
    }

    //MODIFIES: this
    //EFFECT: reset the name of the team
    public void resetTeamName(String teamName) {
        String prevName = teamName;
        this.teamName = teamName;
        EventLog.getInstance().logEvent(new Event("The name of the basketball team: "
                + prevName + " has been changed to " + teamName));
    }

    //EFFECT: get the player in the team according to the given index.
    public Player getPlayer(int index) {
        return team.get(index);
    }

    //EFFECT: get the list of players in the team.
    public List<Player> getPlayers() {
        return team;
    }

    // EFFECTS: returns the team as a JSON object.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", teamName);
        json.put("team", thingiesToJson());
        return json;
    }

    // EFFECTS: returns players in the team as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : team) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
