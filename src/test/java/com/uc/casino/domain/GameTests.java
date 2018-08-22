package com.uc.casino.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class GameTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private IDice dice;

    @Test(expected = CasinoGameException.class)
    public void playerJoinsGame_sixPlayerAlreadyIn_ThrowsCasinoGameException() throws CasinoGameException {

        RollDiceGame game = getGameWith6Players();

        Player player = new Player(FakeCasino.enterRichCasino());
        player.joins(game);

    }

    @Test
    public void addBet_withAmount6_ThrowsCasinoGameException() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame(dice);
        Player player = new Player(FakeCasino.enterRichCasino());
        player.buy(6);
        player.joins(game);

        thrown.expectMessage("We can take bets divisible by 5 only");

        player.bet(new Bet(6, 1));
    }

    @Test
    public void addBet_withAmount5_gameHasThisBet() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame(dice);
        Player player = new Player(FakeCasino.enterRichCasino());
        player.buy(5);
        player.joins(game);

        player.bet(new Bet(5, 1));

        assertEquals(5, game.getPlayerBets(player).get(0).getAmount());
    }

    @Test
    public void addBet_withScore1_gameHasThisBet() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame(dice);
        Player player = new Player(FakeCasino.enterRichCasino());
        player.buy(5);
        player.joins(game);

        player.bet(new Bet(5, 1));

        assertEquals(1, game.getPlayerBets(player).get(0).getScore());
    }

    @Test
    public void addBet_withScore7_throwsCasinoGameException() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame(dice);
        Player player = new Player(FakeCasino.enterRichCasino());
        player.buy(5);
        player.joins(game);

        thrown.expectMessage("Wrong bet: we can take from 1 to 6 only");

        player.bet(new Bet(5, 7));
    }

    @Test
    public void play_playerWithWrongBet_loseIt() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame(new FakeDice(2));
        Player loser = new Player(FakeCasino.enterRichCasino());
        loser.joins(game);

        loser.buy(5);
        loser.bet(new Bet(5, 1));

        game.play();

        assertEquals(5 - 5, loser.getAvailableChips());
    }

    @Test
    public void play_playerWithRightBet_winSixBets() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame(new FakeDice(1));
        Player lucky = new Player(FakeCasino.enterRichCasino());
        lucky.joins(game);

        lucky.buy(5);
        lucky.bet(new Bet(5, 1));

        game.play();

        assertEquals(5 * 6, lucky.getAvailableChips());
    }



    private RollDiceGame getGameWith6Players() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame(dice);

        Player player1 = new Player(FakeCasino.enterRichCasino());
        player1.joins(game);
        Player player2 = new Player(FakeCasino.enterRichCasino());
        player2.joins(game);
        Player player3 = new Player(FakeCasino.enterRichCasino());
        player3.joins(game);
        Player player4 = new Player(FakeCasino.enterRichCasino());
        player4.joins(game);
        Player player5 = new Player(FakeCasino.enterRichCasino());
        player5.joins(game);
        Player player6 = new Player(FakeCasino.enterRichCasino());
        player6.joins(game);

        return game;
    }

}
