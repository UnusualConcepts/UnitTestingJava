package com.uc.casino.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class PlayerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLose_shouldErasePlayersChips() throws CasinoGameException {

        RollDiceGame game = new RollDiceGame(new StubDice(2));
        Player player = new Player();

        player.joins(game);
        player.buy(1);
        player.bet(new Bet(1, 1));

        game.play();

        assertEquals(0, player.getAvailableChips());
    }

    @Test
    public void testWin_shouldGetSixBets() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame(new StubDice(1));
        Player player = new Player();

        player.joins(game);
        player.buy(1);
        player.bet(new Bet(1, 1));

        game.play();

        assertEquals(1 * 6, player.getAvailableChips());
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