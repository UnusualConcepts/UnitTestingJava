package com.ssg.casino.domain;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by ostkav15 23.05.18
 **/
public class WhenGamePlays extends BaseTest {

    @Test
    public void diceRollOneTime() throws CasinoGameException {
        Dice dice = mock(Dice.class);
        RollDiceGame game = new RollDiceGame(dice);

        game.play();

        verify(dice, times(1)).roll();
    }

    @Test
    public void allWinnersCallWin() throws CasinoGameException {
        RollDiceGame winGame = newRollDiceGameWithKnownScore();
        Player winner1 = playerWinGame(winGame);
        Player winner2 = playerWinGame(winGame);

        winGame.play();

        verify(winner1, times(1)).win(10*6);
        verify(winner2, times(1)).win(10*6);
    }
}
