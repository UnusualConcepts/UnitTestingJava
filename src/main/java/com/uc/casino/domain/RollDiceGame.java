package com.uc.casino.domain;

import java.util.HashMap;
import java.util.Random;

public class RollDiceGame {
    private HashMap<Player, Bet> playersBets = new HashMap<Player, Bet>();
    private int playerCounter = 0;

    public void addBet(Player player, Bet bet) {
        playersBets.put(player, bet);
    }

    public void play() throws CasinoGameException {
        Random random = new Random();
        int winningScore = random.nextInt(6) + 1;

        for (Player player : playersBets.keySet()) {
            Bet bet = playersBets.get(player);
            if (bet.getScore() == winningScore) {
                player.win(bet.getAmount() * 6);
            } else {
                player.lose();
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

    public void addPlayer(Player player) throws CasinoGameException {
        if (playerCounter >= 6) {
            throw new CasinoGameException("Game is full!");
        }

        playerCounter++;
    }
}
