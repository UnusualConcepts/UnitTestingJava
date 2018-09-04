package com.uc.casino.domain;

public class StubDice extends Dice {

    private int score;


    public StubDice(int score) {
        this.score = score;
    }

    @Override
    public int getWinningScore() {
        return score;
    }
}
