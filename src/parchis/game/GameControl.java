package parchis.game;

import java.util.ArrayList;
import java.util.HashMap;
import parchis.game.players.Player;
import parchis.game.players.PlayerColor;
import parchis.game.players.PlayerState;
import parchis.game.states.HeaderState;
import parchis.game.views.GameControlView;
import parchis.structures.CircularLinkedList;
import parchis.structures.CircularNode;

public class GameControl implements DiceObserver {

    private CircularLinkedList<ParchisDataCell> parchisList;
    private CircularLinkedList<Player> turnList;
    private CircularNode<Player> playerTurn;
    private HeaderState headerState;
    private HashMap<PlayerColor, PlayerState> playerStateNames;
    private int sixCounter;
    private int prisionOpportunities;
    private ArrayList<Player> podiumOrder;
    private GameControlView gameControlView;
    public GameControl(
        ArrayList<PlayerColor> turnOrders,
        GameControlView gameControlView
    ) {
        this.gameControlView = gameControlView;        
        sixCounter = 0;
        prisionOpportunities = 0;

        parchisList = new CircularLinkedList<>();
        for (int i = 1; i <= 24; i++) {
            parchisList.add(new ParchisDataCell(i));
        }

        turnList = new CircularLinkedList<>();
        playerStateNames = new HashMap<>();
        for (PlayerColor playerColor : turnOrders) {
            Player player = new Player(
                PlayerState.EnInicio,
                playerColor,
                parchisList.getHead()
            );
            turnList.add(player);
            parchisList.getHead().getItem().addPlayer(player);
            playerStateNames.put(playerColor, PlayerState.EnInicio);
        }

        playerTurn = turnList.getHead();
        headerState =
            new HeaderState(
                playerTurn.getItem().getPlayerColor(),
                playerStateNames
            );

        podiumOrder = new ArrayList<>();
    }

    public HeaderState getHeaderState() {
        return headerState;
    }

    public void changeTurn(int diceValue) {
        if (diceValue != 6) {
            playerTurn = playerTurn.next;
            headerState.setPlayerColorTurn(
                playerTurn.getItem().getPlayerColor()
            );
        }
    }

    public void changeTurn() {
        playerTurn = playerTurn.next;
        headerState.setPlayerColorTurn(playerTurn.getItem().getPlayerColor());
    }

    @Override
    public void OnThrow(int diceValue) {
        if (diceValue == 6) {
            sixCounter++;
        } else {
            sixCounter = 0;
        }

        Player player = playerTurn.getItem();
        PlayerState playerState = player.getPlayerState();

        switch (playerState) {
            case EnInicio:
            case Normal:
                movePlayer(player, diceValue);
                checkPlayerVictory(player);
                // if the player won the dice value is 6,
                // so changeTurn will not changed from turn
                changeTurn(diceValue);
                checkPlayerMurder(player);
                break;
            case Carcel:
                checkPrisionStatus(player, diceValue);
                break;
            case Terminado:
                break;
        }

        updatePlayerStates();
        if (podiumOrder.size() >= 2) {
            podiumOrder.add(playerTurn.getItem());
            gameControlView.endOfTheGame(podiumOrder);
        }
    }

    private void checkPlayerVictory(Player player) {
        if (sixCounter == 3) {
            CircularNode<ParchisDataCell> currentPosition = player.getPosition();
            player.updatePosition(currentPosition, parchisList.head);
            sixCounter = 0;
        }

        if (player.getPosition().equals(parchisList.head)) {
            player.setPlayerState(PlayerState.Terminado);
            podiumOrder.add(player);
            CircularNode<Player> playerToDelete = playerTurn;
            changeTurn();
            turnList.deleteNode(playerToDelete);
        }
    }

    private void checkPrisionStatus(Player player, int diceValue) {
        if (diceValue == 6) {
            player.setPlayerState(PlayerState.EnInicio);
            changeTurn();
            prisionOpportunities = 0;
        } else {
            if (++prisionOpportunities == 3) {
                changeTurn();
                prisionOpportunities = 0;
            }
        }
    }

    private void checkPlayerMurder(Player player) {
        CircularNode<ParchisDataCell> currentPosition = player.getPosition();
        ArrayList<Player> players = currentPosition.getItem().getPlayers();
        if (players.size() > 1) {
            for (Player currentPlayer : players) {
                if (
                    !player.equals(currentPlayer) &&
                    currentPlayer.getPlayerState().equals(PlayerState.Normal)
                ) {
                    currentPlayer.setPlayerState(PlayerState.Carcel);
                    currentPlayer.updatePosition(
                        currentPosition,
                        parchisList.getHead()
                    );
                }
            }
        }
    }

    private void movePlayer(Player player, int diceValue) {
        CircularNode<ParchisDataCell> currentPosition = player.getPosition();
        int arrivePosition = currentPosition.getItem().getCellPos() + diceValue;
        // when the player arrived to the head
        if (arrivePosition / parchisList.getSize() == 1) {
            arrivePosition = 1;
        }
        CircularNode<ParchisDataCell> oldPosition = player.getPosition();
        while (currentPosition.getItem().getCellPos() != arrivePosition) {
            oldPosition = currentPosition;
            currentPosition = currentPosition.next;
            player.updatePosition(oldPosition, currentPosition);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        player.setPlayerState(PlayerState.Normal);
    }

    private void updatePlayerStates() {
        turnList.forEach(
            playerNode -> {
                Player player = playerNode.getItem();
                playerStateNames.replace(
                    player.getPlayerColor(),
                    player.getPlayerState()
                );
            }
        );
        for (Player player : podiumOrder) {
            playerStateNames.replace(
                player.getPlayerColor(),
                player.getPlayerState()
            );
        }
    }

    public CircularLinkedList<ParchisDataCell> getParchisList() {
        return parchisList;
    }
}
