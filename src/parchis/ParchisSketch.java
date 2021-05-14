package parchis;

import java.util.ArrayList;

import javax.swing.JFrame;

import parchis.fragments.BoardFragment;
import parchis.fragments.HeaderFragment;
import parchis.game.GameControl;
import parchis.game.players.Player;
import parchis.game.players.PlayerColor;
import parchis.game.views.GameControlView;
import parchis.interfaces.MenuFinal;
import processing.awt.PSurfaceAWT.SmoothCanvas;
import processing.core.PApplet;
import processing.core.PSurface;

public class ParchisSketch extends PApplet implements GameControlView{
    
    public static int SCREEN_WIDTH = 800;
    public static int SCREEN_HEIGHT = 600;

    private HeaderFragment header;
    private BoardFragment board;

    private GameControl gameControl;

    @Override
    public void settings() {
        size(SCREEN_WIDTH, SCREEN_HEIGHT);
    }
    
    public void initGameControl(ArrayList<PlayerColor> orders){
        gameControl = new GameControl(orders, this);
    }

    @Override
    public void setup() {
 
        header = new HeaderFragment(this, 0f, 0f, 800f, 80f, 
            gameControl.getHeaderState()
        );
        board = new BoardFragment(this, 0f, 80f, 800f, 520, 
            gameControl.getParchisList()
        );

        header.getDice().registerDiceObserver(gameControl);

    }

    @Override
    public void draw() {
        background(0);
        header.update();
        board.update();
    }

    public void run() {
        String[] processingArgs = { this.getClass().getName() };
        PApplet.runSketch(processingArgs, this);
    }


    @Override
    public void mouseClicked() {
        header.getDice().onClick();
    }

    @Override
    public void endOfTheGame(ArrayList<Player> podiumOrder) {
        MenuFinal Interfaz = new MenuFinal();
        Interfaz.setBounds(20, 20, 800,600 );
        Interfaz.setVisible(true);
        Interfaz.setPodimun(podiumOrder);

        this.dispose();
        PSurface surface = this.getSurface();
        SmoothCanvas smoothCanvas = (SmoothCanvas)surface.getNative();
        JFrame frame = (JFrame) smoothCanvas.getFrame();
        frame.dispose();
    }
}
