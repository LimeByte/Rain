package me.limebyte.rain.level;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.level.tile.Tile;

public abstract class Level {

    protected int width, height;
    protected int[] tiles;

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

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height) >> 4;
    }

    public Tile getTile(int x, int y) {
        if (tiles[x + y * width] == 0) return Tile.grass;
        return Tile.voidTile;
    }

}
