package me.limebyte.rain.level;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.level.tile.Tile;

public abstract class Level {

    private String name;
    public int width, height;
    protected int[] tiles;

    private int x0, x1, y0, y1;

    public Level(String name) {
        this.name = name;
    }

    public Level(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    protected abstract void generateLevel();

    public String getName() {
        return name;
    }

    public static Tile getTileAt(Location location) {
        return location.getLevel().getTile(location.getTileX(), location.getTileY());
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        return Tile.getByColour(tiles[x + y * width]);
    }

    protected void time() {

    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);

        x0 = xScroll >> 4;
        x1 = (xScroll + screen.width + 16) >> 4;
        y0 = yScroll >> 4;
        y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public void tick() {
        for (int tile : tiles) {
            Tile.getByColour(tile).tick();
        }
    }

}
