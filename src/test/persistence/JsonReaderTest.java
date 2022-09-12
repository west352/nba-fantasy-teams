package persistence;

import model.League;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            League league = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLeague.json");
        try {
            League league = reader.read();
            assertEquals("National Basketball Association", league.getLeagueName());
            assertEquals(0, league.leagueSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLeague.json");
        try {
            League league = reader.read();
            assertEquals("National Basketball Association", league.getLeagueName());
            assertEquals(1, league.leagueSize());
            List<Team> teams = league.getTeams();
            checkTeam("Los Angles Lakers", teams.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
