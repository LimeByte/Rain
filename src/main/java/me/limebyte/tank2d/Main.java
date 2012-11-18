package me.limebyte.tank2d;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        game.frame.setTitle(Game.TITLE);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setResizable(false);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.setFocusable(true);

        game.start();
    }

}
