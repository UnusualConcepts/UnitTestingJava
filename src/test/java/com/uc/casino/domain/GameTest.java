package com.uc.casino.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Mockito.*;

public class GameTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLose_shouldBeCalledWhenPlayerLoses() throws CasinoGameException {

        Dice stubDice = mock(Dice.class);
        when(stubDice.getWinningScore()).thenReturn(1);

        RollDiceGame game = new RollDiceGame(stubDice);

        Player player = mock(Player.class);

        game.addBet(player, new Bet(1,2));

        game.play();

        verify(player).lose();
    }

    @Test
    public void testJoins_shouldNotJoinMoreThanSixPlayers() throws CasinoGameException {
        RollDiceGame game = createGameWithSixPlayers();

        thrown.expectMessage("Game is full!");

        game.addPlayer(new Player());
    }

    private RollDiceGame createGameWithSixPlayers() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame();

        for (int i = 0; i < 6; i++) {
            game.addPlayer(new Player());
        }
        return game;
    }


}
