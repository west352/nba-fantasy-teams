package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    private Player player1;
    private Player player2;

    @BeforeEach
    public void runBefore() {
        player1 = new Player("Stephen Curry", 28.5, 5.0, 12.3);
        player2 = new Player("Anthony Davis", 27.3, 3.5, 10.2);

    }

    @Test
    public void testGetPlayerName() {
        assertEquals("Stephen Curry", player1.getPlayerName());
        assertEquals("Anthony Davis", player2.getPlayerName());
    }

    @Test
    public void testResetPlayerName() {
        player1.resetName("Stephen Curry");
        assertEquals("Stephen Curry", player1.getPlayerName());
        player2.resetName("Anthony Davis");
        assertEquals("Anthony Davis", player2.getPlayerName());
        player1.resetName("Lebron James");
        player2.resetName("Kevin Durant");
        assertEquals("Lebron James", player1.getPlayerName());
        assertEquals("Kevin Durant", player2.getPlayerName());
    }

    @Test
    public void testGetPointPerGame() {
        assertEquals(28.5, player1.getPointPerGame());
        assertEquals(27.3, player2.getPointPerGame());
    }

    @Test
    public void testResetPointPerGame() {
        player1.resetPointPerGame(28.5);
        player2.resetPointPerGame(27.3);
        assertEquals(28.5, player1.getPointPerGame());
        assertEquals(27.3, player2.getPointPerGame());
        player1.resetPointPerGame(30.2);
        player2.resetPointPerGame(25.6);
        assertEquals(30.2, player1.getPointPerGame());
        assertEquals(25.6, player2.getPointPerGame());
    }

    @Test
    public void testGetAssistPerGame() {
        assertEquals(5.0, player1.getAssistPerGame());
        assertEquals(3.5, player2.getAssistPerGame());
    }

    @Test
    public void testResetAssistPerGame() {
        player1.resetAssistPerGame(5.0);
        player2.resetAssistPerGame(3.5);
        assertEquals(5.0, player1.getAssistPerGame());
        assertEquals(3.5, player2.getAssistPerGame());
        player1.resetAssistPerGame(8.7);
        player2.resetAssistPerGame(5.0);
        assertEquals(8.7, player1.getAssistPerGame());
        assertEquals(5.0, player2.getAssistPerGame());
    }

    @Test
    public void testGetReboundPerGame() {
        assertEquals(12.3, player1.getReboundPerGame());
        assertEquals(10.2, player2.getReboundPerGame());
    }

    @Test
    public void testResetReboundPerGame() {
        player1.resetReboundPerGame(12.3);
        player2.resetReboundPerGame(10.2);
        assertEquals(12.3, player1.getReboundPerGame());
        assertEquals(10.2, player2.getReboundPerGame());
        player1.resetReboundPerGame(6.2);
        player2.resetReboundPerGame(13.3);
        assertEquals(6.2, player1.getReboundPerGame());
        assertEquals(13.3, player2.getReboundPerGame());
    }
}
