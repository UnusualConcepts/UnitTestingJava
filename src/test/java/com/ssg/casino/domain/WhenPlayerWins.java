package com.ssg.casino.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhenPlayerWins extends BaseTest {

    @Test
    public void increaseInSixTimesAmountChips() throws CasinoGameException {
        RollDiceGame game = newFakeGameForWin();
        Player winner = activePlayer(game, 10);
        winner.bet(doBetForWin(10));

        game.play();

        assertEquals(10 * 6, winner.getAvailableChips());
    }
}
