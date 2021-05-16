package parchis.interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class GUIUtils {

    public static Rectangle getBounds() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - 800) / 2);
        int y = (int) ((dimension.getHeight() - 600) / 2);
        return new Rectangle(x, y, 800, 600);
    }
}
