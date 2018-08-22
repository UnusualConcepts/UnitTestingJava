package com.uc.casino.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RollDiceGame implements IGame{
    private HashMap<Player, List<Bet>> playersBets = new HashMap<Player, List<Bet>>();
    private int playersCount;
    private IDice dice;

    public RollDiceGame(IDice dice) {
        this.dice = dice;
        playersCount = 0;
    }

    public void addBet(Player player, Bet bet) throws CasinoGameException {

        if (bet.getAmount() % 5 != 0) {
            throw new CasinoGameException("We can take bets divisible by 5 only");
        }

        if (bet.getScore() > 6 || bet.getScore() < 1) {
            throw new CasinoGameException("Wrong bet: we can take from 1 to 6 only");
        }

        if(playersBets.get(player) == null) {
            List<Bet> bets = new ArrayList<Bet>();
            bets.add(bet);
            playersBets.put(player, bets);
        } else {
            playersBets.get(player).add(bet);
        }

    }

    public void play() throws CasinoGameException {
        int winningScore = dice.getWinningScore();
        for (Player player : playersBets.keySet()) {
            List<Bet> bets = playersBets.get(player);
            for (Bet bet : bets) {
                if (bet.getScore() == winningScore) {
                    player.win(bet.getAmount() * 6);
                } else {
                    player.lose();
                }
            }
        }
        playersBets.clear();
    }

    public void leave(Player player) throws CasinoGameException {
        if (!playersBets.containsKey(player)) {
            return;
        }

        playersBets.remove(player);
    }

    public boolean isFull() {
        return playersCount >= 6;
    }

    public void addPlayer() {
        playersCount++;
    }

    public List<Bet> getPlayerBets(Player player) {
        return playersBets.get(player);
    }
}
