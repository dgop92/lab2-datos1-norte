package parchis;

import parchis.interfaces.GUIUtils;
import parchis.interfaces.MenuPrincipal;

public class Parchis {
    
    public static void main(String[] args) {
        
        MenuPrincipal Interfaz = new MenuPrincipal();
        Interfaz.setBounds(GUIUtils.getBounds());
        Interfaz.setVisible(true);
    }
}
