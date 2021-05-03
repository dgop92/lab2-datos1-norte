package parchis.game.states;

import java.util.HashMap;

import parchis.game.players.PlayerState;
import parchis.game.players.PlayerColor;

public class HeaderState {
    
    private PlayerColor playerColorTurn;
    private HashMap<PlayerColor, PlayerState> playerStateNames;

    public HeaderState(PlayerColor playerColorTurn, 
        HashMap<PlayerColor, PlayerState> playerStateNames) {
        this.playerColorTurn = playerColorTurn;
        this.playerStateNames = playerStateNames;
    }

    public void setPlayerColorTurn(PlayerColor playerColorTurn) {
        this.playerColorTurn = playerColorTurn;
    }

    public PlayerColor getPlayerColorTurn() {
        return playerColorTurn;
    }

    public HashMap<PlayerColor, PlayerState> getPlayerStateNames() {
        return playerStateNames;
    }

}
