package parchis.game.players;

import parchis.game.ParchisDataCell;
import parchis.structures.CircularNode;

public class Player {

    protected PlayerState playerState;
    protected PlayerColor playerColor;
    private CircularNode<ParchisDataCell> position;

    public Player(
        PlayerState playerState,
        PlayerColor playerColor,
        CircularNode<ParchisDataCell> position
    ) {
        this.playerState = playerState;
        this.playerColor = playerColor;
        this.position = position;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public CircularNode<ParchisDataCell> getPosition() {
        return position;
    }

    public void setPosition(CircularNode<ParchisDataCell> position) {
        this.position = position;
    }

    public void updatePosition(
        CircularNode<ParchisDataCell> oldPosition,
        CircularNode<ParchisDataCell> newPosition) {
        
        oldPosition.getItem().getPlayers().remove(this);
        newPosition.getItem().getPlayers().add(this);

        setPosition(newPosition);
    }

    
}
