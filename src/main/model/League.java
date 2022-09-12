package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//Represents National Basketball Association that has a list of teams.
public class League {
    private static final String LEAGUE = "National Basketball Association";
    private List<Team> teams;

    // EFFECTS: construct a basketball league
    public League() {
        teams = new ArrayList<Team>();
    }

    //MODIFIES: this
    //EFFECT: add a new team to the league if the team
    //        with same name is not already in the league
    public void addTeam(Team team) {
        boolean isSameTeam = false;
        for (Team next : teams) {
            if (!next.getTeamName().equals(team.getTeamName())) {
                isSameTeam = false;
            } else {
                isSameTeam = true;
                break;
            }
        }
        if (!isSameTeam) {
            teams.add(team);
            EventLog.getInstance().logEvent(new Event(team.getTeamName()
                    + " has been added to " + LEAGUE));
        }
    }

    //MODIFIES: this
    //EFFECT: remove the team from the league
    public void removeTeam(Team team) {
        if (teams.contains(team)) {
            teams.remove(team);
            EventLog.getInstance().logEvent(new Event(team.getTeamName()
                    + " has been removed from " + LEAGUE));

        }
    }

    //EFFECT : return true if the team is in the league, otherwise false.
    public boolean containsTeam(Team team) {
        return teams.contains(team);
    }

    //EFFECT: return the number of team in the league.
    public int leagueSize() {
        return teams.size();
    }

    //EFFECT: get the team in the league according to the given index.
    public Team getTeam(int index) {
        return teams.get(index);
    }

    //EFFECT: get the name of the league
    public String getLeagueName() {
        return LEAGUE;
    }

    //EFFECT: get all the teams in the league
    public List<Team> getTeams() {
        return teams;
    }

    // EFFECTS: returns the league as a JSON object.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", LEAGUE);
        json.put("basketball league", thingiesToJson());
        return json;
    }

    // EFFECTS: returns teams in the league as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : teams) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
