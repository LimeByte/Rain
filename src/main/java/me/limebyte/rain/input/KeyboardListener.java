package me.limebyte.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private boolean[] keys = new boolean[65536];
    public boolean up, down, left, right;
    public boolean faster, slower;

    public void update() {
        up = keys[KeyEvent.VK_W];
        left = keys[KeyEvent.VK_A];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];

        faster = keys[KeyEvent.VK_CLOSE_BRACKET];
        slower = keys[KeyEvent.VK_OPEN_BRACKET];
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

}
