package me.limebyte.rain.level.tile;

import me.limebyte.rain.graphics.Screen;
import me.limebyte.rain.graphics.Sprite;

public abstract class Tile {

    public int x, y;
    public Sprite sprite;

    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile stone = new GrassTile(Sprite.stone);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract void render(int x, int y, Screen screen);

    public abstract boolean isSolid();

}
