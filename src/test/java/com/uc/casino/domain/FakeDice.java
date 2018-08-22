package com.uc.casino.domain;

public class FakeDice implements IDice {

    private int winningScore;

    public FakeDice(int winningScore) {
        this.winningScore = winningScore;
    }

    public int getWinningScore() {
        return winningScore;
    }
}
