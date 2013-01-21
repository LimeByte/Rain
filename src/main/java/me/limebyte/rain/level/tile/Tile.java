package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Sprite;

public abstract class Tile {

    public int x, y;
    public Sprite sprite;

    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile rock = new RockTile(Sprite.rock);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract void render(int x, int y, Screen screen);

    public abstract boolean isSolid();

    public static Tile getByID(int id) {
        if (id == 0) return voidTile;
        if (id == 1) return grass;
        if (id == 2) return rock;
        if (id == -16767233) return voidTile;
        return voidTile;
    }

    public static Tile getByColour(int colour) {
        // System.out.println(colour);
        if (colour == 0) return voidTile;
        if (colour == -16744690) return grass;
        if (colour == -12566464) return rock;
        if (colour == -16767233) return voidTile; // Water
        return voidTile;
    }

}
