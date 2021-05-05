package parchis.game;

import java.util.ArrayList;
import java.util.HashMap;

import parchis.game.players.Player;
import parchis.game.players.PlayerColor;
import parchis.game.players.PlayerState;
import parchis.game.states.HeaderState;
import parchis.structures.CircularLinkedList;
import parchis.structures.CircularNode;

public class GameControl implements DiceObserver{
    
    private CircularLinkedList<ParchisDataCell> parchisList;
    private CircularLinkedList<Player> turnList;
    private CircularNode<Player> playerTurn;
    private ArrayList<Player> players;

    private HeaderState headerState;
    private HashMap<PlayerColor, PlayerState> playerStateNames;

    public GameControl(ArrayList<PlayerColor> turnOrders) {

        
        parchisList = new CircularLinkedList<>();
        for (int i = 1; i <= 24; i++) {
            parchisList.add(new ParchisDataCell(i));
        }

        players = new ArrayList<>();
        turnList = new CircularLinkedList<>();
        playerStateNames = new HashMap<>();
        for (PlayerColor playerColor : turnOrders) {
            Player player = new Player(PlayerState.EnInicio, playerColor);
            turnList.add(player);
            players.add(player);
            parchisList.getHead().getItem().addPlayer(player);
            playerStateNames.put(playerColor, PlayerState.EnInicio);
        }

        playerTurn = turnList.getHead();
        headerState = new HeaderState(
            playerTurn.getItem().getPlayerColor(),
            playerStateNames
        );
    }

    public HeaderState getHeaderState() {
        return headerState;
    }

    public void changeTurn(){
        playerTurn = playerTurn.next;
        headerState.setPlayerColorTurn(playerTurn.getItem().getPlayerColor());
        playerStateNames.put(PlayerColor.Rojo, PlayerState.Normal);
    }

    @Override
    public void OnThrow(int diceValue) {
        System.out.println(diceValue);
    }

    public CircularLinkedList<ParchisDataCell> getParchisList() {
        return parchisList;
    }
}
