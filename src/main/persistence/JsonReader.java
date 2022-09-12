package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.League;
import model.Player;
import model.Team;
import org.json.*;

// Represents a reader that reads league from JSON data stored in file
// The code in this class is based on the phase 2 project demo provided on the edx.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads league from file and returns it;
    // throws IOException if an error occurs reading data from file
    public League read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses basketball league from JSON object and returns it
    private League parseLeague(JSONObject jsonObject) {
        League league = new League();
        addBasketballLeague(league, jsonObject);
        return league;
    }

    // MODIFIES: league
    // EFFECTS: parses basketball league from JSON object and adds them to league
    private void addBasketballLeague(League league, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("basketball league");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            league.addTeam(parseTeam(nextTeam));

        }
    }

    // MODIFIES: league
    // EFFECTS: parses team from JSON object and adds them to league
    private Team parseTeam(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Team team = new Team(name);
        addPlayers(team, jsonObject);
        return team;
    }

    // MODIFIES: team, league
    // EFFECTS: parses players from JSON object and adds them to team
    private void addPlayers(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("team");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(team, nextPlayer);
        }
    }


    // MODIFIES: team, league
    // EFFECTS: parses player from JSON object and adds it to team
    private void addPlayer(Team team, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String pointPerGame = jsonObject.getString("point per game");
        String assistPerGame = jsonObject.getString("assist per game");
        String reboundPerGame = jsonObject.getString("rebound per game");
        Player player = new Player(name, Double.parseDouble(pointPerGame), Double.parseDouble(assistPerGame),
                                   Double.parseDouble(reboundPerGame));
        team.addPlayer(player);
    }
}



