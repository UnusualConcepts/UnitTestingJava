package com.uc.casino.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.uc.casino.domain.FakeCasino.enterRichCasino;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayerTests {

    private final int positiveAmount = 5;
    private final int minimumBetAmount = 5;
    private IDice dice;

    @Test
    public void play_playerMakeTwoBets_getsChipsForCorrectBet() throws CasinoGameException {
        Player player = playerWith(10);

        RollDiceGame game = new RollDiceGame(new FakeDice(1));
        player.joins(game);

        player.bet(new Bet(5, 1));
        player.bet(new Bet(5, 2));

        game.play();

        assertEquals(5 * 6, player.getAvailableChips());
    }

    private Player playerWith(int chips) throws CasinoGameException {
        Player player = new Player(enterRichCasino());

        player.buy(chips);

        return player;
    }


    @Test
    public void joins_emptyGame_gameIsNotNull() throws CasinoGameException {
        Player player = new Player(enterRichCasino());

        player.joins(new RollDiceGame(dice));

        assertNotNull(player.activeGame());
    }

    @Test
    public void leave_playerInGame_gameIsNull() throws CasinoGameException {
        Player player = playerIn(new RollDiceGame(dice));

        player.leave();

        assertNull(player.activeGame());
    }

    @Test(expected = NullPointerException.class)
    public void leave_playerNotInGame_nullPointerExceptionThrows() throws CasinoGameException {
        Player player = new Player(enterRichCasino());

        player.leave();
    }

    @Test(expected = CasinoGameException.class)
    public void joins_playerAlreadyInGame_casinoGameExceptionThrows() throws CasinoGameException {
        Player player = playerIn(new RollDiceGame(dice));

        player.joins(new RollDiceGame(dice));
    }

    @Test
    public void buy_positiveAmount_ChipsAmountMatches() throws CasinoGameException {
        Player player = playerWith(positiveAmount);

        assertEquals(positiveAmount, player.getAvailableChips());
    }

    @Test(expected = CasinoGameException.class)
    public void buy_negativeAmount_isNotAllowed() throws CasinoGameException {
        Player player = new Player(enterRichCasino());

        player.buy(-1);
    }

    @Test(expected = CasinoGameException.class)
    public void bet_playerNotInGame_isNotAllowed() throws CasinoGameException {
        Player player = new Player(enterRichCasino());

        player.buy(positiveAmount);

        player.bet(new Bet(1, 1));
    }

    @Test
    public void bet_chips_chipsDecreasesOnBet() throws CasinoGameException {
        Player player = getPlayerWithChipsInGame(positiveAmount, new RollDiceGame(dice));

        player.bet(new Bet(minimumBetAmount, 1));

        assertEquals(positiveAmount - minimumBetAmount, player.getAvailableChips());
    }

    @Test(expected = CasinoGameException.class)
    public void bet_moreChipsThanYouHave_IsNotAllowed() throws CasinoGameException {
        Player player = getPlayerWithChips(positiveAmount);

        player.bet(new Bet(positiveAmount + 1, 1));
    }


    @Test
    public void bet_onTwoDifferentScores_gameHasBoth() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame(dice);

        Player player = playerIn(game);
        player.buy(10);


        Bet bet = new Bet(minimumBetAmount, 1);
        player.bet(bet);
        Bet anotherBet = new Bet(minimumBetAmount, 2);
        player.bet(anotherBet);

        assertThat(game.getPlayerBets(player), is(getBets(bet, anotherBet)));
    }

    private List<Bet> getBets(Bet bet, Bet bet2) {
        List<Bet> betList = new ArrayList<Bet>();
        betList.add(bet);
        betList.add(bet2);
        return betList;
    }

    private Player playerIn(RollDiceGame game) throws CasinoGameException {
        Player player = new Player(enterRichCasino());
        player.joins(game);
        return player;
    }

    private Player getPlayerWithChips(int amount) throws CasinoGameException {
        Player player = new Player(enterRichCasino());
        player.buy(amount);
        return player;
    }

    private Player getPlayerWithChipsInGame(int amount, RollDiceGame game) throws CasinoGameException {
        Player player = new Player(enterRichCasino());
        player.joins(game);
        player.buy(amount);
        return player;
    }

}