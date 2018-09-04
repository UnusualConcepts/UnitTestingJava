package com.uc.casino.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GameTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testJoins_shouldNotJoinMoreThanSixPlayers() throws CasinoGameException {
        RollDiceGame game = createGameWithSixPlayers();

        thrown.expectMessage("Game is full!");

        game.addPlayer(new Player());
    }

    private RollDiceGame createGameWithSixPlayers() throws CasinoGameException {
        RollDiceGame game = new RollDiceGame();

        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());

        return game;
    }

}
