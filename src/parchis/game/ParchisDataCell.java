package parchis.game;

import java.util.ArrayList;

import parchis.game.players.Player;

public class ParchisDataCell {
    
    private int cellPos;
    private ArrayList<Player> players;

    public ParchisDataCell(int cellPos) {
        this.cellPos = cellPos;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public int getCellPos() {
        return cellPos;
    }

    public void setCellPos(int cellPos) {
        this.cellPos = cellPos;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
