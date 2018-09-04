package com.uc.casino.domain;

import java.util.Random;

public class Dice {
    public int getWinningScore() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
