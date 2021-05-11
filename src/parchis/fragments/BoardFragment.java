package parchis.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import parchis.game.ParchisDataCell;
import parchis.game.players.Player;
import parchis.game.players.PlayerColor;
import parchis.structures.CircularLinkedList;
import parchis.structures.CircularNode;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class BoardFragment extends SketchFragment {

    private final int BACKGROUND_COLOR;
    private final float BOARD_PADDING = 32;
    private final float LIST_MT = 40;
    private final float LIST_MR = 56;
    private final int COLUMNS = 6;
    private final int ROWS;
    private CircularLinkedList<ParchisDataCell> parchisList;
    private final int playerWidth = 24;
    private final int playerHeight = 24;
    private HashMap<PlayerColor, PImage> playerImages;
    private static final String PLAYER_IMAGES_PATH =
        "src/data/board-images/players/%s-piece.png";

    public BoardFragment(
        PApplet sketch,
        float x,
        float y,
        float fragmentWidth,
        float fragmentHeight,
        CircularLinkedList<ParchisDataCell> parchisList
    ) {
        super(sketch, x, y, fragmentWidth, fragmentHeight);
        this.parchisList = parchisList;
        BACKGROUND_COLOR = sketch.color(247, 249, 249);
        ROWS = parchisList.getSize() / COLUMNS;

        playerImages = new HashMap<>();

        for (PlayerColor playerColor : PlayerColor.values()) {
            playerImages.put(
                playerColor,
                sketch.loadImage(
                    String.format(PLAYER_IMAGES_PATH, playerColor.toString())
                )
            );
        }
    }

    private void drawList() {
        CircularNode<ParchisDataCell> curr = parchisList.head;

        do {
            int pos = curr.getItem().getCellPos();
            PVector nodePosition;
            if (pos == 1) {
                nodePosition = getHeadPos();
                drawCellHead(nodePosition);
            } else if (pos < parchisList.getSize() - (ROWS - 2)) {
                int fakePos = pos - 1;
                int fakeColumns = COLUMNS - 1;
                int i = (fakePos - 1) / fakeColumns;
                int j = (fakePos - 1) % fakeColumns;
                nodePosition = getNodeCellPos(i, j);
                drawCellNode(nodePosition, getCellDirection(i, j));
            } else {
                nodePosition = getUpNodePos(parchisList.getSize() - pos);
                drawCellNode(nodePosition, CellNodeDirection.UP);
            }
            renderPlayers(curr, nodePosition);
            curr = curr.next;
        } while (curr != parchisList.head);
    }

    private void renderPlayers(
        CircularNode<ParchisDataCell> currNode,
        PVector position
    ) {
        sketch.textSize(16);
        sketch.fill(0);
        ArrayList<Player> players = currNode.getItem().getPlayers();
        try {
            for (Player player : players) {
                switch (player.getPlayerColor()) {
                    case Verde:
                        sketch.image(
                            playerImages.get(player.getPlayerColor()),
                            position.x - 10,
                            position.y + 5,
                            playerWidth,
                            playerHeight
                        );

                        // System.out.println(((64 - playerWidth) / 2));
                        // sketch.point(position.x, position.y);
                        break;
                    case Rojo:
                        sketch.image(
                            playerImages.get(player.getPlayerColor()),
                            position.x - 20,
                            position.y - 25,
                            playerWidth,
                            playerHeight
                        );
                        break;
                    case Azul:
                        sketch.image(
                            playerImages.get(player.getPlayerColor()),
                            position.x,
                            position.y - 25,
                            playerWidth,
                            playerHeight
                        );
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private PVector getNodeCellPos(int i, int j) {
        float xPos, yPos = 104 * i + 32;
        if (i % 2 == 0) {
            xPos = 104 * j + 136;
        } else {
            xPos = -1 * 104 * j + 552;
        }
        xPos += x + BOARD_PADDING + LIST_MR;
        yPos += y + BOARD_PADDING + LIST_MT;

        return new PVector(xPos, yPos);
    }

    private PVector getHeadPos() {
        float xPos, yPos = 32;
        xPos = 104 * -1 + 136;

        xPos += x + BOARD_PADDING + LIST_MR;
        yPos += y + BOARD_PADDING + LIST_MT;

        return new PVector(xPos, yPos);
    }

    private PVector getUpNodePos(int i) {
        float xPos, yPos = 104 * i + 136;
        xPos = 104 * -1 + 136;

        xPos += x + BOARD_PADDING + LIST_MR;
        yPos += y + BOARD_PADDING + LIST_MT;

        return new PVector(xPos, yPos);
    }

    private void drawCellNode(PVector position, CellNodeDirection direction) {
        sketch.fill(255);
        sketch.stroke(0);
        sketch.circle(position.x, position.y, 64);
        switch (direction) {
            case RIGHT:
                sketch.line(
                    position.x + 32,
                    position.y,
                    position.x + 32 + 40,
                    position.y
                );
                sketch.line(
                    position.x + 32 + 40,
                    position.y,
                    position.x + 32 + 30,
                    position.y - 10
                );
                sketch.line(
                    position.x + 32 + 40,
                    position.y,
                    position.x + 32 + 30,
                    position.y + 10
                );
                break;
            case LEFT:
                sketch.line(
                    position.x - 32,
                    position.y,
                    position.x - 32 - 40,
                    position.y
                );
                sketch.line(
                    position.x - 32 - 40,
                    position.y,
                    position.x - 32 - 30,
                    position.y - 10
                );
                sketch.line(
                    position.x - 32 - 40,
                    position.y,
                    position.x - 32 - 30,
                    position.y + 10
                );
                break;
            case DOWN:
                sketch.line(
                    position.x,
                    position.y + 32,
                    position.x,
                    position.y + 32 + 40
                );
                sketch.line(
                    position.x,
                    position.y + 32 + 40,
                    position.x - 10,
                    position.y + 32 + 30
                );
                sketch.line(
                    position.x,
                    position.y + 32 + 40,
                    position.x + 10,
                    position.y + 32 + 30
                );
                break;
            case UP:
                sketch.line(
                    position.x,
                    position.y - 32,
                    position.x,
                    position.y - 32 - 40
                );
                sketch.line(
                    position.x,
                    position.y - 32 - 40,
                    position.x - 10,
                    position.y - 32 - 30
                );
                sketch.line(
                    position.x,
                    position.y - 32 - 40,
                    position.x + 10,
                    position.y - 32 - 30
                );
                break;
        }
    }

    private void drawCellHead(PVector position) {
        sketch.fill(210);
        sketch.stroke(0);
        sketch.circle(position.x, position.y, 64);

        sketch.line(
            position.x + 32,
            position.y,
            position.x + 32 + 40,
            position.y
        );
        sketch.line(
            position.x + 32 + 40,
            position.y,
            position.x + 32 + 30,
            position.y - 10
        );
        sketch.line(
            position.x + 32 + 40,
            position.y,
            position.x + 32 + 30,
            position.y + 10
        );
        // sketch.textSize(16);
        // sketch.fill(0);
        // sketch.text(1 ,position.x, position.y);
    }

    private CellNodeDirection getCellDirection(int i, int j) {
        if (i % 2 == 0 && j == COLUMNS - 2 || i % 2 != 0 && j == COLUMNS - 2) {
            if (i == ROWS - 1) {
                return CellNodeDirection.LEFT;
            }
            return CellNodeDirection.DOWN;
        } else if (i % 2 == 0) {
            return CellNodeDirection.RIGHT;
        } else {
            return CellNodeDirection.LEFT;
        }
    }

    @Override
    public void update() {
        sketch.fill(BACKGROUND_COLOR);
        sketch.rect(x, y, fwidth, fheight);
        drawList();
    }

    private enum CellNodeDirection {
        LEFT,
        RIGHT,
        DOWN,
        UP,
    }
}
