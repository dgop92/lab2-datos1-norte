package parchis.fragments;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class SketchFragment {

    protected PApplet sketch;
    protected float x;
    protected float y;
    protected float fragmentWidth;
    protected float fragmentHeight;

    public SketchFragment(PApplet sketch, float x, float y, 
        float fragmentWidth, float fragmentHeight) {
            
        this.sketch = sketch;
        this.x = x;
        this.y = y;
        this.fragmentWidth = fragmentWidth;
        this.fragmentHeight = fragmentHeight;
    }

    protected void drawDivison(){
        sketch.stroke(255);
        sketch.strokeWeight(1);
        sketch.line(x, y, x + fragmentWidth, y);
        sketch.line(x + fragmentWidth, y, x + fragmentWidth, y + fragmentHeight);
        sketch.line(x + fragmentWidth, y + fragmentHeight, x, y + fragmentHeight);
        sketch.line(x, y + fragmentHeight, x, y);
    }

    protected PVector getPosForCenterImage(int imageWidth, int imageHeight){
        float xPos = (fragmentWidth - imageWidth) / 2;
        float yPos = (fragmentHeight - imageHeight) / 2;
        return new PVector(x + xPos, y + yPos);
    }

    abstract public void update();
}
