package parchis.game.players;

public class Player {
    
    protected PlayerState playerState;
    protected PlayerColor playerColor;

    public Player(PlayerState playerState, PlayerColor playerColor) {
        this.playerState = playerState;
        this.playerColor = playerColor;
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
}
