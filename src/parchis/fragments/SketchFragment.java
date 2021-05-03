package parchis.fragments;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class SketchFragment {

    protected PApplet sketch;
    protected float x;
    protected float y;
    protected float fwidth;
    protected float fheight;

    public SketchFragment(PApplet sketch, float x, float y, 
        float fragmentWidth, float fragmentHeight) {
            
        this.sketch = sketch;
        this.x = x;
        this.y = y;
        this.fwidth = fragmentWidth;
        this.fheight = fragmentHeight;
    }

    protected void drawDivison(){
        sketch.stroke(255);
        sketch.strokeWeight(1);
        sketch.line(x, y, x + fwidth, y);
        sketch.line(x + fwidth, y, x + fwidth, y + fheight);
        sketch.line(x + fwidth, y + fheight, x, y + fheight);
        sketch.line(x, y + fheight, x, y);
    }

    protected PVector getPosForCenterImage(int imageWidth, int imageHeight){
        float xPos = (fwidth - imageWidth) / 2;
        float yPos = (fheight - imageHeight) / 2;
        return new PVector(x + xPos, y + yPos);
    }

    protected float getXMid(){
        return x + (fwidth / 2);
    }

    protected float getYMid(){
        return y + (fheight / 2);
    }

    abstract public void update();
}
