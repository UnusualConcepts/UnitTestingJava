package com.uc.casino.domain;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CasinoTest {

    private IDice dice;

    @Test
    public void buy_playerBuyChips_casinoBalanceDecreasesOnThatAmount () throws CasinoGameException {
        Casino casino = new Casino(100);
        Player player = new Player(casino);

        player.buy(5);

        assertEquals(100-5, casino.getBalance());
    }

    @Ignore
    public void play_playerBetsOnRightScore_casinoLoseSixBets() throws CasinoGameException {
        Casino casino = new Casino(100);
        Player player = new Player(casino);


        RollDiceGame game = new RollDiceGame(new FakeDice(1));
        player.joins(game);

        player.buy(5);
        player.bet(new Bet(5, 1));

        game.play();

        assertEquals(100-5*6,casino.getBalance());
    }

    @Test
    public void play_playerBetsOnWrongScore_casinoGetHisChips() throws CasinoGameException {

        Casino casino = new Casino(100);
        RollDiceGame game = new RollDiceGame(new FakeDice(1));

        Player loser = new Player(FakeCasino.enterRichCasino());

        loser.joins(game);

        loser.buy(5);
        loser.bet(new Bet(5, 1));

        game.play();

        assertEquals(100-5+5, casino.getBalance());
    }

}
