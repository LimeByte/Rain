package me.limebyte.tank2d.level;

import me.limebyte.tank2d.graphics.Screen;

public abstract class Level {

    private int width, height;
    private int[] tiles;

    public Level() {

    }

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    protected abstract void generateLevel();

    protected void time() {

    }

    public abstract void update();

    public abstract void render(int xScroll, int yScroll, Screen screen);

}
