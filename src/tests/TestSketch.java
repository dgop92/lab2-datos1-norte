package tests;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PShape;

public class TestSketch extends PApplet{
    
    public static int SCREEN_WIDTH = 800;
    public static int SCREEN_HEIGHT = 600;

    PFont myFont;
    PFont myFont2;
    PImage diceM;
    //PShape pShape;

    @Override
    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void setup() {
        myFont = createFont("Segoe UI", 32);
        myFont2 = createFont("Segoe UI Bold", 32);
        diceM = loadImage("data/board-images/dice_movement.gif");
        //pShape = loadShape("data/board-images/testvg1.svg");
        //pShape.setFill(color(255, 0,0));
        //pShape.fill(color(255, 0,0));
        //pShape.setStroke(true);
    }

    @Override
    public void draw() {
        background(0);
        color(255);
        textSize(32);
        textFont(myFont);
        stroke(5);
        text("Hola", 300, 150);
        textFont(myFont2);
        text("Hola", 150, 150);
        // image(diceM, 10, 10);
        //fill(255);
        //shape(pShape, 50, 50, 100, 100);
        strokeWeight(2);
        color(255, 0, 255);
        circle(25, 25, 50);
    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }


    @Override
    public void mouseClicked() {

    }
}
