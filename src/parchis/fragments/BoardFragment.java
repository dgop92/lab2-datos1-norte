package parchis.fragments;

import processing.core.PApplet;

public class BoardFragment extends SketchFragment{
    
    int BACKGROUND_COLOR;

    public BoardFragment(PApplet sketch, float x, float y, 
        float fragmentWidth, float fragmentHeight) {
        super(sketch, x, y, fragmentWidth, fragmentHeight);

        BACKGROUND_COLOR = sketch.color(247, 249, 249);
    }

    @Override
    public void update() {
        sketch.fill(BACKGROUND_COLOR);
        sketch.rect(x, y, fwidth, fheight);
    }

}
