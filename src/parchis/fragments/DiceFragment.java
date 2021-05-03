package parchis.fragments;

import java.util.ArrayList;

import parchis.ThreadHandler;
import parchis.game.DiceObserver;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class DiceFragment extends SketchFragment implements OnClickListener {

    private PFont segoeUIBold;
    private PImage[] diceImages;
    private final static int ANIMATION_TIME = 120;
    private final static String DICE_IMAGES_PATH = "src/data/board-images/dice/dice-%s.png";

    private int diceState = 1;
    private boolean diceLoading = false;
    private ArrayList<DiceObserver> diceObservers;

    public DiceFragment(PApplet sketch, float x, float y, float fragmentWidth, float fragmentHeight) {
        super(sketch, x, y, fragmentWidth, fragmentHeight);

        diceObservers = new ArrayList<>();

        segoeUIBold = sketch.createFont("Segoe UI Bold", 16);

        diceImages = new PImage[6];
        for (int i = 0; i < diceImages.length; i++) {
            diceImages[i] = sketch.loadImage(String.format(DICE_IMAGES_PATH, i + 1));
        }
    }

    public void registerDiceObserver(DiceObserver diceObserver){
        diceObservers.add(diceObserver);
    }

    @Override
    public void onClick() {
        boolean insideX = sketch.mouseX > x && sketch.mouseX < (x + fwidth);
        boolean insideY = sketch.mouseY > y && sketch.mouseY < (y + fheight);

        if (insideX && insideY) {
            if (!diceLoading) {
                diceLoading = true;
                ThreadHandler threadHandler = ThreadHandler.getInstance();
                threadHandler.doInBackground(new Runnable() {

                    @Override
                    public void run() {
                        for (int i = 0; i < ANIMATION_TIME; i++) {
                            diceState = (int) sketch.random(1, 7);
                            try {
                                Thread.sleep(16);
                            } catch (InterruptedException e) {
                                System.out.println(e);
                            }
                        }
                        diceLoading = false;
                        notifyObservers();
                    }

                });
            }
        }
    }

    private void notifyObservers() {
        for (DiceObserver diceObserver : diceObservers) {
            diceObserver.OnThrow(diceState);
        }
    }

    @Override
    public void update() {
        sketch.textFont(segoeUIBold);
        sketch.textSize(16);
        sketch.fill(255);
        sketch.text("Dado: ", x, getYMid() + 8);

        sketch.image(diceImages[diceState - 1], x + 50, 15, 48, 48);
    }

}
