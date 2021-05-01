package tests;

import processing.core.PApplet;

public class TestSketch extends PApplet{
    
    public static int SCREEN_WIDTH = 800;
    public static int SCREEN_HEIGHT = 600;

    @Override
    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void setup() {
        
    }

    @Override
    public void draw() {
        
    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }


    @Override
    public void mouseClicked() {

    }
}