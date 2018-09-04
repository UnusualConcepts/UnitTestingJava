package com.uc.casino.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testJoins_shouldNotJoinMoreThanSixPlayers() throws CasinoGameException {

        RollDiceGame game = new RollDiceGame();

        for (int i = 0; i < 6; i++) {
            Player player = new Player();
            player.joins(game);
        }

        Player player = new Player();

        thrown.expectMessage("Game is full!");
        player.joins(game);
    }

    @Test
    public void testJoins_activeGameShouldBeNotNullAfterJoining() throws CasinoGameException {
        Player player = new Player();
        RollDiceGame game = new RollDiceGame();

        // act
        player.joins(game);

        assertNotNull(player.activeGame());
    }

    @Test
    public void testLeave_gameShouldBeNull() throws CasinoGameException {
        Player player = createPlayerInGame();

        player.leave();

        assertNull(player.activeGame());
    }

    @Test(expected = CasinoGameException.class)
    public void testLeave_shouldNotLeaveIfNotJoined() throws CasinoGameException {

        Player player = new Player();

        player.leave();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testJoins_shouldNotAllowJoinTwice() throws CasinoGameException {
        Player player = createPlayerInGame();

        thrown.expectMessage("Player must leave the current game before joining another game");

        player.joins(new RollDiceGame());
    }

    private Player createPlayerInGame() throws CasinoGameException {
        Player player = new Player();
        RollDiceGame game = new RollDiceGame();
        player.joins(game);
        return player;
    }

}