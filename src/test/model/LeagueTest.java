package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeagueTest {
    private static final Team TEAM1 = new Team("Los Angles Lakers");
    private static final Team TEAM2 = new Team("Milwaukee Bucks");
    private static final Team TEAM3 = new Team("Brooklyn Nets");
    private League league;

    @BeforeEach
    public void runBefore() {
        league = new League();
    }

    @Test
    public void testAddTeamMultiple() {
        league.addTeam(TEAM1);
        league.addTeam(TEAM2);
        league.addTeam(TEAM3);
        assertEquals(3, league.leagueSize());
        assertTrue(league.containsTeam(TEAM1));
        assertTrue(league.containsTeam(TEAM2));
        assertTrue(league.containsTeam(TEAM3));
    }

    @Test
    public void testAddTeamDuplicate() {
        league.addTeam(TEAM1);
        league.addTeam(TEAM2);
        league.addTeam(TEAM2);
        assertEquals(2, league.leagueSize());
        assertTrue(league.containsTeam(TEAM1));
        assertTrue(league.containsTeam(TEAM2));
    }

    @Test
    public void testRemoveTeamEmpty() {
        league.removeTeam(TEAM1);
        assertEquals(0, league.leagueSize());
    }

    @Test
    public void testRemoveTeamOne() {
        league.addTeam(TEAM1);
        assertEquals(1, league.leagueSize());
        league.removeTeam(TEAM1);
        assertFalse(league.containsTeam(TEAM1));
        assertEquals(0, league.leagueSize());
    }

    @Test
    public void testRemoveTeamMultiple() {
        league.addTeam(TEAM1);
        league.addTeam(TEAM2);
        league.addTeam(TEAM3);
        assertEquals(3, league.leagueSize());

        league.removeTeam(TEAM1);
        league.removeTeam(TEAM3);
        assertEquals(1, league.leagueSize());
        assertFalse(league.containsTeam(TEAM1));
        assertFalse(league.containsTeam(TEAM3));
        assertTrue(league.containsTeam(TEAM2));
    }

    @Test
    public void testContainsTeam() {
        league.addTeam(TEAM1);
        league.addTeam(TEAM3);
        assertTrue(league.containsTeam(TEAM1));
        assertTrue(league.containsTeam(TEAM3));
        assertFalse(league.containsTeam(TEAM2));
    }

    @Test
    public void testLeagueSizeZero() {
        assertEquals(0, league.leagueSize());
    }

    @Test
    public void testLeagueSizeOne() {
        league.addTeam(TEAM1);
        assertEquals(1, league.leagueSize());
    }

    @Test
    public void testLeagueSizeMultiple() {
        league.addTeam(TEAM1);
        league.addTeam(TEAM2);
        league.addTeam(TEAM3);
        assertEquals(3, league.leagueSize());
    }

    @Test
    public void testGetTeam() {
        league.addTeam(TEAM1);
        league.addTeam(TEAM2);
        league.addTeam(TEAM3);
        assertEquals("Los Angles Lakers", league.getTeam(0).getTeamName());
        assertEquals("Milwaukee Bucks", league.getTeam(1).getTeamName());
        assertEquals("Brooklyn Nets", league.getTeam(2).getTeamName());
    }

    @Test
    public void testGetLeagueName() {
        assertEquals("National Basketball Association", league.getLeagueName());
    }

    @Test
    public void testGetTeams() {
        league.addTeam(TEAM1);
        league.addTeam(TEAM2);
        league.addTeam(TEAM3);

        int i = 0;
        for (Team team : league.getTeams()) {
            assertEquals(league.getTeam(i).getTeamName(), team.getTeamName());
            i++;
        }
    }

}
