package parchis.game.views;

import java.util.ArrayList;

import parchis.game.players.Player;

public interface GameControlView {
    
    public void endOfTheGame(ArrayList<Player> podiumOrder);
}
