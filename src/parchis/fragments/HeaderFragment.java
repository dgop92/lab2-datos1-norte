package parchis.fragments;

import parchis.game.players.PlayerColor;
import parchis.game.states.HeaderState;
import processing.core.PApplet;
import processing.core.PFont;

public class HeaderFragment extends SketchFragment{

    private HeaderState headerState;
    public DiceFragment dice;
    public static int HEADER_COLOR;
    private PFont segoeUIBold;
    private PFont segoeUI;

    public HeaderFragment(PApplet sketch, float x, float y, 
        float fragmentWidth, float fragmentHeight, HeaderState headerState) {
        super(sketch, x, y, fragmentWidth, fragmentHeight);

        this.headerState = headerState;
        HEADER_COLOR = sketch.color(169, 117, 78);

        segoeUIBold = sketch.createFont("Segoe UI Bold", 16);
        segoeUI = sketch.createFont("Segoe UI", 14);

        dice = new DiceFragment(sketch, 150, 0, 150, 80);
    }

    @Override
    public void update() {
        draw();
        dice.update();
    }

    public void draw(){
        sketch.fill(HEADER_COLOR);
        sketch.strokeWeight(1);
        sketch.stroke(HEADER_COLOR);
        sketch.rect(x, y, fwidth, fheight);
        
        //turn part
        sketch.textFont(segoeUIBold);
        sketch.textSize(16);
        sketch.fill(255);
        sketch.text("Turno: ", x + 20, getYMid() + 8);
        sketch.textFont(segoeUI);
        sketch.textSize(14);
        sketch.text(
            headerState.getPlayerColorTurn().toString(), 
            x + 75, 
            getYMid() + 8
        );

        // player state
        sketch.textFont(segoeUIBold);
        sketch.textSize(16);
        sketch.fill(255);
        sketch.text("Estados: ", x + 600, getYMid() + 8);
        sketch.textFont(segoeUI);
        sketch.textSize(14);
        float yPos = 25;
        for (PlayerColor playerColor : PlayerColor.values()) {
            sketch.text(
                playerColor.toString() + ": ", 
                x + 680, 
                yPos
            );
            sketch.text(
                headerState.getPlayerStateNames().get(playerColor).toString(), 
                x + 730, 
                yPos
            );
            yPos += 20;
        }
    }

    public DiceFragment getDice() {
        return dice;
    }

}
