package me.limebyte.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import me.limebyte.rain.Game;

public class KeyboardListener implements KeyListener {

    private boolean[] keys = new boolean[65536];
    public boolean up, down, left, right, music;
    private boolean musicKeyPressed = false;

    public void update() {
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];

        music = keys[KeyEvent.VK_M];

        if (music && !musicKeyPressed) {
            if (Game.song.isPlaying()) {
                Game.song.pause();
            } else {
                Game.song.play();
            }
            musicKeyPressed = true;
        }

        if (!music) {
            musicKeyPressed = false;
        }
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
