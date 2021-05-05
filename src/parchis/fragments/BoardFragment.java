package parchis.fragments;

import parchis.game.ParchisDataCell;
import parchis.structures.CircularLinkedList;
import parchis.structures.CircularNode;
import processing.core.PApplet;
import processing.core.PVector;

public class BoardFragment extends SketchFragment{
    
    private final int BACKGROUND_COLOR;
    private final float BOARD_PADDING = 32;
    private final float LIST_MT = 40;
    private final float LIST_MR = 56;
    private final int COLUMNS = 6;
    private final int ROWS;
    private CircularLinkedList<ParchisDataCell> parchisList;

    public BoardFragment(PApplet sketch, float x, float y, 
        float fragmentWidth, float fragmentHeight, 
        CircularLinkedList<ParchisDataCell> parchisList) {
        super(sketch, x, y, fragmentWidth, fragmentHeight);
        
        this.parchisList = parchisList;
        BACKGROUND_COLOR = sketch.color(247, 249, 249);
        ROWS = parchisList.getSize() / COLUMNS;
    }

    private void drawList(){

        CircularNode<ParchisDataCell> curr = parchisList.head;

        do{  
            int pos = curr.getItem().getCellPos();
            if (pos == 1){
                PVector headPos = getHeadPos();
                drawCellHead(headPos);
                
            }else if(pos < parchisList.getSize() - (ROWS - 2) ){
                int fakePos = pos - 1;
                int fakeColumns = COLUMNS - 1;
                int i = (fakePos - 1) / fakeColumns;
                int j = (fakePos - 1) % fakeColumns;
                PVector circleCenter = getNodeCellPos(i, j);
                drawCellNode(circleCenter, getCellDirection(i, j));
            }else{
                PVector upPos = getUpNodePos(parchisList.getSize() - pos);
                drawCellNode(upPos, CellNodeDirection.UP);
            }
            curr = curr.next;
        }while(curr != parchisList.head);  
        
    }

    private PVector getNodeCellPos(int i, int j){
        float xPos, yPos = 104*i + 32;
        if (i % 2 == 0){
            xPos = 104*j + 136;
        }else{
            xPos = -1 * 104 * j + 552;
        }
        xPos += x + BOARD_PADDING + LIST_MR;
        yPos += y + BOARD_PADDING + LIST_MT;
        
        return new PVector(xPos, yPos);
    }

    private PVector getHeadPos(){

        float xPos, yPos = 32;
        xPos = 104*-1 + 136;
        
        xPos += x + BOARD_PADDING + LIST_MR;
        yPos += y + BOARD_PADDING + LIST_MT;

        
        return new PVector(xPos, yPos);
    }

    private PVector getUpNodePos(int i){
        float xPos, yPos = 104*i + 136;
        xPos = 104*-1 + 136;
        
        xPos += x + BOARD_PADDING + LIST_MR;
        yPos += y + BOARD_PADDING + LIST_MT;

        return new PVector(xPos, yPos);
    }

    private void drawCellNode(PVector position, CellNodeDirection direction){
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

    private void drawCellHead(PVector position){
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

    private CellNodeDirection getCellDirection(int i, int j){

        if (i % 2 == 0 && j == COLUMNS - 2 || i % 2 != 0 && j == COLUMNS - 2){
            if (i == ROWS - 1){
                return CellNodeDirection.LEFT; 
            }
            return CellNodeDirection.DOWN;
        }else if (i % 2 == 0){
            return CellNodeDirection.RIGHT;
        }else{
            return CellNodeDirection.LEFT;
        }
    }

    @Override
    public void update() {
        sketch.fill(BACKGROUND_COLOR);
        sketch.rect(x, y, fwidth, fheight);
        drawList();
    }

    private enum CellNodeDirection{

        LEFT,
        RIGHT,
        DOWN,
        UP,
    }

}
