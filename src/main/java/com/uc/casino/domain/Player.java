package com.uc.casino.domain;

public class Player {
    private RollDiceGame activeGame;
    private int chips;
    private Casino casino;

    public Player(Casino casino) {
        this.casino = casino;
    }

    public RollDiceGame activeGame() {
        return activeGame;
    }

    public int getAvailableChips() {
        return chips;
    }

    private boolean isInGame() {
        return activeGame != null;
    }

    public void joins(RollDiceGame game) throws CasinoGameException {

        if(game.isFull()) {
            throw new CasinoGameException("Number of players exceeded");
        }

        if (isInGame()) {
            throw new CasinoGameException("Player must leave the current game before joining another game");
        }
        game.addPlayer();
        activeGame = game;
    }

    public void leave() throws CasinoGameException {
        activeGame.leave(this);
        activeGame = null;
    }

    public void buy(int chips) throws CasinoGameException {
        if (chips < 0) {
            throw new CasinoGameException("Buying negative numbers is not allowed");
        }

        this.chips = casino.sell(chips);
    }

    public void bet(Bet bet) throws CasinoGameException {

        if(activeGame == null) {
            throw new CasinoGameException("You must join game to make bet");
        }

        if (bet.getAmount() > chips) {
            throw new CasinoGameException("Can not bet more than chips available");
        }

        chips -= bet.getAmount();
        activeGame.addBet(this, bet);
    }

    public void win(int chips) {
        this.chips += chips;
    }

    public void lose() {
    }

}
