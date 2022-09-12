package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private static final Player PLAYER1 = new Player("Lebron James", 25.5,
            7.8, 7.9);
    private static final Player PLAYER2 = new Player("Stephen Curry", 28.5,
            5.0, 12.3);
    private static final Player PLAYER3 = new Player("Anthony Davis", 27.3,
            3.5, 10.2);
    private Team team;

    @BeforeEach
    public void runBefore() {
        team = new Team("Los Angles Lakers");
    }

    @Test
    public void testAddPlayerMultiple() {
        team.addPlayer(PLAYER1);
        team.addPlayer(PLAYER2);
        team.addPlayer(PLAYER3);
        assertEquals(3, team.teamSize());
        assertTrue(team.containsPlayer(PLAYER1));
        assertTrue(team.containsPlayer(PLAYER2));
        assertTrue(team.containsPlayer(PLAYER3));
    }

    @Test
    public void testAddPlayerDuplicate() {
        team.addPlayer(PLAYER1);
        team.addPlayer(PLAYER2);
        team.addPlayer(PLAYER2);
        assertEquals(2, team.teamSize());
        assertTrue(team.containsPlayer(PLAYER1));
        assertTrue(team.containsPlayer(PLAYER2));
    }

    @Test
    public void testRemovePlayerEmpty() {
        team.removePlayer(PLAYER1);
        assertEquals(0, team.teamSize());
    }

    @Test
    public void testRemovePlayerOne() {
        team.addPlayer(PLAYER1);
        assertEquals(1, team.teamSize());
        team.removePlayer(PLAYER1);
        assertFalse(team.containsPlayer(PLAYER1));
        assertEquals(0, team.teamSize());
    }

    @Test
    public void testRemovePlayerMultiple() {
        team.addPlayer(PLAYER1);
        team.addPlayer(PLAYER2);
        team.addPlayer(PLAYER3);
        assertEquals(3, team.teamSize());

        team.removePlayer(PLAYER1);
        team.removePlayer(PLAYER3);
        assertEquals(1, team.teamSize());
        assertFalse(team.containsPlayer(PLAYER1));
        assertFalse(team.containsPlayer(PLAYER3));
        assertTrue(team.containsPlayer(PLAYER2));
    }

    @Test
    public void testContainsPlayer() {
        team.addPlayer(PLAYER1);
        team.addPlayer(PLAYER3);
        assertTrue(team.containsPlayer(PLAYER1));
        assertTrue(team.containsPlayer(PLAYER3));
        assertFalse(team.containsPlayer(PLAYER2));
    }

    @Test
    public void testTeamSizeZero() {
        assertEquals(0, team.teamSize());
    }

    @Test
    public void testTeamSizeOne() {
        team.addPlayer(PLAYER1);
        assertEquals(1, team.teamSize());
    }

    @Test
    public void testTeamSizeMultiple() {
        team.addPlayer(PLAYER1);
        team.addPlayer(PLAYER2);
        team.addPlayer(PLAYER3);
        assertEquals(3, team.teamSize());
    }

    @Test
    public void testGetTeamName() {
        assertEquals("Los Angles Lakers", team.getTeamName());
    }

    @Test
    public void testResetTeamName() {
        team.resetTeamName("Milwaukee Bucks");
        assertEquals("Milwaukee Bucks", team.getTeamName());
    }

    @Test
    public void testGetPlayer() {
        team.addPlayer(PLAYER1);
        team.addPlayer(PLAYER2);
        team.addPlayer(PLAYER3);
        assertEquals("Lebron James", team.getPlayer(0).getPlayerName());
        assertEquals("Stephen Curry", team.getPlayer(1).getPlayerName());
        assertEquals("Anthony Davis", team.getPlayer(2).getPlayerName());
    }

    @Test
    public void testGetPlayers() {
        team.addPlayer(PLAYER1);
        team.addPlayer(PLAYER2);
        team.addPlayer(PLAYER3);

        int i = 0;
        for (Player player : team.getPlayers()) {
            assertEquals(team.getPlayer(i).getPlayerName(), player.getPlayerName());
            i++;
        }
    }

}













