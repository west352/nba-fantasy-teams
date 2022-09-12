package persistence;

import model.Player;
import model.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    public void checkTeam(String name, Team team){
        assertEquals(name, team.getTeamName());
        List<Player> players = team.getPlayers();
        checkPlayer(players.get(0).getPlayerName(), players.get(0).getPointPerGame(),
                players.get(0).getAssistPerGame(), players.get(0).getReboundPerGame(), players.get(0));
        checkPlayer(players.get(1).getPlayerName(), players.get(1).getPointPerGame(),
                players.get(1).getAssistPerGame(), players.get(1).getReboundPerGame(), players.get(1));
        checkPlayer(players.get(2).getPlayerName(), players.get(2).getPointPerGame(),
                players.get(2).getAssistPerGame(), players.get(2).getReboundPerGame(), players.get(2));
    }

    protected void checkPlayer(String name, double pointPerGame, double assistPerGame,
                               double reboundPerGame, Player player) {
        assertEquals(name, player.getPlayerName());
        assertEquals(pointPerGame, player.getPointPerGame());
        assertEquals(assistPerGame, player.getAssistPerGame());
        assertEquals(reboundPerGame, player.getReboundPerGame());
    }
}
