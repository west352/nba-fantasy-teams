package persistence;

import model.League;
import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            League league = new League();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            League league = new League();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeague.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLeague.json");
            league = reader.read();
            assertEquals("National Basketball Association", league.getLeagueName());
            assertEquals(0, league.leagueSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            League league = new League();
            Team team = new Team("Milwaukee Bucks");
            Player player1 = new Player("Giannis Antetokounmpo",
                    30.5, 5.4, 12.5);
            Player player2 = new Player("Kris Middleton", 23.5, 6.1, 7.3);
            Player player3 = new Player("True Holiday", 20.0, 7.8, 6.9);
            team.addPlayer(player1);
            team.addPlayer(player2);
            team.addPlayer(player3);
            league.addTeam(team);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLeague.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLeague.json");
            league = reader.read();
            assertEquals("National Basketball Association", league.getLeagueName());
            List<Team> teams = league.getTeams();
            assertEquals(1, teams.size());
            checkTeam("Milwaukee Bucks", teams.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    }